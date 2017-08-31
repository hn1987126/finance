#!/bin/bash
#运行命令 /bin/bash ssh.sh "192.168.0.64" root@2017 "192.168.0.88"

# 要免密的服务器ip
SERVERS=$1
# 密码
PASSWORD=$2
# console控制台ip
CONSOLE_IP=$3
# 当前脚本所在的位置
THIS_PATH=$(cd `dirname $0`; pwd)

# 安装expect
yum -y install expect
# 用rpm安装，先需要在与linux版本一致的机器上yumdownloader tcl和yumdownloader expect下载下来 
#rpm -ivh tcl-8.5.7-6.el6.x86_64.rpm
#rpm -ivh expect-5.44.1.15-5.el6_4.x86_64.rpm

# 创建id_rsa
auto_ssh-keygen() {
    expect -c "set timeout -1;
        spawn ssh-keygen;
        expect {
            *key* {send -- \r;exp_continue;}
            *passphrase* {send -- \r;exp_continue;}
            eof        {exit 0;}
        }";
}

# id_rsa如果不存在则创建
id_rsa_file="/root/.ssh/id_rsa.pub"
if [ ! -f "$id_rsa_file" ]; then    
	auto_ssh-keygen
else
	echo "本机id_rsa存在，略过自动创建过程";
fi

# 人机交互输入密码
auto_ssh_copy_id() {
    expect -c "set timeout -1;
        spawn ssh-copy-id $1;
        expect {
            *(yes/no)* {send -- yes\r;exp_continue;}
            *assword:* {send -- $2\r;exp_continue;}
            eof        {exit 0;}
        }";
}

# 拷贝id_rsa.pub到目标机器的authorized_keys
hosts=""
i=2
ssh_copy_id_to_all() {
        
	#加上本机hostname
	auto_ssh_copy_id $CONSOLE_IP $PASSWORD
	hostname=`hostname`
	hostname_short=s1
	hosts=${hosts}${CONSOLE_IP}":"${hostname}".."${hostname_short}","
	## 禁用 /etc/ssh/ssh_config 里的 StrictHostKeyChecking 设为no 便不会每次ssh的时候输yes
	sed -i "/^#   StrictHostKeyChecking/c\StrictHostKeyChecking no" /etc/ssh/ssh_config

        IFS=","
        arr=($SERVERS)
        for ip in ${arr[@]}
        do
		#免密登录
                auto_ssh_copy_id $ip $PASSWORD
		hostname=`ssh $ip hostname`
		hostname_short=s$i

		hosts=${hosts}${ip}":"${hostname}".."${hostname_short}","
		let i+=1
        done
}



#修改host和sshd_config
ssh_edit_host(){
	IFS=","
        arr=($SERVERS)
	for ip in ${arr[@]}
        do
		scp ${THIS_PATH}/hosts.sh root@$ip:/root
		ssh root@$ip /bin/bash /root/hosts.sh "$hosts" $CONSOLE_IP
        done

	#修改本机host
	/bin/bash ${THIS_PATH}/hosts.sh "$hosts" $CONSOLE_IP
}


ssh_copy_id_to_all
ssh_edit_host
