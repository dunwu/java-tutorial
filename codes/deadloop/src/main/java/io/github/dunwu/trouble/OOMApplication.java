package io.github.dunwu.trouble;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 启动参数：
 * <p>
 * java -verbose:gc -Xms128M -Xmx256M -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps
 * -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -Dcom.sun.management.jmxremote=true
 * -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
 * -Djava.rmi.server.hostname=172.22.6.43 -Dcom.sun.management.jmxremote.port=18888 -Xdebug -Xnoagent
 * -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=28888,server=y,suspend=n -jar deadloop.war
 */
@Slf4j
@SpringBootApplication
public class OOMApplication implements CommandLineRunner {

    private final FooService fooService;

    public OOMApplication(FooService fooService) {
        this.fooService = fooService;
    }

    public static void main(String[] args) {
        SpringApplication.run(OOMApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("VM options");
        log.info(ManagementFactory.getRuntimeMXBean()
            .getInputArguments()
            .stream()
            .collect(Collectors.joining(System.lineSeparator())));
        log.info("Program arguments");
        log.info(Arrays.stream(args).collect(Collectors.joining(System.lineSeparator())));

        while (true) {
            fooService.oom();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
