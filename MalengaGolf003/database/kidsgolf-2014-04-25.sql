-- phpMyAdmin SQL Dump
-- version 3.5.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 25, 2014 at 04:42 AM
-- Server version: 5.5.29
-- PHP Version: 5.4.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kidsgolf`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

CREATE TABLE `administrator` (
  `administratorID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `superUserFlag` int(11) NOT NULL,
  `cellphone` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `pin` varchar(45) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `golfGroupID` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`administratorID`),
  UNIQUE KEY `idxmail` (`golfGroupID`,`email`),
  KEY `ffgg` (`golfGroupID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`administratorID`, `superUserFlag`, `cellphone`, `email`, `pin`, `firstName`, `lastName`, `golfGroupID`) VALUES
(1, 0, NULL, 'aubrey@mlab.co.za', '12345', 'Aubrey', 'Malabie', 1),
(4, 1, '0712193543', 'aubrey.malabie@gmail.com', 'dev', 'Aubrey', 'Malabie', 8),
(5, 1, '0896663232', 'rulani@mlab.co.za', 'dev', 'Jonathan', 'Krebbs', 10);

-- --------------------------------------------------------

--
-- Table structure for table `agegroup`
--

CREATE TABLE `agegroup` (
  `ageGroupID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `golfGroupID` int(10) unsigned NOT NULL,
  `groupName` varchar(50) COLLATE latin1_general_ci DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `numberOfHolesPerRound` int(11) DEFAULT NULL,
  `ageFrom` int(10) unsigned NOT NULL,
  `ageTo` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ageGroupID`),
  UNIQUE KEY `golfGroupID` (`golfGroupID`,`groupName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=15 ;

--
-- Dumping data for table `agegroup`
--

INSERT INTO `agegroup` (`ageGroupID`, `golfGroupID`, `groupName`, `gender`, `numberOfHolesPerRound`, `ageFrom`, `ageTo`) VALUES
(1, 10, 'Boys 5 - 6', 1, 9, 5, 6),
(2, 10, 'Boys 7 - 8', 1, 9, 7, 8),
(3, 10, 'Boys 9 - 10', 1, 9, 9, 10),
(4, 10, 'Boys 11 - 12', 1, 18, 11, 12),
(5, 10, 'Boys 13 - 14', 1, 18, 13, 14),
(6, 10, 'Boys 15 - 16', 1, 18, 15, 16),
(7, 10, 'Boys 17 - 18', 1, 18, 17, 18),
(8, 10, 'Girls 5 - 6', 2, 9, 5, 6),
(9, 10, 'Girls 7 - 8', 2, 9, 7, 8),
(10, 10, 'Girls 9 - 10', 2, 9, 9, 10),
(11, 10, 'Girls 11 - 12', 2, 18, 11, 12),
(12, 10, 'Girls 13 - 14', 2, 18, 13, 14),
(13, 10, 'Girls 15 - 16', 2, 18, 15, 16),
(14, 10, 'Girls 17 - 18', 2, 18, 17, 18);

-- --------------------------------------------------------

--
-- Table structure for table `club`
--

CREATE TABLE `club` (
  `clubID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clubName` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `par` int(11) NOT NULL DEFAULT '72',
  `email` varchar(95) COLLATE latin1_general_ci DEFAULT NULL,
  `telephone` varchar(25) COLLATE latin1_general_ci DEFAULT NULL,
  `latitude` double DEFAULT '0',
  `longitude` double DEFAULT '0',
  `address` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `provinceID` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`clubID`),
  UNIQUE KEY `ix1a` (`provinceID`,`clubName`),
  KEY `fprov` (`provinceID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `club`
--

INSERT INTO `club` (`clubID`, `clubName`, `par`, `email`, `telephone`, `latitude`, `longitude`, `address`, `provinceID`) VALUES
(1, 'Pecanwood Golf & Country Club', 72, 'pecanwood.info@gmail.com', '012 244 8000', 0, 0, 'Route 512, Hartebeestpoort', 2),
(2, 'Bryanston Country Club', 72, 'bryanston.info@gmail.com', '011 677 9800', 0, 0, NULL, 1),
(3, 'Modderfontein Golf Club', 72, 'modder.info@modderfontein.co.za', '086 266 8766', 0, 0, NULL, 1),
(4, 'Pretoria Golf Club', 72, 'vermeulen@pretoriagolf.com', '082 366 8900', 0, 0, NULL, 1),
(5, 'Zwartkops Golf Club', 72, 'zwartkops.info@swartkops.co.za', '071 266 3550', 0, 0, NULL, 1),
(6, 'Country Club Johannesburg', 72, 'ccj_info@ccj.co.za', '011 234 6578', 0, 0, NULL, 1),
(7, 'Kyalami Country Club', 72, 'kyalami@gmail.com', '011 255 3656', 0, 0, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `clubCourse`
--

CREATE TABLE `clubCourse` (
  `clubCourseID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clubID` int(10) unsigned NOT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  `holes` int(10) unsigned NOT NULL DEFAULT '18',
  `par` int(10) unsigned NOT NULL DEFAULT '72',
  `parHole1` int(11) DEFAULT '0',
  `parHole2` int(11) DEFAULT '0',
  `parHole3` int(11) DEFAULT '0',
  `parHole4` int(11) DEFAULT '0',
  `parHole5` int(11) DEFAULT '0',
  `parHole6` int(11) DEFAULT '0',
  `parHole7` int(11) DEFAULT '0',
  `parHole8` int(11) DEFAULT '0',
  `parHole9` int(11) DEFAULT '0',
  `parHole10` int(11) DEFAULT '0',
  `parHole11` int(11) DEFAULT '0',
  `parHole12` int(11) DEFAULT '0',
  `parHole13` int(11) DEFAULT '0',
  `parHole14` int(11) DEFAULT '0',
  `parHole15` int(11) DEFAULT '0',
  `parHole16` int(11) DEFAULT '0',
  `parHole17` int(11) DEFAULT '0',
  `parHole18` int(11) DEFAULT '0',
  PRIMARY KEY (`clubCourseID`),
  KEY `clubID` (`clubID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `clubCourse`
--

INSERT INTO `clubCourse` (`clubCourseID`, `clubID`, `courseName`, `holes`, `par`, `parHole1`, `parHole2`, `parHole3`, `parHole4`, `parHole5`, `parHole6`, `parHole7`, `parHole8`, `parHole9`, `parHole10`, `parHole11`, `parHole12`, `parHole13`, `parHole14`, `parHole15`, `parHole16`, `parHole17`, `parHole18`) VALUES
(1, 6, 'Woodmead Course', 18, 72, 4, 4, 5, 3, 4, 5, 4, 3, 4, 4, 4, 3, 4, 4, 3, 5, 4, 5),
(2, 6, 'Rocklands Course', 18, 72, 4, 4, 4, 3, 5, 4, 3, 5, 4, 4, 3, 5, 4, 4, 5, 4, 3, 4),
(3, 1, 'Pecanwood Estate Course', 18, 72, 4, 4, 3, 4, 5, 4, 5, 3, 4, 5, 4, 5, 3, 4, 4, 4, 3, 4),
(4, 2, 'Bryanston Championship Course', 18, 72, 4, 4, 4, 3, 5, 4, 3, 5, 4, 4, 5, 3, 4, 4, 5, 4, 4, 3),
(5, 3, 'Modderfontein Course', 18, 72, 4, 4, 4, 3, 5, 4, 4, 3, 5, 4, 4, 4, 5, 3, 4, 4, 3, 5),
(6, 4, 'Pretoria West Course', 18, 72, 5, 4, 4, 3, 4, 4, 3, 4, 5, 4, 4, 4, 3, 5, 4, 5, 4, 3),
(7, 5, 'Zwartkop Hayes Course', 18, 72, 3, 4, 4, 5, 4, 4, 3, 4, 5, 4, 3, 5, 4, 4, 5, 3, 4, 4),
(8, 7, 'Kyalami Course', 18, 72, 4, 4, 4, 4, 4, 3, 5, 3, 5, 4, 4, 5, 4, 3, 5, 4, 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `countryID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `countryName` varchar(100) NOT NULL,
  `latitude` double DEFAULT '0',
  `longitude` double DEFAULT '0',
  `countryCode` varchar(10) NOT NULL,
  PRIMARY KEY (`countryID`),
  UNIQUE KEY `ctryIdx` (`countryName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`countryID`, `countryName`, `latitude`, `longitude`, `countryCode`) VALUES
(1, 'South Africa', 0, 0, 'ZA'),
(2, 'Namibia', 0, 0, ''),
(3, 'Botswana', 0, 0, ''),
(4, 'Zimbabwe', 0, 0, ''),
(5, 'Swaziland', 0, 0, ''),
(6, 'United States', 0, 0, ''),
(7, 'United Kingdom', 0, 0, ''),
(8, 'Malaysia', 0, 0, ''),
(9, 'Kenya', 0, 0, ''),
(10, 'Zambia', 0, 0, ''),
(12, 'Mozambique', 0, 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `errorStore`
--

CREATE TABLE `errorStore` (
  `errorStoreID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `statusCode` int(11) NOT NULL,
  `message` text NOT NULL,
  `dateOccured` datetime NOT NULL,
  `origin` varchar(255) NOT NULL,
  PRIMARY KEY (`errorStoreID`),
  KEY `dateOccured` (`dateOccured`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=489 ;

--
-- Dumping data for table `errorStore`
--

INSERT INTO `errorStore` (`errorStoreID`, `statusCode`, `message`, `dateOccured`, `origin`) VALUES
(1, 133, 'MGGolf temporary files cleaned up', '2014-04-09 00:24:01', 'HouseKeeper'),
(2, 133, 'MGGolf temporary files cleaned up', '2014-04-09 00:52:00', 'HouseKeeper'),
(3, 133, 'MGGolf temporary files cleaned up', '2014-04-09 01:15:05', 'HouseKeeper'),
(4, 8, 'Database related error\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.CountryDTO\nMethod: <init>\nLine Number: 25\n', '2014-04-09 03:49:13', 'GolfAdminServlet'),
(5, 133, 'MGGolf temporary files cleaned up', '2014-04-09 03:57:09', 'HouseKeeper'),
(6, 133, 'MGGolf temporary files cleaned up', '2014-04-09 08:43:29', 'HouseKeeper'),
(7, 133, 'MGGolf temporary files cleaned up', '2014-04-09 12:46:53', 'HouseKeeper'),
(8, 133, 'MGGolf temporary files cleaned up', '2014-04-09 15:00:09', 'HouseKeeper'),
(9, 8, 'Database related error\nNamedQuery of name: GolfGroup.findByEmail not found.\n\njava.lang.IllegalArgumentException: NamedQuery of name: GolfGroup.findByEmail not found.\n\nClass: org.eclipse.persistence.internal.jpa.QueryImpl\nMethod: getDatabaseQueryInternal\nLine Number: 350\n', '2014-04-09 19:04:41', 'GolfAdminServlet'),
(10, 8, 'Database related error\nNamedQuery of name: GolfGroup.findByEmail not found.\n\njava.lang.IllegalArgumentException: NamedQuery of name: GolfGroup.findByEmail not found.\n\nClass: org.eclipse.persistence.internal.jpa.QueryImpl\nMethod: getDatabaseQueryInternal\nLine Number: 350\n', '2014-04-09 19:07:38', 'GolfAdminServlet'),
(11, 8, 'Database related error\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addGolfGroupAdmin\nLine Number: 1101\n', '2014-04-09 19:14:16', 'GolfAdminServlet'),
(12, 8, 'Database related error\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addGolfGroupAdmin\nLine Number: 1101\n', '2014-04-09 19:16:11', 'GolfAdminServlet'),
(13, 8, 'Database related error\nException [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry ''aubrey.malabie@gmail.com'' for key ''email''\nError Code: 1062\nCall: INSERT INTO golfGroup (cellphone, dateRegistered, email, golfGroupName, countryID) VALUES (?, ?, ?, ?, ?)\n	bind => [5 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.GolfGroup[ golfGroupID=null ])\n\njavax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry ''aubrey.malabie@gmail.com'' for key ''email''\nError Code: 1062\nCall: INSERT INTO golfGroup (cellphone, dateRegistered, email, golfGroupName, countryID) VALUES (?, ?, ?, ?, ?)\n	bind => [5 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.GolfGroup[ golfGroupID=null ])\n\nClass: org.eclipse.persistence.internal.jpa.EntityManagerImpl\nMethod: flush\nLine Number: 851\n', '2014-04-09 19:20:06', 'GolfAdminServlet'),
(14, 133, 'MGGolf temporary files cleaned up', '2014-04-09 19:22:53', 'HouseKeeper'),
(15, 8, 'Generic server app error\njavax.ejb.EJBException\n\nClass: com.sun.ejb.containers.EJBContainerTransactionManager\nMethod: processSystemException\nLine Number: 748\n', '2014-04-09 19:29:01', 'GolfAdminServlet'),
(16, 8, 'Generic server app error\njavax.ejb.EJBException\n\nClass: com.sun.ejb.containers.EJBContainerTransactionManager\nMethod: processSystemException\nLine Number: 748\n', '2014-04-09 19:29:27', 'GolfAdminServlet'),
(17, 8, 'Generic server app error\njavax.ejb.EJBException\n\nClass: com.sun.ejb.containers.EJBContainerTransactionManager\nMethod: processSystemException\nLine Number: 748\n', '2014-04-09 19:50:33', 'GolfAdminServlet'),
(18, 133, 'MGGolf temporary files cleaned up', '2014-04-09 19:50:46', 'HouseKeeper'),
(19, 133, 'MGGolf temporary files cleaned up', '2014-04-09 19:58:24', 'HouseKeeper'),
(20, 133, 'MGGolf temporary files cleaned up', '2014-04-10 10:05:18', 'HouseKeeper'),
(21, 133, 'MGGolf temporary files cleaned up', '2014-04-10 10:11:19', 'HouseKeeper'),
(22, 133, 'MGGolf temporary files cleaned up', '2014-04-10 10:41:17', 'HouseKeeper'),
(23, 133, 'MGGolf temporary files cleaned up', '2014-04-10 10:52:04', 'HouseKeeper'),
(24, 133, 'MGGolf temporary files cleaned up', '2014-04-10 10:57:06', 'HouseKeeper'),
(25, 133, 'MGGolf temporary files cleaned up', '2014-04-10 10:59:15', 'HouseKeeper'),
(26, 133, 'MGGolf temporary files cleaned up', '2014-04-10 11:12:57', 'HouseKeeper'),
(27, 133, 'MGGolf temporary files cleaned up', '2014-04-10 11:24:09', 'HouseKeeper'),
(28, 8, 'Database related error\nFailed to get GolfGroup\nYou have attempted to set a parameter value using a name of id that does not exist in the query string SELECT p FROM Player p, GolfGroupPlayer b where p.playerID = b.player.playerID order by p.lastName, p.firstName.\n\njava.lang.IllegalArgumentException: You have attempted to set a parameter value using a name of id that does not exist in the query string SELECT p FROM Player p, GolfGroupPlayer b where p.playerID = b.player.playerID order by p.lastName, p.firstName.\n\nClass: org.eclipse.persistence.internal.jpa.QueryImpl\nMethod: setParameterInternal\nLine Number: 927\n', '2014-04-10 11:34:22', 'GolfAdminServlet'),
(29, 133, 'MGGolf temporary files cleaned up', '2014-04-10 11:40:25', 'HouseKeeper'),
(30, 8, 'Database related error\nFailed to get GolfGroup\nNamedQuery of name: Parent.findByGolfGroup not found.\n\njava.lang.IllegalArgumentException: NamedQuery of name: Parent.findByGolfGroup not found.\n\nClass: org.eclipse.persistence.internal.jpa.QueryImpl\nMethod: getDatabaseQueryInternal\nLine Number: 350\n', '2014-04-10 11:46:03', 'GolfAdminServlet'),
(31, 8, 'Generic server app error\njavax.ejb.EJBException\n\nClass: com.sun.ejb.containers.EJBContainerTransactionManager\nMethod: processSystemException\nLine Number: 748\n', '2014-04-10 11:50:33', 'GolfAdminServlet'),
(32, 133, 'MGGolf temporary files cleaned up', '2014-04-10 12:04:34', 'HouseKeeper'),
(33, 8, 'Database related error\nFailed to get GolfGroup\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.ProvinceDTO\nMethod: <init>\nLine Number: 26\n', '2014-04-10 12:16:23', 'GolfAdminServlet'),
(34, 8, 'Database related error\nFailed to get GolfGroup\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.ProvinceDTO\nMethod: <init>\nLine Number: 26\n', '2014-04-10 12:18:48', 'GolfAdminServlet'),
(35, 8, 'Database related error\nFailed to get GolfGroup\nUncompilable source code - incompatible types: possible lossy conversion from double to int\n\njava.lang.RuntimeException: Uncompilable source code - incompatible types: possible lossy conversion from double to int\n\nClass: com.boha.golfkids.dto.ProvinceDTO\nMethod: <init>\nLine Number: 26\n', '2014-04-10 12:24:02', 'GolfAdminServlet'),
(36, 8, 'Database related error\nFailed to get GolfGroup\nUncompilable source code - incompatible types: possible lossy conversion from double to int\n\njava.lang.RuntimeException: Uncompilable source code - incompatible types: possible lossy conversion from double to int\n\nClass: com.boha.golfkids.dto.ClubDTO\nMethod: <init>\nLine Number: 33\n', '2014-04-10 12:29:17', 'GolfAdminServlet'),
(37, 133, 'MGGolf temporary files cleaned up', '2014-04-10 12:29:18', 'HouseKeeper'),
(38, 8, 'Database related error\nFailed to get GolfGroup\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.ClubCourseDTO\nMethod: <init>\nLine Number: 114\n', '2014-04-10 12:38:18', 'GolfAdminServlet'),
(39, 133, 'MGGolf temporary files cleaned up', '2014-04-10 12:39:16', 'HouseKeeper'),
(40, 8, 'Database related error\nFailed to get GolfGroup\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.ClubCourseDTO\nMethod: <init>\nLine Number: 114\n', '2014-04-10 12:39:49', 'GolfAdminServlet'),
(41, 8, 'Database related error\nFailed to get GolfGroup\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.ClubCourseDTO\nMethod: <init>\nLine Number: 114\n', '2014-04-10 12:45:02', 'GolfAdminServlet'),
(42, 8, 'Generic server app error\njavax.ejb.EJBException\n\nClass: com.sun.ejb.containers.EJBContainerTransactionManager\nMethod: processSystemException\nLine Number: 748\n', '2014-04-10 12:48:09', 'GolfAdminServlet'),
(43, 133, 'MGGolf temporary files cleaned up', '2014-04-10 12:53:47', 'HouseKeeper'),
(44, 133, 'MGGolf temporary files cleaned up', '2014-04-10 13:09:30', 'HouseKeeper'),
(45, 133, 'MGGolf temporary files cleaned up', '2014-04-10 13:13:20', 'HouseKeeper'),
(46, 133, 'MGGolf temporary files cleaned up', '2014-04-10 13:21:02', 'HouseKeeper'),
(47, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 38\nElapsed time in seconds: 66.34', '2014-04-10 14:06:58', 'GolfAdminServlet'),
(48, 133, 'MGGolf temporary files cleaned up', '2014-04-10 14:07:11', 'HouseKeeper'),
(49, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 38\nElapsed time in seconds: 35.949', '2014-04-10 14:10:54', 'GolfAdminServlet'),
(50, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 38\nElapsed time in seconds: 8257.312', '2014-04-10 17:02:28', 'GolfAdminServlet'),
(51, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 38\nElapsed time in seconds: 10271.443', '2014-04-10 17:02:28', 'GolfAdminServlet'),
(52, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 38\nElapsed time in seconds: 7574.889', '2014-04-10 17:02:28', 'GolfAdminServlet'),
(53, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 38\nElapsed time in seconds: 8497.387', '2014-04-10 17:02:28', 'GolfAdminServlet'),
(54, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 38\nElapsed time in seconds: 8437.349', '2014-04-10 17:02:28', 'GolfAdminServlet'),
(55, 133, 'MGGolf temporary files cleaned up', '2014-04-10 19:59:54', 'HouseKeeper'),
(56, 133, 'MGGolf temporary files cleaned up', '2014-04-10 20:12:07', 'HouseKeeper'),
(57, 133, 'MGGolf temporary files cleaned up', '2014-04-10 20:19:25', 'HouseKeeper'),
(58, 133, 'MGGolf temporary files cleaned up', '2014-04-10 20:37:30', 'HouseKeeper'),
(59, 133, 'MGGolf temporary files cleaned up', '2014-04-10 20:44:30', 'HouseKeeper'),
(60, 8, 'Database related error\nNamedQuery of name: Player.findByEmail not found.\n\njava.lang.IllegalArgumentException: NamedQuery of name: Player.findByEmail not found.\n\nClass: org.eclipse.persistence.internal.jpa.QueryImpl\nMethod: getDatabaseQueryInternal\nLine Number: 350\n', '2014-04-10 23:51:34', 'GolfAdminServlet'),
(61, 8, 'Database related error\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addGolfGroupPlayer\nLine Number: 1142\n', '2014-04-10 23:54:32', 'GolfAdminServlet'),
(62, 133, 'MGGolf temporary files cleaned up', '2014-04-10 23:57:11', 'HouseKeeper'),
(63, 8, 'Database related error\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addGolfGroupPlayer\nLine Number: 1142\n', '2014-04-10 23:57:26', 'GolfAdminServlet'),
(64, 8, 'Database related error\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addGolfGroupPlayer\nLine Number: 1142\n', '2014-04-10 23:57:46', 'GolfAdminServlet'),
(65, 8, 'Database related error\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addGolfGroupPlayer\nLine Number: 1142\n', '2014-04-10 23:58:28', 'GolfAdminServlet'),
(66, 133, 'MGGolf temporary files cleaned up', '2014-04-10 23:59:39', 'HouseKeeper'),
(67, 8, 'Database related error\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addGolfGroupPlayer\nLine Number: 1143\n', '2014-04-11 00:03:37', 'GolfAdminServlet'),
(68, 8, 'Database related error\nException [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: java.sql.SQLException: Lock wait timeout exceeded; try restarting transaction\nError Code: 1205\nCall: INSERT INTO player (cellphone, dateOfBirth, dateRegistered, email, firstName, gender, lastName, middleName, pin, yearJoined, parentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n	bind => [11 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.Player[ playerID=null ])\n\njavax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: java.sql.SQLException: Lock wait timeout exceeded; try restarting transaction\nError Code: 1205\nCall: INSERT INTO player (cellphone, dateOfBirth, dateRegistered, email, firstName, gender, lastName, middleName, pin, yearJoined, parentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n	bind => [11 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.Player[ playerID=null ])\n\nClass: org.eclipse.persistence.internal.jpa.EntityManagerImpl\nMethod: flush\nLine Number: 851\n', '2014-04-11 00:08:53', 'GolfAdminServlet'),
(69, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 15\nElapsed time in seconds: 52.081', '2014-04-11 00:08:54', 'GolfAdminServlet'),
(70, 8, 'Database related error\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addGolfGroupPlayer\nLine Number: 1143\n', '2014-04-11 00:09:23', 'GolfAdminServlet'),
(71, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 15\nElapsed time in seconds: 140.847', '2014-04-11 00:09:23', 'GolfAdminServlet'),
(72, 8, 'Database related error\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addGolfGroupPlayer\nLine Number: 1143\n', '2014-04-11 00:09:33', 'GolfAdminServlet'),
(73, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 15\nElapsed time in seconds: 26.815', '2014-04-11 00:09:33', 'GolfAdminServlet'),
(74, 133, 'MGGolf temporary files cleaned up', '2014-04-11 00:09:58', 'HouseKeeper'),
(75, 8, 'Database related error\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addGolfGroupPlayer\nLine Number: 1143\n', '2014-04-11 00:10:32', 'GolfAdminServlet'),
(76, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 15\nElapsed time in seconds: 8.213', '2014-04-11 00:10:32', 'GolfAdminServlet'),
(77, 133, 'MGGolf temporary files cleaned up', '2014-04-11 00:17:45', 'HouseKeeper'),
(78, 133, 'MGGolf temporary files cleaned up', '2014-04-11 09:17:46', 'HouseKeeper'),
(79, 133, 'MGGolf temporary files cleaned up', '2014-04-11 09:17:46', 'HouseKeeper'),
(80, 133, 'MGGolf temporary files cleaned up', '2014-04-11 10:57:25', 'HouseKeeper'),
(81, 133, 'MGGolf temporary files cleaned up', '2014-04-11 11:12:31', 'HouseKeeper'),
(82, 133, 'MGGolf temporary files cleaned up', '2014-04-11 11:18:32', 'HouseKeeper'),
(83, 133, 'MGGolf temporary files cleaned up', '2014-04-11 13:11:05', 'HouseKeeper'),
(84, 133, 'MGGolf temporary files cleaned up', '2014-04-11 13:15:59', 'HouseKeeper'),
(85, 133, 'MGGolf temporary files cleaned up', '2014-04-11 13:39:15', 'HouseKeeper'),
(86, 133, 'MGGolf temporary files cleaned up', '2014-04-11 13:43:07', 'HouseKeeper'),
(87, 8, 'Database related error\nException [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column ''clubID'' cannot be null\nError Code: 1048\nCall: INSERT INTO tournament (closingDate, endDate, golfRounds, startDate, tourneyName, clubID, clubCourseID, golfGroupID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n	bind => [8 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.Tournament[ tournamentID=null ])\n\njavax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column ''clubID'' cannot be null\nError Code: 1048\nCall: INSERT INTO tournament (closingDate, endDate, golfRounds, startDate, tourneyName, clubID, clubCourseID, golfGroupID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n	bind => [8 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.Tournament[ tournamentID=null ])\n\nClass: org.eclipse.persistence.internal.jpa.EntityManagerImpl\nMethod: flush\nLine Number: 851\n', '2014-04-11 15:05:32', 'GolfAdminServlet'),
(88, 8, 'Database related error\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.TournamentDTO\nMethod: <init>\nLine Number: 30\n', '2014-04-11 15:18:23', 'GolfAdminServlet'),
(89, 133, 'MGGolf temporary files cleaned up', '2014-04-11 15:24:09', 'HouseKeeper'),
(90, 8, 'Database related error\nException [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry ''10-Woody Woodpecker Classic-2014-04-19 12:00:00'' for key ''golfGroupID''\nError Code: 1062\nCall: INSERT INTO tournament (closingDate, endDate, golfRounds, startDate, tourneyName, clubID, clubCourseID, golfGroupID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n	bind => [8 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.Tournament[ tournamentID=null ])\n\njavax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry ''10-Woody Woodpecker Classic-2014-04-19 12:00:00'' for key ''golfGroupID''\nError Code: 1062\nCall: INSERT INTO tournament (closingDate, endDate, golfRounds, startDate, tourneyName, clubID, clubCourseID, golfGroupID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n	bind => [8 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.Tournament[ tournamentID=null ])\n\nClass: org.eclipse.persistence.internal.jpa.EntityManagerImpl\nMethod: flush\nLine Number: 851\n', '2014-04-11 15:35:46', 'GolfAdminServlet'),
(91, 8, 'Database related error\nException [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry ''10-Woody Woodpecker Classic-2014-04-19 12:00:00'' for key ''golfGroupID''\nError Code: 1062\nCall: INSERT INTO tournament (closingDate, endDate, golfRounds, startDate, tourneyName, clubID, clubCourseID, golfGroupID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n	bind => [8 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.Tournament[ tournamentID=null ])\n\njavax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry ''10-Woody Woodpecker Classic-2014-04-19 12:00:00'' for key ''golfGroupID''\nError Code: 1062\nCall: INSERT INTO tournament (closingDate, endDate, golfRounds, startDate, tourneyName, clubID, clubCourseID, golfGroupID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n	bind => [8 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.Tournament[ tournamentID=null ])\n\nClass: org.eclipse.persistence.internal.jpa.EntityManagerImpl\nMethod: flush\nLine Number: 851\n', '2014-04-11 15:47:24', 'GolfAdminServlet'),
(92, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 4\nElapsed time in seconds: 10.399', '2014-04-11 15:47:26', 'GolfAdminServlet'),
(93, 133, 'MGGolf temporary files cleaned up', '2014-04-11 17:04:59', 'HouseKeeper'),
(94, 133, 'MGGolf temporary files cleaned up', '2014-04-11 17:57:43', 'HouseKeeper'),
(95, 133, 'MGGolf temporary files cleaned up', '2014-04-11 18:44:00', 'HouseKeeper'),
(96, 133, 'MGGolf temporary files cleaned up', '2014-04-11 19:35:34', 'HouseKeeper'),
(97, 133, 'MGGolf temporary files cleaned up', '2014-04-11 19:39:04', 'HouseKeeper'),
(98, 133, 'MGGolf temporary files cleaned up', '2014-04-11 19:46:08', 'HouseKeeper'),
(99, 133, 'MGGolf temporary files cleaned up', '2014-04-11 19:55:33', 'HouseKeeper'),
(100, 133, 'MGGolf temporary files cleaned up', '2014-04-11 19:57:04', 'HouseKeeper'),
(101, 133, 'MGGolf temporary files cleaned up', '2014-04-11 19:57:45', 'HouseKeeper'),
(102, 133, 'MGGolf temporary files cleaned up', '2014-04-11 19:57:53', 'HouseKeeper'),
(103, 133, 'MGGolf temporary files cleaned up', '2014-04-11 21:07:20', 'HouseKeeper'),
(104, 133, 'MGGolf temporary files cleaned up', '2014-04-11 21:15:35', 'HouseKeeper'),
(105, 133, 'MGGolf temporary files cleaned up', '2014-04-11 21:25:13', 'HouseKeeper'),
(106, 133, 'MGGolf temporary files cleaned up', '2014-04-11 22:09:09', 'HouseKeeper'),
(107, 8, 'Database related error\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.TourneyPlayerScoreDTO\nMethod: <init>\nLine Number: 47\n', '2014-04-11 23:48:47', 'GolfAdminServlet'),
(108, 133, 'MGGolf temporary files cleaned up', '2014-04-11 23:49:12', 'HouseKeeper'),
(109, 8, 'Database related error\nException [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry ''17-124'' for key ''ixtp''\nError Code: 1062\nCall: INSERT INTO tourneyPlayerScore (dateRegistered, dateUpdated, paidFlag, scoreRound1, scoreRound2, scoreRound3, scoreRound4, scoreRound5, scoreRound6, totalScore, tourneyPosition, tourneyPositionTied, winnerFlag, administratorID, ageGroupID, playerID, tournamentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n	bind => [17 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.TourneyPlayerScore[ tourneyPlayerScoreID=0 ])\n\njavax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry ''17-124'' for key ''ixtp''\nError Code: 1062\nCall: INSERT INTO tourneyPlayerScore (dateRegistered, dateUpdated, paidFlag, scoreRound1, scoreRound2, scoreRound3, scoreRound4, scoreRound5, scoreRound6, totalScore, tourneyPosition, tourneyPositionTied, winnerFlag, administratorID, ageGroupID, playerID, tournamentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n	bind => [17 parameters bound]\nQuery: InsertObjectQuery(com.boha.golfkids.data.TourneyPlayerScore[ tourneyPlayerScoreID=0 ])\n\nClass: org.eclipse.persistence.internal.jpa.EntityManagerImpl\nMethod: flush\nLine Number: 851\n', '2014-04-11 23:53:47', 'GolfAdminServlet'),
(110, 133, 'MGGolf temporary files cleaned up', '2014-04-11 23:55:56', 'HouseKeeper'),
(111, 133, 'MGGolf temporary files cleaned up', '2014-04-11 23:58:23', 'HouseKeeper'),
(112, 8, 'Generic server app error\nTransaction aborted\n\njavax.ejb.EJBException: Transaction aborted\n\nClass: com.sun.ejb.containers.EJBContainerTransactionManager\nMethod: completeNewTx\nLine Number: 725\n', '2014-04-12 00:02:49', 'GolfAdminServlet'),
(113, 133, 'MGGolf temporary files cleaned up', '2014-04-12 00:03:15', 'HouseKeeper'),
(114, 133, 'MGGolf temporary files cleaned up', '2014-04-12 07:27:19', 'HouseKeeper'),
(115, 133, 'MGGolf temporary files cleaned up', '2014-04-12 07:35:08', 'HouseKeeper'),
(116, 133, 'MGGolf temporary files cleaned up', '2014-04-12 07:57:07', 'HouseKeeper'),
(117, 133, 'MGGolf temporary files cleaned up', '2014-04-12 07:58:59', 'HouseKeeper'),
(118, 133, 'MGGolf temporary files cleaned up', '2014-04-12 07:59:02', 'HouseKeeper'),
(119, 133, 'MGGolf temporary files cleaned up', '2014-04-12 07:59:51', 'HouseKeeper'),
(120, 133, 'MGGolf temporary files cleaned up', '2014-04-12 07:59:53', 'HouseKeeper'),
(121, 133, 'MGGolf temporary files cleaned up', '2014-04-12 08:08:58', 'HouseKeeper'),
(122, 133, 'MGGolf temporary files cleaned up', '2014-04-12 08:12:41', 'HouseKeeper'),
(123, 133, 'MGGolf temporary files cleaned up', '2014-04-12 08:49:39', 'HouseKeeper'),
(124, 133, 'MGGolf temporary files cleaned up', '2014-04-12 12:43:07', 'HouseKeeper'),
(125, 8, 'Generic server app error\njavax.ejb.EJBException\n\nClass: com.sun.ejb.containers.EJBContainerTransactionManager\nMethod: processSystemException\nLine Number: 748\n', '2014-04-12 13:41:46', 'GolfAdminServlet'),
(126, 133, 'MGGolf temporary files cleaned up', '2014-04-12 13:58:10', 'HouseKeeper'),
(127, 133, 'MGGolf temporary files cleaned up', '2014-04-12 13:58:13', 'HouseKeeper'),
(128, 133, 'MGGolf temporary files cleaned up', '2014-04-12 13:58:17', 'HouseKeeper'),
(129, 8, 'Generic server app error\njavax.ejb.EJBException\n\nClass: com.sun.ejb.containers.EJBContainerTransactionManager\nMethod: processSystemException\nLine Number: 748\n', '2014-04-12 14:02:09', 'GolfAdminServlet'),
(130, 133, 'MGGolf temporary files cleaned up', '2014-04-12 14:07:01', 'HouseKeeper'),
(131, 133, 'MGGolf temporary files cleaned up', '2014-04-12 16:48:05', 'HouseKeeper'),
(132, 133, 'MGGolf temporary files cleaned up', '2014-04-12 16:57:47', 'HouseKeeper'),
(133, 133, 'MGGolf temporary files cleaned up', '2014-04-12 17:43:10', 'HouseKeeper'),
(134, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 38\nElapsed time in seconds: 7.807', '2014-04-12 18:28:19', 'GolfAdminServlet'),
(135, 8, 'Generic server app error\njavax.ejb.EJBException\n\nClass: com.sun.ejb.containers.EJBContainerTransactionManager\nMethod: processSystemException\nLine Number: 748\n', '2014-04-12 19:42:27', 'GolfAdminServlet'),
(136, 8, 'Database related error\nFailed to get Tourney players\ncom.boha.golfkids.data.TourneyPlayerScore cannot be cast to com.boha.golfkids.data.TourneyScoreByRound\n\njava.lang.ClassCastException: com.boha.golfkids.data.TourneyPlayerScore cannot be cast to com.boha.golfkids.data.TourneyScoreByRound\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: getTournamentPlayers\nLine Number: 82\n', '2014-04-12 19:47:56', 'GolfAdminServlet'),
(137, 8, 'Database related error\nFailed to get Tourney players\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.TourneyScoreByRoundDTO\nMethod: <init>\nLine Number: 44\n', '2014-04-12 19:50:43', 'GolfAdminServlet'),
(138, 133, 'MGGolf temporary files cleaned up', '2014-04-12 19:53:34', 'HouseKeeper'),
(139, 8, 'Database related error\nFailed to get Tourney players\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.TourneyScoreByRoundDTO\nMethod: <init>\nLine Number: 44\n', '2014-04-12 20:02:53', 'GolfAdminServlet'),
(140, 133, 'MGGolf temporary files cleaned up', '2014-04-12 20:08:00', 'HouseKeeper'),
(141, 133, 'MGGolf temporary files cleaned up', '2014-04-12 20:22:54', 'HouseKeeper'),
(142, 133, 'MGGolf temporary files cleaned up', '2014-04-12 23:57:41', 'HouseKeeper'),
(143, 133, 'MGGolf temporary files cleaned up', '2014-04-12 23:58:01', 'HouseKeeper'),
(144, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 38\nElapsed time in seconds: 9.993', '2014-04-13 00:06:05', 'GolfAdminServlet'),
(145, 133, 'MGGolf temporary files cleaned up', '2014-04-13 00:09:09', 'HouseKeeper'),
(146, 133, 'MGGolf temporary files cleaned up', '2014-04-13 00:15:23', 'HouseKeeper'),
(147, 133, 'MGGolf temporary files cleaned up', '2014-04-13 02:40:19', 'HouseKeeper'),
(148, 133, 'MGGolf temporary files cleaned up', '2014-04-13 03:05:06', 'HouseKeeper'),
(149, 133, 'MGGolf temporary files cleaned up', '2014-04-13 03:08:55', 'HouseKeeper'),
(150, 133, 'MGGolf temporary files cleaned up', '2014-04-13 11:37:54', 'HouseKeeper'),
(151, 133, 'MGGolf temporary files cleaned up', '2014-04-13 11:37:54', 'HouseKeeper'),
(152, 133, 'MGGolf temporary files cleaned up', '2014-04-13 11:50:14', 'HouseKeeper'),
(153, 133, 'MGGolf temporary files cleaned up', '2014-04-13 11:54:10', 'HouseKeeper'),
(154, 133, 'MGGolf temporary files cleaned up', '2014-04-13 11:57:59', 'HouseKeeper'),
(155, 133, 'MGGolf temporary files cleaned up', '2014-04-13 11:58:50', 'HouseKeeper'),
(156, 133, 'MGGolf temporary files cleaned up', '2014-04-13 11:58:58', 'HouseKeeper'),
(157, 133, 'MGGolf temporary files cleaned up', '2014-04-13 11:59:47', 'HouseKeeper'),
(158, 133, 'MGGolf temporary files cleaned up', '2014-04-13 12:03:53', 'HouseKeeper'),
(159, 133, 'MGGolf temporary files cleaned up', '2014-04-13 12:44:12', 'HouseKeeper'),
(160, 133, 'MGGolf temporary files cleaned up', '2014-04-13 12:48:05', 'HouseKeeper'),
(161, 133, 'MGGolf temporary files cleaned up', '2014-04-13 14:50:43', 'HouseKeeper'),
(162, 133, 'MGGolf temporary files cleaned up', '2014-04-13 14:57:15', 'HouseKeeper'),
(163, 133, 'MGGolf temporary files cleaned up', '2014-04-13 14:59:22', 'HouseKeeper'),
(164, 133, 'MGGolf temporary files cleaned up', '2014-04-13 15:07:42', 'HouseKeeper'),
(165, 133, 'MGGolf temporary files cleaned up', '2014-04-13 15:15:18', 'HouseKeeper'),
(166, 133, 'MGGolf temporary files cleaned up', '2014-04-13 15:19:54', 'HouseKeeper'),
(167, 133, 'MGGolf temporary files cleaned up', '2014-04-13 15:33:26', 'HouseKeeper'),
(168, 133, 'MGGolf temporary files cleaned up', '2014-04-13 15:44:33', 'HouseKeeper'),
(169, 133, 'MGGolf temporary files cleaned up', '2014-04-13 19:37:00', 'HouseKeeper'),
(170, 8, 'Generic server app error\nUncompilable source code - incompatible types: com.boha.golfkids.dto.ResponseDTO cannot be converted to java.util.List<com.boha.golfkids.dto.LeaderBoardDTO>\n\njava.lang.RuntimeException: Uncompilable source code - incompatible types: com.boha.golfkids.dto.ResponseDTO cannot be converted to java.util.List<com.boha.golfkids.dto.LeaderBoardDTO>\n\nClass: com.boha.golfkids.servlet.GolfAdminServlet\nMethod: processRequest\nLine Number: 84\n', '2014-04-13 20:01:36', 'GolfAdminServlet'),
(171, 133, 'MGGolf temporary files cleaned up', '2014-04-13 20:10:52', 'HouseKeeper'),
(172, 133, 'MGGolf temporary files cleaned up', '2014-04-13 20:27:15', 'HouseKeeper'),
(173, 133, 'MGGolf temporary files cleaned up', '2014-04-14 00:42:04', 'HouseKeeper'),
(174, 133, 'MGGolf temporary files cleaned up', '2014-04-14 10:15:06', 'HouseKeeper'),
(175, 133, 'MGGolf temporary files cleaned up', '2014-04-14 10:15:09', 'HouseKeeper'),
(176, 133, 'MGGolf temporary files cleaned up', '2014-04-14 11:48:16', 'HouseKeeper'),
(177, 133, 'MGGolf temporary files cleaned up', '2014-04-14 11:53:01', 'HouseKeeper'),
(178, 133, 'MGGolf temporary files cleaned up', '2014-04-14 11:59:19', 'HouseKeeper'),
(179, 133, 'MGGolf temporary files cleaned up', '2014-04-14 13:34:07', 'HouseKeeper'),
(180, 133, 'MGGolf temporary files cleaned up', '2014-04-14 13:43:48', 'HouseKeeper'),
(181, 133, 'MGGolf temporary files cleaned up', '2014-04-14 13:58:45', 'HouseKeeper'),
(182, 133, 'MGGolf temporary files cleaned up', '2014-04-14 13:59:10', 'HouseKeeper'),
(183, 133, 'MGGolf temporary files cleaned up', '2014-04-14 13:59:28', 'HouseKeeper'),
(184, 133, 'MGGolf temporary files cleaned up', '2014-04-14 14:19:30', 'HouseKeeper'),
(185, 133, 'MGGolf temporary files cleaned up', '2014-04-14 14:26:05', 'HouseKeeper'),
(186, 133, 'MGGolf temporary files cleaned up', '2014-04-14 15:54:21', 'HouseKeeper'),
(187, 8, 'Database related error\nFailed to get GolfGroup\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.ProvinceDTO\nMethod: <init>\nLine Number: 24\n', '2014-04-14 17:41:45', 'GolfAdminServlet'),
(188, 133, 'MGGolf temporary files cleaned up', '2014-04-14 17:44:19', 'HouseKeeper'),
(189, 133, 'MGGolf temporary files cleaned up', '2014-04-14 17:55:21', 'HouseKeeper'),
(190, 133, 'MGGolf temporary files cleaned up', '2014-04-15 03:59:00', 'HouseKeeper'),
(191, 133, 'MGGolf temporary files cleaned up', '2014-04-15 03:59:03', 'HouseKeeper'),
(192, 133, 'MGGolf temporary files cleaned up', '2014-04-15 04:09:34', 'HouseKeeper'),
(193, 133, 'MGGolf temporary files cleaned up', '2014-04-15 08:10:03', 'HouseKeeper'),
(194, 133, 'MGGolf temporary files cleaned up', '2014-04-15 09:26:36', 'HouseKeeper'),
(195, 133, 'MGGolf temporary files cleaned up', '2014-04-15 10:12:30', 'HouseKeeper'),
(196, 133, 'MGGolf temporary files cleaned up', '2014-04-15 10:15:54', 'HouseKeeper'),
(197, 8, 'Database related error\nFailed to add Parent\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addParent\nLine Number: 1234\n', '2014-04-15 11:34:56', 'GolfAdminServlet'),
(198, 133, 'MGGolf temporary files cleaned up', '2014-04-15 11:41:00', 'HouseKeeper'),
(199, 133, 'MGGolf temporary files cleaned up', '2014-04-15 11:46:52', 'HouseKeeper'),
(200, 133, 'MGGolf temporary files cleaned up', '2014-04-15 11:50:15', 'HouseKeeper'),
(201, 133, 'MGGolf temporary files cleaned up', '2014-04-15 11:59:19', 'HouseKeeper'),
(202, 8, 'Generic server app error\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.servlet.GolfAdminServlet\nMethod: processRequest\nLine Number: 118\n', '2014-04-15 13:42:50', 'GolfAdminServlet'),
(203, 133, 'MGGolf temporary files cleaned up', '2014-04-15 13:47:59', 'HouseKeeper'),
(204, 133, 'MGGolf temporary files cleaned up', '2014-04-15 13:58:09', 'HouseKeeper'),
(205, 133, 'MGGolf temporary files cleaned up', '2014-04-15 14:23:04', 'HouseKeeper'),
(206, 133, 'MGGolf temporary files cleaned up', '2014-04-15 18:11:37', 'HouseKeeper'),
(207, 133, 'MGGolf temporary files cleaned up', '2014-04-15 18:30:13', 'HouseKeeper'),
(208, 133, 'MGGolf temporary files cleaned up', '2014-04-15 18:34:26', 'HouseKeeper'),
(209, 133, 'MGGolf temporary files cleaned up', '2014-04-15 20:51:10', 'HouseKeeper'),
(210, 133, 'MGGolf temporary files cleaned up', '2014-04-15 20:59:17', 'HouseKeeper'),
(211, 8, 'Database related error\nFailed to close tournament\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.LeaderBoardUtil\nMethod: createLeaderBoard\nLine Number: 81\n', '2014-04-15 21:05:30', 'GolfAdminServlet'),
(212, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 42\nElapsed time in seconds: 51.437', '2014-04-15 21:05:30', 'GolfAdminServlet'),
(213, 133, 'MGGolf temporary files cleaned up', '2014-04-15 21:06:12', 'HouseKeeper'),
(214, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 42\nElapsed time in seconds: 411.332', '2014-04-15 21:10:30', 'GolfAdminServlet'),
(215, 8, 'Database related error\nFailed to close tournament\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.LeaderBoardUtil\nMethod: createLeaderBoard\nLine Number: 81\n', '2014-04-15 21:14:47', 'GolfAdminServlet'),
(216, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 42\nElapsed time in seconds: 110.83', '2014-04-15 21:14:47', 'GolfAdminServlet'),
(217, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 42\nElapsed time in seconds: 50.824', '2014-04-15 21:14:47', 'GolfAdminServlet'),
(218, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 42\nElapsed time in seconds: 46.766', '2014-04-15 21:17:02', 'GolfAdminServlet'),
(219, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 42\nElapsed time in seconds: 106.777', '2014-04-15 21:17:02', 'GolfAdminServlet'),
(220, 133, 'MGGolf temporary files cleaned up', '2014-04-15 21:29:04', 'HouseKeeper'),
(221, 133, 'MGGolf temporary files cleaned up', '2014-04-15 21:38:44', 'HouseKeeper'),
(222, 133, 'MGGolf temporary files cleaned up', '2014-04-15 21:46:16', 'HouseKeeper'),
(223, 133, 'MGGolf temporary files cleaned up', '2014-04-15 21:59:48', 'HouseKeeper'),
(224, 133, 'MGGolf temporary files cleaned up', '2014-04-15 22:05:31', 'HouseKeeper'),
(225, 8, 'Database related error\nFailed to get player history\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.util.LeaderBoardUtil\nMethod: getPlayerHistory\nLine Number: 95\n', '2014-04-16 00:08:49', 'GolfAdminServlet'),
(226, 133, 'MGGolf temporary files cleaned up', '2014-04-16 00:12:37', 'HouseKeeper'),
(227, 133, 'MGGolf temporary files cleaned up', '2014-04-16 00:26:51', 'HouseKeeper'),
(228, 133, 'MGGolf temporary files cleaned up', '2014-04-16 02:06:37', 'HouseKeeper'),
(229, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 38\nElapsed time in seconds: 5.65', '2014-04-16 02:31:22', 'GolfAdminServlet'),
(230, 133, 'MGGolf temporary files cleaned up', '2014-04-16 02:34:04', 'HouseKeeper'),
(231, 133, 'MGGolf temporary files cleaned up', '2014-04-16 02:46:32', 'HouseKeeper'),
(232, 133, 'MGGolf temporary files cleaned up', '2014-04-16 02:58:16', 'HouseKeeper'),
(233, 133, 'MGGolf temporary files cleaned up', '2014-04-16 02:58:28', 'HouseKeeper'),
(234, 133, 'MGGolf temporary files cleaned up', '2014-04-16 03:05:15', 'HouseKeeper'),
(235, 8, 'Database related error\nFailed to get Tourney players\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.TourneyScoreByRoundDTO\nMethod: <init>\nLine Number: 54\n', '2014-04-16 03:15:51', 'GolfAdminServlet'),
(236, 8, 'Database related error\nFailed to get leaderboard\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.TourneyScoreByRoundDTO\nMethod: <init>\nLine Number: 54\n', '2014-04-16 03:16:00', 'GolfAdminServlet'),
(237, 133, 'MGGolf temporary files cleaned up', '2014-04-16 03:25:06', 'HouseKeeper'),
(238, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 6\nElapsed time in seconds: 14.931', '2014-04-16 03:31:54', 'GolfAdminServlet'),
(239, 133, 'MGGolf temporary files cleaned up', '2014-04-16 10:48:27', 'HouseKeeper'),
(240, 133, 'MGGolf temporary files cleaned up', '2014-04-16 13:58:15', 'HouseKeeper'),
(241, 133, 'MGGolf temporary files cleaned up', '2014-04-16 13:58:17', 'HouseKeeper'),
(242, 133, 'MGGolf temporary files cleaned up', '2014-04-16 13:58:19', 'HouseKeeper'),
(243, 133, 'MGGolf temporary files cleaned up', '2014-04-16 14:45:52', 'HouseKeeper'),
(244, 133, 'MGGolf temporary files cleaned up', '2014-04-16 14:56:19', 'HouseKeeper'),
(245, 133, 'MGGolf temporary files cleaned up', '2014-04-16 14:59:41', 'HouseKeeper'),
(246, 133, 'MGGolf temporary files cleaned up', '2014-04-16 14:59:42', 'HouseKeeper'),
(247, 133, 'MGGolf temporary files cleaned up', '2014-04-16 15:03:55', 'HouseKeeper'),
(248, 133, 'MGGolf temporary files cleaned up', '2014-04-16 15:14:19', 'HouseKeeper'),
(249, 133, 'MGGolf temporary files cleaned up', '2014-04-16 15:19:49', 'HouseKeeper'),
(250, 133, 'MGGolf temporary files cleaned up', '2014-04-16 15:25:16', 'HouseKeeper'),
(251, 133, 'MGGolf temporary files cleaned up', '2014-04-16 15:33:17', 'HouseKeeper'),
(252, 133, 'MGGolf temporary files cleaned up', '2014-04-16 15:39:47', 'HouseKeeper'),
(253, 133, 'MGGolf temporary files cleaned up', '2014-04-16 15:44:46', 'HouseKeeper'),
(254, 133, 'MGGolf temporary files cleaned up', '2014-04-16 15:48:09', 'HouseKeeper'),
(255, 133, 'MGGolf temporary files cleaned up', '2014-04-16 15:51:48', 'HouseKeeper'),
(256, 133, 'MGGolf temporary files cleaned up', '2014-04-16 15:59:35', 'HouseKeeper'),
(257, 133, 'MGGolf temporary files cleaned up', '2014-04-16 16:03:20', 'HouseKeeper'),
(258, 133, 'MGGolf temporary files cleaned up', '2014-04-16 16:19:46', 'HouseKeeper'),
(259, 133, 'MGGolf temporary files cleaned up', '2014-04-16 16:24:48', 'HouseKeeper'),
(260, 133, 'MGGolf temporary files cleaned up', '2014-04-16 16:29:21', 'HouseKeeper'),
(261, 133, 'MGGolf temporary files cleaned up', '2014-04-16 16:36:35', 'HouseKeeper'),
(262, 133, 'MGGolf temporary files cleaned up', '2014-04-16 16:54:18', 'HouseKeeper'),
(263, 133, 'MGGolf temporary files cleaned up', '2014-04-16 16:57:42', 'HouseKeeper'),
(264, 133, 'MGGolf temporary files cleaned up', '2014-04-16 16:57:43', 'HouseKeeper'),
(265, 133, 'MGGolf temporary files cleaned up', '2014-04-16 17:06:44', 'HouseKeeper'),
(266, 133, 'MGGolf temporary files cleaned up', '2014-04-16 17:10:12', 'HouseKeeper'),
(267, 133, 'MGGolf temporary files cleaned up', '2014-04-16 17:15:34', 'HouseKeeper'),
(268, 133, 'MGGolf temporary files cleaned up', '2014-04-16 18:50:20', 'HouseKeeper'),
(269, 133, 'MGGolf temporary files cleaned up', '2014-04-16 18:58:03', 'HouseKeeper'),
(270, 133, 'MGGolf temporary files cleaned up', '2014-04-16 18:58:06', 'HouseKeeper'),
(271, 133, 'MGGolf temporary files cleaned up', '2014-04-16 18:58:28', 'HouseKeeper'),
(272, 133, 'MGGolf temporary files cleaned up', '2014-04-16 18:58:29', 'HouseKeeper'),
(273, 133, 'MGGolf temporary files cleaned up', '2014-04-16 19:15:33', 'HouseKeeper'),
(274, 133, 'MGGolf temporary files cleaned up', '2014-04-16 19:26:25', 'HouseKeeper'),
(275, 133, 'MGGolf temporary files cleaned up', '2014-04-16 20:44:29', 'HouseKeeper'),
(276, 133, 'MGGolf temporary files cleaned up', '2014-04-16 20:49:34', 'HouseKeeper'),
(277, 133, 'MGGolf temporary files cleaned up', '2014-04-16 20:58:10', 'HouseKeeper'),
(278, 133, 'MGGolf temporary files cleaned up', '2014-04-16 21:44:31', 'HouseKeeper'),
(279, 133, 'MGGolf temporary files cleaned up', '2014-04-16 21:59:34', 'HouseKeeper'),
(280, 133, 'MGGolf temporary files cleaned up', '2014-04-16 22:05:20', 'HouseKeeper'),
(281, 133, 'MGGolf temporary files cleaned up', '2014-04-16 22:15:39', 'HouseKeeper'),
(282, 133, 'MGGolf temporary files cleaned up', '2014-04-16 22:25:17', 'HouseKeeper'),
(283, 133, 'MGGolf temporary files cleaned up', '2014-04-17 00:03:16', 'HouseKeeper'),
(284, 133, 'MGGolf temporary files cleaned up', '2014-04-17 00:40:50', 'HouseKeeper'),
(285, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 10\nElapsed time in seconds: 14.542', '2014-04-17 00:47:18', 'GolfAdminServlet'),
(286, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 10\nElapsed time in seconds: 72.768', '2014-04-17 00:47:19', 'GolfAdminServlet'),
(287, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 10\nElapsed time in seconds: 46.851', '2014-04-17 00:48:34', 'GolfAdminServlet'),
(288, 133, 'MGGolf temporary files cleaned up', '2014-04-17 00:49:02', 'HouseKeeper'),
(289, 133, 'MGGolf temporary files cleaned up', '2014-04-17 00:57:12', 'HouseKeeper'),
(290, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 10\nElapsed time in seconds: 214.752', '2014-04-17 00:58:20', 'GolfAdminServlet'),
(291, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 10\nElapsed time in seconds: 154.831', '2014-04-17 00:58:20', 'GolfAdminServlet'),
(292, 133, 'MGGolf temporary files cleaned up', '2014-04-17 00:58:31', 'HouseKeeper'),
(293, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 10\nElapsed time in seconds: 746.699', '2014-04-17 01:11:10', 'GolfAdminServlet'),
(294, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 10\nElapsed time in seconds: 463.076', '2014-04-17 01:11:27', 'GolfAdminServlet'),
(295, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 10\nElapsed time in seconds: 703.151', '2014-04-17 01:11:27', 'GolfAdminServlet'),
(296, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 10\nElapsed time in seconds: 643.126', '2014-04-17 01:11:27', 'GolfAdminServlet'),
(297, 133, 'MGGolf temporary files cleaned up', '2014-04-17 01:41:17', 'HouseKeeper'),
(298, 133, 'MGGolf temporary files cleaned up', '2014-04-17 01:57:42', 'HouseKeeper'),
(299, 133, 'MGGolf temporary files cleaned up', '2014-04-17 02:21:17', 'HouseKeeper'),
(300, 133, 'MGGolf temporary files cleaned up', '2014-04-17 02:36:19', 'HouseKeeper'),
(301, 133, 'MGGolf temporary files cleaned up', '2014-04-17 03:04:50', 'HouseKeeper'),
(302, 133, 'MGGolf temporary files cleaned up', '2014-04-17 03:12:26', 'HouseKeeper'),
(303, 133, 'MGGolf temporary files cleaned up', '2014-04-17 03:19:52', 'HouseKeeper'),
(304, 133, 'MGGolf temporary files cleaned up', '2014-04-17 03:26:46', 'HouseKeeper'),
(305, 133, 'MGGolf temporary files cleaned up', '2014-04-17 03:44:15', 'HouseKeeper'),
(306, 133, 'MGGolf temporary files cleaned up', '2014-04-17 03:54:07', 'HouseKeeper'),
(307, 133, 'MGGolf temporary files cleaned up', '2014-04-17 03:57:02', 'HouseKeeper'),
(308, 133, 'MGGolf temporary files cleaned up', '2014-04-17 04:05:40', 'HouseKeeper'),
(309, 133, 'MGGolf temporary files cleaned up', '2014-04-17 04:10:55', 'HouseKeeper'),
(310, 133, 'MGGolf temporary files cleaned up', '2014-04-17 04:22:26', 'HouseKeeper'),
(311, 133, 'MGGolf temporary files cleaned up', '2014-04-17 04:38:31', 'HouseKeeper'),
(312, 133, 'MGGolf temporary files cleaned up', '2014-04-17 11:16:21', 'HouseKeeper'),
(313, 133, 'MGGolf temporary files cleaned up', '2014-04-17 15:19:44', 'HouseKeeper'),
(314, 133, 'MGGolf temporary files cleaned up', '2014-04-17 16:15:58', 'HouseKeeper'),
(315, 133, 'MGGolf temporary files cleaned up', '2014-04-17 16:20:56', 'HouseKeeper'),
(316, 133, 'MGGolf temporary files cleaned up', '2014-04-17 16:44:34', 'HouseKeeper'),
(317, 133, 'MGGolf temporary files cleaned up', '2014-04-17 17:21:09', 'HouseKeeper'),
(318, 133, 'MGGolf temporary files cleaned up', '2014-04-17 18:50:16', 'HouseKeeper'),
(319, 133, 'MGGolf temporary files cleaned up', '2014-04-17 18:58:00', 'HouseKeeper'),
(320, 133, 'MGGolf temporary files cleaned up', '2014-04-17 18:58:10', 'HouseKeeper'),
(321, 133, 'MGGolf temporary files cleaned up', '2014-04-17 18:58:21', 'HouseKeeper'),
(322, 133, 'MGGolf temporary files cleaned up', '2014-04-17 19:05:15', 'HouseKeeper'),
(323, 133, 'MGGolf temporary files cleaned up', '2014-04-17 20:19:09', 'HouseKeeper'),
(324, 8, 'Database related error\nFailed to add Tournament player\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.TourneyPlayerScoreDTO\nMethod: <init>\nLine Number: 57\n', '2014-04-18 04:26:44', 'GolfAdminServlet'),
(325, 133, 'MGGolf temporary files cleaned up', '2014-04-18 04:31:25', 'HouseKeeper'),
(326, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 6\nElapsed time in seconds: 51.924', '2014-04-18 06:23:47', 'GolfAdminServlet'),
(327, 133, 'MGGolf temporary files cleaned up', '2014-04-18 06:24:14', 'HouseKeeper'),
(328, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 6\nElapsed time in seconds: 294.442', '2014-04-18 06:26:50', 'GolfAdminServlet'),
(329, 133, 'MGGolf temporary files cleaned up', '2014-04-18 06:29:57', 'HouseKeeper'),
(330, 133, 'MGGolf temporary files cleaned up', '2014-04-18 06:46:55', 'HouseKeeper'),
(331, 133, 'MGGolf temporary files cleaned up', '2014-04-18 12:57:30', 'HouseKeeper'),
(332, 133, 'MGGolf temporary files cleaned up', '2014-04-18 13:09:45', 'HouseKeeper'),
(333, 133, 'MGGolf temporary files cleaned up', '2014-04-18 13:28:42', 'HouseKeeper'),
(334, 111, 'Servlet took more than 5 seconds to process request\nRequest type is 38\nElapsed time in seconds: 6.024', '2014-04-18 13:40:56', 'GolfAdminServlet'),
(335, 133, 'MGGolf temporary files cleaned up', '2014-04-18 13:44:39', 'HouseKeeper'),
(336, 133, 'MGGolf temporary files cleaned up', '2014-04-18 14:42:30', 'HouseKeeper'),
(337, 133, 'MGGolf temporary files cleaned up', '2014-04-18 14:55:47', 'HouseKeeper'),
(338, 133, 'MGGolf temporary files cleaned up', '2014-04-18 15:10:39', 'HouseKeeper'),
(339, 8, 'Database related error\nUnable to add update winner flag\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: updateWinnerFlag\nLine Number: 594\n', '2014-04-18 15:28:08', 'GolfAdminServlet'),
(340, 133, 'MGGolf temporary files cleaned up', '2014-04-18 15:36:25', 'HouseKeeper'),
(341, 8, 'Database related error\nUnable to add update winner flag\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: updateWinnerFlag\nLine Number: 595\n', '2014-04-18 15:46:59', 'GolfAdminServlet'),
(342, 8, 'Database related error\nUnable to add update winner flag\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: updateWinnerFlag\nLine Number: 595\n', '2014-04-18 15:47:42', 'GolfAdminServlet'),
(343, 133, 'MGGolf temporary files cleaned up', '2014-04-18 17:03:36', 'HouseKeeper'),
(344, 133, 'MGGolf temporary files cleaned up', '2014-04-18 17:13:20', 'HouseKeeper'),
(345, 133, 'MGGolf temporary files cleaned up', '2014-04-18 19:48:14', 'HouseKeeper'),
(346, 8, 'Database related error\nFailed to get GolfGroup\nYou have attempted to set a parameter value using a name of id that does not exist in the query string SELECT l.player.playerID, count(l) FROM LeaderBoard l where l.tournament.golfGroup.golfGroupID = :ID group by l.player.playerID.\n\njava.lang.IllegalArgumentException: You have attempted to set a parameter value using a name of id that does not exist in the query string SELECT l.player.playerID, count(l) FROM LeaderBoard l where l.tournament.golfGroup.golfGroupID = :ID group by l.player.playerID.\n\nClass: org.eclipse.persistence.internal.jpa.QueryImpl\nMethod: setParameterInternal\nLine Number: 927\n', '2014-04-18 19:56:15', 'GolfAdminServlet'),
(347, 133, 'MGGolf temporary files cleaned up', '2014-04-18 19:59:10', 'HouseKeeper'),
(348, 133, 'MGGolf temporary files cleaned up', '2014-04-18 19:59:16', 'HouseKeeper'),
(349, 8, 'Database related error\nFailed to add Tournament player\nNamedQuery of name: Leaderboard.findByPlayerTourney not found.\n\njava.lang.IllegalArgumentException: NamedQuery of name: Leaderboard.findByPlayerTourney not found.\n\nClass: org.eclipse.persistence.internal.jpa.QueryImpl\nMethod: getDatabaseQueryInternal\nLine Number: 350\n', '2014-04-18 20:08:07', 'GolfAdminServlet');
INSERT INTO `errorStore` (`errorStoreID`, `statusCode`, `message`, `dateOccured`, `origin`) VALUES
(350, 8, 'Database related error\nFailed to add Tournament player\nNamedQuery of name: Leaderboard.findByPlayerTourney not found.\n\njava.lang.IllegalArgumentException: NamedQuery of name: Leaderboard.findByPlayerTourney not found.\n\nClass: org.eclipse.persistence.internal.jpa.QueryImpl\nMethod: getDatabaseQueryInternal\nLine Number: 350\n', '2014-04-18 20:09:45', 'GolfAdminServlet'),
(351, 8, 'Database related error\nFailed to add Tournament player\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.LeaderBoardDTO\nMethod: setBasics\nLine Number: 50\n', '2014-04-18 20:11:34', 'GolfAdminServlet'),
(352, 133, 'MGGolf temporary files cleaned up', '2014-04-18 20:16:01', 'HouseKeeper'),
(353, 133, 'MGGolf temporary files cleaned up', '2014-04-18 21:52:36', 'HouseKeeper'),
(354, 133, 'MGGolf temporary files cleaned up', '2014-04-18 22:10:35', 'HouseKeeper'),
(355, 133, 'MGGolf temporary files cleaned up', '2014-04-18 22:29:43', 'HouseKeeper'),
(356, 133, 'MGGolf temporary files cleaned up', '2014-04-18 22:48:38', 'HouseKeeper'),
(357, 133, 'MGGolf temporary files cleaned up', '2014-04-18 22:58:10', 'HouseKeeper'),
(358, 133, 'MGGolf temporary files cleaned up', '2014-04-18 22:59:40', 'HouseKeeper'),
(359, 133, 'MGGolf temporary files cleaned up', '2014-04-18 23:07:12', 'HouseKeeper'),
(360, 133, 'MGGolf temporary files cleaned up', '2014-04-18 23:14:05', 'HouseKeeper'),
(361, 133, 'MGGolf temporary files cleaned up', '2014-04-18 23:25:43', 'HouseKeeper'),
(362, 133, 'MGGolf temporary files cleaned up', '2014-04-18 23:29:28', 'HouseKeeper'),
(363, 133, 'MGGolf temporary files cleaned up', '2014-04-18 23:36:20', 'HouseKeeper'),
(364, 133, 'MGGolf temporary files cleaned up', '2014-04-18 23:41:06', 'HouseKeeper'),
(365, 133, 'MGGolf temporary files cleaned up', '2014-04-18 23:52:51', 'HouseKeeper'),
(366, 133, 'MGGolf temporary files cleaned up', '2014-04-18 23:59:21', 'HouseKeeper'),
(367, 133, 'MGGolf temporary files cleaned up', '2014-04-19 00:03:16', 'HouseKeeper'),
(368, 133, 'MGGolf temporary files cleaned up', '2014-04-19 00:11:06', 'HouseKeeper'),
(369, 133, 'MGGolf temporary files cleaned up', '2014-04-19 00:15:00', 'HouseKeeper'),
(370, 133, 'MGGolf temporary files cleaned up', '2014-04-19 00:24:37', 'HouseKeeper'),
(371, 133, 'MGGolf temporary files cleaned up', '2014-04-19 00:40:59', 'HouseKeeper'),
(372, 133, 'MGGolf temporary files cleaned up', '2014-04-19 00:46:23', 'HouseKeeper'),
(373, 8, 'Database related error\nFailed to add Tournament player\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: setAgeGroup\nLine Number: 1616\n', '2014-04-19 01:00:21', 'GolfAdminServlet'),
(374, 8, 'Database related error\nFailed to add Tournament player\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: setAgeGroup\nLine Number: 1617\n', '2014-04-19 01:04:59', 'GolfAdminServlet'),
(375, 133, 'MGGolf temporary files cleaned up', '2014-04-19 01:07:14', 'HouseKeeper'),
(376, 8, 'Database related error\nFailed to add Tournament player\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: setAgeGroup\nLine Number: 1619\n', '2014-04-19 01:07:50', 'GolfAdminServlet'),
(377, 8, 'Database related error\nFailed to add Tournament player\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: setAgeGroup\nLine Number: 1620\n', '2014-04-19 01:10:46', 'GolfAdminServlet'),
(378, 8, 'Database related error\nFailed to add Tournament player\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: setAgeGroup\nLine Number: 1620\n', '2014-04-19 01:14:18', 'GolfAdminServlet'),
(379, 8, 'Database related error\nFailed to add Tournament player\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: setAgeGroup\nLine Number: 1620\n', '2014-04-19 01:14:33', 'GolfAdminServlet'),
(380, 133, 'MGGolf temporary files cleaned up', '2014-04-19 01:17:12', 'HouseKeeper'),
(381, 8, 'Database related error\nFailed to add Tournament player\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: setAgeGroup\nLine Number: 1620\n', '2014-04-19 01:28:13', 'GolfAdminServlet'),
(382, 133, 'MGGolf temporary files cleaned up', '2014-04-19 01:30:53', 'HouseKeeper'),
(383, 133, 'MGGolf temporary files cleaned up', '2014-04-19 01:37:36', 'HouseKeeper'),
(384, 133, 'MGGolf temporary files cleaned up', '2014-04-19 05:39:50', 'HouseKeeper'),
(385, 8, 'Database related error\nFailed to add Tournament player\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: setAgeGroup\nLine Number: 1620\n', '2014-04-19 06:04:40', 'GolfAdminServlet'),
(386, 133, 'MGGolf temporary files cleaned up', '2014-04-19 06:13:04', 'HouseKeeper'),
(387, 133, 'MGGolf temporary files cleaned up', '2014-04-19 06:19:46', 'HouseKeeper'),
(388, 133, 'MGGolf temporary files cleaned up', '2014-04-19 13:07:27', 'HouseKeeper'),
(389, 133, 'MGGolf temporary files cleaned up', '2014-04-19 14:20:06', 'HouseKeeper'),
(390, 133, 'MGGolf temporary files cleaned up', '2014-04-19 14:52:47', 'HouseKeeper'),
(391, 133, 'MGGolf temporary files cleaned up', '2014-04-19 15:04:45', 'HouseKeeper'),
(392, 133, 'MGGolf temporary files cleaned up', '2014-04-19 15:14:56', 'HouseKeeper'),
(393, 133, 'MGGolf temporary files cleaned up', '2014-04-19 15:20:41', 'HouseKeeper'),
(394, 133, 'MGGolf temporary files cleaned up', '2014-04-19 15:27:38', 'HouseKeeper'),
(395, 133, 'MGGolf temporary files cleaned up', '2014-04-19 15:54:11', 'HouseKeeper'),
(396, 133, 'MGGolf temporary files cleaned up', '2014-04-19 19:54:11', 'HouseKeeper'),
(397, 133, 'MGGolf temporary files cleaned up', '2014-04-19 21:53:36', 'HouseKeeper'),
(398, 133, 'MGGolf temporary files cleaned up', '2014-04-19 21:56:49', 'HouseKeeper'),
(399, 133, 'MGGolf temporary files cleaned up', '2014-04-19 21:58:34', 'HouseKeeper'),
(400, 133, 'MGGolf temporary files cleaned up', '2014-04-19 21:59:07', 'HouseKeeper'),
(401, 133, 'MGGolf temporary files cleaned up', '2014-04-19 22:09:26', 'HouseKeeper'),
(402, 133, 'MGGolf temporary files cleaned up', '2014-04-20 09:32:52', 'HouseKeeper'),
(403, 133, 'MGGolf temporary files cleaned up', '2014-04-20 09:32:53', 'HouseKeeper'),
(404, 133, 'MGGolf temporary files cleaned up', '2014-04-20 10:09:26', 'HouseKeeper'),
(405, 133, 'MGGolf temporary files cleaned up', '2014-04-20 10:45:17', 'HouseKeeper'),
(406, 133, 'MGGolf temporary files cleaned up', '2014-04-20 10:56:29', 'HouseKeeper'),
(407, 133, 'MGGolf temporary files cleaned up', '2014-04-20 11:15:37', 'HouseKeeper'),
(408, 8, 'Database related error\nFailed to get Tourney players\nException [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: java.sql.SQLException: Value ''0000-00-00 00:00:00'' can not be represented as java.sql.Timestamp\nError Code: 0\nCall: SELECT t2.tourneyScoreByRoundID, t2.golfRound, t2.holesPerRound, t2.par, t2.score1, t2.score10, t2.score11, t2.score12, t2.score13, t2.score14, t2.score15, t2.score16, t2.score17, t2.score18, t2.score2, t2.score3, t2.score4, t2.score5, t2.score6, t2.score7, t2.score8, t2.score9, t2.tee, t2.teeTime, t2.totalScore, t2.tournamentIDx, t2.clubCourseID, t2.leaderBoardID FROM player t0, tourneyScoreByRound t2, leaderBoard t1 WHERE ((t1.tournamentID = ?) AND ((t1.leaderBoardID = t2.leaderBoardID) AND (t0.playerID = t1.playerID))) ORDER BY t0.playerID, t2.golfRound\n	bind => [1 parameter bound]\nQuery: ReadAllQuery(name="TourneyScoreByRound.getByTourney" referenceClass=TourneyScoreByRound sql="SELECT t2.tourneyScoreByRoundID, t2.golfRound, t2.holesPerRound, t2.par, t2.score1, t2.score10, t2.score11, t2.score12, t2.score13, t2.score14, t2.score15, t2.score16, t2.score17, t2.score18, t2.score2, t2.score3, t2.score4, t2.score5, t2.score6, t2.score7, t2.score8, t2.score9, t2.tee, t2.teeTime, t2.totalScore, t2.tournamentIDx, t2.clubCourseID, t2.leaderBoardID FROM player t0, tourneyScoreByRound t2, leaderBoard t1 WHERE ((t1.tournamentID = ?) AND ((t1.leaderBoardID = t2.leaderBoardID) AND (t0.playerID = t1.playerID))) ORDER BY t0.playerID, t2.golfRound")\n\njavax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.0.v20130507-3faac2b): org.eclipse.persistence.exceptions.DatabaseException\nInternal Exception: java.sql.SQLException: Value ''0000-00-00 00:00:00'' can not be represented as java.sql.Timestamp\nError Code: 0\nCall: SELECT t2.tourneyScoreByRoundID, t2.golfRound, t2.holesPerRound, t2.par, t2.score1, t2.score10, t2.score11, t2.score12, t2.score13, t2.score14, t2.score15, t2.score16, t2.score17, t2.score18, t2.score2, t2.score3, t2.score4, t2.score5, t2.score6, t2.score7, t2.score8, t2.score9, t2.tee, t2.teeTime, t2.totalScore, t2.tournamentIDx, t2.clubCourseID, t2.leaderBoardID FROM player t0, tourneyScoreByRound t2, leaderBoard t1 WHERE ((t1.tournamentID = ?) AND ((t1.leaderBoardID = t2.leaderBoardID) AND (t0.playerID = t1.playerID))) ORDER BY t0.playerID, t2.golfRound\n	bind => [1 parameter bound]\nQuery: ReadAllQuery(name="TourneyScoreByRound.getByTourney" referenceClass=TourneyScoreByRound sql="SELECT t2.tourneyScoreByRoundID, t2.golfRound, t2.holesPerRound, t2.par, t2.score1, t2.score10, t2.score11, t2.score12, t2.score13, t2.score14, t2.score15, t2.score16, t2.score17, t2.score18, t2.score2, t2.score3, t2.score4, t2.score5, t2.score6, t2.score7, t2.score8, t2.score9, t2.tee, t2.teeTime, t2.totalScore, t2.tournamentIDx, t2.clubCourseID, t2.leaderBoardID FROM player t0, tourneyScoreByRound t2, leaderBoard t1 WHERE ((t1.tournamentID = ?) AND ((t1.leaderBoardID = t2.leaderBoardID) AND (t0.playerID = t1.playerID))) ORDER BY t0.playerID, t2.golfRound")\n\nClass: org.eclipse.persistence.internal.jpa.QueryImpl\nMethod: getDetailedException\nLine Number: 377\n', '2014-04-20 11:55:44', 'GolfAdminServlet'),
(409, 133, 'MGGolf temporary files cleaned up', '2014-04-20 12:11:17', 'HouseKeeper'),
(410, 133, 'MGGolf temporary files cleaned up', '2014-04-20 16:11:17', 'HouseKeeper'),
(411, 133, 'MGGolf temporary files cleaned up', '2014-04-20 20:11:17', 'HouseKeeper'),
(412, 133, 'MGGolf temporary files cleaned up', '2014-04-21 00:11:17', 'HouseKeeper'),
(413, 133, 'MGGolf temporary files cleaned up', '2014-04-21 09:42:02', 'HouseKeeper'),
(414, 133, 'MGGolf temporary files cleaned up', '2014-04-21 09:48:52', 'HouseKeeper'),
(415, 133, 'MGGolf temporary files cleaned up', '2014-04-21 10:09:18', 'HouseKeeper'),
(416, 133, 'MGGolf temporary files cleaned up', '2014-04-21 10:16:45', 'HouseKeeper'),
(417, 133, 'MGGolf temporary files cleaned up', '2014-04-21 10:24:29', 'HouseKeeper'),
(418, 133, 'MGGolf temporary files cleaned up', '2014-04-21 13:15:48', 'HouseKeeper'),
(419, 133, 'MGGolf temporary files cleaned up', '2014-04-21 17:15:47', 'HouseKeeper'),
(420, 133, 'MGGolf temporary files cleaned up', '2014-04-21 17:51:10', 'HouseKeeper'),
(421, 133, 'MGGolf temporary files cleaned up', '2014-04-21 17:57:20', 'HouseKeeper'),
(422, 133, 'MGGolf temporary files cleaned up', '2014-04-21 17:58:06', 'HouseKeeper'),
(423, 133, 'MGGolf temporary files cleaned up', '2014-04-21 17:58:08', 'HouseKeeper'),
(424, 133, 'MGGolf temporary files cleaned up', '2014-04-21 17:59:47', 'HouseKeeper'),
(425, 133, 'MGGolf temporary files cleaned up', '2014-04-21 18:11:57', 'HouseKeeper'),
(426, 133, 'MGGolf temporary files cleaned up', '2014-04-21 18:19:40', 'HouseKeeper'),
(427, 133, 'MGGolf temporary files cleaned up', '2014-04-21 18:28:01', 'HouseKeeper'),
(428, 133, 'MGGolf temporary files cleaned up', '2014-04-21 18:35:12', 'HouseKeeper'),
(429, 133, 'MGGolf temporary files cleaned up', '2014-04-21 18:45:51', 'HouseKeeper'),
(430, 133, 'MGGolf temporary files cleaned up', '2014-04-21 18:52:54', 'HouseKeeper'),
(431, 133, 'MGGolf temporary files cleaned up', '2014-04-21 18:57:03', 'HouseKeeper'),
(432, 133, 'MGGolf temporary files cleaned up', '2014-04-21 18:57:21', 'HouseKeeper'),
(433, 133, 'MGGolf temporary files cleaned up', '2014-04-21 19:03:10', 'HouseKeeper'),
(434, 133, 'MGGolf temporary files cleaned up', '2014-04-21 19:09:31', 'HouseKeeper'),
(435, 133, 'MGGolf temporary files cleaned up', '2014-04-21 19:12:53', 'HouseKeeper'),
(436, 133, 'MGGolf temporary files cleaned up', '2014-04-21 19:16:16', 'HouseKeeper'),
(437, 133, 'MGGolf temporary files cleaned up', '2014-04-21 20:21:57', 'HouseKeeper'),
(438, 133, 'MGGolf temporary files cleaned up', '2014-04-21 20:26:17', 'HouseKeeper'),
(439, 133, 'MGGolf temporary files cleaned up', '2014-04-21 20:41:28', 'HouseKeeper'),
(440, 133, 'MGGolf temporary files cleaned up', '2014-04-21 20:58:33', 'HouseKeeper'),
(441, 133, 'MGGolf temporary files cleaned up', '2014-04-21 20:58:48', 'HouseKeeper'),
(442, 133, 'MGGolf temporary files cleaned up', '2014-04-21 21:11:05', 'HouseKeeper'),
(443, 133, 'MGGolf temporary files cleaned up', '2014-04-21 21:15:29', 'HouseKeeper'),
(444, 133, 'MGGolf temporary files cleaned up', '2014-04-22 01:15:29', 'HouseKeeper'),
(445, 133, 'MGGolf temporary files cleaned up', '2014-04-22 02:19:55', 'HouseKeeper'),
(446, 133, 'MGGolf temporary files cleaned up', '2014-04-22 02:32:25', 'HouseKeeper'),
(447, 133, 'MGGolf temporary files cleaned up', '2014-04-22 02:39:11', 'HouseKeeper'),
(448, 133, 'MGGolf temporary files cleaned up', '2014-04-22 02:46:31', 'HouseKeeper'),
(449, 133, 'MGGolf temporary files cleaned up', '2014-04-22 02:57:59', 'HouseKeeper'),
(450, 133, 'MGGolf temporary files cleaned up', '2014-04-22 10:37:22', 'HouseKeeper'),
(451, 133, 'MGGolf temporary files cleaned up', '2014-04-22 10:58:00', 'HouseKeeper'),
(452, 133, 'MGGolf temporary files cleaned up', '2014-04-22 12:37:03', 'HouseKeeper'),
(453, 8, 'Database related error\nFailed to add Tournament player\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.dto.LeaderBoardDTO\nMethod: setBasics\nLine Number: 51\n', '2014-04-22 12:38:25', 'GolfAdminServlet'),
(454, 133, 'MGGolf temporary files cleaned up', '2014-04-22 12:42:58', 'HouseKeeper'),
(455, 133, 'MGGolf temporary files cleaned up', '2014-04-22 13:52:51', 'HouseKeeper'),
(456, 8, 'Database related error\nFailed to add Tournament player\ncom.boha.golfkids.util.DataException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: addTournamentScoreByRound\nLine Number: 574\n', '2014-04-22 13:56:40', 'GolfAdminServlet'),
(457, 133, 'MGGolf temporary files cleaned up', '2014-04-22 13:59:21', 'HouseKeeper'),
(458, 133, 'MGGolf temporary files cleaned up', '2014-04-22 14:03:16', 'HouseKeeper'),
(459, 133, 'MGGolf temporary files cleaned up', '2014-04-22 14:12:18', 'HouseKeeper'),
(460, 133, 'MGGolf temporary files cleaned up', '2014-04-22 14:24:31', 'HouseKeeper'),
(461, 133, 'MGGolf temporary files cleaned up', '2014-04-22 14:33:12', 'HouseKeeper'),
(462, 133, 'MGGolf temporary files cleaned up', '2014-04-22 17:08:12', 'HouseKeeper'),
(463, 133, 'MGGolf temporary files cleaned up', '2014-04-22 21:08:11', 'HouseKeeper'),
(464, 133, 'MGGolf temporary files cleaned up', '2014-04-23 08:57:55', 'HouseKeeper'),
(465, 133, 'MGGolf temporary files cleaned up', '2014-04-23 09:31:21', 'HouseKeeper'),
(466, 133, 'MGGolf temporary files cleaned up', '2014-04-23 09:57:09', 'HouseKeeper'),
(467, 8, 'Database related error\nFailed to update tournament\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: updateTournament\nLine Number: 90\n', '2014-04-23 10:31:20', 'GolfAdminServlet'),
(468, 133, 'MGGolf temporary files cleaned up', '2014-04-23 13:00:10', 'HouseKeeper'),
(469, 133, 'MGGolf temporary files cleaned up', '2014-04-23 17:00:13', 'HouseKeeper'),
(470, 8, 'Database related error\nUnable to add update winner flag\njava.lang.NullPointerException\n\nClass: com.boha.golfkids.util.DataUtil\nMethod: updateWinnerFlag\nLine Number: 543\n', '2014-04-23 18:49:03', 'GolfAdminServlet'),
(471, 133, 'MGGolf temporary files cleaned up', '2014-04-23 19:13:44', 'HouseKeeper'),
(472, 133, 'MGGolf temporary files cleaned up', '2014-04-23 19:58:36', 'HouseKeeper'),
(473, 133, 'MGGolf temporary files cleaned up', '2014-04-23 20:11:02', 'HouseKeeper'),
(474, 133, 'MGGolf temporary files cleaned up', '2014-04-23 20:19:11', 'HouseKeeper'),
(475, 133, 'MGGolf temporary files cleaned up', '2014-04-24 00:19:11', 'HouseKeeper'),
(476, 133, 'MGGolf temporary files cleaned up', '2014-04-24 04:19:11', 'HouseKeeper'),
(477, 133, 'MGGolf temporary files cleaned up', '2014-04-24 13:49:48', 'HouseKeeper'),
(478, 133, 'MGGolf temporary files cleaned up', '2014-04-24 13:49:49', 'HouseKeeper'),
(479, 133, 'MGGolf temporary files cleaned up', '2014-04-24 16:19:11', 'HouseKeeper'),
(480, 133, 'MGGolf temporary files cleaned up', '2014-04-24 20:19:11', 'HouseKeeper'),
(481, 133, 'MGGolf temporary files cleaned up', '2014-04-24 20:30:05', 'HouseKeeper'),
(482, 133, 'MGGolf temporary files cleaned up', '2014-04-24 20:43:10', 'HouseKeeper'),
(483, 133, 'MGGolf temporary files cleaned up', '2014-04-24 20:48:31', 'HouseKeeper'),
(484, 133, 'MGGolf temporary files cleaned up', '2014-04-24 21:14:15', 'HouseKeeper'),
(485, 133, 'MGGolf temporary files cleaned up', '2014-04-25 01:14:15', 'HouseKeeper'),
(486, 133, 'MGGolf temporary files cleaned up', '2014-04-25 03:57:16', 'HouseKeeper'),
(487, 133, 'MGGolf temporary files cleaned up', '2014-04-25 03:58:29', 'HouseKeeper'),
(488, 133, 'MGGolf temporary files cleaned up', '2014-04-25 04:03:35', 'HouseKeeper');

-- --------------------------------------------------------

--
-- Table structure for table `golfGroup`
--

CREATE TABLE `golfGroup` (
  `golfGroupID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `golfGroupName` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `cellphone` varchar(25) DEFAULT NULL,
  `dateRegistered` datetime NOT NULL,
  `countryID` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`golfGroupID`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `ixctrygrp` (`countryID`,`golfGroupName`),
  KEY `fkctry` (`countryID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `golfGroup`
--

INSERT INTO `golfGroup` (`golfGroupID`, `golfGroupName`, `email`, `cellphone`, `dateRegistered`, `countryID`) VALUES
(1, 'Malenga Kids Golf Group', 'malengadev@gmail.com', '852222222', '2013-02-15 02:10:40', 5),
(8, 'Pecanwood Hack Artists', 'aubrey.malabie@gmail.com', '0712193543', '2014-04-09 19:45:50', NULL),
(10, 'Woodmead Golfers', 'rulani@mlab.co.za', '0896663232', '2014-04-09 20:01:31', 1);

-- --------------------------------------------------------

--
-- Table structure for table `golfGroupParent`
--

CREATE TABLE `golfGroupParent` (
  `golfGroupParentID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `golfGroupID` int(11) unsigned NOT NULL,
  `parentID` int(11) unsigned NOT NULL,
  `dateRegistered` datetime NOT NULL,
  PRIMARY KEY (`golfGroupParentID`),
  UNIQUE KEY `ggix002` (`golfGroupID`,`parentID`),
  KEY `fkp003` (`parentID`),
  KEY `fkp004` (`golfGroupID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=142 ;

--
-- Dumping data for table `golfGroupParent`
--

INSERT INTO `golfGroupParent` (`golfGroupParentID`, `golfGroupID`, `parentID`, `dateRegistered`) VALUES
(71, 10, 71, '2013-08-18 16:53:35'),
(72, 10, 72, '2013-08-18 16:53:35'),
(73, 10, 73, '2013-08-18 16:53:36'),
(74, 10, 74, '2013-08-18 16:53:37'),
(75, 10, 75, '2013-08-18 16:53:37'),
(76, 10, 76, '2013-08-18 16:53:38'),
(77, 10, 77, '2013-08-18 16:53:38'),
(78, 10, 78, '2013-08-18 16:53:38'),
(79, 10, 79, '2013-08-18 16:53:39'),
(80, 10, 80, '2013-08-18 16:53:39'),
(81, 10, 81, '2013-08-18 16:53:39'),
(82, 10, 82, '2013-08-18 16:53:40'),
(83, 10, 83, '2013-08-18 16:53:40'),
(84, 10, 84, '2013-08-18 16:53:40'),
(85, 10, 85, '2013-08-18 16:53:40'),
(86, 10, 86, '2013-08-18 16:53:41'),
(87, 10, 87, '2013-08-18 16:53:41'),
(88, 10, 88, '2013-08-18 16:53:41'),
(89, 10, 89, '2013-08-18 16:53:41'),
(90, 10, 90, '2013-08-18 16:53:42'),
(91, 10, 91, '2013-08-18 16:53:42'),
(92, 10, 92, '2013-08-18 16:53:42'),
(93, 10, 93, '2013-08-18 16:53:43'),
(94, 10, 94, '2013-08-18 16:53:43'),
(95, 10, 95, '2013-08-18 16:53:43'),
(96, 10, 96, '2013-08-18 16:53:43'),
(97, 10, 97, '2013-08-18 16:53:44'),
(98, 10, 98, '2013-08-18 16:53:44'),
(99, 10, 99, '2013-08-18 16:53:44'),
(100, 10, 100, '2013-08-18 16:53:44'),
(101, 10, 101, '2013-08-18 16:53:44'),
(102, 10, 102, '2013-08-18 16:53:44'),
(103, 10, 103, '2013-08-18 16:53:44'),
(104, 10, 104, '2013-08-18 16:53:44'),
(105, 10, 105, '2013-08-18 16:53:44'),
(106, 10, 106, '2013-08-18 16:53:44'),
(107, 10, 107, '2013-08-18 16:53:44'),
(108, 10, 108, '2013-08-18 16:53:44'),
(109, 10, 109, '2013-08-18 16:53:45'),
(110, 10, 110, '2013-08-18 16:53:45'),
(111, 10, 111, '2013-08-18 16:53:45'),
(112, 10, 112, '2013-08-18 16:53:45'),
(113, 10, 113, '2013-08-18 16:53:45'),
(114, 10, 114, '2013-08-18 16:53:45'),
(115, 10, 115, '2013-08-18 16:53:45'),
(116, 10, 116, '2013-08-18 16:53:45'),
(117, 10, 117, '2013-08-18 16:53:45'),
(118, 10, 118, '2013-08-18 16:53:46'),
(119, 10, 119, '2013-08-18 16:53:46'),
(120, 10, 120, '2013-08-18 16:53:46'),
(121, 10, 121, '2013-08-18 16:53:46'),
(122, 10, 122, '2013-08-18 16:53:46'),
(123, 10, 123, '2013-08-18 16:53:46'),
(124, 10, 124, '2013-08-18 16:53:46'),
(125, 10, 125, '2013-08-18 16:53:46'),
(126, 10, 126, '2013-08-18 16:53:46'),
(127, 10, 127, '2013-08-18 16:53:46'),
(128, 10, 128, '2013-08-18 16:53:46'),
(129, 10, 129, '2013-08-18 16:53:46'),
(130, 10, 130, '2013-08-18 16:53:46'),
(131, 10, 131, '2013-08-18 16:53:46'),
(132, 10, 132, '2013-08-18 16:53:46'),
(133, 10, 133, '2013-08-18 16:53:46'),
(134, 10, 134, '2013-08-18 16:53:46'),
(135, 10, 135, '2013-08-18 16:53:46'),
(136, 10, 136, '2013-08-18 16:53:46'),
(137, 10, 137, '2013-08-18 16:53:46'),
(138, 10, 138, '2013-08-18 16:53:46'),
(139, 10, 139, '2013-08-18 16:53:46'),
(140, 10, 140, '2013-08-18 16:53:46'),
(141, 10, 143, '2014-04-15 12:01:23');

-- --------------------------------------------------------

--
-- Table structure for table `golfGroupPlayer`
--

CREATE TABLE `golfGroupPlayer` (
  `golfGroupPlayerID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `golfGroupID` int(11) unsigned NOT NULL,
  `playerID` int(11) unsigned NOT NULL,
  `dateRegistered` datetime NOT NULL,
  PRIMARY KEY (`golfGroupPlayerID`),
  UNIQUE KEY `idx005` (`golfGroupID`,`playerID`),
  KEY `fkg007` (`golfGroupID`),
  KEY `fkg008` (`playerID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=74 ;

--
-- Dumping data for table `golfGroupPlayer`
--

INSERT INTO `golfGroupPlayer` (`golfGroupPlayerID`, `golfGroupID`, `playerID`, `dateRegistered`) VALUES
(1, 10, 71, '2013-08-18 16:53:35'),
(2, 10, 72, '2013-08-18 16:53:35'),
(3, 10, 73, '2013-08-18 16:53:36'),
(4, 10, 74, '2013-08-18 16:53:37'),
(5, 10, 75, '2013-08-18 16:53:37'),
(6, 10, 76, '2013-08-18 16:53:38'),
(7, 10, 77, '2013-08-18 16:53:38'),
(8, 10, 78, '2013-08-18 16:53:38'),
(9, 10, 79, '2013-08-18 16:53:39'),
(10, 10, 80, '2013-08-18 16:53:39'),
(11, 10, 81, '2013-08-18 16:53:39'),
(12, 10, 82, '2013-08-18 16:53:40'),
(13, 10, 83, '2013-08-18 16:53:40'),
(14, 10, 84, '2013-08-18 16:53:40'),
(15, 10, 85, '2013-08-18 16:53:40'),
(16, 10, 86, '2013-08-18 16:53:41'),
(17, 10, 87, '2013-08-18 16:53:41'),
(18, 10, 88, '2013-08-18 16:53:41'),
(19, 10, 89, '2013-08-18 16:53:41'),
(20, 10, 90, '2013-08-18 16:53:42'),
(21, 10, 91, '2013-08-18 16:53:42'),
(22, 10, 92, '2013-08-18 16:53:42'),
(23, 10, 93, '2013-08-18 16:53:43'),
(24, 10, 94, '2013-08-18 16:53:43'),
(25, 10, 95, '2013-08-18 16:53:43'),
(26, 10, 96, '2013-08-18 16:53:43'),
(27, 10, 97, '2013-08-18 16:53:44'),
(28, 10, 98, '2013-08-18 16:53:44'),
(29, 10, 99, '2013-08-18 16:53:44'),
(30, 10, 100, '2013-08-18 16:53:44'),
(31, 10, 101, '2013-08-18 16:53:44'),
(32, 10, 102, '2013-08-18 16:53:44'),
(33, 10, 103, '2013-08-18 16:53:44'),
(34, 10, 104, '2013-08-18 16:53:44'),
(35, 10, 105, '2013-08-18 16:53:44'),
(36, 10, 106, '2013-08-18 16:53:44'),
(37, 10, 107, '2013-08-18 16:53:44'),
(38, 10, 108, '2013-08-18 16:53:44'),
(39, 10, 109, '2013-08-18 16:53:45'),
(40, 10, 110, '2013-08-18 16:53:45'),
(41, 10, 111, '2013-08-18 16:53:45'),
(42, 10, 112, '2013-08-18 16:53:45'),
(43, 10, 113, '2013-08-18 16:53:45'),
(44, 10, 114, '2013-08-18 16:53:45'),
(45, 10, 115, '2013-08-18 16:53:45'),
(46, 10, 116, '2013-08-18 16:53:45'),
(47, 10, 117, '2013-08-18 16:53:45'),
(48, 10, 118, '2013-08-18 16:53:46'),
(49, 10, 119, '2013-08-18 16:53:46'),
(50, 10, 120, '2013-08-18 16:53:46'),
(51, 10, 121, '2013-08-18 16:53:46'),
(52, 10, 122, '2013-08-18 16:53:46'),
(53, 10, 123, '2013-08-18 16:53:46'),
(54, 10, 124, '2013-08-18 16:53:46'),
(55, 10, 125, '2013-08-18 16:53:46'),
(56, 10, 126, '2013-08-18 16:53:46'),
(57, 10, 127, '2013-08-18 16:53:46'),
(58, 10, 128, '2013-08-18 16:53:46'),
(59, 10, 129, '2013-08-18 16:53:46'),
(60, 10, 130, '2013-08-18 16:53:46'),
(61, 10, 131, '2013-08-18 16:53:46'),
(62, 10, 132, '2013-08-18 16:53:46'),
(63, 10, 133, '2013-08-18 16:53:46'),
(64, 10, 134, '2013-08-18 16:53:46'),
(65, 10, 135, '2013-08-18 16:53:46'),
(66, 10, 136, '2013-08-18 16:53:46'),
(67, 10, 137, '2013-08-18 16:53:46'),
(68, 10, 138, '2013-08-18 16:53:46'),
(69, 10, 139, '2013-08-18 16:53:46'),
(70, 10, 140, '2013-08-18 16:53:46'),
(71, 10, 149, '2014-04-11 00:10:32'),
(72, 10, 150, '2014-04-11 00:15:17'),
(73, 10, 151, '2014-04-11 00:18:50');

-- --------------------------------------------------------

--
-- Table structure for table `golfGroupVolunteer`
--

CREATE TABLE `golfGroupVolunteer` (
  `golfGroupVolunteerID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `golfGroupID` int(11) unsigned NOT NULL,
  `volunteerID` int(11) unsigned DEFAULT NULL,
  `dateRegistered` datetime DEFAULT NULL,
  PRIMARY KEY (`golfGroupVolunteerID`),
  UNIQUE KEY `gfv001` (`golfGroupID`,`volunteerID`),
  KEY `fkv001` (`golfGroupID`),
  KEY `fkv002` (`volunteerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `leaderBoard`
--

CREATE TABLE `leaderBoard` (
  `leaderBoardID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tournamentID` int(10) unsigned NOT NULL,
  `playerID` int(10) unsigned NOT NULL,
  `winnerFlag` int(10) unsigned DEFAULT '0',
  `position` int(10) unsigned NOT NULL DEFAULT '0',
  `tied` int(10) unsigned DEFAULT '0',
  `parStatus` int(11) NOT NULL DEFAULT '0',
  `scoreRound1` int(10) unsigned NOT NULL DEFAULT '0',
  `scoreRound2` int(10) unsigned DEFAULT '0',
  `scoreRound3` int(10) unsigned DEFAULT '0',
  `scoreRound4` int(10) unsigned DEFAULT '0',
  `scoreRound5` int(10) unsigned DEFAULT '0',
  `scoreRound6` int(10) unsigned DEFAULT '0',
  `totalScore` int(10) unsigned NOT NULL DEFAULT '0',
  `dateRegistered` datetime DEFAULT NULL,
  `ageGroupID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`leaderBoardID`),
  KEY `tournamentID` (`tournamentID`),
  KEY `playerID` (`playerID`),
  KEY `ageGroupID` (`ageGroupID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=467 ;

--
-- Dumping data for table `leaderBoard`
--

INSERT INTO `leaderBoard` (`leaderBoardID`, `tournamentID`, `playerID`, `winnerFlag`, `position`, `tied`, `parStatus`, `scoreRound1`, `scoreRound2`, `scoreRound3`, `scoreRound4`, `scoreRound5`, `scoreRound6`, `totalScore`, `dateRegistered`, `ageGroupID`) VALUES
(182, 29, 124, 0, 0, 0, 0, 73, 0, 0, 0, 0, 0, 73, '2014-04-19 01:37:09', 7),
(183, 29, 127, 0, 0, 0, 0, 71, 0, 0, 0, 0, 0, 71, '2014-04-19 01:38:07', 5),
(185, 29, 150, 0, 0, 0, 0, 81, 0, 0, 0, 0, 0, 81, '2014-04-19 01:46:54', 6),
(186, 29, 135, 0, 0, 0, 0, 76, 0, 0, 0, 0, 0, 76, '2014-04-19 01:47:05', 4),
(187, 29, 112, 0, 0, 0, 0, 74, 0, 0, 0, 0, 0, 74, '2014-04-19 01:47:11', 6),
(188, 29, 139, 0, 0, 0, 0, 78, 0, 0, 0, 0, 0, 78, '2014-04-19 01:47:18', 4),
(189, 29, 121, 0, 0, 0, 0, 69, 0, 0, 0, 0, 0, 69, '2014-04-19 01:47:31', 6),
(190, 29, 134, 0, 0, 0, 0, 79, 0, 0, 0, 0, 0, 79, '2014-04-19 01:47:40', 6),
(191, 29, 105, 0, 0, 0, 0, 69, 0, 0, 0, 0, 0, 69, '2014-04-19 01:47:53', 4),
(192, 29, 71, 0, 0, 0, 0, 74, 0, 0, 0, 0, 0, 74, '2014-04-19 01:48:03', 6),
(193, 29, 116, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-19 01:48:13', 5),
(194, 29, 97, 0, 0, 0, 0, 67, 0, 0, 0, 0, 0, 67, '2014-04-19 01:48:24', 3),
(195, 29, 86, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-19 01:49:41', 2),
(196, 29, 88, 0, 0, 0, 0, 73, 0, 0, 0, 0, 0, 73, '2014-04-19 01:49:51', 2),
(197, 29, 128, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-19 01:49:59', 7),
(198, 29, 75, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-19 01:50:10', 1),
(199, 29, 118, 0, 0, 0, 0, 77, 0, 0, 0, 0, 0, 77, '2014-04-19 01:50:45', 5),
(200, 29, 122, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-19 01:50:56', 7),
(201, 29, 95, 0, 0, 0, 0, 68, 0, 0, 0, 0, 0, 68, '2014-04-19 01:51:10', 4),
(202, 29, 107, 0, 0, 0, 0, 71, 0, 0, 0, 0, 0, 71, '2014-04-19 01:51:37', 4),
(203, 29, 140, 0, 0, 0, 0, 85, 0, 0, 0, 0, 0, 85, '2014-04-19 04:07:56', 3),
(206, 29, 76, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-19 06:30:24', 11),
(207, 29, 151, 0, 0, 0, 0, 77, 0, 0, 0, 0, 0, 77, '2014-04-19 06:30:50', 12),
(208, 29, 103, 0, 0, 0, 0, 78, 0, 0, 0, 0, 0, 78, '2014-04-19 06:31:01', 11),
(209, 29, 130, 0, 0, 0, 0, 75, 0, 0, 0, 0, 0, 75, '2014-04-19 06:31:39', 13),
(210, 29, 114, 0, 0, 0, 0, 81, 0, 0, 0, 0, 0, 81, '2014-04-19 06:31:53', 13),
(211, 29, 90, 0, 0, 0, 0, 70, 0, 0, 0, 0, 0, 70, '2014-04-19 14:23:20', 6),
(212, 29, 87, 0, 0, 0, 0, 80, 0, 0, 0, 0, 0, 80, '2014-04-19 14:24:10', 6),
(213, 29, 120, 0, 0, 0, 0, 68, 0, 0, 0, 0, 0, 68, '2014-04-19 14:24:32', 12),
(214, 29, 137, 0, 0, 0, 0, 75, 0, 0, 0, 0, 0, 75, '2014-04-19 14:24:43', 4),
(215, 29, 126, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-19 14:24:49', 7),
(216, 29, 80, 0, 0, 0, 0, 82, 0, 0, 0, 0, 0, 82, '2014-04-19 14:24:55', 1),
(217, 29, 149, 0, 0, 0, 0, 69, 0, 0, 0, 0, 0, 69, '2014-04-19 14:25:21', 11),
(218, 29, 89, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-19 14:25:26', 2),
(219, 29, 102, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-19 14:25:39', 4),
(220, 29, 83, 0, 0, 0, 0, 78, 0, 0, 0, 0, 0, 78, '2014-04-19 14:25:50', 4),
(221, 29, 73, 0, 0, 0, 0, 74, 0, 0, 0, 0, 0, 74, '2014-04-19 14:25:57', 1),
(222, 29, 72, 0, 0, 0, 0, 70, 0, 0, 0, 0, 0, 70, '2014-04-19 14:26:03', 2),
(223, 29, 79, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-19 14:26:10', 2),
(224, 29, 136, 0, 0, 0, 0, 83, 0, 0, 0, 0, 0, 83, '2014-04-19 14:26:19', 2),
(225, 29, 96, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-19 14:26:27', 4),
(226, 29, 115, 0, 0, 0, 0, 79, 0, 0, 0, 0, 0, 79, '2014-04-19 14:26:32', 6),
(227, 29, 123, 0, 0, 0, 0, 81, 0, 0, 0, 0, 0, 81, '2014-04-19 14:26:37', 6),
(228, 29, 99, 0, 0, 0, 0, 79, 0, 0, 0, 0, 0, 79, '2014-04-19 14:26:42', 3),
(229, 29, 131, 0, 0, 0, 0, 79, 0, 0, 0, 0, 0, 79, '2014-04-19 14:26:52', 4),
(230, 29, 92, 0, 0, 0, 0, 70, 0, 0, 0, 0, 0, 70, '2014-04-19 14:27:02', 3),
(231, 29, 77, 0, 0, 0, 0, 80, 0, 0, 0, 0, 0, 80, '2014-04-19 14:27:15', 2),
(232, 29, 85, 0, 0, 0, 0, 71, 0, 0, 0, 0, 0, 71, '2014-04-19 14:27:22', 2),
(233, 29, 100, 0, 0, 0, 0, 82, 0, 0, 0, 0, 0, 82, '2014-04-19 14:27:28', 3),
(237, 17, 124, 0, 0, 0, 0, 73, 74, 0, 0, 0, 0, 147, '2014-04-19 15:25:10', 7),
(238, 17, 150, 0, 0, 0, 0, 75, 63, 0, 0, 0, 0, 138, '2014-04-19 15:25:25', 13),
(239, 17, 127, 0, 0, 0, 0, 74, 74, 0, 0, 0, 0, 148, '2014-04-19 15:25:34', 5),
(240, 17, 135, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:25:39', 4),
(241, 17, 112, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:25:51', 6),
(242, 17, 139, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:25:57', 4),
(243, 17, 121, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:26:02', 6),
(244, 17, 78, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:26:07', 5),
(245, 17, 90, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:26:12', 6),
(246, 17, 129, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:26:16', 7),
(247, 17, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:26:21', 6),
(248, 17, 87, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:26:26', 6),
(249, 17, 95, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:26:32', 11),
(250, 17, 76, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:26:37', 11),
(251, 17, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:03', 12),
(252, 17, 105, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:07', 4),
(253, 17, 107, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:11', 11),
(254, 17, 151, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:16', 12),
(255, 17, 110, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:22', 12),
(256, 17, 114, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:27', 13),
(257, 17, 137, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:31', 4),
(258, 17, 126, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:36', 7),
(259, 17, 80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:40', 1),
(260, 17, 103, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:43', 11),
(261, 17, 149, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:47', 11),
(262, 17, 89, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:51', 2),
(263, 17, 130, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:54', 13),
(264, 17, 102, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:27:58', 4),
(265, 17, 109, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:03', 5),
(266, 17, 71, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:09', 6),
(267, 17, 83, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:12', 4),
(268, 17, 140, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:17', 10),
(269, 17, 116, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:20', 5),
(270, 17, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:24', 1),
(271, 17, 72, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:27', 2),
(272, 17, 79, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:31', 2),
(273, 17, 117, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:35', 5),
(274, 17, 101, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:40', 4),
(275, 17, 119, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:44', 5),
(276, 17, 93, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:49', 3),
(277, 17, 111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:28:57', 6),
(278, 17, 97, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:29:13', 3),
(279, 17, 136, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:29:17', 2),
(280, 17, 96, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:29:22', 4),
(281, 17, 115, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:29:26', 5),
(282, 17, 123, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:29:30', 6),
(283, 17, 74, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:29:37', 2),
(284, 17, 81, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:29:41', 2),
(285, 17, 99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:29:46', 3),
(286, 17, 84, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:29:50', 2),
(287, 17, 131, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:29:54', 4),
(288, 17, 86, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:02', 2),
(289, 17, 122, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:06', 7),
(290, 17, 108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:10', 4),
(291, 17, 91, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:17', 3),
(292, 17, 98, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:22', 3),
(293, 17, 106, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:27', 4),
(294, 17, 132, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:31', 3),
(295, 17, 133, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:36', 2),
(296, 17, 88, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:40', 2),
(297, 17, 92, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:48', 3),
(298, 17, 118, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:53', 5),
(299, 17, 138, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:30:57', 4),
(300, 17, 75, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:31:11', 1),
(301, 17, 125, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:31:20', 6),
(302, 17, 113, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:31:24', 5),
(303, 17, 94, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:31:32', 3),
(304, 17, 77, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:31:36', 2),
(305, 17, 85, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:31:44', 2),
(306, 17, 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:31:49', 7),
(307, 17, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:31:56', 3),
(308, 17, 82, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-19 15:32:01', 2),
(310, 31, 124, 0, 0, 0, 0, 73, 74, 0, 0, 0, 0, 147, '2014-04-19 15:56:34', 7),
(311, 31, 150, 0, 0, 0, 0, 79, 77, 0, 0, 0, 0, 156, '2014-04-19 15:56:44', 13),
(312, 31, 127, 0, 0, 0, 0, 74, 70, 0, 0, 0, 0, 144, '2014-04-19 15:56:49', 5),
(313, 31, 135, 0, 0, 0, 0, 79, 74, 0, 0, 0, 0, 153, '2014-04-19 15:56:56', 4),
(314, 31, 112, 0, 0, 0, 0, 72, 69, 0, 0, 0, 0, 141, '2014-04-19 15:57:01', 6),
(315, 31, 139, 0, 0, 0, 0, 72, 77, 0, 0, 0, 0, 149, '2014-04-19 15:57:08', 4),
(316, 31, 121, 0, 0, 0, 0, 74, 75, 0, 0, 0, 0, 149, '2014-04-19 15:57:13', 6),
(317, 31, 78, 0, 0, 0, 0, 71, 70, 0, 0, 0, 0, 141, '2014-04-19 15:57:22', 5),
(318, 31, 90, 0, 0, 0, 0, 73, 85, 0, 0, 0, 0, 158, '2014-04-19 15:57:26', 6),
(319, 31, 129, 0, 0, 0, 0, 75, 77, 0, 0, 0, 0, 152, '2014-04-19 15:57:31', 7),
(320, 31, 134, 0, 0, 0, 0, 77, 78, 0, 0, 0, 0, 155, '2014-04-19 15:57:35', 6),
(322, 31, 95, 0, 0, 0, 0, 73, 70, 0, 0, 0, 0, 143, '2014-04-19 15:57:47', 11),
(323, 31, 76, 0, 0, 0, 0, 79, 76, 0, 0, 0, 0, 155, '2014-04-19 15:57:51', 11),
(324, 31, 120, 0, 0, 0, 0, 72, 68, 0, 0, 0, 0, 140, '2014-04-19 15:57:55', 12),
(325, 31, 105, 0, 0, 0, 0, 76, 70, 0, 0, 0, 0, 146, '2014-04-19 15:57:59', 4),
(326, 31, 107, 0, 0, 0, 0, 74, 66, 0, 0, 0, 0, 140, '2014-04-19 15:58:03', 11),
(327, 31, 151, 0, 0, 0, 0, 76, 70, 0, 0, 0, 0, 146, '2014-04-19 15:58:07', 12),
(328, 31, 110, 0, 0, 0, 0, 72, 76, 0, 0, 0, 0, 148, '2014-04-19 15:58:10', 12),
(329, 31, 114, 0, 0, 0, 0, 74, 71, 0, 0, 0, 0, 145, '2014-04-19 15:58:14', 13),
(330, 31, 137, 0, 0, 0, 0, 68, 77, 0, 0, 0, 0, 145, '2014-04-19 15:58:19', 4),
(331, 31, 126, 0, 0, 0, 0, 72, 103, 0, 0, 0, 0, 175, '2014-04-19 15:58:28', 7),
(332, 31, 80, 0, 0, 0, 0, 106, 75, 0, 0, 0, 0, 181, '2014-04-19 15:58:33', 1),
(333, 31, 103, 0, 0, 0, 0, 84, 82, 0, 0, 0, 0, 166, '2014-04-19 15:58:37', 11),
(334, 31, 149, 0, 0, 0, 0, 78, 76, 0, 0, 0, 0, 154, '2014-04-19 15:58:43', 11),
(335, 31, 89, 0, 0, 0, 0, 74, 78, 0, 0, 0, 0, 152, '2014-04-19 15:58:47', 2),
(336, 31, 130, 0, 0, 0, 0, 77, 80, 0, 0, 0, 0, 157, '2014-04-19 15:58:51', 13),
(337, 31, 102, 0, 0, 0, 0, 73, 74, 0, 0, 0, 0, 147, '2014-04-19 15:58:55', 4),
(338, 31, 109, 0, 0, 0, 0, 76, 80, 0, 0, 0, 0, 156, '2014-04-19 15:59:01', 5),
(339, 31, 71, 0, 0, 0, 0, 88, 76, 0, 0, 0, 0, 164, '2014-04-19 15:59:05', 6),
(340, 31, 140, 0, 0, 0, 0, 72, 72, 0, 0, 0, 0, 144, '2014-04-19 15:59:11', 10),
(342, 31, 116, 0, 0, 0, 0, 76, 79, 0, 0, 0, 0, 155, '2014-04-19 15:59:31', 5),
(343, 31, 72, 0, 0, 0, 0, 77, 82, 0, 0, 0, 0, 159, '2014-04-19 15:59:36', 2),
(344, 31, 117, 0, 0, 0, 0, 106, 99, 0, 0, 0, 0, 205, '2014-04-19 15:59:42', 5),
(345, 31, 79, 0, 0, 0, 0, 71, 74, 0, 0, 0, 0, 145, '2014-04-19 15:59:47', 2),
(346, 31, 101, 0, 0, 0, 0, 71, 75, 0, 0, 0, 0, 146, '2014-04-19 15:59:53', 4),
(347, 31, 111, 2, 0, 0, 0, 67, 74, 0, 0, 0, 0, 141, '2014-04-19 15:59:58', 6),
(348, 31, 93, 0, 0, 0, 0, 72, 72, 0, 0, 0, 0, 144, '2014-04-19 16:00:03', 3),
(349, 31, 119, 0, 0, 0, 0, 65, 68, 0, 0, 0, 0, 133, '2014-04-19 16:00:11', 5),
(350, 31, 97, 0, 0, 0, 0, 75, 68, 0, 0, 0, 0, 143, '2014-04-19 16:00:18', 3),
(351, 31, 115, 0, 0, 0, 0, 86, 79, 0, 0, 0, 0, 165, '2014-04-19 16:00:23', 5),
(352, 31, 74, 0, 0, 0, 0, 91, 83, 0, 0, 0, 0, 174, '2014-04-19 16:00:31', 2),
(353, 31, 86, 0, 0, 0, 0, 77, 79, 0, 0, 0, 0, 156, '2014-04-19 16:00:39', 2),
(354, 31, 75, 0, 0, 0, 0, 78, 76, 0, 0, 0, 0, 154, '2014-04-19 16:00:47', 1),
(355, 31, 128, 0, 0, 0, 0, 75, 78, 0, 0, 0, 0, 153, '2014-04-19 16:00:58', 7),
(359, 27, 124, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-22 12:55:10', 7),
(360, 27, 150, 0, 0, 0, 0, 70, 0, 0, 0, 0, 0, 70, '2014-04-22 12:55:53', 13),
(361, 27, 127, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-22 12:56:00', 5),
(362, 27, 135, 0, 0, 0, 0, 73, 0, 0, 0, 0, 0, 73, '2014-04-22 13:01:06', 4),
(363, 27, 112, 0, 0, 0, 0, 78, 0, 0, 0, 0, 0, 78, '2014-04-22 13:01:11', 6),
(364, 27, 139, 0, 0, 0, 0, 71, 0, 0, 0, 0, 0, 71, '2014-04-22 13:01:18', 4),
(365, 27, 121, 0, 0, 0, 0, 76, 0, 0, 0, 0, 0, 76, '2014-04-22 13:01:22', 6),
(366, 27, 78, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:01:27', 5),
(367, 27, 90, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:01:31', 6),
(368, 27, 129, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:01:35', 7),
(369, 27, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:01:39', 6),
(370, 27, 87, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:01:45', 6),
(371, 27, 95, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:01:49', 11),
(372, 27, 76, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:01:54', 11),
(373, 27, 105, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:01:58', 4),
(374, 27, 107, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:02:04', 11),
(375, 27, 151, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:02:09', 12),
(376, 27, 110, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:02:14', 12),
(377, 27, 137, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:02:18', 4),
(378, 27, 126, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:02:23', 7),
(379, 27, 80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:02:27', 1),
(380, 27, 149, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:02:30', 11),
(381, 27, 103, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:02:38', 11),
(382, 27, 89, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:02:47', 2),
(383, 27, 130, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:02:53', 13),
(384, 27, 102, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:02', 4),
(385, 27, 109, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:07', 5),
(386, 27, 71, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:11', 6),
(387, 27, 83, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:16', 4),
(388, 27, 140, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:21', 10),
(389, 27, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:24', 1),
(390, 27, 72, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:28', 2),
(391, 27, 79, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:33', 2),
(392, 27, 101, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:37', 4),
(393, 27, 119, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:40', 5),
(394, 27, 93, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:44', 3),
(395, 27, 97, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:49', 3),
(396, 27, 136, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:52', 2),
(397, 27, 96, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:03:57', 4),
(398, 27, 123, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:00', 6),
(399, 27, 74, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:14', 2),
(400, 27, 81, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:18', 2),
(401, 27, 99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:22', 3),
(402, 27, 84, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:27', 2),
(403, 27, 131, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:31', 4),
(404, 27, 86, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:35', 2),
(405, 27, 122, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:38', 7),
(406, 27, 108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:42', 5),
(407, 27, 91, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:46', 3),
(408, 27, 104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:52', 4),
(409, 27, 75, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:04:58', 1),
(410, 27, 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 13:05:06', 7),
(417, 33, 87, 0, 0, 0, 0, 37, 41, 0, 0, 0, 0, 78, '2014-04-22 14:49:11', 6),
(418, 33, 105, 0, 0, 0, 0, 45, 39, 0, 0, 0, 0, 84, '2014-04-22 14:52:50', 4),
(419, 33, 78, 0, 0, 0, 0, 41, 35, 0, 0, 0, 0, 76, '2014-04-22 15:35:27', 5),
(420, 34, 124, 0, 0, 0, 0, 33, 0, 0, 0, 0, 0, 33, '2014-04-22 16:27:16', 7),
(421, 34, 127, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 16:27:27', 5),
(422, 34, 121, 0, 0, 0, 0, 57, 0, 0, 0, 0, 0, 57, '2014-04-22 16:27:34', 6),
(423, 33, 112, 0, 0, 0, 0, 35, 38, 0, 0, 0, 0, 73, '2014-04-22 17:47:17', 6),
(424, 34, 129, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 17:50:45', 7),
(425, 34, 135, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 17:51:11', 4),
(426, 33, 129, 0, 0, 0, 0, 39, 0, 0, 0, 0, 0, 39, '2014-04-22 18:44:45', 7),
(427, 33, 126, 0, 0, 0, 0, 36, 0, 0, 0, 0, 0, 36, '2014-04-22 18:44:54', 7),
(428, 33, 128, 0, 0, 0, 0, 33, 0, 0, 0, 0, 0, 33, '2014-04-22 18:45:12', 7),
(429, 33, 124, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 32, '2014-04-22 18:45:24', 7),
(430, 33, 122, 0, 0, 0, 0, 38, 0, 0, 0, 0, 0, 38, '2014-04-22 18:45:46', 7),
(431, 33, 85, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-22 19:28:43', 2),
(432, 33, 139, 0, 0, 0, 0, 37, 0, 0, 0, 0, 0, 37, '2014-04-22 19:33:48', 4),
(433, 32, 130, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 12:01:27', 13),
(434, 35, 135, 0, 0, 0, 0, 65, 0, 0, 0, 0, 0, 65, '2014-04-23 16:54:19', 4),
(435, 35, 139, 0, 0, 0, 0, 67, 0, 0, 0, 0, 0, 67, '2014-04-23 16:54:29', 4),
(436, 35, 137, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-23 16:54:38', 4),
(437, 35, 96, 0, 0, 0, 0, 70, 0, 0, 0, 0, 0, 70, '2014-04-23 16:54:48', 4),
(438, 35, 131, 0, 0, 0, 0, 65, 0, 0, 0, 0, 0, 65, '2014-04-23 16:54:57', 3),
(439, 35, 105, 0, 0, 0, 0, 68, 0, 0, 0, 0, 0, 68, '2014-04-23 16:55:05', 4),
(440, 35, 102, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-23 16:55:13', 4),
(441, 35, 83, 0, 0, 0, 0, 74, 0, 0, 0, 0, 0, 74, '2014-04-23 16:55:26', 4),
(442, 35, 101, 0, 0, 0, 0, 69, 0, 0, 0, 0, 0, 69, '2014-04-23 16:55:34', 4),
(443, 35, 108, 2, 0, 0, 0, 65, 0, 0, 0, 0, 0, 65, '2014-04-23 16:55:39', 4),
(444, 35, 106, 0, 0, 0, 0, 39, 0, 0, 0, 0, 0, 39, '2014-04-23 16:55:46', 4),
(445, 35, 104, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 50, '2014-04-23 16:55:52', 4),
(446, 36, 135, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:48:22', 4),
(447, 36, 139, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:48:29', 4),
(448, 36, 137, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:48:35', 4),
(449, 36, 96, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:48:43', 4),
(450, 36, 131, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:48:49', 4),
(451, 36, 105, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:48:57', 4),
(452, 36, 102, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:49:05', 4),
(453, 36, 83, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:49:13', 4),
(454, 36, 101, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:49:21', 4),
(455, 36, 108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:49:33', 5),
(456, 36, 106, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:49:37', 4),
(457, 36, 104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:49:42', 5),
(458, 36, 138, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2014-04-23 20:49:56', 4),
(459, 37, 72, 0, 0, 0, 0, 75, 0, 0, 0, 0, 0, 75, '2014-04-23 21:39:27', 2),
(460, 37, 79, 0, 0, 0, 0, 71, 0, 0, 0, 0, 0, 71, '2014-04-23 21:39:35', 2),
(461, 37, 77, 0, 0, 0, 0, 73, 0, 0, 0, 0, 0, 73, '2014-04-23 21:39:41', 2),
(462, 37, 89, 2, 0, 0, 0, 70, 0, 0, 0, 0, 0, 70, '2014-04-23 21:39:48', 2),
(463, 37, 136, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-23 21:39:54', 2),
(464, 37, 81, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-23 21:40:00', 2),
(465, 37, 84, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 72, '2014-04-23 21:40:05', 2),
(466, 37, 86, 0, 0, 0, 0, 70, 0, 0, 0, 0, 0, 70, '2014-04-23 21:40:14', 2);

-- --------------------------------------------------------

--
-- Table structure for table `parent`
--

CREATE TABLE `parent` (
  `parentID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `middleName` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `lastName` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `email` varchar(95) COLLATE latin1_general_ci DEFAULT NULL,
  `cellphone` varchar(25) COLLATE latin1_general_ci DEFAULT NULL,
  `parentType` int(11) DEFAULT NULL,
  `pin` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`parentID`),
  UNIQUE KEY `ix3` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=144 ;

--
-- Dumping data for table `parent`
--

INSERT INTO `parent` (`parentID`, `firstName`, `middleName`, `lastName`, `email`, `cellphone`, `parentType`, `pin`) VALUES
(71, 'Hunter', NULL, 'Locke', 'malengadev2@gmail.com', '099 999 9999', 0, '12345'),
(72, 'Solomon', NULL, 'Morris', 'malengadev4@gmail.com', '099 999 9999', 0, '12345'),
(73, 'Geoffrey', NULL, 'Maringa', 'malengadev6@gmail.com', '099 999 9999', 0, '12345'),
(74, 'Yipper', NULL, 'Peyton', 'malengadev8@gmail.com', '099 999 9999', 0, '12345'),
(75, 'Jack', NULL, 'Venter', 'malengadev10@gmail.com', '099 999 9999', 0, '12345'),
(76, 'Sam', NULL, 'Maringa', 'malengadev12@gmail.com', '099 999 9999', 0, '12345'),
(77, 'Lawrence', NULL, 'Williams', 'malengadev14@gmail.com', '099 999 9999', 0, '12345'),
(78, 'Caleb', NULL, 'Botha', 'malengadev16@gmail.com', '099 999 9999', 0, '12345'),
(79, 'Zeke', NULL, 'Morris', 'malengadev18@gmail.com', '099 999 9999', 0, '12345'),
(80, 'Geoffrey', NULL, 'Johnson', 'malengadev20@gmail.com', '099 999 9999', 0, '12345'),
(81, 'Benjamin', NULL, 'Player', 'malengadev22@gmail.com', '099 999 9999', 0, '12345'),
(82, 'Johnny', NULL, 'Zulu', 'malengadev24@gmail.com', '099 999 9999', 0, '12345'),
(83, 'Geoffrey', NULL, 'Locke', 'malengadev26@gmail.com', '099 999 9999', 0, '12345'),
(84, 'Francois', NULL, 'Potgieter', 'malengadev28@gmail.com', '099 999 9999', 0, '12345'),
(85, 'Hunter', NULL, 'Williams', 'malengadev30@gmail.com', '099 999 9999', 0, '12345'),
(86, 'Forrest', NULL, 'Priest', 'malengadev32@gmail.com', '099 999 9999', 0, '12345'),
(87, 'Peter', NULL, 'Charles', 'malengadev34@gmail.com', '099 999 9999', 0, '12345'),
(88, 'Jack', NULL, 'Smythe', 'malengadev36@gmail.com', '099 999 9999', 0, '12345'),
(89, 'Solomon', NULL, 'Jones', 'malengadev38@gmail.com', '099 999 9999', 0, '12345'),
(90, 'Samuel', NULL, 'Brown', 'malengadev40@gmail.com', '099 999 9999', 0, '12345'),
(91, 'David', NULL, 'Ringane', 'malengadev42@gmail.com', '099 999 9999', 0, '12345'),
(92, 'Msapa', NULL, 'van der Merwe', 'malengadev44@gmail.com', '099 999 9999', 0, '12345'),
(93, 'Ronnie', NULL, 'Nxasa', 'malengadev46@gmail.com', '099 999 9999', 0, '12345'),
(94, 'Hunter', NULL, 'Williams', 'malengadev48@gmail.com', '099 999 9999', 0, '12345'),
(95, 'Zukwa', NULL, 'Maringa', 'malengadev50@gmail.com', '099 999 9999', 0, '12345'),
(96, 'Vusi', NULL, 'Oosthuizen', 'malengadev52@gmail.com', '099 999 9999', 0, '12345'),
(97, 'Jacob', NULL, 'Oosthuizen', 'malengadev54@gmail.com', '099 999 9999', 0, '12345'),
(98, 'David', NULL, 'Ringane', 'malengadev56@gmail.com', '099 999 9999', 0, '12345'),
(99, 'Yipper', NULL, 'Potgieter', 'malengadev58@gmail.com', '099 999 9999', 0, '12345'),
(100, 'John', NULL, 'Williams', 'malengadev60@gmail.com', '099 999 9999', 0, '12345'),
(101, 'Ivan', NULL, 'Nxasa', 'malengadev62@gmail.com', '099 999 9999', 0, '12345'),
(102, 'Godfrey', NULL, 'Locke', 'malengadev64@gmail.com', '099 999 9999', 0, '12345'),
(103, 'Robert', NULL, 'Sithole', 'malengadev66@gmail.com', '099 999 9999', 0, '12345'),
(104, 'Hunter', NULL, 'Smith', 'malengadev68@gmail.com', '099 999 9999', 0, '12345'),
(105, 'Jeremiah', NULL, 'Els', 'malengadev70@gmail.com', '099 999 9999', 0, '12345'),
(106, 'Ivan', NULL, 'Schwartzel', 'malengadev72@gmail.com', '099 999 9999', 0, '12345'),
(107, 'Andrew', NULL, 'Zungu', 'malengadev74@gmail.com', '099 999 9999', 0, '12345'),
(108, 'Zukwa', NULL, 'Remington', 'malengadev76@gmail.com', '099 999 9999', 0, '12345'),
(109, 'Lee', NULL, 'Locke', 'malengadev78@gmail.com', '099 999 9999', 0, '12345'),
(110, 'Piet', NULL, 'Sithole', 'malengadev80@gmail.com', '099 999 9999', 0, '12345'),
(111, 'Shane', NULL, 'Nxasa', 'malengadev82@gmail.com', '099 999 9999', 0, '12345'),
(112, 'Ivan', NULL, 'Baloyi', 'malengadev84@gmail.com', '099 999 9999', 0, '12345'),
(113, 'Jacob', NULL, 'Vermaak', 'malengadev86@gmail.com', '099 999 9999', 0, '12345'),
(114, 'Solomon', NULL, 'Sithole', 'malengadev88@gmail.com', '099 999 9999', 0, '12345'),
(115, 'Noonan', NULL, 'Paulson', 'malengadev90@gmail.com', '099 999 9999', 0, '12345'),
(116, 'Sean', NULL, 'Maluleka', 'malengadev92@gmail.com', '099 999 9999', 0, '12345'),
(117, 'Ronnie', NULL, 'Naidoo', 'malengadev94@gmail.com', '099 999 9999', 0, '12345'),
(118, 'Newton', NULL, 'van Wyk', 'malengadev96@gmail.com', '099 999 9999', 0, '12345'),
(119, 'Jeremiah', NULL, 'Nxasa', 'malengadev98@gmail.com', '099 999 9999', 0, '12345'),
(120, 'Yipper', NULL, 'Sono', 'malengadev100@gmail.com', '099 999 9999', 0, '12345'),
(121, 'Raymond', NULL, 'Botha', 'malengadev102@gmail.com', '099 999 9999', 0, '12345'),
(122, 'Benjamin', NULL, 'Priest', 'malengadev104@gmail.com', '099 999 9999', 0, '12345'),
(123, 'Malenga', NULL, 'Paulson', 'malengadev106@gmail.com', '099 999 9999', 0, '12345'),
(124, 'Blake', NULL, 'Armstrong', 'malengadev108@gmail.com', '099 999 9999', 0, '12345'),
(125, 'Malenga', NULL, 'Vermaak', 'malengadev110@gmail.com', '099 999 9999', 0, '12345'),
(126, 'Boyce', NULL, 'Johnson', 'malengadev112@gmail.com', '099 999 9999', 0, '12345'),
(127, 'Blake', NULL, 'Bala', 'malengadev114@gmail.com', '099 999 9999', 0, '12345'),
(128, 'Ivan', NULL, 'Williams', 'malengadev116@gmail.com', '099 999 9999', 0, '12345'),
(129, 'Michael', NULL, 'Brown', 'malengadev118@gmail.com', '099 999 9999', 0, '12345'),
(130, 'Mark', NULL, 'Maluleka', 'malengadev120@gmail.com', '099 999 9999', 0, '12345'),
(131, 'William', NULL, 'Priest', 'malengadev122@gmail.com', '099 999 9999', 0, '12345'),
(132, 'Francois', NULL, 'Sithole', 'malengadev124@gmail.com', '099 999 9999', 0, '12345'),
(133, 'Solomon', NULL, 'Smythe', 'malengadev126@gmail.com', '099 999 9999', 0, '12345'),
(134, 'Msapa', NULL, 'Burmingham', 'malengadev128@gmail.com', '099 999 9999', 0, '12345'),
(135, 'Raymond', NULL, 'Black', 'malengadev130@gmail.com', '099 999 9999', 0, '12345'),
(136, 'Msapa', NULL, 'Oosthuizen', 'malengadev132@gmail.com', '099 999 9999', 0, '12345'),
(137, 'Andrew', NULL, 'Jackson', 'malengadev134@gmail.com', '099 999 9999', 0, '12345'),
(138, 'Sipho', NULL, 'Venter', 'malengadev136@gmail.com', '099 999 9999', 0, '12345'),
(139, 'Jeremiah', NULL, 'Baloyi', 'malengadev138@gmail.com', '099 999 9999', 0, '12345'),
(140, 'Petrus', NULL, 'Sithole', 'malengadev140@gmail.com', '099 999 9999', 0, '12345'),
(143, 'Aubrey', NULL, 'Malabie', 'malengadev@gmail.com', '0998865566', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `personalPlayer`
--

CREATE TABLE `personalPlayer` (
  `personalPlayerID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `pin` varchar(20) NOT NULL,
  `cellphone` varchar(30) DEFAULT NULL,
  `countryID` int(10) unsigned DEFAULT NULL,
  `clubID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`personalPlayerID`),
  KEY `countryID` (`countryID`),
  KEY `clubID` (`clubID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `personalScore`
--

CREATE TABLE `personalScore` (
  `personalScoreID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clubID` int(10) unsigned NOT NULL,
  `personalPlayerID` int(11) unsigned DEFAULT NULL,
  `datePlayed` datetime NOT NULL,
  `score1` int(11) DEFAULT '0',
  `score2` int(11) DEFAULT '0',
  `score3` int(11) DEFAULT '0',
  `score4` int(11) DEFAULT '0',
  `score5` int(11) DEFAULT '0',
  `score6` int(11) DEFAULT '0',
  `score7` int(11) DEFAULT '0',
  `score8` int(11) DEFAULT '0',
  `score9` int(11) DEFAULT '0',
  `score10` int(11) DEFAULT '0',
  `score11` int(11) DEFAULT '0',
  `score12` int(11) DEFAULT '0',
  `score13` int(11) DEFAULT '0',
  `score14` int(11) DEFAULT '0',
  `score15` int(11) DEFAULT '0',
  `score16` int(11) DEFAULT '0',
  `score17` int(11) DEFAULT '0',
  `score18` int(11) DEFAULT '0',
  `totalScore` int(11) unsigned DEFAULT '0',
  `fairwaysHit` int(11) unsigned DEFAULT NULL,
  `greensHit` int(11) unsigned DEFAULT NULL,
  `numberOfPutts` int(11) unsigned DEFAULT NULL,
  `timeOfDay` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`personalScoreID`),
  UNIQUE KEY `datePlayed` (`datePlayed`),
  KEY `personalPlayerID` (`personalPlayerID`),
  KEY `clubID` (`clubID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `playerID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `middleName` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `lastName` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `dateRegistered` datetime DEFAULT NULL,
  `yearJoined` int(11) DEFAULT NULL,
  `email` varchar(95) COLLATE latin1_general_ci DEFAULT NULL,
  `cellphone` varchar(25) COLLATE latin1_general_ci DEFAULT NULL,
  `parentID` int(11) unsigned DEFAULT NULL,
  `pin` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`playerID`),
  UNIQUE KEY `ix4` (`lastName`,`firstName`,`middleName`,`dateOfBirth`),
  UNIQUE KEY `ix4a` (`email`),
  KEY `fk2` (`parentID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=153 ;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`playerID`, `firstName`, `middleName`, `lastName`, `dateOfBirth`, `gender`, `dateRegistered`, `yearJoined`, `email`, `cellphone`, `parentID`, `pin`) VALUES
(71, 'Lee', NULL, 'Locke', '1999-03-10', 1, '2013-08-18 16:53:34', 2013, 'malengadev1@gmail.com', '099 999 9999', NULL, '12345'),
(72, 'Harry', NULL, 'Morris', '2007-02-17', 1, '2013-08-18 16:53:35', 2013, 'malengadev3@gmail.com', '099 999 9999', NULL, '12345'),
(73, 'Newton', NULL, 'Maringa', '2007-06-01', 1, '2013-08-18 16:53:35', 2013, 'malengadev5@gmail.com', '099 999 9999', NULL, '12345'),
(74, 'Ronnie', NULL, 'Peyton', '2007-04-02', 1, '2013-08-18 16:53:36', 2013, 'malengadev7@gmail.com', '099 999 9999', NULL, '12345'),
(75, 'Sam', NULL, 'Venter', '2007-05-19', 1, '2013-08-18 16:53:37', 2013, 'malengadev9@gmail.com', '099 999 9999', NULL, '12345'),
(76, 'Samantha', NULL, 'Copeland', '2002-04-19', 2, '2013-08-18 16:53:38', 2013, 'malengadev11@gmail.com', '099 999 9999', NULL, '12345'),
(77, 'Andrew', NULL, 'Williams', '2007-04-09', 1, '2013-08-18 16:53:38', 2013, 'malengadev13@gmail.com', '099 999 9999', NULL, '12345'),
(78, 'Vusi', NULL, 'Botha', '2001-04-05', 1, '2013-08-18 16:53:38', 2013, 'malengadev15@gmail.com', '099 999 9999', NULL, '12345'),
(79, 'Jack', NULL, 'Morris', '2007-02-07', 1, '2013-08-18 16:53:38', 2013, 'malengadev17@gmail.com', '099 999 9999', NULL, '12345'),
(80, 'Ronnie', NULL, 'Johnson', '2007-06-10', 1, '2013-08-18 16:53:39', 2013, 'malengadev19@gmail.com', '099 999 9999', NULL, '12345'),
(81, 'Thomas', NULL, 'Player', '2005-09-07', 1, '2013-08-18 16:53:39', 2013, 'malengadev21@gmail.com', '099 999 9999', NULL, '12345'),
(82, 'Samuel', NULL, 'Zulu', '2005-08-09', 1, '2013-08-18 16:53:40', 2013, 'malengadev23@gmail.com', '099 999 9999', NULL, '12345'),
(83, 'Shakes', NULL, 'Locke', '2001-10-25', 1, '2013-08-18 16:53:40', 2013, 'malengadev25@gmail.com', '099 999 9999', NULL, '12345'),
(84, 'McLean', NULL, 'Potgieter', '2005-05-19', 1, '2013-08-18 16:53:40', 2013, 'malengadev27@gmail.com', '099 999 9999', NULL, '12345'),
(85, 'Jack', NULL, 'Williams', '2005-05-13', 1, '2013-08-18 16:53:40', 2013, 'malengadev29@gmail.com', '099 999 9999', NULL, '12345'),
(86, 'John', NULL, 'Priest', '2005-08-21', 1, '2013-08-18 16:53:41', 2013, 'malengadev31@gmail.com', '099 999 9999', NULL, '12345'),
(87, 'William', NULL, 'Charles', '1999-01-14', 1, '2013-08-18 16:53:41', 2013, 'malengadev33@gmail.com', '099 999 9999', NULL, '12345'),
(88, 'Obakeng', NULL, 'Smythe', '2005-10-11', 1, '2013-08-18 16:53:41', 2013, 'malengadev35@gmail.com', '099 999 9999', NULL, '12345'),
(89, 'Raymond', NULL, 'Jones', '2005-08-13', 1, '2013-08-18 16:53:41', 2013, 'malengadev37@gmail.com', '099 999 9999', NULL, '12345'),
(90, 'Shakes', NULL, 'Brown', '1999-04-07', 1, '2013-08-18 16:53:41', 2013, 'malengadev39@gmail.com', '099 999 9999', NULL, '12345'),
(91, 'Jeremiah', NULL, 'Ringane', '2003-07-06', 1, '2013-08-18 16:53:42', 2013, 'malengadev41@gmail.com', '099 999 9999', NULL, '12345'),
(92, 'Sam', NULL, 'van der Merwe', '2003-08-19', 1, '2013-08-18 16:53:42', 2013, 'malengadev43@gmail.com', '099 999 9999', NULL, '12345'),
(93, 'Raymond', NULL, 'Nxasa', '2003-05-13', 1, '2013-08-18 16:53:43', 2013, 'malengadev45@gmail.com', '099 999 9999', NULL, '12345'),
(94, 'Andrew', NULL, 'Williams', '2003-10-24', 1, '2013-08-18 16:53:43', 2013, 'malengadev47@gmail.com', '099 999 9999', NULL, '12345'),
(95, 'Charlene', NULL, 'Charleston', '2003-02-01', 2, '2013-08-18 16:53:43', 2013, 'ch.charl@gmail.com', '099 999 9999', NULL, '12345'),
(96, 'Sipho', NULL, 'Oosthuizen', '2003-02-05', 1, '2013-08-18 16:53:43', 2013, 'malengadev51@gmail.com', '099 999 9999', NULL, '12345'),
(97, 'Bennie', NULL, 'Oosthuizen', '2003-09-24', 1, '2013-08-18 16:53:44', 2013, 'malengadev53@gmail.com', '099 999 9999', NULL, '12345'),
(98, 'Johannes', NULL, 'Ringane', '2003-09-24', 1, '2013-08-18 16:53:44', 2013, 'malengadev55@gmail.com', '099 999 9999', NULL, '12345'),
(99, 'Jonty', NULL, 'Potgieter', '2003-07-26', 1, '2013-08-18 16:53:44', 2013, 'malengadev57@gmail.com', '099 999 9999', NULL, '12345'),
(100, 'Raymond', NULL, 'Williams', '2003-10-06', 1, '2013-08-18 16:53:44', 2013, 'malengadev59@gmail.com', '099 999 9999', NULL, '12345'),
(101, 'Jerry', NULL, 'Nxasa', '2001-09-16', 1, '2013-08-18 16:53:44', 2013, 'malengadev61@gmail.com', '099 999 9999', NULL, '12345'),
(102, 'Jeremiah', NULL, 'Locke', '2001-08-03', 1, '2013-08-18 16:53:44', 2013, 'malengadev63@gmail.com', '099 999 9999', NULL, '12345'),
(103, 'Venus', NULL, 'Johnson', '2001-05-19', 2, '2013-08-18 16:53:44', 2013, 'v.johnson.cv@gmail.com', '099 999 9999', NULL, '12345'),
(104, 'Tommy', NULL, 'Smith', '2001-06-02', 1, '2013-08-18 16:53:44', 2013, 'malengadev67@gmail.com', '099 999 9999', NULL, '12345'),
(105, 'David', NULL, 'Els', '2001-07-17', 1, '2013-08-18 16:53:44', 2013, 'malengadev69@gmail.com', '099 999 9999', NULL, '12345'),
(106, 'Solomon', NULL, 'Schwartzel', '2001-11-08', 1, '2013-08-18 16:53:44', 2013, 'malengadev71@gmail.com', '099 999 9999', NULL, '12345'),
(107, 'Michelle', NULL, 'Falcinelli', '2001-07-16', 2, '2013-08-18 16:53:44', 2013, 'falc.mich@gmail.com', '099 999 9999', NULL, '12345'),
(108, 'Bernard', NULL, 'Remington', '2001-05-07', 1, '2013-08-18 16:53:44', 2013, 'malengadev75@gmail.com', '099 999 9999', NULL, '12345'),
(109, 'Jonty', NULL, 'Locke', '2001-02-22', 1, '2013-08-18 16:53:44', 2013, 'malengadev77@gmail.com', '099 999 9999', NULL, '12345'),
(110, 'Wendy', NULL, 'Greene', '2001-04-12', 2, '2013-08-18 16:53:45', 2013, 'malengadev79@gmail.com', '099 999 9999', NULL, '12345'),
(111, 'Omar', NULL, 'Nxasa', '1999-02-06', 1, '2013-08-18 16:53:45', 2013, 'malengadev81@gmail.com', '099 999 9999', NULL, '12345'),
(112, 'Caleb T', NULL, 'Blankenship', '1999-02-06', 1, '2013-08-18 16:53:45', 2013, 'malengadev83@gmail.com', '099 999 9999', NULL, '12345'),
(113, 'Thomas', NULL, 'Vermaak', '1999-07-20', 1, '2013-08-18 16:53:45', 2013, 'malengadev85@gmail.com', '099 999 9999', NULL, '12345'),
(114, 'Patricia', NULL, 'Hunter', '1999-02-07', 2, '2013-08-18 16:53:45', 2013, 'pat.hunter.em@gmail.com', '099 999 9999', NULL, '12345'),
(115, 'Harry', NULL, 'Paulson', '1999-04-27', 1, '2013-08-18 16:53:45', 2013, 'malengadev89@gmail.com', '099 999 9999', NULL, '12345'),
(116, 'Boyce', NULL, 'Maluleka', '1999-10-13', 1, '2013-08-18 16:53:45', 2013, 'malengadev91@gmail.com', '099 999 9999', NULL, '12345'),
(117, 'William', NULL, 'Naidoo', '1999-11-08', 1, '2013-08-18 16:53:45', 2013, 'malengadev93@gmail.com', '099 999 9999', NULL, '12345'),
(118, 'David', NULL, 'van Wyk', '1999-07-11', 1, '2013-08-18 16:53:45', 2013, 'malengadev95@gmail.com', '099 999 9999', NULL, '12345'),
(119, 'Mack', NULL, 'Nxasa', '1999-11-27', 1, '2013-08-18 16:53:46', 2013, 'malengadev97@gmail.com', '099 999 9999', NULL, '12345'),
(120, 'Linda Sue', NULL, 'Drake', '1999-09-26', 2, '2013-08-18 16:53:46', 2013, 'malengadev99@gmail.com', '099 999 9999', NULL, '12345'),
(121, 'Paul', NULL, 'Botha', '1997-11-05', 1, '2013-08-18 16:53:46', 2013, 'malengadev101@gmail.com', '099 999 9999', NULL, '12345'),
(122, 'Mark', NULL, 'Priest', '1997-02-16', 1, '2013-08-18 16:53:46', 2013, 'malengadev103@gmail.com', '099 999 9999', NULL, '12345'),
(123, 'Mack', NULL, 'Paulson', '1997-05-12', 1, '2013-08-18 16:53:46', 2013, 'malengadev105@gmail.com', '099 999 9999', NULL, '12345'),
(124, 'Blake', NULL, 'Armstrong', '1997-03-05', 1, '2013-08-18 16:53:46', 2013, 'malengadev107@gmail.com', '099 999 9999', NULL, '12345'),
(125, 'Hunter', NULL, 'Vermaak', '1997-07-11', 1, '2013-08-18 16:53:46', 2013, 'malengadev109@gmail.com', '099 999 9999', NULL, '12345'),
(126, 'Raymond', NULL, 'Johnson', '1997-04-17', 1, '2013-08-18 16:53:46', 2013, 'malengadev111@gmail.com', '099 999 9999', NULL, '12345'),
(127, 'Newton', NULL, 'Bala', '2000-04-18', 1, '2013-08-18 16:53:46', 2013, 'malengadev113@gmail.com', '099 999 9999', NULL, '12345'),
(128, 'Lance', NULL, 'Williams', '1997-03-05', 1, '2013-08-18 16:53:46', 2013, 'malengadev115@gmail.com', '099 999 9999', NULL, '12345'),
(129, 'Tommy', NULL, 'Brown', '1997-02-24', 1, '2013-08-18 16:53:46', 2013, 'malengadev117@gmail.com', '099 999 9999', NULL, '12345'),
(130, 'Roberta S', NULL, 'Jones', '1997-08-24', 2, '2013-08-18 16:53:46', 2013, 'malengadev119@gmail.com', '099 999 9999', NULL, '12345'),
(131, 'Geoffrey', NULL, 'Priest', '2003-04-19', 1, '2013-08-18 16:53:46', 2013, 'malengadev121@gmail.com', '099 999 9999', NULL, '12345'),
(132, 'Geoffrey', NULL, 'Sithole', '2003-09-30', 1, '2013-08-18 16:53:46', 2013, 'malengadev123@gmail.com', '099 999 9999', NULL, '12345'),
(133, 'Geoffrey', NULL, 'Smythe', '2005-06-25', 1, '2013-08-18 16:53:46', 2013, 'malengadev125@gmail.com', '099 999 9999', NULL, '12345'),
(134, 'Harry', NULL, 'Burmingham', '1998-04-06', 1, '2013-08-18 16:53:46', 2013, 'malengadev127@gmail.com', '099 999 9999', NULL, '12345'),
(135, 'Blake', NULL, 'Black', '2002-06-11', 1, '2013-08-18 16:53:46', 2013, 'malengadev129@gmail.com', '099 999 9999', NULL, '12345'),
(136, 'Johannes', NULL, 'Oosthuizen', '2006-04-19', 1, '2013-08-18 16:53:46', 2013, 'malengadev131@gmail.com', '099 999 9999', NULL, '12345'),
(137, 'Shane', NULL, 'Jackson', '2002-05-28', 1, '2013-08-18 16:53:46', 2013, 'malengadev133@gmail.com', '099 999 9999', NULL, '12345'),
(138, 'Petrus', NULL, 'Venter', '2002-04-19', 1, '2013-08-18 16:53:46', 2013, 'malengadev135@gmail.com', '099 999 9999', NULL, '12345'),
(139, 'Lance', NULL, 'Blankenship', '2002-08-27', 1, '2013-08-18 16:53:46', 2013, 'malengadev137@gmail.com', '099 999 9999', NULL, '12345'),
(140, 'Petronella', NULL, 'Malherbe', '2004-09-22', 2, '2013-08-18 16:53:46', 2013, 'malengadev139@gmail.com', '099 999 9999', NULL, '12345'),
(149, 'Erica', NULL, 'Jonathan', '2002-08-20', 2, '2014-04-11 00:10:24', 2010, 'erics@gmal.cpm', '0889963232', NULL, '436110'),
(150, 'Fannie K', NULL, 'Baker', '1998-04-30', 2, '2014-04-11 00:15:16', 2013, 'ejonat@gmal.cpm', '0889963232', NULL, '510384'),
(151, 'Danielle', NULL, 'Franklin', '2000-06-21', 2, '2014-04-11 00:18:50', 2010, 'danielle@gmail.com', '0885556363', NULL, '635551'),
(152, 'Jonathan', NULL, 'Scotland', NULL, NULL, '2014-04-15 10:06:30', NULL, 'hon.scot@gm.com', '0996635222', NULL, '626481');

-- --------------------------------------------------------

--
-- Table structure for table `province`
--

CREATE TABLE `province` (
  `provinceID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `countryID` int(11) unsigned DEFAULT NULL,
  `provinceName` varchar(100) DEFAULT NULL,
  `latitude` double DEFAULT '0',
  `longitude` double DEFAULT '0',
  PRIMARY KEY (`provinceID`),
  KEY `fk001` (`countryID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `province`
--

INSERT INTO `province` (`provinceID`, `countryID`, `provinceName`, `latitude`, `longitude`) VALUES
(1, 1, 'Gauteng', 0, 0),
(2, 1, 'North West', 0, 0),
(3, 1, 'Mpumalanga', 0, 0),
(4, 1, 'Limpopo', 0, 0),
(5, 1, 'Free State', 0, 0),
(6, 1, 'Kwa Zulu Natal', 0, 0),
(7, 1, 'Eastern Cape', 0, 0),
(8, 1, 'Western Cape', 0, 0),
(9, 1, 'Northern Cape', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `scorer`
--

CREATE TABLE `scorer` (
  `scorerID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `golfGroupID` int(10) unsigned NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  `cellphone` varchar(50) NOT NULL,
  `dateRegistered` datetime NOT NULL,
  `pin` varchar(50) NOT NULL,
  PRIMARY KEY (`scorerID`),
  KEY `golfGroupID` (`golfGroupID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `scorer`
--

INSERT INTO `scorer` (`scorerID`, `golfGroupID`, `firstName`, `lastName`, `email`, `cellphone`, `dateRegistered`, `pin`) VALUES
(1, 10, 'Jonathan', 'Scotland', 'hon.scot@gm.com', '0996635222', '2014-04-15 10:13:47', '473000'),
(2, 10, 'Jennifer', 'Lopez', 'lopez.jen@gm.com', '0996653235', '2014-04-15 10:15:05', '787721');

-- --------------------------------------------------------

--
-- Table structure for table `tournament`
--

CREATE TABLE `tournament` (
  `tournamentID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tourneyName` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `clubID` int(11) unsigned NOT NULL,
  `holesPerRound` int(10) unsigned NOT NULL DEFAULT '18',
  `par` int(10) unsigned NOT NULL DEFAULT '72',
  `closingDate` datetime DEFAULT NULL,
  `endDate` datetime NOT NULL,
  `startDate` datetime NOT NULL,
  `golfRounds` int(11) unsigned NOT NULL,
  `golfGroupID` int(11) unsigned NOT NULL,
  `closedForScoringFlag` int(10) unsigned NOT NULL,
  `closedForRegistrationFlag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`tournamentID`),
  UNIQUE KEY `golfGroupID` (`golfGroupID`,`tourneyName`,`startDate`),
  KEY `fk1` (`clubID`),
  KEY `fkgg003` (`golfGroupID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=38 ;

--
-- Dumping data for table `tournament`
--

INSERT INTO `tournament` (`tournamentID`, `tourneyName`, `clubID`, `holesPerRound`, `par`, `closingDate`, `endDate`, `startDate`, `golfRounds`, `golfGroupID`, `closedForScoringFlag`, `closedForRegistrationFlag`) VALUES
(17, 'Woody Woodpecker Classic', 4, 18, 72, NULL, '2014-04-20 12:00:00', '2014-04-19 12:00:00', 2, 10, 0, 0),
(27, 'Thanks Tournament', 5, 18, 72, NULL, '2014-05-10 00:00:00', '2014-05-10 00:00:00', 1, 10, 0, 1),
(29, 'Oppenheimer Classic 2014', 4, 18, 72, NULL, '2014-05-11 00:00:00', '2014-05-11 00:00:00', 1, 10, 1, 1),
(31, 'Easter Tournament', 5, 18, 72, NULL, '2014-04-20 12:00:00', '2014-04-19 12:00:00', 2, 10, 0, 1),
(32, 'Malenga Memorial', 2, 18, 72, NULL, '2014-04-25 12:00:00', '2014-04-22 12:00:00', 4, 10, 0, 0),
(33, 'Kiddies Invitational', 2, 9, 36, NULL, '2014-04-25 12:00:00', '2014-04-22 12:00:00', 4, 10, 0, 1),
(34, 'Pecanwood Classic', 1, 18, 72, NULL, '2014-04-25 12:00:00', '2014-04-24 12:00:00', 2, 10, 0, 0),
(35, 'LiveTest Classic', 7, 18, 72, NULL, '2014-04-05 12:00:00', '2014-04-04 12:00:00', 2, 10, 0, 1),
(36, 'One Round Slugfest', 3, 18, 72, NULL, '2014-06-07 12:00:00', '2014-06-07 12:00:00', 1, 10, 0, 1),
(37, 'GoTee Tournament', 1, 18, 72, NULL, '2014-04-23 12:00:00', '2014-04-23 12:00:00', 1, 10, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tournamentCourse`
--

CREATE TABLE `tournamentCourse` (
  `tournamentCourseID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tournamentID` int(10) unsigned NOT NULL,
  `clubCourseID` int(10) unsigned NOT NULL,
  `round` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`tournamentCourseID`),
  UNIQUE KEY `tournamentID_2` (`tournamentID`,`clubCourseID`,`round`),
  KEY `tournamentID` (`tournamentID`),
  KEY `clubCourseID` (`clubCourseID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `tournamentCourse`
--

INSERT INTO `tournamentCourse` (`tournamentCourseID`, `tournamentID`, `clubCourseID`, `round`) VALUES
(6, 17, 6, 1),
(7, 17, 6, 2),
(4, 27, 7, 1),
(5, 29, 6, 1),
(9, 31, 7, 1),
(10, 31, 7, 2),
(12, 32, 4, 1),
(14, 32, 4, 2),
(13, 32, 4, 3),
(11, 32, 4, 4),
(16, 33, 4, 1),
(15, 33, 4, 2),
(17, 33, 4, 3),
(18, 33, 4, 4),
(19, 34, 3, 1),
(20, 34, 3, 2),
(22, 35, 8, 1),
(21, 35, 8, 2),
(23, 36, 5, 1),
(24, 37, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tournamentVolunteer`
--

CREATE TABLE `tournamentVolunteer` (
  `tournamentVolunteerID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tournamentID` int(11) unsigned NOT NULL,
  `volunteerID` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`tournamentVolunteerID`),
  UNIQUE KEY `ix900` (`tournamentID`,`volunteerID`),
  KEY `fkt001` (`tournamentID`),
  KEY `fkt002` (`volunteerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tourneyScoreByRound`
--

CREATE TABLE `tourneyScoreByRound` (
  `tourneyScoreByRoundID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `leaderBoardID` int(10) unsigned NOT NULL,
  `clubCourseID` int(10) unsigned NOT NULL,
  `holesPerRound` int(10) unsigned NOT NULL DEFAULT '18',
  `par` int(10) unsigned NOT NULL DEFAULT '72',
  `teeTime` datetime DEFAULT NULL,
  `tee` int(11) NOT NULL DEFAULT '1',
  `score1` int(11) DEFAULT '0',
  `score2` int(11) DEFAULT '0',
  `score3` int(11) DEFAULT '0',
  `score4` int(11) DEFAULT '0',
  `score5` int(11) DEFAULT '0',
  `score6` int(11) DEFAULT '0',
  `score7` int(11) DEFAULT '0',
  `score8` int(11) DEFAULT '0',
  `score9` int(11) DEFAULT '0',
  `score10` int(11) DEFAULT '0',
  `score11` int(11) DEFAULT '0',
  `score12` int(11) DEFAULT '0',
  `score13` int(11) DEFAULT '0',
  `score14` int(11) DEFAULT '0',
  `score15` int(11) DEFAULT '0',
  `score16` int(11) DEFAULT '0',
  `score17` int(11) DEFAULT '0',
  `score18` int(11) DEFAULT '0',
  `golfRound` int(11) DEFAULT '0',
  `totalScore` int(11) unsigned DEFAULT '0',
  `tournamentIDx` int(10) unsigned NOT NULL,
  PRIMARY KEY (`tourneyScoreByRoundID`),
  KEY `golfRound` (`golfRound`),
  KEY `clubCourseID` (`clubCourseID`),
  KEY `leaderBoardID` (`leaderBoardID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=875 ;

--
-- Dumping data for table `tourneyScoreByRound`
--

INSERT INTO `tourneyScoreByRound` (`tourneyScoreByRoundID`, `leaderBoardID`, `clubCourseID`, `holesPerRound`, `par`, `teeTime`, `tee`, `score1`, `score2`, `score3`, `score4`, `score5`, `score6`, `score7`, `score8`, `score9`, `score10`, `score11`, `score12`, `score13`, `score14`, `score15`, `score16`, `score17`, `score18`, `golfRound`, `totalScore`, `tournamentIDx`) VALUES
(410, 182, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 73, 29),
(411, 183, 6, 18, 0, NULL, 1, 4, 4, 4, 2, 4, 3, 4, 4, 4, 5, 4, 5, 4, 4, 4, 4, 4, 4, 1, 71, 29),
(413, 185, 6, 18, 0, NULL, 1, 7, 5, 4, 6, 4, 4, 5, 4, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 81, 29),
(414, 186, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 5, 4, 4, 4, 5, 4, 5, 4, 4, 4, 5, 4, 4, 1, 76, 29),
(415, 187, 6, 18, 0, NULL, 1, 4, 4, 5, 4, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 3, 1, 74, 29),
(416, 188, 6, 18, 0, NULL, 1, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 4, 4, 6, 4, 1, 78, 29),
(417, 189, 6, 18, 0, NULL, 1, 4, 4, 4, 3, 4, 5, 3, 4, 4, 4, 2, 4, 4, 4, 4, 4, 4, 4, 1, 69, 29),
(418, 190, 6, 18, 0, NULL, 1, 4, 4, 5, 4, 4, 4, 4, 5, 4, 4, 4, 5, 5, 4, 4, 6, 4, 5, 1, 79, 29),
(419, 191, 6, 18, 0, NULL, 1, 4, 4, 4, 3, 4, 3, 3, 3, 4, 4, 4, 4, 4, 3, 4, 5, 5, 4, 1, 69, 29),
(420, 192, 6, 18, 0, NULL, 1, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 1, 74, 29),
(421, 193, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 3, 2, 4, 4, 4, 1, 72, 29),
(422, 194, 6, 18, 0, NULL, 1, 4, 4, 3, 3, 3, 4, 4, 4, 4, 4, 4, 3, 5, 3, 4, 4, 3, 4, 1, 67, 29),
(423, 195, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 72, 29),
(424, 196, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 1, 73, 29),
(425, 197, 6, 18, 0, NULL, 1, 4, 4, 4, 3, 5, 4, 4, 4, 4, 4, 5, 4, 4, 3, 4, 4, 4, 4, 1, 72, 29),
(426, 198, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 72, 29),
(427, 199, 6, 18, 0, NULL, 1, 4, 4, 4, 5, 4, 4, 4, 5, 4, 5, 5, 5, 4, 4, 4, 4, 4, 4, 1, 77, 29),
(428, 200, 6, 18, 0, NULL, 1, 4, 4, 4, 3, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 1, 72, 29),
(429, 201, 6, 18, 0, NULL, 1, 4, 4, 4, 3, 3, 4, 4, 3, 4, 4, 4, 2, 4, 4, 5, 4, 4, 4, 1, 68, 29),
(430, 202, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 4, 1, 71, 29),
(431, 203, 6, 18, 0, NULL, 1, 4, 4, 4, 5, 6, 4, 4, 5, 6, 5, 6, 5, 4, 6, 4, 4, 5, 4, 1, 85, 29),
(433, 206, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 5, 5, 4, 4, 4, 4, 3, 4, 1, 72, 29),
(434, 207, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 7, 4, 5, 4, 1, 77, 29),
(435, 208, 6, 18, 0, NULL, 1, 5, 5, 6, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 78, 29),
(436, 209, 6, 18, 0, NULL, 1, 4, 4, 4, 5, 4, 5, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 1, 75, 29),
(437, 210, 6, 18, 0, NULL, 1, 5, 4, 5, 5, 6, 4, 4, 5, 4, 5, 4, 5, 4, 4, 4, 5, 4, 4, 1, 81, 29),
(438, 211, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 3, 4, 4, 4, 4, 4, 3, 5, 4, 3, 4, 4, 4, 4, 1, 70, 29),
(439, 212, 6, 18, 0, NULL, 1, 4, 5, 5, 4, 5, 5, 4, 4, 4, 4, 5, 4, 4, 4, 5, 4, 4, 6, 1, 80, 29),
(440, 213, 6, 18, 0, NULL, 1, 4, 4, 4, 2, 4, 4, 3, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 4, 1, 68, 29),
(441, 214, 6, 18, 0, NULL, 1, 4, 4, 5, 4, 5, 4, 4, 5, 4, 4, 4, 5, 4, 4, 4, 3, 4, 4, 1, 75, 29),
(442, 215, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 5, 5, 4, 4, 1, 72, 29),
(443, 216, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 6, 5, 6, 5, 5, 4, 7, 4, 4, 4, 4, 1, 82, 29),
(444, 217, 6, 18, 0, NULL, 1, 4, 4, 3, 4, 4, 5, 3, 3, 4, 4, 4, 4, 4, 5, 4, 3, 4, 3, 1, 69, 29),
(445, 218, 6, 18, 0, NULL, 1, 4, 4, 3, 4, 5, 3, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 72, 29),
(446, 219, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 1, 72, 29),
(447, 220, 6, 18, 0, NULL, 1, 4, 5, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 6, 4, 6, 4, 1, 78, 29),
(448, 221, 6, 18, 0, NULL, 1, 4, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 3, 4, 4, 4, 1, 74, 29),
(449, 222, 6, 18, 0, NULL, 1, 4, 4, 2, 4, 4, 3, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 1, 70, 29),
(450, 223, 6, 18, 0, NULL, 1, 4, 4, 4, 5, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 5, 3, 4, 4, 1, 72, 29),
(451, 224, 6, 18, 0, NULL, 1, 4, 5, 5, 4, 5, 5, 5, 5, 6, 5, 4, 5, 4, 4, 4, 5, 4, 4, 1, 83, 29),
(452, 225, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 3, 4, 6, 3, 3, 4, 1, 72, 29),
(453, 226, 6, 18, 0, NULL, 1, 4, 4, 5, 4, 4, 5, 4, 4, 5, 4, 4, 4, 5, 5, 5, 5, 4, 4, 1, 79, 29),
(454, 227, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 5, 4, 6, 8, 4, 5, 4, 4, 4, 4, 4, 4, 4, 5, 1, 81, 29),
(455, 228, 6, 18, 0, NULL, 1, 4, 4, 5, 4, 4, 4, 5, 4, 4, 5, 4, 5, 4, 5, 4, 5, 5, 4, 1, 79, 29),
(456, 229, 6, 18, 0, NULL, 1, 5, 4, 5, 4, 5, 5, 5, 4, 5, 4, 5, 4, 4, 4, 4, 4, 4, 4, 1, 79, 29),
(457, 230, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 1, 70, 29),
(458, 231, 6, 18, 0, NULL, 1, 4, 5, 4, 4, 5, 4, 4, 4, 5, 4, 4, 5, 5, 4, 5, 4, 5, 5, 1, 80, 29),
(459, 232, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 5, 4, 4, 4, 5, 3, 4, 4, 1, 71, 29),
(460, 233, 6, 18, 0, NULL, 1, 5, 5, 6, 4, 4, 6, 4, 4, 6, 4, 4, 5, 4, 4, 4, 4, 5, 4, 1, 82, 29),
(461, 237, 6, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 5, 4, 4, 4, 2, 4, 4, 4, 6, 4, 4, 5, 4, 2, 74, 17),
(462, 237, 6, 18, 0, NULL, 1, 4, 4, 4, 5, 7, 4, 4, 4, 4, 2, 4, 4, 4, 4, 2, 4, 4, 5, 1, 73, 17),
(463, 238, 6, 18, 0, NULL, 1, 4, 4, 4, 6, 5, 4, 4, 2, 4, 4, 4, 3, 5, 6, 4, 4, 4, 4, 1, 75, 17),
(464, 238, 6, 18, 0, NULL, 1, 5, 4, 5, 4, 4, 7, 4, 4, 2, 0, 0, 0, 4, 4, 4, 4, 4, 4, 2, 63, 17),
(465, 239, 6, 18, 0, NULL, 1, 4, 4, 4, 6, 4, 4, 2, 4, 4, 4, 4, 6, 2, 5, 5, 4, 4, 4, 2, 74, 17),
(466, 239, 6, 18, 0, NULL, 1, 6, 4, 3, 4, 2, 4, 5, 4, 4, 4, 4, 4, 6, 4, 4, 4, 4, 4, 1, 74, 17),
(467, 240, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(468, 240, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(469, 241, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(470, 241, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(471, 242, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(472, 242, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(473, 243, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(474, 243, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(475, 244, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(476, 244, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(477, 245, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(478, 245, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(479, 246, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(480, 246, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(481, 247, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(482, 247, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(483, 248, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(484, 248, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(485, 249, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(486, 249, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(487, 250, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(488, 250, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(489, 251, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(490, 251, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(491, 252, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(492, 252, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(493, 253, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(494, 253, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(495, 254, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(496, 254, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(497, 255, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(498, 255, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(499, 256, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(500, 256, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(501, 257, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(502, 257, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(503, 258, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(504, 258, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(505, 259, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(506, 259, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(507, 260, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(508, 260, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(509, 261, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(510, 261, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(511, 262, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(512, 262, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(513, 263, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(514, 263, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(515, 264, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(516, 264, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(517, 265, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(518, 265, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(519, 266, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(520, 266, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(521, 267, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(522, 267, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(523, 268, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(524, 268, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(525, 269, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(526, 269, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(527, 270, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(528, 270, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(529, 271, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(530, 271, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(531, 272, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(532, 272, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(533, 273, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(534, 273, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(535, 274, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(536, 274, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(537, 275, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(538, 275, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(539, 276, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(540, 276, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(541, 277, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(542, 277, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(543, 278, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(544, 278, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(545, 279, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(546, 279, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(547, 280, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(548, 280, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(549, 281, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(550, 281, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(551, 282, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(552, 282, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(553, 283, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(554, 283, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(555, 284, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(556, 284, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(557, 285, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(558, 285, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(559, 286, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(560, 286, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(561, 287, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(562, 287, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(563, 288, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(564, 288, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(565, 289, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(566, 289, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(567, 290, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(568, 290, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(569, 291, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(570, 291, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(571, 292, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(572, 292, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(573, 293, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(574, 293, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(575, 294, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(576, 294, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(577, 295, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(578, 295, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(579, 296, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(580, 296, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(581, 297, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(582, 297, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(583, 298, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(584, 298, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(585, 299, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(586, 299, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(587, 300, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(588, 300, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(589, 301, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(590, 301, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(591, 302, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(592, 302, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(593, 303, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(594, 303, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(595, 304, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(596, 304, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(597, 305, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(598, 305, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(599, 306, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(600, 306, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(601, 307, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(602, 307, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(603, 308, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17),
(604, 308, 6, 18, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 17),
(606, 310, 7, 18, 0, NULL, 1, 4, 5, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 74, 31),
(607, 310, 7, 18, 0, '2014-04-19 07:16:00', 1, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 5, 4, 4, 4, 3, 4, 4, 1, 73, 31),
(608, 311, 7, 18, 0, '2014-04-19 07:16:00', 1, 4, 4, 4, 5, 6, 4, 5, 4, 4, 4, 4, 5, 4, 5, 5, 4, 4, 4, 1, 79, 31),
(609, 311, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 6, 5, 4, 4, 4, 2, 77, 31),
(610, 312, 7, 18, 0, '2014-04-19 07:16:00', 1, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 5, 5, 4, 4, 5, 4, 4, 1, 74, 31),
(611, 312, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 2, 70, 31),
(612, 313, 7, 18, 0, '2014-04-19 07:25:00', 10, 4, 4, 5, 4, 4, 4, 6, 5, 6, 4, 5, 4, 4, 4, 4, 5, 3, 4, 1, 79, 31),
(613, 313, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 4, 2, 74, 31),
(614, 314, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 2, 3, 3, 4, 4, 5, 5, 2, 69, 31),
(615, 314, 7, 18, 0, '2014-04-19 07:30:00', 10, 4, 4, 5, 4, 5, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 4, 1, 72, 31),
(616, 315, 7, 18, 0, '2014-04-19 07:30:00', 10, 4, 4, 4, 4, 4, 4, 4, 5, 4, 5, 4, 3, 4, 4, 3, 4, 4, 4, 1, 72, 31),
(617, 315, 7, 18, 0, NULL, 1, 4, 4, 4, 5, 4, 5, 4, 5, 4, 5, 4, 4, 5, 4, 4, 4, 4, 4, 2, 77, 31),
(618, 316, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 5, 4, 4, 2, 75, 31),
(619, 316, 7, 18, 0, '2014-04-19 07:35:00', 10, 4, 4, 4, 4, 4, 4, 3, 3, 4, 4, 4, 6, 5, 5, 4, 4, 4, 4, 1, 74, 31),
(620, 317, 7, 18, 0, '2014-04-19 07:35:00', 10, 4, 4, 3, 3, 4, 4, 4, 5, 4, 4, 4, 4, 4, 5, 4, 4, 3, 4, 1, 71, 31),
(621, 317, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 2, 5, 3, 4, 4, 4, 4, 4, 5, 3, 4, 3, 5, 4, 2, 70, 31),
(622, 318, 7, 18, 0, NULL, 1, 5, 5, 4, 4, 5, 6, 5, 4, 6, 4, 4, 4, 5, 6, 5, 4, 4, 5, 2, 85, 31),
(623, 318, 7, 18, 0, '2014-04-19 07:40:00', 10, 4, 4, 4, 5, 4, 4, 4, 3, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 1, 73, 31),
(624, 319, 7, 18, 0, NULL, 1, 4, 4, 5, 4, 4, 5, 4, 5, 4, 4, 5, 4, 4, 4, 4, 4, 4, 5, 2, 77, 31),
(625, 319, 7, 18, 0, '2014-04-19 07:40:00', 10, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 1, 75, 31),
(626, 320, 7, 18, 0, '2014-04-19 07:45:00', 1, 4, 5, 4, 4, 5, 4, 4, 4, 5, 5, 4, 4, 5, 4, 4, 4, 4, 4, 1, 77, 31),
(627, 320, 7, 18, 0, NULL, 1, 4, 4, 5, 4, 5, 4, 4, 5, 4, 5, 4, 5, 4, 4, 4, 5, 4, 4, 2, 78, 31),
(630, 322, 7, 18, 0, '2014-04-19 07:45:00', 1, 4, 4, 5, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 5, 3, 4, 3, 4, 1, 73, 31),
(631, 322, 7, 18, 0, NULL, 1, 4, 5, 4, 3, 4, 3, 3, 4, 4, 4, 3, 4, 4, 3, 5, 4, 4, 5, 2, 70, 31),
(632, 323, 7, 18, 0, NULL, 1, 4, 4, 5, 4, 4, 5, 5, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 2, 76, 31),
(633, 323, 7, 18, 0, '2014-04-19 07:45:00', 1, 4, 4, 4, 4, 5, 4, 5, 6, 4, 6, 4, 4, 4, 4, 4, 4, 4, 5, 1, 79, 31),
(634, 324, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 3, 5, 3, 3, 4, 4, 4, 2, 68, 31),
(635, 324, 7, 18, 0, '2014-04-19 07:00:00', 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 72, 31),
(636, 325, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 4, 5, 4, 3, 3, 4, 4, 4, 2, 70, 31),
(637, 325, 7, 18, 0, '2014-04-19 11:30:00', 1, 4, 4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4, 5, 1, 76, 31),
(638, 326, 7, 18, 0, '2014-04-19 07:40:00', 10, 4, 4, 4, 5, 4, 5, 3, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 1, 74, 31),
(639, 326, 7, 18, 0, NULL, 1, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 4, 2, 66, 31),
(640, 327, 7, 18, 0, NULL, 1, 4, 4, 4, 3, 3, 5, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 70, 31),
(641, 327, 7, 18, 0, '2014-04-19 08:30:00', 10, 4, 5, 6, 4, 4, 5, 3, 4, 4, 4, 5, 4, 4, 3, 4, 4, 4, 5, 1, 76, 31),
(642, 328, 7, 18, 0, NULL, 1, 4, 4, 5, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 5, 5, 4, 2, 76, 31),
(643, 328, 7, 18, 0, '2014-04-19 07:15:00', 1, 4, 4, 4, 5, 3, 4, 4, 3, 3, 4, 4, 4, 4, 6, 4, 4, 4, 4, 1, 72, 31),
(644, 329, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 5, 4, 4, 4, 4, 3, 4, 4, 2, 71, 31),
(645, 329, 7, 18, 0, '2014-04-19 01:40:00', 10, 4, 5, 4, 4, 5, 4, 4, 4, 4, 4, 3, 4, 4, 3, 4, 4, 5, 5, 1, 74, 31),
(646, 330, 7, 18, 0, '2014-04-19 06:35:00', 10, 4, 4, 3, 3, 4, 3, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 4, 1, 68, 31),
(647, 330, 7, 18, 0, NULL, 1, 4, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 4, 5, 4, 5, 4, 4, 5, 2, 77, 31),
(648, 331, 7, 18, 0, '2014-04-19 09:30:00', 1, 4, 4, 5, 4, 4, 4, 4, 4, 4, 3, 4, 3, 4, 4, 4, 4, 5, 4, 1, 72, 31),
(649, 331, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 6, 10, 6, 4, 4, 4, 4, 12, 4, 4, 4, 11, 10, 4, 2, 103, 31),
(650, 332, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 5, 4, 4, 4, 5, 4, 4, 2, 75, 31),
(651, 332, 7, 18, 0, '2014-04-19 08:00:00', 1, 5, 6, 6, 5, 6, 10, 7, 4, 4, 5, 5, 5, 5, 12, 4, 7, 5, 5, 1, 106, 31),
(652, 333, 7, 18, 0, NULL, 1, 4, 4, 5, 4, 5, 4, 6, 5, 4, 6, 4, 4, 4, 5, 4, 5, 4, 5, 2, 82, 31),
(653, 333, 7, 18, 0, '2014-04-19 06:20:00', 10, 4, 4, 5, 4, 4, 5, 4, 5, 4, 5, 4, 5, 7, 5, 4, 5, 6, 4, 1, 84, 31),
(654, 334, 7, 18, 0, NULL, 1, 4, 4, 5, 4, 4, 5, 4, 5, 4, 4, 4, 5, 4, 5, 4, 3, 4, 4, 2, 76, 31),
(655, 334, 7, 18, 0, '2014-04-19 10:40:00', 1, 4, 5, 4, 6, 4, 4, 4, 5, 4, 5, 4, 5, 4, 4, 4, 5, 3, 4, 1, 78, 31),
(656, 335, 7, 18, 0, '2014-04-19 08:20:00', 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 4, 4, 4, 4, 4, 1, 74, 31),
(657, 335, 7, 18, 0, NULL, 1, 4, 4, 5, 4, 5, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 7, 4, 2, 78, 31),
(658, 336, 7, 18, 0, '2014-04-19 07:35:00', 10, 4, 4, 5, 4, 4, 5, 4, 5, 4, 5, 4, 5, 4, 4, 4, 4, 4, 4, 1, 77, 31),
(659, 336, 7, 18, 0, NULL, 1, 4, 4, 5, 4, 6, 4, 4, 6, 4, 5, 4, 5, 4, 5, 4, 4, 4, 4, 2, 80, 31),
(660, 337, 7, 18, 0, '2014-04-19 08:15:00', 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 3, 4, 4, 4, 5, 4, 1, 73, 31),
(661, 337, 7, 18, 0, NULL, 1, 5, 4, 4, 4, 5, 5, 4, 4, 4, 4, 5, 4, 4, 4, 3, 3, 4, 4, 2, 74, 31),
(662, 338, 7, 18, 0, '2014-04-19 08:15:00', 1, 6, 4, 5, 5, 4, 4, 3, 3, 4, 4, 4, 4, 3, 8, 4, 3, 4, 4, 1, 76, 31),
(663, 338, 7, 18, 0, NULL, 1, 4, 5, 4, 6, 3, 3, 7, 4, 6, 6, 3, 3, 7, 3, 3, 3, 7, 3, 2, 80, 31),
(664, 339, 7, 18, 0, NULL, 1, 4, 4, 5, 5, 3, 3, 5, 4, 3, 4, 4, 3, 4, 3, 4, 6, 6, 6, 2, 76, 31),
(665, 339, 7, 18, 0, '2014-04-19 08:15:00', 1, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 5, 9, 7, 4, 10, 4, 4, 4, 1, 88, 31),
(666, 340, 7, 18, 0, '2014-04-19 07:40:00', 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 72, 31),
(667, 340, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 3, 2, 72, 31),
(670, 342, 7, 18, 0, '2014-04-19 07:15:00', 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 5, 4, 5, 5, 4, 1, 76, 31),
(671, 342, 7, 18, 0, NULL, 1, 5, 4, 5, 4, 5, 4, 5, 4, 4, 5, 4, 5, 4, 5, 4, 4, 4, 4, 2, 79, 31),
(672, 343, 7, 18, 0, '2014-04-19 08:25:00', 1, 4, 5, 5, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 5, 4, 5, 4, 4, 1, 77, 31),
(673, 343, 7, 18, 0, NULL, 1, 4, 5, 4, 4, 5, 4, 4, 5, 4, 5, 5, 4, 4, 5, 4, 5, 5, 6, 2, 82, 31),
(674, 344, 7, 18, 0, '2014-04-19 08:25:00', 1, 6, 7, 6, 6, 7, 6, 6, 5, 5, 6, 8, 4, 6, 6, 7, 4, 6, 5, 1, 106, 31),
(675, 344, 7, 18, 0, NULL, 1, 5, 5, 6, 4, 5, 5, 4, 5, 7, 5, 5, 6, 8, 7, 7, 7, 4, 4, 2, 99, 31),
(676, 345, 7, 18, 0, '2014-04-19 08:30:00', 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 1, 71, 31),
(677, 345, 7, 18, 0, NULL, 1, 4, 4, 5, 4, 4, 4, 4, 3, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 2, 74, 31),
(678, 346, 7, 18, 0, '2014-04-19 08:30:00', 1, 4, 5, 4, 4, 3, 4, 3, 4, 4, 4, 5, 3, 4, 4, 3, 4, 4, 5, 1, 71, 31),
(679, 346, 7, 18, 0, NULL, 1, 4, 5, 5, 5, 4, 4, 5, 4, 5, 4, 4, 2, 4, 4, 4, 4, 4, 4, 2, 75, 31),
(680, 347, 7, 18, 0, '2014-04-19 08:40:00', 1, 4, 4, 5, 5, 4, 4, 4, 4, 4, 4, 3, 2, 3, 3, 4, 4, 3, 3, 1, 67, 31),
(681, 347, 7, 18, 0, NULL, 1, 4, 4, 5, 5, 5, 3, 4, 2, 4, 4, 5, 4, 5, 4, 4, 4, 4, 4, 2, 74, 31),
(682, 348, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 72, 31),
(683, 348, 7, 18, 0, NULL, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 72, 31),
(684, 349, 7, 18, 0, NULL, 1, 4, 4, 4, 3, 4, 4, 3, 4, 3, 5, 3, 4, 4, 3, 4, 4, 4, 4, 2, 68, 31),
(685, 349, 7, 18, 0, '2014-04-19 07:20:00', 1, 4, 3, 3, 4, 3, 3, 4, 4, 3, 4, 3, 4, 3, 4, 4, 4, 5, 3, 1, 65, 31),
(686, 350, 7, 18, 0, NULL, 1, 4, 3, 3, 4, 4, 4, 3, 3, 4, 4, 5, 4, 4, 3, 4, 4, 4, 4, 2, 68, 31),
(687, 350, 7, 18, 0, '2014-04-19 07:20:00', 1, 4, 5, 3, 3, 5, 4, 4, 5, 4, 5, 4, 5, 4, 4, 4, 4, 4, 4, 1, 75, 31),
(688, 351, 7, 18, 0, '2014-04-19 07:30:00', 1, 4, 4, 5, 5, 5, 4, 5, 5, 6, 6, 4, 6, 4, 4, 5, 5, 5, 4, 1, 86, 31),
(689, 351, 7, 18, 0, NULL, 1, 4, 5, 4, 4, 4, 5, 4, 4, 5, 4, 4, 7, 4, 4, 4, 5, 4, 4, 2, 79, 31),
(690, 352, 7, 18, 0, NULL, 1, 4, 5, 6, 4, 4, 6, 4, 4, 5, 5, 4, 5, 4, 6, 4, 4, 4, 5, 2, 83, 31),
(691, 352, 7, 18, 0, NULL, 1, 5, 5, 5, 6, 4, 6, 5, 4, 5, 4, 5, 4, 4, 5, 11, 4, 4, 5, 1, 91, 31),
(692, 353, 7, 18, 0, NULL, 1, 5, 4, 5, 4, 4, 4, 5, 4, 4, 5, 4, 5, 4, 5, 4, 4, 5, 4, 2, 79, 31),
(693, 353, 7, 18, 0, '2014-04-19 08:20:00', 1, 4, 4, 4, 4, 4, 5, 4, 6, 4, 4, 4, 5, 5, 5, 4, 4, 3, 4, 1, 77, 31),
(694, 354, 7, 18, 0, '2014-04-19 08:10:00', 1, 4, 5, 5, 5, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 5, 5, 4, 4, 1, 78, 31),
(695, 354, 7, 18, 0, NULL, 1, 5, 4, 4, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 2, 76, 31),
(696, 355, 7, 18, 0, NULL, 1, 4, 5, 5, 5, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 6, 4, 2, 78, 31),
(697, 355, 7, 18, 0, '2014-04-19 07:20:00', 10, 4, 4, 4, 4, 4, 4, 4, 6, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 1, 75, 31),
(700, 359, 7, 18, 0, '2014-05-10 07:00:00', 1, 4, 4, 4, 5, 4, 4, 3, 3, 4, 4, 4, 5, 5, 3, 5, 3, 4, 4, 1, 72, 27),
(701, 360, 7, 18, 0, '2014-05-10 07:00:00', 1, 4, 4, 5, 4, 4, 3, 4, 4, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 1, 70, 27),
(702, 361, 7, 18, 0, '2014-05-10 06:10:00', 1, 4, 5, 5, 4, 4, 4, 4, 4, 4, 4, 5, 4, 3, 5, 3, 3, 3, 4, 1, 72, 27),
(703, 362, 7, 18, 0, '2014-05-10 06:10:00', 1, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 5, 4, 5, 5, 2, 4, 3, 1, 73, 27),
(704, 363, 7, 18, 0, '2014-05-10 07:30:00', 1, 4, 4, 4, 5, 4, 4, 4, 5, 4, 4, 5, 4, 4, 5, 4, 4, 4, 6, 1, 78, 27),
(705, 364, 7, 18, 0, '2014-05-10 07:30:00', 1, 4, 4, 4, 3, 4, 4, 4, 5, 3, 4, 4, 3, 4, 4, 4, 5, 4, 4, 1, 71, 27),
(706, 365, 7, 18, 0, '2014-05-10 07:30:00', 1, 4, 4, 4, 4, 4, 6, 5, 4, 4, 5, 4, 5, 5, 4, 3, 4, 3, 4, 1, 76, 27),
(707, 366, 7, 18, 0, '2014-05-10 07:40:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(708, 367, 7, 18, 0, '2014-05-10 07:45:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(709, 368, 7, 18, 0, '2014-05-10 07:45:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(710, 369, 7, 18, 0, '2014-05-10 07:50:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(711, 370, 7, 18, 0, '2014-05-10 06:50:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(712, 371, 7, 18, 0, '2014-05-10 06:50:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(713, 372, 7, 18, 0, '2014-05-10 07:50:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(714, 373, 7, 18, 0, '2014-05-10 07:55:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(715, 374, 7, 18, 0, '2014-05-10 08:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(716, 375, 7, 18, 0, '2014-05-10 08:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(717, 376, 7, 18, 0, '2014-05-10 08:10:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(718, 377, 7, 18, 0, '2014-05-10 08:15:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(719, 378, 7, 18, 0, '2014-05-10 08:15:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(720, 379, 7, 18, 0, '2014-05-10 08:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(721, 380, 7, 18, 0, '2014-05-10 08:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(722, 381, 7, 18, 0, '2014-05-10 08:25:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(723, 382, 7, 18, 0, '2014-05-10 08:30:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(724, 383, 7, 18, 0, '2014-05-10 07:45:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(725, 384, 7, 18, 0, '2014-05-10 08:45:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(726, 385, 7, 18, 0, '2014-05-10 08:50:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(727, 386, 7, 18, 0, '2014-05-10 09:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(728, 387, 7, 18, 0, '2014-05-10 08:40:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(729, 388, 7, 18, 0, '2014-05-10 07:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(730, 389, 7, 18, 0, '2014-05-10 09:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(731, 390, 7, 18, 0, '2014-05-10 08:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(732, 391, 7, 18, 0, '2014-05-10 08:40:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(733, 392, 7, 18, 0, '2014-05-10 08:40:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(734, 393, 7, 18, 0, '2014-05-10 08:25:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(735, 394, 7, 18, 0, '2014-05-10 08:50:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(736, 395, 7, 18, 0, '2014-05-10 08:55:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(737, 396, 7, 18, 0, '2014-05-10 09:10:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(738, 397, 7, 18, 0, '2014-05-10 09:10:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(739, 398, 7, 18, 0, '2014-05-10 08:40:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(740, 399, 7, 18, 0, '2014-05-10 08:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(741, 400, 7, 18, 0, '2014-05-10 09:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(742, 401, 7, 18, 0, '2014-05-10 09:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(743, 402, 7, 18, 0, '2014-05-10 08:40:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(744, 403, 7, 18, 0, '2014-05-10 08:10:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(745, 404, 7, 18, 0, '2014-05-10 08:30:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(746, 405, 7, 18, 0, '2014-05-10 07:50:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(747, 406, 7, 18, 0, '2014-05-10 08:15:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(748, 407, 7, 18, 0, '2014-05-10 09:30:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(749, 408, 7, 18, 0, '2014-05-10 08:35:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(750, 409, 7, 18, 0, '2014-05-10 08:50:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(751, 410, 7, 18, 0, '2014-05-10 08:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 27),
(772, 417, 4, 9, 36, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33),
(773, 417, 4, 9, 36, '2014-04-22 08:00:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 33),
(774, 417, 4, 9, 36, '2014-04-22 07:25:00', 1, 4, 4, 4, 5, 6, 4, 4, 4, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 41, 33),
(775, 417, 4, 9, 36, '2014-04-22 08:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 5, 4, 1, 37, 33),
(776, 418, 4, 9, 36, '2014-04-22 07:40:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 5, 5, 4, 4, 6, 4, 5, 1, 45, 33),
(777, 418, 4, 9, 36, '2014-04-22 07:25:00', 1, 4, 5, 4, 4, 4, 4, 4, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 39, 33),
(778, 418, 4, 9, 36, '2014-04-22 07:40:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 33),
(779, 418, 4, 9, 36, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33),
(780, 419, 4, 9, 36, '2014-04-22 07:30:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 5, 3, 4, 3, 2, 35, 33),
(781, 419, 4, 9, 36, '2014-04-22 07:40:00', 1, 4, 5, 4, 5, 4, 4, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 41, 33),
(782, 419, 4, 9, 36, '2014-04-22 07:40:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 33),
(783, 419, 4, 9, 36, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33),
(784, 420, 3, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 34),
(785, 420, 3, 18, 72, '2014-04-24 08:30:00', 10, 3, 4, 3, 4, 4, 3, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 33, 34),
(786, 421, 3, 18, 72, '2014-04-24 08:30:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 34),
(787, 421, 3, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 34),
(788, 422, 3, 18, 72, '2014-04-24 08:25:00', 10, 4, 4, 4, 4, 5, 5, 3, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 1, 57, 34),
(789, 422, 3, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 34),
(790, 423, 4, 9, 36, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33),
(791, 423, 4, 9, 36, '2014-04-22 07:45:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 33),
(792, 423, 4, 9, 36, '2014-04-22 07:35:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 5, 4, 4, 4, 5, 4, 2, 38, 33),
(793, 423, 4, 9, 36, '2014-04-22 09:00:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 3, 4, 4, 4, 4, 4, 1, 35, 33),
(794, 424, 3, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 34),
(795, 424, 3, 18, 72, '2014-04-24 09:10:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 34),
(796, 425, 3, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 34),
(797, 425, 3, 18, 72, '2014-04-24 08:30:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 34),
(798, 426, 4, 9, 36, '2014-04-22 07:45:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 33),
(799, 426, 4, 9, 36, '2014-04-22 08:00:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 33),
(800, 426, 4, 9, 36, '2014-04-22 08:30:00', 10, 4, 4, 4, 5, 5, 4, 4, 5, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 39, 33),
(801, 426, 4, 9, 36, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33),
(802, 427, 4, 9, 36, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33),
(803, 427, 4, 9, 36, '2014-04-22 08:10:00', 10, 4, 4, 5, 4, 4, 3, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 36, 33),
(804, 427, 4, 9, 36, '2014-04-22 07:25:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 33),
(805, 427, 4, 9, 36, '2014-04-22 07:55:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 33),
(806, 428, 4, 9, 36, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33),
(807, 428, 4, 9, 36, '2014-04-22 07:55:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 33),
(808, 428, 4, 9, 36, '2014-04-22 07:30:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 33),
(809, 428, 4, 9, 36, '2014-04-22 07:20:00', 1, 4, 4, 4, 3, 4, 3, 4, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 33, 33),
(810, 429, 4, 9, 36, '2014-04-22 07:10:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 33),
(811, 429, 4, 9, 36, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33),
(812, 429, 4, 9, 36, '2014-04-22 07:30:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 4, 4, 4, 3, 3, 4, 4, 1, 32, 33),
(813, 429, 4, 9, 36, '2014-04-22 07:30:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 33),
(814, 430, 4, 9, 36, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33),
(815, 430, 4, 9, 36, '2014-04-22 07:10:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 33),
(816, 430, 4, 9, 36, '2014-04-22 07:20:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 4, 5, 5, 3, 4, 4, 4, 4, 1, 38, 33),
(817, 430, 4, 9, 36, '2014-04-22 07:25:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 33),
(818, 431, 4, 9, 36, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33),
(819, 431, 4, 9, 36, '2014-04-22 07:25:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 33),
(820, 431, 4, 9, 36, '2014-04-22 08:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 33),
(821, 431, 4, 9, 36, '2014-04-22 07:00:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 33),
(822, 432, 4, 9, 36, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33),
(823, 432, 4, 9, 36, '2014-04-22 08:20:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 5, 4, 4, 4, 1, 37, 33),
(824, 432, 4, 9, 36, '2014-04-22 08:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 33),
(825, 432, 4, 9, 36, '2014-04-22 07:00:00', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 33),
(826, 433, 4, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 32),
(827, 433, 4, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 32),
(828, 433, 4, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 32),
(829, 433, 4, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 32),
(830, 434, 8, 18, 72, '2014-04-04 07:00:00', 1, 4, 4, 4, 5, 3, 2, 4, 3, 4, 4, 3, 4, 3, 4, 3, 4, 4, 3, 1, 65, 35),
(831, 434, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(832, 435, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(833, 435, 8, 18, 72, '2014-04-04 07:00:00', 1, 4, 4, 3, 3, 4, 3, 4, 4, 3, 4, 4, 3, 4, 4, 4, 4, 4, 4, 1, 67, 35),
(834, 436, 8, 18, 72, '2014-04-04 07:00:00', 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 72, 35),
(835, 436, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(836, 437, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(837, 437, 8, 18, 72, '2014-04-04 07:00:00', 1, 4, 4, 4, 4, 3, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 70, 35),
(838, 438, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(839, 438, 8, 18, 72, '2014-04-04 07:15:00', 1, 4, 4, 3, 4, 3, 4, 3, 3, 4, 3, 4, 3, 4, 3, 4, 4, 4, 4, 1, 65, 35),
(840, 439, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(841, 439, 8, 18, 72, '2014-04-04 07:15:00', 1, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 4, 3, 4, 4, 4, 4, 4, 1, 68, 35),
(842, 440, 8, 18, 72, '2014-04-04 07:15:00', 1, 4, 4, 5, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 72, 35),
(843, 440, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(844, 441, 8, 18, 72, '2014-04-04 07:15:00', 1, 4, 5, 4, 4, 5, 4, 4, 4, 4, 5, 4, 4, 4, 4, 3, 4, 4, 4, 1, 74, 35),
(845, 441, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(846, 442, 8, 18, 72, '2014-04-04 07:30:00', 1, 4, 4, 4, 4, 3, 3, 4, 4, 2, 4, 4, 4, 4, 4, 5, 4, 4, 4, 1, 69, 35),
(847, 442, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(848, 443, 8, 18, 72, '2014-04-04 07:30:00', 1, 4, 4, 2, 3, 2, 3, 3, 3, 4, 4, 2, 4, 3, 4, 4, 4, 9, 3, 1, 65, 35),
(849, 443, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(850, 444, 8, 18, 72, '2014-04-04 07:30:00', 1, 4, 3, 4, 3, 4, 4, 4, 4, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 1, 39, 35),
(851, 444, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(852, 445, 8, 18, 72, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 35),
(853, 445, 8, 18, 72, '2014-04-04 07:30:00', 1, 4, 4, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 1, 50, 35),
(854, 446, 5, 18, 72, '2014-06-07 07:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(855, 447, 5, 18, 72, '2014-06-07 07:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(856, 448, 5, 18, 72, '2014-06-07 07:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(857, 449, 5, 18, 72, '2014-06-07 07:00:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(858, 450, 5, 18, 72, '2014-06-07 07:10:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(859, 451, 5, 18, 72, '2014-06-07 07:10:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(860, 452, 5, 18, 72, '2014-06-07 07:10:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(861, 453, 5, 18, 72, '2014-06-07 07:10:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(862, 454, 5, 18, 72, '2014-06-07 07:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(863, 455, 5, 18, 72, '2014-06-07 07:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(864, 456, 5, 18, 72, '2014-06-07 07:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(865, 457, 5, 18, 72, '2014-06-07 07:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(866, 458, 5, 18, 72, '2014-06-07 07:20:00', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 36),
(867, 459, 3, 18, 72, '2014-04-23 07:20:00', 1, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 1, 75, 37),
(868, 460, 3, 18, 72, '2014-04-23 07:00:00', 1, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 3, 4, 3, 4, 4, 4, 4, 1, 71, 37),
(869, 461, 3, 18, 72, '2014-04-23 07:20:00', 1, 4, 4, 6, 4, 4, 3, 4, 3, 4, 4, 4, 4, 3, 4, 5, 4, 5, 4, 1, 73, 37),
(870, 462, 3, 18, 72, '2014-04-23 07:00:00', 1, 4, 4, 3, 4, 3, 4, 3, 4, 5, 4, 4, 4, 3, 4, 4, 4, 5, 4, 1, 70, 37),
(871, 463, 3, 18, 72, '2014-04-23 07:20:00', 1, 4, 4, 4, 5, 4, 3, 4, 4, 4, 4, 3, 5, 4, 4, 4, 4, 4, 4, 1, 72, 37),
(872, 464, 3, 18, 72, '2014-04-23 07:00:00', 1, 4, 4, 4, 4, 5, 4, 4, 4, 4, 2, 4, 5, 4, 4, 4, 4, 4, 4, 1, 72, 37),
(873, 465, 3, 18, 72, '2014-04-23 07:00:00', 1, 4, 4, 5, 4, 4, 4, 3, 3, 4, 4, 3, 4, 4, 5, 5, 4, 4, 4, 1, 72, 37),
(874, 466, 3, 18, 72, '2014-04-23 07:20:00', 1, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 1, 70, 37);

-- --------------------------------------------------------

--
-- Table structure for table `videoClip`
--

CREATE TABLE `videoClip` (
  `videoClipID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `golfGroupID` int(10) unsigned NOT NULL,
  `tournamentID` int(10) unsigned DEFAULT NULL,
  `videoDate` datetime NOT NULL,
  `comment` text,
  `length` int(10) unsigned DEFAULT NULL,
  `youTubeID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`videoClipID`),
  KEY `golfGroupID` (`golfGroupID`),
  KEY `tournamentID` (`tournamentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `volunteer`
--

CREATE TABLE `volunteer` (
  `volunteerID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `middleName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) NOT NULL,
  `cellphone` varchar(45) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `pin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`volunteerID`),
  UNIQUE KEY `idxmail004` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `administrator`
--
ALTER TABLE `administrator`
  ADD CONSTRAINT `administrator_ibfk_1` FOREIGN KEY (`golfGroupID`) REFERENCES `golfGroup` (`golfGroupID`);

--
-- Constraints for table `agegroup`
--
ALTER TABLE `agegroup`
  ADD CONSTRAINT `agegroup_ibfk_1` FOREIGN KEY (`golfGroupID`) REFERENCES `golfGroup` (`golfGroupID`);

--
-- Constraints for table `club`
--
ALTER TABLE `club`
  ADD CONSTRAINT `club_ibfk_1` FOREIGN KEY (`provinceID`) REFERENCES `province` (`provinceID`);

--
-- Constraints for table `clubCourse`
--
ALTER TABLE `clubCourse`
  ADD CONSTRAINT `clubCourse_ibfk_1` FOREIGN KEY (`clubID`) REFERENCES `club` (`clubID`);

--
-- Constraints for table `golfGroup`
--
ALTER TABLE `golfGroup`
  ADD CONSTRAINT `golfGroup_ibfk_1` FOREIGN KEY (`countryID`) REFERENCES `country` (`countryID`);

--
-- Constraints for table `golfGroupParent`
--
ALTER TABLE `golfGroupParent`
  ADD CONSTRAINT `golfGroupParent_ibfk_1` FOREIGN KEY (`golfGroupID`) REFERENCES `golfGroup` (`golfGroupID`),
  ADD CONSTRAINT `golfGroupParent_ibfk_2` FOREIGN KEY (`parentID`) REFERENCES `parent` (`parentID`);

--
-- Constraints for table `golfGroupPlayer`
--
ALTER TABLE `golfGroupPlayer`
  ADD CONSTRAINT `golfGroupPlayer_ibfk_1` FOREIGN KEY (`golfGroupID`) REFERENCES `golfGroup` (`golfGroupID`),
  ADD CONSTRAINT `golfGroupPlayer_ibfk_2` FOREIGN KEY (`playerID`) REFERENCES `player` (`playerID`);

--
-- Constraints for table `golfGroupVolunteer`
--
ALTER TABLE `golfGroupVolunteer`
  ADD CONSTRAINT `golfGroupVolunteer_ibfk_1` FOREIGN KEY (`golfGroupID`) REFERENCES `golfGroup` (`golfGroupID`),
  ADD CONSTRAINT `golfGroupVolunteer_ibfk_2` FOREIGN KEY (`volunteerID`) REFERENCES `volunteer` (`volunteerID`);

--
-- Constraints for table `leaderBoard`
--
ALTER TABLE `leaderBoard`
  ADD CONSTRAINT `leaderBoard_ibfk_1` FOREIGN KEY (`tournamentID`) REFERENCES `tournament` (`tournamentID`),
  ADD CONSTRAINT `leaderBoard_ibfk_2` FOREIGN KEY (`playerID`) REFERENCES `player` (`playerID`),
  ADD CONSTRAINT `leaderBoard_ibfk_3` FOREIGN KEY (`ageGroupID`) REFERENCES `agegroup` (`ageGroupID`);

--
-- Constraints for table `personalPlayer`
--
ALTER TABLE `personalPlayer`
  ADD CONSTRAINT `personalPlayer_ibfk_1` FOREIGN KEY (`countryID`) REFERENCES `country` (`countryID`),
  ADD CONSTRAINT `personalPlayer_ibfk_2` FOREIGN KEY (`clubID`) REFERENCES `club` (`clubID`);

--
-- Constraints for table `personalScore`
--
ALTER TABLE `personalScore`
  ADD CONSTRAINT `personalScore_ibfk_1` FOREIGN KEY (`personalPlayerID`) REFERENCES `personalPlayer` (`personalPlayerID`),
  ADD CONSTRAINT `personalScore_ibfk_2` FOREIGN KEY (`clubID`) REFERENCES `club` (`clubID`);

--
-- Constraints for table `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT `player_ibfk_1` FOREIGN KEY (`parentID`) REFERENCES `parent` (`parentID`);

--
-- Constraints for table `province`
--
ALTER TABLE `province`
  ADD CONSTRAINT `province_ibfk_1` FOREIGN KEY (`countryID`) REFERENCES `country` (`countryID`);

--
-- Constraints for table `scorer`
--
ALTER TABLE `scorer`
  ADD CONSTRAINT `scorer_ibfk_1` FOREIGN KEY (`golfGroupID`) REFERENCES `golfGroup` (`golfGroupID`);

--
-- Constraints for table `tournament`
--
ALTER TABLE `tournament`
  ADD CONSTRAINT `tournament_ibfk_1` FOREIGN KEY (`clubID`) REFERENCES `club` (`clubID`),
  ADD CONSTRAINT `tournament_ibfk_2` FOREIGN KEY (`golfGroupID`) REFERENCES `golfGroup` (`golfGroupID`);

--
-- Constraints for table `tournamentCourse`
--
ALTER TABLE `tournamentCourse`
  ADD CONSTRAINT `tournamentCourse_ibfk_1` FOREIGN KEY (`tournamentID`) REFERENCES `tournament` (`tournamentID`),
  ADD CONSTRAINT `tournamentCourse_ibfk_2` FOREIGN KEY (`clubCourseID`) REFERENCES `clubCourse` (`clubCourseID`);

--
-- Constraints for table `tournamentVolunteer`
--
ALTER TABLE `tournamentVolunteer`
  ADD CONSTRAINT `tournamentVolunteer_ibfk_1` FOREIGN KEY (`tournamentID`) REFERENCES `tournament` (`tournamentID`),
  ADD CONSTRAINT `tournamentVolunteer_ibfk_2` FOREIGN KEY (`volunteerID`) REFERENCES `volunteer` (`volunteerID`);

--
-- Constraints for table `tourneyScoreByRound`
--
ALTER TABLE `tourneyScoreByRound`
  ADD CONSTRAINT `tourneyScoreByRound_ibfk_2` FOREIGN KEY (`clubCourseID`) REFERENCES `clubCourse` (`clubCourseID`),
  ADD CONSTRAINT `tourneyScoreByRound_ibfk_3` FOREIGN KEY (`leaderBoardID`) REFERENCES `leaderBoard` (`leaderBoardID`);

--
-- Constraints for table `videoClip`
--
ALTER TABLE `videoClip`
  ADD CONSTRAINT `videoClip_ibfk_1` FOREIGN KEY (`golfGroupID`) REFERENCES `golfGroup` (`golfGroupID`),
  ADD CONSTRAINT `videoClip_ibfk_2` FOREIGN KEY (`tournamentID`) REFERENCES `tournament` (`tournamentID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
