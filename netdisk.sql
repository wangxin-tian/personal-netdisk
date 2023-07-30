/*
 Navicat Premium Data Transfer

 Source Server         : local-mysql
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : netdisk

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 14/06/2023 10:55:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_folder
-- ----------------------------
DROP TABLE IF EXISTS `tb_folder`;
CREATE TABLE `tb_folder`  (
  `folder_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文件夹id',
  `folder_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件夹名',
  `folder_father` bigint(20) NOT NULL DEFAULT 0 COMMENT '上一级文件夹id，默认0',
  `belong_user` bigint(20) NOT NULL COMMENT '属于的用户id',
  `folder_createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建的时间',
  `isDel` smallint(6) NULL DEFAULT 0 COMMENT '是否删除 1为删除0没删除',
  PRIMARY KEY (`folder_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_share
-- ----------------------------
DROP TABLE IF EXISTS `tb_share`;
CREATE TABLE `tb_share`  (
  `share_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分享id',
  `share_user` bigint(255) NOT NULL COMMENT '分享用户',
  `share_userfile_id` bigint(20) NULL DEFAULT NULL COMMENT '用户文件id',
  `share_comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分享文件消息',
  `file_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件的位置',
  `share_date` datetime(0) NULL DEFAULT NULL COMMENT '分享日期',
  PRIMARY KEY (`share_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_system_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_file`;
CREATE TABLE `tb_system_file`  (
  `file_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件地址',
  `belong_user` bigint(255) UNSIGNED NULL DEFAULT NULL COMMENT '上传用户',
  `file_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '5' COMMENT '文件类型，1图片2音频3视频4文档5其他',
  `file_size` decimal(10, 2) UNSIGNED NOT NULL COMMENT '文件大小，单位kb',
  `upload_time` timestamp(0) NULL DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_level` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '用户级别',
  `drive_size` bigint(20) UNSIGNED NULL DEFAULT 51200 COMMENT '网盘可用空间，单位kb',
  `user_nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `user_imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_behavior
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_behavior`;
CREATE TABLE `tb_user_behavior`  (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `behavior_id` bigint(20) NOT NULL COMMENT '行为id',
  `belong_user` bigint(255) NOT NULL COMMENT '文件的投稿人',
  `user_id` bigint(255) NOT NULL COMMENT '行为用户',
  `file_id` bigint(20) NULL DEFAULT NULL COMMENT '用户文件id',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息',
  `file_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件的位置',
  `behavior_date` datetime(0) NULL DEFAULT NULL COMMENT '分享日期',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_file`;
CREATE TABLE `tb_user_file`  (
  `userfile_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户文件ID',
  `user_sysfile_id` bigint(20) NULL DEFAULT NULL COMMENT '对应系统文件表的id',
  `user_file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户对应文件的名字',
  `belong_user` bigint(255) UNSIGNED NOT NULL COMMENT '所属用户',
  `file_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型，1图片2音频3视频4文档5其他',
  `file_size` decimal(10, 2) NULL DEFAULT NULL COMMENT '文件大小',
  `file_location` bigint(255) UNSIGNED NOT NULL COMMENT '文件所属文件夹的位置',
  `upload_time` timestamp(0) NULL DEFAULT NULL COMMENT '用户上传文件时间',
  `isDel` smallint(6) NULL DEFAULT 0 COMMENT '是否删除,1为删除',
  PRIMARY KEY (`userfile_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_status
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_status`;
CREATE TABLE `tb_user_status`  (
  `user_name` char(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `user_status` bigint(255) NULL DEFAULT 0 COMMENT '用户更新时间',
  PRIMARY KEY (`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
