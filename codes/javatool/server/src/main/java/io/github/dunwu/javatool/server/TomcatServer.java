package io.github.dunwu.javatool.server;

import java.io.File;
import java.util.Optional;

import org.apache.catalina.Server;
import org.apache.catalina.startup.Catalina;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 嵌入式 Tomcat 启动类
 * 启动后可访问 http://localhost:8080/javatool-server/
 * @author Zhang Peng
 */
public class TomcatServer {

    private static final String PORT = "8080";
    // private static final String RELATIVE_DUBBO_RESOVE_FILE =
    // "src/main/resources/properties/dubbo-resolve.properties";
    private static final String RELATIVE_BASE_DIR = "src/main/resources/tomcat/";

    /**
     * 除了 spring.profiles.active，System.setProperty 设置的属性都是为了配置 server.xml
     */
    public static void main(String[] args) throws Exception {
        // 设定 Spring 的 profile
        Optional<String> profile = Optional.ofNullable(System.getProperty("spring.profiles.active"));
        System.setProperty("spring.profiles.active", profile.orElse("develop"));

        // 设定 Tomcat 的 catalina.base
        System.setProperty("catalina.base", getAbsolutePath() + RELATIVE_BASE_DIR);

        // 设定 Tomcat 的 port
        Optional<String> port = Optional.ofNullable(System.getProperty("tomcat.connector.port"));
        System.setProperty("tomcat.connector.port", port.orElse(PORT));

        ExtendedTomcat tomcat = new ExtendedTomcat();
        // 开启JNDI,注意maven必须添加tomcat-dbcp依赖
        // tomcat.enableNaming();
        tomcat.start();
        tomcat.getServer().await();
    }

    private static String getAbsolutePath() {
        String path = null;
        String folderPath = TomcatServer.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                .substring(1);
        if (folderPath.indexOf("target") > 0) {
            path = folderPath.substring(0, folderPath.indexOf("target"));
        }
        return path;
    }
}

/**
 * Tomcat 扩展类，添加功能是启动时，加载 src/main/resources/tomcat/conf/server.xml 文件中配置，而非默认配置。
 */
class ExtendedTomcat extends Tomcat {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String RELATIVE_SERVERXML_PATH = "/conf/server.xml";

    private static class ExtendedCatalina extends Catalina {
        @Override
        public Digester createStartDigester() {
            return super.createStartDigester();
        }
    }

    @Override
    public Server getServer() {
        if (server != null) {
            return server;
        }
        ExtendedCatalina extendedCatalina = new ExtendedCatalina();
        Digester digester = extendedCatalina.createStartDigester();
        digester.push(extendedCatalina);
        try {
            server = ((ExtendedCatalina) digester
                    .parse(new File(System.getProperty("catalina.base") + RELATIVE_SERVERXML_PATH))).getServer();
            this.initBaseDir();
        } catch (Exception e) {
            log.error("Error while parsing server.xml", e);
            server = null;
        } finally {
            return server;
        }
    }

}
