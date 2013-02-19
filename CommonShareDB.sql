/*
SQLyog Community v9.50 
MySQL - 5.1.38-community : Database - commonshare
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`commonshare` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `commonshare`;

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `taxonomy` int(11) unsigned DEFAULT NULL,
  `usergroup` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_taxonomy` (`taxonomy`),
  KEY `item_group` (`usergroup`),
  CONSTRAINT `item_group` FOREIGN KEY (`usergroup`) REFERENCES `user_group` (`ID`),
  CONSTRAINT `item_taxonomy` FOREIGN KEY (`taxonomy`) REFERENCES `taxonomy` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `item` */

insert  into `item`(`id`,`name`,`description`,`taxonomy`,`usergroup`) values (1,'Chicken','Chicken meat',2,26),(2,'Paneer',NULL,4,26),(3,'Biscuits','Buiscuits go here',7,26),(4,'Test','dsf',2,26),(5,'Test2','hahaha',3,26),(6,'Khoa','Khoa is a milk product',4,26),(7,'Chips','All the chips go here',7,26),(8,'Garden Chair','Garden chair is used in garden',17,26),(9,'Garden Table','This is garden table, used along with garden chair.',17,26),(10,'Pot','Pot to put flower plants in',17,26);

/*Table structure for table `purchase` */

DROP TABLE IF EXISTS `purchase`;

CREATE TABLE `purchase` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Item` int(11) unsigned NOT NULL,
  `Purchase_date` date NOT NULL,
  `Paid_by` varchar(100) NOT NULL,
  `Excluded_persons` varchar(100) DEFAULT NULL,
  `Comment` varchar(200) DEFAULT NULL,
  `Usergroup` int(11) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `purchase_usergroup` (`Usergroup`),
  KEY `purchase_item` (`Item`),
  CONSTRAINT `purchase_item` FOREIGN KEY (`Item`) REFERENCES `item` (`id`),
  CONSTRAINT `purchase_usergroup` FOREIGN KEY (`Usergroup`) REFERENCES `user_group` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

/*Data for the table `purchase` */

/*Table structure for table `taxonomy` */

DROP TABLE IF EXISTS `taxonomy`;

CREATE TABLE `taxonomy` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `taxonomy` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `parent` int(11) unsigned DEFAULT NULL,
  `usergroup` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `taxonomy_parent` (`parent`),
  KEY `taxonomy_group` (`usergroup`),
  CONSTRAINT `taxonomy_group` FOREIGN KEY (`usergroup`) REFERENCES `user_group` (`ID`),
  CONSTRAINT `taxonomy_parent` FOREIGN KEY (`parent`) REFERENCES `taxonomy` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

/*Data for the table `taxonomy` */

insert  into `taxonomy`(`id`,`taxonomy`,`description`,`parent`,`usergroup`) values (1,'Food','All food items',NULL,26),(2,'Meat','All meat items',1,26),(3,'Vegetables','All veggied go here',1,26),(4,'Milk Products','All milk products go here',1,26),(5,'Clothes','All clothes go here',NULL,26),(6,'Undergarments','Undergarments',5,26),(7,'Bakery','All bakery items go here',1,26),(8,'Shirt','All shirt items go here/',5,26),(9,'Pant','All pant items go here',5,26),(10,'Decoratives','All decoration items ho here',NULL,26),(11,'Treat','Treats',1,26),(12,'Jeans','Jeans Pant',9,26),(13,'Formal','Formal pants',9,26),(14,'Pyjama','Pyjamas',9,26),(17,'Gardening','All gardening items go in here.',NULL,26),(43,'Deco1','',10,26),(44,'Deco2','',10,26),(45,'Gar1','',17,26),(46,'Gar2','',17,26),(47,'Gar2.Gar2.1','',46,26),(48,'Informal','',9,26),(49,'Codds','These are codds',9,26);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(150) DEFAULT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
  `USERGROUP` int(11) unsigned NOT NULL,
  `ROLE` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN` (`LOGIN`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`ID`,`LOGIN`,`NAME`,`EMAIL`,`PASSWORD`,`ACTIVE`,`DELETED`,`USERGROUP`,`ROLE`) values (10,'Ego1','Ego1','harsh_singh2003@yahoo.co.in','<FÕgýLØÐ8«Ð/',1,0,26,'MODERATOR'),(11,'Wolverine','Wolverine','hkj','<FÕgýLØÐ8«Ð/',1,0,26,'MEMBER'),(12,'test','test','test2@test.com','	kÍF!ÓsÊÞNƒ&\'´ö',1,0,27,'MODERATOR'),(13,'Gunjan','Gunjan','gunjan.choudhary@gmail.com','ýÙ¥Ý\0ºÒ†æ:ïÙèd',1,0,26,'MEMBER');

/*Table structure for table `user_group` */

DROP TABLE IF EXISTS `user_group`;

CREATE TABLE `user_group` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(150) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `user_group` */

insert  into `user_group`(`ID`,`NAME`) values (26,'House#150'),(27,'testgroup'),(1,'TestGrp1'),(2,'TestGrp2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
