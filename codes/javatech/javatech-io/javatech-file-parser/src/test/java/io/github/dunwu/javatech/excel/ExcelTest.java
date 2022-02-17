package io.github.dunwu.javatech.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-07-01
 */
public class ExcelTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelTest.class);

    public static final String OUT_FILE = "d:\\temp-" + System.currentTimeMillis() + ".xlsx";
    public static final String IN_FILE = "d:\\属性列表模板.xlsx";

    @Test
    public void simpleWrite() {
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        BeanDemo demo = new BeanDemo();
        demo.setA("分析维度1");
        demo.setB("分析维度2");
        demo.setC("分析维度3");
        demo.setD("分析维度4");
        EasyExcel.write(OUT_FILE, BeanDemo.class).sheet("模板").doWrite(Collections.singletonList(demo));
    }

    /**
     * 动态头，实时生成头写入
     * <p>
     * 思路是这样子的，先创建List<String>头格式的sheet仅仅写入头,然后通过table 不写入头的方式 去写入数据
     *
     * <p>
     * 1. 创建excel对应的实体对象
     * <p>
     * 2. 然后写入table即可
     */
    @Test
    public void dynamicHeadWrite() {
        BeanDemo demo = new BeanDemo();
        demo.setA("分析维度1");
        demo.setB("分析维度2");
        demo.setC("分析维度3");
        demo.setD("分析维度4");

        EasyExcel.write(OUT_FILE)
            // 这里放入动态头
            .head(head()).sheet("模板")
            // 当然这里数据也可以用 List<List<String>> 去传入
            .doWrite(Collections.singletonList(demo));
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("是否分析维度");
        List<String> head1 = new ArrayList<String>();
        head1.add("默认值");
        List<String> head2 = new ArrayList<String>();
        head2.add("校验规则");
        List<String> head3 = new ArrayList<String>();
        head3.add("衍生函数名称");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        return list;
    }

    @Data
    @Accessors(chain = true)
    public static class BeanDemo {

        @ExcelProperty("是否分析维度")
        private String a;
        @ExcelProperty("默认值")
        private String b;
        @ExcelProperty("校验规则")
        private String c;
        @ExcelProperty("衍生函数名称")
        private String d;
        @NotBlank
        @ExcelProperty("衍生函数实例参数")
        private String e;
        @ExcelProperty("参数配置示例")
        private String f;

    }

    @Test
    public void syncRead() throws FileNotFoundException {
        File file = new File(IN_FILE);
        InputStream inputStream = new FileInputStream(file);
        List<BeanDemo> eventAttrDefExcelDTOS = ExcelUtil.readSync(inputStream, BeanDemo.class);
        System.out.println(eventAttrDefExcelDTOS);
    }

    @Test
    public void syncRead2() throws FileNotFoundException {
        File file = new File("d:\\temp-1595405363178.xlsx");
        InputStream inputStream = new FileInputStream(file);
        List<BeanDemo> list = ExcelUtil.readSync(inputStream, BeanDemo.class);
        System.out.println(list);
    }

    @Test
    public void asyncReadByCustom() throws FileNotFoundException {
        File file = new File("d:\\temp-1595405363178.xlsx");
        InputStream inputStream = new FileInputStream(file);
        List<BeanDemo> list = ExcelUtil.readSync(inputStream, BeanDemo.class);
        System.out.println(list);
    }
}
