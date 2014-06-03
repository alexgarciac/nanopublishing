-- MySQL dump 10.13  Distrib 5.6.17, for osx10.7 (i386)
--
-- Host: localhost    Database: nanotate
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `annotation`
--

DROP TABLE IF EXISTS `annotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `annotation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `original_text` TEXT,
  `tags` varchar(200) DEFAULT NULL,
  `document` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `creation` datetime DEFAULT NULL,
  `completed` datetime DEFAULT NULL,
  `doi` varchar(40) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `facebook_posts` TEXT,
  `json_value` TEXT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annotation`
--

LOCK TABLES `annotation` WRITE;
/*!40000 ALTER TABLE `annotation` DISABLE KEYS */;
INSERT INTO `annotation` VALUES (1,'Stress may also induce rumination by undermining self-\nregulation, or the capacity to engage in self-control over one’s\nbehavior (Baumeister, Gailliot, DeWall, & Oaten, 2006; In-\nzlicht, McKay, & Aronson, 2006). Limited regulatory abilities\nmay impair an individual’s ability to engage in problem solving\nor active coping and increase the likelihood of engagement in\nrumination. Indeed, various studies have shown that active\ncoping strategies such as problem solving are negatively cor-\nrelated with rumination (see review by Nolen-Hoeksema,\nWisco, & Lyubomirsky, 2008). A variety of other cognitive\nmechanisms might also increase the likelihood of rumination\nfollowing stressful life events, including attention to negative\nthoughts and feelings, autobiographical memory for previous\nnegative events, and negative self-schema activation (Scher,\nIngram, & Segal, 2005; Segal & Ingram, 1994).','','4d702da3-e8c5-4402-9db6-c5cff6120f1d','WORKING','2014-05-29 17:04:26',NULL,'http://dx.doi.org/10.1037/a0031994','admin',NULL,NULL);
/*!40000 ALTER TABLE `annotation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `uuid` varchar(255) NOT NULL,
  `doi` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `full_citation` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES ('4d702da3-e8c5-4402-9db6-c5cff6120f1d','http://dx.doi.org/10.1037/a0031994','Rumination as a mechanism linking stressful life events to symptoms of depression and anxiety: Longitudinal evidence in early adolescents and adults.','Louisa C. Michl, Katie A. McLaughlin, Kathrine Shepherd, Susan Nolen-Hoeksema, 2013, \'Rumination as a mechanism linking stressful life events to symptoms of depression and anxiety: Longitudinal evidence in early adolescents and adults.\', <i>Journal of Abn',2013),('8ba48ec2-90a8-4980-acef-e9d587871899','http://dx.doi.org/10.1037/a0033656','Can???t get it out of my mind: Employee rumination after customer mistreatment and negative mood in the next morning.','Mo Wang, Songqi Liu, Hui Liao, Yaping Gong, John Kammeyer-Mueller, Junqi Shi, 2013, \'Can???t get it out of my mind: Employee rumination after customer mistreatment and negative mood in the next morning.\', <i>Journal of Applied Psychology</i>, vol. 98, no.',2013);
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `twitter_username` varchar(255) DEFAULT NULL,
  `facebook_username` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `facebook_token` varchar(255) DEFAULT NULL,
  `twitter_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','password',NULL,NULL,NULL,NULL,NULL,NULL),('Fico89','89Fico10*',NULL,'Fico89','Federico',' GÃ³me',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-29 17:07:16