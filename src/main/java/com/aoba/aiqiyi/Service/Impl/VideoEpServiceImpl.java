package com.aoba.aiqiyi.Service.Impl;

import com.aoba.aiqiyi.Entiy.VideoFile;
import com.aoba.aiqiyi.Mapper.VideoEpMapper;
import com.aoba.aiqiyi.Service.VideoEpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VideoEpServiceImpl implements VideoEpService {

    @Autowired
    private VideoEpMapper videoEpMapper;

    @Override
    public List<VideoFile> selectByVid(Integer vid) {
        return videoEpMapper.selectByVid(vid);
    }

    @Override
    public int insert(VideoFile ep) {
        return videoEpMapper.insert(ep);
    }

    @Override
    public int deleteById(Integer id) {
        return videoEpMapper.deleteById(id);
    }

    @Override
    public int update(VideoFile ep) {
        return videoEpMapper.update(ep);
    }
}
