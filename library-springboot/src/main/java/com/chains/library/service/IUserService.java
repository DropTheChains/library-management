package com.chains.library.service;

import com.chains.library.entity.User;
import org.apache.catalina.LifecycleState;


import java.util.List;

public interface IUserService {
    List<User> list();
}
