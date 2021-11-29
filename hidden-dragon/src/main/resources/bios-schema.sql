-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cmpe172
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cmpe172` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `cmpe172` ;

-- -----------------------------------------------------
-- Table `cmpe172`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cmpe172`.`item` ;

CREATE TABLE IF NOT EXISTS `cmpe172`.`item` (
  `item_id` INT NOT NULL,
  `item_name` VARCHAR(20) NOT NULL,
  `price` DECIMAL(10,2) NULL,
  `stock` INT NULL,
  PRIMARY KEY (`item_id`))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
