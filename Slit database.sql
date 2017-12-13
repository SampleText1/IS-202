-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Dec 13, 2017 at 03:24 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `slit`
--

-- --------------------------------------------------------

--
-- Table structure for table `modulbesvarelse`
--

CREATE TABLE `modulbesvarelse` (
  `id` int(11) NOT NULL,
  `vurdering` varchar(25) DEFAULT NULL,
  `innhold` varchar(25) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  `m_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `modulbesvarelse`
--

INSERT INTO `modulbesvarelse` (`id`, `vurdering`, `innhold`, `s_id`, `m_id`) VALUES
(1, 'Godkjent', 'Modul', 1, 6),
(2, 'Godkjent', 'xD', 3, 7),
(4, 'Godkjent', 'Modul 5', 1, 6),
(7, 'Godkjent', 'Test', 4, 6);

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `title` varchar(75) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `goals` varchar(250) DEFAULT NULL,
  `resources` varchar(250) DEFAULT NULL,
  `task` varchar(250) DEFAULT NULL,
  `deadline` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`id`, `title`, `description`, `goals`, `resources`, `task`, `deadline`) VALUES
(6, 'Modul 1', 'Dette er modul nummer en.', 'LÃ¦r deg Java.', NULL, 'Hehe', NULL),
(7, 'Ã', 'Ã', 'Ã', NULL, 'ASDHASHD', NULL),
(8, 'Modul 6', 'Test', 'Test', NULL, 'Test', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `uploads`
--

CREATE TABLE `uploads` (
  `ID` int(11) NOT NULL,
  `modID` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `fileName` varchar(45) DEFAULT NULL,
  `file` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `useraccount`
--

CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `firstName` varchar(25) DEFAULT NULL,
  `lastName` varchar(25) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `pass` varchar(30) DEFAULT NULL,
  `admin` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `useraccount`
--

INSERT INTO `useraccount` (`id`, `firstName`, `lastName`, `email`, `pass`, `admin`) VALUES
(1, 'Vegard Arvid', 'Steinnes', 'vegard.steinnes@gmail.com', 'passord', 1),
(2, 'Stegard', 'Veinnes', 'steve@gmail.com', 'pass', 0),
(3, 'Frank', 'Reynolds', 'egg@gmail.com', 'charlie', NULL),
(4, 'Charlie', 'Kelly', 'milkstake@gmail.com', 'frank', NULL),
(19, 'Vegard Arvid', 'Steinnes', 'vegas17@uia.no', 'pass', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `modulbesvarelse`
--
ALTER TABLE `modulbesvarelse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `s_id` (`s_id`),
  ADD KEY `m_id` (`m_id`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uploads`
--
ALTER TABLE `uploads`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_modID` (`modID`);

--
-- Indexes for table `useraccount`
--
ALTER TABLE `useraccount`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `uploads`
--
ALTER TABLE `uploads`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `useraccount`
--
ALTER TABLE `useraccount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `modulbesvarelse`
--
ALTER TABLE `modulbesvarelse`
  ADD CONSTRAINT `modulbesvarelse_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `useraccount` (`id`),
  ADD CONSTRAINT `modulbesvarelse_ibfk_2` FOREIGN KEY (`m_id`) REFERENCES `module` (`id`);

--
-- Constraints for table `uploads`
--
ALTER TABLE `uploads`
  ADD CONSTRAINT `FK_modID` FOREIGN KEY (`modID`) REFERENCES `module` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
