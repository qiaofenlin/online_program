# docker

## 基本概念

* docker 主机
* docker客户端
* docker仓库
* docker镜像
* docker容器

## 容器操作dc
```text
docker run -d -p 8888:8080 tomcat 
-d 后台运行
-p 将主机的端口映射到容器的端口 主机端口：容器内部端口

docker logs ***

mysql 启动：  docker run -p 3307:3306 --name mysql011 -e MYSQL_ROOT_PASSWORD=123456 -d mysql --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

```