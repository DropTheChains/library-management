package com.chains.library.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.chains.library.controller.dto.LoginDTO;
import com.chains.library.controller.request.AdminRequest;
import com.chains.library.controller.request.PasswordRequest;
import com.chains.library.entity.Admin;
import com.chains.library.exception.ServiceException;
import com.chains.library.mapper.AdminMapper;
import com.chains.library.service.IAdminService;
import com.chains.library.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class AdminService implements IAdminService {

    private static final String DEFAULT_PASS = "admin" ;
    private static final String PASS_SALT = "qwer";

    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Admin> list() {
        List<Admin> list = adminMapper.list();
        return list;
    }

    @Override
    public Object page(AdminRequest adminRequest) {
        PageHelper.startPage(adminRequest.getPageNum(), adminRequest.getPageSize());
        //PageHelper.startPage相当于开启分页,通过拦截MySQL的方式,把你的查询语句拦截下来加limit.
        //你需要将你的查询语句放到这个PageHelper.startPage的后面进行执行。这样才能用到拦截功能。
        List<Admin> adminList = adminMapper.listByCondition(adminRequest);
        PageInfo<Admin> pageInfo = new PageInfo<>(adminList);
        return pageInfo;
    }

    @Override
    public Integer save(Admin admin) {
        if (StrUtil.isBlank(admin.getPassword())){
            admin.setPassword(securePass(DEFAULT_PASS));
        }
        admin.setPassword(securePass(admin.getPassword()));
        Date date = new Date();
        admin.setCreateTime(date);
        Integer saveint = null;
        try {
            saveint = adminMapper.save(admin);
        }catch (Exception e){
            if (e.getMessage().contains("Duplicate entry")){
                log.error("数据插入失败，username:{}",admin.getUsername());
                throw new ServiceException("数据插入失败，用户名重复");
            }
        }

        return saveint;
    }

    @Override
    public Admin getById(Integer id) {
        Admin byId = adminMapper.getById(id);
        return byId;
    }

    @Override
    public List<Integer> getAllId() {
        List<Integer> allId = adminMapper.getAllId();
        return allId;
    }

    @Override
    public Boolean update(Admin admin) {
       List<Integer> allId = getAllId();
            if (allId.contains(admin.getId())){
                admin.setUpdateTime(new Date());
                adminMapper.update(admin);
                return true;
            }else{
                return false;
        }
    }

    @Override
    public void delById(Integer id) {
        adminMapper.delById(id);
    }

    @Override
    public LoginDTO login(AdminRequest adminRequest) {

        Admin admin = null;

        try {
            admin = adminMapper.getByUsername(adminRequest.getUsername());
        }catch (Exception e){
            log.error("根据用户名{}出错",adminRequest.getUsername());
            throw new ServiceException("用户名错误！");
        }

        adminRequest.setPassword(securePass(adminRequest.getPassword()));
        Admin login = adminMapper.login(adminRequest);
        if (login == null){
            throw new ServiceException("用户名或密码错误！");
        }
        if (!login.getStatus()){
            throw new ServiceException("账户被禁用，请联系管理员！");
        }

        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(login,loginDTO);
        String genToken = TokenUtils.genToken(String.valueOf(login.getId()), login.getPassword());
        loginDTO.setToken(genToken);
        return loginDTO;
    }

    @Override
    public void changePass(PasswordRequest request) {
        String securePass = securePass(request.getNewPass());
        adminMapper.changePass(request.getId(), securePass);
    }

    public String securePass(String pass){
        return SecureUtil.md5(pass + PASS_SALT);
    }

}
