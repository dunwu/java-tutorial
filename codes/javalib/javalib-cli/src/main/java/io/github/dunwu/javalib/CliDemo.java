package io.github.dunwu.javalib;

import org.apache.commons.cli.ParseException;

import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019/10/29
 */
public class CliDemo {

    public static void main(String[] args) throws ParseException {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String param = "";
            if (scanner.hasNext()) {
                param = scanner.next();
            }

            switch (param) {
                case "date":
                    AnsiSystem.BLUE.println("date = " + new Date());
                    break;
                case "area":
                    AnsiSystem.BLUE.println("area = " + "China");
                    break;
                case "system":
                    Properties props = System.getProperties();
                    System.out.println("Java的运行环境版本：" + props.getProperty("java.version"));
                    System.out.println("默认的临时文件路径：" + props.getProperty("java.io.tmpdir"));
                    System.out.println("操作系统的名称：" + props.getProperty("os.name"));
                    System.out.println("操作系统的构架：" + props.getProperty("os.arch"));
                    System.out.println("操作系统的版本：" + props.getProperty("os.version"));
                    System.out.println("用户的账户名称：" + props.getProperty("user.name"));
                    System.out.println("用户的主目录：" + props.getProperty("user.home"));
                    System.out.println("用户的当前工作目录：" + props.getProperty("user.dir"));
                    System.out.println("操作系统：" + props.getProperty("sun.desktop"));
                    System.out.println("CPU个数:" + Runtime.getRuntime().availableProcessors());
                    System.out.println("虚拟机内存总量:" + Runtime.getRuntime().totalMemory());
                    System.out.println("虚拟机空闲内存量:" + Runtime.getRuntime().freeMemory());
                    System.out.println("虚拟机使用最大内存量:" + Runtime.getRuntime().maxMemory());
                    break;
                case "exit":
                    return;
                default:
                    System.err.println("invalid param");
                    break;
            }
        }
    }

}
