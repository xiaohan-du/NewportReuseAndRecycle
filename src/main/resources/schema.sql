SET MODE MySQL;
SET IGNORECASE = TRUE;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS listings;


-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS users
(
    id int(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(64) NOT NULL,
    role VARCHAR(45) NOT NULL,
    enabled BIT DEFAULT NULL,
    PRIMARY KEY (`id`)
    )
    engine = InnoDB;

-- -----------------------------------------------------
-- Table listing
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS listing
(
    id INT(11) NOT NULL AUTO_INCREMENT,
    user_id INT(11) NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(MAX) NOT NULL,
    price DOUBLE NOT NULL,
    image_url TEXT NOT NULL,
    category VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
    )
    engine = InnoDB;

-- -----------------------------------------------------
-- Table category
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS category
(
    id INT(11) NOT NULL AUTO_INCREMENT,
    category VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
    )
    engine = InnoDB;