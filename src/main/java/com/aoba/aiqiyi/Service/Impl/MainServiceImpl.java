package com.aoba.aiqiyi.Service.Impl;

import com.aoba.aiqiyi.Entiy.Category;
import com.aoba.aiqiyi.Mapper.MainMapper;
import com.aoba.aiqiyi.Entiy.Banner;
import com.aoba.aiqiyi.Entiy.Video;
import com.aoba.aiqiyi.Service.MainService;
import com.aoba.aiqiyi.util.LocalImgUtil;
import com.aoba.aiqiyi.util.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private MainMapper mainMapper;
    @Autowired
    private LocalImgUtil localImgUtil;
    @Autowired
    private RedisCacheUtil redisCacheUtil;

    private static final String KEY_TV = "tv_video_list";
    private static final String KEY_ANIME = "anime_video_list";
    private static final String KEY_FILM = "film_video_list";

    @Value("${redis.enable:true}")
    private Boolean redisEnable;

    @Override
    public List<Video> getTvList() {
        // 如果关闭redis，直接查数据库
        if(!redisEnable){
            return mainMapper.selectTvVideo();
        }
        // 1. 先从Redis取缓存
        List<Video> cacheList = (List<Video>) redisCacheUtil.get(KEY_TV);
        if (cacheList != null) {
            return cacheList;
        }
        // 2. 缓存为空，查询数据库
        List<Video> dbList = mainMapper.selectTvVideo();
        // 3. 存入Redis，缓存30分钟（新增/编辑/删除会主动清缓存，不用等过期）
        redisCacheUtil.set(KEY_TV, dbList, 30);
        return dbList;
    }

    // 新增、编辑、删除影视后，清空缓存
    public void clearTvCache() {
        if(redisEnable){
            redisCacheUtil.delete(KEY_TV);
        }
    }

    @Override
    public List<Video> getAnimeList() {
        if(!redisEnable){
            return mainMapper.selectAnimeVideo();
        }
        List<Video> cacheList = (List<Video>) redisCacheUtil.get(KEY_ANIME);
        if (cacheList != null) {
            return cacheList;
        }
        List<Video> dbList = mainMapper.selectAnimeVideo();
        redisCacheUtil.set(KEY_ANIME, dbList, 30);
        return dbList;
    }

    @Override
    public void clearAnimeList() {
        if(redisEnable){
            redisCacheUtil.delete(KEY_ANIME);
        }
    }

    @Override
    public List<Video> getFilemList() {
        if(!redisEnable){
            return mainMapper.selectFilmVideo();
        }
        List<Video> cacheList = (List<Video>) redisCacheUtil.get(KEY_FILM);
        if (cacheList != null) {
            return cacheList;
        }
        List<Video> dbList = mainMapper.selectFilmVideo();
        redisCacheUtil.set(KEY_FILM, dbList, 30);
        return dbList;
    }

    public void clearFilmList() {
        if(redisEnable){
            redisCacheUtil.delete(KEY_FILM);
        }
    }

