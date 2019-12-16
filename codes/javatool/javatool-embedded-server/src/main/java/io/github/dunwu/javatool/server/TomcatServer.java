package io.github.dunwu.javatool.server;

import org.apache.catalina.Server;
import org.apache.catalina.startup.Catalina;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.scan.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;

public class TomcatServer {

    private static final Logger log = LoggerFactory.getLogger(TomcatServer.class);

    private static final String CONNECTOR_PORT = "8080";

    private static final String RELATIVE_DEV_DUBBO_RESOVE_FILE =
        "src/main/resources/properties/dubbo-resolve.properties";

    private static final String RELATIVE_DUBBO_RESOVE_FILE = "WEB-INF/classes/properties/dubbo-resolve.properties";

    // 以下设置轻易不要改动
    private static final String RELATIVE_DEV_BASE_DIR = "src/main/resources/tomcat/";

    private static final String RELATIVE_BASE_DIR = "WEB-INF/classes/tomcat/";

    private static final String RELATIVE_DEV_DOCBASE_DIR = "src/main/webapp";

    private static final String RELATIVE_DOCBASE_DIR = "./";

    public static void main(String[] args) throws Exception {
        // 设定Spring的profile
        if (StringUtils.isEmpty(System.getProperty("spring.profiles.active"))) {
            System.setProperty("spring.profiles.active", "develop");
        }

        System.setProperty("tomcat.host.appBase", getAbsolutePath());
        File checkFile = new File(System.getProperty("tomcat.host.appBase") + "/WEB-INF");
        if (!checkFile.exists()) {
            System.setProperty("catalina.base", getAbsolutePath() + RELATIVE_DEV_BASE_DIR);
            System.setProperty("tomcat.context.docBase", RELATIVE_DEV_DOCBASE_DIR);
            System.setProperty("dubbo.resolve.file", getAbsolutePath() + RELATIVE_DEV_DUBBO_RESOVE_FILE);
        } else {
            System.setProperty("catalina.base", getAbsolutePath() + RELATIVE_BASE_DIR);
            System.setProperty("tomcat.context.docBase", RELATIVE_DOCBASE_DIR);
            if ("develop".equalsIgnoreCase(System.getProperty("spring.profiles.active"))
                || "test".equalsIgnoreCase("spring.profiles.active")) {
                System.setProperty("dubbo.resolve.file", getAbsolutePath() + RELATIVE_DUBBO_RESOVE_FILE);
            }
        }

        if (StringUtils.isEmpty(System.getProperty("tomcat.connector.port"))) {
            System.setProperty("tomcat.connector.port", CONNECTOR_PORT);
        }
        if (StringUtils.isEmpty(System.getProperty("tomcat.server.shutdownPort"))) {
            System.setProperty("tomcat.server.shutdownPort",
                String.valueOf(Integer.valueOf(System.getProperty("tomcat.connector.port")) + 10000));
        }

        log.info("====================ENV setting====================");
        log.info("spring.profiles.active:" + System.getProperty("spring.profiles.active"));
        log.info("dubbo.resolve.file:" + System.getProperty("dubbo.resolve.file"));
        log.info("catalina.base:" + System.getProperty("catalina.base"));
        log.info("tomcat.host.appBase:" + System.getProperty("tomcat.host.appBase"));
        log.info("tomcat.context.docBase:" + System.getProperty("tomcat.context.docBase"));
        log.info("tomcat.connector.port:" + System.getProperty("tomcat.connector.port"));
        log.info("tomcat.server.shutdownPort:" + System.getProperty("tomcat.server.shutdownPort"));

        ExtendedTomcat tomcat = new ExtendedTomcat();
        tomcat.start();
        tomcat.getServer().await();
    }

    private static String getAbsolutePath() {
        String path = null;
        String folderPath = TomcatServer.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (folderPath.indexOf("WEB-INF") > 0) {
            path = folderPath.substring(0, folderPath.indexOf("WEB-INF"));
        } else if (folderPath.indexOf("target") > 0) {
            path = folderPath.substring(0, folderPath.indexOf("target"));
        }
        return path;
    }

}

class ExtendedTomcat extends Tomcat {

    private static final String RELATIVE_SERVERXML_PATH = "/conf/server.xml";

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Server getServer() {
        if (server != null) {
            return server;
        }
        // 默认不开启JNDI. 开启时, 注意maven必须添加tomcat-dbcp依赖
        System.setProperty("catalina.useNaming", "false");
        ExtendedCatalina extendedCatalina = new ExtendedCatalina();

        // 覆盖默认的skip和scan jar包配置
        System.setProperty(Constants.SKIP_JARS_PROPERTY, "");
        System.setProperty(Constants.SCAN_JARS_PROPERTY, "");

        Digester digester = extendedCatalina.createStartDigester();
        digester.push(extendedCatalina);
        try {
            server = ((ExtendedCatalina) digester
                .parse(new File(System.getProperty("catalina.base") + RELATIVE_SERVERXML_PATH))).getServer();
            // 设置catalina.base和catalna.home
            this.initBaseDir();
            return server;
        } catch (Exception e) {
            log.error("Error while parsing server.xml", e);
            throw new RuntimeException("server未创建,请检查server.xml(路径:" + System.getProperty("catalina.base")
                + RELATIVE_SERVERXML_PATH + ")配置是否正确");
        }
    }

    private static class ExtendedCatalina extends Catalina {

        @Override
        public Digester createStartDigester() {
            return super.createStartDigester();
        }

    }

}
