package com.aoba.aiqiyi.Mapper;

import com.aoba.aiqiyi.Entiy.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface UserMapper {

    Users selectUserAndRoleByUsername(String username);

    Users selectByUsername(String username);

    int insertUser(Users users);
}
