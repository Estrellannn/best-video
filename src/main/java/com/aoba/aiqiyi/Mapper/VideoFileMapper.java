package com.aoba.aiqiyi.Mapper;

import com.aoba.aiqiyi.Entiy.VideoFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoFileMapper {

    VideoFile selectByVidAndNum(@Param("v_id") Integer v_id, @Param("num") Integer num);

    List<VideoFile> selectListByVid(@Param("v_id") Integer v_id);
}
