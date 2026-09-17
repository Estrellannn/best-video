package com.aoba.aiqiyi.Service;

import com.aoba.aiqiyi.Entiy.Banner;
import com.aoba.aiqiyi.Entiy.Category;
import com.aoba.aiqiyi.Entiy.Video;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface MainService {

    List<Video> selectAllVideo();

    List<Video> selectTv();

    List<Video> selectFilm();

    List<Video> selectAnime();

    List<Banner> selectAll();

    List<Category> selectAllfeilei();

    void updateCategory(Category category);

    List<Category> selectAllTvCategroy();

    void deleteCategory(Integer id);


    void addcategory(Category category);

    void addVideo(Video video, MultipartFile coverImg) throws Exception;

    List<Video> getTvList();

    void clearTvCache();

    List<Video> getAnimeList();

    void clearAnimeList();

    void updateTv(Video video, MultipartFile coverImg) throws Exception;

    void deleteTv(Video video);

    void addAnime(Video video, MultipartFile coverImg) throws Exception;

    void updateAnime(Video video, MultipartFile coverImg) throws Exception ;

    void deleteAnime(Video video);

    List<Video> getFilemList();

    void clearFilmList();

    void addFilm(Video video, MultipartFile coverImg) throws Exception ;

    void updateFilm(Video video, MultipartFile coverImg) throws Exception ;

    void deleteFilm(Video video);

    // 根据主分类id（电视剧11/电影13）查询该大类全部影视
    List<Video> listByMainCid(Integer mainCid);

    // 多分类联合筛选（主分类+题材/地区）
    List<Video> listByCidList(List<Integer> cidList, Integer mainCid);

    // 查询全部分类，用于页面渲染筛选标签
    List<Category> getAllCategory();

    // 新增这一行，和Controller调用参数完全匹配
    List<Video> listVideoByCidList(List<Integer> cidList, Integer mainCid);

    List<Video> listTvAdminWithCategory();


    List<Category> selectAllCategory();

    List<Category> getCatByVid(Integer vid);

    void updateVideoCategory(Integer vid, List<Integer> cidList);

    void updateAVideoCategory(Integer vid, List<Integer> cidList);

    void updateFVideoCategory(Integer vid, List<Integer> cidList);

    Integer getMaxVideoId();
}
