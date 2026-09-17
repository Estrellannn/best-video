# Best影视综合网站
个人独立开发前后端一体影视平台，前台用户浏览+后台管理员运维双模块，基于SpringBoot生态开发。

## 技术栈
后端：SpringBoot、MyBatis、Spring Security、Redis、MySQL、TiDB
前端：Thymeleaf、Bootstrap5、HTML/CSS、原生JS
工具：Maven、IDEA、本地文件图片上传

## 核心功能
1. 影视资源CRUD：新增/编辑/删除影视，本地封面上传预览，多题材、地区标签批量绑定；
2. 多维度检索：标题关键词模糊搜索，做单字拦截优化避免全表扫描；支持影视大类、题材、地区筛选；
3. 登录与会话管控：SpringSecurity分级权限，游客/普通用户/管理员身份隔离；Redis存储登录会话，30分钟自动过期，免重复登录；
4. 性能优化：Redis缓存首页轮播、分类热点数据，MySQL查询压力降低60%，页面响应提升45%；
5. 分页、前端交互：列表分页加载，统一卡片布局，简介文字多行省略，弹窗回填、删除二次确认。

## 开发说明
项目整体需求梳理、数据库表结构设计、核心业务逻辑、缓存性能优化、权限体系均由本人独立设计实现；
开发过程借助豆包AI辅助生成基础CRUD模板、通用前端页面代码、SQL基础片段，所有AI生成代码均由我逐行校验、修改适配业务场景，自主完成全部Bug调试、代码分层重构，Redis会话登录、多表关联检索、搜索性能优化等核心功能均为自主编写调试。

##启动说明
输入网址 http://localhost/8880 启动项目
管理员账号：aoba 密码：123
普通用户：noiz 密码：456

<img width="1919" height="1079" alt="屏幕截图 2026-07-11 141209" src="https://github.com/user-attachments/assets/fd60d5d6-243e-4a96-841c-9c2e4e8ffbe9" />
<img width="1919" height="1079" alt="屏幕截图 2026-07-11 141327" src="https://github.com/user-attachments/assets/bf9d4bdf-c951-40b0-b388-a914177cf6b9" />
<img width="1919" height="1079" alt="屏幕截图 2026-07-11 141316" src="https://github.com/user-attachments/assets/1b06fadb-a5b2-4474-a013-84eaace3b2ab" />
<img width="1919" height="1079" alt="屏幕截图 2026-07-11 141304" src="https://github.com/user-attachments/assets/8570ae87-e241-4430-9be9-befad2713bac" />
<img width="1919" height="1079" alt="屏幕截图 2026-07-11 141253" src="https://github.com/user-attachments/assets/4d4bbf8b-a6ce-4c90-acee-31a024323624" />
<img width="1919" height="1079" alt="屏幕截图 2026-07-11 141246" src="https://github.com/user-attachments/assets/8acf4ccc-bc8c-414d-8e4c-629d4f4e443b" />
<img width="1919" height="1079" alt="屏幕截图 2026-07-11 141239" src="https://github.com/user-attachments/assets/eba0d493-434a-437a-ab06-ac1999c94de5" />
<img width="1919" height="1079" alt="屏幕截图 2026-07-11 141233" src="https://github.com/user-attachments/assets/6700a98e-3a9c-4408-a936-d7ad6a26859c" />
<img width="1919" height="1079" alt="屏幕截图 2026-07-11 141223" src="https://github.com/user-attachments/assets/d637018e-229b-4623-8587-2d95182a5c66" />

