SET MODE MySQL;
SET IGNORECASE = TRUE;

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------

DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users
(
    id                  INTEGER         NOT NULL,
    email               VARCHAR(45)     NOT NULL,
    password            VARCHAR(64)     NOT NULL,
    first_name          VARCHAR(20)     NOT NULL,
    last_name           VARCHAR(20)     NOT NULL,
    PRIMARY KEY (`id`)
    )
    engine = InnoDB;