package com.aoba.aiqiyi.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    @Insert("insert into user_role(user_id,role_id) values(#{userId},#{roleId})")
    int insertUserRole(Integer userId,Integer roleId);
}
