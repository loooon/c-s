USE mysql;

DELETE FROM user WHERE user = 'root' and host <> 'localhost';
DELETE FROM user WHERE user ='';
flush privileges;