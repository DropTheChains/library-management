package com.chains.library.service.impl;

import com.chains.library.entity.User;
import com.chains.library.mapper.UserMapper;
import com.chains.library.service.IUserService;
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
}
