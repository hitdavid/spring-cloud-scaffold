#!/bin/bash

LC_ALL=C ifconfig  | grep 'inet addr:'| grep -v '127.0.0.1' | grep -v ".1 " | cut -d: -f2|awk '{print $1}' | xagrs java -server -jar /usr/local/app/jar_bin.jar --server.port=8000 --eureka.instance.ip-address:
