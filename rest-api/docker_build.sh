#!/usr/bin/env bash

./mvnw clean package
rm -f ./docker/jar_bin.jar
cp ./target/jar_bin.jar ./docker/jar_bin.jar
cd docker
docker build -t hitdavid/api-gateway-manager .
docker tag hitdavid/api-gateway-manager registry.gzq.chanjet.com/hitdavid/api-gateway-manager:2017.06.15.012
docker push registry.gzq.chanjet.com/hitdavid/api-gateway-manager:2017.06.15.012
