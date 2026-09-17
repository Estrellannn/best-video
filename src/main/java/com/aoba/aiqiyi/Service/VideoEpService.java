package com.aoba.aiqiyi.Service;

import com.aoba.aiqiyi.Entiy.VideoFile;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VideoEpService {

    List<VideoFile> selectByVid(Integer vid);

    int insert(VideoFile ep);

    int deleteById(Integer id);

    int update(VideoFile ep);
}
