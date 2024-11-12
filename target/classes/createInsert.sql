CREATE DATABASE IF NOT EXISTS `animal_shelter` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `animal_shelter`;

DROP TABLE IF EXISTS `adoption_request`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `animal`;

CREATE TABLE `user` (
                        `idUser` INT NOT NULL AUTO_INCREMENT,
                        `email` VARCHAR(105) NOT NULL,
                        `password` VARCHAR(105) NOT NULL,
                        `role` VARCHAR(50) NOT NULL,
                        PRIMARY KEY (`idUser`),
                        UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `animal` (
                          `idAnimal` INT NOT NULL AUTO_INCREMENT,
                          `name` VARCHAR(50) NOT NULL,
                          `species` VARCHAR(15) NOT NULL,
                          `age` INT NOT NULL,
                          `gender` VARCHAR(50) NOT NULL,
                          `description` VARCHAR(255) NOT NULL,
                          `status` VARCHAR(50) NOT NULL,
                          PRIMARY KEY (`idAnimal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `adoption_request` (
                                    `idAdoption` INT NOT NULL AUTO_INCREMENT,
                                    `idUser` INT NOT NULL,
                                    `idAnimal` INT NOT NULL,
                                    `requestDate` DATE NOT NULL,
                                    `status` VARCHAR(50) NOT NULL,
                                    PRIMARY KEY (`idAdoption`),
                                    KEY `userId_idx` (`idUser`),
                                    KEY `animalId_idx` (`idAnimal`),
                                    CONSTRAINT `adoption_request_user_fk` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE,
                                    CONSTRAINT `adoption_request_animal_fk` FOREIGN KEY (`idAnimal`) REFERENCES `animal` (`idAnimal`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
