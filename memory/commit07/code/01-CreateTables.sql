USE pojo;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS UsersEvents;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS addresses;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(60) NOT NULL,
    dob DATETIME NOT NULL,
    CONSTRAINT User_PK
        PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE addresses (
    id BIGINT NOT NULL,
    street VARCHAR(50) NOT NULL,
    CONSTRAINT Address_PK
        PRIMARY KEY (id),
    CONSTRAINT AddressUser
        FOREIGN KEY (id)
            REFERENCES users(id)
) ENGINE = InnoDB;

CREATE TABLE categories (
	id BIGINT NOT NULL AUTO_INCREMENT,
	description VARCHAR(100) NOT NULL,
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

CREATE TABLE UsersEvents (
    user_id  BIGINT NOT NULL,
    event BIGINT NOT NULL,
    CONSTRAINT UserEvent_PK
        PRIMARY KEY (user_id, event),
    CONSTRAINT User_UsersEvents
        FOREIGN KEY (user_id)
            REFERENCES users(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT Event_UsersEvents
        FOREIGN KEY (event)
            REFERENCES events(event_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;
