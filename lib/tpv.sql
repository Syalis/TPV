-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: tpv
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cliente` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(15) NOT NULL,
  `name` varchar(45) NOT NULL,
  `firstSurname` varchar(45) DEFAULT NULL,
  `secondSurname` varchar(45) DEFAULT NULL,
  `address` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'12345678A','Paco','García','García','C/ Málaga 38','660011223'),(2,'770022553Z','Borja','Montes','Gutierrez','C/ Murcia','852643298');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_factura`
--

DROP TABLE IF EXISTS `detalle_factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `detalle_factura` (
  `codeBill` int(100) NOT NULL,
  `codeProduct` int(100) NOT NULL,
  `total` int(100) NOT NULL,
  `subTotal` int(100) NOT NULL,
  PRIMARY KEY (`codeBill`),
  KEY `fk_idProducto_idx` (`codeProduct`),
  CONSTRAINT `fk_idFactura` FOREIGN KEY (`codeBill`) REFERENCES `factura` (`codeBill`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_factura`
--

LOCK TABLES `detalle_factura` WRITE;
/*!40000 ALTER TABLE `detalle_factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `factura` (
  `codeBill` int(11) NOT NULL,
  `dateBill` varchar(45) DEFAULT NULL,
  `idClient` int(100) DEFAULT NULL,
  `totalBill` int(100) DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `totalAPay` int(100) DEFAULT NULL,
  PRIMARY KEY (`codeBill`),
  KEY `fk_IdClienteFactura_idx` (`idClient`),
  CONSTRAINT `fk_IdClienteFactura` FOREIGN KEY (`idClient`) REFERENCES `cliente` (`idClient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (1,'17/05/19',NULL,NULL,0.9,16,NULL),(2,'17/05/19',NULL,NULL,0.8,16,NULL),(3,'17/05/19',NULL,NULL,0.7,16,NULL),(4,'17/05/19',NULL,NULL,0.6,16,NULL),(5,'17/05/19',NULL,NULL,0.5,16,NULL),(6,'17/05/19',NULL,NULL,0.4,16,NULL),(7,'17/05/19',NULL,NULL,0.3,16,NULL),(8,'17/05/19',NULL,NULL,0.2,16,NULL),(9,'17/05/19',NULL,NULL,0.1,16,NULL);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `producto` (
  `codeProduct` int(11) NOT NULL AUTO_INCREMENT,
  `nameProduct` varchar(45) NOT NULL,
  `descriptionProduct` varchar(45) NOT NULL,
  `priceProduct` float NOT NULL,
  `stockProduct` int(100) NOT NULL,
  PRIMARY KEY (`codeProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'CAFÉ SOLO','Café solo solo',2,100),(2,'CAFÉ LARGO','Café largo',1.9,100),(3,'CAFÉ SEMI LARGO','Cafe semi largo',1.8,100),(4,'CAFÉ SOLO CORTO','Café solo corto',1.7,100),(5,'CAFÉ MITAD','Café mitad',1.6,100),(6,'CAFÉ ENTRE CORTO','Café entre corto',1.5,100),(7,'CAFÉ CORTO','Café corto',1.4,100),(8,'CAFÉ SOMBRA','Café sombra',1.3,100),(9,'CAFÉ NUBE','Café nube',1.2,100);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vendedor` (
  `idSeller` int(11) NOT NULL AUTO_INCREMENT,
  `nameSeller` varchar(45) NOT NULL,
  `firstSurname` varchar(45) NOT NULL,
  `secondSurname` varchar(45) NOT NULL,
  PRIMARY KEY (`idSeller`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (1,'Juan','Gómez','García');
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-22 21:47:57
