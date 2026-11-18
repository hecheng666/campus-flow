-- CampusFlow 数据库初始化脚本
-- 默认演示账号：admin / 123456（首次登录后密码会自动升级为 BCrypt）
-- 本地导入：mysql -uroot -p < sql/schema.sql

CREATE DATABASE IF NOT EXISTS `campusflow` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `campusflow`;

-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: campusflow
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clazz`
--

DROP TABLE IF EXISTS `clazz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clazz` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,主键',
  `name` varchar(30) NOT NULL COMMENT '班级名称',
  `room` varchar(20) DEFAULT NULL COMMENT '班级教室',
  `begin_date` date NOT NULL COMMENT '开课时间',
  `end_date` date NOT NULL COMMENT '结课时间',
  `master_id` int unsigned DEFAULT NULL COMMENT '辅导员ID, 关联员工表ID',
  `subject` tinyint unsigned NOT NULL COMMENT '专业, 1:软件工程, 2:计算机科学与技术, 3:人工智能, 4:数据科学, 5:网络工程, 6:信息安全',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clazz`
--

LOCK TABLES `clazz` WRITE;
/*!40000 ALTER TABLE `clazz` DISABLE KEYS */;
INSERT INTO `clazz` VALUES (0,'暂无对应班级','0','2025-11-01','2025-11-01',0,0,'2025-11-16 20:12:29','2025-11-16 20:12:31'),(2,'软件工程2301班','105','2023-07-20','2024-02-20',2,1,'2023-06-01 17:46:10','2023-06-01 17:46:10'),(4,'数据科学2201班','209','2023-08-01','2024-02-15',4,4,'2023-06-01 17:51:21','2023-06-01 17:51:21'),(8,'计算机科学2302班','101','2023-06-01','2024-01-25',8,2,'2023-11-15 11:35:46','2023-12-13 14:31:24'),(14,'人工智能2301班','101','2023-06-01','2024-01-25',7,3,'2025-11-16 07:39:05','2025-11-16 07:39:05');
/*!40000 ALTER TABLE `clazz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID, 主键',
  `name` varchar(10) NOT NULL COMMENT '部门名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (2,'计算机学院','2024-09-25 09:47:40','2025-11-14 03:29:50'),(3,'招生就业处','2024-09-25 09:47:40','2025-11-14 03:29:44'),(4,'学生工作处','2024-09-25 09:47:40','2025-11-14 03:29:48'),(5,'人事处','2024-09-25 09:47:40','2025-11-20 12:00:21'),(6,'后勤保障处','2024-11-30 20:56:37','2025-11-14 03:29:46'),(12,'软件学院','2025-11-14 03:16:01','2025-11-14 03:29:52');
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,主键',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT '123456' COMMENT '密码, BCrypt 哈希',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `gender` tinyint unsigned NOT NULL COMMENT '性别, 1:男, 2:女',
  `phone` char(11) NOT NULL COMMENT '手机号',
  `job` tinyint unsigned DEFAULT NULL COMMENT '职位, 1 辅导员, 2 任课教师 , 3 教务管理员, 4 系主任, 5 招生专员',
  `salary` int unsigned DEFAULT NULL COMMENT '薪资',
  `image` varchar(255) DEFAULT NULL COMMENT '头像',
  `entry_date` date DEFAULT NULL COMMENT '入职日期',
  `dept_id` int unsigned DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
INSERT INTO `emp` VALUES (1,'zhangwei','123456','张伟',1,'13309090001',4,15000,NULL,'2000-01-01',2,'2023-10-20 16:35:33','2023-11-16 16:11:26'),(2,'wangfang','123456','王芳',1,'13309090002',2,8000,NULL,'2015-01-01',2,'2023-10-20 16:35:33','2025-12-24 01:37:06'),(3,'liqiang','123456','李强',1,'13309090003',2,8900,NULL,'2008-05-01',2,'2023-10-20 16:35:33','2023-10-20 16:35:39'),(4,'liuyang','123456','刘洋',1,'13309090004',2,9200,NULL,'2007-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:35:41'),(5,'chenjing','123456','陈静',1,'13309090005',2,9500,NULL,'2012-12-05',2,'2023-10-20 16:35:33','2023-10-20 16:35:43'),(6,'yangfan','123456','杨帆',1,'18809091214',1,8000,NULL,'2022-09-01',12,'2025-11-14 20:02:15','2025-12-24 01:38:46'),(7,'zhaolei','123456','赵磊',1,'13309090007',1,4700,NULL,'2005-08-01',1,'2023-10-20 16:35:33','2023-10-20 16:35:47'),(8,'huangmin','123456','黄敏',1,'13309090008',1,4800,NULL,'2014-11-09',1,'2023-10-20 16:35:33','2023-10-20 16:35:49'),(9,'zhoujie','123456','周杰',1,'13309090009',1,4900,NULL,'2011-03-11',1,'2023-10-20 16:35:33','2023-10-20 16:35:51'),(10,'wuting','123456','吴婷',1,'13309090010',1,5000,NULL,'2013-09-05',1,'2023-10-20 16:35:33','2023-10-20 16:35:53'),(11,'xupeng','123456','徐鹏',1,'13309090011',2,9700,NULL,'2007-02-01',2,'2023-10-20 16:35:33','2023-10-20 16:35:55'),(12,'sunyue','123456','孙悦',1,'13309090012',2,10000,NULL,'2008-08-18',2,'2023-10-20 16:35:33','2023-10-20 16:35:57'),(13,'machao','123456','马超',1,'13309090013',1,5300,NULL,'2012-11-01',1,'2023-10-20 16:35:33','2023-10-20 16:35:59'),(14,'zhulin','123456','朱琳',1,'13309090014',2,10600,NULL,'2002-08-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:01'),(15,'hujun','123456','胡军',2,'13309090015',2,10900,NULL,'2011-05-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:03'),(16,'guotao','123456','郭涛',1,'13309090016',2,9600,NULL,'2010-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:05'),(17,'hejing','12345678','何静',1,'13309090017',1,5800,NULL,'2015-03-21',1,'2023-10-20 16:35:33','2023-10-20 16:36:07'),(18,'gaoxiang','123456','高翔',1,'13309090018',2,10200,NULL,'2015-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:09'),(19,'linxue','123456','林雪',2,'13309090019',2,10500,NULL,'2008-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:11'),(20,'luocheng','123456','罗成',1,'13309090020',2,10800,NULL,'2018-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:13'),(21,'liangyu','123456','梁宇',1,'13309090021',5,5200,NULL,'2015-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:15'),(22,'songjia','123456','宋佳',1,'13309090022',5,5500,NULL,'2016-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:17'),(23,'tanglei','123456','唐磊',1,'13309090023',5,5800,NULL,'2012-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:19'),(24,'xuqing','123456','许晴',1,'13309090024',5,5000,NULL,'2006-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:21'),(25,'dengchao','123456','邓超',1,'13309090025',5,4800,NULL,'2002-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:23'),(26,'fengxue','123456','冯雪',1,'13309090026',5,5400,NULL,'2011-01-01',3,'2023-10-20 16:35:33','2023-11-08 22:12:46'),(27,'zengyi','123456','曾毅',1,'13309090027',2,6600,NULL,'2004-01-01',2,'2023-10-20 16:35:33','2023-11-16 17:56:59'),(30,'pengbo','123456','彭博',1,'13309090030',NULL,NULL,NULL,'2020-03-01',12,'2023-10-20 16:35:33','2025-12-24 01:36:49'),(36,'donghao','123456','董浩',1,'18809091212',2,6800,NULL,'2023-10-19',2,'2023-10-20 20:44:54','2023-11-09 09:41:04'),(56,'yuanquan','123456','袁泉',1,'13333333333',2,50000,NULL,'2025-12-18',12,'2025-12-24 01:10:52','2025-12-24 01:10:52');
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_expr`
--

DROP TABLE IF EXISTS `emp_expr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_expr` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID, 主键',
  `emp_id` int unsigned DEFAULT NULL COMMENT '员工ID',
  `begin` date DEFAULT NULL COMMENT '开始时间',
  `end` date DEFAULT NULL COMMENT '结束时间',
  `company` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `job` varchar(50) DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工作经历';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_expr`
--

LOCK TABLES `emp_expr` WRITE;
/*!40000 ALTER TABLE `emp_expr` DISABLE KEYS */;
INSERT INTO `emp_expr` VALUES (27,1,'2025-11-22','2025-12-29','字节跳动','c++开发'),(31,53,'2012-07-01','2019-03-03','百度科技股份有限公司','java开发'),(32,53,'2019-03-15','2023-03-01','阿里巴巴科技股份有限公司','架构师'),(39,2,'2012-07-01','2015-06-20','中软国际股份有限公司','java开发'),(40,2,'2015-07-01','2019-03-03','百度科技股份有限公司','java开发'),(41,2,'2019-03-15','2023-03-01','阿里巴巴科技股份有限公司','架构师');
/*!40000 ALTER TABLE `emp_expr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operate_log`
--

DROP TABLE IF EXISTS `operate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operate_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `operate_emp_id` int unsigned DEFAULT NULL COMMENT '操作人ID',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `class_name` varchar(100) DEFAULT NULL COMMENT '操作的类名',
  `method_name` varchar(100) DEFAULT NULL COMMENT '操作的方法名',
  `method_params` varchar(2000) DEFAULT NULL COMMENT '方法参数',
  `return_value` varchar(2000) DEFAULT NULL COMMENT '返回值',
  `cost_time` bigint unsigned DEFAULT NULL COMMENT '方法执行耗时, 单位:ms',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operate_log`
--

LOCK TABLES `operate_log` WRITE;
/*!40000 ALTER TABLE `operate_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `operate_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,主键',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `no` char(10) NOT NULL COMMENT '学号',
  `gender` tinyint unsigned NOT NULL COMMENT '性别, 1: 男, 2: 女',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `id_card` char(18) NOT NULL COMMENT '身份证号',
  `is_college` tinyint unsigned NOT NULL COMMENT '是否来自于院校, 1:是, 0:否',
  `address` varchar(100) DEFAULT NULL COMMENT '联系地址',
  `degree` tinyint unsigned DEFAULT NULL COMMENT '最高学历, 1:初中, 2:高中, 3:大专, 4:本科, 5:硕士, 6:博士',
  `graduation_date` date DEFAULT NULL COMMENT '毕业时间',
  `clazz_id` int unsigned NOT NULL COMMENT '班级ID, 关联班级表ID',
  `violation_count` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '违纪次数',
  `violation_score` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '违纪扣分',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `no` (`no`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `id_card` (`id_card`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (5,'李梦','2022000005',2,'18800160002','110120000300200005',1,'北京市海淀区学院路5号',1,'2020-07-01',0,2,10,'2023-11-14 21:22:19','2025-11-16 21:29:07'),(6,'王雪','2022000006',2,'18800000034','110120000300200006',1,'北京市海淀区学院路6号',4,'2021-07-01',2,1,2,'2023-11-14 21:22:19','2025-11-16 21:29:22'),(7,'陈晨','2023001010',1,'13712345678','110090110090110090',0,'学院路110号',5,'2020-07-01',2,0,0,'2023-11-14 21:22:19','2025-11-16 21:29:37'),(8,'张敏','2022000008',2,'18800000077','110120000300200008',1,'北京市海淀区学院路8号',5,'2023-07-01',2,0,0,'2023-11-14 21:22:19','2023-11-14 21:22:19'),(9,'徐涛','2022000009',1,'18800000341','110120000300200009',1,'北京市海淀区学院路9号',3,'2024-07-01',2,0,0,'2023-11-14 21:22:19','2023-11-14 21:22:19'),(10,'赵鹏','2022000010',1,'18800006571','110120000300200010',1,'北京市海淀区学院路10号',2,'2020-07-01',2,0,0,'2023-11-14 21:22:19','2023-11-14 21:22:19'),(11,'钟杰','2022000011',1,'18800000391','110120000300200011',1,'北京市海淀区学院路11号',4,'2021-07-01',0,0,0,'2023-11-14 21:22:19','2023-11-15 16:21:24'),(12,'崔浩','2022000012',1,'18800000781','110120000300200018',1,'北京市海淀区学院路12号',4,'2022-07-05',8,6,17,'2023-11-14 21:22:19','2023-12-13 14:33:58'),(13,'韩磊','2022000013',1,'18800008901','110120000300200013',1,'北京市海淀区学院路13号',4,'2023-07-01',2,0,0,'2023-11-14 21:22:19','2023-11-15 16:21:21'),(14,'唐婉','2022000014',2,'18800009201','110120000300200014',1,'北京市海淀区学院路14号',4,'2024-07-01',0,0,0,'2023-11-14 21:22:19','2023-11-15 16:21:17'),(15,'刘畅','2022000015',1,'18800009401','110120000300200015',1,'北京市海淀区学院路15号',3,'2020-07-01',4,0,0,'2023-11-14 21:22:19','2023-11-14 21:22:19'),(16,'李阳','2022000016',1,'18800008501','110120000300200016',1,'北京市海淀区学院路16号',4,'2021-07-01',4,0,0,'2023-11-14 21:22:19','2023-11-14 21:22:19'),(17,'王悦','2022000017',2,'18800007601','110120000300200017',1,'北京市海淀区学院路17号',2,'2022-07-01',4,0,0,'2023-11-14 21:22:19','2023-11-14 21:22:19'),(18,'郑凯','2023001101',1,'13309092345','110110110110110110',0,'北京市海淀区学院路街道88号',5,'2021-07-01',8,2,7,'2023-11-15 16:26:18','2023-11-15 16:40:10'),(20,'周晨','2024010801',1,'15909091235','159090912351590909',1,'海淀学院路',4,'2024-01-01',0,0,0,'2025-11-16 19:48:34','2025-12-22 18:31:37');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-03 14:27:47

-- 演示数据清理
DELETE FROM `operate_log`;
