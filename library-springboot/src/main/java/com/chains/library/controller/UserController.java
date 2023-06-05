package com.chains.library.controller;

import com.chains.library.common.Result;
import com.chains.library.controller.request.UserRequest;
import com.chains.library.entity.User;
import com.chains.library.service.IUserService;
import com.chains.library.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iuserService;

    @GetMapping("/list")
    public Result list(){
        List<User> list = iuserService.list();
        return Result.success(list);
    }
    @GetMapping("/page")
    public Result page(UserRequest userRequest){
        return Result.success(iuserService.page(userRequest));
    }
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        iuserService.save(user);
        return Result.success();
    }
}
