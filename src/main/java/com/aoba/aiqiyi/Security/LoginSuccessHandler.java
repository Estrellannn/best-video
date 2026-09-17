package com.aoba.aiqiyi.Security;

import com.aoba.aiqiyi.Entiy.Users;
import com.aoba.aiqiyi.Mapper.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        // 调用你已有的mapper方法，查询完整用户（包含nickname）
        Users loginUser = userMapper.selectUserAndRoleByUsername(username);

        //存入session！前端thymeleaf就能读取 loginUser
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser);

        response.sendRedirect("/");
    }
}
