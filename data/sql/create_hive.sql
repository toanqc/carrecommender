-- Step to execute

-- 1. create schema then use this schema
-- 2. drop all tables
-- 3. create all text table
-- 4. load all tables by csv file (load_data.sql)
-- 5. create all orc tables by text tables

CREATE SCHEMA carrecommender;
USE carrecommender;

DROP TABLE car;
DROP TABLE make;
DROP TABLE model;
DROP TABLE tracker;
DROP TABLE `user`;
DROP TABLE year;

DROP TABLE car_text;
DROP TABLE make_text;
DROP TABLE model_text;
DROP TABLE tracker_text;
DROP TABLE user_text;
DROP TABLE year_text;


CREATE TABLE car_text (cid bigint, name string, make string, model string, year int, mpg string, transmission string, doors tinyint, submodel string, msrp int, rating string, customer_rating string, photo string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;
CREATE TABLE make_text (id bigint, name string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;
CREATE TABLE model_text (id bigint, make string, model string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;
CREATE TABLE tracker_text (id bigint, userid bigint, cartrace string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;
CREATE TABLE user_text (id bigint, name string, email string, recommendation string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;
CREATE TABLE year_text (id bigint, year int) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;


CREATE TABLE car STORED AS ORC AS SELECT * FROM car_text;
CREATE TABLE make STORED AS ORC AS SELECT * FROM make_text;
CREATE TABLE model STORED AS ORC AS SELECT * FROM model_text;
CREATE TABLE tracker STORED AS ORC AS SELECT * FROM tracker_text;
CREATE TABLE `user` STORED AS ORC AS SELECT * FROM user_text;
CREATE TABLE year STORED AS ORC AS SELECT * FROM year_text;