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


SELECT * FROM users WHERE username = 'ste';

DELETE from users;

SELECT HashBytes('MD5','admin');


UPDATE users SET role = 'admin' WHERE username = 'admin';

SELECT name from resolvegenre r JOIN genre g ON g.genreid = r.genreid WHERE movieid = 7;