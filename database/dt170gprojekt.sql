-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 07, 2024 at 10:20 AM
-- Server version: 10.4.32-MariaDB-log
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dt170gprojekt`
--
CREATE DATABASE IF NOT EXISTS `dt170gprojekt` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `dt170gprojekt`;

-- --------------------------------------------------------

--
-- Table structure for table `a_la_carte_menu`
--

DROP TABLE IF EXISTS `a_la_carte_menu`;
CREATE TABLE `a_la_carte_menu` (
  `a_la_carte_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `a_la_carte_menu`
--

INSERT INTO `a_la_carte_menu` (`a_la_carte_id`, `name`, `description`, `type`, `price`) VALUES
(17, 'Oxfile', 'med potatisgratäng och rödvinssås', 'Huvudrätt', 149),
(18, 'Älg-gryta', 'Älg-gryta med svamp och gräddsås, serveras med råstekt potatis', 'Huvudrätt', 139),
(19, 'Parmesangratinerad torskrygg', 'serveras med pressad potatis och gröna bönor', 'Huvudrätt', 119),
(20, 'Pasta carbonara', 'på pecorino och parmesan-ost', 'Huvudrätt', 109),
(21, 'Toast skagen', 'med löjrom på surdegsbröd', 'Förrätt', 79),
(22, 'Miso-soppa', 'soppa på miso och sjögräs', 'Förrätt', 59),
(23, 'Tomatsallad', 'med ruccola och pinjenötter', 'Förrätt', 49),
(24, 'Vitlöksbröd', 'surdegsbaguette med vitlökssmör', 'Förrätt', 69),
(25, 'Kladdkaka', 'serveras med grädde', 'Efterrätt', 59),
(26, 'Pannacotta', 'ChokladPannacotta med hallonsylt', 'Efterrätt', 69),
(27, 'Pecanpaj', 'paj på Pecannötter, serveras med grädde', 'Efterrätt', 89),
(28, 'Glass med friterad banan', 'glass som serveras men friterad banan och lönnsirap', 'Efterrätt', 59);

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `telephone` varchar(12) NOT NULL,
  `amount` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`booking_id`, `name`, `telephone`, `amount`, `date`, `time`) VALUES
