-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2022 at 08:59 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `usermanage`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `PID` int(15) NOT NULL,
  `Pcode` varchar(15) NOT NULL,
  `PName` varchar(100) NOT NULL,
  `PNIC` varchar(15) NOT NULL,
  `PhoneNo` varchar(15) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Address` varchar(500) NOT NULL,
  `Password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`PID`, `Pcode`, `PName`, `PNIC`, `PhoneNo`, `Email`, `Address`, `Password`) VALUES
(16, 'aa', 'aaaaaaa', 'aaaa', 'aaaaaaa', 'qqq', 'qqqqq', 'qqqqqqqq'),
(17, 'aa', 'aaaaaaa', 'aaaa', 'aaaaaaa', 'qqq', 'qqqqq', 'qqqqqqqq'),
(19, 'aa', 'aaaaaaa', 'aaaa', 'aaaaaaa', 'qqq', 'qqqqq', 'qqqqqqqq');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`PID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `PID` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
