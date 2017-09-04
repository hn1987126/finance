#!/bin/bash

# 安装hadoop
cd /root
if [ ! -d "/apps" ]; then
  mkdir apps
fi

cd soft
tar -vxzf cenos-6.5-hadoop-2.6.4.tar.gz -C ../apps
cd ../apps
ln -s hadoop-2.6.4 hadoop
cd hadoop
rm -rf share/doc/*
cd etc/hadoop


#修改配置文件，一共4个
#vim hadoop-env.sh   修改里面有个export JAVA_HOME 把他修改成自己的jdk目录
sed -i "/^export JAVA_HOME=/c\export JAVA_HOME=/usr/local/java" hadoop-env.sh

#vim core-site.xml   核心配置
sed -i "/^<configuration>/d" core-site.xml
sed -i "/^<\/configuration>/d" core-site.xml

cat >> core-site.xml << EOF
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://s1:9000</value>
    </property>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/root/hdpdata</value>
    </property>
</configuration>
EOF


#vim hdfs-site.xml 在hdfs-site.xml中<configuration>里加入
sed -i "/^<configuration>/d" hdfs-site.xml
sed -i "/^<\/configuration>/d" hdfs-site.xml

cat >> hdfs-site.xml << EOF
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>2</value>
    </property>
    <!--这是为了启动secondaryNameNode时，不从0.0.0.0(本机)上起，从那上起会很慢-->
    <property>
        <name>dfs.secondary.http.address</name>
        <value>s1:50090</value>
    </property>
    <!--设置hdfs的namenode在多块磁盘上，每个同完全相同的东西，这个不是必须的。-->
    <!--datanode也可以指定多块磁盘，设置dfs.data.dir属性，往不同的地方存，并发时效率高，各个磁盘并不存相同的东西，只是为了方便写操作-->
    <property>
        <name>dfs.name.dir</name>
        <value>/root/hdpdata/name1,/root/hdpdata/name2</value>
    </property>

</configuration>
EOF

#vim mapred-site.xml.template
mv mapred-site.xml.template mapred-site.xml

sed -i "/^<configuration>/d" mapred-site.xml
sed -i "/^<\/configuration>/d" mapred-site.xml

cat >> mapred-site.xml << EOF
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>
EOF

#vim yarn-site.xml 在yarn-site.xml中<configuration>里加入：
sed -i "/^<configuration>/d" yarn-site.xml
sed -i "/^<\/configuration>/d" yarn-site.xml

cat >> yarn-site.xml << EOF
<configuration>
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>s1</value>
    </property>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
</configuration>
EOF


#将hadoop添加到环境变量：

cat >> /etc/profile << EOF
export HADOOP_HOME=/root/apps/hadoop
export PATH=\$PATH:\$JAVA_HOME/bin:\$HADOOP_HOME/bin:\$HADOOP_HOME/sbin
EOF


#格式化namenode
/root/apps/hadoop/bin/hadoop namenode -format
#怎样算格式化成功呢？
#-执行完的时候有提示 /root/hdpdata/dfs/name has been successfully formatted.

#启动：
#/home/hadoop/apps/hadoop-2.6.4/sbin/hadoop-daemon.sh start namenode
#也可以直接hadoop-daemon.sh start namenode ,因为已经加了环境变量把sbin加进去了
#然后用jps查看看是否有NameNode
#通过网页访问(hadoop里内置了一个小的web应用服务器[jetty服务器]，他的默认端口是50070,http://s1:50070)
#查看当前有多少空间，通过点击上方的overview标签查，地址为http://s1:50070/dfshealth.html#tab-overview



#把hadoop放到其他机器
cd /root/apps
tar -vczf hadoop-2.6.4.tar.gz hadoop-2.6.4
SERVERS=(
	s2
	s3
)
for SERVER in ${SERVERS[@]}
do
	ssh $SERVER "mkdir -h /root/apps/"
    scp -r hadoop-2.6.4.tar.gz root@$SERVER:/root/apps/
	ssh $SERVER "cd /root/apps;tar -vxzf hadoop-2.6.4.tar.gz;ln -s hadoop-2.6.4 hadoop;rm -rf hadoop-2.6.4.tar.gz;"
	ssh $SERVER 'cat >> /etc/profile << EOF
export HADOOP_HOME=/root/apps/hadoop
export PATH=\$PATH:\$JAVA_HOME/bin:\$HADOOP_HOME/bin:\$HADOOP_HOME/sbin
EOF'
done

rm -rf /root/apps/hadoop-2.6.4.tar.gz


#启动datanode，在其他机器上
#/root/apps/hadoop/sbin/hadoop-daemon.sh start datanode
#/root/apps/hadoop/sbin/hadoop-daemon.sh stop datanode  停止服务
#其他机器的datanode是怎么跟本机的namenode握手的呢？是因为hadoop目录连同配置文件一起拷过去了，他们都认识namenode为s1

#批量启动：
#sbin/start-all.sh起所有(不建议)   start-dsf.sh只起dsf     start-yarn.sh只起yarn
#批量启动哪些？在etc/hadoop/slaves中，这文件纯粹是给批量启动用的
#vim slaves里加入（默认里面有个localhost，把这个去掉，改成底下的）

sed -i "/^localhost/d" /root/apps/hadoop/etc/hadoop/slaves
cat >> /root/apps/hadoop/etc/hadoop/slaves << EOF
s1
s2
s3
EOF

#批量停止
#sbin/stop-dsf.sh

#启动
/root/apps/hadoop/sbin/start-all.sh