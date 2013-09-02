/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : newblog

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2013-09-02 13:39:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `article_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`article_id`,`tag_id`),
  KEY `FK_pkndl0ud6fkak73gdkls858a5` (`tag_id`),
  KEY `FK_5ao70rbptu4cd93wbu7o38y1y` (`article_id`),
  CONSTRAINT `FK_5ao70rbptu4cd93wbu7o38y1y` FOREIGN KEY (`article_id`) REFERENCES `mb_articles` (`id`),
  CONSTRAINT `FK_pkndl0ud6fkak73gdkls858a5` FOREIGN KEY (`tag_id`) REFERENCES `mb_tags` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mb_articles
-- ----------------------------
DROP TABLE IF EXISTS `mb_articles`;
CREATE TABLE `mb_articles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_status` enum('PUBLISH','DRAFT','PRIVATE') DEFAULT NULL,
  `comment_count` int(11) NOT NULL DEFAULT '0',
  `comment_status` enum('OPEN','CLOSED','REGISTER') DEFAULT NULL,
  `post_content` longtext NOT NULL,
  `post_date` datetime NOT NULL,
  `post_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `post_pv` int(11) NOT NULL DEFAULT '0',
  `post_tags` varchar(255) DEFAULT NULL,
  `post_title` varchar(255) NOT NULL,
  `post_category` bigint(20) DEFAULT NULL,
  `post_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t1hsrrad3os7j7koeqjeg65dp` (`post_category`),
  KEY `FK_fbh6pfbytxu7xdxmig5qbkg8k` (`post_user`),
  CONSTRAINT `FK_fbh6pfbytxu7xdxmig5qbkg8k` FOREIGN KEY (`post_user`) REFERENCES `mb_users` (`id`),
  CONSTRAINT `FK_t1hsrrad3os7j7koeqjeg65dp` FOREIGN KEY (`post_category`) REFERENCES `mb_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mb_categories
-- ----------------------------
DROP TABLE IF EXISTS `mb_categories`;
CREATE TABLE `mb_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_cn` varchar(255) NOT NULL,
  `category_en` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mb_categories
-- ----------------------------
INSERT INTO `mb_categories` VALUES ('1', '奇趣酷玩', 'gadget');
INSERT INTO `mb_categories` VALUES ('2', '家居/生活', 'home');
INSERT INTO `mb_categories` VALUES ('3', '手机/无线', 'mobile');
INSERT INTO `mb_categories` VALUES ('4', '数码/影音', 'digital');
INSERT INTO `mb_categories` VALUES ('5', '电脑/硬件', 'computer');
INSERT INTO `mb_categories` VALUES ('6', '游戏周边', 'game');
INSERT INTO `mb_categories` VALUES ('7', '家用电器', 'appliance');
INSERT INTO `mb_categories` VALUES ('8', '科技/探索', 'science');
INSERT INTO `mb_categories` VALUES ('9', '交通工具', 'vehicle');
INSERT INTO `mb_categories` VALUES ('10', '创意设计', 'design');
INSERT INTO `mb_categories` VALUES ('11', '大杂烩', 'mess');

-- ----------------------------
-- Table structure for mb_comments
-- ----------------------------
DROP TABLE IF EXISTS `mb_comments`;
CREATE TABLE `mb_comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_content` longtext NOT NULL,
  `comment_email` varchar(255) DEFAULT NULL,
  `comment_url` varchar(255) DEFAULT NULL,
  `comment_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment_submitter` varchar(255) NOT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lc8qwebkaw1i7146dpg74hqq5` (`article_id`),
  CONSTRAINT `FK_lc8qwebkaw1i7146dpg74hqq5` FOREIGN KEY (`article_id`) REFERENCES `mb_articles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mb_options
-- ----------------------------
DROP TABLE IF EXISTS `mb_options`;
CREATE TABLE `mb_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `option_name` varchar(255) NOT NULL,
  `option_value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mb_options
-- ----------------------------
INSERT INTO `mb_options` VALUES ('1', null, 'url', 'http://localhost:8080/myblog');
INSERT INTO `mb_options` VALUES ('2', null, 'title', '极客集');
INSERT INTO `mb_options` VALUES ('3', null, 'adminPageSize', '10');
INSERT INTO `mb_options` VALUES ('4', null, 'description', '汇聚全球科技---保持鲜活的大脑从浏览极客集开始.');
INSERT INTO `mb_options` VALUES ('5', null, 'homePageSize', '10');
INSERT INTO `mb_options` VALUES ('6', null, 'commentPageSize', '5');

-- ----------------------------
-- Table structure for mb_tags
-- ----------------------------
DROP TABLE IF EXISTS `mb_tags`;
CREATE TABLE `mb_tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tag_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mb_users
-- ----------------------------
DROP TABLE IF EXISTS `mb_users`;
CREATE TABLE `mb_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_nickname` varchar(255) DEFAULT NULL,
  `user_pass` varchar(255) NOT NULL,
  `user_registered` datetime NOT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `user_status` enum('ENABLE','DISABLE') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mb_users
-- ----------------------------
INSERT INTO `mb_users` VALUES ('1', null, 'admin', 'admin', 'W6ph5Mm5Pz8GgiULbPgzG37mj9g=', '2013-06-23 13:00:04', null, 'DISABLE');
