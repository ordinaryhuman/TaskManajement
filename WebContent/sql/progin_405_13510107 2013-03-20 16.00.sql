-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 20, 2013 at 09:59 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `task_manajement`
--

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `attachmentID` varchar(30) NOT NULL,
  `taskID` varchar(30) NOT NULL,
  `filepath` varchar(30) NOT NULL,
  PRIMARY KEY (`attachmentID`),
  KEY `attachment_ibfk_1` (`taskID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attachment`
--

INSERT INTO `attachment` (`attachmentID`, `taskID`, `filepath`) VALUES
('1', '1', ''),
('2', '1', ''),
('3', '2', '');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `categoryID` varchar(30) NOT NULL,
  `categoryname` varchar(30) NOT NULL,
  `creatorID` varchar(25) NOT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryID`, `categoryname`, `creatorID`) VALUES
('1', 'Kuliah', 'gmochid2'),
('2', 'Keprofesian', 'gmochid2'),
('3', 'Hiburan', 'rubiano');

-- --------------------------------------------------------

--
-- Table structure for table `category_user`
--

CREATE TABLE IF NOT EXISTS `category_user` (
  `categoryID` varchar(25) NOT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`categoryID`,`username`),
  KEY `category_user_ibfk_2` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category_user`
--

INSERT INTO `category_user` (`categoryID`, `username`) VALUES
('1', 'gmochid2'),
('2', 'gmochid2'),
('1', 'ordinaryhuman'),
('2', 'ordinaryhuman'),
('2', 'rubiano');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `commentID` varchar(30) NOT NULL,
  `taskID` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `content` varchar(50) DEFAULT NULL,
  `timestamps` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`commentID`),
  KEY `comment_ibfk_1` (`taskID`),
  KEY `comment_ibfk_2` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`commentID`, `taskID`, `username`, `content`, `timestamps`) VALUES
('1', '2', 'rubiano', 'Ini tugas sebentar lagi deadline lho', '2013-02-03 13:10:00'),
('2', '3', 'gmochid2', 'self remainder: kerjakan sehabis semua tugas kelar', '2013-03-07 05:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `taskID` varchar(30) NOT NULL,
  `tagname` varchar(30) NOT NULL,
  KEY `tag_ibfk_2` (`taskID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`taskID`, `tagname`) VALUES
('1', 'kuliah'),
('1', 'susah'),
('1', 'berkelompok'),
('2', 'kuliah'),
('2', 'individu'),
('3', 'neardeadline');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `taskID` varchar(30) NOT NULL,
  `categoryID` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `taskname` varchar(30) NOT NULL,
  `status` int(11) NOT NULL,
  `deadline` date NOT NULL,
  PRIMARY KEY (`taskID`),
  KEY `task_ibfk_1` (`categoryID`),
  KEY `task_ibfk_2` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`taskID`, `categoryID`, `username`, `taskname`, `status`, `deadline`) VALUES
('1', '1', 'ordinaryhuman', 'Mengerjakan Tugas Progin', 1, '2013-03-20'),
('2', '1', 'ordinaryhuman', 'Mengerjakan Tugas Kripto', 0, '2013-03-22'),
('3', '2', 'gmochid2', 'Bermain Dota', 1, '2013-03-25'),
('4', '1', 'gmochid2', 'Mengerjakan Tugas Sister', 0, '2013-03-23');

-- --------------------------------------------------------

--
-- Table structure for table `task_user`
--

CREATE TABLE IF NOT EXISTS `task_user` (
  `taskID` varchar(30) NOT NULL,
  `userID` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_user`
--


-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `fullname` varchar(30) NOT NULL,
  `birthplace` varchar(30) NOT NULL,
  `birthdate` date NOT NULL,
  `email` varchar(30) NOT NULL,
  `avatar` varchar(30) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `fullname`, `birthplace`, `birthdate`, `email`, `avatar`) VALUES
('gmochid2', 'passwordgmochid2', 'Abdurrosyid Broto Handoyo2', 'Bandung2', '1993-02-03', 'abdurrosyid.broto@gmail.com2', '1'),
('ordinaryhuman', 'rubianopassword', 'Rubiano Adityas', 'Bandung', '1994-03-03', 'novriady.saputra@gmail.com', ''),
('rubiano', 'rubianopassword', 'Rubiano Adityas', 'Bandung', '1992-01-01', 'rubiano.adityas@gmail.com', '');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `category_user`
--
ALTER TABLE `category_user`
  ADD CONSTRAINT `category_user_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `category_user_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES `category` (`categoryID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tag`
--
ALTER TABLE `tag`
  ADD CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `task_user`
--
ALTER TABLE `task_user`
  ADD CONSTRAINT `task_user_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `task_user_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
  
--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `task_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
