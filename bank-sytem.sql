-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: banking_db
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `accno` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `amount` double NOT NULL,
  `fee` double NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,'200','withdrawal',10,10,'2024-05-19 17:24:54'),(2,'2','transfer out',100,0,'2024-05-19 17:25:59'),(3,'200','transfer in',100,0,'2024-05-19 17:25:59'),(4,'666','Deposit',100,0,'2024-05-19 17:36:03'),(5,'666','Withdrawal',200,0,'2024-05-19 17:36:17'),(6,'888','Deposit',155,0,'2024-05-19 17:42:51'),(7,'888','Withdrawal',155,0,'2024-05-19 17:43:06'),(8,'888','Transfer to 666',1400,0,'2024-05-19 17:43:20'),(9,'666','Transfer from 888',1400,0,'2024-05-19 17:43:20'),(10,'000','Deposit',65.55,0,'2024-05-19 17:55:37'),(11,'000','Withdrawal',16,0,'2024-05-19 17:56:03'),(12,'2','Transfer to 000',29999,0,'2024-05-19 17:56:20'),(13,'000','Transfer from 2',29999,0,'2024-05-19 17:56:21'),(14,'111','Deposit',50,0,'2024-05-19 18:10:57'),(15,'333','Deposit',55,0,'2024-05-19 18:16:41'),(16,'333','Deposit',20,0,'2024-05-19 18:16:56'),(17,'333','Transfer to 000',10,0,'2024-05-19 18:17:17'),(18,'000','Transfer from 333',10,0,'2024-05-19 18:17:17'),(19,'899','Withdrawal',100,0,'2024-05-19 18:35:53'),(20,'899','Deposit',999,0,'2024-05-19 18:36:20'),(21,'899','Transfer to 000',89,0,'2024-05-19 18:36:38'),(22,'000','Transfer from 899',89,0,'2024-05-19 18:36:38');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `accno` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `acc_type` varchar(50) NOT NULL,
  `balance` double NOT NULL,
  `bankName` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (4,'2','qefsernimani','1005',10901,'procreditbank'),(203,'200','arlindnimani','mastercard',180,'procreditbank'),(204,'500','jjjjk','jj',10,'kkk'),(205,'666','babaarlind','debit',2000,'reiffeisen'),(206,'888','arlindbaba','debit',6600,'bkt'),(207,'000','xhylinimani','mastercard',30998.05,'nlb'),(208,'111','hglg','jjj',5555,'bkt'),(209,'333','hala','master',220,'bankaekeqe'),(210,'899','arlindbabbaba','baba',910,'arlindbabba');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-19 22:37:20
