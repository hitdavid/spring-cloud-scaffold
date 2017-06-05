## Another scaffold project for spring

CI by travis-ci.org:[![Build Status](https://travis-ci.org/hitdavid/app.svg?branch=master)](https://travis-ci.org/hitdavid/app)

### Why this project exists?
 
 To quickly setup a project using some part of spring technology.
 
### What components are included in this project?
 
 For a demo RESTful service:
 
 * Spring-Boot
 * Spring-Security
 * Spring-JPA
 * Spring-Session
 * Spring-Cache
 * and etc.
 
 are included.
 
 #### eureka
 This project has a folder `eureka` which is the demo code and config of a standalone `eureka` server.
 You can setup a project build and visit via URL [http://127.0.0.1:8761](http://127.0.0.1:8761)
 
 #### Zuul 
 This project has a folder `zuul_ribbon` which is the demo code and config of `zuul_ribbon`.
 You can setup a project build and visit via URL [http://127.0.0.1:8000](http://127.0.0.1:8000)

 #### database and cache
 Use mysql DB, import Database.DDL create tables.
 
 Use redis for cache, start server command: 
  > redis-server --protected-mode no
 
 for local server
 

### some cmd

to run:

mvn clean package spring-boot:run

to debug:

mvn clean package spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"


