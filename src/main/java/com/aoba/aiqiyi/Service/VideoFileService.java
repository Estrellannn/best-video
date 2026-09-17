package com.aoba.aiqiyi.Service;

import com.aoba.aiqiyi.Entiy.VideoFile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VideoFileService {

    VideoFile getByVidAndNum(Integer v_id, Integer num);

    List<VideoFile> getListByVid(Integer v_id);
}
