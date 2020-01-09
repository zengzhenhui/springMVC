#! /bin/bash
env=$1
path=`dirname $0`
cd $path
mvn clean package
docker rm -fv jgm
docker run --restart=always --name=jgm -p 6000:8080 \
    -v /home/service/jgm:/home/tomcat/src/demo \
    -v /home/service/jgm/logs:/home/tomcat/logs \
    -ti -d docker.listcloud.cn:5000/maven /home/tomcat/src/demo/run.sh $env
