CREATE DATABASE IF NOT EXISTS libraryapidb;
CREATE USER IF NOT EXISTS 'dbuser'@'localhost' IDENTIFIED BY 'dbuser';
ALTER USER 'dbuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'dbuser';
GRANT ALL PRIVILEGES ON libraryapidb.* TO 'dbuser'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES