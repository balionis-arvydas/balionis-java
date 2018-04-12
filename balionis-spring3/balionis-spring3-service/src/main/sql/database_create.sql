/*
Database: MySQL 5.1
*/

-- Create database section -------------------------------------------------

CREATE DATABASE mydb1;

GRANT ALL PRIVILEGES ON mydb1.* TO 'mydba1'@'localhost' IDENTIFIED BY 'mydba1_pwd'  WITH GRANT OPTION;

GRANT SELECT,UPDATE,DELETE,INSERT ON mydb1.* TO 'myapp1'@'localhost' IDENTIFIED BY 'myapp1_pwd';

GRANT SELECT ON mydb1.* TO 'myusr1'@'localhost' IDENTIFIED BY 'myusr1_pwd';

SELECT * FROM mysql.USER;
