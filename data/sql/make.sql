-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 07, 2015 at 10:10 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `make`
--

CREATE TABLE IF NOT EXISTS `make` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `make`
--

INSERT INTO `make` (`id`, `name`) VALUES
(1, 'Acura'),
(2, 'Alfa Romeo'),
(3, 'Aston Martin'),
(4, 'Audi'),
(5, 'BMW'),
(6, 'Bentley'),
(7, 'Buick'),
(8, 'Cadillac'),
(9, 'Chevrolet'),
(10, 'Chrysler'),
(11, 'Dodge'),
(12, 'FIAT'),
(13, 'Ferrari'),
(14, 'Ford'),
(15, 'GMC'),
(16, 'Honda'),
(17, 'Hyundai'),
(18, 'Infiniti'),
(19, 'Jaguar'),
(20, 'Jeep'),
(21, 'Kia'),
(22, 'Lamborghini'),
(23, 'Land Rover'),
(24, 'Lexus'),
(25, 'Lincoln'),
(26, 'MINI'),
(27, 'Maserati'),
(28, 'Mazda'),
(29, 'McLaren'),
(30, 'Mercedes-Benz'),
(31, 'Mitsubishi'),
(32, 'Nissan'),
(33, 'Porsche'),
(34, 'Ram'),
(35, 'Rolls-Royce'),
(36, 'Scion'),
(37, 'Subaru'),
(38, 'Tesla'),
(39, 'Toyota'),
(40, 'Volkswagen'),
(41, 'Volvo'),
(42, 'smart'),
(43, 'Lotus');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
