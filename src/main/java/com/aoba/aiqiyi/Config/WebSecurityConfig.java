package com.aoba.aiqiyi.Config;

import com.aoba.aiqiyi.Security.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LoginSuccessHandler LoginSuccessHandler;

    /**
     * 密码加密器：全局统一使用BCrypt加密，两种版本通用
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 权限拦截、登录、退出配置，MyBatis/MP无区别
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 权限放行规则
        http.authorizeHttpRequests(auth -> auth

                // 登录页面、静态资源（css、图片）直接放行，无需登录
                .requestMatchers("/","/login", "/register","/doRegister", "/css/**", "/img/**").permitAll()
                // 影视、动漫、电影页面：普通用户、管理员两种角色都可以访问
                .requestMatchers("/tv", "/anime", "/film","/bofang","/search").hasAnyRole("USER", "ADMIN")
                // 后台管理接口：仅管理员角色可以访问
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // 剩余所有请求，必须登录认证后才能访问
                .anyRequest().authenticated()
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .successHandler(LoginSuccessHandler)
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout")
                .permitAll()
        );

        // 防iframe嵌套，只允许同源页面嵌入
        http.headers(headers -> headers.frameOptions(config -> config.sameOrigin()));

        return http.build();
    }
}