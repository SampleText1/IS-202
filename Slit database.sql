-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Dec 13, 2017 at 11:50 PM
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
  `vurdering` varchar(25) DEFAULT 'Ikke godkjent',
  `innhold` varchar(25) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  `m_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `modulbesvarelse`
--

INSERT INTO `modulbesvarelse` (`id`, `vurdering`, `innhold`, `s_id`, `m_id`) VALUES
(1, 'Ikke godkjent', 'Modul1.zip', 29, 9),
(2, 'Ikke godkjent', 'Modul2.zip', 29, 10),
(3, 'Ikke godkjent', 'Modul3.zip', 29, 11),
(4, 'Ikke godkjent', 'Modul4.zip', 29, 12),
(5, 'Ikke godkjent', 'Modul5.zip', 29, 13),
(6, 'Ikke godkjent', 'Modul1.zip', 30, 9),
(7, 'Ikke godkjent', 'Modul2.zip', 32, 10),
(8, 'Ikke godkjent', 'Modul3.zip', 31, 11),
(9, 'Ikke godkjent', 'Modul4.zip', 33, 12),
(10, 'Ikke godkjent', 'Modul5.zip', 34, 13);

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
(9, 'Modul 1 - Introduksjon til Blue J', 'I den aller første modulen skal du bli kjent med programmet vi skal bruke for å lære Java, som er BlueJ.', 'Bli bedre kjent med BlueJ.', 'Objects First with Java: A Practical Introduction using BlueJ og BlueJ.', 'Åpne BlueJ og prøv deg fram. Gjør testoppgavene i kapittel 1.', '2018-01-17'),
(10, 'Modul 2 - Klassedefinisjoner', 'I denne modulen skal dere lage deres første klasser.', 'Målet er å kunne lage en klasse som gjør noe.', 'Objects First with Java: A Practical Introduction using BlueJ og BlueJ.', 'Lag en klasse som som har metoder som kan returnere en verdi.', '2018-01-30'),
(11, 'Modul 3 - Objektinteraksjon', 'I denne modulen skal dere lage klasser som kommuniserer med hverandre.', 'Målet er å kunne lage klasser som kommuniserer med hverandre.', 'Objects First with Java: A Practical Introduction using BlueJ og BlueJ.', 'Lag tre klasser som kommuniserer med hverandre.', '2018-02-14'),
(12, 'Modul 4 - Gruppering av objekter', 'I denne modulen skal dere bli kjent med ArrayList, iteratorer og looper.', 'Målet er å forstå hvordan ArrayList fungerer, samt kunne bruke/skrive en iterator/loop.', 'Objects First with Java: A Practical Introduction using BlueJ og BlueJ.', 'Lag en klasse som går igjennom en liste av et slag ved hjelp av iterator eller loop.', '2018-02-28'),
(13, 'Modul 5 - Design av klasser', 'I denne modulen skal dere bli bedre til å designe klasser.', 'Målet er å lære seg å drive med ansvarsdrevet design.', 'Objects First with Java: A Practical Introduction using BlueJ.', 'Forklar hva begrepene coupling, cohesion og refactoring betyr.', '2018-03-13');

-- --------------------------------------------------------

--
-- Table structure for table `uploads`
--

CREATE TABLE `uploads` (
  `fileID` int(11) NOT NULL,
  `modID` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `fileName` varchar(45) DEFAULT NULL,
  `file` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `uploads`
--

INSERT INTO `uploads` (`fileID`, `modID`, `name`, `fileName`, `file`) VALUES
(2, 9, 'Vegard Arvid Steinnes', 'UploadTest.txt', '');

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
(26, 'admin', 'admin', 'admin', 'admin', 1),
(29, 'Abdikani', 'Gureye', 'abdig17@uia.no', 'abdgur', NULL),
(30, 'Joakim', 'Hellang', 'joakh17@uia.no', 'joahel', NULL),
(31, 'Jørgen', 'Kongsberg', 'jorgk17@uia.no', 'jorkon', NULL),
(32, 'Marius', 'Kaurin', 'marik17@uia.no', 'markar', NULL),
(33, 'Mikael', 'Kimerud', 'mikak17@uia.no', 'mikkim', NULL),
(34, 'Preben', 'Tjemsland', 'prebt17@uia.no', 'pretje', NULL);

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
  ADD PRIMARY KEY (`fileID`),
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
-- AUTO_INCREMENT for table `modulbesvarelse`
--
ALTER TABLE `modulbesvarelse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `uploads`
--
ALTER TABLE `uploads`
  MODIFY `fileID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `useraccount`
--
ALTER TABLE `useraccount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

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
