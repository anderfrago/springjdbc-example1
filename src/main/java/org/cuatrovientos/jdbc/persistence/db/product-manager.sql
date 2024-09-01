CREATE DATABASE IF NOT EXISTS `productmanager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `productmanager`;

CREATE TABLE `category` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Components'),
(2, 'Networking'),
(3, 'Peripherals'),
(4, 'Services'),
(5, 'Software');
-- --------------------------------------------------------
CREATE TABLE `product` (
  `id` int(10) UNSIGNED NOT NULL,
  `reference` varchar(8) NOT NULL,
  `name` varchar(200) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `category` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `product` (`id`, `reference`, `name`, `price`, `category`) VALUES
(1, '4032', 'AMD A10-7850k', '145.97', 1),
(2, '4203', 'AMD Radeon R9 Fury X', '619.00', 1),
(3, '2009', 'ASUS FM2+ Motherboard', '65.95', 1),
(4, '9001', 'Montaje de ordenador', '40.00', 4),
(6, '1034', 'DVD-RW Drive', '23.75', 1),
(7, '3001', 'Epson XP-322 Multifuntion Printer WIFI', '55.65', 3);

ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `reference` (`reference`),
  ADD KEY `name` (`name`),
  ADD KEY `category` (`category`);

ALTER TABLE `category`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `product`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category`) REFERENCES `category` (`id`);
COMMIT;
