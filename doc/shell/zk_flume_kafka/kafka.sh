#!/bin/bash

# 安装kafka
cd /root
tar -zxvf soft/kafka_2.11-0.8.2.2.tgz -C apps
cd apps
mv kafka_2.11-0.8.2.2 kafka
mkdir -p /export/servers/logs

#### 配置文件
cp kafka/config/server.properties kafka/config/server.properties.bak
cat > kafka/config/server.properties << EOF
#broker的全局唯一编号，不能重复
broker.id=0
#用来监听连接的端口，producer或consumer将在此端口建立连接
port=9092
#处理网络请求的线程数量
num.network.threads=3
#用来处理磁盘IO的现成数量
num.io.threads=8
#发送套接字的缓冲区大小
socket.send.buffer.bytes=102400
#接受套接字的缓冲区大小
socket.receive.buffer.bytes=102400
#请求套接字的缓冲区大小
socket.request.max.bytes=104857600
#kafka运行日志存放的路径
log.dirs=/export/servers/logs/kafka
#topic在当前broker上的分片个数
num.partitions=2
#用来恢复和清理data下数据的线程数量
num.recovery.threads.per.data.dir=1
#segment文件保留的最长时间，超时将被删除，7天
log.retention.hours=168
#滚动生成新的segment文件的最大时间
log.roll.hours=168
#zookeeper
zookeeper.connect=s1:2181,s2:2181,s3:2181
zookeeper.connection.timeout.ms=6000
EOF

#### 分发安装包
SERVERS=(
	2
	3
)
i=1
for SERVERNUM in ${SERVERS[@]}
do
	cd /root/apps
	SERVER="s${SERVERNUM}"
	scp -r kafka $SERVER:/root/apps
	ssh $SERVER "mkdir -p /export/servers/logs"
	#修改别的机器的 broker.id，分别为0，1，2，3等
	exe="/^broker.id=/c\broker.id=${i}"
	ssh $SERVER "sed -i ${exe} /root/apps/kafka/config/server.properties"
	let i+=1
done

#启动本机和目标机器上的kafka服务
nohup /root/apps/kafka/bin/kafka-server-start.sh  /root/apps/kafka/config/server.properties >/dev/null 2>&1 &
for SERVERNUM in ${SERVERS[@]}
do
	SERVER="s${SERVERNUM}"
	ssh $SERVER 'nohup /root/apps/kafka/bin/kafka-server-start.sh  /root/apps/kafka/config/server.properties >/dev/null 2>&1 &'
done
#测试
#/root/apps/kafka/bin/kafka-topics.sh --list --zookeeper s1:2181
#/root/apps/kafka/bin/kafka-console-consumer.sh --zookeeper s1:2181 --from-beginning --topic wifi6
#查看分区情况
#/root/apps/kafka/bin/kafka-topics.sh --describe --zookeeper s1:2181
#新建主题
#/root/apps/kafka/bin/kafka-topics.sh --create --zookeeper s1:2181 --replication-factor 2 --partitions 4 --topic wifi7
#kafka增加分区
#/root/apps/kafka/bin/kafka-topics.sh --alter --topic wifi7 --zookeeper s1:2181 --partitions 6