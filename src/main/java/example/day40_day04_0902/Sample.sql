DROP DATABASE if EXISTS mydb0902;
CREATE DATABASE mydb0902;
use mydb0902;

CREATE Table exam(
    eno INT AUTO_INCREMENT PRIMARY KEY,
    ename VARCHAR(255)
);
INSERT INTO exam(ename) VALUES('유재석');
INSERT INTO exam(ename) VALUES('강호동');
INSERT INTO exam(ename) VALUES('신동엽');