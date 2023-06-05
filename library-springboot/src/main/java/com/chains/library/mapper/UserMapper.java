package com.chains.library.mapper;

import com.chains.library.controller.request.UserRequest;
import com.chains.library.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
//    @Select("select * from user")
    List<User> list();

    List<User> listByCondition(UserRequest userRequest);

    void save(User user);
}
