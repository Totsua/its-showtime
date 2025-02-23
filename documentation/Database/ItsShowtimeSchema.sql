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
  cinemaId INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL
);

-- Table structure for UserCinema
CREATE TABLE UserCinema (
  username VARCHAR(255),
  cinemaId INT,
  PRIMARY KEY (username, cinemaId),
  FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE,
  FOREIGN KEY (cinemaId) REFERENCES Cinema(cinemaId) ON DELETE CASCADE
)