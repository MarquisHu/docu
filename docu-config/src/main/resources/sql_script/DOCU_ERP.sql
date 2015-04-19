/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.16 : Database - docu_erp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`docu_erp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `docu_erp`;

/*Table structure for table `docu_erp_account` */

DROP TABLE IF EXISTS `docu_erp_account`;

CREATE TABLE `docu_erp_account` (
  `ACCOUNT_ID` char(20) NOT NULL,
  `USER_ID` char(20) NOT NULL,
  `BALANCE_AMOUNT` float DEFAULT NULL,
  `PRIVATE_AMOUNT` float DEFAULT NULL,
  `COMMON_AMOUNT` float DEFAULT NULL,
  `UPDATE_BY` char(20) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_ID`),
  KEY `FK_docu_erp_account` (`USER_ID`),
  CONSTRAINT `FK_docu_erp_account` FOREIGN KEY (`USER_ID`) REFERENCES `docu_erp_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `docu_erp_account` */

insert  into `docu_erp_account`(`ACCOUNT_ID`,`USER_ID`,`BALANCE_AMOUNT`,`PRIVATE_AMOUNT`,`COMMON_AMOUNT`,`UPDATE_BY`,`UPDATE_TIME`) values ('1','HUMA4',300,120,180,'HUMA4','2015-04-19 21:27:04'),('2','ADMIN',300,120,180,'HUMA4','2015-04-19 21:34:42');

/*Table structure for table `docu_erp_system_user` */

DROP TABLE IF EXISTS `docu_erp_system_user`;

CREATE TABLE `docu_erp_system_user` (
  `USER_ID` char(20) NOT NULL,
  `PASSWORD` char(20) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `docu_erp_system_user` */

insert  into `docu_erp_system_user`(`USER_ID`,`PASSWORD`) values ('HUMA4','123456');

/*Table structure for table `docu_erp_user` */

DROP TABLE IF EXISTS `docu_erp_user`;

CREATE TABLE `docu_erp_user` (
  `USER_ID` char(20) NOT NULL,
  `EN_NAME` char(50) NOT NULL,
  `CN_NAME` char(50) NOT NULL,
  `TEL_NUMBER` char(11) NOT NULL,
  `IS_ACTIVE` char(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `docu_erp_user` */

insert  into `docu_erp_user`(`USER_ID`,`EN_NAME`,`CN_NAME`,`TEL_NUMBER`,`IS_ACTIVE`) values ('ADMIN','ADMIN','ADMIN','18688187010','1'),('HUMA4','Marquis Hu','HU XINHUI','18688187017','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
