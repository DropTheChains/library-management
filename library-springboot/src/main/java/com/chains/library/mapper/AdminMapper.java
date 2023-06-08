package com.chains.library.mapper;

import com.chains.library.controller.request.AdminRequest;
import com.chains.library.controller.request.UserRequest;
import com.chains.library.entity.Admin;
import com.chains.library.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
//    @Select("select * from user")
    List<Admin> list();

    List<Admin> listByCondition(AdminRequest adminRequest);

    Integer save(Admin admin);

    Admin getById(Integer id);

    Integer update(Admin admin);

    void delById(Integer id);


    List<Integer> getAllId();

    Admin login(AdminRequest adminRequest);
}
