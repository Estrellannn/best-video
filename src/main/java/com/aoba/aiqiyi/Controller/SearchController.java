package com.aoba.aiqiyi.Controller;

// 修复拼写错误 Entiy → Entity
import com.aoba.aiqiyi.Entiy.Category;
import com.aoba.aiqiyi.Entiy.Video;
import com.aoba.aiqiyi.Mapper.MainMapper;
import com.aoba.aiqiyi.Service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private MainService mainService;

    @Autowired
    private MainMapper mainMapper;

    @GetMapping("/search")
    public String search(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "cid", required = false, defaultValue = "0") Integer cid,
            Model model
    ){
        List<Video> list;
        String trimKw = keyword.trim();
        // 空关键词：只按分类查全部，不做模糊搜索
        if(trimKw.isEmpty()){
            list = mainMapper.searchVideo("", cid);
        }else{
            // 有文字才传关键词去模糊匹配
            list = mainMapper.searchVideo(trimKw, cid);
        }
        model.addAttribute("videoList", list);
        model.addAttribute("searchKey", keyword);
        return "search";
    }

    @GetMapping("/admin/search")
    public String adminsearch(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "cid", required = false, defaultValue = "0") Integer cid,
            Model model
    ){
        List<Video> list;
        String trimKw = keyword.trim();
        // 空关键词：只按分类查全部，不做模糊搜索
        if(trimKw.isEmpty()){
            list = mainMapper.searchVideo("", cid);
        }else{
            // 有文字才传关键词去模糊匹配
            list = mainMapper.searchVideo(trimKw, cid);
        }

        // ========== 新增两行代码 ==========
        // 1. 查询所有分类
        List<Category> allCategory = mainService.selectAllCategory();
        // 2. 传给页面，弹窗才能循环渲染复选框
        model.addAttribute("allCategory", allCategory);

        model.addAttribute("videoList", list);
        model.addAttribute("searchKey", keyword);
        return "admin/searchtv";
    }

    @GetMapping("/admin/searcha")
    public String adminsearcha(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "cid", required = false, defaultValue = "0") Integer cid,
            Model model
    ){
        List<Video> list;
        String trimKw = keyword.trim();

        if(trimKw.isEmpty()){
            list = mainMapper.searchVideo("", cid);
        }else{
            // 有文字才传关键词去模糊匹配
            list = mainMapper.searchVideo(trimKw, cid);
        }

        List<Category> allCategory = mainService.selectAllCategory();

        model.addAttribute("allCategory", allCategory);
        model.addAttribute("videoList", list);
        model.addAttribute("searchKey", keyword);
        return "admin/searchanime";
    }

    @GetMapping("/admin/searchf")
    public String adminsearchf(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "cid", required = false, defaultValue = "0") Integer cid,
            Model model
    ){
        List<Video> list;
        String trimKw = keyword.trim();

        if(trimKw.isEmpty()){
            list = mainMapper.searchVideo("", cid);
        }else{

            list = mainMapper.searchVideo(trimKw, cid);
        }

        List<Category> allCategory = mainService.selectAllCategory();

        model.addAttribute("allCategory", allCategory);
        model.addAttribute("videoList", list);
        model.addAttribute("searchKey", keyword);
        return "admin/searchfilme";
    }

}