docker build -t hitdavid/eureka .

docker run -d -p 8001:8761 -e HOST=10.1.146.184 -e PORT=8002 hitdavid/eureka

docker run -d -p 8002:8761 -e HOST=10.1.146.184 -e PORT=8001 hitdavid/eureka