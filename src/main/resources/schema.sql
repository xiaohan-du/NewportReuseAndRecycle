SET MODE MySQL;
SET IGNORECASE = TRUE;

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------

DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users
(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(64) NOT NULL,
    `role` VARCHAR(45) NOT NULL,
    `enabled` BIT DEFAULT NULL,
    PRIMARY KEY (`id`)
    )
    engine = InnoDB;