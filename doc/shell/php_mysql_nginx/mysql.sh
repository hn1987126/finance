#!/bin/bash

groupadd mysql
useradd -r -g mysql mysql
rpm -e mysql-libs-5.1.73-3.el6_5.x86_64 --nodeps

cd /root/soft/mysql
tar -vxzf mysql-5.7.18-linux-glibc2.5-x86_64.tar.gz
mv mysql-5.7.18-linux-glibc2.5-x86_64 /usr/local/mysql
cd /usr/local/
chown -R mysql mysql/
chgrp -R mysql mysql/
cd /usr/local/mysql
bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data
# 密码为随机的 ?(g&BrocX6%P 或cat /root/.mysql_secret这样去看密码
bin/mysql_ssl_rsa_setup  --datadir=/usr/local/mysql/data
cp -a support-files/mysql.server  /etc/init.d/mysqld

#貌似这些都可以不需要
#mkdir /var/lib/mysql
# 修改配置文件  把里面的datadir 改为  datadir = /usr/local/mysql/data
#sed -i "/^datadir=/c\datadir=/usr/local/mysql/data" /etc/my.cnf
#sed -i "/^pid-file=/c\pid-file=/var/lib/mysql/mysqld.pid" /etc/my.cnf
#ln -s /var/lib/mysql/mysql.sock /tmp/mysql.sock
#如果不能启动解决办法：
#rm -rf /tmp/mysql.*


# 修改密码
# 启动mysql
#bin/mysqld_safe --user=mysql --skip-grant-tables&
# 进入mysql命令行
#bin/mysql -uroot -p
# 在mysql命令中输入  
#use mysql;
#update user set authentication_string=password('1qaz@WSX123') where user='root';
#grant all privileges on *.* to root@'%' identified by '1qaz@WSX123';
#flush privileges;
# 重启与开机启动
#/etc/init.d/mysqld restart



chkconfig --level 35 mysqld on
