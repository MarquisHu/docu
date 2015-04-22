/*
SQLyog ∆Û“µ∞Ê - MySQL GUI v8.14 
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

insert  into `docu_erp_account`(`ACCOUNT_ID`,`USER_ID`,`BALANCE_AMOUNT`,`PRIVATE_AMOUNT`,`COMMON_AMOUNT`,`UPDATE_BY`,`UPDATE_TIME`) values ('1','HUMA4',400,80,320,'huma4','2015-04-22 17:00:15'),('2','ADMIN',100,20,80,'huma4','2015-04-22 12:46:43');

/*Table structure for table `docu_erp_bill` */

DROP TABLE IF EXISTS `docu_erp_bill`;

CREATE TABLE `docu_erp_bill` (
  `BILL_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` char(20) NOT NULL,
  `USER_ID` char(20) NOT NULL,
  `ORIGIN_AMOUNT` float NOT NULL,
  `EXPENSE_AMOUNT` float NOT NULL,
  `BALANCE` float NOT NULL,
  `ACTIVITY_ID` bigint(20) NOT NULL,
  `UPDATE_BY` char(20) NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`BILL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `docu_erp_bill` */

insert  into `docu_erp_bill`(`BILL_ID`,`ACCOUNT_ID`,`USER_ID`,`ORIGIN_AMOUNT`,`EXPENSE_AMOUNT`,`BALANCE`,`ACTIVITY_ID`,`UPDATE_BY`,`UPDATE_TIME`) values (1,'1','HUMA4',100,10,90,1,'HUMA4','2015-04-22 09:41:03');

/*Table structure for table `docu_erp_charge` */

DROP TABLE IF EXISTS `docu_erp_charge`;

CREATE TABLE `docu_erp_charge` (
  `CHARGE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` char(20) NOT NULL,
  `ACCOUNT_ID` char(20) NOT NULL,
  `RECV_AMOUNT` float NOT NULL,
  `RECV_TIME` datetime NOT NULL,
  `PERCENT` int(11) NOT NULL,
  `UPDATE_BY` char(20) NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`CHARGE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `docu_erp_charge` */

insert  into `docu_erp_charge`(`CHARGE_ID`,`USER_ID`,`ACCOUNT_ID`,`RECV_AMOUNT`,`RECV_TIME`,`PERCENT`,`UPDATE_BY`,`UPDATE_TIME`) values (1,'HUMA4','1',100,'2015-04-20 22:39:42',20,'HUMA4','2015-04-20 22:39:42'),(2,'ADMIN','2',100,'2015-04-22 12:46:43',20,'ADMIN','2015-04-22 12:46:43'),(3,'HUMA4','1',200,'2015-04-22 13:12:30',20,'HUMA4','2015-04-22 13:12:30'),(4,'HUMA4','1',100,'2015-04-22 17:00:15',20,'HUMA4','2015-04-22 17:00:15');

/*Table structure for table `docu_erp_user` */

DROP TABLE IF EXISTS `docu_erp_user`;

CREATE TABLE `docu_erp_user` (
  `USER_ID` char(20) NOT NULL,
  `USER_NAME` char(50) NOT NULL,
  `TEL_NUMBER` char(11) NOT NULL,
  `IS_ACTIVE` char(1) NOT NULL DEFAULT '1',
  `IS_ADMIN` char(1) NOT NULL DEFAULT '0',
  `PASSWORD` char(20) NOT NULL DEFAULT '123456',
  `UPDATE_BY` char(20) NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `docu_erp_user` */

insert  into `docu_erp_user`(`USER_ID`,`USER_NAME`,`TEL_NUMBER`,`IS_ACTIVE`,`IS_ADMIN`,`PASSWORD`,`UPDATE_BY`,`UPDATE_TIME`) values ('ADMIN','ADMIN','18688187011','1','0','123456','huma4','2015-04-22 17:01:22'),('HUMA4','Marquis Hu','18688187017','1','1','123456','huma4','2015-04-22 18:15:43'),('TEST','TEST','18988187011','0','0','123456','huma4','2015-04-22 17:01:36');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
