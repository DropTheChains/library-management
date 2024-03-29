package com.chains.library.service;

import com.chains.library.controller.dto.LoginDTO;
import com.chains.library.controller.request.AdminRequest;
import com.chains.library.controller.request.PasswordRequest;
import com.chains.library.controller.request.UserRequest;
import com.chains.library.entity.Admin;
import com.chains.library.entity.User;

import java.util.List;

public interface IAdminService {
    List<Admin> list();

    Object page(AdminRequest adminRequest);

    Integer save(Admin admin);

    Admin getById(Integer id);

    List<Integer> getAllId();

    Boolean update(Admin admin);

    void delById(Integer id);

    LoginDTO login(AdminRequest adminRequest);

    void changePass(PasswordRequest request);
}
