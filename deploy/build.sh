#!/bin/bash
# 正常情况下源码和实际运行的jar不能放在一起
# 可以考虑构建完成后清除源代码和Git

PWD=`pwd`
cd ..

mvn clean package -Dmaven.test.skip=true

cd $PWD