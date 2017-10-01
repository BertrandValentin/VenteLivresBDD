-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 17 Septembre 2017 à 13:23
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `ventelivresbdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `author`
--

CREATE TABLE IF NOT EXISTS `author` (
  `IdAuthor` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `Nationality` varchar(50) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdAuthor`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `author`
--

INSERT INTO `author` (`IdAuthor`, `FirstName`, `LastName`, `Birthday`, `Nationality`, `IsActive`) VALUES
(1, 'NIETZSCHE', 'Friedrich', '1844-10-15', 'prussien', 1),
(2, 'LACAN', 'Jacques', '1901-04-13', 'french', 1),
(3, 'DHAMMA', 'Rewata', '1924-12-04', 'burmese', 1),
(4, 'QUELQU', 'Un', '1999-11-11', 'none', 1);

-- --------------------------------------------------------

--
-- Structure de la table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `IdBook` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  `Author_IdAuthor` int(11) NOT NULL,
  `Category_IdCategory` int(11) NOT NULL,
  `Editor_IdEditor` int(11) NOT NULL,
  `Price` double DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdBook`),
  KEY `fk_Book_Author1_idx` (`Author_IdAuthor`),
  KEY `fk_Book_Editor1_idx` (`Editor_IdEditor`),
  KEY `fk_Book_Category1_idx` (`Category_IdCategory`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `book`
--

INSERT INTO `book` (`IdBook`, `title`, `Author_IdAuthor`, `Category_IdCategory`, `Editor_IdEditor`, `Price`, `IsActive`) VALUES
(1, 'Ecce homo', 1, 1, 1, 16, 1),
(2, 'Le mot d''esprit dans sa relation à  l''inconscient', 2, 1, 1, 8, 1),
(3, 'more human than human', 1, 1, 1, 24, 0),
(4, 'humain trop humain', 1, 1, 1, 16.8, 1),
(5, 'finaux de charpente', 2, 3, 1, 8.16, 1),
(7, 'ee', 2, 1, 3, 0.02, 0),
(8, 'Ainsi parlait Zarathoustra', 1, 1, 1, 16.16, 1),
(9, 'Le crépuscule des idoles', 1, 1, 1, 8.88, 1),
(10, 'L''antéchrist', 1, 1, 1, 6.66, 1),
(11, '0', 4, 2, 3, 0.01, 0);

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `IdCategory` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(50) NOT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdCategory`),
  UNIQUE KEY `CategoryName_UNIQUE` (`CategoryName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `category`
--

INSERT INTO `category` (`IdCategory`, `CategoryName`, `IsActive`) VALUES
(1, 'philosophy', 1),
(2, 'roman', 1),
(3, 'psychanalysis', 1);

-- --------------------------------------------------------

--
-- Structure de la table `editor`
--

CREATE TABLE IF NOT EXISTS `editor` (
  `IdEditor` int(11) NOT NULL AUTO_INCREMENT,
  `EditorName` varchar(50) NOT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdEditor`),
  UNIQUE KEY `EditorName_UNIQUE` (`EditorName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `editor`
--

INSERT INTO `editor` (`IdEditor`, `EditorName`, `IsActive`) VALUES
(1, 'philology and mind', 1),
(2, 'galbu', 1),
(3, 'KO', 1);

-- --------------------------------------------------------

--
-- Structure de la table `locality`
--

CREATE TABLE IF NOT EXISTS `locality` (
  `IdLocality` int(11) NOT NULL AUTO_INCREMENT,
  `ZipCode` varchar(50) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdLocality`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `locality`
--

INSERT INTO `locality` (`IdLocality`, `ZipCode`, `City`) VALUES
(1, '1348', 'Louvain-la-Neuve'),
(2, '5660', 'Couvin'),
(3, '6000', 'Charleroi');

-- --------------------------------------------------------

--
-- Structure de la table `order`
--

CREATE TABLE IF NOT EXISTS `order` (
  `IdOrder` int(11) NOT NULL AUTO_INCREMENT,
  `User_IdUser` int(11) NOT NULL,
  PRIMARY KEY (`IdOrder`),
  KEY `fk_Order_User1_idx` (`User_IdUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `order`
--

INSERT INTO `order` (`IdOrder`, `User_IdUser`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `orderline`
--

CREATE TABLE IF NOT EXISTS `orderline` (
  `IdOrderLine` int(11) NOT NULL AUTO_INCREMENT,
  `Order_IdOrder` int(11) NOT NULL,
  `Book_IdBook` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdOrderLine`),
  KEY `fk_OrderLine_Book1_idx` (`Book_IdBook`),
  KEY `fk_OrderLine_Order1_idx` (`Order_IdOrder`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `orderline`
--

INSERT INTO `orderline` (`IdOrderLine`, `Order_IdOrder`, `Book_IdBook`, `Quantity`) VALUES
(1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `orderstatus`
--

CREATE TABLE IF NOT EXISTS `orderstatus` (
  `IdOrderStatus` int(11) NOT NULL AUTO_INCREMENT,
  `OrderStatusName` varchar(50) NOT NULL,
  PRIMARY KEY (`IdOrderStatus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `orderstatushistory`
--

CREATE TABLE IF NOT EXISTS `orderstatushistory` (
  `IdOrderStatusHistory` int(11) NOT NULL AUTO_INCREMENT,
  `Order_IdOrder` int(11) NOT NULL,
  `OrderStatus_IdOrderStatus` int(11) NOT NULL,
  `Date` datetime DEFAULT NULL,
  PRIMARY KEY (`IdOrderStatusHistory`),
  KEY `fk_OrderStatusHistory_Order1_idx` (`Order_IdOrder`),
  KEY `fk_OrderStatusHistory_OrderStatus1_idx` (`OrderStatus_IdOrderStatus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `IdRole` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(50) NOT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdRole`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`IdRole`, `RoleName`, `IsActive`) VALUES
(1, 'admin', 1),
(2, 'user', 1),
(3, 'moderator', 1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `IdUser` int(11) NOT NULL AUTO_INCREMENT,
  `Role_IdRole` int(11) NOT NULL,
  `Locality_IdLocality` int(11) NOT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `PersonalPhone` varchar(50) DEFAULT NULL,
  `Street` varchar(50) DEFAULT NULL,
  `Number` int(11) DEFAULT NULL,
  `Box` varchar(50) DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdUser`),
  KEY `fk_User_Locality1_idx` (`Locality_IdLocality`),
  KEY `fk_User_Role1_idx` (`Role_IdRole`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`IdUser`, `Role_IdRole`, `Locality_IdLocality`, `FirstName`, `LastName`, `Birthday`, `Email`, `PersonalPhone`, `Street`, `Number`, `Box`, `Country`, `Password`, `IsActive`) VALUES
(1, 1, 1, 'val', 'bertr', '1957-02-01', 'e@mailinator.com', '0474032541', 'rue des blancs cheveaux', 7, '205', 'belgique', 'e', 1),
(2, 2, 2, 'Marduk', 'Nibiru', '1984-01-01', 'a@mailinator.com', '010123654', 'Rue des Combattants', 1, '1', 'Belgique', 'aaaaa', 1),
(3, 3, 1, 'b', 'b', '1983-02-02', 'b@mailinator.com', '000000010', 'b', 1, '1', 'be', 'b', 0);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `fk_Book_Author1` FOREIGN KEY (`Author_IdAuthor`) REFERENCES `author` (`IdAuthor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Book_Category1` FOREIGN KEY (`Category_IdCategory`) REFERENCES `category` (`IdCategory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Book_Editor1` FOREIGN KEY (`Editor_IdEditor`) REFERENCES `editor` (`IdEditor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
