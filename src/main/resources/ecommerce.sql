SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ecommerce
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ecommerce` ;

-- -----------------------------------------------------
-- Schema ecommerce
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecommerce` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ecommerce` ;

-- -----------------------------------------------------
-- Table `ecommerce`.`cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`cart` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`cart` (
  `id` BIGINT NOT NULL,
  `userid` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecommerce`.`cart_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`cart_items` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`cart_items` (
  `id` BIGINT NOT NULL,
  `cartid` BIGINT NULL DEFAULT NULL,
  `productid` BIGINT NULL DEFAULT NULL,
  `quantity` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecommerce`.`cart_items_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`cart_items_seq` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`cart_items_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ecommerce`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`category` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `thumbnail_path` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecommerce`.`notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`notification` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`notification` (
  `id` BIGINT NOT NULL,
  `detail_content` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `userid` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ecommerce`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`orders` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`orders` (
  `orderid` BIGINT NOT NULL,
  `expected_shipped_date` VARCHAR(255) NULL DEFAULT NULL,
  `order_date` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `type_of_payment` VARCHAR(255) NULL DEFAULT NULL,
  `userid` BIGINT NULL DEFAULT NULL,
  `voucherid` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ecommerce`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`product` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `available` BIGINT NULL DEFAULT NULL,
  `category` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `imgurl` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `rating` DOUBLE NULL DEFAULT NULL,
  `shop_name` VARCHAR(255) NULL DEFAULT NULL,
  `sold` BIGINT NULL DEFAULT NULL,
  `orderid` BIGINT NULL DEFAULT NULL,
  `shopid` BIGINT NULL DEFAULT NULL,
  `cart_itemid` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 103
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ecommerce`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`roles` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`roles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` ENUM('SYSTEM_ADMIN', 'USER', 'SHOP_ADMIN') NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ecommerce`.`shop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`shop` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`shop` (
  `shopid` BIGINT NOT NULL,
  `chat_performance` DOUBLE NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `followers` BIGINT NULL DEFAULT NULL,
  `following` BIGINT NULL DEFAULT NULL,
  `joining_date` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `type_of_product` VARCHAR(255) NULL DEFAULT NULL,
  `type_of_shop` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`shopid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ecommerce`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`user` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`user` (
  `id` BIGINT NOT NULL,
  `dob` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `gender` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecommerce`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`user_role` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`user_role` (
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ecommerce`.`voucher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`voucher` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`voucher` (
  `id` BIGINT NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `discount_amount` DOUBLE NULL DEFAULT NULL,
  `expiration_date` VARCHAR(255) NULL DEFAULT NULL,
  `minimum_order_value` DOUBLE NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `userid` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
