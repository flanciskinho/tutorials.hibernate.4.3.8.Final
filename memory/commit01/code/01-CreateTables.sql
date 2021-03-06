USE pojo;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS events;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE events (
    event_id BIGINT NOT NULL AUTO_INCREMENT,
    event_date TIMESTAMP NOT NULL,
    title VARCHAR(30) NOT NULL,
    CONSTRAINT Uvent_PK PRIMARY KEY (event_id)
) ENGINE = InnoDB;
