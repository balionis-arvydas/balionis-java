/*
Database: MySQL 5.1
*/

-- Drop database section -------------------------------------------------

DROP USER 'mydba1'@'localhost';
DROP USER 'myapp1'@'localhost';
DROP USER 'myusr1'@'localhost';

DROP DATABASE mydb1;

SELECT * FROM mysql.USER;
