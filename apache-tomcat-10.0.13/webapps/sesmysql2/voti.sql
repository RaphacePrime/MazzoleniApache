-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Feb 12, 2022 alle 09:44
-- Versione del server: 10.4.17-MariaDB
-- Versione PHP: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ginopino`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `voti`
--

CREATE TABLE `voti` (
  `username` varchar(20) NOT NULL,
  `materia` varchar(30) NOT NULL,
  `voto` float NOT NULL,
  `data` date NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `voti`
--

INSERT INTO `voti` (`username`, `materia`, `voto`, `data`, `id`) VALUES
('RaphacePrime', 'tps', 10, '2022-02-12', 1),
('Luzzi03', 'tps', 10, '2022-02-12', 2),
('RaphacePrime', 'informatica', 9, '2022-02-06', 3),
('Luzzi03', 'informatica', 6, '2022-02-06', 4),
('RaphacePrime', 'matematica', 9, '2022-01-20', 5),
('Luzzi03', 'matematica', 6, '2022-01-24', 6),
('Grata', 'italiano', 6, '2022-01-27', 7),
('Grata', 'matematica', 9, '2022-01-28', 8),
('Luzzi03', 'italiano', 6, '2022-02-15', 9),
('RaphacePrime', 'italiano', 8.5, '2022-01-10', 10),
('Grata', 'matematica', 6, '2022-02-07', 11),
('Grata', 'tps', 6, '2022-02-06', 12);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `voti`
--
ALTER TABLE `voti`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
