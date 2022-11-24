SET MODE MySQL;
SET IGNORECASE = TRUE;

-- -----------------------------------------------------
-- Table `project_user`
-- -----------------------------------------------------

DROP TABLE IF EXISTS project_user;


CREATE TABLE IF NOT EXISTS project_user
(
    id              INTEGER         NOT NULL,
    email           VARCHAR(45)     NOT NULL,
    password        VARCHAR(64)     NOT NULL,
    first_name      VARCHAR(20)     NOT NULL,
    last_name       VARCHAR(20)     NOT NULL,
    PRIMARY KEY (`id`)
    )
    engine = InnoDB;