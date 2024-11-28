-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: mysql
-- Generation Time: Sty 20, 2024 at 11:21 PM
-- Wersja serwera: 8.0.31
-- Wersja PHP: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel`
--

DELIMITER $$
--
-- Procedury
--
CREATE DEFINER=`root`@`%` PROCEDURE `insert_additional_equipment` (IN `name` VARCHAR(255), IN `price_increase` FLOAT, IN `type` VARCHAR(255))   BEGIN
    DECLARE newest_id INT;

    SELECT MAX(id) INTO newest_id FROM AdditionalEquipment;
    IF newest_id IS NULL THEN
        SET newest_id = 1;
    ELSE
        SET newest_id = newest_id + 1;
    END IF;

    INSERT INTO AdditionalEquipment VALUES (newest_id, name);
    INSERT INTO AdditionalEquipmentPricingIncrease VALUES (newest_id, price_increase, type);
END$$

CREATE DEFINER=`root`@`%` PROCEDURE `insert_equipment` (IN `name` VARCHAR(255), IN `price_increase` FLOAT, IN `type` VARCHAR(255))   BEGIN
    DECLARE newest_id INT;

    SELECT MAX(id) INTO newest_id FROM Equipment;
    IF newest_id IS NULL THEN
        SET newest_id = 1;
    ELSE
        SET newest_id = newest_id + 1;
    END IF;

    INSERT INTO Equipment VALUES (newest_id, name);
    INSERT INTO EquipmentPricingIncrease VALUES (newest_id, price_increase, type);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `AdditionalEquipment`
--

CREATE TABLE `AdditionalEquipment` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb3_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

--
-- Dumping data for table `AdditionalEquipment`
--

INSERT INTO `AdditionalEquipment` (`id`, `name`) VALUES
(1, 'Telewizor'),
(2, 'Dodatkowe łóżko'),
(3, 'Czajnik'),
(4, 'Suszarka'),
(5, 'Mydło'),
(6, 'Klucz do pokoju'),
(7, 'Ręcznik'),
(8, 'Ręcznik');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `AdditionalEquipmentForReservation`
--

CREATE TABLE `AdditionalEquipmentForReservation` (
  `id` int NOT NULL,
  `additional_equipment_id` int NOT NULL,
  `reservation_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `AdditionalEquipmentPricingIncrease`
--

