#!/bin/bash

PWD=`pwd`
cd ..

# 控制运行日志, 可以直接观察log4j的日志, 这里直接将日志丢弃
runlog=/dev/null
echo "run log : $runlog"

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
-Djava.net.preferIPv4Stack=true"
echo "JAVA_OPTS : $JAVA_OPTS"

# 主函数参数
MAIN_CLASS_ARGS="port=8080"
echo "main class args : $MAIN_CLASS_ARGS"

exec java $JAVA_OPTS -jar target/java-test.jar $MAIN_CLASS_ARGS >> $runlog 2>&1

cd $PWD