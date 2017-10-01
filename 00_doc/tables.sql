CREATE TABLE IF NOT EXISTS `author` (
  `IdAuthor` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `Nationality` varchar(50) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdAuthor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `category` (
  `IdCategory` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(50) NOT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdCategory`),
  UNIQUE KEY `CategoryName_UNIQUE` (`CategoryName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

CREATE TABLE IF NOT EXISTS `editor` (
  `IdEditor` int(11) NOT NULL AUTO_INCREMENT,
  `EditorName` varchar(50) NOT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdEditor`),
  UNIQUE KEY `EditorName_UNIQUE` (`EditorName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `locality` (
  `IdLocality` int(11) NOT NULL AUTO_INCREMENT,
  `ZipCode` varchar(50) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdLocality`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `order` (
  `IdOrder` int(11) NOT NULL AUTO_INCREMENT,
  `User_IdUser` int(11) NOT NULL,
  PRIMARY KEY (`IdOrder`),
  KEY `fk_Order_User1_idx` (`User_IdUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `orderline` (
  `IdOrderLine` int(11) NOT NULL AUTO_INCREMENT,
  `Order_IdOrder` int(11) NOT NULL,
  `Book_IdBook` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdOrderLine`),
  KEY `fk_OrderLine_Book1_idx` (`Book_IdBook`),
  KEY `fk_OrderLine_Order1_idx` (`Order_IdOrder`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `orderstatus` (
  `IdOrderStatus` int(11) NOT NULL AUTO_INCREMENT,
  `OrderStatusName` varchar(50) NOT NULL,
  PRIMARY KEY (`IdOrderStatus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `orderstatushistory` (
  `IdOrderStatusHistory` int(11) NOT NULL AUTO_INCREMENT,
  `Order_IdOrder` int(11) NOT NULL,
  `OrderStatus_IdOrderStatus` int(11) NOT NULL,
  `Date` datetime DEFAULT NULL,
  PRIMARY KEY (`IdOrderStatusHistory`),
  KEY `fk_OrderStatusHistory_Order1_idx` (`Order_IdOrder`),
  KEY `fk_OrderStatusHistory_OrderStatus1_idx` (`OrderStatus_IdOrderStatus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `role` (
  `IdRole` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(50) NOT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdRole`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `book`
  ADD CONSTRAINT `fk_Book_Author1` FOREIGN KEY (`Author_IdAuthor`) REFERENCES `author` (`IdAuthor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Book_Category1` FOREIGN KEY (`Category_IdCategory`) REFERENCES `category` (`IdCategory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Book_Editor1` FOREIGN KEY (`Editor_IdEditor`) REFERENCES `editor` (`IdEditor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `fk_Order_User1` FOREIGN KEY (`User_IdUser`) REFERENCES `user` (`IdUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `orderline`
--
ALTER TABLE `orderline`
  ADD CONSTRAINT `fk_OrderLine_Book1` FOREIGN KEY (`Book_IdBook`) REFERENCES `book` (`IdBook`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_OrderLine_Order1` FOREIGN KEY (`Order_IdOrder`) REFERENCES `order` (`IdOrder`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `orderstatushistory`
--
ALTER TABLE `orderstatushistory`
  ADD CONSTRAINT `fk_OrderStatusHistory_Order1` FOREIGN KEY (`Order_IdOrder`) REFERENCES `order` (`IdOrder`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_OrderStatusHistory_OrderStatus1` FOREIGN KEY (`OrderStatus_IdOrderStatus`) REFERENCES `orderstatus` (`IdOrderStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_User_Locality1` FOREIGN KEY (`Locality_IdLocality`) REFERENCES `locality` (`IdLocality`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_User_Role1` FOREIGN KEY (`Role_IdRole`) REFERENCES `role` (`IdRole`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
