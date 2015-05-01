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
  `ACCOUNT_ID` bigint(20) NOT NULL,
  `USER_ID` char(20) NOT NULL,
  `BALANCE` float DEFAULT NULL,
  `COMMON_AMOUNT` float DEFAULT NULL,
  `ACCOUNT_TYPE` char(1) NOT NULL DEFAULT '1',
  `UPDATE_BY` char(20) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_ID`),
  KEY `FK_docu_erp_account` (`USER_ID`),
  CONSTRAINT `FK_docu_erp_account` FOREIGN KEY (`USER_ID`) REFERENCES `docu_erp_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `docu_erp_account` */

insert  into `docu_erp_account`(`ACCOUNT_ID`,`USER_ID`,`BALANCE`,`COMMON_AMOUNT`,`ACCOUNT_TYPE`,`UPDATE_BY`,`UPDATE_TIME`) values (1000001,'CMN',0,0,'0','huma4','2015-05-01 22:38:54'),(1000002,'HUMA4',0,0,'1','huma4','2015-05-01 22:38:54');

/*Table structure for table `docu_erp_acct_detail` */

DROP TABLE IF EXISTS `docu_erp_acct_detail`;

CREATE TABLE `docu_erp_acct_detail` (
  `ACCT_DETAIL_ID` bigint(20) NOT NULL,
  `ACCOUNT_ID` bigint(20) NOT NULL,
  `USER_ID` char(20) NOT NULL,
  `ORIGIN_AMOUNT` float NOT NULL,
  `CHANGE_AMOUNT` float NOT NULL,
  `BALANCE` float NOT NULL,
  `PAYER_ID` char(20) NOT NULL,
  `TRANSACTION_TYPE` char(1) NOT NULL DEFAULT '1',
  `TRANSACTION_TIME` datetime NOT NULL,
  `ACTIVITY_ID` bigint(20) DEFAULT NULL,
  `PERCENT` int(11) NOT NULL DEFAULT '20',
  `UPDATE_BY` char(20) NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`ACCT_DETAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `docu_erp_acct_detail` */

/*Table structure for table `docu_erp_activity` */

DROP TABLE IF EXISTS `docu_erp_activity`;

CREATE TABLE `docu_erp_activity` (
  `ACTIVITY_ID` bigint(20) NOT NULL,
  `EXPENSE_AMOUNT` float NOT NULL,
  `REMARK` char(255) NOT NULL,
  `LOCATION` char(255) NOT NULL,
  `ACTIVITY_TIME` datetime NOT NULL,
  `PERCENT` int(11) NOT NULL DEFAULT '20',
  `UPDATE_BY` char(20) NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`ACTIVITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `docu_erp_activity` */

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

insert  into `docu_erp_user`(`USER_ID`,`USER_NAME`,`TEL_NUMBER`,`IS_ACTIVE`,`IS_ADMIN`,`PASSWORD`,`UPDATE_BY`,`UPDATE_TIME`) values ('CMN','Common','18600000001','1','0','123456','HUMA4','2015-04-28 20:43:58'),('HUMA4','Marquis Hu','18688187017','1','1','123456','HUMA4','2015-04-28 20:44:04');

/*Table structure for table `docu_erp_uuid` */

DROP TABLE IF EXISTS `docu_erp_uuid`;

CREATE TABLE `docu_erp_uuid` (
  `SEQUENCE_NAME` varchar(100) NOT NULL,
  `SEQUENCE_INCREMENT` int(11) unsigned NOT NULL DEFAULT '1',
  `SEQUENCE_MIN_VALUE` int(11) unsigned NOT NULL DEFAULT '1000000',
  `SEQUENCE_MAX_VALUE` bigint(20) unsigned NOT NULL DEFAULT '18446744073709551615',
  `SEQUENCE_CUR_VALUE` bigint(20) unsigned NOT NULL DEFAULT '1000001',
  `SEQUENCE_CYCLE` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`SEQUENCE_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `docu_erp_uuid` */

insert  into `docu_erp_uuid`(`SEQUENCE_NAME`,`SEQUENCE_INCREMENT`,`SEQUENCE_MIN_VALUE`,`SEQUENCE_MAX_VALUE`,`SEQUENCE_CUR_VALUE`,`SEQUENCE_CYCLE`) values ('DOCU_ACCOUNT_UUID',1,1000000,18446744073709551615,1000001,0),('DOCU_ACTIVITY_UUID',1,1000000,18446744073709551615,1000001,0),('DOCU_ACCT_DETAIL_UUID',1,1000000,18446744073709551615,1000001,0);

/* Function  structure for function  `NEXTVAL` */

/*!50003 DROP FUNCTION IF EXISTS `NEXTVAL` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `NEXTVAL`(`SEQ_NAME` VARCHAR(100)) RETURNS bigint(20)
BEGIN
	DECLARE CUR_VAL BIGINT(20);
 
	SELECT SEQUENCE_CUR_VALUE INTO CUR_VAL
	 FROM DOCU_ERP_UUID
	WHERE SEQUENCE_NAME = SEQ_NAME;
 
	IF CUR_VAL IS NOT NULL THEN
		UPDATE DOCU_ERP_UUID
		SET SEQUENCE_CUR_VALUE = IF (
			(SEQUENCE_CUR_VALUE + SEQUENCE_INCREMENT) > SEQUENCE_MAX_VALUE,
			IF (
			SEQUENCE_CYCLE = TRUE,
			SEQUENCE_MIN_VALUE,
			NULL
			),
			SEQUENCE_CUR_VALUE + SEQUENCE_INCREMENT
		)
		WHERE SEQUENCE_NAME = SEQ_NAME;
	END IF; 
	RETURN CUR_VAL;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
