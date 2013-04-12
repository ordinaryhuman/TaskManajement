-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 12, 2013 at 03:45 
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
  `filename` varchar(100) NOT NULL,
  `type` varchar(25) NOT NULL,
  PRIMARY KEY (`attachmentID`),
  KEY `attachment_ibfk_1` (`taskID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attachment`
--

INSERT INTO `attachment` (`attachmentID`, `taskID`, `filename`, `type`) VALUES
('1', '5', 'att_5_temp.txt', 'file'),
('2', '5', 'att_5_aneh.jpg', 'image'),
('3', '5', 'att_5_tes.mp4', 'video'),
('4', '5', 'att_5_tes.docx', 'file');

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
('2', '3', 'gmochid2', 'self remainder: kerjakan sehabis semua tugas kelar', '2013-03-07 05:00:00'),
('4', '5', 'gmochid2', 'gdsh', '2013-04-12 19:51:01');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `taskID` varchar(30) NOT NULL,
  `tagname` varchar(30) NOT NULL,
  PRIMARY KEY (`taskID`, `tagname`),
  KEY `tag_ibfk_2` (`taskID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`taskID`, `tagname`) VALUES
('2', 'kuliah'),
('2', 'individu'),
('3', 'neardeadline'),
('1', 'susah'),
('1', 'berkelompok'),
('5', 'asdasf'),
('6', 'asda'),
('5', 'zzzz'),
('3', 'abc'),
('5', 'berkelompok'),
('1', 'kuliah');

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
('2', '1', 'ordinaryhuman', 'Mengerjakan Tugas Kripto', 1, '2013-03-22'),
('3', '2', 'gmochid2', 'Bermain Dota', 1, '2013-03-25'),
('4', '1', 'gmochid2', 'Mengerjakan Tugas Sister', 0, '2013-03-23'),
('5', '2', 'gmochid2', 'coba', 0, '2013-04-30'),
('6', '1', 'gmochid2', 'safsaf', 0, '2013-03-13');

-- --------------------------------------------------------

--
-- Table structure for table `task_user`
--

CREATE TABLE IF NOT EXISTS `task_user` (
  `taskID` varchar(30) NOT NULL,
  `userID` varchar(30) NOT NULL,
  KEY `task_user_ibfk_1` (`taskID`),
  KEY `task_user_ibfk_2` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_user`
--

INSERT INTO `task_user` (`taskID`, `userID`) VALUES
('5', 'gmochid2'),
('3', 'gmochid2'),
('4', 'gmochid2'),
('1', 'ordinaryhuman'),
('2', 'ordinaryhuman'),
('6', 'gmochid2'),
('3', 'rubiano'),
('5', 'rubiano');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `fullname` varchar(30) NOT NULL,
  `birthdate` date NOT NULL,
  `email` varchar(30) NOT NULL,
  `avatar` varchar(30) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `fullname`, `birthdate`, `email`, `avatar`) VALUES
('gmochid2', '123', 'Abdurrosyid Broto H', '1993-02-03', 'abdurrosyidbroto@gmail.com2', 'gmochid2.jpg'),
('ordinaryhuman', 'rubianopassword', 'Rubiano Adityas', '1994-03-03', 'novriadysaputra@gmail.com', 'ordinaryhuman.jpg'),
('rubiano', 'rubianopassword', 'Rubiano Adityas', '1992-01-01', 'rubianoadityas@gmail.com', 'rubiano.jpg');

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
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tag`
--
ALTER TABLE `tag`
  ADD CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `task_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `task_user`
--
ALTER TABLE `task_user`
  ADD CONSTRAINT `task_user_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `task_user_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
