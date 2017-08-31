#!/bin/bash
#运行命令 /bin/bash php.sh /usr/local/php/ /root/soft/php/

TAR_SRC=${2}
cd $TAR_SRC

#源文件目录下创建临时目录
mkdir tmp

ls *.tar.gz > ls.list
for TAR in `cat ls.list`
do
	tar -xzf $TAR -C tmp
done
rm ls.list

cd tmp/libxml2-2.6.30
./configure --prefix=/usr/local/libxml2
make && make install

cd ../libmcrypt-2.5.8
./configure --prefix=/usr/local/libmcrypt
make && make install

cd libltdl/
./configure --enable-ltdl-install
make && make install
cd ../


cd ../zlib-1.2.3
./configure
make && make install

cd ../libpng-1.2.31
./configure --prefix=/usr/local/libpng
make && make install

cd ../jpeg-6b/
mkdir /usr/local/jpeg6
mkdir /usr/local/jpeg6/bin
mkdir /usr/local/jpeg6/lib
mkdir /usr/local/jpeg6/include
mkdir mkdir -p /usr/local/jpeg6/man/man1
./configure --prefix=/usr/local/jpeg6 --enable-shared --enable-statc
make && make install

cd ../freetype-2.3.5
./configure --prefix=/usr/local/freetype
make && make install

cd ../autoconf-2.61
./configure
make && make install

# 安装gd库
cd ../libgd-2.1.1
./configure --prefix=/usr/local/gd2/ --with-jpeg=/usr/local/jpeg6/ --with-freetype=/usr/local/freetype/
make && make install

# 安装php
PHP_PATH=$1
PHP_SRC=${2}tmp/php-7.0.6
if [ ! -d $PHP_PATH ]; then
    mkdir $PHP_PATH
fi

cd ${PHP_SRC}

./configure \
--prefix=/usr/local/php \
--with-libxml-dir=/usr/local/libxml2 \
--with-png-dir=/usr/local/libpng \
--with-freetype-dir=/usr/local/freetype \
--enable-soap \
--enable-mbstring=all \
--enable-sockets \
--enable-fpm \
--enable-zip \
--enable-json \
--with-mysqli=mysqlnd \
--with-pdo-mysql=mysqlnd \
--with-mysql-sock=mysqlnd \
--enable-pdo \
--enable-dom --enable-pcntl


make && make install
cp php.ini-production /usr/local/php/etc/php.ini
cp /usr/local/php/etc/php-fpm.conf.default /usr/local/php/etc/php-fpm.conf
cp /usr/local/php/etc/php-fpm.d/www.conf.default /usr/local/php/etc/php-fpm.d/www.conf
#sed -i "/^user = nobody/c\user = root" /usr/local/php/etc/php-fpm.d/www.conf
#sed -i "/^group = nobody/c\group = root" /usr/local/php/etc/php-fpm.d/www.conf
sed -i '$a\export PATH=$PATH:/usr/local/php/bin:/usr/local/php/sbin' /etc/profile
#启动
#nohup /usr/local/php/sbin/php-fpm -c /usr/local/php/etc/php.ini -R > /dev/null 2>&1


# 安装
#  ./install_php.sh /usr/local/php/ /root/files/

#查看是否有旧版本
#rpm -qa | grep php
# 卸载php5.3.3
#rpm -e php-5.3.3-46.el6_6.x86_64
#rpm -e php-xml-5.3.3-46.el6_6.x86_64
#rpm -e php-fpm-5.3.3-46.el6_6.x86_64
#rpm -e php-mbstring-5.3.3-46.el6_6.x86_64
#rpm -e php-cli-5.3.3-46.el6_6.x86_64
#rpm -e php-common-5.3.3-46.el6_6.x86_64