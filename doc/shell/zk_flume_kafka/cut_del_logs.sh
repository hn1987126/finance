#!/bin/bash
#初始化
LOGS_PATH=/export/wwwlogs
HISTORY_LOGS_PATH=/export/wwwhistorylogs
if [ ! -d $HISTORY_LOGS_PATH ]; then
  mkdir $HISTORY_LOGS_PATH
fi

#按每天切割
YESTERDAY=$(date -d "yesterday" +%Y%m%d)

# 取所有日志
cd ${LOGS_PATH}
ls flume.access.log* > ls.list
for f in `cat ls.list`
do
	mv $f ${HISTORY_LOGS_PATH}/${YESTERDAY}_$f
done
rm ls.list

#向nginx主进程发送USR1信号，重新打开日志文件，否则会继续往mv后的文件写数据的。原因在于：linux系统中，内核是根据文件描述符来找文件的。如果不这样操作导致日志切割失败。
#kill -USR1 `ps axu | grep "nginx: master process" | grep -v grep | awk '{print $2}'`
rm -f /root/wifi_gateway/wifi_gateway.log
pkill -f wifi_gateway-1.0.0.jar
. /etc/profile;nohup java -jar /root/wifi_gateway/wifi_gateway-1.0.0.jar >/root/wifi_gateway/wifi_gateway.log 2>&1 &
#删除7天前的日志
#cd ${LOGS_PATH}
#find . -mtime +7 -name "flume.access_*" | xargs rm -f
exit 0



#每天零点切割
#0 0 * * * /bin/sh /root/apps/flume/conf/cut_del_logs.sh
#每分钟切割一次
#* */1 * * * /bin/sh /root/apps/flume/conf/cut_del_logs.sh
#curl http://172.27.17.113?mac=18:5e:0f:be:87:a5\&rssi=-70\&lat=25.555\&lon=30.334