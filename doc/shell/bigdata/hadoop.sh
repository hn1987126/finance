#!/bin/bash

# 安装hadoop
cd /root
if [ ! -d "/apps" ]; then
  mkdir apps
fi

tar -vxzf hadoop-2.6.4.tar.gz -C apps
cd apps/hadoop-2.6.4
rm -rf share/doc/*
cd etc/hadoop
#修改配置文件，一共4个
vim hadoop-env.sh   修改里面有个export JAVA_HOME 把他修改成自己的jdk目录
vim core-site.xml   核心配置
vim hdfs-site.xml
vim mapred-site.xml.example
vim yarn-site.xml

在core-site.xml中<configuration>里加入：
<property>
<name>fs.defaultFS</name>
<value>hdfs://s1:9000</value>
</property>
<property>
<name>hadoop.tmp.dir</name>
<value>/home/hadoop/hdpdata</value>
</property>


在hdfs-site.xml中<configuration>里加入：
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
<value>/home/hadoop/name1,/home/hadoop/name2</value>
</property>


mv mapred-site.xml.example vim mapred-site.xml
<property>
<name>mapreduce.framework.name</name>
<value>yarn</value>
</property>

在yarn-site.xml中<configuration>里加入：
<property>
<name>yarn.resourcemanager.hostname</name>
<value>s1</value>
</property>
<property>
<name>yarn.nodemanager.aux-services</name>
<value>mapreduce_shuffle</value>
</property>

#将hadoop添加到环境变量：
sudo vim /etc/profile
export HADOOP_HOME=/home/hadoop/apps/hadoop-2.6.4
export PATH=$PATH:$JAVA_HOME/bin:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
拷到其他机器
sudo scp /etc/profile s2:/etc/
sudo scp /etc/profile s3:/etc/
sudo scp /etc/profile s4:/etc/


#格式化namenode
hadoop namenode -format
怎样算格式化成功呢？
-执行完的时候有提示 /home/hadoop/hdpdata/dfs/name has been successfully formatted.

#启动：
/home/hadoop/apps/hadoop-2.6.4/sbin/hadoop-daemon.sh start namenode
也可以直接hadoop-daemon.sh start namenode ,因为已经加了环境变量把sbin加进去了
然后用jps查看看是否有NameNode
通过网页访问(hadoop里内置了一个小的web应用服务器[jetty服务器]，他的默认端口是50070,http://s1:50070)
查看当前有多少空间，通过点击上方的overview标签查，地址为http://s1:50070/dfshealth.html#tab-overview


#####hadoop用户的免密登录
ssh-kengen
ssh-copy-id -i ~/.ssh/id_rsa.pub s1
ssh-copy-id -i ~/.ssh/id_rsa.pub s2
ssh-copy-id -i ~/.ssh/id_rsa.pub s3
ssh-copy-id -i ~/.ssh/id_rsa.pub s4

#把hadoop放到其他机器
cd /home/hadoop
tar -vczf apps.tar.gz apps
scp /home/hadoop/apps.tar.gz s2:/home/hadoop
ssh s2 tar -vxzf /home/hadoop/apps.tar.gz
scp /home/hadoop/apps.tar.gz s3:/home/hadoop
ssh s3 tar -vxzf /home/hadoop/apps.tar.gz
scp /home/hadoop/apps.tar.gz s4:/home/hadoop
ssh s4 tar -vxzf /home/hadoop/apps.tar.gz

#启动datanode，在其他机器上
hadoop-daemon.sh start datanode
hadoop-daemon.sh stop datanode  停止服务
#其他机器的datanode是怎么跟本机的namenode握手的呢？是因为hadoop目录连同配置文件一起拷过去了，他们都认识namenode为s1
日志文件是在：/home/hadoop/apps/hadoop-2.6.4/logs/目录下的 那个.log文件，而不是.out文件
namenode机器上jps查看，有namenode,还有个secondaryNameNode(这个就是想本机的0.0.0.0,这个慢，也可以在hdfs-site.xml中加dfs.secondary.http.address这个属性为s1:50090)
而datanode机器上jps查看只有datanode

#批量启动：
sbin/start-all.sh起所有(不建议)   start-dsf.sh只起dsf     start-yarn.sh只起yarn
批量启动哪些？在etc/hadoop/slaves中，这文件纯粹是给批量启动用的
vim slaves里加入（默认里面有个localhost，把这个去掉，改成底下的）
s2
s3
s4

#批量停止
sbin/stop-dsf.sh

#启动yarn
sbin/yarn-daemon.sh start resourcemanager
其他机器
sbin/yarn-daemon.sh start nodemanager