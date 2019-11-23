-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: bibliotecapp
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `Articulo`
--

DROP TABLE IF EXISTS `Articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Articulo` (
  `Ar_id` int(10) NOT NULL AUTO_INCREMENT,
  `Ar_titulo` varchar(255) NOT NULL,
  `Ar_autor` varchar(255) DEFAULT NULL,
  `Ar_identificador` varchar(255) NOT NULL,
  `Ar_estado` bit(1) NOT NULL,
  `fk_tema` int(10) NOT NULL,
  `fk_tipo` int(10) NOT NULL,
  `Ar_plataforma` varchar(255) DEFAULT NULL,
  `Ar_qualidad` varchar(255) DEFAULT NULL,
  `Ar_numeroPistas` int(10) DEFAULT NULL,
  `Ar_numeroPaginas` int(10) DEFAULT NULL,
  `Ar_archivo` bit(1) NOT NULL,
  PRIMARY KEY (`Ar_id`),
  KEY `FKArticulo111904` (`fk_tipo`),
  KEY `FKArticulo238970` (`fk_tema`),
  CONSTRAINT `FKArticulo111904` FOREIGN KEY (`fk_tipo`) REFERENCES `tipo` (`Ti_id`),
  CONSTRAINT `FKArticulo238970` FOREIGN KEY (`fk_tema`) REFERENCES `tema` (`Te_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Articulo`
--

LOCK TABLES `Articulo` WRITE;
/*!40000 ALTER TABLE `Articulo` DISABLE KEYS */;
INSERT INTO `Articulo` VALUES (1,'Need For Speed','Ubisoft','VJ0001',_binary '\0',17,4,'PS4',NULL,NULL,NULL,_binary '\0'),(2,'Ce monde est cruel','Vald','CD0001',_binary '\0',12,2,NULL,NULL,13,NULL,_binary '\0'),(3,'One More Light','Linkin Park','CD0002',_binary '',7,2,NULL,NULL,15,NULL,_binary '\0'),(4,'L\'Assomoir','Émile Zola','L0001',_binary '',2,1,NULL,NULL,NULL,420,_binary '\0'),(5,'League of Legends','Riot Games','VJ0002',_binary '',18,4,'PC',NULL,NULL,NULL,_binary '\0'),(6,'Interstellar','Christopher Nolan','DVD0001',_binary '',2,3,NULL,'Full HD',NULL,NULL,_binary '\0'),(7,'Lucy','Luc Besson','DVD0002',_binary '',1,3,NULL,'Full HD',NULL,NULL,_binary '\0');
/*!40000 ALTER TABLE `Articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ArticuloToCliente`
--

DROP TABLE IF EXISTS `ArticuloToCliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ArticuloToCliente` (
  `At_id` int(10) NOT NULL AUTO_INCREMENT,
  `At_fechaPrestamo` varchar(30) NOT NULL,
  `At_fechaPlanificadaDevolucion` varchar(30) NOT NULL,
  `At_fechaRealDevolucion` varchar(30) DEFAULT NULL,
  `fk_cliente` int(10) NOT NULL,
  `fk_articulo` int(10) NOT NULL,
  `At_archivo` bit(1) NOT NULL,
  PRIMARY KEY (`At_id`),
  KEY `FKArticuloTo234304` (`fk_articulo`),
  KEY `FKArticuloTo960803` (`fk_cliente`),
  CONSTRAINT `FKArticuloTo234304` FOREIGN KEY (`fk_articulo`) REFERENCES `articulo` (`Ar_id`),
  CONSTRAINT `FKArticuloTo960803` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`Cl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ArticuloToCliente`
--

LOCK TABLES `ArticuloToCliente` WRITE;
/*!40000 ALTER TABLE `ArticuloToCliente` DISABLE KEYS */;
INSERT INTO `ArticuloToCliente` VALUES (6,'2019-11-14','2019-11-30','2019-11-18',1,4,_binary '\0'),(7,'18-11-2019','27-11-2019',NULL,2,1,_binary '\0'),(8,'2019-11-20','2020-01-10',NULL,1,2,_binary '\0');
/*!40000 ALTER TABLE `ArticuloToCliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cliente` (
  `Cl_id` int(10) NOT NULL AUTO_INCREMENT,
  `Cl_nombre` varchar(255) NOT NULL,
  `Cl_apellido` varchar(255) NOT NULL,
  `Cl_telefono` int(20) NOT NULL,
  `Cl_direccion` varchar(255) NOT NULL,
  `Cl_email` varchar(255) NOT NULL,
  `Cl_inicioSuscripcion` varchar(30) NOT NULL,
  `Cl_finSuscripcion` varchar(30) NOT NULL,
  `Cl_archivo` bit(1) NOT NULL,
  PRIMARY KEY (`Cl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` VALUES (1,'Thomas','SINAN',630139975,'18 rue du Calvaire, Saint-Andre-de-la-Marche, 49450','sinan.thomas@opendeusto.es','10-11-2019','10-03-2020',_binary '\0'),(2,'Arthur','ALEIXO',634567890,'18 rue de Nice, Nice','arthur.aleixo@opendeusto.es','10-12-2020','12-04-2020',_binary '\0');
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tema`
--

DROP TABLE IF EXISTS `Tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tema` (
  `Te_id` int(10) NOT NULL AUTO_INCREMENT,
  `Te_mensaje` varchar(255) NOT NULL,
  PRIMARY KEY (`Te_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tema`
--

LOCK TABLES `Tema` WRITE;
/*!40000 ALTER TABLE `Tema` DISABLE KEYS */;
INSERT INTO `Tema` VALUES (1,'Cómico'),(2,'Dramático'),(3,'Historia real'),(4,'Historia'),(5,'Bibliografía'),(6,'Niño'),(7,'Rock'),(8,'Pop'),(9,'Clàsico'),(10,'Funk'),(11,'Jazz'),(12,'Rap'),(13,'Romance'),(14,'Policiaca'),(15,'Thriller'),(16,'Deportivo'),(17,'Automóvil'),(18,'Aventura');
/*!40000 ALTER TABLE `Tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tipo`
--

DROP TABLE IF EXISTS `Tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tipo` (
  `Ti_id` int(10) NOT NULL AUTO_INCREMENT,
  `Ti_mensaje` varchar(255) NOT NULL,
  PRIMARY KEY (`Ti_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tipo`
--

LOCK TABLES `Tipo` WRITE;
/*!40000 ALTER TABLE `Tipo` DISABLE KEYS */;
INSERT INTO `Tipo` VALUES (1,'Libro'),(2,'CD'),(3,'DVD'),(4,'Video juego');
/*!40000 ALTER TABLE `Tipo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-23 16:39:18
