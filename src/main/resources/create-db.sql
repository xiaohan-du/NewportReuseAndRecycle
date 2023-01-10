-- -----------------------------------------------------
-- Set User Privilege
-- -----------------------------------------------------

DROP USER 'c21127478'@'localhost';
CREATE USER 'c21127478'@'localhost' IDENTIFIED BY 'comsc';
GRANT SELECT, UPDATE, INSERT ON newportreuseandrecycle.* TO 'c21127478'@'localhost';

-- -----------------------------------------------------
-- Table actions
-- -----------------------------------------------------

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS listing;
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
    description VARCHAR(100) NOT NULL,
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

-- -----------------------------------------------------
-- Insert Data
-- -----------------------------------------------------


INSERT INTO users (username, password, role, enabled)
VALUES ('xiaohan',
        '$2a$12$CC6NMiiN5TVemkp4BVyVsO69wg4Pofgu35Lg9LvFmiE/OQhOT7dbm',
        'user', 1),
       ('admin',
        '$2a$04$VwoWUzjRYC3eodssFgcO6.HLQXjzOj4p33y4Afmp4hdrONYG4kut2',
        'admin', 1);

INSERT INTO listing (title, description, price, image_url, user_id, category, latitude, longitude)
VALUES ('Yaris', 'Toyota Yaris', 12000, 'https://scene7.toyota.eu/is/image/toyotaeurope/used-cars-asset-header?qlt=80&wid=1280&fit=fit,1&ts=1672182094644', 1, 'vehicle', 51.4902713, -3.1766767),
       ('EQS', 'Mercedes Benz EQS 2022', 49999, 'https://media.wired.co.uk/photos/61e0bda26e0778ad10beb48a/16:9/w_1280,c_limit/Gear-Mercedes-EQS-21C0123_001.jpg', 1, 'vehicle', 51.4902713, -3.1766767),
       ('Calculator', 'Calculator, like new', 3.99, 'https://www.hrblock.com/tax-center/wp-content/uploads/2022/12/tax-law-changes-2022-feature.-1280x720.jpg', 1, 'electronics', 51.4902713, -3.1766767),
       ('Kitchen tools', 'A set of kitchen tools, new', 4.99, 'https://assets.epicurious.com/photos/57597a5a973781fc02c2a823/16:9/w_1280,c_limit/20150513_Pantry_Items_Posters_Toolkit.jpg', 1, 'household', 51.4902713, -3.1766767);

INSERT INTO category (category)
VALUES ('vehicle'),
       ('electronics'),
       ('household');

INSERT INTO report (user_id, listing_id, reason)
VALUES(1,2,'This is inappropriate.');