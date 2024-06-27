CREATE DATABASE IF NOT EXISTS `animal_shelter` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `animal_shelter`;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` INT NOT NULL AUTO_INCREMENT,
                        `email` VARCHAR(105) NOT NULL,
                        `password` VARCHAR(105) NOT NULL,
                        `role` VARCHAR(50) NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `animal`;
CREATE TABLE `animal` (
                          `id` INT NOT NULL AUTO_INCREMENT,
                          `name` VARCHAR(50) NOT NULL,
                          `species` VARCHAR(15) NOT NULL,
                          `age` INT NOT NULL,
                          `gender` VARCHAR(50) NOT NULL,
                          `description` VARCHAR(255) NOT NULL,
                          `status` VARCHAR(50) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
DROP TABLE IF EXISTS `adoption_request`;
CREATE TABLE `adoption_request` (
                                   `id` INT NOT NULL AUTO_INCREMENT,
                                   `userId` INT NOT NULL,
                                   `animalId` INT NOT NULL,
                                   `requestDate` DATE NOT NULL,
                                   `status` VARCHAR(50) NOT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `userId_idx` (`userId`),
                                   KEY `animalId_idx` (`animalId`),
                                   CONSTRAINT `adoption_request_user_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
                                   CONSTRAINT `adoption_request_animal_fk` FOREIGN KEY (`animalId`) REFERENCES `animal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
