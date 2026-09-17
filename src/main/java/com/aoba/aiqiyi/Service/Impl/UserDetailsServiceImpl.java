package com.aoba.aiqiyi.Service.Impl;

import com.aoba.aiqiyi.Entiy.Role;
import com.aoba.aiqiyi.Entiy.Users;
import com.aoba.aiqiyi.Mapper.UserMapper;
import com.aoba.aiqiyi.Security.LoginUser;
import com.aoba.aiqiyi.util.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 原生MyBatis版本 自定义用户详情服务
 * 和MyBatis-Plus版本区别：
 * 1、MP：使用MP内置条件构造器查询用户
 * 2、MyBatis：调用自己写的Mapper方法，执行XML手写SQL查询用户+角色
 * 权限封装、异常判断、返回UserDetails 逻辑完全一致
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    /**
     * Security登录自动回调该方法，校验账号、封装权限
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // =========【唯一区别】原生MyBatis调用Mapper查询，不是MP的Service查询=========
        //Users loginUser = userMapper.selectUserAndRoleByUsername(username);

        // 定义登录缓存key
        String cacheKey = "security:login:user:" + username;
        Users loginUser = null;
        // 第一步：先从Redis缓存读取用户信息
        Object cacheData = redisCacheUtil.get(cacheKey);
        if (cacheData != null) {
            loginUser = (Users) cacheData;
        } else {
            // 第二步：缓存不存在，才去查数据库
            loginUser = userMapper.selectUserAndRoleByUsername(username);

            // 1、判断用户是否存在，不存在抛出异常，登录失败
            if (loginUser == null) {
                throw new UsernameNotFoundException("用户名：" + username + " 不存在");
            }

            // 把查询到的用户存入Redis，缓存30分钟
            redisCacheUtil.set(cacheKey, loginUser, 30);
        }

        // 2、封装角色为Security权限对象
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Role> roleList = loginUser.getRoleList();
        for (Role role : roleList) {
            authorityList.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        System.out.println("当前用户角色：" + roleList);
        // 3、封装自定义UserDetails返回
        return new LoginUser(loginUser, roleList);
    }

//        提供清除登录缓存的方法
//        修改用户信息、重置密码时调用，保证数据一致性

    public void clearLoginCache(String username) {
        String cacheKey = "security:login:user:" + username;
        redisCacheUtil.delete(cacheKey);
    }

}