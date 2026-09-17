package com.aoba.aiqiyi;

import com.aoba.aiqiyi.Entiy.Video;
import com.aoba.aiqiyi.Mapper.MainMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VideoTest {
    @Autowired
    private MainMapper mainMapper;
    @Test
    void testSelectTvVideo(){
        // 直接调用 selectTvVideo（就是你tv页面的查询SQL）
        List<Video> tvList = mainMapper.selectTvVideo();
        System.out.println("=====查到的电视列表，总条数："+tvList.size());
        for(Video v : tvList){
            System.out.println("id:"+v.getId() + " 标题:"+v.getTitle());
        }
    }
}
