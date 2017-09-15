#!/usr/bin/env bash

mvn clean package
rm -f ./docker/jar_bin.jar
cp ./target/jar_bin.jar ./docker/jar_bin.jar
cd docker
docker build -t hitdavid/eureka .
docker tag hitdavid/eureka hitdavid/eureka:2017.09.15.001
docker push hitdavid/eureka:2017.09.15.001