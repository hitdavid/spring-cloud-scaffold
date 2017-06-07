#!/bin/bash

java -server -jar /usr/local/app/jar_bin.jar --server.port=8761 --eureka.client.service-url.defaultZone='http://'${HOST}':'${PORT}'/eureka/'
