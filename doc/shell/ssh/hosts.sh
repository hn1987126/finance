#!/bin/bash

hosts=""

IFS=","
arr=($1)
for s in ${arr[@]}
do
        iphost=$s
        IFS=":"
        iphost=($iphost)
        ip=${iphost[0]}
	#注释此hosts文件中此ip之前的设置
	sed -i "s/^$ip/#$ip/g" /etc/hosts
        host=${iphost[1]}
	host=${host//'..'/' '}
        hosts=${hosts}${ip}" "${host}"\n"
        #统一时区
        ssh $host 'cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime'
done

echo -e $hosts >> /etc/hosts