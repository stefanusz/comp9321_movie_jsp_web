INSERT INTO users VALUES (DEFAULT,'username','first_name', 'last_name', 'nickname', 'email', 'password', 'role');

SELECT username from USERS WHERE username = 'ste' ;

SELECT * from users;
SELECT * from cinema;
SELECT * from amenities;
SELECT * from actor;
SELECT * from movies;
SELECT * from genre;
SELECT * from resolvegenre;
SELECT * from resolveamenities;
SELECT * from comment;
SELECT * from resolveactor;
SELECT * FROM SHOWTIMES;
SELECT * FROM resolvemovies;


SELECT * FROM users WHERE username = 'ste';

DELETE from users;

SELECT HashBytes('MD5','admin');


UPDATE users SET role = 'admin' WHERE username = 'admin';

SELECT name from resolvegenre r JOIN genre g ON g.genreid = r.genreid WHERE movieid = 7;


SELECT AVG(rating) AS averageRating FROM comment WHERE movieid = 2;

SELECT * FROM movies WHERE releasedate < '2001-01-01' ORDER BY movieid DESC;
SELECT * FROM movies WHERE releasedate > '2014-10-08' ORDER BY movieid DESC;

SELECT title,username,comment,rating FROM comment c
JOIN movies m ON c.movieid = m.movieid
JOIN users u ON c.userid = u.userid
WHERE m.movieid = 2;

INSERT INTO actor VALUES (DEFAULT,'cat');

SELECT name FROM resolveactor r JOIN actor a ON a.actorid = r.actorid WHERE movieid = 15;

SELECT AVG(rating) AS averagerating FROM comment WHERE movieid =11;

SELECT * FROM movies WHERE releasedate < '2014-10-10' ORDER BY movieid ,releaseDate DESC;


SELECT * FROM movies WHERE releasedate > '2014-10-09' ORDER BY movieid DESC;




SELECT * FROM resolvemovies rm JOIN showtimes s ON rm.resolvemoviesid = s.resolvemoviesid WHERE rm.cinemaid = 1;


SELECT time FROM resolvemovies rm JOIN showtimes s ON rm.resolvemoviesid = s.resolvemoviesid WHERE rm.cinemaid = 1 AND rm.movieid=1;

SELECT * FROM resolvemovies;

SELECT title FROM MOVIES WHERE title = "hayabusa";

SELECT DISTINCT m.movieID, m.title, g.name, m.poster, m.sypnosis FROM (SELECT * from movies) as m, (SELECT * FROM genre) as g, (SELECT * FROM resolveGenre) as rg WHERE m.title LIKE '%horror%' OR g.name LIKE 'horror%';


SELECT DISTINCT m.title, g.name FROM (SELECT * from movies) as m, (SELECT * FROM genre) as g, (SELECT * FROM resolveGenre) as rg WHERE m.title = 'a' OR g.name = 'comedy';




INSERT INTO resolvemovies VALUES (DEFAULT,100,100);


SELECT * FROM resolvemovies WHERE movieID=1 AND cinemaid =2;

SELECT name FROM resolveactor r JOIN actor a ON a.actorid = r.actorid WHERE movieid = 15;
