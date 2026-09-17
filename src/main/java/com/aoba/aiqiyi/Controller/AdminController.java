package com.aoba.aiqiyi.Controller;

import com.aoba.aiqiyi.Entiy.Category;
import com.aoba.aiqiyi.Entiy.Video;
import com.aoba.aiqiyi.Entiy.VideoFile;
import com.aoba.aiqiyi.Service.MainService;
import com.aoba.aiqiyi.Service.VideoEpService;
import com.aoba.aiqiyi.util.LocalImgUtil;
import com.aoba.aiqiyi.util.RedisCacheUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    public MainService mainService;
    @Autowired
    private LocalImgUtil localImgUtil;

    @GetMapping("/admin/tv")
    public String atv(@RequestParam(defaultValue = "1") Integer pageNum,Model model){
        // 开启分页：pageNum当前页码，每页10条
        PageHelper.startPage(pageNum, 10);
        // 原来的查询方法不变
        List<Video> alltv = mainService.selectTv();
        // 包装分页信息
        PageInfo<Video> pageInfo = new PageInfo<>(alltv);

        // 传给前端，前端用pageInfo取数据
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("videoList",alltv);
        // 查询当前最大id，传给页面
        Integer maxId = mainService.getMaxVideoId();
        model.addAttribute("maxId", maxId);
        // 编辑弹窗需要全部分类下拉
        List<Category> allCategory = mainService.selectAllCategory();
        model.addAttribute("allCategory", allCategory);
        return "admin/atv";

    }

    @GetMapping("/admin/getVideoCat")
    @ResponseBody
    public Map<String,Object> getVideoCat(Integer vid){
        List<Category> list = mainService.getCatByVid(vid);
        StringBuilder typeSb = new StringBuilder();
        StringBuilder areaSb = new StringBuilder();
        List<Integer> cidList = new ArrayList<>();
        for(Category c : list){
            cidList.add(c.getId());
            if(c.getId()>=1 && c.getId()<=13) typeSb.append(c.getName()).append(",");
            if(c.getId()>16) areaSb.append(c.getName()).append(",");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("typeStr", typeSb.length()>0 ? typeSb.substring(0,typeSb.length()-1) : "-");
        map.put("areaStr", areaSb.length()>0 ? areaSb.substring(0,areaSb.length()-1) : "-");
        map.put("catIds", cidList);
        return map;
    }

    @GetMapping("/admin/getAVideoCat")
    @ResponseBody
    public Map<String,Object> getAVideoCat(Integer vid){
        List<Category> list = mainService.getCatByVid(vid);
        StringBuilder typeSb = new StringBuilder();
        StringBuilder areaSb = new StringBuilder();
        List<Integer> cidList = new ArrayList<>();
        for(Category c : list){
            cidList.add(c.getId());
            if(c.getId()>=1 && c.getId()<=13) typeSb.append(c.getName()).append(",");
            if(c.getId()>16) areaSb.append(c.getName()).append(",");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("typeStr", typeSb.length()>0 ? typeSb.substring(0,typeSb.length()-1) : "-");
        map.put("areaStr", areaSb.length()>0 ? areaSb.substring(0,areaSb.length()-1) : "-");
        map.put("catIds", cidList);
        return map;
    }

    @GetMapping("/admin/getFVideoCat")
    @ResponseBody
    public Map<String,Object> getFVideoCat(Integer vid){
        List<Category> list = mainService.getCatByVid(vid);
        StringBuilder typeSb = new StringBuilder();
        StringBuilder areaSb = new StringBuilder();
        List<Integer> cidList = new ArrayList<>();
        for(Category c : list){
            cidList.add(c.getId());
            if(c.getId()>=1 && c.getId()<=13) typeSb.append(c.getName()).append(",");
            if(c.getId()>16) areaSb.append(c.getName()).append(",");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("typeStr", typeSb.length()>0 ? typeSb.substring(0,typeSb.length()-1) : "-");
        map.put("areaStr", areaSb.length()>0 ? areaSb.substring(0,areaSb.length()-1) : "-");
        map.put("catIds", cidList);
        return map;
    }

    @PostMapping("/admin/updateFVideoCat")
    @ResponseBody
    public String updateFVideoCat(@RequestBody Map<String,Object> param){
        // 容错1：vid为空拦截
        Object vidObj = param.get("vid");
        if(vidObj == null){
            throw new RuntimeException("影视ID不能为空");
        }
        Integer vid = Integer.valueOf(vidObj.toString());

        // 容错2：cidList为null时赋值空集合，避免强转报错
        List<Integer> cidList = new ArrayList<>();
        Object arrObj = param.get("cidList");
        if(arrObj != null){
            cidList = (List<Integer>) arrObj;
        }
        mainService.updateFVideoCategory(vid, cidList);
        mainService.clearFilmList();
        return "ok";
    }

    @PostMapping("/admin/updateAVideoCat")
    @ResponseBody
    public String updateAVideoCat(@RequestBody Map<String,Object> param){

        Object vidObj = param.get("vid");
        if(vidObj == null){
            throw new RuntimeException("影视ID不能为空");
        }
        Integer vid = Integer.valueOf(vidObj.toString());

        List<Integer> cidList = new ArrayList<>();
        Object arrObj = param.get("cidList");
        if(arrObj != null){
            cidList = (List<Integer>) arrObj;
        }
        mainService.updateAVideoCategory(vid, cidList);
        mainService.clearAnimeList();
        return "ok";
    }

    @PostMapping("/admin/updateVideoCat")
    @ResponseBody
    public String updateVideoCat(@RequestBody Map<String,Object> param){

        Object vidObj = param.get("vid");
        if(vidObj == null){
            throw new RuntimeException("影视ID不能为空");
        }
        Integer vid = Integer.valueOf(vidObj.toString());

        List<Integer> cidList = new ArrayList<>();
        Object arrObj = param.get("cidList");
        if(arrObj != null){
            cidList = (List<Integer>) arrObj;
        }
        mainService.updateVideoCategory(vid, cidList);
        mainService.clearTvCache();
        return "ok";
    }

    @PostMapping("/admin/tv/add")
    public String addTv(Video video, @RequestParam("coverFile") MultipartFile coverImg) throws Exception {
        mainService.addVideo(video, coverImg);
        // 2. 新增完成，清空缓存
        mainService.clearTvCache();
        return "redirect:/admin/tv";
//        只要执行新增 / 编辑 / 删除影视操作，调用clearXxxCache()清空对应分类缓存；
//        页面刷新时会重新查询数据库，生成最新缓存，不会出现新旧数据不一致。
    }

    @PostMapping("/admin/tv/edit")
    public String editTv(Video video,@RequestParam("coverFile") MultipartFile coverImg) throws Exception{
        mainService.updateTv(video,coverImg);
        mainService.clearTvCache();
        return "redirect:/admin/tv";
    }

    @GetMapping("/admin/tv/delete")
    public String deleteTv(Video video){
        mainService.deleteTv(video);
        mainService.clearTvCache();
        return "redirect:/admin/tv";
    }

    @GetMapping("/admin/film")
    public String afilm(@RequestParam(defaultValue = "1") Integer pageNum,Model model){

        PageHelper.startPage(pageNum, 10);

        List<Video> allfilm = mainService.selectFilm();

        PageInfo<Video> pageInfo = new PageInfo<>(allfilm);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("showFilm",allfilm);

        Integer maxId = mainService.getMaxVideoId();
        model.addAttribute("maxId", maxId);

        List<Category> allCategory = mainService.selectAllCategory();
        model.addAttribute("allCategory", allCategory);
        return "admin/afilm";
    }

    @PostMapping("/admin/film/add")
    public String addfilm(Video video,MultipartFile coverImg) throws Exception {
        mainService.addFilm(video,coverImg);
        mainService.clearFilmList();
        return "redirect:/admin/film";
    }

    @PostMapping("/admin/film/edit")
    public String editfilm(Video video,MultipartFile coverImg) throws Exception {
        mainService.updateFilm(video,coverImg);
        mainService.clearFilmList();
        return "redirect:/admin/film";
    }

    @GetMapping("/admin/film/delete")
    public String deletefilm(Video video){
        mainService.deleteFilm(video);
        mainService.clearFilmList();
        return "redirect:/admin/film";
    }

    @PostMapping("/admin/anime/add")
    public String addanime(Video video,MultipartFile coverImg) throws Exception {
        mainService.addAnime(video,coverImg);
        mainService.clearAnimeList();
        return "redirect:/admin/anime";
    }

    @PostMapping("/admin/anime/edit")
    public String editanime(Video video,MultipartFile coverImg) throws Exception {
        mainService.updateAnime(video,coverImg);
        mainService.clearAnimeList();
        return "redirect:/admin/anime";
    }

    @GetMapping("/admin/anime/delete")
    public String deleteanime(Video video,MultipartFile coverImg) throws Exception {
        mainService.deleteAnime(video);
        mainService.clearAnimeList();
        return "redirect:/admin/anime";
    }

    @GetMapping("/admin/anime")
    public String aanime(@RequestParam(defaultValue = "1") Integer pageNum,Model model){

        PageHelper.startPage(pageNum, 10);

        List<Video> allanime = mainService.selectAnime();

        PageInfo<Video> pageInfo = new PageInfo<>(allanime);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("showAnime", allanime);

        Integer maxId = mainService.getMaxVideoId();
        model.addAttribute("maxId", maxId);

        List<Category> allCategory = mainService.selectAllCategory();
        model.addAttribute("allCategory", allCategory);
        return "admin/aanime";
    }

    @Resource
    private VideoEpService videoEpService;

    @GetMapping("/admin/getEpList")
    @ResponseBody
    public List<VideoFile> getEpList(Integer vid){
        return videoEpService.selectByVid(vid);
    }

    @PostMapping("/admin/saveEpisode")
    @ResponseBody
    public String saveEpisode(MultipartFile file, Integer vId, Integer num, String videopath, String name){

        String saveFolder = "D:/video_upload/";
        File dir = new File(saveFolder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {

            File targetFile = new File(saveFolder + videopath);
            file.transferTo(targetFile);

            VideoFile videoFile = new VideoFile();
            videoFile.setV_id(vId);
            videoFile.setNum(num);
            videoFile.setName(name);
            videoFile.setVideopath(videopath);
            videoEpService.insert(videoFile);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


    @PostMapping("/admin/delEpisode")
    @ResponseBody
    public String delEpisode(Integer id){
        videoEpService.deleteById(id);
        return "ok";
    }


}
