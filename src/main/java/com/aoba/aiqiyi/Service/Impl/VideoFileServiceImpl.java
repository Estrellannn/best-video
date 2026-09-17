package com.aoba.aiqiyi.Service.Impl;

import com.aoba.aiqiyi.Entiy.VideoFile;
import com.aoba.aiqiyi.Mapper.VideoFileMapper;
import com.aoba.aiqiyi.Service.VideoFileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoFileServiceImpl implements VideoFileService {

    @Resource
    private VideoFileMapper videoFileMapper;

    @Override
    public VideoFile getByVidAndNum(Integer v_id, Integer num) {
        return videoFileMapper.selectByVidAndNum(v_id, num);
    }

    @Override
    public List<VideoFile> getListByVid(Integer v_id) {
        return videoFileMapper.selectListByVid(v_id);
    }
}
