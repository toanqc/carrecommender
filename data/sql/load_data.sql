-- this is hadoop command to load csv to hdfs
hdfs dfs -put /Users/qct/Documents/Eclipse/workspace/carrecommender/data/csv/car.csv
hdfs dfs -put /Users/qct/Documents/Eclipse/workspace/carrecommender/data/csv/make.csv
hdfs dfs -put /Users/qct/Documents/Eclipse/workspace/carrecommender/data/csv/model.csv
hdfs dfs -put /Users/qct/Documents/Eclipse/workspace/carrecommender/data/csv/year.csv

-- hive command to load data into tables
LOAD DATA INPATH '/user/qct/car.csv' OVERWRITE INTO TABLE car_text;
LOAD DATA INPATH '/user/qct/make.csv' OVERWRITE INTO TABLE make_text;
LOAD DATA INPATH '/user/qct/model.csv' OVERWRITE INTO TABLE model_text;
LOAD DATA INPATH '/user/qct/year.csv' OVERWRITE INTO TABLE year_text;