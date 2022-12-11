SET MODE MySQL;
SET IGNORECASE = TRUE;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS listings;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS report;


-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS users
(
    id int(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(45) UNIQUE NOT NULL,
    password VARCHAR(64) NOT NULL,
    role VARCHAR(5) NOT NULL,  -- use enums (predetermined values) for role ('admin', 'user')
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
    image_url TEXT DEFAULT 'http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png',
    category VARCHAR(100) NOT NULL,
    collection_or_delivery VARCHAR(10) DEFAULT 'both',
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
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


-- -----------------------------------------------------
-- Table report
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS report
(

    id INT(11) NOT NULL AUTO_INCREMENT,
    user_id INT(11) NOT NULL,
    listing_id INT(11) NOT NULL,
    reason VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
    )
    engine = InnoDB;