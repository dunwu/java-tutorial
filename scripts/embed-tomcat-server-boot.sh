#################################################################################
# 嵌入式 Tomcat 应用启动脚本
#################################################################################

# 删除旧日志文件
removeOldLog() {
  mkdir -p /home/zp/log/startup
  if [ -f $LOG_FILE ];then
     rm -rf $LOG_FILE
  fi
}

# 检查脚本参数，如必要参数未传入，退出脚本。
checkInput() {
  if [ "${app}" == "" ] || [ "${oper}" == "" ] || [ "${javaArgs}" == "" ] || [ "${classpathArgs}" == "" ] || [ "${bootstrapClass}" == "" ]; then
    echo "请输入脚本参数：app oper javaArgs classpathArgs bootstrapClass"
    echo "    app: 应用名。"
    echo "    oper: 运行环境（必填）。可选值：start|stop|restart"
    echo "    javaArgs: JVM 参数（必填）。"
    echo "    classpathArgs: classpath参数（必填）。"
    echo "    bootstrapClass: 启动类（必填）。"
    exit 0
  fi
}

# 检查服务是否已经启动
PIDS=""
checkStarted() {
    PIDS=`ps -ef | grep java | grep ${app} | awk '{print $2}'`
    if [ -n "$PIDS" ]; then
        return 0
    else
        return 1
    fi
}

execOper() {
  case "${oper}" in
    start)
      echo -n "starting server: "
      #检查服务是否已经启动
#      if checkStarted ;then
#        echo "ERROR: server already started!"
#        echo "PID: $PIDS"
#        exit 1
#      fi

      args="${javaArgs} -classpath ${classpathArgs} ${bootstrapClass}"
      #echo -e "启动参数:\n${args}"

      #启动服务
      nohup java ${args} > ${LOG_FILE} 2>&1 &
      # echo -e "执行参数：\n${args}"
      echo -e "\nthe server is started..."
      ;;
    stop)
      echo -n "stopping server: "
      #dubbo提供优雅停机, 不能使用kill -9
      if checkStarted ;then
        kill $PIDS
        echo -e "\nthe server is stopped..."
      else
        echo -e "\nno server to be stopped..."
      fi
      ;;
    restart)
      $0 ${app} stop "${javaArgs}" "${classpathArgs}" "${bootstrapClass}"
      sleep 5
      $0 ${app} start "${javaArgs}" "${classpathArgs}" "${bootstrapClass}"
      ;;
    *)
      echo "Invalid oper: ${oper}."
      exit 1
  esac
}

######################################## MAIN ########################################
# 获取输入参数
app=`echo $1`
oper=`echo $2`
javaArgs=`echo $3`
classpathArgs=`echo $4`
bootstrapClass=`echo $5`
vars=$*
checkInput

LOG_FILE=/home/zp/log/startup/${app}-startup.log
removeOldLog

execOper
