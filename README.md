##Another scaffold project for spring

[![Build Status](https://travis-ci.org/hitdavid/app.svg?branch=master)](https://travis-ci.org/hitdavid/app)


### cmd

redis-server --protected-mode no

mvn clean package spring-boot:run

mvn clean package spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"


