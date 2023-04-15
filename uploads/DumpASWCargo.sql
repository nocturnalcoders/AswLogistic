CREATE DATABASE  IF NOT EXISTS `aswcargo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `aswcargo`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: aswcargo
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Dumping data for table `card_payment`
--

LOCK TABLES `card_payment` WRITE;
/*!40000 ALTER TABLE `card_payment` DISABLE KEYS */;
INSERT INTO `card_payment` VALUES ('9cfe72b6-14d5-4e23-99f8-f1fd6792a7f5','100000','BCA','1010010101','flaz');
/*!40000 ALTER TABLE `card_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `card_payment_type`
--

LOCK TABLES `card_payment_type` WRITE;
/*!40000 ALTER TABLE `card_payment_type` DISABLE KEYS */;
INSERT INTO `card_payment_type` VALUES ('03447a88-583f-4d32-bac4-a697a757d6f7','flaz');
/*!40000 ALTER TABLE `card_payment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `claim`
--

LOCK TABLES `claim` WRITE;
/*!40000 ALTER TABLE `claim` DISABLE KEYS */;
INSERT INTO `claim` VALUES ('6b92a8cc-97ac-4e64-b040-391cc0907e79','am','cl','CONT-123',NULL,'pr','q','r','acaca','A ',NULL),('cb95e6a2-c611-405a-8b44-0c738da3e8e4','amount','claim','CONT-123','Transcript.png','price','qty','rate','Rec-1','remakrss ',NULL);
/*!40000 ALTER TABLE `claim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('9f75e7fe-7583-47b2-941e-acb6b042ddc3','10101010','Jalan Mangga Besar IV R. No.18','Christianto Lie','marking ','DKI Jakarta');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES ('a4d7824d-9015-4019-89cb-99af99a4878d','cipto','B 123 BE');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `extra_fee`
--

LOCK TABLES `extra_fee` WRITE;
/*!40000 ALTER TABLE `extra_fee` DISABLE KEYS */;
INSERT INTO `extra_fee` VALUES ('cda58cec-15cd-43fe-8aa9-b8a11cef3ea4','Uang Container','100000','Notes');
/*!40000 ALTER TABLE `extra_fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES ('89df4c5f-0178-45bd-bb3b-fdcacc3d0b52',NULL,'9f75e7fe-7583-47b2-941e-acb6b042ddc3',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `invoice_detail`
--

LOCK TABLES `invoice_detail` WRITE;
/*!40000 ALTER TABLE `invoice_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES ('0b806654-8e01-4016-a8b9-eaca8fd65dc9','ACCO','2023-02-10 00:00:00.000000','Screenshot (7).png',_binary '89df4c5f-0178-45bd-bb3b-fdcacc3d0b52\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','101010','NOTE','STATUS','TYPE',NULL),('3fa7a6ed-c013-4eb8-a2e5-5f4db1e0a0bb','ACC','2023-02-10 00:00:00.000000','1678304230764.jpeg',NULL,'Nominal ajah','Note','status','TYPE',NULL);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES ('1','READ_USERS');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `proof_of_delivery`
--

LOCK TABLES `proof_of_delivery` WRITE;
/*!40000 ALTER TABLE `proof_of_delivery` DISABLE KEYS */;
INSERT INTO `proof_of_delivery` VALUES ('296c5501-ce8b-4fa2-a2ee-47908dfafdc3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('a9d9daeb-ded7-440a-a069-340517c12e99','fa59a629-08cb-4315-aeb5-8f45b53b1835','2029-02-01 00:00:00.000000','air','a4d7824d-9015-4019-89cb-99af99a4878d','a4d7824d-9015-4019-89cb-99af99a4878d',NULL,'12309319','2023-02-20 00:00:00.000000','recv-1',NULL,NULL),('dcd65097-268d-4fb5-bc55-ec91a6949e4e','',NULL,'','','',NULL,'',NULL,'',NULL,NULL);
/*!40000 ALTER TABLE `proof_of_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `proof_of_delivery_detail`
--

LOCK TABLES `proof_of_delivery_detail` WRITE;
/*!40000 ALTER TABLE `proof_of_delivery_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `proof_of_delivery_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `quality_control`
--

LOCK TABLES `quality_control` WRITE;
/*!40000 ALTER TABLE `quality_control` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `quality_control_detail`
--

LOCK TABLES `quality_control_detail` WRITE;
/*!40000 ALTER TABLE `quality_control_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_control_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('1','ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles_privileges`
--

LOCK TABLES `roles_privileges` WRITE;
/*!40000 ALTER TABLE `roles_privileges` DISABLE KEYS */;
INSERT INTO `roles_privileges` VALUES ('1','1');
/*!40000 ALTER TABLE `roles_privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `stuffing`
--

LOCK TABLES `stuffing` WRITE;
/*!40000 ALTER TABLE `stuffing` DISABLE KEYS */;
INSERT INTO `stuffing` VALUES ('fa59a629-08cb-4315-aeb5-8f45b53b1835','2023-03-27 07:27:03.000000','CONT-123','CONTAINER123','2023-03-27 07:27:20.000000','2023-03-27 07:27:22.000000','fa59a629-08cb-4315-aeb5-8f45b53b1839',NULL,'RECV1','DONE','2023-03-27 07:27:42.000000','SUMMARU','fa59a629-08cb-4315-aeb5-8f45b53b1830');
/*!40000 ALTER TABLE `stuffing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('a72fd9fa-b675-469e-a17e-01ab469533b7',NULL,'2023-03-22 15:38:25.699613','admin@aswlogistic.com','Admin','ASW Cargo','$2a$10$El2VfOBD9oVN4BP5E3V0Xu4jauEfaLRqb.o6xQ0s7bD/L5G78eWKy');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES ('a72fd9fa-b675-469e-a17e-01ab469533b7','1');
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-15  7:59:49
