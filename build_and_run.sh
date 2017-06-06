#!/usr/bin/env bash

nohup redis-server >>/dev/null &

cd eureka
./mvnw clean package > /dev/null
sleep 5

java -server -jar ./target/jar_bin.jar --server.port=8761 > /dev/null &
echo $! > ../pid

echo 'Eureka Server started! Visit http://127.0.0.1:8761 to visit'

cd ../rest-api/
./mvnw clean package > /dev/null
sleep 5

java -server -jar ./target/jar_bin.jar --server.port=8080 > /dev/null &
echo $! >> ../pid
java -server -jar ./target/jar_bin.jar --server.port=8081 > /dev/null &
echo $! >> ../pid

echo 'Restful api Server started! Visit http://127.0.0.1:8080/hello/world to visit'


cd ../zuul/
./mvnw clean package > /dev/null
sleep 5

java -server -jar ./target/jar_bin.jar --server.port=8000 > /dev/null &
echo $! >> ../pid

echo 'Restful api Server started! Visit http://127.0.0.1:8000/api/server/hello/world to visit'