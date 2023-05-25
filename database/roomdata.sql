-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20221207.ce5ce76a8d
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 25, 2023 at 06:00 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `roomdata`
--

-- --------------------------------------------------------

--
-- Table structure for table `room0`
--

CREATE TABLE `room0` (
  `column_name` int(11) DEFAULT NULL,
  `row_no` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room0`
--

INSERT INTO `room0` (`column_name`, `row_no`) VALUES
(1, 4),
(2, 6);

-- --------------------------------------------------------

--
-- Table structure for table `room1`
--

CREATE TABLE `room1` (
  `column_name` int(11) DEFAULT NULL,
  `row_no` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room1`
--

INSERT INTO `room1` (`column_name`, `row_no`) VALUES
(1, 4),
(2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `room3`
--

CREATE TABLE `room3` (
  `column_name` int(11) DEFAULT NULL,
  `row_no` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room3`
--

INSERT INTO `room3` (`column_name`, `row_no`) VALUES
(1, 12),
(2, 13),
(3, 12);

-- --------------------------------------------------------

--
-- Table structure for table `room5`
--

CREATE TABLE `room5` (
  `column_name` int(11) DEFAULT NULL,
  `row_no` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room5`
--

INSERT INTO `room5` (`column_name`, `row_no`) VALUES
(1, 4),
(2, 5),
(3, 7);

-- --------------------------------------------------------

--
-- Table structure for table `room6`
--

CREATE TABLE `room6` (
  `column_name` int(11) DEFAULT NULL,
  `row_no` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room6`
--

INSERT INTO `room6` (`column_name`, `row_no`) VALUES
(1, 4),
(2, 12);

-- --------------------------------------------------------

--
-- Table structure for table `room7`
--

CREATE TABLE `room7` (
  `column_name` int(11) DEFAULT NULL,
  `row_no` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room7`
--

INSERT INTO `room7` (`column_name`, `row_no`) VALUES
(1, 10),
(2, 12),
(3, 13);

-- --------------------------------------------------------

--
-- Table structure for table `room21`
--

CREATE TABLE `room21` (
  `column_name` int(11) DEFAULT NULL,
  `row_no` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room21`
--

INSERT INTO `room21` (`column_name`, `row_no`) VALUES
(1, 3),
(2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `room23`
--

CREATE TABLE `room23` (
  `column_name` int(11) DEFAULT NULL,
  `row_no` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room23`
--

INSERT INTO `room23` (`column_name`, `row_no`) VALUES
(1, 12),
(2, 6),
(3, 7);

-- --------------------------------------------------------

--
-- Table structure for table `room100`
--

CREATE TABLE `room100` (
  `column_name` int(11) DEFAULT NULL,
  `row_no` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room100`
--

INSERT INTO `room100` (`column_name`, `row_no`) VALUES
(1, 25),
(2, 25);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
