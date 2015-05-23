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

/*Table structure for table `docu_erp_charge` */

DROP TABLE IF EXISTS `docu_erp_charge`;

CREATE TABLE `docu_erp_charge` (
  `CHARGE_ID` bigint(20) NOT NULL,
  `ACCOUNT_ID` bigint(20) NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `RECV_AMOUNT` decimal(16,0) NOT NULL,
  `RECV_TIME` datetime NOT NULL,
  `PAYER_ID` varchar(20) NOT NULL,
  `PERCENT` int(11) NOT NULL,
  `STATUS` varchar(1) NOT NULL,
  `UPDATE_BY` varbinary(20) NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`CHARGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
