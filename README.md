# Best影视综合网站
前后端一体的影视平台，前台用户浏览+后台管理员运维双模块，基于SpringBoot生态开发。

## 技术栈
后端：SpringBoot、MyBatis、Spring Security、Redis、MySQL、TiDB
前端：Thymeleaf、Bootstrap5、HTML/CSS、原生JS
工具：Maven、IDEA、本地文件图片上传

## 核心功能
1. 影视资源CRUD：新增/编辑/删除影视，本地封面上传预览，多题材、地区标签批量绑定；
2. 多维度检索：标题关键词模糊搜索，做单字拦截优化避免全表扫描；支持影视大类、题材、地区筛选；
3. 登录与会话管控：SpringSecurity分级权限，普通用户/管理员身份隔离；Redis存储登录会话，30分钟自动过期，免重复登录；
4. 性能优化：Redis缓存首页轮播、分类热点数据，降低MySQL查询压力，提升页面响应；
5. 分页、前端交互：列表分页加载，统一卡片布局，简介文字多行省略，弹窗回填、删除二次确认。
6. 视频播放与选集功能：播放影视资源，支持多集选集切换，选集列表展示集数信息，点击选集即可切换播放对应剧集。

## 开发说明
项目整体需求梳理、数据库表结构设计、核心业务逻辑、缓存性能优化、权限体系均由本人独立设计实现；
开发过程借助豆包AI辅助生成基础CRUD模板、通用前端页面代码、SQL基础片段，所有AI生成代码均由我逐行校验、修改适配业务场景，自主完成全部Bug调试、代码分层重构，Redis会话登录、多表关联检索、搜索性能优化等核心功能均为自主编写调试。代码还有很多可以优化的地方，欢迎大家提出意见与建议。

## 部署说明
### 数据库
1. 项目已自带数据库备份脚本：`src/main/resources/sql/best-db.sql`
2. 在本地MySQL新建`best`数据库，使用Navicat右键`best`数据库 → 运行SQL文件，选中本项目内的`best-db.sql`导入，导入完成刷新，确认所有表和数据存在。
3. 修改`application.yml`里的数据库连接，指向本地的`best`库，填写你的 MySQL 账号密码。

### Redis 可选开关
项目自定义Redis开关：`redis.enable` 在 `application.yml`
- `redis.enable: true`：开启Redis缓存，本地需要安装并启动Redis（默认端口6379，db0无密码），用于登录信息缓存，性能更好。
- `redis.enable: false`：关闭Redis，**不需要安装Redis**，核心业务功能完全正常。

## 静态资源目录配置
项目配置了本地文件访问路径：`file:D:/video_upload/`
1. **必须在D盘手动创建文件夹`video_upload`**，路径：`D:\video_upload\`
2. 下载解压网盘的文件放进创建好的文件夹`video_upload`里面
3. 通过网盘分享的文件：video_upload.zip
链接: https://pan.baidu.com/s/1KIaE9MByn-LinFv3zOwWOw?pwd=0917 提取码: 0917
4. 上传的视频文件会保存到此目录，项目可以直接访问这个目录下的资源。

## 启动说明
输入网址 http://localhost/8880 启动项目
管理员账号：aoba 密码：123
普通用户：noiz 密码：456
普通用户：mink 密码：789
普通用户：kojaku 密码：012
普通用户：ken 密码：345

<img width="1919" height="1079" alt="屏幕截图 2026-09-17 131955" src="https://github.com/user-attachments/assets/8cc6781e-dfef-4fa4-b5b2-bf33ac73ac2b" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132008" src="https://github.com/user-attachments/assets/00fbfa6b-716f-40fd-8dfe-4865c8d6e069" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132014" src="https://github.com/user-attachments/assets/049213da-0b2d-4db1-9683-d91f5bd032b2" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132045" src="https://github.com/user-attachments/assets/c0d3ab85-2b11-4b92-ba39-46faafb1aa69" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132058" src="https://github.com/user-attachments/assets/a18cb19b-745c-4ec5-814b-baa13d8d148c" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132106" src="https://github.com/user-attachments/assets/ff6e31af-8b91-4c4c-a063-175f49d4b2a3" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132254" src="https://github.com/user-attachments/assets/a8c3cef7-fc1f-4b70-8414-fde6a5f28a22" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132408" src="https://github.com/user-attachments/assets/9db771f3-2db9-46da-8f8c-05f9d40142fe" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132428" src="https://github.com/user-attachments/assets/05f0a7eb-ade3-40eb-be29-01b3673a899c" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132134" src="https://github.com/user-attachments/assets/2948a6d2-8f34-4d20-84db-506bd74f9ed1" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132150" src="https://github.com/user-attachments/assets/480ed7d7-2b26-4338-a6b7-85d6f212e966" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132212" src="https://github.com/user-attachments/assets/b244d2bf-1215-4884-ab0c-5297f88c516d" />
<img width="1919" height="1079" alt="屏幕截图 2026-09-17 132228" src="https://github.com/user-attachments/assets/71d5f401-c69a-4bc1-84b1-22334f48108f" />
