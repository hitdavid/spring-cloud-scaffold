#!/usr/bin/env bash

./mvnw clean package
rm -f ./docker/jar_bin.jar
cp ./target/jar_bin.jar ./docker/jar_bin.jar
cd docker
docker build -t hitdavid/zuul-manager .
docker ps | grep 0.0.0.0:8001 | awk '{print $1}' | xargs docker stop
docker run -d -p 8001:8001 hitdavid/zuul-manager
