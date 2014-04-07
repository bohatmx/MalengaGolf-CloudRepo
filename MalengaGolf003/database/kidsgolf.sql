-- phpMyAdmin SQL Dump
-- version 3.5.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 07, 2014 at 02:23 PM
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
  `cellphone` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `pin` varchar(45) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `golfGroupID` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`administratorID`),
  UNIQUE KEY `idxmail` (`golfGroupID`,`email`),
  KEY `ffgg` (`golfGroupID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`administratorID`, `cellphone`, `email`, `pin`, `firstName`, `lastName`, `golfGroupID`) VALUES
(1, NULL, 'aubrey@mlab.co.za', '12345', 'Aubrey', 'Malabie', 1),
(2, NULL, 'admin', 'admin', 'admin', NULL, 2);

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
  `email` varchar(95) COLLATE latin1_general_ci DEFAULT NULL,
  `telephone` varchar(25) COLLATE latin1_general_ci DEFAULT NULL,
  `latitude` int(11) DEFAULT NULL,
  `longitude` int(11) DEFAULT NULL,
  `address` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `provinceID` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`clubID`),
  UNIQUE KEY `ix1a` (`provinceID`,`clubName`),
  KEY `fprov` (`provinceID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `club`
--

INSERT INTO `club` (`clubID`, `clubName`, `email`, `telephone`, `latitude`, `longitude`, `address`, `provinceID`) VALUES
(1, 'Pecanwood Golf & Country Club', NULL, NULL, NULL, NULL, NULL, 2),
(2, 'Bryanston Country Club', NULL, NULL, NULL, NULL, NULL, 1),
(3, 'Modderfontein Golf Club', NULL, NULL, NULL, NULL, NULL, 1),
(4, 'Pretoria Golf Club', NULL, NULL, NULL, NULL, NULL, 1),
(5, 'Zwartkops Golf Club', NULL, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `clubCourse`
--

CREATE TABLE `clubCourse` (
  `clubCourseID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clubID` int(11) unsigned NOT NULL,
  `par` int(11) NOT NULL,
  `par1` int(11) DEFAULT NULL,
  `par2` int(11) DEFAULT NULL,
  `par3` int(11) DEFAULT NULL,
  `par4` int(11) DEFAULT NULL,
  `par5` int(11) DEFAULT NULL,
  `par6` int(11) DEFAULT NULL,
  `par7` int(11) DEFAULT NULL,
  `par8` int(11) DEFAULT NULL,
  `par9` int(11) DEFAULT NULL,
  `par10` int(11) DEFAULT NULL,
  `par11` int(11) DEFAULT NULL,
  `par12` int(11) DEFAULT NULL,
  `par13` int(11) DEFAULT NULL,
  `par14` int(11) DEFAULT NULL,
  `par15` int(11) DEFAULT NULL,
  `par16` int(11) DEFAULT NULL,
  `par17` int(11) DEFAULT NULL,
  `par18` int(11) DEFAULT NULL,
  `latitude` int(11) DEFAULT NULL,
  `longitude` int(11) DEFAULT NULL,
  `courseName` varchar(100) DEFAULT NULL,
  `length1` int(11) DEFAULT NULL,
  `length2` int(11) DEFAULT NULL,
  `length3` int(11) DEFAULT NULL,
  `length4` int(11) DEFAULT NULL,
  `length5` int(11) DEFAULT NULL,
  `length6` int(11) DEFAULT NULL,
  `length7` int(11) DEFAULT NULL,
  `length8` int(11) DEFAULT NULL,
  `length9` int(11) DEFAULT NULL,
  `length10` int(11) DEFAULT NULL,
  `length11` int(11) DEFAULT NULL,
  `length12` int(11) DEFAULT NULL,
  `length13` int(11) DEFAULT NULL,
  `length14` int(11) DEFAULT NULL,
  `length15` int(11) DEFAULT NULL,
  `length16` int(11) DEFAULT NULL,
  `length17` int(11) DEFAULT NULL,
  `length18` int(11) DEFAULT NULL,
  PRIMARY KEY (`clubCourseID`),
  KEY `clubID` (`clubID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `clubCourse`
--

INSERT INTO `clubCourse` (`clubCourseID`, `clubID`, `par`, `par1`, `par2`, `par3`, `par4`, `par5`, `par6`, `par7`, `par8`, `par9`, `par10`, `par11`, `par12`, `par13`, `par14`, `par15`, `par16`, `par17`, `par18`, `latitude`, `longitude`, `courseName`, `length1`, `length2`, `length3`, `length4`, `length5`, `length6`, `length7`, `length8`, `length9`, `length10`, `length11`, `length12`, `length13`, `length14`, `length15`, `length16`, `length17`, `length18`) VALUES
(1, 1, 72, 4, 4, 3, 4, 5, 4, 5, 3, 4, 5, 4, 5, 3, 4, 4, 4, 3, 4, NULL, NULL, 'Pecanwood Course', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 3, 72, 5, 4, 3, 4, 4, 4, 4, 5, 3, 5, 3, 4, 5, 3, 4, 4, 4, 4, NULL, NULL, 'Modderfontein Course', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `countryID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `countryName` varchar(100) NOT NULL,
  `latitude` int(11) DEFAULT NULL,
  `longitude` int(11) DEFAULT NULL,
  PRIMARY KEY (`countryID`),
  UNIQUE KEY `ctryIdx` (`countryName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`countryID`, `countryName`, `latitude`, `longitude`) VALUES
(1, 'South Africa', NULL, NULL),
(2, 'Namibia', NULL, NULL),
(3, 'Botswana', NULL, NULL),
(4, 'Zimbabwe', NULL, NULL),
(5, 'Swaziland', NULL, NULL),
(6, 'United States', NULL, NULL),
(7, 'United Kingdom', NULL, NULL),
(8, 'Malaysia', NULL, NULL),
(9, 'Kenya', NULL, NULL),
(10, 'Zambia', NULL, NULL),
(12, 'Mozambique', NULL, NULL);

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
  UNIQUE KEY `ixctrygrp` (`countryID`,`golfGroupName`),
  KEY `fkctry` (`countryID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `golfGroup`
--

INSERT INTO `golfGroup` (`golfGroupID`, `golfGroupName`, `email`, `cellphone`, `dateRegistered`, `countryID`) VALUES
(1, 'Malenga Kids Golf Group', 'malengadev@gmail.com', '852222222', '2013-02-15 02:10:40', 5),
(2, 'Little kids golf', 'fggg', '852822525', '2013-02-15 02:33:48', 6);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=71 ;

--
-- Dumping data for table `golfGroupPlayer`
--

INSERT INTO `golfGroupPlayer` (`golfGroupPlayerID`, `golfGroupID`, `playerID`, `dateRegistered`) VALUES
(1, 1, 71, '2013-08-18 16:53:35'),
(2, 1, 72, '2013-08-18 16:53:35'),
(3, 1, 73, '2013-08-18 16:53:36'),
(4, 1, 74, '2013-08-18 16:53:37'),
(5, 1, 75, '2013-08-18 16:53:37'),
(6, 1, 76, '2013-08-18 16:53:38'),
(7, 1, 77, '2013-08-18 16:53:38'),
(8, 1, 78, '2013-08-18 16:53:38'),
(9, 1, 79, '2013-08-18 16:53:39'),
(10, 1, 80, '2013-08-18 16:53:39'),
(11, 1, 81, '2013-08-18 16:53:39'),
(12, 1, 82, '2013-08-18 16:53:40'),
(13, 1, 83, '2013-08-18 16:53:40'),
(14, 1, 84, '2013-08-18 16:53:40'),
(15, 1, 85, '2013-08-18 16:53:40'),
(16, 1, 86, '2013-08-18 16:53:41'),
(17, 1, 87, '2013-08-18 16:53:41'),
(18, 1, 88, '2013-08-18 16:53:41'),
(19, 1, 89, '2013-08-18 16:53:41'),
(20, 1, 90, '2013-08-18 16:53:42'),
(21, 1, 91, '2013-08-18 16:53:42'),
(22, 1, 92, '2013-08-18 16:53:42'),
(23, 1, 93, '2013-08-18 16:53:43'),
(24, 1, 94, '2013-08-18 16:53:43'),
(25, 1, 95, '2013-08-18 16:53:43'),
(26, 1, 96, '2013-08-18 16:53:43'),
(27, 1, 97, '2013-08-18 16:53:44'),
(28, 1, 98, '2013-08-18 16:53:44'),
(29, 1, 99, '2013-08-18 16:53:44'),
(30, 1, 100, '2013-08-18 16:53:44'),
(31, 1, 101, '2013-08-18 16:53:44'),
(32, 1, 102, '2013-08-18 16:53:44'),
(33, 1, 103, '2013-08-18 16:53:44'),
(34, 1, 104, '2013-08-18 16:53:44'),
(35, 1, 105, '2013-08-18 16:53:44'),
(36, 1, 106, '2013-08-18 16:53:44'),
(37, 1, 107, '2013-08-18 16:53:44'),
(38, 1, 108, '2013-08-18 16:53:44'),
(39, 1, 109, '2013-08-18 16:53:45'),
(40, 1, 110, '2013-08-18 16:53:45'),
(41, 1, 111, '2013-08-18 16:53:45'),
(42, 1, 112, '2013-08-18 16:53:45'),
(43, 1, 113, '2013-08-18 16:53:45'),
(44, 1, 114, '2013-08-18 16:53:45'),
(45, 1, 115, '2013-08-18 16:53:45'),
(46, 1, 116, '2013-08-18 16:53:45'),
(47, 1, 117, '2013-08-18 16:53:45'),
(48, 1, 118, '2013-08-18 16:53:46'),
(49, 1, 119, '2013-08-18 16:53:46'),
(50, 1, 120, '2013-08-18 16:53:46'),
(51, 1, 121, '2013-08-18 16:53:46'),
(52, 1, 122, '2013-08-18 16:53:46'),
(53, 1, 123, '2013-08-18 16:53:46'),
(54, 1, 124, '2013-08-18 16:53:46'),
(55, 1, 125, '2013-08-18 16:53:46'),
(56, 1, 126, '2013-08-18 16:53:46'),
(57, 1, 127, '2013-08-18 16:53:46'),
(58, 1, 128, '2013-08-18 16:53:46'),
(59, 1, 129, '2013-08-18 16:53:46'),
(60, 1, 130, '2013-08-18 16:53:46'),
(61, 1, 131, '2013-08-18 16:53:46'),
(62, 1, 132, '2013-08-18 16:53:46'),
(63, 1, 133, '2013-08-18 16:53:46'),
(64, 1, 134, '2013-08-18 16:53:46'),
(65, 1, 135, '2013-08-18 16:53:46'),
(66, 1, 136, '2013-08-18 16:53:46'),
(67, 1, 137, '2013-08-18 16:53:46'),
(68, 1, 138, '2013-08-18 16:53:46'),
(69, 1, 139, '2013-08-18 16:53:46'),
(70, 1, 140, '2013-08-18 16:53:46');

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
  `parentID` int(11) NOT NULL AUTO_INCREMENT,
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
  `playerID` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=141 ;

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
(140, 'Petrus', NULL, 'Sithole', '1995-05-24', 1, '2013-08-18 16:53:46', 2013, 'malengadev139@gmail.com', '099 999 9999', NULL, '12345');

-- --------------------------------------------------------

--
-- Table structure for table `province`
--

CREATE TABLE `province` (
  `provinceID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `countryID` int(11) unsigned DEFAULT NULL,
  `provinceName` varchar(100) DEFAULT NULL,
  `latitude` int(11) DEFAULT NULL,
  `longitude` int(11) DEFAULT NULL,
  PRIMARY KEY (`provinceID`),
  KEY `fk001` (`countryID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `province`
--

INSERT INTO `province` (`provinceID`, `countryID`, `provinceName`, `latitude`, `longitude`) VALUES
(1, 1, 'Gauteng', NULL, NULL),
(2, 1, 'North West', NULL, NULL),
(3, 1, 'Mpumalanga', NULL, NULL),
(4, 1, 'Limpopo', NULL, NULL),
(5, 1, 'Free State', NULL, NULL),
(6, 1, 'Kwa Zulu Natal', NULL, NULL),
(7, 1, 'Eastern Cape', NULL, NULL),
(8, 1, 'Western Cape', NULL, NULL),
(9, 1, 'Northern Cape', NULL, NULL);

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
  `clubCourseID` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`tournamentID`),
  KEY `fk1` (`clubID`),
  KEY `fkgg003` (`golfGroupID`),
  KEY `clubCourseID` (`clubCourseID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=14 ;

--
-- Dumping data for table `tournament`
--

INSERT INTO `tournament` (`tournamentID`, `tourneyName`, `clubID`, `closingDate`, `endDate`, `startDate`, `golfRounds`, `golfGroupID`, `clubCourseID`) VALUES
(9, 'PecanwoodClassic2013', 1, NULL, '2013-08-29 17:25:08', '2013-08-26 17:25:08', 3, 1, NULL),
(10, 'PecanwoodClassic2012', 1, NULL, '2012-04-24 18:03:27', '2012-04-20 18:03:27', 4, 1, NULL),
(11, 'ModderfonteinClassic2012', 3, NULL, '2012-08-28 18:20:25', '2012-08-24 18:20:25', 4, 1, NULL),
(12, 'PecanKidsInvitational2012', 1, NULL, '2012-03-09 18:25:49', '2012-03-05 18:25:49', 4, 1, NULL),
(13, 'PecanKidsInvitational2011', 1, NULL, '2011-02-09 18:53:15', '2011-02-05 18:53:15', 4, 1, NULL);

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
  `scoreRound1` int(11) DEFAULT NULL,
  `scoreRound2` int(11) DEFAULT NULL,
  `scoreRound3` int(11) DEFAULT NULL,
  `scoreRound4` int(11) DEFAULT NULL,
  `tourneyPosition` int(11) unsigned DEFAULT NULL,
  `tourneyPositionTied` int(11) unsigned DEFAULT NULL,
  `winnerFlag` int(11) DEFAULT NULL,
  `playerID` int(11) unsigned DEFAULT NULL,
  `tournamentID` int(11) unsigned DEFAULT NULL,
  `ageGroupID` int(11) unsigned DEFAULT NULL,
  `administratorID` int(11) unsigned DEFAULT NULL,
  `dateRegistered` datetime DEFAULT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `paidFlag` int(11) DEFAULT NULL,
  `totalScore` int(11) DEFAULT NULL,
  PRIMARY KEY (`tourneyPlayerScoreID`),
  UNIQUE KEY `ixtp` (`tournamentID`,`playerID`),
  KEY `ff001` (`tournamentID`),
  KEY `ff002` (`playerID`),
  KEY `ff003` (`ageGroupID`),
  KEY `ff004` (`administratorID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=771 ;

-- --------------------------------------------------------

--
-- Table structure for table `tourneyScoreByRound`
--

CREATE TABLE `tourneyScoreByRound` (
  `tourneyScoreByRoundID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `score1` int(11) DEFAULT NULL,
  `score2` int(11) DEFAULT NULL,
  `score3` int(11) DEFAULT NULL,
  `score4` int(11) DEFAULT NULL,
  `score5` int(11) DEFAULT NULL,
  `score6` int(11) DEFAULT NULL,
  `score7` int(11) DEFAULT NULL,
  `score8` int(11) DEFAULT NULL,
  `score9` int(11) DEFAULT NULL,
  `score10` int(11) DEFAULT NULL,
  `score11` int(11) DEFAULT NULL,
  `score12` int(11) DEFAULT NULL,
  `score13` int(11) DEFAULT NULL,
  `score14` int(11) DEFAULT NULL,
  `score15` int(11) DEFAULT NULL,
  `score16` int(11) DEFAULT NULL,
  `score17` int(11) DEFAULT NULL,
  `score18` int(11) DEFAULT NULL,
  `tourneyPlayerScoreID` int(10) unsigned DEFAULT NULL,
  `golfRound` int(11) DEFAULT NULL,
  PRIMARY KEY (`tourneyScoreByRoundID`),
  KEY `tourneyPlayerScoreID` (`tourneyPlayerScoreID`),
  KEY `golfRound` (`golfRound`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1931 ;

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
  ADD CONSTRAINT `ffgg` FOREIGN KEY (`golfGroupID`) REFERENCES `golfgroup` (`golfGroupID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `club`
--
ALTER TABLE `club`
  ADD CONSTRAINT `fprov` FOREIGN KEY (`provinceID`) REFERENCES `province` (`provinceID`) ON UPDATE NO ACTION;

--
-- Constraints for table `clubCourse`
--
ALTER TABLE `clubCourse`
  ADD CONSTRAINT `clubcourse_ibfk_1` FOREIGN KEY (`clubID`) REFERENCES `club` (`clubID`);

--
-- Constraints for table `golfGroup`
--
ALTER TABLE `golfGroup`
  ADD CONSTRAINT `fkctry` FOREIGN KEY (`countryID`) REFERENCES `country` (`countryID`) ON UPDATE NO ACTION;

--
-- Constraints for table `golfGroupParent`
--
ALTER TABLE `golfGroupParent`
  ADD CONSTRAINT `fkp003` FOREIGN KEY (`parentID`) REFERENCES `parent` (`parentID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fkp004` FOREIGN KEY (`golfGroupID`) REFERENCES `golfgroup` (`golfGroupID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `golfGroupPlayer`
--
ALTER TABLE `golfGroupPlayer`
  ADD CONSTRAINT `fkg007` FOREIGN KEY (`golfGroupID`) REFERENCES `golfgroup` (`golfGroupID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fkg008` FOREIGN KEY (`playerID`) REFERENCES `player` (`playerID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `golfGroupVolunteer`
--
ALTER TABLE `golfGroupVolunteer`
  ADD CONSTRAINT `fkv001` FOREIGN KEY (`golfGroupID`) REFERENCES `golfgroup` (`golfGroupID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fkv002` FOREIGN KEY (`volunteerID`) REFERENCES `volunteer` (`volunteerID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT `fk2` FOREIGN KEY (`parentID`) REFERENCES `parent` (`parentID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `province`
--
ALTER TABLE `province`
  ADD CONSTRAINT `fk001` FOREIGN KEY (`countryID`) REFERENCES `country` (`countryID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `teeTime`
--
ALTER TABLE `teeTime`
  ADD CONSTRAINT `teetime_ibfk_2` FOREIGN KEY (`tourneyPlayerScoreID`) REFERENCES `tourneyplayerscore` (`tourneyPlayerScoreID`);

--
-- Constraints for table `tournament`
--
ALTER TABLE `tournament`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`clubID`) REFERENCES `club` (`clubID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fkgg003` FOREIGN KEY (`golfGroupID`) REFERENCES `golfgroup` (`golfGroupID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `tournament_ibfk_1` FOREIGN KEY (`clubCourseID`) REFERENCES `clubcourse` (`clubCourseID`);

--
-- Constraints for table `tournamentVolunteer`
--
ALTER TABLE `tournamentVolunteer`
  ADD CONSTRAINT `fkt001` FOREIGN KEY (`tournamentID`) REFERENCES `tournament` (`tournamentID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fkt002` FOREIGN KEY (`volunteerID`) REFERENCES `volunteer` (`volunteerID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `tourneyPlayerScore`
--
ALTER TABLE `tourneyPlayerScore`
  ADD CONSTRAINT `ff001` FOREIGN KEY (`tournamentID`) REFERENCES `tournament` (`tournamentID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `ff002` FOREIGN KEY (`playerID`) REFERENCES `player` (`playerID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `ff003` FOREIGN KEY (`ageGroupID`) REFERENCES `agegroup` (`ageGroupID`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `ff004` FOREIGN KEY (`administratorID`) REFERENCES `administrator` (`administratorID`) ON UPDATE NO ACTION;

--
-- Constraints for table `tourneyScoreByRound`
--
ALTER TABLE `tourneyScoreByRound`
  ADD CONSTRAINT `tourneyscorebyround_ibfk_1` FOREIGN KEY (`tourneyPlayerScoreID`) REFERENCES `tourneyplayerscore` (`tourneyPlayerScoreID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
