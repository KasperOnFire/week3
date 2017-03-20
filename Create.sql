drop database if exists library;
create database library;
use library;


CREATE TABLE users (
    uno INT AUTO_INCREMENT,
    uname VARCHAR(255) NOT NULL UNIQUE,
    pass VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(255),
    mail VARCHAR(255),
    ssn VARCHAR(255),
    libstatus BOOL DEFAULT 1,
    rescount INT DEFAULT 0,
    PRIMARY KEY (uno)
);
	
CREATE TABLE inventory (
    ino INT AUTO_INCREMENT,
    isbn VARCHAR(255),
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    itype ENUM('book', 'dvd', 'journal'),
    language VARCHAR(255),
    publisher VARCHAR(255),
    publishdate DATE,
    regdate DATE,
    PRIMARY KEY (ino)
);

CREATE TABLE reservations (
    rno INT AUTO_INCREMENT,
    ino INT,
    uno INT,
    resdate DATE,
    PRIMARY KEY (rno),
    FOREIGN KEY (ino)
        REFERENCES inventory (ino),
    FOREIGN KEY (uno)
        REFERENCES users (uno)
);

CREATE TABLE employees (
    eno INT AUTO_INCREMENT,
    ename VARCHAR(255) NOT NULL,
    epass VARCHAR(255) NOT NULL,
    PRIMARY KEY (eno)
);