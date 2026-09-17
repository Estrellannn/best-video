package com.aoba.aiqiyi.util;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 设置缓存，带过期时间（单位分钟）
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
    }

    // 获取缓存
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除缓存
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
//固定套路（通用工具，所有页面共用）
//注入 Spring 自带RedisTemplate；
//封装 3 个基础方法：存、取、删；
//@Component交给 Spring 管理，Service 里直接@Autowired注入使用。