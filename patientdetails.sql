-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2020 at 06:00 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital`
--

-- --------------------------------------------------------

--
-- Table structure for table `patientdetails`
--

CREATE TABLE `patientdetails` (
  `PID` int(10) NOT NULL,
  `pName` varchar(120) NOT NULL,
  `gender` varchar(120) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `address` varchar(200) NOT NULL,
  `age` varchar(10) NOT NULL,
  `bloodGroup` varchar(15) NOT NULL,
  `nextOfKin` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patientdetails`
--

INSERT INTO `patientdetails` (`PID`, `pName`, `gender`, `phone`, `address`, `age`, `bloodGroup`, `nextOfKin`) VALUES
(29, 'jathushan', 'Male', '0758562497', 'jaffna', '22', 'B_negative', 'friend'),
(30, 'vathu', 'Female', '0765489652', 'colombo 3', '25', 'AB_negative', 'brother'),
(31, 'senthu', 'Male', '0756985212', 'malabe', '23', 'O_positive', 'friend'),
(32, 'D.S.bandara', 'Male', '0712265847', 'colombo13', '32', 'AB_positive', 'aasd'),
(33, 'T.Interajith', 'Male', '0765894269', 'vavuniya', '25', 'A_positive', 'egtsdd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `patientdetails`
--
ALTER TABLE `patientdetails`
  ADD PRIMARY KEY (`PID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `patientdetails`
--
ALTER TABLE `patientdetails`
  MODIFY `PID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
