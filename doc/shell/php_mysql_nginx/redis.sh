#!/bin/bash

cd /root/soft/redis
tar -vxzf redis-3.2.9.tar.gz
cd redis-3.2.9
make
cd src
make install
mkdir -p /usr/local/redis/bin
mkdir -p /usr/local/redis/etc
mv /root/soft/redis/redis-3.2.9/redis.conf /usr/local/redis/etc
mv redis-benchmark redis-check-aof redis-cli redis-server /usr/local/redis/bin
sed -i "/^datadir=/c\datadir=/usr/local/mysql/data" /etc/my.cnf
#vim /usr/local/redis/etc/redis.conf  把daemonize属性改为yes
#requirepass foobared 前面的注释去掉并把foobared改为如jd.com密码
#启动
/usr/local/bin/redis-server /usr/local/redis/etc/redis.conf &

#安装扩展
#wget -c https://github.com/phpredis/phpredis/archive/php7.zip
#最后放到php.ini文件最后一行 加上  extension="redis.so"
#重启php，需要带上 -c php.ini位置