package io.github.dunwu.javatool.server;

import org.apache.catalina.startup.Tomcat;

import java.util.Optional;

/**
 * 简单的嵌入式 Tomcat 启动类 启动后可访问 http://localhost:8080/javatool-server/
 *
 * @author Zhang Peng
 */
public class SimpleTomcatServer {

    private static final int PORT = 8080;

    private static final String CONTEXT_PATH = "/javatool-server";

    public static void main(String[] args) throws Exception {
        // 设定 profile
        Optional<String> profile = Optional.ofNullable(System.getProperty("spring.profiles.active"));
        System.setProperty("spring.profiles.active", profile.orElse("develop"));

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(PORT);
        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp(CONTEXT_PATH, getAbsolutePath() + "src/main/webapp");
        tomcat.start();
        tomcat.getServer().await();
    }

    private static String getAbsolutePath() {
        String path = null;
        String folderPath = SimpleTomcatServer.class.getProtectionDomain().getCodeSource().getLocation().getPath()
            .substring(1);
        if (folderPath.indexOf("target") > 0) {
            path = folderPath.substring(0, folderPath.indexOf("target"));
        }
        return path;
    }

}
