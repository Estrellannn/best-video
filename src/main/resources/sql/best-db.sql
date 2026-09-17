/*
 Navicat Premium Dump SQL

 Source Server         : TIDB
 Source Server Type    : MySQL
 Source Server Version : 80011 (8.0.11-TiDB-v8.5.3-serverless)
 Source Host           : gateway01.ap-southeast-1.prod.alicloud.tidbcloud.com:4000
 Source Schema         : best

 Target Server Type    : MySQL
 Target Server Version : 80011 (8.0.11-TiDB-v8.5.3-serverless)
 File Encoding         : 65001

 Date: 17/09/2026 02:23:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, '/img/banner01.jpg');
INSERT INTO `banner` VALUES (2, '/img/banner02.jpg');
INSERT INTO `banner` VALUES (3, '/img/banner03.jpg');
INSERT INTO `banner` VALUES (4, '/img/banner04.jpg');
INSERT INTO `banner` VALUES (5, '/img/banner05.jpg');
INSERT INTO `banner` VALUES (6, '/img/banner06.jpg');
INSERT INTO `banner` VALUES (7, '/img/banner07.jpg');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 270015 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '喜剧');
INSERT INTO `category` VALUES (2, '古装');
INSERT INTO `category` VALUES (3, '家庭');
INSERT INTO `category` VALUES (4, '犯罪');
INSERT INTO `category` VALUES (5, '动作');
INSERT INTO `category` VALUES (6, '剧情');
INSERT INTO `category` VALUES (7, '同性');
INSERT INTO `category` VALUES (8, '武侠');
INSERT INTO `category` VALUES (9, '悬疑');
INSERT INTO `category` VALUES (10, '爱情');
INSERT INTO `category` VALUES (11, '奇幻');
INSERT INTO `category` VALUES (12, '科幻');
INSERT INTO `category` VALUES (13, '冒险');
INSERT INTO `category` VALUES (14, '电视剧');
INSERT INTO `category` VALUES (15, '电影');
INSERT INTO `category` VALUES (16, '动漫');
INSERT INTO `category` VALUES (17, '大陆');
INSERT INTO `category` VALUES (18, '香港');
INSERT INTO `category` VALUES (19, '台湾');
INSERT INTO `category` VALUES (20, '韩国');
INSERT INTO `category` VALUES (21, '日本');
INSERT INTO `category` VALUES (22, '美国');
INSERT INTO `category` VALUES (23, '泰国');
INSERT INTO `category` VALUES (24, '英国');
INSERT INTO `category` VALUES (25, '法国');
INSERT INTO `category` VALUES (26, '其他');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称，格式必须ROLE_开头',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (2, 'ROLE_ADMIN');
INSERT INTO `role` VALUES (1, 'ROLE_USER');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `fk_2`(`role_id` ASC) USING BTREE,
  CONSTRAINT `fk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (2, 1);
INSERT INTO `user_role` VALUES (30003, 1);
INSERT INTO `user_role` VALUES (30004, 1);
INSERT INTO `user_role` VALUES (30008, 1);
INSERT INTO `user_role` VALUES (1, 2);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户昵称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60002 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'aoba', '$2a$10$o1fz95/vYgbtuJr1Fv3p2OivIlIIZ594tu1dgM4RXAkcFaCLlGsNq', '苍叶');
INSERT INTO `users` VALUES (2, 'noiz', '$2a$10$QvP/H2VVFE7dyuRQ45hTGels4Yw.dOiXv53HS/eQpey7714z7Uft.', '诺伊兹');
INSERT INTO `users` VALUES (30003, 'mink', '$2a$10$.JXGy6DumY/NQsLnXvUZ1.EWUMBxqqo9n.B3iF2V7Jw7GL//zlT1m', '敏克');
INSERT INTO `users` VALUES (30004, 'kojaku', '$2a$10$iOgc9E6Nu2Gi.6fDBOchc.Z7TNPRUxOzsmHXoiPcMZdVbSA1EKBq6', '红雀');
INSERT INTO `users` VALUES (30008, 'ken', '$2a$10$Z1aGvSBUiQjS8bcozZOQDONo5c0Z.n2U45eeqI5h5xuTDhGVBK4R.', '莲');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '影视名称',
  `episodes` int NULL DEFAULT NULL COMMENT '集数',
  `cover_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '封面图片路径',
  `info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 540001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '影视信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (1, '蓝眼武士', 8, '/img/蓝眼武士.jpg', '故事以1633年德川幕府颁布锁国令为背景，聚焦混血少女阿水因蓝瞳遭受歧视的经历。母亲遇害后，她隐藏性别生存，在盲眼老人指导下通过陨石锻刀习得剑术，最终踏上向生父复仇的旅程，并在第一季结尾前往伦敦寻找另外两名可能为生父的白人男子，同时得知抚养自己的“母亲”并非生母。');
INSERT INTO `video` VALUES (2, '恶魔人', 10, '/img/恶魔人.jpg', '剧情讲述恶魔即将重返人间，主角不动明由好友飞鸟了帮助下获得恶魔勇者安蒙的力量，得以变身恶魔人对抗恶魔族的故事。');
INSERT INTO `video` VALUES (3, '爱 死亡和机器人', 8, '/img/爱 死亡和机器人.jpg', '影片是动画短片合集，涵盖多种类型和形式。短片每一集都是不同的导演，题材也不同，包含了幻想、科幻和恐怖等范围。');
INSERT INTO `video` VALUES (4, '进击的巨人', 16, '/img/进击的巨人.jpg', '故事发生在一个被巨人威胁的世界。107年前，神秘的巨人突然出现，人类为了生存建造了三重高墙，将自己隔绝在外界 。人类在墙内享受了百余年的和平，直到少年艾伦·耶格尔十岁时，超大型巨人破坏城门，巨人群体冲入城内，艾伦亲眼目睹母亲被吞食，从此立志消灭所有巨人 。');
INSERT INTO `video` VALUES (5, '工作细胞', 24, '/img/工作细胞.jpg', '作品设定在人体内部的抽象世界，约有37兆个细胞在各自岗位上无休止地工作 。通过拟人化手法，红细胞被描绘为负责运输氧气和二氧化碳的快递员，白细胞则是冲锋在前的战士，血小板则化身为活泼的幼儿园小孩，负责止血和修复伤口 。');
INSERT INTO `video` VALUES (6, '怪化猫', 1, '/img/怪化猫.jpg', '故事讲述了小镇上发生了奇怪的事件，在慌乱的村民面前出现了一个持退魔之剑的谜一般的卖药人，他能够通过退魔之剑斩杀引起慌乱的妖怪，但是拔出退魔之剑需要聚齐“形”、“真”和“理”。形即由人的因缘所构成的妖怪的形态；真即事件的真相；理即当事人真实的想法。拔出剑后，变为驱魔人，斩妖杀怪。');
INSERT INTO `video` VALUES (7, '时光代理人', 11, '/img/时光代理人.jpg', '在繁华都市的某个角落，有一家叫做“时光照相馆”的小店还在照常营业。虽然门庭冷落，但它背后其实由两个具有特殊能力的男人——程小时和陆光经营着。为了完成顾客的委托，陆光和程小时组队配合使用超能力进入照片，然而事情的发展却没能按他们所想的那样……');
INSERT INTO `video` VALUES (8, '动物狂想曲', 12, '/img/动物狂想曲.jpg', '作品以肉食动物和草食动物像人类一样共同生活的世界为舞台，讲述了在全宿舍制的切里顿学园里，羊驼提姆被食杀的事件发生后，大灰狼雷格西、兔子春、红鹿路易等动物交织在一起的青春群像剧。故事探讨了肉食与草食动物之间的对立与共存、本能与理性的纠葛等主题。');
INSERT INTO `video` VALUES (9, '替身', 12, '/img/替身.jpg', '1998年春天，榊原恒一（15岁）转学到夜见山北中学，他对班级里那种害怕着某物的气氛抱有违和感。恒一被拥有奇怪存在感的美少女见崎鸣所吸引，不禁想试着接近她，但却就此陷入更深的谜团之中。在这种情况下，意想不到的惨剧发生了……');
INSERT INTO `video` VALUES (10, '亚人', 13, '/img/亚人.jpg', '17年前，非洲战场上突然出现了不会死的“人类”。而自从那之后，人类当中频繁地出现了这种不死的新物种，因此被称为“亚人”。主人公永井圭在遭遇交通意外之后被确认了是亚人，不想成为实验动物的他，便与好朋友海斗，展开了浪迹天涯的旅行。');
INSERT INTO `video` VALUES (11, '咒术回战', 23, '/img/咒术回战.jpg', '17岁的高中生虎杖悠仁是一个优哉游哉的体育特长生，只因他偶然捡到了一个被布条包裹的手指，便从此打开异世界的大门。 为了解救同学，他吞下这根“两面宿傩之指”，于是便和特级咒物融为一体。 在最强咒术师五条悟的帮助下，虎杖进入东京都立咒术高等专门学校学习。 由此开启了自己险象环生、波澜壮阔的咒术师之旅…… ');
INSERT INTO `video` VALUES (12, '伤物语', 1, '/img/伤物语.jpg', '该作改编自 西尾维新 所著系列轻小说《物语系列》的同名第二部，是《化物语》的前传。');
INSERT INTO `video` VALUES (13, '机甲拳击 第一季', 13, '/img/机甲拳击.jpg', ' 该片以融合肉体与“装甲技术”的格斗技“装甲拳击”为背景，讲述拳击手“乔”在“重拳之巅”大会中从底层赛事崛起并夺冠。 七年后，乔化名“诺玛德”重返地下赛场，其佩戴装甲的复出引发关注。');
INSERT INTO `video` VALUES (14, '鬼灭之刃', 11, '/img/鬼灭之刃.jpg', '故事以大正时代为背景，讲述了少年灶门炭治郎在家人被鬼杀害、妹妹祢豆子变成鬼后，为寻找让妹妹变回人类的方法并讨伐恶鬼而加入猎鬼组织“鬼杀队”的历程。');
INSERT INTO `video` VALUES (15, '怪奇物语', 8, '/img/怪奇物语.jpg', '故事始于1983年，印第安纳州霍金斯小镇的男孩威尔·拜尔斯在回家途中神秘失踪。威尔的朋友麦克、达斯汀和卢卡斯在寻找过程中遇到拥有超能力的女孩Eleven（简称EI），并逐步揭开涉及政府秘密实验和超自然力量的阴谋 。随着剧情发展，主角团面临的威胁不断升级，第四季揭示了与Eleven过去有直接关联的强大反派“维克那”，第五季中，主角们团结起来对抗维克那，展开最终战役 。');
INSERT INTO `video` VALUES (16, '同志亦凡人', 13, '/img/queer as folk.jpg', '......');
INSERT INTO `video` VALUES (17, '九龙城寨之围城', 1, '/img/九龙城寨之围城.jpg', '该片根据余儿原著小说《九龙城寨》改编，讲述了二十世纪八十年代恶名昭著的“三不管”地带九龙城寨中危机四伏，落难青年陈洛军逃难误闯入城，被寨中话事人龙卷风搭救后，意外在此收获兄弟情谊，面临恶人夺城众人殊死保家卫寨的高燃热血故事。');
INSERT INTO `video` VALUES (18, '羞耻', 10, '/img/skam.jpg', '该片讲述了30岁出头、事业有成的纽约客布兰登，表面生活高档舒适，却被混乱性生活困扰。妹妹茜茜的意外到访打乱他的节奏，面对妹妹情感诉求，他逃避躲藏，渐入纽约黑暗腹地的故事。');
INSERT INTO `video` VALUES (19, '永远的第1名', 7, '/img/永远的第1名.jpg', '该剧讲述了周书逸的校园生活经历。原本成绩优异的他，在遭遇强劲对手高仕德后，屡遭挫败。高仕德不仅学业上超越他，还时常出现在他的生活中。周书逸本以为进入大学能摆脱高仕德的阴影，但迎新PK赛上再度相遇，同时暗恋的学姐与好友公开恋情，让他的大学生活充满挑战和波折 。');
INSERT INTO `video` VALUES (20, '流浪地球', 1, '/img/流浪地球.jpg', '....');
INSERT INTO `video` VALUES (21, '他不是我', 14, '/img/not me.jpg', '该剧讲述了White在得知双胞胎兄弟Black遭受恶意攻击后，无法容忍他人对兄弟的伤害，于是决定伪装成Black，以来揭露背叛Black的叛徒。在这个过程中，他需要面对不同性格的人物，包括冷静严肃的Sean、开朗友好的Gram以及喜欢打架热血的Yok，以此来解开事件的谜团。');
INSERT INTO `video` VALUES (22, '血观音', 1, '/img/血观音.jpg', '....');
INSERT INTO `video` VALUES (23, '英雄本色', 1, '/img/英雄本色.jpg', '....');
INSERT INTO `video` VALUES (24, '鹿鼎记', 1, '/img/鹿鼎记.jpg', '....');
INSERT INTO `video` VALUES (25, '致命女人', 10, '/img/致命女人.jpg', '该剧讲述了60年代的家庭主妇贝斯如何应对出轨的丈夫；80年代的社交名媛西蒙尼在发现丈夫卡尔竟然是一名同性恋者后的做法；以及2018年的律师泰勒在一场开放式婚姻中遭遇背叛的故事。');
INSERT INTO `video` VALUES (26, '食神', 1, '/img/食神.jpg', '该片讲述了食神史蒂芬·周因名利迷失自我，遭背叛和击败后沦落庙街，在小摊老板娘的帮助下重拾初心并夺回食神之位的故事。');
INSERT INTO `video` VALUES (27, '逃学威龙', 1, '/img/逃学威龙.jpg', '该片讲述了学生参观警署后警长丢枪，遂派遣飞虎队员周星星伪装成学生进入学校开展调查，周星星殴打学生，与老师谈恋爱，并被卷入走私军火案件的故事。');
INSERT INTO `video` VALUES (28, '导火线', 1, '/img/导火线.jpg', '该片讲述了香港重案组督查马军、卧底华生与越南帮三兄弟阿渣、Tony、阿虎对抗的故事。');
INSERT INTO `video` VALUES (29, '过春天', 1, '/img/过春天.jpg', '《过春天》将镜头对准少女的成长过程，讲述了一个颇具现实骨感意义的青春故事。16岁的高中生佩佩是一个处于青春困惑期的“单非少女”，她虽然随母亲居住在深圳，但是每天都要通过关口去往香港读书，为了攒钱完成与好友一起去日本的约定，她选择成为一名“水客”，一段冒险之旅就此展开。');
INSERT INTO `video` VALUES (30, '加勒比海盗', 1, '/img/加勒比海盗.jpg', '系列电影主要讲述17世纪的海盗杰克·斯帕罗充满危险的加勒比冒险之旅的故事。杰克船长曾与铁匠威尔·特纳联手从海盗巴博萨手中救出伊丽莎白·斯旺。随后，杰克船长卷入了与戴维·琼斯的债务纠纷，为了拯救杰克，威尔·特纳、伊丽莎白等人前往“死界”，并共同对抗东印度贸易公司和戴维·琼斯。');
INSERT INTO `video` VALUES (31, '神秘巨星', 1, '/img/神秘巨星.jpg', '该片讲述了印度少女尹希娅突破歧视与阻挠，坚持追寻音乐梦想的故事。');
INSERT INTO `video` VALUES (32, '哈尔的移动城堡', 1, '/img/哈尔的移动城堡.jpg', '讲述了少女苏菲因受荒野女巫诅咒，变成一个90岁的老太太，机缘巧合之下以清洁工的身份进入了哈尔的移动城堡，在两人朝夕相处中，苏菲渐渐走进魔法师哈尔不为人知的内心世界的故事。');
INSERT INTO `video` VALUES (33, '千与千寻', 1, '/img/千与千寻.jpg', '讲述了一个小女孩在神秘世界的奇幻冒险。');
INSERT INTO `video` VALUES (34, '博德之门3', 10, '/img/db0e95428881453ea3767723876329a9.jpg', '《博德之门3》是一款以“龙与地下城”世界为背景的、强调故事与队伍冒险体验的角色扮演游戏。你的每一个选择，都会编织出一段关于同伴与背叛、生存与牺牲，以及至上力量诱惑的故事。');
INSERT INTO `video` VALUES (35, '天国：拯救2', 8, '/img/31b5c8ae097a427ea665ae177b39228c.jpg', '该剧讲述一位铁匠的儿子。他被卷入一场残酷的内战，并绝望的看着入侵者席卷城镇，屠杀他的家人和朋友。亨利决心拿起剑誓言反抗，为双亲报仇并击退侵略者。');
INSERT INTO `video` VALUES (36, '戏剧性谋杀', 14, '/img/be38f13e1c9145819de878b1da6a3ae6.jpg', '赛博朋克风格');
INSERT INTO `video` VALUES (37, '让子弹飞', 1, '/img/75c8ac73102849c1be93939bea8f0ad1.jpg', '讲述了悍匪张牧之摇身一变化名清官“马邦德”上任鹅城县长，并与镇守鹅城的恶霸黄四郎展开一场激烈争斗的故事。');
INSERT INTO `video` VALUES (38, '闵勇', 12, '/img/5f26a2adf1174597ab0f5ca014a145e7.webp', '讲述人类拳击手闵勇与吸血鬼医生之间的爱情故事。');
INSERT INTO `video` VALUES (39, '一个人的武林', 1, '/img/9116fcb2ffad4d4da6bc5f1fb9944ccc.jpg', '讲述了武术教头夏侯武遭遇武痴封于修挑战，在香港武林掀起血雨腥风的故事。');
INSERT INTO `video` VALUES (40, '黑神话：悟空', 6, '/img/55eab94a260f4af0a397d6ee1256f541.jpg', '你将扮演一位“天命人”，为了探寻昔日传说的真相，踏上一条充满危险与惊奇的西游之路。');
INSERT INTO `video` VALUES (41, '赛博朋克 2077', 6, '/img/b1a384f0fcec4b5687f5a15efc946861.png', '《赛博朋克 2077》是一款开放世界动作冒险 RPG 游戏。故事发生在暗黑未来的夜之城，一座五光十色、危机四伏的超级大都会，权力更迭和无尽的身体改造是这里不变的主题。');
INSERT INTO `video` VALUES (42, '死亡男孩侦探社', 8, '/img/4fbad67a0753499ca77a16b11fb2bbba.jpg', '该剧讲述了埃德温·佩恩和查尔斯·罗兰两位少年在死亡中找到彼此的故事。埃德温·佩恩（乔治·列克斯图饰）和查尔斯·罗兰（杰登·雷瓦里饰）分别代表着“死亡男孩侦探社”的脑力与体力，他们既是鬼魂、也是最好的朋友，在伙伴们的协助下，他们留在人间破解超自然现象，并破获一系列案件。在此过程中，拥有预知能力的女孩克里斯朵（卡西厄斯·纳尔逊饰）和朋友仁子（悠悠·北村饰）对他们伸出援手。');
INSERT INTO `video` VALUES (43, '夜行书生', 20, '/img/6e09abb0d81c4296915f25ca198a147a.jpg', '因被人诬陷谋反而没落的官宦家之女，在女扮男装卖书糊口的过程中，与拥有帅气外貌的神秘书生相识后展开的故事。最后她发现原来这位神秘的书生是吸血鬼，而且在朝鲜宫中还有邪恶的吸血鬼存在。');
INSERT INTO `video` VALUES (44, '隐蔽的角落', 12, '/img/1cee169982d24d279152dcc0c2148031.jpg', '该剧改编自紫金陈推理小说《坏小孩》 ，讲述了沿海小城的三个孩子在景区游玩时无意拍摄记录了一次谋杀，他们的冒险也由此展开。扑朔迷离的案情，将几个家庭裹挟其中，带向不可预知的未来......');
INSERT INTO `video` VALUES (45, '机甲拳击 第二季', 13, '/img/efbfe3f9e29f4d8fad84f826aafabfc8.jpg', '再一次，为梦想而活——将肉体与“装甲技术”融合的究极格斗技“装甲拳击”。在决出其顶点之人的大会“重拳之巅”上，身穿装甲用自己肉身挑战一切的拳击手“JOE”。从最下层的比赛开始仅仅花了三个月就登上顶峰，获得奇迹般的优胜传说的身姿令人们陷入狂热的梦幻之中。然而，在此7年后，“乔”再次出现在了地下赛场。遍体鳞伤的身上佩戴着装甲，并自报了“诺玛德”的名字……。曾经“乔”完成的梦想。人们如今在其后续中究竟能看到什么呢？');
INSERT INTO `video` VALUES (46, '青春王室 第二季', 6, '/img/a634f2f4a5ac48e48b4abb20da12ea6a.jpg', '在知名寄宿学校希列斯卡就读后，威廉王子终于有机会了解真实的自己，并找到真正想要的生活。他开始幻想未来能够远离皇室义务，生活则充满自由与无条件的爱。但在威廉意外成为头号王位继承人后，他的处境变得更加艰难。他必须在爱与责任之间做出选择。');
INSERT INTO `video` VALUES (47, '城寨英雄', 28, '/img/7adc9a7b9d9443cb943ee6026c945578.jpg', '故事发生在五十年代末的九龙城寨，左勾拳和段迎风的父亲均为名震千里的八极拳宗师，哪知道一场阴谋使得段迎风失去了父亲，而左勾拳的父亲则沦为了杀人凶手含冤自尽，左勾拳亦因此和妹妹失散，离开了九龙城寨，前往澳门谋生。 一晃眼十五年过去，已经长大成人的左勾拳回到了九龙城寨这片伤心地，只为了寻找妹妹的下落，哪知道此处早已经物是人非，冯春美、柯万长和福寿金三大黑恶势力头目三足鼎立一手遮天，令九龙城寨的居民们终日生活在恐惧之中。正直的左勾拳向三人发起了挑战，他的勇气亦感染了段迎风，两人再度联手，组成了九龙城寨街坊福利会，惩恶扬善，行侠仗义。');
INSERT INTO `video` VALUES (48, '谍影重重', 1, '/img/9eebbf7aa9d44b8c8dd1d6bf55aeb8f1.jpg', '杰森·伯恩在意大利被人从海上救起，他失去了记忆，除了臀部的瑞士银行帐号之外，他完全没有办法证明自己的身份。 杰森从瑞士银行找到了大量的现金，六本护照，一把枪，同时他发现自己格斗、枪械、和语言等方面的能力，他开始追查自己的身份，并且把陌生女子玛丽也卷了进来。两人从瑞士到巴黎，一路上受到神秘杀手组织的追杀，危机重重。 杰森能否化险为夷，找到自己的真实身份？他是否有勇气面对真相？');
INSERT INTO `video` VALUES (49, '镇魂街 第一季', 24, '/img/55ae489ecefc4f49af4c8f04ac6ae248.jpg', '普通的应届大学毕业生夏铃在求职之际，收到了一条奇怪的面试通知，因此误入罗刹街并遭到了危险，幸而被镇魂将曹焱兵搭救。然而接触中，曹焱兵却发现夏铃并非看起来那样普通，因为她的身体里也存在着灵力，并且寄宿着一位神秘的守护灵。与此同时，夏铃开始遭到不明身份刺客的追杀，曹焱兵亦被卷入其中，二人的命运从此开始了交集，而在这一切的背后，似乎还隐藏着某些更加危险的秘密……');

-- ----------------------------
-- Table structure for video_bak
-- ----------------------------
DROP TABLE IF EXISTS `video_bak`;
CREATE TABLE `video_bak`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '影视名称',
  `episodes` int NULL DEFAULT NULL COMMENT '集数',
  `cover_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '封面图片路径',
  `info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '影视信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of video_bak
-- ----------------------------
INSERT INTO `video_bak` VALUES (1, '蓝眼武士', 8, '/img/蓝眼武士.jpg', NULL);
INSERT INTO `video_bak` VALUES (2, '恶魔人', 10, '/img/恶魔人.jpg', NULL);
INSERT INTO `video_bak` VALUES (3, '爱 死亡和机器人', 8, '/img/爱 死亡和机器人.jpg', NULL);
INSERT INTO `video_bak` VALUES (4, '进击的巨人', 16, '/img/进击的巨人.jpg', NULL);
INSERT INTO `video_bak` VALUES (5, '工作细胞', 24, '/img/工作细胞.jpg', NULL);
INSERT INTO `video_bak` VALUES (6, '怪化猫', 1, '/img/怪化猫.jpg', NULL);
INSERT INTO `video_bak` VALUES (7, '时光代理人', 11, '/img/时光代理人.jpg', NULL);
INSERT INTO `video_bak` VALUES (8, '动物狂想曲', 12, '/img/动物狂想曲.jpg', NULL);
INSERT INTO `video_bak` VALUES (9, '替身', 12, '/img/替身.jpg', NULL);
INSERT INTO `video_bak` VALUES (10, '亚人', 13, '/img/亚人.jpg', NULL);
INSERT INTO `video_bak` VALUES (11, '咒术回战', 23, '/img/咒术回战.jpg', NULL);
INSERT INTO `video_bak` VALUES (12, '伤物语', 1, '/img/伤物语.jpg', NULL);
INSERT INTO `video_bak` VALUES (13, '机甲拳击', 13, '/img/机甲拳击.jpg', NULL);
INSERT INTO `video_bak` VALUES (14, '鬼灭之刃', 11, '/img/鬼灭之刃.jpg', NULL);
INSERT INTO `video_bak` VALUES (30001, '怪奇物语', 8, '/img/怪奇物语.jpg', NULL);
INSERT INTO `video_bak` VALUES (30002, '同志亦凡人', 13, '/img/queer as folk.jpg', NULL);
INSERT INTO `video_bak` VALUES (30003, '九龙城寨之围城', 1, '/img/九龙城寨之围城.jpg', NULL);
INSERT INTO `video_bak` VALUES (30004, '羞耻', 10, '/img/skam.jpg', NULL);
INSERT INTO `video_bak` VALUES (30005, '永远的第1名', 7, '/img/永远的第1名.jpg', NULL);
INSERT INTO `video_bak` VALUES (30006, '流浪地球', 1, '/img/流浪地球.jpg', NULL);
INSERT INTO `video_bak` VALUES (30007, '他不是我', 14, '/img/not me.jpg', NULL);
INSERT INTO `video_bak` VALUES (30008, '血观音', 1, '/img/血观音.jpg', NULL);
INSERT INTO `video_bak` VALUES (30009, '英雄本色', 1, '/img/英雄本色.jpg', NULL);
INSERT INTO `video_bak` VALUES (30010, '鹿鼎记', 1, '/img/鹿鼎记.jpg', NULL);
INSERT INTO `video_bak` VALUES (30011, '致命女人', 10, '/img/致命女人.jpg', NULL);
INSERT INTO `video_bak` VALUES (30012, '食神', 1, '/img/食神.jpg', NULL);
INSERT INTO `video_bak` VALUES (30013, '逃学威龙', 1, '/img/逃学威龙.jpg', NULL);
INSERT INTO `video_bak` VALUES (30014, '导火线', 1, '/img/导火线.jpg', NULL);
INSERT INTO `video_bak` VALUES (30015, '过春天', 1, '/img/过春天.jpg', NULL);
INSERT INTO `video_bak` VALUES (30016, '加勒比海盗', 1, '/img/加勒比海盗.jpg', NULL);
INSERT INTO `video_bak` VALUES (30017, '神秘巨星', 1, '/img/神秘巨星.jpg', NULL);
INSERT INTO `video_bak` VALUES (30018, '哈尔的移动城堡', 1, '/img/哈尔的移动城堡.jpg', NULL);
INSERT INTO `video_bak` VALUES (30019, '千与千寻', 1, '/img/千与千寻.jpg', NULL);

-- ----------------------------
-- Table structure for video_category
-- ----------------------------
DROP TABLE IF EXISTS `video_category`;
CREATE TABLE `video_category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `v_id` int NOT NULL COMMENT '视频id',
  `c_id` int NOT NULL COMMENT '分类id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `v_id`(`v_id` ASC) USING BTREE,
  INDEX `c_id`(`c_id` ASC) USING BTREE,
  CONSTRAINT `v_c_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `v_c_ibfk_1` FOREIGN KEY (`v_id`) REFERENCES `video` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 540001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of video_category
-- ----------------------------
INSERT INTO `video_category` VALUES (240006, 25, 1);
INSERT INTO `video_category` VALUES (240007, 25, 4);
INSERT INTO `video_category` VALUES (240008, 25, 6);
INSERT INTO `video_category` VALUES (240009, 25, 14);
INSERT INTO `video_category` VALUES (240010, 15, 6);
INSERT INTO `video_category` VALUES (240011, 15, 9);
INSERT INTO `video_category` VALUES (240012, 15, 14);
INSERT INTO `video_category` VALUES (240013, 18, 6);
INSERT INTO `video_category` VALUES (240014, 18, 7);
INSERT INTO `video_category` VALUES (240015, 18, 14);
INSERT INTO `video_category` VALUES (240016, 19, 7);
INSERT INTO `video_category` VALUES (240017, 19, 10);
INSERT INTO `video_category` VALUES (240018, 19, 14);
INSERT INTO `video_category` VALUES (240019, 34, 6);
INSERT INTO `video_category` VALUES (240020, 34, 14);
INSERT INTO `video_category` VALUES (240021, 35, 6);
INSERT INTO `video_category` VALUES (240022, 35, 14);
INSERT INTO `video_category` VALUES (240026, 8, 6);
INSERT INTO `video_category` VALUES (240027, 8, 9);
INSERT INTO `video_category` VALUES (240028, 8, 16);
INSERT INTO `video_category` VALUES (240029, 11, 6);
INSERT INTO `video_category` VALUES (240030, 11, 16);
INSERT INTO `video_category` VALUES (240031, 1, 5);
INSERT INTO `video_category` VALUES (240032, 1, 6);
INSERT INTO `video_category` VALUES (240033, 1, 16);
INSERT INTO `video_category` VALUES (240034, 9, 16);
INSERT INTO `video_category` VALUES (240041, 7, 6);
INSERT INTO `video_category` VALUES (240042, 7, 16);
INSERT INTO `video_category` VALUES (240043, 2, 6);
INSERT INTO `video_category` VALUES (240044, 2, 16);
INSERT INTO `video_category` VALUES (240045, 12, 5);
INSERT INTO `video_category` VALUES (240046, 12, 16);
INSERT INTO `video_category` VALUES (240047, 3, 1);
INSERT INTO `video_category` VALUES (240048, 3, 16);
INSERT INTO `video_category` VALUES (240051, 5, 1);
INSERT INTO `video_category` VALUES (240052, 5, 5);
INSERT INTO `video_category` VALUES (240053, 5, 16);
INSERT INTO `video_category` VALUES (240057, 6, 9);
INSERT INTO `video_category` VALUES (240058, 6, 16);
INSERT INTO `video_category` VALUES (240059, 13, 6);
INSERT INTO `video_category` VALUES (240060, 13, 16);
INSERT INTO `video_category` VALUES (240061, 14, 5);
INSERT INTO `video_category` VALUES (240062, 14, 6);
INSERT INTO `video_category` VALUES (240063, 14, 16);
INSERT INTO `video_category` VALUES (240064, 36, 12);
INSERT INTO `video_category` VALUES (240065, 36, 16);
INSERT INTO `video_category` VALUES (240066, 4, 5);
INSERT INTO `video_category` VALUES (240067, 4, 6);
INSERT INTO `video_category` VALUES (240068, 4, 16);
INSERT INTO `video_category` VALUES (240070, 38, 16);
INSERT INTO `video_category` VALUES (270004, 30, 15);
INSERT INTO `video_category` VALUES (270005, 28, 15);
INSERT INTO `video_category` VALUES (270006, 20, 15);
INSERT INTO `video_category` VALUES (270007, 24, 15);
INSERT INTO `video_category` VALUES (270008, 31, 15);
INSERT INTO `video_category` VALUES (270009, 22, 15);
INSERT INTO `video_category` VALUES (270010, 32, 15);
INSERT INTO `video_category` VALUES (270011, 27, 15);
INSERT INTO `video_category` VALUES (270012, 29, 15);
INSERT INTO `video_category` VALUES (270013, 37, 15);
INSERT INTO `video_category` VALUES (270014, 23, 15);
INSERT INTO `video_category` VALUES (270015, 26, 15);
INSERT INTO `video_category` VALUES (300004, 10, 6);
INSERT INTO `video_category` VALUES (300005, 10, 9);
INSERT INTO `video_category` VALUES (300006, 10, 21);
INSERT INTO `video_category` VALUES (300007, 10, 16);
INSERT INTO `video_category` VALUES (300008, 17, 5);
INSERT INTO `video_category` VALUES (300009, 17, 6);
INSERT INTO `video_category` VALUES (300010, 17, 18);
INSERT INTO `video_category` VALUES (300011, 17, 15);
INSERT INTO `video_category` VALUES (330002, 40, 5);
INSERT INTO `video_category` VALUES (330003, 40, 6);
INSERT INTO `video_category` VALUES (330004, 40, 17);
INSERT INTO `video_category` VALUES (330005, 40, 14);
INSERT INTO `video_category` VALUES (330006, 41, 14);
INSERT INTO `video_category` VALUES (360001, 21, 4);
INSERT INTO `video_category` VALUES (360002, 21, 6);
INSERT INTO `video_category` VALUES (360003, 21, 7);
INSERT INTO `video_category` VALUES (360004, 21, 9);
INSERT INTO `video_category` VALUES (360005, 21, 23);
INSERT INTO `video_category` VALUES (360006, 21, 14);
INSERT INTO `video_category` VALUES (480001, 16, 14);
INSERT INTO `video_category` VALUES (480003, 43, 14);
INSERT INTO `video_category` VALUES (480005, 45, 16);
INSERT INTO `video_category` VALUES (510004, 49, 16);
INSERT INTO `video_category` VALUES (510005, 47, 5);
INSERT INTO `video_category` VALUES (510006, 47, 6);
INSERT INTO `video_category` VALUES (510007, 47, 18);
INSERT INTO `video_category` VALUES (510008, 47, 14);
INSERT INTO `video_category` VALUES (510009, 46, 6);
INSERT INTO `video_category` VALUES (510010, 46, 7);
INSERT INTO `video_category` VALUES (510011, 46, 26);
INSERT INTO `video_category` VALUES (510012, 46, 14);
INSERT INTO `video_category` VALUES (510013, 44, 4);
INSERT INTO `video_category` VALUES (510014, 44, 6);
INSERT INTO `video_category` VALUES (510015, 44, 9);
INSERT INTO `video_category` VALUES (510016, 44, 17);
INSERT INTO `video_category` VALUES (510017, 44, 14);
INSERT INTO `video_category` VALUES (510018, 42, 11);
INSERT INTO `video_category` VALUES (510019, 42, 14);
INSERT INTO `video_category` VALUES (510020, 48, 4);
INSERT INTO `video_category` VALUES (510021, 48, 5);
INSERT INTO `video_category` VALUES (510022, 48, 9);
INSERT INTO `video_category` VALUES (510023, 48, 15);
INSERT INTO `video_category` VALUES (510024, 39, 5);
INSERT INTO `video_category` VALUES (510025, 39, 18);
INSERT INTO `video_category` VALUES (510026, 39, 15);
INSERT INTO `video_category` VALUES (510027, 33, 6);
INSERT INTO `video_category` VALUES (510028, 33, 21);
INSERT INTO `video_category` VALUES (510029, 33, 15);

-- ----------------------------
-- Table structure for video_file
-- ----------------------------
DROP TABLE IF EXISTS `video_file`;
CREATE TABLE `video_file`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `v_id` int NOT NULL COMMENT '关联影视id',
  `num` int NULL DEFAULT 1 COMMENT '集数',
  `videopath` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '视频访问地址',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of video_file
-- ----------------------------
INSERT INTO `video_file` VALUES (60003, 35, 1, '天国：拯救2 a.mp4', '第1集');
INSERT INTO `video_file` VALUES (60004, 35, 2, '天国：拯救2 b.mp4', '第2集');
INSERT INTO `video_file` VALUES (60005, 35, 3, '天国：拯救2 c.mp4', '第3集');
INSERT INTO `video_file` VALUES (60006, 34, 1, '博德之门3 a.mp4', '第1集');
INSERT INTO `video_file` VALUES (60007, 34, 2, '博德之门3 b.mp4', '第2集');

SET FOREIGN_KEY_CHECKS = 1;
