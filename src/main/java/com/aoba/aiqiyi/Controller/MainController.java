package com.aoba.aiqiyi.Controller;
import com.aoba.aiqiyi.Entiy.Banner;
import com.aoba.aiqiyi.Entiy.Category;
import com.aoba.aiqiyi.Entiy.R;
import org.springframework.ui.Model;
import com.aoba.aiqiyi.Entiy.Video;
import com.aoba.aiqiyi.Service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller

public class MainController {
    @Autowired
    private MainService mainService;

    @GetMapping("/")
    public String index(Model model){
        List<Video> videoList = mainService.getTvList();
        List<Video> showVideoList = videoList.stream()
                .limit(12)
                .collect(Collectors.toList());
        model.addAttribute("videoList",showVideoList);

        List<Video> tv=mainService.selectFilm();
        List<Video> showTv=tv.stream().limit(12).collect(Collectors.toList());
        model.addAttribute("showFilm",showTv);

        List<Video> anime=mainService.getAnimeList();
        List<Video> showAnime=anime.stream().limit(12).collect(Collectors.toList());
        model.addAttribute("showAnime",showAnime);

        List<Banner> bannerList=mainService.selectAll();
        model.addAttribute("bannerList",bannerList);

        return "main";
    }

    @GetMapping("/tv")
    public String tv(
            Model model,
            @RequestParam(value = "cids",required = false) List<Integer> cidList
    ){
        Integer tvMainCid = 14; // 电视剧主分类id固定14
        List<Video> tvVideoList;

        // 两个标记，传给页面控制【全部】按钮高亮
        boolean hasThemeSelect = false;
        boolean hasAreaSelect = false;

        // 判断是否有筛选条件
        if(cidList == null || cidList.isEmpty()){
            // 无筛选，查询全部电视剧
            tvVideoList = mainService.getTvList();
        }else{
            // =========新增这一块过滤逻辑=========
            List<Integer> themeList = cidList.stream().filter(i -> i >= 1 && i <=13).toList();
            List<Integer> areaList = cidList.stream().filter(i -> i >=17 && i <=26).toList();

            // 判断有没有选中题材、有没有选中地区
            hasThemeSelect = !themeList.isEmpty();
            hasAreaSelect = !areaList.isEmpty();

            List<Integer> realCids = new java.util.ArrayList<>();
            if(!themeList.isEmpty()){
                realCids.add(themeList.get(0));
            }
            if(!areaList.isEmpty()){
                realCids.add(areaList.get(0));
            }
            // 多分类筛选（题材+地区）
            tvVideoList = mainService.listVideoByCidList(realCids, tvMainCid);
        }
        // 全部分类（渲染筛选按钮）
        List<Category> tvCategory = mainService.selectAllTvCategroy();

        model.addAttribute("category", tvCategory);
        model.addAttribute("Tv", tvVideoList);
        // 传给页面当前选中的分类id，用于按钮高亮
        model.addAttribute("selectedCids", cidList);
        // 把两个布尔标记传到thymeleaf页面
        model.addAttribute("hasThemeSelect", hasThemeSelect);
        model.addAttribute("hasAreaSelect", hasAreaSelect);
        return "tv";
    }

    @GetMapping("/anime")
    public String anime(Model model,
                        @RequestParam(value = "cids",required = false) List<Integer> cidList){

        Integer animeMainCid = 16;
        List<Video> animeList;

        boolean hasThemeSelect = false;
        boolean hasAreaSelect = false;

        if(cidList == null || cidList.isEmpty()){
            animeList = mainService.getAnimeList();
        }else{

            List<Integer> themeList = cidList.stream().filter(i -> i >= 1 && i <=13).toList();
            List<Integer> areaList = cidList.stream().filter(i -> i >=17 && i <=26).toList();

            hasThemeSelect = !themeList.isEmpty();
            hasAreaSelect = !areaList.isEmpty();

            List<Integer> realCids = new java.util.ArrayList<>();
            if(!themeList.isEmpty()){
                realCids.add(themeList.get(0));
            }
            if(!areaList.isEmpty()){
                realCids.add(areaList.get(0));
            }

            animeList = mainService.listVideoByCidList(cidList, animeMainCid);
        }
        List<Category> categoryList = mainService.selectAllTvCategroy();

        model.addAttribute("category", categoryList);
        model.addAttribute("anime", animeList);
        model.addAttribute("selectedCids", cidList);
        model.addAttribute("hasThemeSelect", hasThemeSelect);
        model.addAttribute("hasAreaSelect", hasAreaSelect);
        return "anime";
    }

    @GetMapping("/film")
    public String film(Model model,
                       @RequestParam(value = "cids",required = false) List<Integer> cidList){

        Integer filmMainCid = 15;
        List<Video> filmList;

        boolean hasThemeSelect = false;
        boolean hasAreaSelect = false;

        if(cidList == null || cidList.isEmpty()){
            filmList = mainService.getFilemList();
        }else{

            List<Integer> themeList = cidList.stream().filter(i -> i >= 1 && i <=13).toList();
            List<Integer> areaList = cidList.stream().filter(i -> i >=17 && i <=26).toList();

            hasThemeSelect = !themeList.isEmpty();
            hasAreaSelect = !areaList.isEmpty();

            List<Integer> realCids = new java.util.ArrayList<>();
            if(!themeList.isEmpty()){
                realCids.add(themeList.get(0));
            }
            if(!areaList.isEmpty()){
                realCids.add(areaList.get(0));
            }

            filmList = mainService.listVideoByCidList(cidList, filmMainCid);
        }
        List<Category> categoryList = mainService.selectAllTvCategroy();
        model.addAttribute("category", categoryList);
        model.addAttribute("film", filmList);
        model.addAttribute("selectedCids", cidList);
        model.addAttribute("hasThemeSelect", hasThemeSelect);
        model.addAttribute("hasAreaSelect", hasAreaSelect);
        return "film";
    }

    @GetMapping("/admin")
    public String manage(Model model){
        List<Category> fenlei =mainService.selectAllfeilei();
        model.addAttribute("leibie",fenlei);
        return "admin/manage";
    }

    @PostMapping("/category")
    public String updateCategory(Category category){
        mainService.updateCategory(category);
        return "redirect:/admin";
    }

    @PostMapping("/category/delete")
    public String deleteCategory(Integer id){
        mainService.deleteCategory(id);
        return "redirect:/admin";
    }

    @PostMapping("/category/ajaxDelete")
    @ResponseBody
    public R ajaxDeleteCategory(Integer id) {
        try {
            mainService.deleteCategory(id);
            return R.ok("删除成功");
        } catch (Exception e) {
            return R.fail("删除失败：该分类存在关联视频，无法删除");
        }
    }

    @PostMapping("/category/add")
    public String addCategory(Category category){
        mainService.addcategory(category);
        return "redirect:/admin";
    }
}
