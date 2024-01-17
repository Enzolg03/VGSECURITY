CREATE DATABASE  IF NOT EXISTS `visionsecurity` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `visionsecurity`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: visionsecurity
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `tb_cab_boleta`
--

DROP TABLE IF EXISTS `tb_cab_boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cab_boleta` (
  `num_bol` char(5) NOT NULL,
  `fch_bol` date DEFAULT NULL,
  `cod_cliente` int NOT NULL,
  `cod_vendedor` int NOT NULL,
  `total_bol` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`num_bol`),
  KEY `cod_cliente` (`cod_cliente`),
  KEY `tb_cab_boleta_ibfk_2` (`cod_vendedor`),
  CONSTRAINT `tb_cab_boleta_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `tb_usuario` (`codigo`),
  CONSTRAINT `tb_cab_boleta_ibfk_2` FOREIGN KEY (`cod_vendedor`) REFERENCES `tb_usuario` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cab_boleta`
--

LOCK TABLES `tb_cab_boleta` WRITE;
/*!40000 ALTER TABLE `tb_cab_boleta` DISABLE KEYS */;
INSERT INTO `tb_cab_boleta` VALUES ('B0001','2020-09-15',8,1,1000.00),('B0002','2022-11-28',8,1,2000.00),('B0003','2022-11-28',8,1,1490.00),('B0004','2022-11-28',8,1,1200.00),('B0005','2022-11-28',8,1,0.00),('B0006','2022-11-28',8,1,5000.00),('B0007','2022-11-28',8,1,3096.00),('B0008','2022-11-28',8,1,500.00),('B0009','2022-11-28',8,1,500.00),('B0010','2022-11-28',8,1,1419.00),('B0011','2022-11-28',9,1,254.00);
/*!40000 ALTER TABLE `tb_cab_boleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cotizar`
--

DROP TABLE IF EXISTS `tb_cotizar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cotizar` (
  `Nro_cot` int NOT NULL AUTO_INCREMENT,
  `Fecha` datetime NOT NULL,
  `Año` datetime NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Apellido` varchar(100) NOT NULL,
  `Tipo_doc` varchar(100) NOT NULL,
  `Nro_doc` int NOT NULL,
  `Cod_prod` int NOT NULL,
  `Nom_prod` varchar(100) NOT NULL,
  `Precio` double NOT NULL,
  `Stock` int NOT NULL,
  `Cantidad` int NOT NULL,
  `Total` double NOT NULL,
  PRIMARY KEY (`Nro_cot`),
  UNIQUE KEY `idnew_table_UNIQUE` (`Nro_cot`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cotizar`
--

LOCK TABLES `tb_cotizar` WRITE;
/*!40000 ALTER TABLE `tb_cotizar` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_cotizar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_det_boleta`
--

DROP TABLE IF EXISTS `tb_det_boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_det_boleta` (
  `num_bol` char(5) NOT NULL,
  `idprod` int NOT NULL,
  `cantidad` int DEFAULT NULL,
  `preciovta` decimal(8,2) DEFAULT NULL,
  `importe` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`num_bol`,`idprod`),
  KEY `idprod` (`idprod`),
  CONSTRAINT `tb_det_boleta_ibfk_1` FOREIGN KEY (`num_bol`) REFERENCES `tb_cab_boleta` (`num_bol`),
  CONSTRAINT `tb_det_boleta_ibfk_2` FOREIGN KEY (`idprod`) REFERENCES `tb_producto` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_det_boleta`
--

LOCK TABLES `tb_det_boleta` WRITE;
/*!40000 ALTER TABLE `tb_det_boleta` DISABLE KEYS */;
INSERT INTO `tb_det_boleta` VALUES ('B0007',2,4,149.00,596.00),('B0007',3,5,500.00,2500.00),('B0008',3,1,500.00,500.00),('B0009',3,1,500.00,500.00),('B0010',2,1,149.00,149.00),('B0010',4,10,127.00,1270.00),('B0011',4,2,127.00,254.00);
/*!40000 ALTER TABLE `tb_det_boleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_producto`
--

DROP TABLE IF EXISTS `tb_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_producto` (
  `Codigo` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) DEFAULT NULL,
  `Marca` varchar(100) DEFAULT NULL,
  `Descripcion` varchar(100) DEFAULT NULL,
  `precio` decimal(8,2) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  UNIQUE KEY `idtb_producto_UNIQUE` (`Codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_producto`
--

LOCK TABLES `tb_producto` WRITE;
/*!40000 ALTER TABLE `tb_producto` DISABLE KEYS */;
INSERT INTO `tb_producto` VALUES (1,'2MP DOMO COLOR VU LENTE 2.8MM','HikVision','Camara Domo',300.00,100),(2,'DOMO PTZ 25X 1080P IR 100M','HikVision','Camara Domo Fijo',149.00,100),(3,'TUBO 2MP COLOR VU LENTE 2.8MM','HikVision','Camara Tubo',500.00,150),(4,'DOMO IP 2MP. LENTE 2.8MM','Hikvision','Camara Domo IP Fijo',127.00,200);
/*!40000 ALTER TABLE `tb_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tipo`
--

DROP TABLE IF EXISTS `tb_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_tipo` (
  `idtipo` int NOT NULL,
  `des_tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tipo`
--

LOCK TABLES `tb_tipo` WRITE;
/*!40000 ALTER TABLE `tb_tipo` DISABLE KEYS */;
INSERT INTO `tb_tipo` VALUES (1,'Administrador'),(2,'Vendedor'),(3,'Cliente');
/*!40000 ALTER TABLE `tb_tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `usuario` char(4) NOT NULL,
  `nombre` varchar(15) DEFAULT NULL,
  `apellido` varchar(25) DEFAULT NULL,
  `dni` varchar(15) DEFAULT NULL,
  `contraseña` char(5) DEFAULT NULL,
  `fnaReg` date DEFAULT NULL,
  `tipo` int DEFAULT '2',
  `estado` int DEFAULT '1',
  PRIMARY KEY (`codigo`),
  KEY `tipo` (`tipo`),
  CONSTRAINT `tb_usuario_ibfk_1` FOREIGN KEY (`tipo`) REFERENCES `tb_tipo` (`idtipo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (1,'U001','David','Flores','65498721','10001','2022-11-28',2,1),(8,'U008','Daniela','Flores','65495521','10008','2022-11-28',3,1),(9,'U009','Alesia','Quispe','12295521','10009','2022-11-28',3,1),(10,'U010','Lucia','De la cruz','55495521','10010','2022-11-28',3,1),(11,'U011','Lucho','Gonzalo','25585521','10011','2022-11-28',3,1),(12,'U012','Enzo','Limay','2340173','10012','2022-11-28',1,1);
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'visionsecurity'
--

--
-- Dumping routines for database 'visionsecurity'
--
/*!50003 DROP PROCEDURE IF EXISTS `usp_buscarProducto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_buscarProducto`(xnombProd varchar(45))
select * from tb_producto where Descripcion like concat (xnombProd, '%' ) ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_listarUsuarioTipo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listarUsuarioTipo`(xtipo int)
begin
select u.codigo,concat(u.nombre,u.apellido) as 'Nombre Completos', t.des_tipo as 'Descripcion'
from tb_usuario u inner join tb_tipo t
on u.tipo = t.idtipo 
where u.tipo = xtipo;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_tipoUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_tipoUsuario`(xtipo int)
begin
select u.codigo,u.nombre,u.apellido,u.dni, t.des_tipo as 'Descripcion'
from tb_usuario u inner join tb_tipo t
on u.tipo = t.idtipo
where u.tipo = xtipo;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_validarAcesso` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_validarAcesso`(usr char(4),clav char(5))
begin
select * from tb_usuario where usuario = usr and contraseña=clav;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-29  1:54:49
