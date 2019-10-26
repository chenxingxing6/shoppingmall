## 安装MongoDB

docker pull mongo   
docker run -p 27017:27017 -v $PWD/db:/data/db -d mongo
