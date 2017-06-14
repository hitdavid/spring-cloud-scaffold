#!/usr/bin/env bash

./mvnw clean package
cp ./target/jar_bin.jar ./docker/jar_bin.jar
cd docker
docker build -t hitdavid/zuul .
docker ps | grep zuul | awk '{print $1}' | xargs docker stop
docker run -d -p 8000:8000 hitdavid/zuul
