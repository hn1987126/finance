### hive装一台或多台都行，不需要集群，连同一个mysql就行。

cd /root/soft
tar -vxzf apache-hive-1.2.1-bin.tar.gz -C ../apps
cd ../apps
ln -s apache-hive-1.2.1-bin hive

cd hive/conf
touch hive-site.xml
cat >> hive-site.xml << EOF
<configuration>
    <property>
    <name>javax.jdo.option.ConnectionURL</name>
    <value>jdbc:mysql://localhost:3306/hive?createDatabaseIfNotExist=true</value>
    <description>JDBC connect string for a JDBC metastore</description>
    </property>

    <property>
    <name>javax.jdo.option.ConnectionDriverName</name>
    <value>com.mysql.jdbc.Driver</value>
    <description>Driver class name for a JDBC metastore</description>
    </property>

    <property>
    <name>javax.jdo.option.ConnectionUserName</name>
    <value>root</value>
    <description>username to use against metastore database</description>
    </property>

    <property>
    <name>javax.jdo.option.ConnectionPassword</name>
    <value>123456</value>
    <description>password to use against metastore database</description>
    </property>
</configuration>
EOF

#### 还需要把mysql-connector-java-5.1.28.jar包放到 hive/lib目录下
cp /root/soft/mysql-connector-java-5.1.28.jar /root/apps/hive/lib

#4、启动
/home/hadoop/apps/hive/bin/hive