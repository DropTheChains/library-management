/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : library-springboot

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 01/08/2023 13:01:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `createtime` datetime(0) NULL DEFAULT NULL,
  `updatetime` datetime(0) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `status` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `usernamekey`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (8, 'admin', '18403560311', '2606018@qq.com', '2023-06-08 16:16:47', '2023-06-08 16:50:18', 'ed5fe207a87f5e287c0c38690bfda59f', 1);
INSERT INTO `admin` VALUES (9, 'ceshi01', '18803560356', 'admin@qq.com', '2023-06-08 16:53:21', '2023-06-16 14:14:23', '02d74c48692dc4878dca317efe5f988c', 1);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `publish_date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '出版日期',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作者',
  `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '出版社',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类',
  `book_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标准码',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书封面',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `score` int(0) NULL DEFAULT NULL COMMENT '图书所需积分',
  `nums` int(0) NULL DEFAULT NULL COMMENT '图书数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, 'On Java', 'Java学习必备', '2023-06-06', '测试', '山西出版社', '计算机技术', 'ISBN: 9787115585011', 'https://tse3-mm.cn.bing.net/th/id/OIP-C._d4w1Fsb_GkWkAFMLRrKlQHaJ0?w=122&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7', '2023-06-22 15:55:23', '2023-06-22 00:00:00', 10, 5);
INSERT INTO `book` VALUES (7, '测试图书', '测试图书', '2023-07-14', '测试', '山西出版社', '历史文化 > 中国现代史', 'ISBN:11111', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.sOomj6G-J50RJInOg61GlgHaEo?w=292&h=182&c=7&r=0&o=5&dpr=1.3&pid=1.7', NULL, NULL, 10, 85);
INSERT INTO `book` VALUES (9, 'Java学习手册', 'Java学习手册', '2023-08-02', 'qing', '山西出版社', '计算机技术', 'ISBN:19231040351341', 'http://localhost:9090/book/file/download/1690865986809?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4IiwiZXhwIjoxNjkyMTYxOTg2fQ.1rls4EZzzCpZs_FVc2gTNfNuoxzjgS3BrCKnmYM27dY', NULL, NULL, 10, 19);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书名称',
  `book_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书标准码',
  `user_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会员码',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户联系方式',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `score` int(0) NULL DEFAULT NULL COMMENT '借书积分',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '已借出' COMMENT '借书状态',
  `days` int(0) NULL DEFAULT NULL COMMENT '借书天数',
  `return_date` datetime(0) NULL DEFAULT NULL COMMENT '归还日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (24, 'On Java', 'ISBN: 9787115585011', 'yunhai', '云海', '18802580258', '2023-08-01 02:58:32', '2023-08-01 02:58:32', 10, '已借出', 5, '2023-08-06 00:00:00');
INSERT INTO `borrow` VALUES (25, 'On Java', 'ISBN: 9787115585011', 'ceshi2', '测试账号', '18403560369', '2023-08-01 02:58:39', '2023-08-01 02:58:39', 10, '已借出', 5, '2023-08-06 00:00:00');
INSERT INTO `borrow` VALUES (26, '测试图书', 'ISBN:11111', 'ceshi3', '测试账号', '18403560359', '2023-08-01 02:58:44', '2023-08-01 02:58:44', 10, '已借出', 5, '2023-08-06 00:00:00');
INSERT INTO `borrow` VALUES (27, 'On Java', 'ISBN: 9787115585011', 'ceshi2', '测试账号', '18403560369', '2023-08-01 02:58:53', '2023-08-01 02:58:53', 10, '已借出', 4, '2023-08-05 00:00:00');
INSERT INTO `borrow` VALUES (28, 'Java学习手册', 'ISBN:19231040351341', 'lisisi', '李思思', '18403560369', '2023-08-01 13:00:25', '2023-08-01 13:00:25', 10, '已借出', 7, '2023-08-08 00:00:00');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `pid` int(0) NULL DEFAULT NULL COMMENT '父级id',
  `createtime` timestamp(0) NULL DEFAULT NULL,
  `updatetime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '历史文化', '历史文化', NULL, '2023-06-16 14:04:44', '2023-06-16 15:14:46');
