#!/bin/bash
# 正常情况下源码和实际运行的jar不能放在一起
# 可以考虑构建完成后清除源代码和Git

prof=$profile
[ ! $a ] && export prof=dev
echo "current environment : $prof"

PWD=`pwd`
cd ..

mvn clean -U
mvn package -Dmaven.test.skip=true -P $prof

mv target/java-test*.jar target/java-test.jar

cd $PWD