-- phpMyAdmin SQL Dump
-- version 3.5.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 15, 2014 at 08:32 AM
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
  PRIMARY KEY (`ageGroupID`),
  UNIQUE KEY `golfGroupID` (`golfGroupID`,`groupName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `agegroup`
--

INSERT INTO `agegroup` (`ageGroupID`, `golfGroupID`, `groupName`, `gender`, `numberOfHolesPerRound`) VALUES
(1, 1, 'Boys 5 - 6', 1, 9),
(2, 1, 'Boys 7 - 8', 1, 9),
(3, 1, 'Boys 9 - 10', 1, 9),
(4, 1, 'Boys 11 - 12', 1, 18),
(5, 1, 'Boys 13 - 14', 1, 18),
(6, 1, 'Boys 15 - 16', 1, 18),
(7, 1, 'Boys 17 - 18', 1, 18);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `club`
--

INSERT INTO `club` (`clubID`, `clubName`, `par`, `email`, `telephone`, `latitude`, `longitude`, `address`, `provinceID`) VALUES
(1, 'Pecanwood Golf & Country Club', 72, 'pecanwood.info@gmail.com', '012 244 8000', 0, 0, 'Route 512, Hartebeestpoort', 2),
(2, 'Bryanston Country Club', 72, 'bryanston.info@gmail.com', '011 677 9800', 0, 0, NULL, 1),
(3, 'Modderfontein Golf Club', 72, 'modder.info@modderfontein.co.za', '086 266 8766', 0, 0, NULL, 1),
(4, 'Pretoria Golf Club', 72, 'vermeulen@pretoriagolf.com', '082 366 8900', 0, 0, NULL, 1),
(5, 'Zwartkops Golf Club', 72, 'zwartkops.info@swartkops.co.za', '071 266 3550', 0, 0, NULL, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=194 ;

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
(193, 133, 'MGGolf temporary files cleaned up', '2014-04-15 08:10:03', 'HouseKeeper');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=141 ;

--
-- Dumping data for table `golfGroupParent`
--

INSERT INTO `golfGroupParent` (`golfGroupParentID`, `golfGroupID`, `parentID`, `dateRegistered`) VALUES
(71, 1, 71, '2013-08-18 16:53:35'),
(72, 1, 72, '2013-08-18 16:53:35'),
(73, 1, 73, '2013-08-18 16:53:36'),
(74, 1, 74, '2013-08-18 16:53:37'),
(75, 1, 75, '2013-08-18 16:53:37'),
(76, 1, 76, '2013-08-18 16:53:38'),
(77, 1, 77, '2013-08-18 16:53:38'),
(78, 1, 78, '2013-08-18 16:53:38'),
(79, 1, 79, '2013-08-18 16:53:39'),
(80, 1, 80, '2013-08-18 16:53:39'),
(81, 1, 81, '2013-08-18 16:53:39'),
(82, 1, 82, '2013-08-18 16:53:40'),
(83, 1, 83, '2013-08-18 16:53:40'),
(84, 1, 84, '2013-08-18 16:53:40'),
(85, 1, 85, '2013-08-18 16:53:40'),
(86, 1, 86, '2013-08-18 16:53:41'),
(87, 1, 87, '2013-08-18 16:53:41'),
(88, 1, 88, '2013-08-18 16:53:41'),
(89, 1, 89, '2013-08-18 16:53:41'),
(90, 1, 90, '2013-08-18 16:53:42'),
(91, 1, 91, '2013-08-18 16:53:42'),
(92, 1, 92, '2013-08-18 16:53:42'),
(93, 1, 93, '2013-08-18 16:53:43'),
(94, 1, 94, '2013-08-18 16:53:43'),
(95, 1, 95, '2013-08-18 16:53:43'),
(96, 1, 96, '2013-08-18 16:53:43'),
(97, 1, 97, '2013-08-18 16:53:44'),
(98, 1, 98, '2013-08-18 16:53:44'),
(99, 1, 99, '2013-08-18 16:53:44'),
(100, 1, 100, '2013-08-18 16:53:44'),
(101, 1, 101, '2013-08-18 16:53:44'),
(102, 1, 102, '2013-08-18 16:53:44'),
(103, 1, 103, '2013-08-18 16:53:44'),
(104, 1, 104, '2013-08-18 16:53:44'),
(105, 1, 105, '2013-08-18 16:53:44'),
(106, 1, 106, '2013-08-18 16:53:44'),
(107, 1, 107, '2013-08-18 16:53:44'),
(108, 1, 108, '2013-08-18 16:53:44'),
(109, 1, 109, '2013-08-18 16:53:45'),
(110, 1, 110, '2013-08-18 16:53:45'),
(111, 1, 111, '2013-08-18 16:53:45'),
(112, 1, 112, '2013-08-18 16:53:45'),
(113, 1, 113, '2013-08-18 16:53:45'),
(114, 1, 114, '2013-08-18 16:53:45'),
(115, 1, 115, '2013-08-18 16:53:45'),
(116, 1, 116, '2013-08-18 16:53:45'),
(117, 1, 117, '2013-08-18 16:53:45'),
(118, 1, 118, '2013-08-18 16:53:46'),
(119, 1, 119, '2013-08-18 16:53:46'),
(120, 1, 120, '2013-08-18 16:53:46'),
(121, 1, 121, '2013-08-18 16:53:46'),
(122, 1, 122, '2013-08-18 16:53:46'),
(123, 1, 123, '2013-08-18 16:53:46'),
(124, 1, 124, '2013-08-18 16:53:46'),
(125, 1, 125, '2013-08-18 16:53:46'),
(126, 1, 126, '2013-08-18 16:53:46'),
(127, 1, 127, '2013-08-18 16:53:46'),
(128, 1, 128, '2013-08-18 16:53:46'),
(129, 1, 129, '2013-08-18 16:53:46'),
(130, 1, 130, '2013-08-18 16:53:46'),
(131, 1, 131, '2013-08-18 16:53:46'),
(132, 1, 132, '2013-08-18 16:53:46'),
(133, 1, 133, '2013-08-18 16:53:46'),
(134, 1, 134, '2013-08-18 16:53:46'),
(135, 1, 135, '2013-08-18 16:53:46'),
(136, 1, 136, '2013-08-18 16:53:46'),
(137, 1, 137, '2013-08-18 16:53:46'),
(138, 1, 138, '2013-08-18 16:53:46'),
(139, 1, 139, '2013-08-18 16:53:46'),
(140, 1, 140, '2013-08-18 16:53:46');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=141 ;

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
(140, 'Petrus', NULL, 'Sithole', 'malengadev140@gmail.com', '099 999 9999', 0, '12345');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=152 ;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`playerID`, `firstName`, `middleName`, `lastName`, `dateOfBirth`, `gender`, `dateRegistered`, `yearJoined`, `email`, `cellphone`, `parentID`, `pin`) VALUES
(71, 'Lee', NULL, 'Locke', '2007-05-17', 1, '2013-08-18 16:53:34', 2013, 'malengadev1@gmail.com', '099 999 9999', NULL, '12345'),
(72, 'Harry', NULL, 'Morris', '2007-02-17', 1, '2013-08-18 16:53:35', 2013, 'malengadev3@gmail.com', '099 999 9999', NULL, '12345'),
(73, 'Newton', NULL, 'Maringa', '2007-06-01', 1, '2013-08-18 16:53:35', 2013, 'malengadev5@gmail.com', '099 999 9999', NULL, '12345'),
(74, 'Ronnie', NULL, 'Peyton', '2007-04-02', 1, '2013-08-18 16:53:36', 2013, 'malengadev7@gmail.com', '099 999 9999', NULL, '12345'),
(75, 'Sam', NULL, 'Venter', '2007-05-19', 1, '2013-08-18 16:53:37', 2013, 'malengadev9@gmail.com', '099 999 9999', NULL, '12345'),
(76, 'Sam', NULL, 'Maringa', '2007-06-27', 1, '2013-08-18 16:53:38', 2013, 'malengadev11@gmail.com', '099 999 9999', NULL, '12345'),
(77, 'Andrew', NULL, 'Williams', '2007-04-09', 1, '2013-08-18 16:53:38', 2013, 'malengadev13@gmail.com', '099 999 9999', NULL, '12345'),
(78, 'Vusi', NULL, 'Botha', '2007-11-05', 1, '2013-08-18 16:53:38', 2013, 'malengadev15@gmail.com', '099 999 9999', NULL, '12345'),
(79, 'Jack', NULL, 'Morris', '2007-02-07', 1, '2013-08-18 16:53:38', 2013, 'malengadev17@gmail.com', '099 999 9999', NULL, '12345'),
(80, 'Ronnie', NULL, 'Johnson', '2007-06-10', 1, '2013-08-18 16:53:39', 2013, 'malengadev19@gmail.com', '099 999 9999', NULL, '12345'),
(81, 'Thomas', NULL, 'Player', '2005-09-07', 1, '2013-08-18 16:53:39', 2013, 'malengadev21@gmail.com', '099 999 9999', NULL, '12345'),
(82, 'Samuel', NULL, 'Zulu', '2005-08-09', 1, '2013-08-18 16:53:40', 2013, 'malengadev23@gmail.com', '099 999 9999', NULL, '12345'),
(83, 'Shakes', NULL, 'Locke', '2005-04-18', 1, '2013-08-18 16:53:40', 2013, 'malengadev25@gmail.com', '099 999 9999', NULL, '12345'),
(84, 'McLean', NULL, 'Potgieter', '2005-05-19', 1, '2013-08-18 16:53:40', 2013, 'malengadev27@gmail.com', '099 999 9999', NULL, '12345'),
(85, 'Jack', NULL, 'Williams', '2005-05-13', 1, '2013-08-18 16:53:40', 2013, 'malengadev29@gmail.com', '099 999 9999', NULL, '12345'),
(86, 'John', NULL, 'Priest', '2005-08-21', 1, '2013-08-18 16:53:41', 2013, 'malengadev31@gmail.com', '099 999 9999', NULL, '12345'),
(87, 'William', NULL, 'Charles', '2005-09-17', 1, '2013-08-18 16:53:41', 2013, 'malengadev33@gmail.com', '099 999 9999', NULL, '12345'),
(88, 'Obakeng', NULL, 'Smythe', '2005-10-11', 1, '2013-08-18 16:53:41', 2013, 'malengadev35@gmail.com', '099 999 9999', NULL, '12345'),
(89, 'Raymond', NULL, 'Jones', '2005-08-13', 1, '2013-08-18 16:53:41', 2013, 'malengadev37@gmail.com', '099 999 9999', NULL, '12345'),
(90, 'Shakes', NULL, 'Brown', '2005-10-24', 1, '2013-08-18 16:53:41', 2013, 'malengadev39@gmail.com', '099 999 9999', NULL, '12345'),
(91, 'Jeremiah', NULL, 'Ringane', '2003-07-06', 1, '2013-08-18 16:53:42', 2013, 'malengadev41@gmail.com', '099 999 9999', NULL, '12345'),
(92, 'Sam', NULL, 'van der Merwe', '2003-08-19', 1, '2013-08-18 16:53:42', 2013, 'malengadev43@gmail.com', '099 999 9999', NULL, '12345'),
(93, 'Raymond', NULL, 'Nxasa', '2003-05-13', 1, '2013-08-18 16:53:43', 2013, 'malengadev45@gmail.com', '099 999 9999', NULL, '12345'),
(94, 'Andrew', NULL, 'Williams', '2003-10-24', 1, '2013-08-18 16:53:43', 2013, 'malengadev47@gmail.com', '099 999 9999', NULL, '12345'),
(95, 'Shane', NULL, 'Maringa', '2003-02-01', 1, '2013-08-18 16:53:43', 2013, 'malengadev49@gmail.com', '099 999 9999', NULL, '12345'),
(96, 'Sipho', NULL, 'Oosthuizen', '2003-02-05', 1, '2013-08-18 16:53:43', 2013, 'malengadev51@gmail.com', '099 999 9999', NULL, '12345'),
(97, 'Bennie', NULL, 'Oosthuizen', '2003-09-24', 1, '2013-08-18 16:53:44', 2013, 'malengadev53@gmail.com', '099 999 9999', NULL, '12345'),
(98, 'Johannes', NULL, 'Ringane', '2003-09-24', 1, '2013-08-18 16:53:44', 2013, 'malengadev55@gmail.com', '099 999 9999', NULL, '12345'),
(99, 'Jonty', NULL, 'Potgieter', '2003-07-26', 1, '2013-08-18 16:53:44', 2013, 'malengadev57@gmail.com', '099 999 9999', NULL, '12345'),
(100, 'Raymond', NULL, 'Williams', '2003-10-06', 1, '2013-08-18 16:53:44', 2013, 'malengadev59@gmail.com', '099 999 9999', NULL, '12345'),
(101, 'Jerry', NULL, 'Nxasa', '2001-09-16', 1, '2013-08-18 16:53:44', 2013, 'malengadev61@gmail.com', '099 999 9999', NULL, '12345'),
(102, 'Jeremiah', NULL, 'Locke', '2001-08-03', 1, '2013-08-18 16:53:44', 2013, 'malengadev63@gmail.com', '099 999 9999', NULL, '12345'),
(103, 'Shane', NULL, 'Sithole', '2001-05-19', 1, '2013-08-18 16:53:44', 2013, 'malengadev65@gmail.com', '099 999 9999', NULL, '12345'),
(104, 'Tommy', NULL, 'Smith', '2001-06-02', 1, '2013-08-18 16:53:44', 2013, 'malengadev67@gmail.com', '099 999 9999', NULL, '12345'),
(105, 'David', NULL, 'Els', '2001-07-17', 1, '2013-08-18 16:53:44', 2013, 'malengadev69@gmail.com', '099 999 9999', NULL, '12345'),
(106, 'Solomon', NULL, 'Schwartzel', '2001-11-08', 1, '2013-08-18 16:53:44', 2013, 'malengadev71@gmail.com', '099 999 9999', NULL, '12345'),
(107, 'Michael', NULL, 'Zungu', '2001-07-16', 1, '2013-08-18 16:53:44', 2013, 'malengadev73@gmail.com', '099 999 9999', NULL, '12345'),
(108, 'Bernard', NULL, 'Remington', '2001-05-07', 1, '2013-08-18 16:53:44', 2013, 'malengadev75@gmail.com', '099 999 9999', NULL, '12345'),
(109, 'Jonty', NULL, 'Locke', '2001-02-22', 1, '2013-08-18 16:53:44', 2013, 'malengadev77@gmail.com', '099 999 9999', NULL, '12345'),
(110, 'Harry', NULL, 'Sithole', '2001-04-12', 1, '2013-08-18 16:53:45', 2013, 'malengadev79@gmail.com', '099 999 9999', NULL, '12345'),
(111, 'Omar', NULL, 'Nxasa', '1999-02-06', 1, '2013-08-18 16:53:45', 2013, 'malengadev81@gmail.com', '099 999 9999', NULL, '12345'),
(112, 'Caleb', NULL, 'Baloyi', '1999-02-06', 1, '2013-08-18 16:53:45', 2013, 'malengadev83@gmail.com', '099 999 9999', NULL, '12345'),
(113, 'Thomas', NULL, 'Vermaak', '1999-07-20', 1, '2013-08-18 16:53:45', 2013, 'malengadev85@gmail.com', '099 999 9999', NULL, '12345'),
(114, 'Piet', NULL, 'Sithole', '1999-02-07', 1, '2013-08-18 16:53:45', 2013, 'malengadev87@gmail.com', '099 999 9999', NULL, '12345'),
(115, 'Harry', NULL, 'Paulson', '1999-04-27', 1, '2013-08-18 16:53:45', 2013, 'malengadev89@gmail.com', '099 999 9999', NULL, '12345'),
(116, 'Boyce', NULL, 'Maluleka', '1999-10-13', 1, '2013-08-18 16:53:45', 2013, 'malengadev91@gmail.com', '099 999 9999', NULL, '12345'),
(117, 'William', NULL, 'Naidoo', '1999-11-08', 1, '2013-08-18 16:53:45', 2013, 'malengadev93@gmail.com', '099 999 9999', NULL, '12345'),
(118, 'David', NULL, 'van Wyk', '1999-07-11', 1, '2013-08-18 16:53:45', 2013, 'malengadev95@gmail.com', '099 999 9999', NULL, '12345'),
(119, 'Mack', NULL, 'Nxasa', '1999-11-27', 1, '2013-08-18 16:53:46', 2013, 'malengadev97@gmail.com', '099 999 9999', NULL, '12345'),
(120, 'Lance', NULL, 'Sono', '1999-09-26', 1, '2013-08-18 16:53:46', 2013, 'malengadev99@gmail.com', '099 999 9999', NULL, '12345'),
(121, 'Paul', NULL, 'Botha', '1997-11-05', 1, '2013-08-18 16:53:46', 2013, 'malengadev101@gmail.com', '099 999 9999', NULL, '12345'),
(122, 'Mark', NULL, 'Priest', '1997-02-16', 1, '2013-08-18 16:53:46', 2013, 'malengadev103@gmail.com', '099 999 9999', NULL, '12345'),
(123, 'Mack', NULL, 'Paulson', '1997-05-12', 1, '2013-08-18 16:53:46', 2013, 'malengadev105@gmail.com', '099 999 9999', NULL, '12345'),
(124, 'Blake', NULL, 'Armstrong', '1997-03-05', 1, '2013-08-18 16:53:46', 2013, 'malengadev107@gmail.com', '099 999 9999', NULL, '12345'),
(125, 'Hunter', NULL, 'Vermaak', '1997-07-11', 1, '2013-08-18 16:53:46', 2013, 'malengadev109@gmail.com', '099 999 9999', NULL, '12345'),
(126, 'Raymond', NULL, 'Johnson', '1997-04-17', 1, '2013-08-18 16:53:46', 2013, 'malengadev111@gmail.com', '099 999 9999', NULL, '12345'),
(127, 'Newton', NULL, 'Bala', '1997-02-16', 1, '2013-08-18 16:53:46', 2013, 'malengadev113@gmail.com', '099 999 9999', NULL, '12345'),
(128, 'Lance', NULL, 'Williams', '1997-03-05', 1, '2013-08-18 16:53:46', 2013, 'malengadev115@gmail.com', '099 999 9999', NULL, '12345'),
(129, 'Tommy', NULL, 'Brown', '1997-02-24', 1, '2013-08-18 16:53:46', 2013, 'malengadev117@gmail.com', '099 999 9999', NULL, '12345'),
(130, 'Robert', NULL, 'Maluleka', '1997-08-24', 1, '2013-08-18 16:53:46', 2013, 'malengadev119@gmail.com', '099 999 9999', NULL, '12345'),
(131, 'Geoffrey', NULL, 'Priest', '1995-06-20', 1, '2013-08-18 16:53:46', 2013, 'malengadev121@gmail.com', '099 999 9999', NULL, '12345'),
(132, 'Geoffrey', NULL, 'Sithole', '1995-07-23', 1, '2013-08-18 16:53:46', 2013, 'malengadev123@gmail.com', '099 999 9999', NULL, '12345'),
(133, 'Geoffrey', NULL, 'Smythe', '1995-11-15', 1, '2013-08-18 16:53:46', 2013, 'malengadev125@gmail.com', '099 999 9999', NULL, '12345'),
(134, 'Harry', NULL, 'Burmingham', '1995-03-10', 1, '2013-08-18 16:53:46', 2013, 'malengadev127@gmail.com', '099 999 9999', NULL, '12345'),
(135, 'Blake', NULL, 'Black', '1995-03-13', 1, '2013-08-18 16:53:46', 2013, 'malengadev129@gmail.com', '099 999 9999', NULL, '12345'),
(136, 'Johannes', NULL, 'Oosthuizen', '1995-04-17', 1, '2013-08-18 16:53:46', 2013, 'malengadev131@gmail.com', '099 999 9999', NULL, '12345'),
(137, 'Shane', NULL, 'Jackson', '1995-09-21', 1, '2013-08-18 16:53:46', 2013, 'malengadev133@gmail.com', '099 999 9999', NULL, '12345'),
(138, 'Petrus', NULL, 'Venter', '1995-06-13', 1, '2013-08-18 16:53:46', 2013, 'malengadev135@gmail.com', '099 999 9999', NULL, '12345'),
(139, 'Lance', NULL, 'Baloyi', '1995-02-12', 1, '2013-08-18 16:53:46', 2013, 'malengadev137@gmail.com', '099 999 9999', NULL, '12345'),
(140, 'Petrus', NULL, 'Sithole', '1995-05-24', 1, '2013-08-18 16:53:46', 2013, 'malengadev139@gmail.com', '099 999 9999', NULL, '12345'),
(149, 'Erica', NULL, 'Jonathan', NULL, 0, '2014-04-11 00:10:24', 0, 'erics@gmal.cpm', '0889963232', NULL, '436110'),
(150, 'Jonathan K', NULL, 'Jonathan', NULL, 0, '2014-04-11 00:15:16', 0, 'ejonat@gmal.cpm', '0889963232', NULL, '510384'),
(151, 'Danielle', NULL, 'Khomeini', NULL, 0, '2014-04-11 00:18:50', 0, 'danielle@gmail.com', '0885556363', NULL, '635551');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `teeTime`
--

CREATE TABLE `teeTime` (
  `teeTimeID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tourneyPlayerScoreID` int(11) unsigned NOT NULL,
  `golfRound` int(11) unsigned NOT NULL,
  `teeTime` datetime NOT NULL,
  PRIMARY KEY (`teeTimeID`),
  KEY `tourneyPlayerScoreID` (`tourneyPlayerScoreID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tournament`
--

CREATE TABLE `tournament` (
  `tournamentID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tourneyName` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `clubID` int(11) unsigned NOT NULL,
  `closingDate` datetime DEFAULT NULL,
  `endDate` datetime NOT NULL,
  `startDate` datetime NOT NULL,
  `golfRounds` int(11) unsigned NOT NULL,
  `golfGroupID` int(11) unsigned NOT NULL,
  `closedForScoringFlag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`tournamentID`),
  UNIQUE KEY `golfGroupID` (`golfGroupID`,`tourneyName`,`startDate`),
  KEY `fk1` (`clubID`),
  KEY `fkgg003` (`golfGroupID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=24 ;

--
-- Dumping data for table `tournament`
--

INSERT INTO `tournament` (`tournamentID`, `tourneyName`, `clubID`, `closingDate`, `endDate`, `startDate`, `golfRounds`, `golfGroupID`, `closedForScoringFlag`) VALUES
(17, 'Woody Woodpecker Classic', 4, NULL, '2014-04-20 12:00:00', '2014-04-19 12:00:00', 2, 10, 0),
(20, 'Summer Invitational', 3, NULL, '2014-04-27 12:00:00', '2014-04-27 12:00:00', 1, 10, 0),
(21, 'Bryanston Junior Major', 2, NULL, '2014-04-27 12:00:00', '2014-04-24 12:00:00', 4, 10, 0),
(22, 'World Qualifier 2014', 5, NULL, '2014-04-03 12:00:00', '2014-04-01 12:00:00', 3, 10, 0),
(23, 'The Qualifier 2014', 4, NULL, '2014-04-26 12:00:00', '2014-04-21 12:00:00', 6, 10, 0);

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
-- Table structure for table `tourneyPlayerScore`
--

CREATE TABLE `tourneyPlayerScore` (
  `tourneyPlayerScoreID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `scoreRound1` int(11) DEFAULT '0',
  `scoreRound2` int(11) DEFAULT '0',
  `scoreRound3` int(11) DEFAULT '0',
  `scoreRound4` int(11) DEFAULT '0',
  `scoreRound5` int(11) DEFAULT '0',
  `scoreRound6` int(11) DEFAULT '0',
  `tourneyPosition` int(11) unsigned DEFAULT '0',
  `tourneyPositionTied` int(11) unsigned DEFAULT NULL,
  `winnerFlag` int(11) DEFAULT NULL,
  `playerID` int(11) unsigned DEFAULT NULL,
  `tournamentID` int(11) unsigned DEFAULT NULL,
  `ageGroupID` int(11) unsigned DEFAULT NULL,
  `administratorID` int(11) unsigned DEFAULT NULL,
  `dateRegistered` datetime DEFAULT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `paidFlag` int(11) DEFAULT NULL,
  `totalScore` int(11) DEFAULT '0',
  PRIMARY KEY (`tourneyPlayerScoreID`),
  UNIQUE KEY `ixtp` (`tournamentID`,`playerID`),
  KEY `ff001` (`tournamentID`),
  KEY `ff002` (`playerID`),
  KEY `ff003` (`ageGroupID`),
  KEY `ff004` (`administratorID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=100 ;

--
-- Dumping data for table `tourneyPlayerScore`
--

INSERT INTO `tourneyPlayerScore` (`tourneyPlayerScoreID`, `scoreRound1`, `scoreRound2`, `scoreRound3`, `scoreRound4`, `scoreRound5`, `scoreRound6`, `tourneyPosition`, `tourneyPositionTied`, `winnerFlag`, `playerID`, `tournamentID`, `ageGroupID`, `administratorID`, `dateRegistered`, `dateUpdated`, `paidFlag`, `totalScore`) VALUES
(57, 65, 73, 72, 65, 0, 0, 0, 0, 0, 124, 21, NULL, NULL, '2014-04-12 19:58:11', NULL, 0, 275),
(58, 81, 75, 67, 77, 0, 0, 0, 0, 0, 127, 21, NULL, NULL, '2014-04-12 19:58:16', NULL, 0, 300),
(59, 70, 72, 77, 74, 0, 0, 0, 0, 0, 139, 21, NULL, NULL, '2014-04-12 19:58:21', NULL, 0, 293),
(60, 75, 71, 73, 69, 0, 0, 0, 0, 0, 90, 21, NULL, NULL, '2014-04-12 19:58:25', NULL, 0, 288),
(61, 74, 73, 84, 71, 0, 0, 0, 0, 0, 126, 21, NULL, NULL, '2014-04-12 19:58:29', NULL, 0, 302),
(62, 72, 69, 66, 74, 0, 0, 0, 0, 0, 71, 21, NULL, NULL, '2014-04-12 19:58:36', NULL, 0, 281),
(63, 67, 72, 72, 79, 0, 0, 0, 0, 0, 72, 21, NULL, NULL, '2014-04-12 19:58:45', NULL, 0, 290),
(64, 78, 74, 73, 70, 0, 0, 0, 0, 0, 115, 21, NULL, NULL, '2014-04-12 19:58:50', NULL, 0, 295),
(65, 70, 72, 79, 72, 0, 0, 0, 0, 0, 108, 21, NULL, NULL, '2014-04-12 19:58:55', NULL, 0, 293),
(66, 69, 77, 74, 66, 0, 0, 0, 0, 0, 88, 21, NULL, NULL, '2014-04-12 19:59:04', NULL, 0, 286),
(67, 65, 73, 75, 79, 0, 0, 0, 0, 0, 85, 21, NULL, NULL, '2014-04-12 19:59:24', NULL, 0, 292),
(68, 78, 66, 73, 77, 0, 0, 0, 0, 0, 75, 21, NULL, NULL, '2014-04-12 19:59:30', NULL, 0, 294),
(69, 79, 72, 64, 72, 0, 0, 0, 0, 0, 87, 21, NULL, NULL, '2014-04-12 20:21:18', NULL, 0, 287),
(70, 72, 81, 68, 73, 0, 0, 0, 0, 0, 83, 21, NULL, NULL, '2014-04-12 20:21:23', NULL, 0, 294),
(71, 73, 71, 79, 78, 0, 0, 0, 0, 0, 94, 21, NULL, NULL, '2014-04-12 20:21:29', NULL, 0, 301),
(72, 71, 70, 75, 89, 0, 0, 0, 0, 0, 100, 21, NULL, NULL, '2014-04-12 20:21:37', NULL, 0, 305),
(73, 72, 0, 0, 0, 0, 0, 0, 0, 0, 135, 20, NULL, NULL, '2014-04-12 21:37:19', NULL, 0, 72),
(74, 70, 0, 0, 0, 0, 0, 0, 0, 0, 126, 20, NULL, NULL, '2014-04-12 21:37:24', NULL, 0, 70),
(75, 72, 0, 0, 0, 0, 0, 0, 0, 0, 130, 20, NULL, NULL, '2014-04-12 21:37:27', NULL, 0, 72),
(76, 0, 0, 0, 0, 0, 0, 0, 0, 0, 139, 17, NULL, NULL, '2014-04-13 00:58:47', NULL, 0, 0),
(77, 72, 77, 81, 80, 0, 0, 0, 0, 0, 73, 21, NULL, NULL, '2014-04-13 05:01:28', NULL, 0, 310),
(78, 68, 67, 81, 64, 0, 0, 0, 0, 0, 97, 21, NULL, NULL, '2014-04-13 05:02:03', NULL, 0, 280),
(79, 105, 85, 101, 78, 0, 0, 0, 0, 0, 150, 21, NULL, NULL, '2014-04-13 05:06:01', NULL, 0, 369),
(80, 75, 73, 75, 74, 0, 0, 0, 0, 0, 79, 21, NULL, NULL, '2014-04-13 06:46:23', NULL, 0, 297),
(81, 79, 79, 79, 78, 0, 0, 0, 0, 0, 121, 21, NULL, NULL, '2014-04-13 06:48:33', NULL, 0, 315),
(82, 72, 72, 72, 72, 0, 0, 0, 0, 0, 74, 21, NULL, NULL, '2014-04-13 12:04:22', NULL, 0, 288),
(83, 67, 80, 83, 78, 0, 0, 0, 0, 0, 112, 21, NULL, NULL, '2014-04-13 12:25:47', NULL, 0, 308),
(84, 79, 71, 72, 80, 0, 0, 0, 0, 0, 78, 21, NULL, NULL, '2014-04-13 12:28:18', NULL, 0, 302),
(85, 75, 0, 0, 0, 0, 0, 0, 0, 0, 112, 20, NULL, NULL, '2014-04-14 01:07:12', NULL, 0, 75),
(86, 69, 0, 0, 0, 0, 0, 0, 0, 0, 105, 20, NULL, NULL, '2014-04-14 01:07:21', NULL, 0, 69),
(87, 71, 0, 0, 0, 0, 0, 0, 0, 0, 151, 20, NULL, NULL, '2014-04-14 01:07:26', NULL, 0, 71),
(88, 74, 0, 0, 0, 0, 0, 0, 0, 0, 116, 20, NULL, NULL, '2014-04-14 01:07:29', NULL, 0, 74),
(89, 74, 0, 0, 0, 0, 0, 0, 0, 0, 119, 20, NULL, NULL, '2014-04-14 01:07:34', NULL, 0, 74),
(90, 67, 0, 0, 0, 0, 0, 0, 0, 0, 133, 20, NULL, NULL, '2014-04-14 01:08:01', NULL, 0, 67),
(91, 74, 0, 0, 0, 0, 0, 0, 0, 0, 120, 20, NULL, NULL, '2014-04-14 01:08:06', NULL, 0, 74),
(92, 79, 74, 73, 0, 0, 0, 0, 0, 0, 127, 23, NULL, NULL, '2014-04-14 14:57:50', NULL, 0, 226),
(93, 71, 73, 75, 0, 0, 0, 0, 0, 0, 129, 23, NULL, NULL, '2014-04-14 14:58:48', NULL, 0, 219),
(94, 73, 72, 80, 0, 0, 0, 0, 0, 0, 121, 23, NULL, NULL, '2014-04-14 16:49:20', NULL, 0, 225),
(95, 70, 69, 78, 0, 0, 0, 0, 0, 0, 124, 23, NULL, NULL, '2014-04-15 03:17:37', NULL, 0, 217),
(96, 72, 72, 71, 0, 0, 0, 0, 0, 0, 134, 23, NULL, NULL, '2014-04-15 03:26:32', NULL, 0, 215),
(97, 74, 71, 72, 0, 0, 0, 0, 0, 0, 116, 23, NULL, NULL, '2014-04-15 03:28:46', NULL, 0, 217),
(98, 68, 71, 77, 0, 0, 0, 0, 0, 0, 139, 23, NULL, NULL, '2014-04-15 03:32:08', NULL, 0, 216),
(99, 71, 72, 71, 0, 0, 0, 0, 0, 0, 80, 23, NULL, NULL, '2014-04-15 03:33:25', NULL, 0, 214);

-- --------------------------------------------------------

--
-- Table structure for table `tourneyScoreByRound`
--

CREATE TABLE `tourneyScoreByRound` (
  `tourneyScoreByRoundID` int(11) unsigned NOT NULL AUTO_INCREMENT,
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
  `tourneyPlayerScoreID` int(10) unsigned DEFAULT NULL,
  `golfRound` int(11) DEFAULT '0',
  `totalScore` int(11) unsigned DEFAULT '0',
  PRIMARY KEY (`tourneyScoreByRoundID`),
  KEY `tourneyPlayerScoreID` (`tourneyPlayerScoreID`),
  KEY `golfRound` (`golfRound`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=317 ;

--
-- Dumping data for table `tourneyScoreByRound`
--

INSERT INTO `tourneyScoreByRound` (`tourneyScoreByRoundID`, `score1`, `score2`, `score3`, `score4`, `score5`, `score6`, `score7`, `score8`, `score9`, `score10`, `score11`, `score12`, `score13`, `score14`, `score15`, `score16`, `score17`, `score18`, `tourneyPlayerScoreID`, `golfRound`, `totalScore`) VALUES
(161, 8, 4, 4, 3, 5, 4, 4, 4, 4, 4, 4, 3, 4, 4, 3, 2, 5, 4, 57, 2, 73),
(162, 7, 4, 4, 4, 5, 5, 4, 4, 4, 3, 3, 4, 4, 3, 2, 3, 5, 4, 57, 3, 72),
(163, 5, 3, 4, 3, 4, 4, 3, 4, 4, 1, 4, 4, 4, 4, 3, 4, 3, 4, 57, 4, 65),
(164, 4, 2, 4, 3, 4, 3, 3, 3, 4, 3, 4, 3, 5, 3, 5, 4, 5, 3, 57, 1, 65),
(165, 5, 4, 4, 4, 5, 4, 5, 4, 3, 3, 4, 2, 3, 4, 3, 3, 4, 3, 58, 3, 67),
(166, 6, 3, 6, 5, 6, 4, 6, 4, 4, 4, 5, 3, 5, 4, 3, 3, 6, 4, 58, 1, 81),
(167, 4, 2, 5, 4, 7, 5, 7, 6, 5, 3, 3, 2, 4, 3, 4, 4, 5, 4, 58, 4, 77),
(168, 4, 3, 5, 4, 4, 4, 4, 4, 5, 4, 3, 3, 4, 4, 4, 5, 6, 5, 58, 2, 75),
(169, 4, 6, 4, 4, 2, 4, 3, 4, 5, 4, 4, 4, 4, 4, 3, 5, 4, 4, 59, 2, 72),
(170, 3, 5, 4, 5, 3, 4, 4, 5, 6, 3, 3, 5, 3, 5, 4, 4, 3, 5, 59, 4, 74),
(171, 4, 4, 4, 4, 4, 4, 2, 4, 3, 4, 4, 4, 5, 5, 4, 4, 4, 3, 59, 1, 70),
(172, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 6, 5, 4, 4, 4, 4, 5, 4, 59, 3, 77),
(173, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 60, 3, 73),
(174, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 2, 4, 4, 60, 4, 69),
(175, 2, 4, 4, 4, 4, 4, 4, 4, 3, 5, 4, 4, 4, 3, 5, 4, 5, 4, 60, 2, 71),
(176, 4, 4, 4, 2, 6, 4, 4, 5, 4, 6, 4, 4, 3, 3, 4, 4, 6, 4, 60, 1, 75),
(177, 4, 4, 4, 5, 4, 6, 6, 4, 4, 4, 4, 4, 5, 6, 7, 4, 4, 5, 61, 3, 84),
(178, 4, 4, 4, 4, 5, 4, 5, 4, 4, 4, 4, 2, 4, 4, 4, 4, 5, 4, 61, 2, 73),
(179, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 61, 1, 74),
(180, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 61, 4, 71),
(181, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 5, 62, 4, 74),
(182, 4, 4, 4, 3, 4, 4, 4, 3, NULL, 4, 1, 4, 6, 4, 4, 4, 4, 5, 62, 3, 66),
(183, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 5, 4, 62, 1, 72),
(184, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 4, 4, 3, 4, 4, 3, 4, 4, 62, 2, 69),
(185, 4, 4, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 4, 2, 4, 4, 63, 1, 67),
(186, 5, 5, 4, 4, 4, 4, 5, 4, 4, 4, 5, 4, 4, 4, 6, 4, 4, 5, 63, 4, 79),
(187, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 63, 2, 72),
(188, 4, 4, 4, 5, 4, 4, 4, 4, NULL, 5, 5, 4, 4, 4, 5, 4, 4, 4, 63, 3, 72),
(189, 4, 4, 4, 4, 4, 6, 5, 4, 4, 5, 4, 4, 6, 5, 4, 3, 4, 4, 64, 1, 78),
(190, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 2, 3, 3, 64, 4, 70),
(191, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 4, 64, 2, 74),
(192, 4, 4, 4, 4, 2, 4, 4, 3, 4, 4, 4, 6, 5, 5, 4, 4, 4, 4, 64, 3, 73),
(193, 4, 4, 4, 4, 5, 4, 5, 6, 5, 4, 4, 4, 5, 5, 4, 4, 4, 4, 65, 3, 79),
(194, 4, 5, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 65, 2, 72),
(195, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 3, 65, 1, 70),
(196, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 65, 4, 72),
(197, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 66, 1, 69),
(198, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 3, 4, 3, 66, 4, 66),
(199, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 5, 5, 6, 66, 2, 77),
(200, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 5, 4, 4, 4, 66, 3, 74),
(201, 4, 4, 4, 2, 4, 2, 4, 4, 4, 4, 5, 3, 4, 2, 4, 4, 4, 3, 67, 1, 65),
(202, 5, 5, 3, 3, 6, 3, 5, 5, 5, 5, 4, 2, 3, 3, 5, 4, 5, 4, 67, 3, 75),
(203, 6, 6, 3, 2, 5, 2, 5, 4, 4, 4, 3, 3, 3, 3, 6, 5, 4, 5, 67, 2, 73),
(204, 7, 5, 4, 3, 6, 3, 4, 5, 5, 5, 4, 4, 4, 4, 3, 5, 3, 5, 67, 4, 79),
(205, 4, 4, 4, 4, 4, 3, 3, 4, 4, 4, 2, 3, 4, 4, 3, 4, 5, 3, 68, 2, 66),
(206, 4, 4, 5, 4, 6, 5, 4, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 68, 1, 78),
(207, 4, 4, 4, 5, 5, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 5, 5, 4, 68, 4, 77),
(208, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 68, 3, 73),
(209, 4, 4, 4, 4, 4, 3, 4, 4, 4, 4, 5, 5, 4, 3, 4, 4, 4, 4, 69, 2, 72),
(210, 4, 5, 4, 4, 4, 4, 2, 5, 4, 4, 5, 4, 4, 4, 4, 4, 3, 4, 69, 4, 72),
(211, 4, 4, 4, 5, 7, 4, 4, 4, 5, 4, 5, 5, 5, 5, 4, 4, 2, 4, 69, 1, 79),
(212, 4, 4, 2, 4, 4, 3, 4, 3, 4, 4, 3, 4, 4, 4, 2, 4, 4, 3, 69, 3, 64),
(213, 5, 4, 4, 6, 6, 4, 4, 5, 4, 4, 4, 5, 4, 5, 4, 4, 5, 4, 70, 2, 81),
(214, 4, 4, 4, 4, 4, 3, 4, 5, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 70, 4, 73),
(215, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 3, 2, 4, 4, 4, 4, 4, 4, 70, 3, 68),
(216, 4, 5, 6, 4, 6, 4, 4, 4, 4, 4, 4, 4, 4, 2, 3, 3, 4, 3, 70, 1, 72),
(217, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 71, 1, 73),
(218, 4, 4, 2, 4, 3, 3, 4, 5, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 71, 2, 71),
(219, 4, 4, 5, 5, 4, 4, 5, 4, 6, 4, 4, 5, 4, 4, 5, 4, 4, 4, 71, 3, 79),
(220, 5, 4, 5, 4, 5, 4, 4, 4, 4, 4, 5, 4, 4, 5, 4, 5, 4, 4, 71, 4, 78),
(221, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 72, 2, 70),
(222, 5, 5, 3, 5, 5, 5, 3, 5, 3, 5, 4, 3, 3, 4, 3, 6, 5, 3, 72, 3, 75),
(223, 5, 5, 4, 5, 4, 6, 4, 6, 5, 6, 5, 4, 4, 5, 4, 7, 6, 4, 72, 4, 89),
(224, 6, 4, 3, 4, 3, 4, 4, 5, 3, 5, 6, 3, 3, 5, 3, 4, 4, 2, 72, 1, 71),
(225, 4, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 4, 4, 4, 5, 4, 4, 5, 73, 1, 72),
(226, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 5, 3, 3, 4, 3, 74, 1, 70),
(227, 4, 4, 4, 4, 4, 4, 5, 4, 5, 4, 4, 4, 4, 3, 4, 4, 4, 3, 75, 1, 72),
(228, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 76, 2, 0),
(229, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 76, 1, 0),
(230, 4, 5, 4, 5, 5, 5, 5, 5, 5, 5, 4, 4, 4, 5, 4, 4, 3, 4, 77, 4, 80),
(231, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 77, 1, 72),
(232, 5, 5, 4, 4, 6, 5, 4, 4, 4, 5, 5, 4, 4, 5, 4, 4, 5, 4, 77, 3, 81),
(233, 5, 5, 4, 4, 4, 5, 4, 4, 4, 5, 5, 4, 4, 5, 4, 4, 4, 3, 77, 2, 77),
(234, 4, 4, 4, 3, 4, 5, 4, 4, 4, 6, 5, 5, 5, 5, 6, 5, 4, 4, 78, 3, 81),
(235, 4, 4, 4, 4, 5, 5, 4, 4, 2, 5, 1, 3, 4, 4, 4, 4, 3, 4, 78, 1, 68),
(236, 4, 3, 3, 3, 4, 3, 4, 2, 4, 4, 4, 4, 3, 4, 4, 3, 4, 4, 78, 4, 64),
(237, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 3, 3, 4, 4, 4, 3, 4, 4, 78, 2, 67),
(238, 6, 4, 5, 4, 5, 6, 4, 7, 7, 8, 5, 6, 6, 5, 8, 7, 6, 6, 79, 1, 105),
(239, 4, 5, 5, 4, 4, 4, 5, 4, 4, 4, 5, 4, 4, 4, 4, 4, 5, 5, 79, 4, 78),
(240, 4, 4, 4, 4, 6, 5, 4, 4, 4, 4, 4, 6, 6, 4, 5, 5, 5, 7, 79, 2, 85),
(241, 7, 4, 5, 5, 5, 6, 6, 5, 7, 5, 5, 8, 7, 6, 6, 6, 4, 4, 79, 3, 101),
(242, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 80, 2, 73),
(243, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 4, 4, 4, 4, 80, 1, 75),
(244, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 80, 4, 74),
(245, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 80, 3, 75),
(246, 4, 4, 5, 5, 4, 4, 5, 4, 4, 4, 5, 4, 5, 4, 5, 5, 4, 4, 81, 1, 79),
(247, 4, 5, 6, 5, 4, 4, 4, 4, 4, 4, 5, 4, 5, 4, 4, 4, 5, 4, 81, 3, 79),
(248, 4, 4, 4, 5, 5, 4, 5, 5, 4, 4, 4, 6, 4, 4, 5, 4, 4, 4, 81, 2, 79),
(249, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 5, 7, 4, 4, 4, 4, 81, 4, 78),
(250, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 82, 3, 72),
(251, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 82, 1, 72),
(252, 4, 4, 4, 3, 5, 4, 4, 3, 4, 3, 5, 4, 4, 5, 4, 4, 4, 4, 82, 4, 72),
(253, 4, 3, 3, 4, 5, 4, 3, 5, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 82, 2, 72),
(254, 4, 4, 4, 4, 4, 3, 4, 4, 3, 2, 4, 4, 3, 4, 4, 4, 4, 4, 83, 1, 67),
(255, 4, 4, 5, 5, 5, 6, 4, 6, 4, 4, 5, 4, 4, 5, 4, 6, 4, 4, 83, 3, 83),
(256, 4, 4, 4, 5, 4, 6, 5, 4, 4, 4, 5, 4, 4, 4, 6, 3, 6, 4, 83, 2, 80),
(257, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 5, 6, 5, 4, 4, 5, 83, 4, 78),
(258, 4, 4, 4, 4, 4, 5, 4, 4, 3, 4, 4, 3, 4, 4, 3, 5, 4, 4, 84, 2, 71),
(259, 4, 4, 6, 7, 4, 4, 4, 5, 4, 4, 5, 4, 5, 4, 4, 4, 4, 4, 84, 4, 80),
(260, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 3, 4, 4, 4, 7, 84, 3, 72),
(261, 4, 4, 4, 4, 5, 5, 4, 4, 4, 4, 6, 4, 4, 6, 4, 5, 4, 4, 84, 1, 79),
(262, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 6, 4, 4, 4, 4, 4, 85, 1, 75),
(263, 4, 4, 4, 4, 4, 4, 5, 4, 4, 3, 2, 3, 4, 4, 4, 4, 4, 4, 86, 1, 69),
(264, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 5, 4, 3, 4, 4, 4, 4, 87, 1, 71),
(265, 4, 4, 4, 4, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 88, 1, 74),
(266, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 89, 1, 74),
(267, 4, 4, 4, 3, 4, 3, 3, 4, 4, 4, 4, 4, 4, 3, 3, 4, 4, 4, 90, 1, 67),
(268, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 91, 1, 74),
(269, 4, 5, 4, 4, 5, 4, 4, 4, 4, 5, 5, 4, 4, 6, 4, 4, 5, 4, 92, 1, 79),
(270, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 92, 6, 0),
(271, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 92, 2, 74),
(272, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 92, 4, 0),
(273, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 92, 3, 73),
(274, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 92, 5, 0),
(275, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 93, 6, 0),
(276, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 93, 2, 73),
(277, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 93, 5, 0),
(278, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 4, 4, 93, 1, 71),
(279, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 93, 4, 0),
(280, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 5, 4, 93, 3, 75),
(281, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 5, 3, 4, 4, 94, 1, 73),
(282, 5, 5, 4, 4, 4, 5, 5, 6, 4, 4, 4, 4, 4, 4, 4, 5, 4, 5, 94, 3, 80),
(283, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 94, 2, 72),
(284, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 94, 4, 0),
(285, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 94, 5, 0),
(286, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 94, 6, 0),
(287, 4, 4, 4, 5, 5, 2, 4, 4, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 95, 2, 69),
(288, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 95, 4, 0),
(289, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 95, 6, 0),
(290, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 95, 5, 0),
(291, 4, 4, 4, 4, 4, 3, 4, 4, 2, 4, 4, 5, 4, 4, 4, 4, 4, 4, 95, 1, 70),
(292, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 4, 4, 6, 4, 5, 4, 5, 95, 3, 78),
(293, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 96, 2, 72),
(294, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 96, 6, 0),
(295, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 96, 4, 0),
(296, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 96, 1, 72),
(297, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 2, 4, 4, 4, 4, 4, 4, 4, 96, 3, 71),
(298, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 96, 5, 0),
(299, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 97, 5, 0),
(300, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 97, 6, 0),
(301, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 97, 1, 74),
(302, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 97, 3, 72),
(303, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 97, 2, 71),
(304, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 97, 4, 0),
(305, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 98, 2, 71),
(306, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 98, 4, 0),
(307, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 98, 5, 0),
(308, 4, 4, 4, 4, 6, 4, 4, 4, 5, 4, 5, 4, 4, 4, 3, 6, 4, 4, 98, 3, 77),
(309, 4, 4, 4, 4, 5, 3, 4, 4, 4, 4, 2, 3, 4, 4, 4, 4, 4, 3, 98, 1, 68),
(310, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 98, 6, 0),
(311, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99, 6, 0),
(312, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 3, 4, 5, 4, 3, 99, 2, 72),
(313, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99, 5, 0),
(314, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99, 4, 0),
(315, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 4, 99, 3, 71),
(316, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 99, 1, 71);

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
-- Constraints for table `teeTime`
--
ALTER TABLE `teeTime`
  ADD CONSTRAINT `teeTime_ibfk_1` FOREIGN KEY (`tourneyPlayerScoreID`) REFERENCES `tourneyPlayerScore` (`tourneyPlayerScoreID`);

--
-- Constraints for table `tournament`
--
ALTER TABLE `tournament`
  ADD CONSTRAINT `tournament_ibfk_1` FOREIGN KEY (`clubID`) REFERENCES `club` (`clubID`),
  ADD CONSTRAINT `tournament_ibfk_2` FOREIGN KEY (`golfGroupID`) REFERENCES `golfGroup` (`golfGroupID`);

--
-- Constraints for table `tournamentVolunteer`
--
ALTER TABLE `tournamentVolunteer`
  ADD CONSTRAINT `tournamentVolunteer_ibfk_1` FOREIGN KEY (`tournamentID`) REFERENCES `tournament` (`tournamentID`),
  ADD CONSTRAINT `tournamentVolunteer_ibfk_2` FOREIGN KEY (`volunteerID`) REFERENCES `volunteer` (`volunteerID`);

--
-- Constraints for table `tourneyPlayerScore`
--
ALTER TABLE `tourneyPlayerScore`
  ADD CONSTRAINT `tourneyPlayerScore_ibfk_1` FOREIGN KEY (`playerID`) REFERENCES `player` (`playerID`),
  ADD CONSTRAINT `tourneyPlayerScore_ibfk_2` FOREIGN KEY (`tournamentID`) REFERENCES `tournament` (`tournamentID`),
  ADD CONSTRAINT `tourneyPlayerScore_ibfk_3` FOREIGN KEY (`ageGroupID`) REFERENCES `agegroup` (`ageGroupID`),
  ADD CONSTRAINT `tourneyPlayerScore_ibfk_4` FOREIGN KEY (`administratorID`) REFERENCES `administrator` (`administratorID`);

--
-- Constraints for table `tourneyScoreByRound`
--
ALTER TABLE `tourneyScoreByRound`
  ADD CONSTRAINT `tourneyScoreByRound_ibfk_1` FOREIGN KEY (`tourneyPlayerScoreID`) REFERENCES `tourneyPlayerScore` (`tourneyPlayerScoreID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
