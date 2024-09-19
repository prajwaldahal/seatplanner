-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20221207.ce5ce76a8d
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 25, 2023 at 05:59 PM
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
-- Database: `seatplanner`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `uname` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`uname`, `password`) VALUES
('admin', 'root12');

-- --------------------------------------------------------

--
-- Table structure for table `studentlist`
--

CREATE TABLE `studentlist` (
  `studentid` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `faculty` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `studentlist`
--

INSERT INTO `studentlist` (`studentid`, `name`, `faculty`) VALUES
(2, 'ram dahal', 'C.E 1st'),
(3, 'shyam khatri', 'BCA 1st'),
(4, 'hari jadeja', 'C.E 1st'),
(5, 'hari khadka', 'C.E 1st'),
(6, 'ram dahal', 'C.E 1st'),
(17, 'Catrin Gutzeit', 'BIM 5th'),
(19, 'Armgard Kopf', 'BCA 5th'),
(20, 'Korth Baur', 'BCA 3rd'),
(21, 'Lenchen Schwarz', 'BBA 4th'),
(23, 'Krispin Heckler', 'BCA 3rd'),
(24, 'Wilbrecht Ebel', 'BCA 3rd'),
(25, 'Dieter ietzsche', 'BCA 6th'),
(26, 'Rüder Hermann', 'BBA 8th'),
(27, 'California Zabel', 'BBA 7th'),
(28, 'Fredegar chultz', 'BBA 7th'),
(29, 'Lisa-Maria Marschner', 'BIM 2nd'),
(30, 'Aurion  Günther', 'BIM 4th'),
(31, 'Friedwart osenblatt', 'BBA 3rd'),
(32, 'Cölestin Treviranus', 'BIM 4th'),
(33, 'Abeline  Kramer', 'BBA 6th'),
(34, 'Balte  Joseph', 'BCA 1st'),
(35, 'Chlothar Franz', 'BIM 5th'),
(36, 'Dietlana Auerbach', 'BIM 4th'),
(37, 'Mirinda Bartels', 'BIM 7th'),
(38, 'Diepold Arnold', 'BBA 6th'),
(39, 'Janeric Thalberg', 'BCA 2nd'),
(40, 'Adriane  chs', 'BCA 8th'),
(41, 'Volbrecht Angst', 'BBA 2nd'),
(42, 'Haiko erl', 'BCA 7th'),
(43, 'Avenell olken', 'BIM 1st'),
(44, 'Annalena Lichtenberg', 'BCA 3rd'),
(45, 'Haita aulig', 'BCA 8th'),
(46, 'Yente Fischnaller', 'BCA 4th'),
(47, 'Justus Laufer', 'BIM 4th'),
(48, 'Ottfried Abel', 'BIM 8th'),
(49, 'Almute Josef', 'BCA 8th'),
(50, 'Friederun Appel', 'BIM 7th'),
(51, 'Siegher Schweizer', 'BBA 2nd'),
(52, 'Rubertus Leist', 'BBA 4th'),
(53, 'Levia Gardenberg', 'BIM 4th'),
(54, 'Günther chulmeister', 'BCA 1st'),
(55, 'Edeltrude Hanke', 'BBA 2nd'),
(56, 'Uodalrich Böhm', 'BCA 6th'),
(57, 'Kandidus Albrecht', 'BIM 6th'),
(58, 'Yascha Baade', 'BCA 5th'),
(59, 'Conczlin Felder', 'BCA 7th'),
(60, 'Baldo  Grasse', 'BCA 2nd'),
(61, 'Gundelinde Abert', 'BCA 4th'),
(62, 'Varina Konradi', 'BIM 4th'),
(63, 'Zacharias Kurz', 'BCA 5th'),
(64, 'Joannis Buhmann', 'BIM 5th'),
(65, 'Baldur  ebinder', 'BCA 4th'),
(66, 'Eitel-Fritz aspe', 'BBA 1st'),
(67, 'Annchristin osenmüller', 'BCA 5th'),
(68, 'Friedensreich Geller', 'BCA 7th'),
(69, 'Dörk iedel', 'BIM 7th'),
(70, 'Zilly Stern', 'BBA 3rd'),
(71, 'Falko Hoyer', 'BCA 5th'),
(72, 'Thorin Gerngross', 'BCA 7th'),
(73, 'Goswin Dils', 'BIM 7th'),
(74, 'Maybee Dorn', 'BBA 8th'),
(75, 'Alexandrine  Seidlitz', 'BBA 4th'),
(76, 'Syria ascher', 'BBA 3rd'),
(77, 'Balian  ander', 'BIM 3rd'),
(78, 'Alruna Baumann', 'BIM 3rd'),
(79, 'Thees cheidemann', 'BBA 5th'),
(80, 'Oktavian Lorenz', 'BBA 4th'),
(81, 'Benedikta aul', 'BCA 4th'),
(82, 'Friedlinde odersohn', 'BIM 8th'),
(83, 'Oktavia Weiss', 'BIM 2nd'),
(84, 'Eirin Göring', 'BIM 4th'),
(85, 'Hanjo Krebs', 'BIM 2nd'),
(86, 'Rebekka chiefer', 'BIM 1st'),
(87, 'Heico Drechsler', 'BBA 6th'),
(88, 'Bosse Weber', 'BIM 8th'),
(89, 'Avera  Dorn', 'BBA 7th'),
(90, 'Romarich Krüger', 'BCA 8th'),
(91, 'Aurion  Kern', 'BBA 2nd'),
(92, 'Wilfried Döring', 'BCA 8th'),
(93, 'Marinda Totleben', 'BCA 8th'),
(94, 'Hatto Grimm', 'BBA 5th'),
(95, 'Tanaja Wagner', 'BCA 5th'),
(96, 'Marlie Dörner', 'BCA 6th'),
(97, 'Aurion  Kugler', 'BIM 4th'),
(98, 'Burkhardt Forster', 'BIM 2nd'),
(99, 'Itria Abich', 'BBA 5th'),
(100, 'Talliana Genkel', 'BBA 7th'),
(101, 'Lennik Kirschner', 'BIM 7th'),
(102, 'Juni Braunfels', 'BIM 5th'),
(103, 'Kathrina Wieser', 'BBA 5th'),
(104, 'Mose Seiler', 'BIM 1st'),
(105, 'Wikhard Bach', 'BIM 1st'),
(106, 'Berno Altmann', 'BIM 1st'),
(107, 'Witold Freund', 'BCA 6th'),
(108, 'Jehova Zeller', 'BIM 3rd'),
(109, 'Gottlinde Abicht', 'BBA 7th'),
(110, 'Burghilde Weigl', 'BCA 3rd'),
(111, 'Balian  Winkelhock', 'BIM 1st'),
(112, 'Fridolin Abert', 'BBA 3rd'),
(113, 'Mechthild Bruner', 'BCA 3rd'),
(114, 'Neolie Kellermann', 'BCA 6th'),
(115, 'Heico Totleben', 'BIM 5th'),
(116, 'Bulk ack', 'BIM 5th'),
(117, 'Maylin Katz', 'BIM 8th'),
(118, 'Waida Abel', 'BIM 2nd'),
(119, 'Kenoah Stern', 'BIM 2nd'),
(120, 'Almuth Döring', 'BCA 8th'),
(121, 'Clovis Simmel', 'BIM 8th'),
(122, 'Chlothar Altmann', 'BIM 6th'),
(123, 'Barthel  Kolbe', 'BBA 4th'),
(124, 'Catharina Baum', 'BIM 7th'),
(125, 'Jeanny Gutzeit', 'BIM 4th'),
(126, 'Rautendelein chulze', 'BBA 7th'),
(127, 'Edelbert Mann', 'BIM 1st'),
(128, 'Wiprecht ichler', 'BIM 8th'),
(129, 'Veit Kromberg', 'BIM 5th'),
(130, 'Fadrina Horkheimer', 'BCA 6th'),
(131, 'Endris Bohrmann', 'BBA 6th'),
(132, 'Abby  Zöller', 'BCA 4th'),
(133, 'Laif Blumentahl', 'BBA 2nd'),
(134, 'Mirke Zeiger', 'BIM 1st'),
(135, 'Joyceline Haas', 'BBA 1st'),
(136, 'Rudger Appel', 'BBA 7th'),
(137, 'Adine  Immerig', 'BBA 7th'),
(138, 'Ute Klee', 'BCA 1st'),
(139, 'Courtney Grossmann', 'BBA 5th'),
(140, 'Winka Horkheimer', 'BIM 5th'),
(141, 'Filina chirrmann', 'BBA 3rd'),
(142, 'Dark Bohrmann', 'BCA 3rd'),
(143, 'Annedore Anger', 'BCA 7th'),
(144, 'Abbeygail  osenbaum', 'BCA 5th'),
(145, 'Almuth Makensen', 'BCA 3rd'),
(146, 'Hadewin Böhm', 'BIM 7th'),
(147, 'Romea Baum', 'BBA 5th'),
(148, 'Lieas Meiendorf', 'BCA 5th'),
(149, 'Emerentia Luckner', 'BCA 3rd'),
(150, 'Just Mering', 'BIM 6th'),
(151, 'Friedebert lehwe', 'BBA 7th'),
(152, 'Volbrecht Knecht', 'BCA 4th'),
(153, 'Eusebia Hornberger', 'BBA 3rd'),
(154, 'Moritz Feuerbach', 'BCA 6th'),
(155, 'Cilli Buchloh', 'BBA 3rd'),
(156, 'Eleen Bohrmann', 'BBA 8th'),
(157, 'Chlodwig Altmann', 'BIM 8th'),
(158, 'Richart Blücher', 'BBA 3rd'),
(159, 'Naema Merkel', 'BCA 4th'),
(160, 'Chaymae Imhof', 'BCA 7th'),
(161, 'Adlin  Bauer', 'BBA 8th'),
(162, 'Romuald Abert', 'BBA 2nd'),
(163, 'Madlena Bach', 'BIM 8th'),
(164, 'Hugobald Hermann', 'BBA 6th'),
(165, 'Waltraud Bebel', 'BCA 4th'),
(166, 'Achime  Tetzner', 'BBA 6th'),
(167, 'Luitpold Bongard', 'BIM 1st'),
(168, 'Bertin Feuermann', 'BBA 2nd'),
(169, 'Tabea choll', 'BBA 3rd'),
(170, 'Avera  Bergmann', 'BIM 7th'),
(171, 'Ennja Fleischer', 'BIM 2nd'),
(172, 'Hadassa Lohrer', 'BCA 4th'),
(173, 'Bassel  Drews', 'BIM 1st'),
(174, 'Filibert Göring', 'BBA 4th'),
(175, 'Juli Freund', 'BCA 1st'),
(176, 'Dominikus Angst', 'BBA 5th'),
(177, 'Annamarie Albrecht', 'BBA 2nd'),
(178, 'Friedhilde Köstlin', 'BCA 4th'),
(179, 'Bertrade Mann', 'BCA 1st'),
(180, 'Annalisa oritz', 'BIM 3rd'),
(181, 'Sigwin Stuchenberg', 'BIM 3rd'),
(182, 'Hubertina Dils', 'BBA 7th'),
(183, 'Dietleib Kant', 'BCA 1st'),
(184, 'Gangolf Dils', 'BBA 7th'),
(185, 'Carine chuhmacher', 'BCA 7th'),
(186, 'Alraune Bülow', 'BBA 7th'),
(187, 'Evianne Tischbein', 'BIM 7th'),
(188, 'Meridian Blücher', 'BBA 4th'),
(189, 'Kathrein cheffer', 'BCA 4th'),
(190, 'Xenie Kallisen', 'BCA 4th'),
(191, 'Felizia Dörner', 'BBA 1st'),
(192, 'Ailine  berländer', 'BIM 1st'),
(193, 'Maik Falkenstein', 'BIM 3rd'),
(194, 'Fridoline Lerch', 'BIM 6th'),
(195, 'Humbert Simmel', 'BCA 3rd'),
(196, 'Agnete  Appel', 'BBA 1st'),
(197, 'Inola Dils', 'BIM 4th'),
(198, 'Odowakar Albrecht', 'BCA 8th'),
(199, 'Kunz Döring', 'BIM 7th'),
(200, 'Lacoste Zöller', 'BCA 8th'),
(201, 'roshan rai', 'BBA 3rd'),
(202, 'Subhanjal Subhanjal', 'BCA 5th'),
(221, 'prajwal dahal', 'BCA 1st'),
(223, 'prajwal dahal', 'BCA 1st'),
(225, 'prajwal dahal', 'BIM 1st'),
(300, 'subhanjal rai', 'BBA 1st'),
(1234, 'prajwal dahal', 'BIM 1st'),
(1276, 'ram barma', 'BIM 5th'),
(1456, 'prajwal dahal', 'BCA 1st'),
(1955, 'subhanjal giri', 'BCA 5th'),
(12345, 'nikit rai', 'BIM 3rd'),
(12376, 'prajwal dahal', 'BIM 4th'),
(123456, 'prajwal dahal', 'BIM 8th');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `studentlist`
--
ALTER TABLE `studentlist`
  ADD PRIMARY KEY (`studentid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