INSERT INTO `category` VALUES (2, '中国现代史', '中国现代史', 1, '2023-06-16 14:04:44', NULL);
INSERT INTO `category` VALUES (3, '中国古代史', '中国古代史', 1, '2023-06-16 14:04:44', NULL);
INSERT INTO `category` VALUES (4, '西方古代史', '西方古代史', 1, '2023-06-16 15:14:05', NULL);
INSERT INTO `category` VALUES (5, '西方现代史', '西方现代史', 1, '2023-06-16 15:14:14', NULL);
INSERT INTO `category` VALUES (6, '西方近代史', '西方近代史', 1, '2023-06-16 15:14:26', NULL);
INSERT INTO `category` VALUES (7, '中国近代史', '中国近代史', 1, '2023-06-16 15:14:32', NULL);
INSERT INTO `category` VALUES (8, '计算机技术', '计算机技术', NULL, '2023-06-16 15:15:37', NULL);

-- ----------------------------
-- Table structure for retur
-- ----------------------------
DROP TABLE IF EXISTS `retur`;
CREATE TABLE `retur`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书名称',
  `book_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书标准码',
  `user_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会员码',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户联系方式',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `score` int(0) NULL DEFAULT NULL COMMENT '借书积分',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '已归还' COMMENT '借书状态',
  `days` int(0) NULL DEFAULT NULL COMMENT '借书天数',
  `return_date` datetime(0) NULL DEFAULT NULL COMMENT '归还日期',
  `real_date` datetime(0) NULL DEFAULT NULL COMMENT '实际归还日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of retur
-- ----------------------------
INSERT INTO `retur` VALUES (22, 'On Java', 'ISBN: 9787115585011', 'weiyunhai', '云海', '18802580258', '2023-07-31 21:55:06', '2023-07-31 21:55:06', 10, '已归还', 3, '2023-08-03 00:00:00', '2023-07-31 00:00:00');
INSERT INTO `retur` VALUES (23, '测试图书', 'ISBN:11111', 'ceshi2', '测试账号', '18403560369', '2023-07-31 21:57:12', '2023-07-31 21:57:12', 10, '已归还', 1, '2023-08-01 00:00:00', '2023-07-31 00:00:00');
INSERT INTO `retur` VALUES (24, 'On Java', 'ISBN: 9787115585011', 'ceshi3', '测试账号', '18403560359', '2023-08-01 00:04:55', '2023-08-01 00:04:55', 10, '已归还', 1, '2023-08-01 00:00:00', '2023-08-01 00:00:00');
INSERT INTO `retur` VALUES (25, 'On Java', 'ISBN: 9787115585011', 'weiyunhai', '云海', '18802580258', '2023-08-01 00:06:45', '2023-08-01 00:06:45', 10, '已归还', 1, '2023-08-02 00:00:00', '2023-08-01 00:00:00');
INSERT INTO `retur` VALUES (26, '测试图书', 'ISBN:11111', 'weiyunhai', '云海', '18802580258', '2023-08-01 00:07:28', '2023-08-01 00:07:28', 10, '已归还', 2, '2023-08-03 00:00:00', '2023-08-01 00:00:00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `cardid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '卡号',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `status` tinyint(1) NULL DEFAULT NULL,
  `account` int(0) NULL DEFAULT 0 COMMENT '账户余额',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (9, '赵大', 'zhaoda', 18, '男', '18403560359', '山西', '20230605ea056608', NULL, '2023-08-01 00:00:00', 1, 100);
INSERT INTO `user` VALUES (22, '李思思', 'lisisi', 12, '女', '18403560369', '山西', '202306061973065313', '2023-06-06 20:52:12', '2023-08-01 12:55:09', 1, 70);
INSERT INTO `user` VALUES (23, '张三', 'zhangsan', 18, '男', '18403560359', '山西', '20230607633490485', '2023-06-07 13:45:35', '2023-08-01 12:54:49', 1, 110);
INSERT INTO `user` VALUES (24, '罗杰', 'luojie', 21, '男', '13134567892', '山西', '20230801-689325888', '2023-08-01 12:56:11', '2023-08-01 12:56:37', 1, 100);

SET FOREIGN_KEY_CHECKS = 1;
