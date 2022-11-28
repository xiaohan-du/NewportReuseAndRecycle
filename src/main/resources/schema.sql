SET MODE MySQL;
SET IGNORECASE = TRUE;

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------

DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users
(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(45) NOT NULL,
    `password` varchar(64) NOT NULL,
    `role` varchar(45) NOT NULL,
    `enabled` tinyint(4) DEFAULT NULL,
    PRIMARY KEY (`id`)
    )
    engine = InnoDB;