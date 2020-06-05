-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mjcookbook
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mjcookbook` ;

-- -----------------------------------------------------
-- Schema mjcookbook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mjcookbook` DEFAULT CHARACTER SET utf8 ;
USE `mjcookbook` ;

-- -----------------------------------------------------
-- Table `profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `profile` ;

CREATE TABLE IF NOT EXISTS `profile` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `username` VARCHAR(32) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  `profile_id` INT NOT NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(8) NULL,
  PRIMARY KEY (`username`),
  INDEX `fk_user_profile_idx` (`profile_id` ASC),
  CONSTRAINT `fk_user_profile`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipe` ;

CREATE TABLE IF NOT EXISTS `recipe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(256) NOT NULL,
  `recipe_text` VARCHAR(20000) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  `user_username` VARCHAR(32) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `status` ENUM('working', 'published') NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_user1_idx` (`user_username` ASC),
  CONSTRAINT `fk_recipe_user1`
    FOREIGN KEY (`user_username`)
    REFERENCES `user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipe_has_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipe_has_user` ;

CREATE TABLE IF NOT EXISTS `recipe_has_user` (
  `recipe_id` INT NOT NULL,
  `user_username` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`recipe_id`, `user_username`),
  INDEX `fk_recipe_has_user_user1_idx` (`user_username` ASC),
  INDEX `fk_recipe_has_user_recipe1_idx` (`recipe_id` ASC),
  CONSTRAINT `fk_recipe_has_user_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_has_user_user1`
    FOREIGN KEY (`user_username`)
    REFERENCES `user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meal` ;

CREATE TABLE IF NOT EXISTS `meal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `completed` TINYINT NOT NULL,
  `enabled` TINYINT NOT NULL,
  `planned_for` DATETIME NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  `recipe_id` INT NOT NULL,
  `user_username` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meal_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_meal_user1_idx` (`user_username` ASC),
  CONSTRAINT `fk_meal_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meal_user1`
    FOREIGN KEY (`user_username`)
    REFERENCES `user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `instruction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `instruction` ;

CREATE TABLE IF NOT EXISTS `instruction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `recipe_id` INT NOT NULL,
  `sequence` INT NOT NULL,
  `text` VARCHAR(20000) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_instruction_recipe1_idx` (`recipe_id` ASC),
  CONSTRAINT `fk_instruction_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ingredient` ;

