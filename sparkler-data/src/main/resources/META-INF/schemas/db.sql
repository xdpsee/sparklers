-- MySQL dump 10.13  Distrib 8.0.11, for osx10.13 (x86_64)
--
-- Host: localhost    Database: sparkler
-- ------------------------------------------------------
-- Server version	8.0.11


DROP TABLE IF EXISTS `reply_stats`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reply_stats` (
  `post_id` bigint(20) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `comment_stats`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comment_stats` (
  `reply_id` bigint(20) NOT NULL,
  `ref_id` bigint(20) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`reply_id`, `ref_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `comment_relations`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comment_relations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ref_id` bigint(20) DEFAULT NULL,
  `comment_id` bigint(20) DEFAULT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_relation_uk_1` (`ref_id`,`comment_id`),
  KEY `comment_relation_idx_1` (`ref_id`,`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `comments`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reply_id` bigint(20) NOT NULL,
  `ref_id` bigint(20) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `content` text NOT NULL,
  `number` int(11) NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_idx_1` (`reply_id`,`ref_id`,`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



DROP TABLE IF EXISTS `replies`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `replies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `content` text NOT NULL,
  `number` int(11) NOT NULL,
  `status` int(11) DEFAULT 0 NOT NULL,
  PRIMARY KEY (`id`),
  KEY `reply_idx_1` (`post_id`,`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `posts`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `title` varchar(64) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `content` text NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `update_at` datetime(6) NOT NULL,
  `status` int(11) DEFAULT 0 NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_idx_1` (`create_at`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `post_stats`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `post_stats` (
  `category` bigint(20) NOT NULL,
  `section` bigint(20) NOT NULL,
  `number` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  KEY `post_stats_idx_1` (`number` DESC,`category`, `section`),
  UNIQUE KEY `post_stats_uk_1` (`category`,`section`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `sequences`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sequences` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sequence_uk_1` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `categories`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categories` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(32) NOT NULL,
  `status` int(11) DEFAULT 1 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_uk_1` (`title`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `sections`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sections` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) unsigned NOT NULL,
  `title` varchar(32) NOT NULL,
  `description` varchar(256) DEFAULT '',
  `status` int(11) DEFAULT 1 NOT NULL,
  `timestamp` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `section_uk_1` (`category_id`,`title`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;