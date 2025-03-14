-- create database itshowtime;
-- use itshowtime;


-- Table structure for user
CREATE TABLE User (
  username VARCHAR(255) PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

-- Table Structure for Cinema
CREATE TABLE Cinema (
  id INT NOT NULL AUTO_INCREMENT,
  cinemaId INT NOT NULL,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  postcode VARCHAR(45) NOT NULL,
  PRIMARY KEY(`id`),
  UNIQUE KEY `cinemaId` (`cinemaId`)
);

-- Table structure for UserCinema
CREATE TABLE UserCinema (
  userID INT NOT NULL,
  cinemaId INT NOT NULL,
  PRIMARY KEY (userID, cinemaId),
  KEY `cinemaId` (`cinemaId`)
  CONSTRAINT `usercinema_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE,
  CONSTRAINT `usercinema_ibfk_2` FOREIGN KEY (`cinemaId`) REFERENCES `cinema` (`cinemaId`) ON DELETE CASCADE
)