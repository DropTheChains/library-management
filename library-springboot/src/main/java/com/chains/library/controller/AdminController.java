package com.chains.library.controller;

import com.chains.library.common.Result;
import com.chains.library.controller.dto.LoginDTO;
import com.chains.library.controller.request.AdminRequest;
import com.chains.library.controller.request.UserRequest;
import com.chains.library.entity.Admin;
import com.chains.library.entity.User;
import com.chains.library.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService iAdminService;
    @PostMapping("/login")
    public Result login(@RequestBody AdminRequest adminRequest){
        LoginDTO login = iAdminService.login(adminRequest);
        return Result.success(login);
    }

    @GetMapping("/list")
    public Result list(){
        List<Admin> list = iAdminService.list();
        return Result.success(list);
    }
    @GetMapping("/page")
    public Result page(AdminRequest adminRequest){
        return Result.success(iAdminService.page(adminRequest));
    }
    @PostMapping("/save")
    public Result save(@RequestBody Admin admin){
        int returnid = iAdminService.save(admin);
        return Result.success(returnid);
    }
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id){
        return Result.success(iAdminService.getById(id));
    }

    @PostMapping("/update")
    public Result update(@RequestBody Admin admin){
        Boolean update = iAdminService.update(admin);
        if (update){
            return Result.success();
        }else {
            return Result.error("没有此账户");
        }
    }
    @GetMapping("/delete/{id}")
    public Result delById(@PathVariable Integer id){
        iAdminService.delById(id);
        return Result.success();
    }
}
