DROP TABLE  resolveAmenities ;
DROP TABLE  resolveGenre ;
DROP TABLE  resolveActor ;
DROP TABLE  comment ;
DROP TABLE  booking ;
DROP TABLE  users;
DROP TABLE  amenities ;
DROP TABLE  genre ;
DROP TABLE  actor ;
DROP TABLE  showTimes ;
DROP TABLE  resolveMovies ;
DROP TABLE  cinema;
DROP TABLE  movies ;

CREATE TABLE  users (
  userID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  username VARCHAR(200) NOT NULL,
  first_name VARCHAR(70) NOT NULL,
  last_name VARCHAR(70) NOT NULL,
  nickname VARCHAR(50) NOT NULL,
  email VARCHAR(200) NOT NULL,
  password VARCHAR(200) NOT NULL,
  role VARCHAR(50) NOT NULL,
  status VARCHAR(300),
  PRIMARY KEY (userID));




CREATE TABLE  cinema (
  cinemaID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(45) NOT NULL,
  location VARCHAR(45) NOT NULL,
  capacity INT NOT NULL,
  PRIMARY KEY (cinemaID));





CREATE TABLE  amenities (
  amenitiesID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (amenitiesID));





CREATE TABLE  resolveAmenities (
  cinemaID INT NOT NULL,
  amenitiesID INT NOT NULL,
  CONSTRAINT fk_resolveAmenities_cinema
    FOREIGN KEY (cinemaID)
    REFERENCES cinema (cinemaID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_resolveAmenities_amenities1
    FOREIGN KEY (amenitiesID)
    REFERENCES amenities (amenitiesID));
    





CREATE TABLE  movies (
  movieID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  title VARCHAR(45) NOT NULL,
  poster VARCHAR(45) NOT NULL,
  director VARCHAR(45) NOT NULL,
  sypnosis VARCHAR(45) NOT NULL,
  ageRating VARCHAR(10) NOT NULL,
  releaseDate DATE NOT NULL,
  PRIMARY KEY (movieID));





CREATE TABLE  resolveMovies (
  resolveMoviesID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  cinemaID INT NOT NULL,
  movieID INT NOT NULL,
  PRIMARY KEY (resolveMoviesID),
  CONSTRAINT fk_table1_cinema1
    FOREIGN KEY (cinemaID)
    REFERENCES cinema (cinemaID),
  CONSTRAINT fk_table1_movies1
    FOREIGN KEY (movieID)
    REFERENCES movies (movieID));
    



CREATE TABLE  genre (
  genreID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (genreID));





CREATE TABLE  resolveGenre (
  movieID INT NOT NULL,
  genreID INT NOT NULL,
  CONSTRAINT fk_table1_movies2
    FOREIGN KEY (movieID)
    REFERENCES movies (movieID),
  CONSTRAINT fk_table1_genre1
    FOREIGN KEY (genreID)
    REFERENCES genre (genreID));



CREATE TABLE  actor (
  actorID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(200) NOT NULL,
  PRIMARY KEY (actorID));



CREATE TABLE  resolveActor (
  movieID INT NOT NULL,
  actorID INT NOT NULL,
  CONSTRAINT fk_resolveActor_movies1
    FOREIGN KEY (movieID)
    REFERENCES movies (movieID),
  CONSTRAINT fk_resolveActor_actor1
    FOREIGN KEY (actorID)
    REFERENCES actor (actorID));
    


CREATE TABLE  showTimes (
  showTimeID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  resolveMoviesID INT NOT NULL,
  time VARCHAR(50) NOT NULL,
  PRIMARY KEY (showTimeID),
  CONSTRAINT fk_showTimes_resolveMovies1
    FOREIGN KEY (resolveMoviesID)
    REFERENCES resolveMovies (resolveMoviesID));




CREATE TABLE  comment (
  commentID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  movieID INT NOT NULL,
  userID INT NOT NULL,
  comment VARCHAR(45) NOT NULL,
  rating DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (commentID),
  CONSTRAINT fk_comment_movies1
    FOREIGN KEY (movieID)
    REFERENCES movies (movieID),
  CONSTRAINT fk_comment_user1
    FOREIGN KEY (userID)
    REFERENCES users (userID));




CREATE TABLE  booking (
  bookingID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  userID INT NOT NULL,
  noOfTicket INT NOT NULL,
  showTimeID INT NOT NULL,
  bookingDate DATE NOT NULL,
  PRIMARY KEY (bookingID),
  CONSTRAINT fk_booking_user1
    FOREIGN KEY (userID)
    REFERENCES users (userID),
  CONSTRAINT fk_booking_showTimes1
    FOREIGN KEY (showTimeID)
    REFERENCES showTimes (showTimeID));


