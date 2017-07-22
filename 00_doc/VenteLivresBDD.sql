-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema VenteLivresBDD
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema VenteLivresBDD
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `VenteLivresBDD` DEFAULT CHARACTER SET utf8 ;
USE `VenteLivresBDD` ;

-- -----------------------------------------------------
-- Table `VenteLivresBDD`.`Locality`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VenteLivresBDD`.`Locality` (
  `IdLocality` INT NOT NULL,
  `ZipCode` VARCHAR(50) NULL,
  `City` VARCHAR(50) NULL,
  PRIMARY KEY (`IdLocality`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VenteLivresBDD`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VenteLivresBDD`.`Role` (
  `IdRole` INT NOT NULL AUTO_INCREMENT,
  `RoleName` VARCHAR(50) NOT NULL,
  `IsActive` TINYINT(1) NULL,
  PRIMARY KEY (`IdRole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VenteLivresBDD`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VenteLivresBDD`.`User` (
  `IdUser` INT NOT NULL,
  `Role_IdRole` INT NOT NULL,
  `Locality_IdLocality` INT NOT NULL,
  `FirstName` VARCHAR(255) NULL,
  `LastName` VARCHAR(255) NULL,
  `Birthday` DATE NULL,
  `Email` VARCHAR(50) NULL,
  `PersonalPhone` VARCHAR(50) NULL,
  `Street` VARCHAR(50) NULL,
  `Number` INT NULL,
  `Box` VARCHAR(50) NULL,
  `Country` VARCHAR(50) NULL,
  `Password` VARCHAR(255) NULL,
  `IsActive` TINYINT(1) NULL,
  PRIMARY KEY (`IdUser`),
  INDEX `fk_User_Locality1_idx` (`Locality_IdLocality` ASC),
  INDEX `fk_User_Role1_idx` (`Role_IdRole` ASC),
  CONSTRAINT `fk_User_Locality1`
    FOREIGN KEY (`Locality_IdLocality`)
    REFERENCES `VenteLivresBDD`.`Locality` (`IdLocality`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Role1`
    FOREIGN KEY (`Role_IdRole`)
    REFERENCES `VenteLivresBDD`.`Role` (`IdRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VenteLivresBDD`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VenteLivresBDD`.`Order` (
  `IdOrder` INT NOT NULL,
  `User_IdUser` INT NOT NULL,
  PRIMARY KEY (`IdOrder`),
  INDEX `fk_Order_User1_idx` (`User_IdUser` ASC),
  CONSTRAINT `fk_Order_User1`
    FOREIGN KEY (`User_IdUser`)
    REFERENCES `VenteLivresBDD`.`User` (`IdUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VenteLivresBDD`.`OrderStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VenteLivresBDD`.`OrderStatus` (
  `IdOrderStatus` INT NOT NULL AUTO_INCREMENT,
  `OrderStatusName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdOrderStatus`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VenteLivresBDD`.`Author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VenteLivresBDD`.`Author` (
  `IdAuthor` INT NOT NULL,
  `FirstName` VARCHAR(50) NULL,
  `LastName` VARCHAR(50) NULL,
  `Birthday` DATE NULL,
  `Nationality` VARCHAR(50) NULL,
  `IsActive` TINYINT(1) NULL,
  PRIMARY KEY (`IdAuthor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VenteLivresBDD`.`Editor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VenteLivresBDD`.`Editor` (
  `IdEditor` INT NOT NULL,
  `EditorName` VARCHAR(50) NOT NULL,
  `IsActive` TINYINT(1) NULL,
  PRIMARY KEY (`IdEditor`),
  UNIQUE INDEX `EditorName_UNIQUE` (`EditorName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VenteLivresBDD`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VenteLivresBDD`.`Category` (
  `IdCategory` INT NOT NULL AUTO_INCREMENT,
  `CategoryName` VARCHAR(50) NOT NULL,
  `IsActive` TINYINT(1) NULL,
  PRIMARY KEY (`IdCategory`),
  UNIQUE INDEX `CategoryName_UNIQUE` (`CategoryName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VenteLivresBDD`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VenteLivresBDD`.`Book` (
  `IdBook` INT NOT NULL,
  `Author_IdAuthor` INT NOT NULL,
  `Category_IdCategory` INT NOT NULL,
  `Editor_IdEditor` INT NOT NULL,
  `Price` DOUBLE NULL,
  `IsActive` TINYINT(1) NULL,
  PRIMARY KEY (`IdBook`),
  INDEX `fk_Book_Author1_idx` (`Author_IdAuthor` ASC),
  INDEX `fk_Book_Editor1_idx` (`Editor_IdEditor` ASC),
  INDEX `fk_Book_Category1_idx` (`Category_IdCategory` ASC),
  CONSTRAINT `fk_Book_Author1`
    FOREIGN KEY (`Author_IdAuthor`)
    REFERENCES `VenteLivresBDD`.`Author` (`IdAuthor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_Editor1`
    FOREIGN KEY (`Editor_IdEditor`)
    REFERENCES `VenteLivresBDD`.`Editor` (`IdEditor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_Category1`
    FOREIGN KEY (`Category_IdCategory`)
    REFERENCES `VenteLivresBDD`.`Category` (`IdCategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VenteLivresBDD`.`OrderLine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VenteLivresBDD`.`OrderLine` (
  `IdOrderLine` INT NOT NULL,
  `Order_IdOrder` INT NOT NULL,
  `Book_IdBook` INT NOT NULL,
  `Quantity` INT NULL,
  PRIMARY KEY (`IdOrderLine`),
  INDEX `fk_OrderLine_Book1_idx` (`Book_IdBook` ASC),
  INDEX `fk_OrderLine_Order1_idx` (`Order_IdOrder` ASC),
  CONSTRAINT `fk_OrderLine_Book1`
    FOREIGN KEY (`Book_IdBook`)
    REFERENCES `VenteLivresBDD`.`Book` (`IdBook`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderLine_Order1`
    FOREIGN KEY (`Order_IdOrder`)
    REFERENCES `VenteLivresBDD`.`Order` (`IdOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VenteLivresBDD`.`OrderStatusHistory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VenteLivresBDD`.`OrderStatusHistory` (
  `IdOrderStatusHistory` INT NOT NULL,
  `Order_IdOrder` INT NOT NULL,
  `OrderStatus_IdOrderStatus` INT NOT NULL,
  `Date` DATETIME NULL,
  PRIMARY KEY (`IdOrderStatusHistory`),
  INDEX `fk_OrderStatusHistory_Order1_idx` (`Order_IdOrder` ASC),
  INDEX `fk_OrderStatusHistory_OrderStatus1_idx` (`OrderStatus_IdOrderStatus` ASC),
  CONSTRAINT `fk_OrderStatusHistory_Order1`
    FOREIGN KEY (`Order_IdOrder`)
    REFERENCES `VenteLivresBDD`.`Order` (`IdOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderStatusHistory_OrderStatus1`
    FOREIGN KEY (`OrderStatus_IdOrderStatus`)
    REFERENCES `VenteLivresBDD`.`OrderStatus` (`IdOrderStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- add the title column to the book table
ALTER TABLE `book` CHANGE `title` `title` VARCHAR(255) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL;
