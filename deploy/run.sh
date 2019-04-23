#!/bin/bash

PWD=`pwd`
cd ..

# 控制运行日志
# runlog="/tmp/java-staging.running.`date '+%Y-%m-%d-%H:%M:%S'`.log"
runlog="/dev/null"
echo "run log: $runlog"

# 默认profile
if [ -z $SPRING_PROFILES_ACTIVE ]; then
    SPRING_PROFILES_ACTIVE="dev"
fi

# 虚拟机参数
JAVA_OPTS="
-server -Xms1g -Xmx1g -XX:NewRatio=3
-XX:ReservedCodeCacheSize=128m -XX:InitialCodeCacheSize=128m -XX:ParallelCMSThreads=8
-XX:+CMSParallelRemarkEnabled
-XX:+ExplicitGCInvokesConcurrent
-XX:+CMSClassUnloadingEnabled
-XX:+CMSClassUnloadingEnabled
-XX:+DisableExplicitGC
-XX:+PrintGCDetails
-XX:+PrintHeapAtGC
-XX:+PrintTenuringDistribution
-XX:+UseConcMarkSweepGC
-XX:+PrintGCTimeStamps
-XX:+PrintGCDateStamps
-XX:CMSFullGCsBeforeCompaction=0
-XX:+UseCMSCompactAtFullCollection
-XX:CMSInitiatingOccupancyFraction=80
-XX:-OmitStackTraceInFastThrow
-XX:+HeapDumpOnOutOfMemoryError
-Dfile.encoding=UTF-8
-Djava.net.preferIPv4Stack=true
-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}"
echo "JAVA_OPTS: $JAVA_OPTS"

exec nohup java $JAVA_OPTS -jar target/java-staging.jar >> $runlog 2>&1 &

cd $PWD