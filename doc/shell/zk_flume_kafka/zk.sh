 #!/bin/bash


#安装zookeeper
cd /root
mkdir apps
tar -vxzf soft/zookeeper-3.4.5.tar.gz -C apps
cd apps/zookeeper-3.4.5
rm -rf src/ *.xml *.txt docs dist-maven
cd conf
cp zoo_sample.cfg zoo.cfg

mkdir -p /root/zkdata
sed -i "/^dataDir=/c\dataDir=/root/zkdata" zoo.cfg
cat >> zoo.cfg << EOF
server.1=s1:2888:3888
server.2=s2:2888:3888
server.3=s3:2888:3888
EOF

cd /root/zkdata
echo 1 >myid

#加环境变量
cat >> /etc/profile << EOF
export ZK_HOME=/root/apps/zookeeper-3.4.5
export PATH=\$PATH:\$ZK_HOME/bin
EOF
source /etc/profile

#---------------拷到其他机器中
SERVERS=(
	2
	3
)
for SERVERNUM in ${SERVERS[@]}
do
	cd /root/apps
	SERVER="s${SERVERNUM}"
	ssh $SERVER "mkdir -p /root/apps"
	scp -r zookeeper-3.4.5 $SERVER:/root/apps/zookeeper-3.4.5
	ssh $SERVER "mkdir -p /root/zkdata"
	ssh $SERVER "echo ${SERVERNUM} >/root/zkdata/myid"
	ssh $SERVER 'cat >> /etc/profile << EOF
export ZK_HOME=/root/apps/zookeeper-3.4.5
export PATH=\$PATH:\$ZK_HOME/bin
EOF'
	ssh $SERVER "source /etc/profile"
done


#启动服务
/root/apps/zookeeper-3.4.5/bin/zkServer.sh start
for SERVERNUM in ${SERVERS[@]}
do
	SERVER="s${SERVERNUM}"
	ssh $SERVER ". /etc/profile;/root/apps/zookeeper-3.4.5/bin/zkServer.sh start"
done
#查看自己是不是leader
#/root/apps/apps/zookeeper-3.4.5/bin/zkServer.sh status
#必须要里面显示leader或follower才证明是成功
#用jps查看  能看到 QuorumPeerMain



