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

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `mjcookbook`;
INSERT INTO `user` (`username`, `password`, `created_at`, `profile_id`) VALUES ('orangeisntblue', 'password', '2020-01-01', 1);
INSERT INTO `user` (`username`, `password`, `created_at`, `profile_id`) VALUES ('mj', 'password', '2020-01-01', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `mjcookbook`;
INSERT INTO `recipe` (`id`, `title`, `recipe_text`, `created_at`, `updated_at`, `user_username`) VALUES (1, 'pizza', 'make dough make sauce assemble bake', '2020-01-01', '2020-01-01', 'orangeisntblue');
INSERT INTO `recipe` (`id`, `title`, `recipe_text`, `created_at`, `updated_at`, `user_username`) VALUES (2, 'turkey', 'slice up put in bowl', '2020-01-01', '2020-01-01', 'mj');

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

