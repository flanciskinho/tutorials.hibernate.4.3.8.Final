USE pojo;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS categories;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE categories (
	id BIGINT NOT NULL AUTO_INCREMENT,
	description VARCHAR(100),
	CONSTRAINT Category_PK
		PRIMARY KEY(id)
) ENGINE = InnoDB;

CREATE TABLE events (
    event_id BIGINT NOT NULL AUTO_INCREMENT,
    event_date TIMESTAMP NOT NULL,
    title VARCHAR(30) NOT NULL,
    category BIGINT NOT NULL,
    CONSTRAINT Event_PK
    	PRIMARY KEY (event_id),
    CONSTRAINT EventCategory
    	FOREIGN KEY (category)
    		REFERENCES  categories(id)
    	ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;