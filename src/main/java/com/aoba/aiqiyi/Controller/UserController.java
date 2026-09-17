package com.aoba.aiqiyi.Controller;

import com.aoba.aiqiyi.Entiy.Users;
import com.aoba.aiqiyi.Mapper.UserMapper;
import com.aoba.aiqiyi.Mapper.UserRoleMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/doRegister")
    public String doRegister(@RequestParam String username,
                             @RequestParam String nickname,
                             @RequestParam String password,
                             Model model){

        Users existUser = userMapper.selectByUsername(username);
        if(existUser != null){
            model.addAttribute("msg","用户名已存在！");
            return "register";
        }

        //2. 新建用户，密码加密
        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setNickname(nickname);
        // BCrypt加密密码存入数据库
        newUser.setPassword(passwordEncoder.encode(password));

        userMapper.insertUser(newUser);
        Integer uid = newUser.getId();
        userRoleMapper.insertUserRole(uid,1);
        model.addAttribute("successMsg", "注册成功！即将跳转登录");
        return "register";


    }

}
