package com.aoba.aiqiyi.util;
import com.aoba.aiqiyi.Config.OssConfig;
import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

@Component
public class LocalImgUtil {
    @Autowired(required = false)
    private OSS ossClient;
    @Autowired
    private OssConfig ossConfig;

    // 允许的图片后缀（和你原有逻辑保持一致）
    private static final String[] IMAGE_SUFFIX = {"jpg", "jpeg", "png", "gif", "webp"};

    // ========== 本地存储配置（可按需修改）==========
    // 1. 文件实际保存路径：项目内 static/img/videoCover 文件夹
    private final String localSaveRoot = System.getProperty("user.dir") + "/src/main/resources/static/img/";
    // 2. 前端访问URL前缀，数据库只存相对路径
    private final String visitPrefix = "/img/";

    public String uploadImage(MultipartFile file) throws Exception {
        // 1. 判断文件是否为空（保留你原有校验逻辑）
        if (file.isEmpty()) {
            throw new RuntimeException("图片不能为空");
        }
        String originalName = file.getOriginalFilename();
        // 截取文件后缀
        String suffix = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();
        boolean pass = false;
        for (String s : IMAGE_SUFFIX) {
            if (s.equals(suffix)) {
                pass = true;
                break;
            }
        }
        if (!pass) {
            throw new RuntimeException("仅支持jpg/png/gif/webp");
        }

        // 2. 生成唯一文件名，和OSS目录结构对齐 videoCover/xxx.jpg
        String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + "." + suffix;
        String fullSavePath = localSaveRoot + uniqueFileName;
        File saveFile = new File(fullSavePath);

        // 3. 不存在文件夹则自动创建
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }

        // 4. 写入本地文件
        try (InputStream is = file.getInputStream()) {
            file.transferTo(saveFile);
        }

        // 5. 返回相对访问路径，存入数据库（页面th:src直接渲染）
        return visitPrefix + uniqueFileName;
    }
}
