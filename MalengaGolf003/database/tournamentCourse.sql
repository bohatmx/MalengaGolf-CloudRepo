-- phpMyAdmin SQL Dump
-- version 3.5.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 01, 2014 at 10:23 AM
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=65 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tournamentCourse`
--
ALTER TABLE `tournamentCourse`
  ADD CONSTRAINT `tournamentCourse_ibfk_1` FOREIGN KEY (`tournamentID`) REFERENCES `tournament` (`tournamentID`),
  ADD CONSTRAINT `tournamentCourse_ibfk_2` FOREIGN KEY (`clubCourseID`) REFERENCES `clubCourse` (`clubCourseID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
