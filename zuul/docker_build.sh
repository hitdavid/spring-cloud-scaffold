#!/usr/bin/env bash

./mvnw clean package
rm -f ./docker/jar_bin.jar
cp ./target/jar_bin.jar ./docker/jar_bin.jar
cd docker
docker build -t hitdavid/api-gateway .



docker ps | grep 0.0.0.0:8000 | awk '{print $1}' | xargs docker stop
LC_ALL=C ifconfig  | grep 'inet addr:'| grep -v '127.0.0.1' | grep -v ".1 " | cut -d: -f2|awk '{print $1}'| xargs -t -I{} docker run -d -p 8000:8000 -e MACHINE_IP={} hitdavid/zuul
