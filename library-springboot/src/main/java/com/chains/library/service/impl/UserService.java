package com.chains.library.service.impl;

import com.chains.library.controller.request.UserRequest;
import com.chains.library.entity.User;
import com.chains.library.mapper.UserMapper;
import com.chains.library.service.IUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> list() {
        List<User> list = userMapper.list();
        return list;
    }

    @Override
    public Object page(UserRequest userRequest) {
        PageHelper.startPage(userRequest.getPageNum(), userRequest.getPageSize());
        //PageHelper.startPage相当于开启分页,通过拦截MySQL的方式,把你的查询语句拦截下来加limit.
        //你需要将你的查询语句放到这个PageHelper.startPage的后面进行执行。这样才能用到拦截功能。
        List<User> userList = userMapper.listByCondition(userRequest);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

}
