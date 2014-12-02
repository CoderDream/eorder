/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : eorder

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-12-03 02:49:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_function`
-- ----------------------------
DROP TABLE IF EXISTS `t_function`;
CREATE TABLE `t_function` (
  `function_id` int(32) NOT NULL AUTO_INCREMENT,
  `function_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'the name of function',
  `function_desc` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'detail information',
  `function_path` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'the access path to this function',
  `function_parent` int(32) DEFAULT NULL COMMENT 'parent function',
  `function_order` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT 'use to organize menu in order\r\n            the top function should be a integer as such 100000, 140000\r\n            the first 2 bit is for top function',
  `function_status` tinyint(1) DEFAULT '1',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`function_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='define system functions & path';

-- ----------------------------
-- Records of t_function
-- ----------------------------
INSERT INTO `t_function` VALUES ('1', '系统管理', 'doAdministration', '', '0', '010000', '1', null, null);
INSERT INTO `t_function` VALUES ('2', '功能管理', 'doFunction', '/function/doFunction.action', '1', '010100', '1', null, null);
INSERT INTO `t_function` VALUES ('3', '角色管理', 'doRole', '/role/doRole.action', '1', '010200', '1', null, null);
INSERT INTO `t_function` VALUES ('4', '用户管理', 'doUser', '/user/doUser.action', '1', '010300', '1', null, null);
INSERT INTO `t_function` VALUES ('5', '角色功能关联', 'doRoleFunction', '/rolefunction/doRoleFunction.action', '1', '010400', '1', null, null);
INSERT INTO `t_function` VALUES ('6', '用户角色关联', 'doUserRole', '/userrole/doUserRole.action', '1', '010500', '1', null, null);

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int(32) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `role_desc` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `role_status` tinyint(1) DEFAULT '1',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', '管理员', '1', null, null);
INSERT INTO `t_role` VALUES ('2', '普通用户', '普通用户', '1', null, null);

-- ----------------------------
-- Table structure for `t_role_function`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_function`;
CREATE TABLE `t_role_function` (
  `role_function_id` int(32) NOT NULL AUTO_INCREMENT,
  `role_id` int(32) NOT NULL DEFAULT '0',
  `function_id` int(32) NOT NULL DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`role_function_id`),
  KEY `pk_role_id_2` (`role_id`),
  KEY `pk_function_id_2` (`function_id`),
  CONSTRAINT `pk_function_id_2` FOREIGN KEY (`function_id`) REFERENCES `t_function` (`function_id`),
  CONSTRAINT `pk_role_id_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_role_function
-- ----------------------------
INSERT INTO `t_role_function` VALUES ('1', '1', '1', null, null);
INSERT INTO `t_role_function` VALUES ('2', '1', '2', null, null);
INSERT INTO `t_role_function` VALUES ('3', '1', '3', null, null);
INSERT INTO `t_role_function` VALUES ('4', '1', '4', null, null);
INSERT INTO `t_role_function` VALUES ('5', '1', '5', null, null);
INSERT INTO `t_role_function` VALUES ('6', '1', '6', null, null);

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `cellphone` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `user_status` tinyint(1) DEFAULT '1',
  `level_id` int(32) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='user''s basic information';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'ceb4f32325eda6142bd65215f4c0f371', '', '1', null, '2012-09-29 00:00:00', null);
INSERT INTO `t_user` VALUES ('2', 'test', '889255f1c9c8f12a353be255f78a848b', '', '1', null, '2012-09-29 00:00:00', null);

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_role_id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL,
  `role_id` int(32) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `t_user_id_1` (`user_id`),
  KEY `t_role_id_1` (`role_id`),
  CONSTRAINT `t_role_id_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`),
  CONSTRAINT `t_user_id_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1', null, null);
INSERT INTO `t_user_role` VALUES ('2', '2', '2', null, null);
INSERT INTO `t_user_role` VALUES ('5', '2', '1', '2014-12-03 01:28:13', null);
