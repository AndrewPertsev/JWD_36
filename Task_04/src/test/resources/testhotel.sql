-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hotelappdb
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `apartments`
--

DROP TABLE IF EXISTS `apartments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartments` (
  `apartment_id` int NOT NULL,
  `capacity` int NOT NULL,
  `picture` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`apartment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartments`
--

LOCK TABLES `apartments` WRITE;
/*!40000 ALTER TABLE `apartments` DISABLE KEYS */;
INSERT INTO `apartments` VALUES (102,1,'2.jpg','business class apartment, 1 room',1),(103,2,'3.jpg','business class room',2),(104,2,'4.jpg','excellent double room',2),(105,2,'5.jpg','luxury room',3),(106,1,'6.jpg','business class apartment luxury',2),(107,6,'7.jpg','luxury room',3),(108,4,'8.jpg','business class room',1),(109,4,'9.jpg','super large room, with amazing view',1);
/*!40000 ALTER TABLE `apartments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_room`
--

DROP TABLE IF EXISTS `category_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_room` (
  `category_id` int DEFAULT NULL,
  `category_name` varchar(45) DEFAULT NULL,
  `category_price` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_room`
--

LOCK TABLES `category_room` WRITE;
/*!40000 ALTER TABLE `category_room` DISABLE KEYS */;
INSERT INTO `category_room` VALUES (1,'suite',80),(2,'business',200),(3,'luxury',400);
/*!40000 ALTER TABLE `category_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `managers`
--

DROP TABLE IF EXISTS `managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `managers` (
  `manager_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `is_topmanager` tinyint NOT NULL,
  PRIMARY KEY (`manager_id`),
  KEY `fk_managers_users1_idx` (`user_id`),
  CONSTRAINT `fk_managers_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `managers`
--

LOCK TABLES `managers` WRITE;
/*!40000 ALTER TABLE `managers` DISABLE KEYS */;
INSERT INTO `managers` VALUES (1,3,1);
/*!40000 ALTER TABLE `managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `menu` varchar(45) NOT NULL,
  `price_menu` int NOT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'breakfast',80),(2,'half',150),(3,'all',200),(4,'ultra',250);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offer` (
  `offer_id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `transfer_id` int NOT NULL,
  `menu_id` int NOT NULL,
  `apartment_id` int NOT NULL,
  `booked_from` date NOT NULL,
  `booked_before` date NOT NULL,
  `date_sent` date DEFAULT NULL,
  `date_paid` date DEFAULT NULL,
  `date_closed` date DEFAULT NULL,
  `manager_id` int NOT NULL,
  `is_sent` tinyint DEFAULT NULL,
  `is_paid` tinyint DEFAULT NULL,
  `is_closed` tinyint DEFAULT NULL,
  `price_offer` int DEFAULT NULL,
  `quantity_persons` int DEFAULT NULL,
  PRIMARY KEY (`offer_id`),
  KEY `fk_Response_Transfer1_idx` (`transfer_id`),
  KEY `fk_Response_Food1_idx` (`menu_id`),
  KEY `fk_response_user_request1_idx` (`request_id`),
  KEY `fk_offer_apartments1_idx` (`apartment_id`),
  CONSTRAINT `fk_offer_apartments1` FOREIGN KEY (`apartment_id`) REFERENCES `apartments` (`apartment_id`),
  CONSTRAINT `fk_Response_Food1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `fk_Response_Transfer1` FOREIGN KEY (`transfer_id`) REFERENCES `transfer` (`transfer_id`),
  CONSTRAINT `fk_response_user_request1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` VALUES (112,55,1,1,109,'2022-02-17','2022-02-18','2022-01-25','2022-01-25','2022-01-25',0,1,0,0,170,1),(113,56,1,1,108,'2022-02-17','2022-02-19','2022-01-25','2022-01-25','2022-01-25',0,1,1,0,330,1),(114,57,4,4,108,'2022-03-09','2022-03-11','2022-01-26','2022-01-26','2022-01-26',0,1,0,0,3140,4);
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `menu_id` int NOT NULL,
  `transfer_id` int NOT NULL,
  `quantity_persons` int NOT NULL,
  `date_in` date NOT NULL,
  `date_out` date NOT NULL,
  `distance` int NOT NULL,
  `date_request` date NOT NULL,
  `category_id` int DEFAULT NULL,
  `responded` tinyint DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `fk_user_request_users1_idx` (`user_id`),
  KEY `fk_request_menu1_idx` (`menu_id`),
  KEY `fk_request_transfer1_idx` (`transfer_id`),
  CONSTRAINT `fk_request_menu1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `fk_request_transfer1` FOREIGN KEY (`transfer_id`) REFERENCES `transfer` (`transfer_id`),
  CONSTRAINT `fk_user_request_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (55,3,1,1,1,'2022-02-17','2022-02-18',10,'2022-01-25',1,1),(56,3,1,1,1,'2022-02-17','2022-02-19',10,'2022-01-25',1,1),(57,3,1,1,4,'2022-03-01','2022-03-05',10,'2022-01-26',1,0);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'guest'),(2,'manager');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timesheet`
--

DROP TABLE IF EXISTS `timesheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timesheet` (
  `id_timesheet` int NOT NULL AUTO_INCREMENT,
  `apartment_id` int NOT NULL,
  `reserved_date` date NOT NULL,
  `isreserved` tinyint NOT NULL,
  PRIMARY KEY (`id_timesheet`),
  KEY `fk_booking_schedule_apartment_pool1_idx` (`apartment_id`),
  CONSTRAINT `fk_booking_schedule_apartment_pool1` FOREIGN KEY (`apartment_id`) REFERENCES `apartments` (`apartment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timesheet`
--

LOCK TABLES `timesheet` WRITE;
/*!40000 ALTER TABLE `timesheet` DISABLE KEYS */;
INSERT INTO `timesheet` VALUES (57,109,'2022-02-18',1),(58,108,'2022-02-18',1),(59,108,'2022-02-19',1),(60,108,'2022-03-10',1),(61,108,'2022-03-11',1);
/*!40000 ALTER TABLE `timesheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer`
--

DROP TABLE IF EXISTS `transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer` (
  `transfer_id` int NOT NULL AUTO_INCREMENT,
  `transfer` varchar(45) NOT NULL,
  `price_1_km` int NOT NULL,
  PRIMARY KEY (`transfer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
INSERT INTO `transfer` VALUES (1,'shuttle',1),(2,'taxi',3),(3,'vip_car',7),(4,'helicopter',50);
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `user_name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `tel` varchar(45) NOT NULL,
  `passport_number` int NOT NULL,
  `country` varchar(45) DEFAULT NULL,
  `comments` varchar(45) DEFAULT NULL,
  `vip_status` tinyint DEFAULT NULL,
  `nongrata_status` tinyint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_admin_roles1_idx` (`role_id`),
  CONSTRAINT `fk_admin_roles10` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'first','$2a$10$8DSXR9ABCBknjOpD98kwQ.tfHjn/oqkYUGD3rOd8eUfTlJzv/v9QK','John','Red','7722lungo@gmail.com','3452211',55555,'US',' ',1,0),(2,1,'second','$2a$10$Xii6qlu/D31yN0W8zc.M0ujwWAUfPNUzbdhcHlN3uOcXydhskHoBu','Jim','Blue','7722lungo@gmail.com','448866',22222,'GB','',0,0),(3,2,'third','$2a$10$7btXYHJjcv3oMYODFSNB3Osxbs/.O19Wxpa0/n5Z8Oamjwvuqxg4i','Billy','Bonus','7722lungo@gmail.com','225522',99999,'FR',' ',0,0),(4,1,'fourth','$2a$10$bC.P0YvpzjHpMdCtN1IQ.O67sjI/8DRCCWPd.QBFo9Q748GVy9zim','George','Brown','7722lungo@gmail.com','223322',12342,'D',' Super client',1,0),(5,1,'fifth','$2a$10$oYIjam.mquzONwv5YOTamO0JuMWbsoH5KIyZGbogiW.KskrmVEoBq','Jiovanni','Capellini','7722Lungo@gmail.com','775577',7788990,'IT',' ',0,0),(6,1,'bad','$2a$10$CAH1VGhKxtlYw0aXeIC8iODR2rAZJOfdb9scDDKE7m4XdZqEHv5ke','Bad','Guy','bad@guy.no','112211',343234,'RM','',0,1),(18,1,'seventh','777','Bobby','Texaso','7722lungo@gmail.com','8878855',9000000,'FR','errr vvv.',0,0),(19,1,'eight','888','Иван','Петров','7722lungo@gmail.com','8878866',3434334,'RU',' ',0,0),(33,1,'peter','$2a$10$2U0BglLMWRWvGq4Dyp4phupIKLMJyjYOORRFG/3a8lTrywwYi5iay','Peter','Goetz','12@12.de','45634443',6677889,'Germany','',1,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'hotelappdb'
--

--
-- Dumping routines for database 'hotelappdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-27  0:47:27
