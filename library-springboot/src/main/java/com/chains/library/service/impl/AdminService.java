package com.chains.library.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.chains.library.controller.request.AdminRequest;
import com.chains.library.controller.request.UserRequest;
import com.chains.library.entity.Admin;
import com.chains.library.entity.User;
import com.chains.library.mapper.AdminMapper;
import com.chains.library.service.IAdminService;
import com.chains.library.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminService implements IAdminService {

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
        Date date = new Date();
        admin.setCreateTime(new Date());
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

}
