prepare and download jdk-8u131-linux-x64.tar.gz to this folder first!!!
 
docker build -t hitdavid/api-gateway .

docker tag hitdavid/api-gateway registry.gzq.chanjet.com/hitdavid/api-gateway:2017.06.15.001

docker push registry.gzq.chanjet.com/hitdavid/api-gateway:2017.06.15.001 

