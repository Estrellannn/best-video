package com.aoba.aiqiyi.Controller;


import com.aoba.aiqiyi.Entiy.Video;
import com.aoba.aiqiyi.Entiy.VideoFile;
import com.aoba.aiqiyi.Mapper.MainMapper;
import com.aoba.aiqiyi.Service.VideoFileService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlayController {

    @Resource
    private VideoFileService videoFileService;
    @Resource
    private MainMapper videoMapper;

    @GetMapping("/bofang")
    public String toBofangPage(@RequestParam("id")Integer vid, Integer num, Model model){
        // 1. 查询影视主表信息（标题、简介，给页面展示）
        Video video = videoMapper.selectById(vid);

        // 2. 查询当前选中这一集的video_file数据
        VideoFile currentEp = videoFileService.getByVidAndNum(vid, num);

        // 3. 重点！拼接播放地址：前面加 /
        if(currentEp != null){
            // playUrl 赋值： /文件名.mp4
            video.setPlayUrl("/" + currentEp.getVideopath());
        }

        // 4. 查询这个影视全部集数，做选集按钮（后面加到页面）
        List<VideoFile> epList = videoFileService.getListByVid(vid);

        model.addAttribute("video", video);
        model.addAttribute("epList", epList);
        model.addAttribute("vid", vid);
        model.addAttribute("num", num);
        model.addAttribute("currentEp", currentEp);

        return "bofang";
    }
}
