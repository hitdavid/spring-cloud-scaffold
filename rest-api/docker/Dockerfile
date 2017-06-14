FROM ubuntu:latest
MAINTAINER David hitdavid@gmail.com


RUN mkdir /usr/local/jdk1.8.0_131
COPY jdk-8u131-linux-x64.tar.gz  ./
RUN tar -zxvf ./jdk-8u131-linux-x64.tar.gz -C /usr/local/

RUN ls /usr/local/jdk1.8.0_131

ENV JAVA_HOME /usr/local/jdk1.8.0_131
ENV PATH $JAVA_HOME/bin:$PATH
ENV CLASSPATH .:$JAVA_HOME/lib
ENV HOST 10.1.146.184
ENV PORT 8002

RUN java -version

RUN mkdir /usr/local/app/
COPY ./jar_bin.jar  /usr/local/app/
COPY ./onStart.sh /usr/local/app/
RUN chmod 755 /usr/local/app/onStart.sh

EXPOSE 8080

ENTRYPOINT ["/usr/local/app/onStart.sh"]