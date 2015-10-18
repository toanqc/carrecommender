-- CARS
CREATE SCHEMA carrecommender;
USE carrecommender;

CREATE TABLE car (id bigint, cid int, name string, make string, model string, year int, mpg string, transmission string, doors tinyint, submodel string, msrp int, rating string, cusumer_rating string, photo string);
CREATE TABLE make (id bigint, name string);
CREATE TABLE model (id bigint, make string, model string);
CREATE TABLE tracker (id bigint, userid bigint, cartrace string);
CREATE TABLE user (id bigint, name string, email string);
CREATE TABLE year (id bigint, year int);

LOAD DATA INPATH '/user/qct/car.csv' OVERWRITE INTO TABLE car;
LOAD DATA INPATH '/user/qct/make.csv' OVERWRITE INTO TABLE make;
LOAD DATA INPATH '/user/qct/model.csv' OVERWRITE INTO TABLE model;
LOAD DATA INPATH '/user/qct/year.csv' OVERWRITE INTO TABLE year;