//    标准三段式固定套路（所有分类完全一致）
//    查缓存：先根据唯一 KEY 去 Redis 拿数据；
//    查库兜底：缓存为空，执行 Mapper SQL 查询数据库；
//    回填缓存：把数据库结果存入 Redis，设置过期时间；
//    配套清理方法：修改数据后删除对应 KEY，下次访问自动刷新新缓存。

    @Override
    public List<Video> selectAllVideo() {
        return mainMapper.selectTvVideo();
    }

    @Override
    public List<Video> selectTv() {
        return mainMapper.selectTvVideo();
    }

    @Override
    public List<Video> selectFilm() {
        return mainMapper.selectFilmVideo();
    }

    @Override
    public List<Video> selectAnime() {
        return mainMapper.selectAnimeVideo();
    }

    @Override
    public List<Banner> selectAll() {
        return mainMapper.selectAll();
    }

    @Override
    public List<Category> selectAllfeilei() {
        return mainMapper.selectAllleibie();
    }

    @Override
    public void updateCategory(Category category) {
        mainMapper.updateCategory(category);
    }

    @Override
    public List<Category> selectAllTvCategroy() {
        return mainMapper.selectTvCategory();
    }

    @Override
    public void deleteCategory(Integer id) {
        mainMapper.deleteCategory(id);
    }

    @Override
    public void addcategory(Category category) {
        mainMapper.addcategory(category);
    }


    @Override
    public void updateTv(Video video, MultipartFile coverImg) throws Exception {
        // 1. 先根据id查询数据库原有影视数据，拿到旧封面
        Video oldVideo = mainMapper.selectById(video.getId());

        // 2. 判断：用户是否选择了新封面文件
        if (coverImg != null && !coverImg.isEmpty()) {
            // 有新图：上传OSS，把新地址赋值给video
            String newCoverUrl = localImgUtil.uploadImage(coverImg);
            video.setCover_img(newCoverUrl);
        } else {
            // 无新图：复用数据库原来的封面，防止更新成null
            video.setCover_img(oldVideo.getCover_img());
        }

        // 3. 执行数据库更新
        mainMapper.updateTv(video);
    }

    @Override
    public void deleteTv(Video video) {
        mainMapper.deleteTv(video);
    }

    @Override
    @Transactional // 重点注解：保证两张表同时成功/同时回滚，不会出现单表脏数据
    public void addVideo(Video video, MultipartFile coverFile) throws Exception {
        int count = mainMapper.countById(video.getId());
        if(count > 0){
            // 抛出异常，Controller捕获
            throw new RuntimeException("该ID已存在！请更换ID");
        }
        // 1. 文件上传，业务分层清晰
        String imgUrl = localImgUtil.uploadImage(coverFile);
        video.setCover_img(imgUrl);

        // 2. 插入影视主表
        mainMapper.insert(video);

        // 3. 插入分类中间关联表（电视剧固定分类id=14）
        Integer videoId = video.getId();
        mainMapper.insertVideoCategory(videoId, 14);

    }



    @Override
    @Transactional
    public void addAnime(Video video, MultipartFile coverFile) throws Exception {
        String imgUrl = localImgUtil.uploadImage(coverFile);
        video.setCover_img(imgUrl);
        mainMapper.insert(video);
        Integer videoId = video.getId();
        mainMapper.insertVideoCategory(videoId, 16);
    }

    @Override
    public void addFilm(Video video, MultipartFile coverImg) throws Exception {
        String imgUrl = localImgUtil.uploadImage(coverImg);
        video.setCover_img(imgUrl);
        mainMapper.insert(video);
        Integer videoId = video.getId();
        mainMapper.insertVideoCategory(videoId, 15);
    }

    @Override
    public void updateFilm(Video video, MultipartFile coverImg) throws Exception {
        // 1. 先根据id查询数据库原有影视数据，拿到旧封面
        Video oldVideo = mainMapper.selectById(video.getId());

        // 2. 判断：用户是否选择了新封面文件
        if (coverImg != null && !coverImg.isEmpty()) {
            // 有新图：上传OSS，把新地址赋值给video
            String newCoverUrl = localImgUtil.uploadImage(coverImg);
            video.setCover_img(newCoverUrl);
        } else {
            // 无新图：复用数据库原来的封面，防止更新成null
            video.setCover_img(oldVideo.getCover_img());
        }

        // 3.执行数据库更新video主表
        mainMapper.updateFilm(video);
    }

    @Override
    public void deleteFilm(Video video) {
        mainMapper.deleteFilm(video);
    }


    @Override
    public void updateAnime(Video video, MultipartFile coverImg) throws Exception {

        Video oldVideo = mainMapper.selectById(video.getId());

        if (coverImg != null && !coverImg.isEmpty()) {

            String newCoverUrl = localImgUtil.uploadImage(coverImg);
            video.setCover_img(newCoverUrl);
        } else {

            video.setCover_img(oldVideo.getCover_img());
        }


        mainMapper.updateAnime(video);
    }

    @Override
    public void deleteAnime(Video video) {
        mainMapper.deleteAnime(video);
    }

    @Override
    public List<Video> listByMainCid(Integer mainCid) {
        return mainMapper.listVideoByMainCid(mainCid);
    }

    @Override
    public List<Video> listByCidList(List<Integer> cidList, Integer mainCid) {
        return List.of();
    }

    @Override
    public List<Category> getAllCategory() {
        return mainMapper.selectAllCategory();
    }

    @Override
    public List<Video> listVideoByCidList(List<Integer> cidList, Integer mainCid) {
        // 不再复制、不再add，直接原样传给Mapper，彻底规避只读集合修改
        return mainMapper.listVideoByCidList(cidList, mainCid);
    }

    @Override
    public List<Video> listTvAdminWithCategory() {
        // 1. 执行关联查询，得到平铺多行数据（一条影视多条分类）
        List<Video> rawRows = mainMapper.listTvAdminWithCategory();
        Map<Integer, Video> videoMap = new HashMap<>();

        for (Video row : rawRows) {
            // 不存在则存入map，初始化分类集合
            Video targetVideo = videoMap.computeIfAbsent(row.getId(), vid -> row);
            // 当前行存在分类，封装Category存入集合
            if (row.getCat_id() != null) {
                Category cat = new Category();
                cat.setId(row.getCat_id());
                cat.setName(row.getCat_name());
                targetVideo.getCategoryList().add(cat);
            }
        }
        // map转list返回给Controller
        return new ArrayList<>(videoMap.values());
    }

    @Override
    public List<Category> selectAllCategory() {
        return mainMapper.selectAllCategory();
    }

    @Override
    public List<Category> getCatByVid(Integer vid) {
        return mainMapper.getCatByVid(vid);
    }

    @Override
    public void updateVideoCategory(Integer vid, List<Integer> cidList) {
        // ==========新增核心修复：强制绑定电视剧大类id=14，不会被删除==========
        if (!cidList.contains(14)) {
            cidList.add(14);
        }
        // 1、先删除该影视原有所有绑定分类
        mainMapper.deleteByVid(vid);
        // 2、批量插入新勾选分类
        if (!cidList.isEmpty()) {
            // 第一个参数对应 @Param("vidParam")
            mainMapper.batchInsert(vid, cidList);
        }
    }

    @Override
    public void updateAVideoCategory(Integer vid, List<Integer> cidList) {

        if (!cidList.contains(16)) {
            cidList.add(16);
        }

        mainMapper.deleteByVid(vid);

        if (!cidList.isEmpty()) {

            mainMapper.batchInsert(vid, cidList);
        }
    }

    @Override
    public void updateFVideoCategory(Integer vid, List<Integer> cidList) {

        if (!cidList.contains(15)) {
            cidList.add(15);
        }

        mainMapper.deleteByVid(vid);

        if (!cidList.isEmpty()) {

            mainMapper.batchInsert(vid, cidList);
        }
    }

    @Override
    public Integer getMaxVideoId() {
        return mainMapper.selectMaxVideoId();
    }
}