CREATE TABLE `AdditionalEquipmentPricingIncrease` (
  `additional_equipment_id` int NOT NULL,
  `increase` float NOT NULL,
  `type` varchar(255) COLLATE utf8mb3_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

--
-- Dumping data for table `AdditionalEquipmentPricingIncrease`
--

INSERT INTO `AdditionalEquipmentPricingIncrease` (`additional_equipment_id`, `increase`, `type`) VALUES
(1, 12, 'percent'),
(2, 7, 'percent'),
(3, 2, 'percent'),
(4, 4, 'percent'),
(5, 15, 'percent'),
(6, 1, 'percent'),
(7, 2, 'percent'),
(8, 2, 'percent');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Client`
--

CREATE TABLE `Client` (
  `id` int NOT NULL,
  `email` varchar(255) COLLATE utf8mb3_polish_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb3_polish_ci NOT NULL,
  `verified` tinyint(1) NOT NULL,
  `disabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

--
-- Dumping data for table `Client`
--

INSERT INTO `Client` (`id`, `email`, `password`, `verified`, `disabled`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, 0),
(10, 'test', '098f6bcd4621d373cade4e832627b4f6', 1, 0),
(11, 'test2', 'ad0234829205b9033196ba818f7a872b', 1, 0),
(12, 'l', '83878c91171338902e0fe0fb97a8c47a', 1, 0),
(17, 'laledobre', '4fc819340604f7c85a412b358de5f0f9', 1, 0),
(18, 'kuba', 'fccbce33643556ee698db7d599853a1f', 1, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Discount`
--

CREATE TABLE `Discount` (
  `id` int NOT NULL,
  `decrease` float NOT NULL,
  `discountType` varchar(255) COLLATE utf8mb3_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Equipment`
--

CREATE TABLE `Equipment` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb3_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

--
-- Dumping data for table `Equipment`
--

INSERT INTO `Equipment` (`id`, `name`) VALUES
(1, 'Dwa łóżka'),
(2, 'Łóżko wodne'),
(3, 'Telewizor plazmowy'),
(4, 'Widok na ściane'),
(5, 'Lodówka'),
(6, 'Zamrażarka'),
(7, 'Radio');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `EquipmentInRoom`
--

CREATE TABLE `EquipmentInRoom` (
  `id` int NOT NULL,
  `equipment_id` int NOT NULL,
  `room_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

--
-- Dumping data for table `EquipmentInRoom`
--

INSERT INTO `EquipmentInRoom` (`id`, `equipment_id`, `room_id`) VALUES
(23, 2, 3),
(24, 4, 4),
(25, 6, 4),
(26, 5, 4),
(27, 3, 4),
(28, 5, 5),
(29, 3, 5),
(30, 1, 5),
(31, 2, 2),
(32, 3, 2),
(33, 1, 1),
(34, 6, 1),
(35, 2, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `EquipmentPricingIncrease`
--

CREATE TABLE `EquipmentPricingIncrease` (
  `equipment_id` int NOT NULL,
  `increase` float NOT NULL,
  `type` varchar(255) COLLATE utf8mb3_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

--
-- Dumping data for table `EquipmentPricingIncrease`
--

INSERT INTO `EquipmentPricingIncrease` (`equipment_id`, `increase`, `type`) VALUES
(1, 7, 'percent'),
(2, 20, 'percent'),
(3, 5, 'percent'),
(4, 1, 'percent'),
(5, 2, 'percent'),
(6, 3, 'percent'),
(7, 5, 'percent');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Reservation`
--

CREATE TABLE `Reservation` (
  `id` int NOT NULL,
  `client_id` int NOT NULL,
  `room_id` int NOT NULL,
  `checkin_date` timestamp NOT NULL,
  `checkout_date` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

--
-- Dumping data for table `Reservation`
--

INSERT INTO `Reservation` (`id`, `client_id`, `room_id`, `checkin_date`, `checkout_date`) VALUES
(3, 11, 2, '2024-01-20 00:00:00', '2024-01-27 00:00:00'),
(10, 10, 5, '2024-01-12 00:00:00', '2024-01-26 00:00:00'),
(11, 10, 2, '2024-01-27 00:00:00', '2024-01-28 00:00:00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ReservationPricing`
--

CREATE TABLE `ReservationPricing` (
  `reservation_id` int NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

--
-- Dumping data for table `ReservationPricing`
--

INSERT INTO `ReservationPricing` (`reservation_id`, `price`) VALUES
(3, 3450),
(10, 3633.54),
(11, 504);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Room`
--

CREATE TABLE `Room` (
  `id` int NOT NULL,
  `roomSize` int NOT NULL,
  `basePrice` float NOT NULL,
  `capacity` int NOT NULL,
  `roomNumber` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb3_polish_ci DEFAULT '',
  `description` text COLLATE utf8mb3_polish_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

--
-- Dumping data for table `Room`
--

INSERT INTO `Room` (`id`, `roomSize`, `basePrice`, `capacity`, `roomNumber`, `name`, `description`) VALUES
(1, 10, 100.99, 3, 1, 'Apartament dla 3 osób', 'Wspaniała okazja! Apartament dla 3 osób tylko dzisiaj we wspaniałej cenie!'),
(2, 100, 400, 2, 2, 'Pokój dla dwojga', 'Pokój dla dwóch osób z widokiem na ceglaną ściane.'),
(3, 50, 1000.5, 29, 3, 'Apartament ULTRA dla 29 osób!', 'Idealny na wieczorne wieczory z ziomeczkami przy grillu - WIELOMA ZIOMECZKAMI'),
(4, 10, 150.69, 10, 4, 'Pokój czteroosobowy', 'Były wszystkie musi byc i czteroosobowy!'),
(5, 10, 200.11, 5, 5, 'Pokój pięcioosobowy', 'Idealny na wieczorne wieczory z ziomeczkami przy grillu.');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `RoomBlockade`
--

CREATE TABLE `RoomBlockade` (
  `id` int NOT NULL,
  `room_id` int NOT NULL,
  `date_from` timestamp NOT NULL,
  `date_to` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `RoomDiscount`
--

CREATE TABLE `RoomDiscount` (
  `room_id` int NOT NULL,
  `discount_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_polish_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `AdditionalEquipment`
--
ALTER TABLE `AdditionalEquipment`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `AdditionalEquipmentForReservation`
--
ALTER TABLE `AdditionalEquipmentForReservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `additional_equipment_id` (`additional_equipment_id`),
  ADD KEY `reservation_id` (`reservation_id`);

--
-- Indeksy dla tabeli `AdditionalEquipmentPricingIncrease`
--
ALTER TABLE `AdditionalEquipmentPricingIncrease`
  ADD PRIMARY KEY (`additional_equipment_id`);

--
-- Indeksy dla tabeli `Client`
--
ALTER TABLE `Client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indeksy dla tabeli `Discount`
--
ALTER TABLE `Discount`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `Equipment`
--
ALTER TABLE `Equipment`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `EquipmentInRoom`
--
ALTER TABLE `EquipmentInRoom`
  ADD PRIMARY KEY (`id`),
  ADD KEY `equipment_id` (`equipment_id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indeksy dla tabeli `EquipmentPricingIncrease`
--
ALTER TABLE `EquipmentPricingIncrease`
  ADD PRIMARY KEY (`equipment_id`);

--
-- Indeksy dla tabeli `Reservation`
--
ALTER TABLE `Reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indeksy dla tabeli `ReservationPricing`
--
ALTER TABLE `ReservationPricing`
  ADD PRIMARY KEY (`reservation_id`);

--
-- Indeksy dla tabeli `Room`
--
ALTER TABLE `Room`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `RoomBlockade`
--
ALTER TABLE `RoomBlockade`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indeksy dla tabeli `RoomDiscount`
--
ALTER TABLE `RoomDiscount`
  ADD KEY `room_id` (`room_id`),
  ADD KEY `discount_id` (`discount_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `AdditionalEquipment`
--
ALTER TABLE `AdditionalEquipment`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `AdditionalEquipmentForReservation`
--
ALTER TABLE `AdditionalEquipmentForReservation`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `AdditionalEquipmentPricingIncrease`
--
ALTER TABLE `AdditionalEquipmentPricingIncrease`
  MODIFY `additional_equipment_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `Client`
--
ALTER TABLE `Client`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `Discount`
--
ALTER TABLE `Discount`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Equipment`
--
ALTER TABLE `Equipment`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `EquipmentInRoom`
--
ALTER TABLE `EquipmentInRoom`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `Reservation`
--
ALTER TABLE `Reservation`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `ReservationPricing`
--
ALTER TABLE `ReservationPricing`
  MODIFY `reservation_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `Room`
--
ALTER TABLE `Room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `RoomBlockade`
--
ALTER TABLE `RoomBlockade`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `AdditionalEquipmentForReservation`
--
ALTER TABLE `AdditionalEquipmentForReservation`
  ADD CONSTRAINT `AdditionalEquipmentForReservation_ibfk_1` FOREIGN KEY (`additional_equipment_id`) REFERENCES `AdditionalEquipment` (`id`),
  ADD CONSTRAINT `AdditionalEquipmentForReservation_ibfk_2` FOREIGN KEY (`reservation_id`) REFERENCES `Reservation` (`id`);

--
-- Constraints for table `EquipmentInRoom`
--
ALTER TABLE `EquipmentInRoom`
  ADD CONSTRAINT `EquipmentInRoom_ibfk_1` FOREIGN KEY (`equipment_id`) REFERENCES `Equipment` (`id`),
  ADD CONSTRAINT `EquipmentInRoom_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `Room` (`id`);

--
-- Constraints for table `Reservation`
--
ALTER TABLE `Reservation`
  ADD CONSTRAINT `Reservation_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `Client` (`id`),
  ADD CONSTRAINT `Reservation_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `Room` (`id`);

--
-- Constraints for table `RoomBlockade`
--
ALTER TABLE `RoomBlockade`
  ADD CONSTRAINT `RoomBlockade_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `Room` (`id`);

--
-- Constraints for table `RoomDiscount`
--
ALTER TABLE `RoomDiscount`
  ADD CONSTRAINT `RoomDiscount_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `Room` (`id`),
  ADD CONSTRAINT `RoomDiscount_ibfk_2` FOREIGN KEY (`discount_id`) REFERENCES `Discount` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
