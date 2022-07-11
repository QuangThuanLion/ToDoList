-- MySQL dump 10.13  Distrib 8.0.28, for macos11 (x86_64)
--
-- Host: localhost    Database: todolist
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) NOT NULL,
  `product_price` int NOT NULL,
  `product_description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Kia Morning',7656,'Description Kia Morning'),(2,'HuynDai Brand',444,'Description HuynDai'),(3,'Yamaha Brand',6666,'Description Yamaha'),(5,'Kia Morning',5555,NULL),(7,'Kia Morning',2333,'Description Kia Morning'),(8,'HuynDai',444,'Description HuynDai'),(9,'Yamaha',6666,'Description Yamaha'),(10,'Lamborigi',9999,'Description Lamborigi'),(11,'Kia Morning',2333,'Description Kia Morning'),(12,'HuynDai',444,'Description HuynDai'),(13,'Yamaha',6666,'Description Yamaha'),(14,'Lamborigi',9999,'Description Lamborigi');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `works`
--

DROP TABLE IF EXISTS `works`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `works` (
  `id` int NOT NULL AUTO_INCREMENT,
  `work_name` varchar(100) NOT NULL,
  `starting_date` datetime(6) NOT NULL,
  `ending_date` datetime(6) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `works`
--

LOCK TABLES `works` WRITE;
/*!40000 ALTER TABLE `works` DISABLE KEYS */;
INSERT INTO `works` VALUES (1,'Go to school','2022-06-06 15:58:11.741000','2022-06-16 15:58:11.741000','COMPLETE'),(2,'Eating breafast','2022-01-14 10:48:25.000000','2022-01-20 10:48:25.000000','COMPLETE'),(4,'Start working job 1','2022-01-14 10:48:25.000000','2022-01-20 10:48:25.000000','PLANNING'),(5,'Start working job 2','2022-01-14 10:48:25.000000','2022-01-20 10:48:25.000000','PLANNING'),(6,'working in the office','2022-07-11 11:18:30.890000','2022-07-11 11:18:30.890000','PLANNING'),(7,'Go to school','2022-07-11 11:18:31.182000','2022-07-21 11:18:31.182000','COMPLETE'),(8,'plaing football','2022-07-11 11:20:06.180000','2022-07-11 11:20:06.180000','PLANNING'),(9,'Go to school','2022-07-11 11:20:06.345000','2022-07-21 11:20:06.345000','COMPLETE'),(10,'Start working G8','2022-01-14 10:48:25.000000','2022-01-20 10:48:25.000000','PLANNING');
/*!40000 ALTER TABLE `works` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-11 17:38:12
