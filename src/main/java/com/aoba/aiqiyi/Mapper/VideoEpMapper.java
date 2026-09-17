package com.aoba.aiqiyi.Mapper;

import com.aoba.aiqiyi.Entiy.VideoFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoEpMapper {
    // 查询某影视的所有分集
    List<VideoFile> selectByVid(@Param("v_id") Integer vid);

    int insert(VideoFile ep);

    int deleteById(@Param("id") Integer id);

    int update(VideoFile ep);
}
