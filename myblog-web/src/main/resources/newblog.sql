/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : newblog

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2014-04-16 18:17:20
*/

CREATE DATABASE /*!32312 IF NOT EXISTS*/`myblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myblog`;

/*Table structure for table `article_tag` */

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `art_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `art_author` bigint(20) unsigned NOT NULL DEFAULT '0',
  `art_category` bigint(20) unsigned NOT NULL,
  `art_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `art_content` longtext NOT NULL,
  `art_title` text NOT NULL,
  `art_tagstring` varchar(255) NOT NULL DEFAULT '',
  `art_excerpt` text,
  `art_status` varchar(20) NOT NULL DEFAULT 'PUBLISH',
  `comment_status` varchar(20) NOT NULL DEFAULT 'OPEN',
  `art_name` varchar(200) NOT NULL DEFAULT '',
  `art_modified` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comment_count` bigint(20) unsigned NOT NULL DEFAULT '0',
  `art_viewnum` bigint(20) unsigned NOT NULL DEFAULT '0',
  `art_url` varchar(255) DEFAULT NULL,
  `art_istop` int(255) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`art_id`),
  KEY `art_author` (`art_author`),
  KEY `art_category` (`art_category`),
  CONSTRAINT `blog_article_ibfk_1` FOREIGN KEY (`art_author`) REFERENCES `blog_member` (`mem_id`),
  CONSTRAINT `blog_article_ibfk_2` FOREIGN KEY (`art_category`) REFERENCES `blog_category` (`cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_article
-- ----------------------------
INSERT INTO `blog_article` VALUES ('1', '1', '1', '2014-04-16 15:10:25', 'hello world', 'test', 'test', null, 'PUBLISH', 'OPEN', '', '2014-04-16 15:10:36', '1', '11', null, '0');

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `art_id` bigint(20) unsigned NOT NULL,
  `tag_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b29au47qjlqlf4b6lu9smpa03` (`tag_id`),
  KEY `FK_svw0fr2q7h5afmbee804guac6` (`art_id`),
  CONSTRAINT `FK_svw0fr2q7h5afmbee804guac6` FOREIGN KEY (`art_id`) REFERENCES `blog_article` (`art_id`),
  CONSTRAINT `FK_b29au47qjlqlf4b6lu9smpa03` FOREIGN KEY (`tag_id`) REFERENCES `blog_tag` (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of blog_article_tag
-- ----------------------------
INSERT INTO `blog_article_tag` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `cate_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `cate_name` varchar(255) NOT NULL,
  `cate_intro` varchar(255) NOT NULL,
  `cate_count` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_category
-- ----------------------------
INSERT INTO `blog_category` VALUES ('1', 'gadget', '奇趣酷玩', '0');
INSERT INTO `blog_category` VALUES ('2', 'home', '家居/生活', '0');
INSERT INTO `blog_category` VALUES ('3', 'mobile', '手机/无线', '0');
INSERT INTO `blog_category` VALUES ('4', 'digital', '数码/影音', '0');
INSERT INTO `blog_category` VALUES ('5', 'other', '未定义', '0');

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `comm_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `art_id` bigint(20) unsigned NOT NULL,
  `comm_authorid` bigint(20) unsigned NOT NULL DEFAULT '0',
  `comm_author` varchar(200) NOT NULL,
  `comm_content` text NOT NULL,
  `comm_email` varchar(255) NOT NULL DEFAULT '',
  `comm_homepage` varchar(255) NOT NULL DEFAULT '',
  `comm_posttime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comm_ip` varchar(200) DEFAULT NULL,
  `comm_agent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comm_id`),
  KEY `art_id` (`art_id`),
  KEY `comm_authorid` (`comm_authorid`),
  CONSTRAINT `blog_comment_ibfk_1` FOREIGN KEY (`art_id`) REFERENCES `blog_article` (`art_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
INSERT INTO `blog_comment` VALUES ('2', '1', '1', 'david', 'dddw', '', '', '2014-04-16 15:13:54', null, null);

-- ----------------------------
-- Table structure for blog_member
-- ----------------------------
DROP TABLE IF EXISTS `blog_member`;
CREATE TABLE `blog_member` (
  `mem_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `mem_level` int(255) unsigned NOT NULL DEFAULT '0',
  `mem_name` varchar(200) NOT NULL,
  `mem_password` varchar(255) NOT NULL DEFAULT '',
  `mem_sex` int(255) unsigned NOT NULL DEFAULT '0',
  `mem_email` varchar(255) DEFAULT NULL,
  `mem_qq` varchar(255) DEFAULT NULL,
  `mem_homepage` varchar(255) DEFAULT NULL,
  `mem_lastvisit` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `mem_status` int(255) unsigned NOT NULL DEFAULT '0',
  `mem_postarts` bigint(255) NOT NULL DEFAULT '0',
  `mem_postcomms` bigint(255) NOT NULL DEFAULT '0',
  `mem_intro` varchar(255) NOT NULL DEFAULT '',
  `mem_ip` varchar(255) DEFAULT NULL,
  `mem_agent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mem_id`),
  UNIQUE KEY `mem_name` (`mem_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_member
-- ----------------------------
INSERT INTO `blog_member` VALUES ('1', '0', 'admin', 'password', '0', null, null, null, '2014-04-16 15:14:27', '0', '0', '0', '', null, null);

-- ----------------------------
-- Table structure for blog_options
-- ----------------------------
DROP TABLE IF EXISTS `blog_options`;
CREATE TABLE `blog_options` (
  `option_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `option_name` varchar(64) NOT NULL DEFAULT '',
  `option_value` longtext NOT NULL,
  `autoload` varchar(20) NOT NULL DEFAULT 'yes',
  PRIMARY KEY (`option_id`),
  UNIQUE KEY `option_name` (`option_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_options
-- ----------------------------
INSERT INTO `blog_options` VALUES ('1', 'url', 'http://localhost:8080/myblog', 'yes');
INSERT INTO `blog_options` VALUES ('2', 'title', '极客集', 'yes');
INSERT INTO `blog_options` VALUES ('3', 'adminPageSize', '10', 'yes');
INSERT INTO `blog_options` VALUES ('4', 'description', '汇聚全球科技---保持鲜活的大脑从浏览极客集开始.', 'yes');
INSERT INTO `blog_options` VALUES ('5', 'homePageSize', '10', 'yes');
INSERT INTO `blog_options` VALUES ('6', 'commentPageSize', '5', 'yes');

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `tag_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(200) NOT NULL,
  `tag_intro` varchar(255) NOT NULL DEFAULT '',
  `tag_count` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES ('1', 'test', '', '0');
