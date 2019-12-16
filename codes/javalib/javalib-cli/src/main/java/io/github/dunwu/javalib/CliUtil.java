package io.github.dunwu.javalib;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class CliUtil {

    public static void prepare(String[] args) throws Exception {
        // commons-cli命令行参数，需要带参数值
        Options options = new Options();
        // sql文件路径
        options.addOption("sql", true, "sql config");
        // 任务名称
        options.addOption("name", true, "job name");

        // 解析命令行参数
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);
        String sql = cl.getOptionValue("sql");
        String name = cl.getOptionValue("name");

        System.out.println("sql : " + sql);
        System.out.println("name : " + name);
    }

}
