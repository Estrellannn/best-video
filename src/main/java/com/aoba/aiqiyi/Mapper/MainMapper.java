package com.aoba.aiqiyi.Mapper;

import com.aoba.aiqiyi.Entiy.Banner;
import com.aoba.aiqiyi.Entiy.Category;
import com.aoba.aiqiyi.Entiy.Video;
import org.apache.ibatis.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Mapper
public interface MainMapper {

     List<Video> selectTvVideo();

     List<Video> selectFilmVideo();

     @Select("select id,img_path from banner")
     List<Banner> selectAll();

     List<Video> selectAnimeVideo();

     @Select("select id,name from category")
     List<Category> selectAllleibie();

     @Update("update category set name=#{name} where id=#{id}")
     void updateCategory(Category category);

     @Select("select name,id from category where name not in ('电视剧','动漫','电影')")
     List<Category> selectTvCategory();

     @Delete("delete from category where id=#{id}")
     void deleteCategory(Integer id);

     @Insert("insert into category(name) values (#{name})")
     void addcategory(Category category);

     // 新增影视
     @Insert("insert into video(id,title,info,episodes,cover_img)values(#{id},#{title},#{info},#{episodes},#{cover_img})")
     int insert(Video video);
     @Insert("insert into video_category(v_id, c_id)values(#{videoId}, #{categoryId});")
     void insertVideoCategory(@Param("videoId") Integer videoId, @Param("categoryId") Integer categoryId);

     @Update("update video set title=#{title},episodes=#{episodes},cover_img=#{cover_img},info=#{info} where id=#{id}")
     void updateTv(Video video);

     @Select("select title,episodes,cover_img,info from video where id=#{id}")
     Video selectById(Integer id);

     @Delete("delete from video where id=#{id}")
     void deleteTv(Video video);

     @Update("update video set title=#{title},episodes=#{episodes},cover_img=#{cover_img},info=#{info} where id=#{id}")
     void updateAnime(Video video);

     @Delete("delete from video where id=#{id}")
     void deleteAnime(Video video);

     @Update("update video set title=#{title},episodes=#{episodes},cover_img=#{cover_img},info=#{info} where id=#{id}")
     void updateFilm(Video video);

     @Delete("delete from video where id=#{id}")
     void deleteFilm(Video video);

     // 统一搜索：关键词+分类id，cid=0查全部分类
     List<Video> searchVideo(@Param("kw") String kw, @Param("cid") Integer cid);

     // 根据主分类查询全部（电视剧/电影大类）
     List<Video> listVideoByMainCid(@Param("mainCid") Integer mainCid);

     // 根据多个分类筛选（题材+地区组合）
     List<Video> listVideoByCidList(@Param("cidList") List<Integer> cidList,
                                    @Param("mainCid") Integer mainCid);

     // 查询全部分类（用于页面渲染筛选标签）
     @Select("SELECT id,name FROM category WHERE name NOT IN ('电视剧','动漫','电影') ORDER BY id ASC")
     List<Category> selectAllCategory();

     //查询所有影视并带出关联分类（后台列表专用）只查电视剧大类 c.id=11
     @Select("SELECT v.*,c.id cat_id,c.name cat_name FROM video v LEFT JOIN video_category vc ON v.id = vc.v_id LEFT JOIN category c ON vc.c_id = c.id WHERE EXISTS (SELECT 1 FROM video_category vc2 WHERE vc2.v_id = v.id AND vc2.c_id = 11 ORDER BY v.id DESC")
     List<Video> tvVideoWithCategory();

     // 后台电视剧列表，携带关联分类
     List<Video> listTvAdminWithCategory();

     List<Category> getCatByVid(Integer vid);

     // 删除该影视所有关联
     @Delete("DELETE FROM video_category WHERE v_id = #{vid}")
     void deleteByVid(@Param("vid") Integer vid);

     // 批量新增
     @Insert("<script>INSERT INTO video_category(v_id,c_id) VALUES " +
             "<foreach collection='cidList' item='singleCid' separator=','> (#{vidParam},#{singleCid}) </foreach>" +
             "</script>")
     void batchInsert(@Param("vidParam") Integer vidParam, @Param("cidList") List<Integer> cidList);

     // 根据视频id删除中间表全部绑定
     @Delete("DELETE FROM video_category WHERE v_id = #{vid}")
     void deleteVideoCatByVid(Integer vid);


     @Select("select IFNULL(max(id),0) from video")
     Integer selectMaxVideoId();

     @Select("select count(*) from video where id = #{id}")
     int countById(Integer id);

     @Select("")
     List<Video> selectTv();
}