CREATE TABLE IF NOT EXISTS `ingredient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `amount` VARCHAR(256) NOT NULL,
  `substitute` VARCHAR(1024) NULL,
  `inclusion` ENUM('mandatory', 'removable', 'extra') NOT NULL,
  `instruction_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ingredient_instruction1_idx` (`instruction_id` ASC),
  INDEX `fk_ingredient_recipe1_idx` (`recipe_id` ASC),
  CONSTRAINT `fk_ingredient_instruction1`
    FOREIGN KEY (`instruction_id`)
    REFERENCES `instruction` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingredient_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS jpauser;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'jpauser' IDENTIFIED BY 'whatsaneal';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'jpauser';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `profile`
-- -----------------------------------------------------
START TRANSACTION;
USE `mjcookbook`;
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `description`) VALUES (1, 'jp', 'sypniewski', 'a developer');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `description`) VALUES (2, 'mj', 'cs', 'a cat');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `description`) VALUES (3, 'mb', 'chandler', 'a chef');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `mjcookbook`;
INSERT INTO `user` (`username`, `password`, `created_at`, `profile_id`, `enabled`, `role`) VALUES ('orangeisntblue', '$2a$10$6Xfo2Hdk.PseVvxYah70Y.NN087XAh2Wr8rd8wMxs4b7Gigj9Pr7K', '2020-01-01', 1, 1, 'admin');
INSERT INTO `user` (`username`, `password`, `created_at`, `profile_id`, `enabled`, `role`) VALUES ('mj', '$2a$10$6Xfo2Hdk.PseVvxYah70Y.NN087XAh2Wr8rd8wMxs4b7Gigj9Pr7K', '2020-01-01', 2, 1, 'user');
INSERT INTO `user` (`username`, `password`, `created_at`, `profile_id`, `enabled`, `role`) VALUES ('mbc', '$2a$10$6Xfo2Hdk.PseVvxYah70Y.NN087XAh2Wr8rd8wMxs4b7Gigj9Pr7K', '2020-06-01', 3, 1, 'chef');

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `mjcookbook`;
INSERT INTO `recipe` (`id`, `title`, `recipe_text`, `created_at`, `updated_at`, `user_username`, `enabled`, `status`) VALUES (1, 'pizza', 'make dough make sauce assemble bake', '2020-01-01', '2020-01-01', 'orangeisntblue', 1, 'published');
INSERT INTO `recipe` (`id`, `title`, `recipe_text`, `created_at`, `updated_at`, `user_username`, `enabled`, `status`) VALUES (2, 'turkey', 'slice up put in bowl', '2020-01-01', '2020-01-01', 'mj', 1, 'published');

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe_has_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `mjcookbook`;
INSERT INTO `recipe_has_user` (`recipe_id`, `user_username`) VALUES (1, 'orangeisntblue');
INSERT INTO `recipe_has_user` (`recipe_id`, `user_username`) VALUES (1, 'mj');
INSERT INTO `recipe_has_user` (`recipe_id`, `user_username`) VALUES (2, 'mj');

COMMIT;


-- -----------------------------------------------------
-- Data for table `meal`
-- -----------------------------------------------------
START TRANSACTION;
USE `mjcookbook`;
INSERT INTO `meal` (`id`, `rating`, `completed`, `enabled`, `planned_for`, `created_at`, `updated_at`, `recipe_id`, `user_username`) VALUES (1, 5, 1, 1, '2020-05-28', '2020-05-01', '2020-05-28', 2, 'mj');

COMMIT;


-- -----------------------------------------------------
-- Data for table `instruction`
-- -----------------------------------------------------
START TRANSACTION;
USE `mjcookbook`;
INSERT INTO `instruction` (`id`, `recipe_id`, `sequence`, `text`) VALUES (1, 1, 1, 'first instruction recipe one');
INSERT INTO `instruction` (`id`, `recipe_id`, `sequence`, `text`) VALUES (2, 1, 2, 'second instruction recipe one');
INSERT INTO `instruction` (`id`, `recipe_id`, `sequence`, `text`) VALUES (3, 1, 3, 'third instruction recipe one');
INSERT INTO `instruction` (`id`, `recipe_id`, `sequence`, `text`) VALUES (4, 2, 1, 'get turkey from fridge');
INSERT INTO `instruction` (`id`, `recipe_id`, `sequence`, `text`) VALUES (5, 2, 2, 'tear up put on floor');
INSERT INTO `instruction` (`id`, `recipe_id`, `sequence`, `text`) VALUES (6, 2, 3, 'give treat too');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ingredient`
-- -----------------------------------------------------
START TRANSACTION;
USE `mjcookbook`;
INSERT INTO `ingredient` (`id`, `name`, `amount`, `substitute`, `inclusion`, `instruction_id`, `recipe_id`) VALUES (1, 'turkey', '1 slice', 'ham, salami', 'mandatory', 4, 2);
INSERT INTO `ingredient` (`id`, `name`, `amount`, `substitute`, `inclusion`, `instruction_id`, `recipe_id`) VALUES (2, 'treat', '1 treat', 'turkey', 'extra', 6, 2);
INSERT INTO `ingredient` (`id`, `name`, `amount`, `substitute`, `inclusion`, `instruction_id`, `recipe_id`) VALUES (3, 'pets', 'all of them', NULL, 'mandatory', 6, 2);

COMMIT;

