package com.aoba.aiqiyi;

import com.aoba.aiqiyi.Entiy.VideoFile;
import com.aoba.aiqiyi.Mapper.VideoFileMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VideoFileTest {
    // 注入你的mapper
    @Autowired
    private VideoFileMapper videoFileMapper;

    @Test
    void testSelectOne() {
        // ========== 这里改成你数据库真实的 v_id 和 num ==========
        Integer testVid = 35;
        Integer testNum = 1;
        // 调用xml里面写好的方法 selectByVidAndNum
        VideoFile videoFile = videoFileMapper.selectByVidAndNum(testVid, testNum);

        if(videoFile == null){
            System.out.println("查询结果：null，没有查到这条数据");
            return;
        }
        System.out.println("id = " + videoFile.getId());
        System.out.println("v_id = " + videoFile.getV_id());
        System.out.println("num = " + videoFile.getNum());
        System.out.println("videopath = " + videoFile.getVideopath());
        System.out.println("name = " + videoFile.getName()); //重点看这一行！
    }

    // 顺带测试选集列表 selectListByVid
    @Test
    void testSelectList() {
        Integer testVid = 1;
        var list = videoFileMapper.selectListByVid(testVid);
        for(VideoFile v : list){
            System.out.println("【集号"+v.getNum()+"】 name="+v.getName() + " path="+v.getVideopath());
        }
    }
}
