package com.aoba.aiqiyi;

import com.aoba.aiqiyi.util.RedisCacheUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

@SpringBootTest
public class VideoCacheClearTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    @Test
//    void clearAllVideoCache() {
//        @Autowired
//        private RedisCacheUtil redisCacheUtil;
//
//        @Test
//        void clearAllVideoCache() {
//            // 这里改成你项目视频缓存的key前缀！！！
//            // 如果你的key是 video::16 就写 "video::"
//            // 如果你的key是 video:16 就写 "video:"
//            String keyPrefix = "video:";
//            redisCacheUtil.deleteAllByPrefix(keyPrefix);
//            System.out.println("✅ 全部视频缓存已清空");
//        }
}
