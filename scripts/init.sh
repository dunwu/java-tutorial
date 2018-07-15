#!/usr/bin/env bash
#################################################################################
# javatool-server 项目初始化脚本
# 执行本脚本后，会将 JavaStack 下载到 /home/zp/source/JavaStack 目录下。
# 环境中必须安装了 git
#################################################################################

rm -rf /home/temp
rm -rf /home/zp/source/JavaStack
mkdir -p /home/temp
cd /home/temp
wget https://raw.githubusercontent.com/dunwu/JavaStack/master/scripts/git-clone.sh
chmod 777 git-clone.sh
./git-clone.sh JavaStack master
chmod 777 -R /home/zp/source/JavaStack
rm -rf /home/temp
