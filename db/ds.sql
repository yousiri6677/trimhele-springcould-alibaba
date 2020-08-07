/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.62 : Database - decoratestate
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`decoratestate` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `decoratestate`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `addressId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '住址id',
  `address` varchar(225) COLLATE utf8mb4_bin NOT NULL COMMENT '住址',
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `address` */

insert  into `address`(`addressId`,`address`) values (1,'郑州市'),(2,'二七区'),(3,'管城区'),(4,'金水区'),(5,'中原区');

/*Table structure for table `caseImgs` */

DROP TABLE IF EXISTS `caseImgs`;

CREATE TABLE `caseImgs` (
  `imgId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '装修案例图片',
  `imgsrc` varchar(225) COLLATE utf8mb4_bin NOT NULL COMMENT '图片路径',
  `decoratecaseImgType` bigint(20) NOT NULL COMMENT '1:Logo，2：各种图',
  PRIMARY KEY (`imgId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `caseImgs` */

insert  into `caseImgs`(`imgId`,`imgsrc`,`decoratecaseImgType`) values (1,'imgs/zhuangxiutu/z2.jpg',1);

/*Table structure for table `companyImages` */

DROP TABLE IF EXISTS `companyImages`;

CREATE TABLE `companyImages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `imgInfo` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '图片大概信息',
  `imgPath` varchar(225) COLLATE utf8mb4_bin NOT NULL COMMENT '图片路径',
  `imgState` int(11) NOT NULL DEFAULT '0' COMMENT '0:正常,1:默认',
  `userId` bigint(20) NOT NULL COMMENT '所属用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `companyImages` */

/*Table structure for table `companyinfo` */

DROP TABLE IF EXISTS `companyinfo`;

CREATE TABLE `companyinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公司信息Id',
  `userId` bigint(20) NOT NULL COMMENT '用户id',
  `companyProfile` varchar(225) COLLATE utf8mb4_bin NOT NULL COMMENT '公司名称',
  `companyLogo` bigint(20) NOT NULL COMMENT 'imgId',
  `regCapital` double NOT NULL COMMENT '注册资金',
  `addressId` bigint(20) NOT NULL COMMENT '公司住址Id',
  `companyTypeId` bigint(20) NOT NULL COMMENT '公司装修类型',
  `companyStyleId` bigint(20) NOT NULL COMMENT '公司主要装修风格',
  `companyJianjie` varchar(225) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公司简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `companyinfo` */

insert  into `companyinfo`(`id`,`userId`,`companyProfile`,`companyLogo`,`regCapital`,`addressId`,`companyTypeId`,`companyStyleId`,`companyJianjie`) values (1,4,'游氏大公司',1,66666,2,1,2,'游大公司的简介'),(2,3,'游大公司',1,11111,3,1,2,'游大公司的简介'),(7,5,'scc',1,50000,4,2,3,'scc的简介');

/*Table structure for table `decoratecase` */

DROP TABLE IF EXISTS `decoratecase`;

CREATE TABLE `decoratecase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '装修案例ID',
  `companyId` bigint(20) NOT NULL COMMENT '所属公司ID',
  `imgId` bigint(20) NOT NULL COMMENT '图片库ID',
  `title` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '标题',
  `content` varchar(225) COLLATE utf8mb4_bin NOT NULL COMMENT '装修简介',
  `area` double DEFAULT NULL COMMENT '装修面积',
  `browseNum` int(11) NOT NULL COMMENT '浏览数',
  `demandTypeId` bigint(20) NOT NULL COMMENT '装修类型Id',
  `turnover` double NOT NULL COMMENT '成交金额',
  `decorateStyleId` bigint(20) NOT NULL COMMENT '装修风格id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `decoratecase` */

insert  into `decoratecase`(`id`,`companyId`,`imgId`,`title`,`content`,`area`,`browseNum`,`demandTypeId`,`turnover`,`decorateStyleId`) values (1,1,1,'现代简约装修','这是一个成功的现代简约装修的案例',130,1,1,100000,2),(2,1,1,'中式现代装修','这是一个成功的中式现代装修的案例',120,1,1,90000,3);

/*Table structure for table `decoratestyle` */

DROP TABLE IF EXISTS `decoratestyle`;

CREATE TABLE `decoratestyle` (
  `decorateStyleid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `decorateStyleName` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '装修风格',
  PRIMARY KEY (`decorateStyleid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `decoratestyle` */

insert  into `decoratestyle`(`decorateStyleid`,`decorateStyleName`) values (1,'现代简约'),(2,'中式现代'),(3,'美式田园'),(4,'美式经典'),(8,'其他'),(9,'不限');

/*Table structure for table `demand` */

DROP TABLE IF EXISTS `demand`;

CREATE TABLE `demand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '需求id自增长',
  `title` varchar(225) COLLATE utf8mb4_bin NOT NULL COMMENT '需求标题',
  `content` varchar(500) COLLATE utf8mb4_bin NOT NULL COMMENT '需求具体内容',
  `releaseId` bigint(20) NOT NULL COMMENT '发布人Id',
  `demandTypeId` bigint(20) NOT NULL COMMENT '需求类型Id',
  `money` double NOT NULL COMMENT '需求金额（可能是范围）',
  `requirements` varchar(500) COLLATE utf8mb4_bin NOT NULL COMMENT '要求',
  `releaseTime` datetime NOT NULL COMMENT '发布时间',
  `acceptanceTime` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '验收时间',
  `referenceDoc` varchar(225) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参考文档（可选）',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '0:正在招标，1：已结束，-1：冻结',
  `decoratestyleId` bigint(20) NOT NULL COMMENT '风格id',
  `contactsName` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `demand` */

insert  into `demand`(`id`,`title`,`content`,`releaseId`,`demandTypeId`,`money`,`requirements`,`releaseTime`,`acceptanceTime`,`referenceDoc`,`state`,`decoratestyleId`,`contactsName`,`phone`) values (1,'的撒123123','test',0,1,999999,'test','2020-03-20 04:15:00','2021-03-31',NULL,0,1,'企鹅','123'),(2,'阿萨德','阿迪啊',0,2,123,'阿萨德 ','2020-03-20 04:42:25','2020-03-25',NULL,0,1,'阿萨德啊','13'),(3,'阿三打扫','阿迪啊',0,3,1,'阿萨德 ','2020-03-20 04:42:42','2020-03-25',NULL,0,1,'阿萨德','12312321123'),(4,'阿萨德','阿迪啊 答案是的123123',0,2,1,'阿萨德 爱的阿瑟','2020-03-20 04:44:00','2020-03-28',NULL,0,2,'阿三打扫阿瑟','123213123123'),(5,'大声地','阿瑟打',0,2,2131,'撒的撒','2020-03-20 04:54:27','2020-03-25',NULL,0,1,'阿萨德','1231'),(6,'大声地','阿瑟打',0,2,1,'撒的撒','2020-03-20 04:57:59','2020-03-25',NULL,0,1,'阿萨德','1231'),(7,'阿萨德啊','阿萨德',0,3,123,'阿萨德','2020-03-20 04:58:17','2020-03-28',NULL,0,1,'但飒飒的','1123'),(8,'阿萨德啊','阿萨德',0,3,123,'阿萨德','2020-03-20 04:59:13','2020-03-25',NULL,0,1,'但飒飒的','1123'),(9,'的撒','啊的',0,2,123,'阿萨德','2020-03-20 05:00:51','2020-03-25',NULL,0,1,'阿瑟打算','123'),(10,'的撒','啊的',0,2,123,'阿萨德','2020-03-20 05:01:01','2020-03-25',NULL,0,1,'阿瑟打算','123'),(11,'测试','测试',0,2,123,'测试','2020-03-20 05:44:23','2020-03-25',NULL,0,1,'测试','123'),(12,'测试1','测试',0,2,123,'测试','2020-03-20 05:45:16','2020-03-25',NULL,0,1,'测试','123'),(13,'测试1','测试1',0,2,123123,'测试','2020-03-21 01:06:37','2020-03-27',NULL,0,1,'测试','123'),(14,'test','test',0,3,123,'test','2020-03-21 01:31:04','2020-03-31',NULL,0,2,'test','123'),(15,'1231231','123213',0,3,2132131,'123213','2020-03-22 08:19:04','2020-03-27',NULL,0,2,'213213','213213'),(16,'123123111','123213',0,3,2132131,'123213','2020-03-22 08:19:19','2020-03-24',NULL,0,2,'213213','213213'),(20,'我需装修一个中式现代的房子','我需装修一个中式现代的房子',9,1,30000,'我需装修一个中式现代的房子','2020-04-03 16:14:08','2021-04-09',NULL,0,2,'嘻嘻嘻','123123'),(23,'hhqeqw额我企鹅','额我企鹅群我',9,3,13221321321321,'请问我去额球','2020-04-06 09:35:41','2020-04-30','https://th-images1.oss-cn-beijing.aliyuncs.com/img/2020-04-06/cf39987d5d364abd984a9ece042e887f-简历-游志龙.doc',0,1,'12321321','3123213'),(24,'的撒打算学习','详细信息',9,2,123123,'撒旦撒旦','2020-04-06 09:59:51','2020-04-22','https://th-images1.oss-cn-beijing.aliyuncs.com/img/2020-04-06/748887bd31f74a3a96b87529a1a9b4a3-新建文本文档.txt',0,1,'123213','21321321'),(25,'aaa','aaa',9,1,123123,'aaa','2020-04-08 05:22:52','2020-04-08','https://th-images1.oss-cn-beijing.aliyuncs.com/img/2020-04-08/d2e52bd17e3443788006ff164657e58a-.project',0,9,'aaa','123123'),(26,'aaaaa','qqqq',9,2,111,'qqqqqqqqqqqqq','2020-04-08 05:23:28','2020-04-15','https://th-images1.oss-cn-beijing.aliyuncs.com/img/2020-04-08/4c216da4df594712a32b45d7a02b9aa3-.project',0,2,'111','123123123');

/*Table structure for table `demandbill` */

DROP TABLE IF EXISTS `demandbill`;

CREATE TABLE `demandbill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账单Id',
  `userId` bigint(20) NOT NULL COMMENT '用户id',
  `demand` bigint(20) NOT NULL COMMENT '需求id',
  `completionTime` datetime NOT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `demandbill` */

/*Table structure for table `demandinfo` */

DROP TABLE IF EXISTS `demandinfo`;

CREATE TABLE `demandinfo` (
  `demandInfoId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '需求具体内容id',
  `demandTypeId` bigint(20) NOT NULL COMMENT '对应需求类型Id',
  `demandInfoName` varchar(25) COLLATE utf8mb4_bin NOT NULL COMMENT '具体需求名称',
  PRIMARY KEY (`demandInfoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `demandinfo` */

/*Table structure for table `demandtype` */

DROP TABLE IF EXISTS `demandtype`;

CREATE TABLE `demandtype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '装修条件ID',
  `typeName` varchar(25) COLLATE utf8mb4_bin NOT NULL COMMENT '条件名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `demandtype` */

insert  into `demandtype`(`id`,`typeName`) values (1,'全包'),(2,'半包'),(3,'纯设计');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userName` varchar(25) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `account` varchar(25) COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `passWord` varchar(25) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `idNumber` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证号',
  `userTypeId` int(11) DEFAULT NULL COMMENT '用户类型id',
  `address` varchar(225) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '住址',
  `userState` int(11) DEFAULT NULL COMMENT '0:正常使用，-1：冻结',
  `phone` varbinary(11) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`userName`,`account`,`passWord`,`idNumber`,`userTypeId`,`address`,`userState`,`phone`) values (1,'admin','admin','123456','410000000000000000',1,'河南',0,NULL),(2,'you','you','you','21231231',4,'河南',0,NULL),(3,'you1','you1','you','12431241',3,'河南',0,NULL),(4,'游志龙666','you666','123','12321321321321',3,'河南',0,NULL),(5,'scc','scc','123','41000000000000',3,'河南安阳',0,NULL),(6,'aaa','aaa','123','241242142141',3,'河南',1,NULL),(7,'ccc','ccc','123','241242142141',3,'河南',1,NULL),(8,'sss','sss','123','241242142141',3,'河南',1,NULL),(9,'user1','user1','123','241242142141',4,NULL,0,NULL),(11,'yougg','yougg','123','241242142141',4,NULL,0,'15738790496');

/*Table structure for table `usertype` */

DROP TABLE IF EXISTS `usertype`;

CREATE TABLE `usertype` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户类型Id',
  `userType` varchar(25) COLLATE utf8mb4_bin NOT NULL COMMENT '用户类型',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `usertype` */

insert  into `usertype`(`Id`,`userType`) values (1,'管理员'),(2,'商家'),(3,'公司'),(4,'普通用户');

/*Table structure for table `vieList` */

DROP TABLE IF EXISTS `vieList`;

CREATE TABLE `vieList` (
  `vieId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '竞标关系Id',
  `demandId` bigint(20) NOT NULL COMMENT '需求Id',
  `vieUserId` bigint(20) NOT NULL COMMENT '竞标人Id',
  `predictMoney` int(11) NOT NULL COMMENT '预计金额',
  `case1Id` bigint(20) DEFAULT NULL COMMENT '案例1',
  `case2Id` bigint(20) DEFAULT NULL COMMENT '案例2',
  `case3Id` bigint(20) DEFAULT NULL COMMENT '案例3',
  PRIMARY KEY (`vieId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `vieList` */

insert  into `vieList`(`vieId`,`demandId`,`vieUserId`,`predictMoney`,`case1Id`,`case2Id`,`case3Id`) values (3,1,3,999999,NULL,NULL,NULL),(11,1,4,999999,NULL,NULL,NULL),(13,2,4,200,2,0,0),(18,2,3,99,0,0,0),(19,20,4,10000,2,2,2),(20,1,5,22222,0,0,0),(21,15,4,111,1,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
