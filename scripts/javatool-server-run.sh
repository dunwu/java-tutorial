#################################################################################
# 渠道 athena 启动脚本
#################################################################################

# 检查脚本参数，如必要参数未传入，退出脚本。
checkInput() {
  if [ "${profile}" == "" ] || [ "${oper}" == "" ]; then
    echo "请输入脚本参数：profile oper [debug]"
    echo "    profile: 运行环境（必填）。可选值：development|test"
    echo "    oper: 运行环境（必填）。可选值：start|stop|restart"
    echo "    debug: debug启动开关。默认不填为不启动。"
    exit 0
  fi
}

#检查文件是否存在，不存在则退出脚本
checkFileExist() {
  if [ ! -f "$1" ]
  then
    echo "关键文件 $1 找不到，脚本执行结束"
    exit 0
  fi
}

# 封装启动参数，调用启动脚本
execBootScript(){
  APP_NAME=javatool-server

  # JVM 参数
  # JAVA_OPTS=" -Ddubbo.resolve.file=${RESOURCES_PATH}/dubbo/dubbo-resolve.properties -Djava.awt.headless=true -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Ddubbo.shutdown.hook=true -Dspring.profiles.active=${profile} -Djava.security.egd=file:/dev/./urandom -Xms1024m -Xmx1024m -Xss2m "
  JAVA_OPTS=" -Djava.awt.headless=true -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dspring.profiles.active=${profile} -Xms1024m -Xmx1024m -Xss2m "
  JAVA_DEBUG_OPTS=""
  if [ "$3" == "debug" ]; then
      JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=2235,server=y,suspend=n "
      shift
  fi
  javaArgs=" ${JAVA_OPTS} ${JAVA_DEBUG_OPTS} "

  # classpath 参数
  classpathArgs="${COMPILED_WEBAPP}/WEB-INF/classes:${COMPILED_WEBAPP}/WEB-INF/lib/*"

  # 启动类
  bootstrapClass="io.github.dunwu.javatool.server.TomcatServer"

  ${SCRIPT_PATH}/embed-tomcat-server-boot.sh ${APP_NAME} ${oper} "${javaArgs}" "${classpathArgs}" "${bootstrapClass}"
}

######################################## MAIN ########################################
# 获取输入参数
profile=$1
oper=$2
debug=$3
checkInput

# 设置环境变量
export LANG="zh_CN.UTF-8"
#export JAVA_HOME=/opt/software/java/jdk1.8.0_121
#export CLASSPATH=$JAVA_HOME/lib:$JAVA_HOME/jre/lib:$CLASSPATH
#export MAVEN_HOME=/opt/software/maven/apache-maven-3.0.5
#export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$MAVEN_HOME/bin:$PATH

# 关键路径
SCRIPT_PATH=/home/zp/source/JavaStack/scripts
COMPILED_WEBAPP=/home/zp/source/JavaStack/codes/javatool/server/target/javatool-server
RESOURCES_PATH=/home/zp/source/JavaStack/codes/javatool/server/src/main/resources
execBootScript
