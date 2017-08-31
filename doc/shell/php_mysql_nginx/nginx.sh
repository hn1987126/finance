#!/bin/bash
mkdir -p /root/soft/nginx/tmp
#安装gcc环境
yum -y install gcc gcc-c++ autoconf automake make
yum install gcc* -y

### 安装PCRE库
cd /root/soft/nginx
tar -zxvf pcre-8.39.tar.gz -C tmp
cd tmp/pcre-8.39
./configure
make && make install

### 安装zlib库
cd /root/soft/nginx
tar -zxvf zlib-1.2.11.tar.gz -C tmp
cd tmp/zlib-1.2.11
./configure
make && make install

### 安装ssl
cd /root/soft/nginx
tar -zxvf openssl-1.1.0b.tar.gz -C tmp
cd tmp/openssl-1.1.0b
./config
make && make install

### 安装Nginx
cd /root/soft/nginx
tar -zxvf nginx-1.13.1.tar.gz -C tmp
cd tmp/nginx-1.13.1
groupadd -r nginx
useradd -r -g nginx nginx

./configure \
  --prefix=/usr/local/nginx \
  --sbin-path=/usr/local/nginx/sbin/nginx \
  --conf-path=/usr/local/nginx/nginx.conf \
  --pid-path=/usr/local/nginx/nginx.pid \
  --user=nginx \
  --group=nginx \
  --with-http_ssl_module \
  --with-http_flv_module \
 --with-http_mp4_module  \
 --with-http_stub_status_module \
 --with-http_gzip_static_module \
 --http-client-body-temp-path=/var/tmp/nginx/client/ \
 --http-proxy-temp-path=/var/tmp/nginx/proxy/ \
 --http-fastcgi-temp-path=/var/tmp/nginx/fcgi/ \
 --http-uwsgi-temp-path=/var/tmp/nginx/uwsgi \
 --http-scgi-temp-path=/var/tmp/nginx/scgi \
 --with-pcre=/root/soft/nginx/tmp/pcre-8.39 \
 --with-zlib=/root/soft/nginx/tmp/zlib-1.2.11 \
 --with-openssl=/root/soft/nginx/tmp/openssl-1.1.0b
 
make && make install
mkdir -p /var/tmp/nginx/client
netstat -ano|grep 80
/usr/local/nginx/sbin/nginx