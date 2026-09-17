package com.aoba.aiqiyi;

import com.aoba.aiqiyi.Entiy.Category;
import com.aoba.aiqiyi.Entiy.Video;
import com.aoba.aiqiyi.Service.MainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryTest {
    @Autowired
    private MainService mainService;

    // 测试查询全部分类，判断后端有没有数据
    @Test
    void testSelectAllCategory(){
        List<Category> list = mainService.selectAllTvCategroy();
        System.out.println("分类总条数："+list.size());
        if(list.isEmpty()){
            System.out.println("❌ 没有查到任何分类，问题在Mapper SQL");
        }else{
            for (Category cat : list) {
                System.out.println("id="+cat.getId() + " 名称="+cat.getName());
            }
            System.out.println("✅ 数据正常，问题只在前端Thymeleaf语法");
        }
    }
    // 模拟电视剧页面筛选：选中题材cid=1（喜剧），电视剧大类id=11
    @Test
    void testTvFilterVideo() {
        // 模拟前端传入的cidList（只读集合，模拟浏览器传参场景）
        List<Integer> inputCid = List.of(1);

        // 调用你修复后的筛选方法
        List<Video> result = mainService.listVideoByCidList(inputCid, 11);

        System.out.println("====筛选结果====");
        System.out.println("查询到影视数量：" + result.size());
        for (Video video : result) {
            System.out.println("影视id:" + video.getId() + " 名称:" + video.getTitle());
        }
    }

    // 单独测试只读集合add报错问题
    @Test
    void testUnmodifiableList() {
        // Spring传入的List.of()是只读集合，直接add会抛异常
        List<Integer> readOnlyList = List.of(1);
        // readOnlyList.add(11); // 放开此行会直接复现 500报错 UnsupportedOperationException

        // 你的修复方案测试
        List<Integer> paramList = new ArrayList<>();
        paramList.addAll(readOnlyList);
        paramList.add(11);
        System.out.println("转换后可修改集合：" + paramList);
    }

    @Test
    void testListTvWithCategory() {
        // 执行查询
        List<Video> tvList = mainService.listTvAdminWithCategory();

        // 循环打印每一条影视 + 绑定的全部分类
        for (Video video : tvList) {
            System.out.println("======================================");
            System.out.println("影视ID：" + video.getId());
            System.out.println("剧名：" + video.getTitle());
            System.out.println("绑定分类集合：" + video.getCategoryList());

            // 手动拆分 题材(1~10) / 地区(>11)，控制台模拟页面效果
            StringBuilder typeSb = new StringBuilder();
            StringBuilder areaSb = new StringBuilder();
            for (var cat : video.getCategoryList()) {
                if (cat.getId() >= 1 && cat.getId() <= 10) {
                    typeSb.append(cat.getName()).append(",");
                }
                if (cat.getId() > 11) {
                    areaSb.append(cat.getName()).append(",");
                }
            }

            String type = typeSb.length() > 0 ? typeSb.substring(0, typeSb.length()-1) : "-";
            String area = areaSb.length() > 0 ? areaSb.substring(0, areaSb.length()-1) : "-";
            System.out.println("页面渲染-类型：" + type);
            System.out.println("页面渲染-地区：" + area);
        }
    }

    @Test
    void updateVideoCategory() {
        Integer videoId = 35;
        List<Integer> cidList = List.of(11);
        mainService.updateVideoCategory(videoId, cidList);
        System.out.println("更新分类成功");
    }

    @Test
    void cleartv(){
        mainService.clearTvCache();
        System.out.println("yes");
    }

}
