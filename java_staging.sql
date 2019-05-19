# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.23)
# Database: java_staging
# Generation Time: 2018-09-26 04:03:21 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table order
# ------------------------------------------------------------

CREATE TABLE `order` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `status` tinyint(3) unsigned NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `order_name` varchar(32) NOT NULL DEFAULT '',
  `order_desc` varchar(255) NOT NULL DEFAULT '',
  `order_extra` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user
# ------------------------------------------------------------

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `status` tinyint(3) unsigned NOT NULL,
  `name` varchar(32) NOT NULL,
  `display_name` varchar(16) NOT NULL,
  `profile_picture` varchar(64) NOT NULL DEFAULT '',
  `phone` char(11) NOT NULL,
  `email` varchar(32) NOT NULL DEFAULT '',
  `password` char(64) NOT NULL DEFAULT '',
  `password_salt` char(64) NOT NULL DEFAULT '',
  `sex` tinyint(3) unsigned NOT NULL,
  `age` tinyint(3) unsigned NOT NULL,
  `birthday` date DEFAULT '1970-01-01',
  `introduction` varchar(32) DEFAULT '',
  `add_time` datetime(3) NOT NULL DEFAULT '1970-01-01 00:00:00.000',
  `update_time` datetime(3) NOT NULL DEFAULT '1970-01-01 00:00:00.000',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idex_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10291 DEFAULT CHARSET=utf8mb4;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
