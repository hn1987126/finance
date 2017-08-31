#!/bin/bash

#关防火墙
/etc/init.d/iptables stop
chkconfig iptables off


#安装jdk
cd /root/soft
tar -vxzf jdk-8u131-linux-x64.tar.gz
mv jdk1.8.0_131 /usr/local/java

cat >> /etc/profile << EOF
export JAVA_HOME=/usr/local/java
export PATH=\$PATH:\$JAVA_HOME/bin
EOF

SERVERS=(
	s2
	s3
)
for SERVER in ${SERVERS[@]}
do
	ssh $SERVER "mkdir /root/soft/"
	scp -r /root/soft/jdk-8u131-linux-x64.tar.gz root@$SERVER:/root/soft/
	ssh $SERVER "cd /root/soft;tar -vxzf jdk-8u131-linux-x64.tar.gz"
	ssh $SERVER "mv /root/soft/jdk1.8.0_131 /usr/local/java"
	ssh $SERVER 'cat >> /etc/profile << EOF
export JAVA_HOME=/usr/local/java
export PATH=\$PATH:\$JAVA_HOME/bin
EOF'
done

#如果有老版本的jdk
#rpm -qa|grep java
#卸载
#rpm -e --nodeps jdk-1.5.0_22-fcs
