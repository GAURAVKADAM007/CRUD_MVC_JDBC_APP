DROP DATABASE IF EXISTS CrudDB;
CREATE DATABASE CrudDB;

\c CrudDB

DROP TABLE IF EXISTS student;
CREATE TABLE student(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mobile VARCHAR(100) NOT NULL
);

INSERT INTO student(name,email,mobile) VALUES('Gaurav','gaurav19@gmail.com','9646812787');
INSERT INTO student(name,email,mobile) VALUES('Samarth','samarth29@gmail.com','8646000787');
INSERT INTO student(name,email,mobile) VALUES('Vivek','vivek702@gmail.com','8654912787');
INSERT INTO student(name,email,mobile) VALUES('Sarthak','sarthak90@gmail.com','8945632178');

SELECT * FROM student
