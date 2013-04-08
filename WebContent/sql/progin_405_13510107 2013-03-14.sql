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
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `taskID` varchar(30) NOT NULL,
  `categoryID` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `taskname` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  `deadline` date NOT NULL,
  PRIMARY KEY (`taskID`)
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `categoryID` varchar(30) NOT NULL,
  `categoryname` varchar(30) NOT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `attachmentID` varchar(30) NOT NULL,
  `taskID` varchar(30) NOT NULL,
  `filepath` varchar(30) NOT NULL,
  PRIMARY KEY (`attachmentID`)
) ENGINE=InnoDB;

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
  PRIMARY KEY (`commentID`)
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `taskID` varchar(30) NOT NULL,
  `tagname` varchar(30) NOT NULL
) ENGINE=InnoDB;

--
-- Dumping data for table `Mahasiswa`
--

INSERT INTO `user` (`username`, `password`, `fullname`, `birthplace`, `birthdate`, `email`, `avatar`) VALUES
('rubiano', 'rubianopassword', 'Rubiano Adityas', 'Bandung', '1992-01-01', 'rubiano.adityas@gmail.com', ''),
('gmochid', 'passwordgmochid', 'Abdurrosyid Broto Handoyo', 'Bandung', '1993-02-02', 'abdurrosyid.broto@gmail.com', ''),
('ordinaryhuman', 'rubianopassword', 'Rubiano Adityas', 'Bandung', '1994-03-03', 'novriady.saputra@gmail.com', '');

--
-- Dumping data for table `Mata_kuliah`
--

INSERT INTO `task` (`taskID`, `categoryID`, `username`, `taskname`, `status`, `deadline`) VALUES
(1, 1, 'ordinaryhuman', 'Mengerjakan Tugas Progin', 'finished', '2013-03-20'),
(2, 1, 'ordinaryhuman', 'Mengerjakan Tugas Kripto', 'unfinished', '2013-03-22'),
(3, 2, 'gmochid', 'Mengerjakan Proyek', 'finished', '2013-03-25');

--
-- Dumping data for table `Mengambil`
--

INSERT INTO `category` (`categoryID`, `categoryname`) VALUES
(1, 'Kuliah'),
(2, 'Keprofesian'),
(3, 'Hiburan'),
(4, 'Utilitas');

--
-- Dumping data for table `attachment`
--

INSERT INTO `attachment` (`attachmentID`, `taskID`, `filepath`) VALUES
(1, 1, ''),
(2, 1, ''),
(3, 2, '');

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`commentID`, `taskID`, `username`, `content`, `timestamps`) VALUES
(1, 2, 'rubiano', 'Ini tugas sebentar lagi deadline lho', '2013-02-03 13:10:00'),
(2, 3, 'gmochid', 'self remainder: kerjakan sehabis semua tugas kelar', '2013-03-07 05:00:00');

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`taskID`, `tagname`) VALUES
(1, 'kuliah'),
(1, 'susah'),
(1, 'berkelompok'),
(2, 'kuliah'),
(2, 'individu'),
(3, 'near deadline');

ALTER TABLE `task`
	ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES category(`categoryID`) ON UPDATE CASCADE ON DELETE RESTRICT,
	ADD CONSTRAINT `task_ibfk_2`FOREIGN KEY (`username`) REFERENCES user(`username`) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE `attachment`
	ADD CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES task(`taskID`) ON UPDATE CASCADE ON DELETE RESTRICT;
	
ALTER TABLE `comment`
	ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES category(`categoryID`) ON UPDATE CASCADE ON DELETE RESTRICT,
	ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`username`) REFERENCES user(`username`) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE `tag`
	ADD CONSTRAINT `tag_ibfk_2` FOREIGN KEY (`taskID`) REFERENCES category(`categoryID`) ON UPDATE CASCADE ON DELETE RESTRICT;
