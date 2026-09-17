package com.aoba.aiqiyi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AiqiyiApplicationTests {

    @Test
    void contextLoads() {
    }

    // 密码加密测试方法
    @Test
    void testPasswordEncode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd1 = encoder.encode("123");
        String pwd2 = encoder.encode("456");
        System.out.println("123 加密后：" + pwd1);
        System.out.println("456 加密后：" + pwd2);
    }
}
