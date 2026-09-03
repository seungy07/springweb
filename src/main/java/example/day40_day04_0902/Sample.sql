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

CREATE Table practice2(
    no INT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255),
    writer VARCHAR(10)
);
INSERT into practice2(content,writer) VALUES("내용1","유재석"),("내용2","강호동")

DROP DATABASE if EXISTS mydb0903;
CREATE DATABASE mydb0903;
use mydb0903;