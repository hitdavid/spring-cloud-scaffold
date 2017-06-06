#!/usr/bin/env bash

cd eureka
./mvnw clean package

cd ../rest-api/
./mvnw clean package

cd ../zuul/
./mvnw clean package