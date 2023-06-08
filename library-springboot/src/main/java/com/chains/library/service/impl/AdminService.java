package com.chains.library.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.chains.library.controller.dto.LoginDTO;
import com.chains.library.controller.request.AdminRequest;
import com.chains.library.entity.Admin;
import com.chains.library.exception.ServiceException;
import com.chains.library.mapper.AdminMapper;
import com.chains.library.service.IAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        return adminMapper.save(admin);
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
        adminRequest.setPassword(securePass(adminRequest.getPassword()));
        Admin login = adminMapper.login(adminRequest);
        if (login == null){
            throw new ServiceException("用户名或密码错误！");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(login,loginDTO);
        return loginDTO;
    }

    public String securePass(String pass){
        return SecureUtil.md5(pass + PASS_SALT);
    }

}
