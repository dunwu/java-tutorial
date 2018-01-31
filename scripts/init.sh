#!/usr/bin/env bash
#################################################################################
# javatool-server 项目初始化脚本
# 执行本脚本后，会将 java-stack 下载到 /home/zp/source/java-stack 目录下。
# 环境中必须安装了 git
#################################################################################

rm -rf /home/temp
rm -rf /home/zp/source/java-stack
mkdir -p /home/temp
cd /home/temp
wget https://raw.githubusercontent.com/dunwu/java-stack/master/scripts/git-clone.sh
chmod 777 git-clone.sh
./git-clone.sh java-stack master
chmod 777 /home/zp/source/java-stack
rm -rf /home/temp
