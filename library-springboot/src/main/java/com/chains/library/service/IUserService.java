package com.chains.library.service;

import com.chains.library.controller.request.UserRequest;
import com.chains.library.entity.User;
import org.apache.catalina.LifecycleState;


import java.util.List;

public interface IUserService {
    List<User> list();

    Object page(UserRequest userRequest);

    Integer save(User user);

    User getById(Integer id);

    Integer update(User user);

    void delById(Integer id);

    void handleAccount(User user);
}
