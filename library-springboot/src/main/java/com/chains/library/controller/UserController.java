package com.chains.library.controller;

import com.chains.library.entity.User;
import com.chains.library.service.IUserService;
import com.chains.library.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iuserService;

    @GetMapping("/list")
    public List<User> list(){
        List<User> list = iuserService.list();
        return list;
    }
}
