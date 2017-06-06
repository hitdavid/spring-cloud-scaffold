#!/usr/bin/env bash

for pid in $(cat ./pid)
do
    echo ${pid}
    kill -9 ${pid}
done