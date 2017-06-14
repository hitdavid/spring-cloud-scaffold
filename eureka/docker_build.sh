#!/usr/bin/env bash

./mvnw clean package
cp ./target/jar_bin.jar ./docker/jar_bin.jar
cd docker
docker build -t hitdavid/eureka .
docker ps | grep 0.0.0.0:8761 | awk '{print $1}' | xargs docker stop
read -p "请输入HOST:" host
read -p "请输入PORT:" port
docker run -d -p 8761:8761 -e HOST="$host" -e PORT="$port" hitdavid/eureka
