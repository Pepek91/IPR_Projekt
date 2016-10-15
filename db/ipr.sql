-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 25, 2014 at 03:49 PM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ipr`
--
CREATE DATABASE IF NOT EXISTS `ipr` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `ipr`;

-- --------------------------------------------------------

--
-- Table structure for table `bledy_sedziego`
--

CREATE TABLE IF NOT EXISTS `bledy_sedziego` (
  `id_bledu` int(11) NOT NULL AUTO_INCREMENT,
  `typ_bledu` int(11) NOT NULL,
  `ocena` tinyint(4) NOT NULL,
  PRIMARY KEY (`id_bledu`),
  KEY `typ_bled_idx` (`typ_bledu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `druzyny`
--

CREATE TABLE IF NOT EXISTS `druzyny` (
  `id_druzyny` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(100) NOT NULL,
  `trener` varchar(100) NOT NULL,
  PRIMARY KEY (`id_druzyny`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `druzyny`
--

INSERT INTO `druzyny` (`id_druzyny`, `nazwa`, `trener`) VALUES
(1, 'Korona Kielce', 'Jan Kowalski'),
(2, 'Wisła Kraków', 'Waldemar Świądek'),
(3, 'Motor Lublin', 'John Smith');

-- --------------------------------------------------------

--
-- Table structure for table `kartki`
--

CREATE TABLE IF NOT EXISTS `kartki` (
  `id_kartki` int(11) NOT NULL AUTO_INCREMENT,
  `rodzaj` enum('czerwona','żółta') NOT NULL,
  `minuta` smallint(6) NOT NULL,
  PRIMARY KEY (`id_kartki`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `klienci`
--

CREATE TABLE IF NOT EXISTS `klienci` (
  `id_klienta` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `haslo` varchar(100) NOT NULL,
  `imie` varchar(100) NOT NULL,
  `nazwisko` varchar(100) NOT NULL,
  `data_urodzenia` date NOT NULL,
  `adres_miasto` varchar(100) DEFAULT NULL,
  `adres_ulica` varchar(100) DEFAULT NULL,
  `adres_kod` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_klienta`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `klienci`
--

INSERT INTO `klienci` (`id_klienta`, `login`, `haslo`, `imie`, `nazwisko`, `data_urodzenia`, `adres_miasto`, `adres_ulica`, `adres_kod`) VALUES
(5, 'michal', '827ccb0eea8a706c4c34a16891f84e7b', 'Michał', 'Stępień', '1991-08-30', '', '', ''),
(12, 'krzysztof', '827ccb0eea8a706c4c34a16891f84e7b', 'Krzysztof', 'Górecki', '1987-01-01', 'asdfasd', 'asdfasd', 'asdfsadf');

-- --------------------------------------------------------

--
-- Table structure for table `kontrakty`
--

CREATE TABLE IF NOT EXISTS `kontrakty` (
  `id_kontraktu` int(11) NOT NULL AUTO_INCREMENT,
  `id_zawodnika` int(11) NOT NULL,
  `id_druzyny` int(11) NOT NULL,
  `okres` date DEFAULT NULL,
  PRIMARY KEY (`id_kontraktu`),
  KEY `id_zawodnika_kontrakty_idx` (`id_zawodnika`),
  KEY `id_druzyny_kontrakty_idx` (`id_druzyny`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `kontrakty`
--

INSERT INTO `kontrakty` (`id_kontraktu`, `id_zawodnika`, `id_druzyny`, `okres`) VALUES
(1, 1, 1, NULL),
(2, 2, 2, NULL),
(3, 3, 1, NULL),
(4, 4, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `kontuzje`
--

CREATE TABLE IF NOT EXISTS `kontuzje` (
  `id_kontuzji` int(11) NOT NULL AUTO_INCREMENT,
  `id_zawodnika` int(11) NOT NULL,
  `opis` varchar(100) DEFAULT NULL,
  `przewidywany_okres` smallint(6) DEFAULT NULL COMMENT 'dni',
  PRIMARY KEY (`id_kontuzji`),
  KEY `id_zawodnika_kontuzje_idx` (`id_zawodnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mecze`
--

CREATE TABLE IF NOT EXISTS `mecze` (
  `id_meczu` int(11) NOT NULL AUTO_INCREMENT,
  `id_gospodarzy` int(11) NOT NULL,
  `id_gosci` int(11) NOT NULL,
  `id_sedziego` int(11) DEFAULT NULL,
  `data` date NOT NULL,
  `wynik` varchar(7) DEFAULT NULL,
  `posiadanie_pilki` varchar(5) DEFAULT NULL,
  `frekwencja` int(11) DEFAULT NULL,
  `liczba_miejsc` int(11) DEFAULT NULL,
  `cytowania` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_meczu`),
  KEY `id_gospodarzy_idx` (`id_gospodarzy`),
  KEY `id_sedziego_idx` (`id_sedziego`),
  KEY `id_gosci_idx` (`id_gosci`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `mecze`
--

INSERT INTO `mecze` (`id_meczu`, `id_gospodarzy`, `id_gosci`, `id_sedziego`, `data`, `wynik`, `posiadanie_pilki`, `frekwencja`, `liczba_miejsc`, `cytowania`) VALUES
(1, 1, 2, 1, '2014-01-17', '3:1', '50:50', 100000, 1000000, 'cytat 1; cytat 2; cytat 3'),
(2, 3, 1, 2, '2014-01-12', '2:2', '46:54', 1234, 1234, NULL),
(3, 1, 2, 1, '2014-02-12', NULL, NULL, NULL, NULL, NULL),
(4, 3, 2, NULL, '2014-02-18', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mecze_bledy`
--

CREATE TABLE IF NOT EXISTS `mecze_bledy` (
  `id_meczu` int(11) NOT NULL,
  `id_bledu` int(11) NOT NULL,
  PRIMARY KEY (`id_meczu`,`id_bledu`),
  KEY `id_bledu_idx` (`id_bledu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `mecze_zaklady`
--

CREATE TABLE IF NOT EXISTS `mecze_zaklady` (
  `id_zakladu` int(11) NOT NULL,
  `id_meczu` int(11) NOT NULL,
  `obstawiony_wynik` varchar(7) NOT NULL,
  `kurs` float NOT NULL,
  PRIMARY KEY (`id_zakladu`,`id_meczu`),
  KEY `id_meczu_zaklady_idx` (`id_meczu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mecze_zaklady`
--

INSERT INTO `mecze_zaklady` (`id_zakladu`, `id_meczu`, `obstawiony_wynik`, `kurs`) VALUES
(1, 3, '1:1', 0),
(2, 3, '1:1', 0),
(3, 3, '1:1', 0),
(3, 4, '2:2', 0),
(4, 4, '1:1', 0);

-- --------------------------------------------------------

--
-- Table structure for table `mecze_zawodnicy_faule`
--

CREATE TABLE IF NOT EXISTS `mecze_zawodnicy_faule` (
  `id_faulu` int(11) NOT NULL,
  `id_meczu` int(11) NOT NULL,
  `id_faulującego` int(11) NOT NULL,
  `id_faulowanego` int(11) NOT NULL,
  `id_kartki` int(11) DEFAULT NULL,
  `minuta` smallint(6) NOT NULL,
  PRIMARY KEY (`id_faulu`),
  KEY `id_meczu_faule_idx` (`id_meczu`),
  KEY `id_faulującego_faule_idx` (`id_faulującego`),
  KEY `id_faulowanego_faule_idx` (`id_faulowanego`),
  KEY `id_kartki_faule_idx` (`id_kartki`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `mecze_zawodnicy_kartki`
--

CREATE TABLE IF NOT EXISTS `mecze_zawodnicy_kartki` (
  `id_meczu` int(11) NOT NULL,
  `id_zawodnik` int(11) NOT NULL,
  `id_kartki` int(11) NOT NULL,
  PRIMARY KEY (`id_meczu`,`id_zawodnik`,`id_kartki`),
  KEY `id_zawodnik_kartki_idx` (`id_zawodnik`),
  KEY `id_kartki_idx` (`id_kartki`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `mecze_zawodnicy_zagrania`
--

CREATE TABLE IF NOT EXISTS `mecze_zawodnicy_zagrania` (
  `id_meczu` int(11) NOT NULL,
  `id_zawodnika` int(11) NOT NULL,
  `id_zagrania` int(11) NOT NULL,
  PRIMARY KEY (`id_meczu`,`id_zawodnika`,`id_zagrania`),
  KEY `id_zagrania_idx` (`id_zagrania`),
  KEY `id_zawodnika_zagrania_idx` (`id_zawodnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `mecze_zawodnicy_zmiany`
--

CREATE TABLE IF NOT EXISTS `mecze_zawodnicy_zmiany` (
  `id_meczu` int(11) NOT NULL,
  `id_zawodnik_in` int(11) NOT NULL,
  `id_zawodnik_out` int(11) NOT NULL,
  `minuta` smallint(6) NOT NULL,
  PRIMARY KEY (`id_meczu`,`id_zawodnik_in`,`id_zawodnik_out`),
  KEY `id_zawodnik_in_zmiany_idx` (`id_zawodnik_in`),
  KEY `id_zawodnik_out_zmiany_idx` (`id_zawodnik_out`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `mecze_zwodnicy_udzial`
--

CREATE TABLE IF NOT EXISTS `mecze_zwodnicy_udzial` (
  `id_meczu` int(11) NOT NULL,
  `id_zawodnika` int(11) NOT NULL,
  `podstawowy_sklad` tinyint(4) DEFAULT NULL,
  `czas_gry` smallint(6) DEFAULT NULL,
  `dystans` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_meczu`,`id_zawodnika`),
  KEY `id_zawodnika_udzial_idx` (`id_zawodnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mecze_zwodnicy_udzial`
--

INSERT INTO `mecze_zwodnicy_udzial` (`id_meczu`, `id_zawodnika`, `podstawowy_sklad`, `czas_gry`, `dystans`) VALUES
(1, 1, NULL, NULL, NULL),
(1, 2, NULL, NULL, NULL),
(1, 3, NULL, NULL, NULL),
(1, 4, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pracownicy`
--

CREATE TABLE IF NOT EXISTS `pracownicy` (
  `id_pracownika` int(11) NOT NULL AUTO_INCREMENT,
  `poziom_dostepu` tinyint(4) NOT NULL,
  `login` varchar(100) NOT NULL,
  `haslo` varchar(100) NOT NULL,
  `imie` varchar(100) NOT NULL,
  `nazwisko` varchar(100) NOT NULL,
  `data_urodzenia` date DEFAULT NULL,
  `adres_miasto` varchar(100) DEFAULT NULL,
  `adres_ulica` varchar(100) DEFAULT NULL,
  `adres_kod` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_pracownika`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `pracownicy`
--

INSERT INTO `pracownicy` (`id_pracownika`, `poziom_dostepu`, `login`, `haslo`, `imie`, `nazwisko`, `data_urodzenia`, `adres_miasto`, `adres_ulica`, `adres_kod`) VALUES
(1, 1, 'pracownik', '81dc9bdb52d04dc20036dbd8313ed055', 'Michał', 'Stępień', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `slownik_bledow`
--

CREATE TABLE IF NOT EXISTS `slownik_bledow` (
  `typ_bledu` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(100) NOT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`typ_bledu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `slownik_pozycji`
--

CREATE TABLE IF NOT EXISTS `slownik_pozycji` (
  `typ_pozycji` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(100) NOT NULL,
  PRIMARY KEY (`typ_pozycji`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `slownik_pozycji`
--

INSERT INTO `slownik_pozycji` (`typ_pozycji`, `nazwa`) VALUES
(1, 'Atak'),
(2, 'Obrona'),
(3, 'Pomocnik'),
(4, 'Bramkarz');

-- --------------------------------------------------------

--
-- Table structure for table `slownik_zagran`
--

CREATE TABLE IF NOT EXISTS `slownik_zagran` (
  `typ_zagrania` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(100) NOT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`typ_zagrania`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `slownik_zagran`
--

INSERT INTO `slownik_zagran` (`typ_zagrania`, `nazwa`, `opis`) VALUES
(2, 'Rzut rożny', NULL),
(3, 'Aut', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `stacje`
--

CREATE TABLE IF NOT EXISTS `stacje` (
  `id_stacji` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(100) NOT NULL,
  PRIMARY KEY (`id_stacji`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `stacje`
--

INSERT INTO `stacje` (`id_stacji`, `nazwa`) VALUES
(1, 'Polsat'),
(2, 'TVP 1');

-- --------------------------------------------------------

--
-- Table structure for table `sędziowie`
--

CREATE TABLE IF NOT EXISTS `sędziowie` (
  `id_sedziego` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(100) NOT NULL,
  `nazwisko` varchar(100) NOT NULL,
  PRIMARY KEY (`id_sedziego`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `sędziowie`
--

INSERT INTO `sędziowie` (`id_sedziego`, `imie`, `nazwisko`) VALUES
(1, 'Heniek', 'Wiernacki'),
(2, 'Janusz', 'Barszcz');

-- --------------------------------------------------------

--
-- Table structure for table `transmisje`
--

CREATE TABLE IF NOT EXISTS `transmisje` (
  `id_stacji` int(11) NOT NULL,
  `id_meczu` int(11) NOT NULL,
  `ogladalnosc` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_stacji`,`id_meczu`),
  KEY `id_meczu_transmisje_idx` (`id_meczu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `zagrania`
--

CREATE TABLE IF NOT EXISTS `zagrania` (
  `id_zagrania` int(11) NOT NULL AUTO_INCREMENT,
  `typ_zagrania` int(11) NOT NULL,
  `minuta` smallint(6) NOT NULL,
  PRIMARY KEY (`id_zagrania`),
  KEY `typ_zagrania_idx` (`typ_zagrania`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `zaklady`
--

CREATE TABLE IF NOT EXISTS `zaklady` (
  `id_zakladu` int(11) NOT NULL AUTO_INCREMENT,
  `id_klienta` int(11) NOT NULL,
  `stawka` float NOT NULL,
  `kurs_zakladu` float NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`id_zakladu`),
  KEY `id_klienta_idx` (`id_klienta`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `zaklady`
--

INSERT INTO `zaklady` (`id_zakladu`, `id_klienta`, `stawka`, `kurs_zakladu`, `data`) VALUES
(1, 5, 12, 1, '2014-01-22'),
(2, 5, 12, 1, '2014-01-22'),
(3, 5, 1, 1, '2014-01-25'),
(4, 5, 33, 1, '2014-01-25');

-- --------------------------------------------------------

--
-- Table structure for table `zawieszenia`
--

CREATE TABLE IF NOT EXISTS `zawieszenia` (
  `id_zawieszenia` int(11) NOT NULL AUTO_INCREMENT,
  `id_zawodnika` int(11) NOT NULL,
  `okres` smallint(6) NOT NULL,
  PRIMARY KEY (`id_zawieszenia`),
  KEY `id_zawodnika_zawieszenia_idx` (`id_zawodnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `zawodnicy`
--

CREATE TABLE IF NOT EXISTS `zawodnicy` (
  `id_zawodnika` int(11) NOT NULL AUTO_INCREMENT,
  `typ_pozycji` int(11) NOT NULL,
  `imie` varchar(100) NOT NULL,
  `nazwisko` varchar(100) NOT NULL,
  PRIMARY KEY (`id_zawodnika`),
  KEY `typ_pozycji_idx` (`typ_pozycji`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=45 ;

--
-- Dumping data for table `zawodnicy`
--

INSERT INTO `zawodnicy` (`id_zawodnika`, `typ_pozycji`, `imie`, `nazwisko`) VALUES
(1, 1, 'Jan', 'Biernacki'),
(2, 1, 'Michał', 'Wiśniewski'),
(3, 2, 'Jan', 'Wieniecki'),
(4, 1, 'Michał', 'Wiśniewski'),
(5, 2, 'Jan', 'Wieniecki'),
(6, 3, 'Adam', 'Adamowicz'),
(7, 4, 'Bartosz', 'Bartosiński'),
(8, 1, 'Dominik', 'Dubiel'),
(9, 2, 'Maciej', 'Matematyczny'),
(10, 3, 'Michał', 'Całkowicz'),
(11, 4, 'Marek', 'Markowski'),
(12, 1, 'Krystian', 'Mołdoch'),
(13, 2, 'Kordian', 'Sobański'),
(14, 3, 'Rafał', 'Kaczorowski'),
(15, 4, 'Piotr', 'Miśkiewicz'),
(16, 1, 'Paweł', 'Jakubowski'),
(17, 2, 'Jakub', 'Weber'),
(18, 3, 'Jarosław', 'Piórkowski'),
(19, 4, 'Radosław', 'Nadstawny'),
(20, 1, 'Łukasz', 'Dziedzic'),
(21, 2, 'Włodzimierz', 'Chrobry'),
(22, 3, 'Artur', 'Wasil'),
(23, 4, 'Konrad', 'Pląska'),
(24, 1, 'Darek', 'Majewski'),
(25, 2, 'Kamil', 'Czuma'),
(26, 3, 'Marcin', 'Wierzyński'),
(27, 4, 'Jacek', 'Bartoszewski'),
(28, 1, 'Robert', 'Szewczyk'),
(29, 2, 'Damian', 'Guział'),
(30, 3, 'Tomasz', 'Tomaszewski'),
(31, 4, 'Daniel', 'Stasiak'),
(32, 1, 'Arkadiusz', 'Lenarcik'),
(33, 2, 'Maksymilian', 'Jaguś'),
(34, 3, 'Mateusz', 'Waśniewski'),
(35, 4, 'Wojtek', 'Gorzkiewicz'),
(36, 1, 'Stanisław', 'Toporowski'),
(37, 2, 'Karol', 'Mikulski'),
(38, 3, 'Adrian', 'Pilarski'),
(39, 4, 'Andrzej', 'Chmiel'),
(40, 1, 'Janusz', 'Listkiewicz'),
(41, 2, 'Grzegorz', 'Szustkiewicz'),
(42, 3, 'Franciszek', 'Tkaczyński'),
(43, 4, 'Kajetan', 'Dąbrowski'),
(44, 2, 'Filip', 'Motrenko');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bledy_sedziego`
--
ALTER TABLE `bledy_sedziego`
  ADD CONSTRAINT `typ_bled` FOREIGN KEY (`typ_bledu`) REFERENCES `slownik_bledow` (`typ_bledu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `kontrakty`
--
ALTER TABLE `kontrakty`
  ADD CONSTRAINT `id_druzyny_kontrakty` FOREIGN KEY (`id_druzyny`) REFERENCES `druzyny` (`id_druzyny`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_zawodnika_kontrakty` FOREIGN KEY (`id_zawodnika`) REFERENCES `zawodnicy` (`id_zawodnika`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `kontuzje`
--
ALTER TABLE `kontuzje`
  ADD CONSTRAINT `id_zawodnika_kontuzje` FOREIGN KEY (`id_zawodnika`) REFERENCES `zawodnicy` (`id_zawodnika`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mecze`
--
ALTER TABLE `mecze`
  ADD CONSTRAINT `id_gosci` FOREIGN KEY (`id_gosci`) REFERENCES `druzyny` (`id_druzyny`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_gospodarzy` FOREIGN KEY (`id_gospodarzy`) REFERENCES `druzyny` (`id_druzyny`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_sedziego` FOREIGN KEY (`id_sedziego`) REFERENCES `sędziowie` (`id_sedziego`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mecze_bledy`
--
ALTER TABLE `mecze_bledy`
  ADD CONSTRAINT `id_bledu` FOREIGN KEY (`id_bledu`) REFERENCES `bledy_sedziego` (`id_bledu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_meczu_bledy` FOREIGN KEY (`id_meczu`) REFERENCES `mecze` (`id_meczu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mecze_zaklady`
--
ALTER TABLE `mecze_zaklady`
  ADD CONSTRAINT `id_meczu_zaklady` FOREIGN KEY (`id_meczu`) REFERENCES `mecze` (`id_meczu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_zakladu` FOREIGN KEY (`id_zakladu`) REFERENCES `zaklady` (`id_zakladu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mecze_zawodnicy_faule`
--
ALTER TABLE `mecze_zawodnicy_faule`
  ADD CONSTRAINT `id_faulowanego_faule` FOREIGN KEY (`id_faulowanego`) REFERENCES `zawodnicy` (`id_zawodnika`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_faulującego_faule` FOREIGN KEY (`id_faulującego`) REFERENCES `zawodnicy` (`id_zawodnika`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_kartki_faule` FOREIGN KEY (`id_kartki`) REFERENCES `kartki` (`id_kartki`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_meczu_faule` FOREIGN KEY (`id_meczu`) REFERENCES `mecze` (`id_meczu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mecze_zawodnicy_kartki`
--
ALTER TABLE `mecze_zawodnicy_kartki`
  ADD CONSTRAINT `id_kartki` FOREIGN KEY (`id_kartki`) REFERENCES `kartki` (`id_kartki`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_meczu_kartki` FOREIGN KEY (`id_meczu`) REFERENCES `mecze` (`id_meczu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_zawodnik_kartki` FOREIGN KEY (`id_zawodnik`) REFERENCES `zawodnicy` (`id_zawodnika`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mecze_zawodnicy_zagrania`
--
ALTER TABLE `mecze_zawodnicy_zagrania`
  ADD CONSTRAINT `id_meczu_zagrania` FOREIGN KEY (`id_meczu`) REFERENCES `mecze` (`id_meczu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_zagrania` FOREIGN KEY (`id_zagrania`) REFERENCES `zagrania` (`id_zagrania`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_zawodnika_zagrania` FOREIGN KEY (`id_zawodnika`) REFERENCES `zawodnicy` (`id_zawodnika`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mecze_zawodnicy_zmiany`
--
ALTER TABLE `mecze_zawodnicy_zmiany`
  ADD CONSTRAINT `id_meczu_zmiany` FOREIGN KEY (`id_meczu`) REFERENCES `mecze` (`id_meczu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_zawodnik_in_zmiany` FOREIGN KEY (`id_zawodnik_in`) REFERENCES `zawodnicy` (`id_zawodnika`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_zawodnik_out_zmiany` FOREIGN KEY (`id_zawodnik_out`) REFERENCES `zawodnicy` (`id_zawodnika`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mecze_zwodnicy_udzial`
--
ALTER TABLE `mecze_zwodnicy_udzial`
  ADD CONSTRAINT `id_meczu_udzial` FOREIGN KEY (`id_meczu`) REFERENCES `mecze` (`id_meczu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_zawodnika_udzial` FOREIGN KEY (`id_zawodnika`) REFERENCES `zawodnicy` (`id_zawodnika`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `transmisje`
--
ALTER TABLE `transmisje`
  ADD CONSTRAINT `id_meczu_transmisje` FOREIGN KEY (`id_meczu`) REFERENCES `mecze` (`id_meczu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_stacji` FOREIGN KEY (`id_stacji`) REFERENCES `stacje` (`id_stacji`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `zagrania`
--
ALTER TABLE `zagrania`
  ADD CONSTRAINT `typ_zagrania` FOREIGN KEY (`typ_zagrania`) REFERENCES `slownik_zagran` (`typ_zagrania`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `zaklady`
--
ALTER TABLE `zaklady`
  ADD CONSTRAINT `id_klienta` FOREIGN KEY (`id_klienta`) REFERENCES `klienci` (`id_klienta`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `zawieszenia`
--
ALTER TABLE `zawieszenia`
  ADD CONSTRAINT `id_zawodnika_zawieszenia` FOREIGN KEY (`id_zawodnika`) REFERENCES `zawodnicy` (`id_zawodnika`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `zawodnicy`
--
ALTER TABLE `zawodnicy`
  ADD CONSTRAINT `typ_pozycji` FOREIGN KEY (`typ_pozycji`) REFERENCES `slownik_pozycji` (`typ_pozycji`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
