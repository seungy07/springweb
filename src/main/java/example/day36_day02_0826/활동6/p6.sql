DROP DATABASE IF EXISTS mydb0826;
CREATE DATABASE mydb0826;
USE mydb0826;
CREATE TABLE board(  
    number VARCHAR(255) ,
    n VARCHAR(30) ,
    constraint PRIMARY KEY( number ) 
);
insert into board( number, n )values( "010-1234-4567", "2" ),( "010-4744-4567", "20"),( "010-1234-0000", "4");;