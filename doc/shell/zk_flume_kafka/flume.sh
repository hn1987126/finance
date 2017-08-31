#!/bin/bash

#安装flume
cd /root
tar -vxzf soft/apache-flume-1.6.0-bin.tar.gz -C apps
cd apps
mv apache-flume-1.6.0-bin flume
cd flume/conf

cat >> exe2kafka.conf << EOF
# Name the components on this agent
a1.sources = r1
a1.sinks = k1
a1.channels = c1

# Describe/configure the source
a1.sources.r1.type = exec
a1.sources.r1.command = tail -F /export/wwwlogs/flume.access.log
a1.sources.r1.channels = c1
# 下面三行是用来重启的
a1.sources.r1.restartThrottle = 10000
a1.sources.r1.restart = true
a1.sources.r1.logStdErr = true

# Use a channel which buffers events in memory
a1.channels.c1.type = memory
a1.channels.c1.capacity = 1000
a1.channels.c1.transactionCapacity = 100
a1.channels.c1.keep-alive = 100

# 随机平均分区
a1.sources.r1.interceptors = i1
a1.sources.r1.interceptors.i1.type = org.apache.flume.sink.solr.morphline.UUIDInterceptor$Builder
a1.sources.r1.interceptors.i1.headerName = key
a1.sources.r1.interceptors.i1.preserveExisting = false

#a1.sources.r1.interceptors = i1
#a1.sources.r1.interceptors.i1.type = regex_extractor
#a1.sources.r1.interceptors.i1.regex = .*?\"mac\"\:(.*?)\,.*
#a1.sources.r1.interceptors.i1.serializers = s1
#a1.sources.r1.interceptors.i1.serializers.s1.name = key


a1.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink
a1.sinks.k1.topic = wifi7
a1.sinks.k1.brokerList = 116.196.88.60:9092,116.196.88.61:9092,116.196.88.62:9092
#a1.sinks.k1.brokerList = 116.196.88.60:9092,116.196.88.62:9092
a1.sinks.k1.metadata.borker.list = 116.196.88.60:9092,116.196.88.61:9092,116.196.88.62:9092
#a1.sinks.k1.metadata.borker.list = 116.196.88.60:9092,116.196.88.62:9092
a1.sinks.k1.requiredAcks = 1
a1.sinks.k1.batchSize = 20
a1.sinks.k1.channel = c1
a1.sinks.k1.request-timeout = 100000
a1.sinks.k1.callTimeout = 100000
a1.sinks.k1.connect-timeout = 8000
#a1.sinks.k1.kafka.partitioner.class = com.lxw1234.flume17.SimplePartitioner
EOF

#启动flume
/root/apps/flume/bin/flume-ng agent --conf /root/apps/flume/conf --conf-file /root/apps/flume/conf/exe2kafka.conf --name a1 -Dflume.root.logger=INFO,console &
