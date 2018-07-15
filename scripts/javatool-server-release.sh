#################################################################################
# javatool-server 发布脚本
#################################################################################

#
# 检查脚本参数，如必要参数未传入，退出脚本。
#
checkInput() {
  if [ "${branch}" == "" ] || [ "${profile}" == "" ]; then
    echo "请输入脚本参数：branch profile"
    echo "    branch: git分支（必填）。如 develop, master"
    echo "    profile: 运行环境（必填）。可选值：development/test"
    echo "例：./javatool-server-release.sh master test"
    exit 0
  fi
}

#
# 检查文件是否存在，不存在则退出脚本
#
checkFileExist() {
  if [ ! -f "$1" ];then
    echo "关键文件 $1 找不到，脚本执行结束"
    exit 0
  fi
}

#
# 检查目录是否存在，不存在则退出脚本
#
checkFolderExist() {
  if [ ! -d "$1" ];then
    echo "关键目录 $1 找不到，脚本执行结束"
    exit 0
  fi
}

#
# 记录发布的版本信息
#
saveVersionInfo() {
  VERSION_LOG_PATH=/home/zp/log/deploy/version.txt
  if [ ! -f ${VERSION_LOG_PATH} ]; then
    mkdir -p /home/zp/log/deploy
    touch ${VERSION_LOG_PATH}
  fi
  chmod 777 ${VERSION_LOG_PATH}
  sed -i '/'XYZ'/,+3d' ${VERSION_LOG_PATH}
  echo "============ zp javatool-server info ===========" >> ${VERSION_LOG_PATH}
  cd ${SOURCE_PATH}
  echo "Branch is: ${branch}..." >> ${VERSION_LOG_PATH}
  echo "Profile is: ${profile}..." >> ${VERSION_LOG_PATH}
  echo "CommitID is : $(git log --pretty=oneline -1)..." >> ${VERSION_LOG_PATH}
}

######################################## MAIN ########################################
export LANG="zh_CN.UTF-8"

# 设置全局常量
SOURCE_PATH=/home/zp/source/JavaStack
SCRIPT_PATH=/home/zp/source/JavaStack/scripts

# 分配 script 和 config 目录的权限
chmod -R 755 /home/zp/source/JavaStack

# 0. 获取传入参数并检查
branch=`echo $1`
profile=`echo $2`
repository=`echo $3`
checkInput
checkFolderExist ${SOURCE_PATH}
checkFolderExist ${SCRIPT_PATH}
checkFileExist ${SCRIPT_PATH}/git-clone.sh
checkFileExist ${SCRIPT_PATH}/javatool-server-run.sh
checkFileExist ${SCRIPT_PATH}/embed-tomcat-server-boot.sh

# 1. 停止应用
#echo "停止所有 javatool-server 应用开始"
#${SCRIPT_PATH}/javatool-server-run.sh ${profile} stop
#echo "停止所有 javatool-server 应用结束"

# 2. 更新代码
cd ${SOURCE_PATH}
${SCRIPT_PATH}/git-clone.sh JavaStack ${branch}
chmod -R 777 ${SOURCE_PATH}

# 3. 替换配置
#${SCRIPT_PATH}/javatool-server-replace-config.sh ${SOURCE_PATH} ${profile}

# 4. 编译打包
cd ${SOURCE_PATH}/codes/javatool
mvn clean package -Dmaven.test.skip=true

# 5. 启动应用
echo "启动所有 javatool-server 应用开始"
# 手动释放内存
echo 3 > /proc/sys/vm/drop_caches
${SCRIPT_PATH}/javatool-server-run.sh ${profile} start
echo "启动所有 javatool-server 应用结束"

## 6. 记录发布的版本信息
saveVersionInfo