(11, 'jesper', '0761363895', 4, '2024-03-14', '18:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `drinks`
--

DROP TABLE IF EXISTS `drinks`;
CREATE TABLE `drinks` (
  `drink_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `drinks`
--

INSERT INTO `drinks` (`drink_id`, `name`, `description`, `price`) VALUES
(1, 'Fanta', 'EN TOAST', 38),
(2, 'Somersby cider', '4,5 %', 75),
(3, 'vatten', 'glas vatten', 0),
(4, 'vatten', 'glas vatten', 0),
(5, 'vatten', 'glas vatten', 0),
(6, 'vatten', 'glas vatten', 0),
(7, 'vatten', 'glas vatten', 0),
(8, 'vatten', 'glas vatten', 0),
(9, 'vatten', 'glas vatten', 0),
(10, 'vatten', 'glas vatten', 19),
(11, 'vatten', 'glas vatten', 19),
(12, 'vatten', 'glas vatten', 19),
(13, 'mojito', 'med mynta', 99),
(14, 'drink', 'test', 0),
(16, 'Stor stark', 'en stor stark öl, flera sorter', 69),
(17, 'rött vin', 'ett glas zinfadel', 79),
(18, 'Cola', 'Coca Cola', 29),
(19, 'IPA', 'IPA från Örebros bryggeri, 33cl', 79),
(20, 'Vatten', 'ett glas vatten', 0);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `adress` varchar(255) NOT NULL,
  `telephone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `name`, `role`, `adress`, `telephone`) VALUES
(1, 'Anton Antonsson', 'Kock', 'Storgatan 12\r\n54822 Sundsvall', 70516546),
(2, 'Cecilia Servitris', 'Service', 'Storgatan 23\r\n54654 Sundsvall', 7064654),
(3, 'Benjamin Bok', 'Service', 'Storgatan 3\r\n46545 Sundsvall', 70544654);

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
CREATE TABLE `events` (
  `event_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `price` int(11) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `name`, `date`, `time`, `price`, `description`) VALUES
(12, 'Algortim-stuga', '2024-03-09', '17:00:00', 0, 'Martin Kjellqvist lär er om Bogo-sort'),
(13, ' tävling Tyngdlyftning för grodor', '2024-03-16', '15:00:00', 100, 'Ta med din Groda och bevisa dess styrka!!!'),
(14, 'Jazz kväll', '2024-03-22', '21:00:00', 99, 'kom och dansa till obegriplig musik!!');

-- --------------------------------------------------------

--
-- Table structure for table `lunch_menu`
--

DROP TABLE IF EXISTS `lunch_menu`;
CREATE TABLE `lunch_menu` (
  `lunch_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lunch_menu`
--

INSERT INTO `lunch_menu` (`lunch_id`, `name`, `description`, `date`, `price`) VALUES
(2, 'fiskgratäng', 'fiskgratäng på torsk', '2024-03-06', 99),
(3, 'purjolökssoppa', 'en soppa med potatis och purjolök och grädde', '2024-03-07', 99),
(5, 'lövbiff och hasselbackspotatis', 'smörgratinerad hasselbackspotatis med lövbiff och rödvinssås', '2024-03-05', 99),
(6, 'vintergryta', 'vintergryta gjord på älgstek, serveras med ris', '2024-03-08', 99),
(21, 'köttbullar och potatis', 'köttbullar och potatis med gräddsås och lingonsylt', '2024-03-04', 99),
(22, 'Råraka', 'med stekt fläsk och lingonsylt', '2024-03-11', 99),
(23, 'schnitzel', 'med pommes och sallad', '2024-03-12', 99);

-- --------------------------------------------------------

--
-- Table structure for table `purchased_a_la_carte`
--

DROP TABLE IF EXISTS `purchased_a_la_carte`;
CREATE TABLE `purchased_a_la_carte` (
  `purchased_ID` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `a_la_carte_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `purchased_drinks`
--

DROP TABLE IF EXISTS `purchased_drinks`;
CREATE TABLE `purchased_drinks` (
  `purchased_ID` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `drink_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
CREATE TABLE `receipt` (
  `receipt_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_order`
--

DROP TABLE IF EXISTS `restaurant_order`;
CREATE TABLE `restaurant_order` (
  `restaurant_order_id` int(11) NOT NULL,
  `status_appetizer` varchar(255) NOT NULL,
  `status_main` varchar(255) NOT NULL,
  `status_dessert` varchar(255) NOT NULL,
  `restaurant_table_id` int(11) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `order_status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `shift`
--

DROP TABLE IF EXISTS `shift`;
CREATE TABLE `shift` (
  `shift_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `type` varchar(255) NOT NULL,
  `employee_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tables`
--

DROP TABLE IF EXISTS `tables`;
CREATE TABLE `tables` (
  `table_number` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  `table_size` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tables`
--

INSERT INTO `tables` (`table_number`, `status`, `table_size`) VALUES
(1, 'Free', 4),
(2, 'Free', 6),
(3, 'Free', 4),
(4, 'Free', 4),
(5, 'Free', 6),
(6, 'Free', 6),
(7, 'Free', 2),
(8, 'Free', 4),
(9, 'Free', 4),
(10, 'Free', 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `a_la_carte_menu`
--
ALTER TABLE `a_la_carte_menu`
  ADD PRIMARY KEY (`a_la_carte_id`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`booking_id`);

--
-- Indexes for table `drinks`
--
ALTER TABLE `drinks`
  ADD PRIMARY KEY (`drink_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`event_id`);

--
-- Indexes for table `lunch_menu`
--
ALTER TABLE `lunch_menu`
  ADD PRIMARY KEY (`lunch_id`);

--
-- Indexes for table `purchased_a_la_carte`
--
ALTER TABLE `purchased_a_la_carte`
  ADD PRIMARY KEY (`purchased_ID`),
  ADD KEY `aLaCartePuchasedByOrder` (`order_id`),
  ADD KEY `ALaCartePurchasedByALaCarte` (`a_la_carte_id`);

--
-- Indexes for table `purchased_drinks`
--
ALTER TABLE `purchased_drinks`
  ADD PRIMARY KEY (`purchased_ID`),
  ADD KEY `DrinkPurchasedByOrderID` (`order_id`),
  ADD KEY `DrinkPurchasedByDrinkID` (`drink_id`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`receipt_id`);

--
-- Indexes for table `restaurant_order`
--
ALTER TABLE `restaurant_order`
  ADD PRIMARY KEY (`restaurant_order_id`);

--
-- Indexes for table `shift`
--
ALTER TABLE `shift`
  ADD PRIMARY KEY (`shift_id`);

--
-- Indexes for table `tables`
--
ALTER TABLE `tables`
  ADD PRIMARY KEY (`table_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `a_la_carte_menu`
--
ALTER TABLE `a_la_carte_menu`
  MODIFY `a_la_carte_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `booking_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `drinks`
--
ALTER TABLE `drinks`
  MODIFY `drink_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `lunch_menu`
--
ALTER TABLE `lunch_menu`
  MODIFY `lunch_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `purchased_a_la_carte`
--
ALTER TABLE `purchased_a_la_carte`
  MODIFY `purchased_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=172;

--
-- AUTO_INCREMENT for table `purchased_drinks`
--
ALTER TABLE `purchased_drinks`
  MODIFY `purchased_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT for table `restaurant_order`
--
ALTER TABLE `restaurant_order`
  MODIFY `restaurant_order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT for table `tables`
--
ALTER TABLE `tables`
  MODIFY `table_number` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `purchased_a_la_carte`
--
ALTER TABLE `purchased_a_la_carte`
  ADD CONSTRAINT `ALaCartePurchasedByALaCarte` FOREIGN KEY (`a_la_carte_id`) REFERENCES `a_la_carte_menu` (`a_la_carte_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `aLaCartePuchasedByOrder` FOREIGN KEY (`order_id`) REFERENCES `restaurant_order` (`restaurant_order_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `purchased_drinks`
--
ALTER TABLE `purchased_drinks`
  ADD CONSTRAINT `DrinkPurchasedByDrinkID` FOREIGN KEY (`drink_id`) REFERENCES `drinks` (`drink_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `DrinkPurchasedByOrderID` FOREIGN KEY (`order_id`) REFERENCES `restaurant_order` (`restaurant_order_id`) ON DELETE CASCADE ON UPDATE CASCADE;

DELIMITER $$
--
-- Events
--
DROP EVENT IF EXISTS `reset_bookings_daily`$$
CREATE DEFINER=`root`@`localhost` EVENT `reset_bookings_daily` ON SCHEDULE EVERY 1 DAY STARTS '2024-03-06 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE tables  SET status = "Free"$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
