package io.github.dunwu.javatech.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.event.SyncReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-07-01
 */
public class ExcelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    public static List<Map<Integer, String>> readAsync(InputStream inputStream) {
        List<Map<Integer, String>> list = new ArrayList<>();
        EasyExcel.read(inputStream, new NoModelDataListener(list)).sheet().doRead();
        return list;
    }

    /**
     * 异步方式，根据 InputStream 读取 Excel 内容。
     * <p>
     * 返回结果为指定的 Class 类型列表
     * <p>
     * 需要指定读取结束后，负责处理数据的监听器
     * <p>
     * 注意：Class 类型中不能使用枚举类型
     */
    public static <T> void readAsync(InputStream inputStream, Class clazz, ReadListener<T> listener) {
        EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
    }

    /**
     * 同步方式，根据 InputStream 读取 Excel 内容。
     * <p>
     * 返回结果为指定的 Class 类型列表
     * <p>
     * 注意：Class 类型中不能使用枚举类型
     */
    public static <T> List<T> readSync(InputStream inputStream, Class<T> clazz) {
        SyncReadListener listener = new SyncReadListener();
        EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
        List<Object> list = listener.getList();
        List<T> dtoList = list.stream().map(i -> (T) i).collect(Collectors.toList());
        System.out.println(dtoList);
        return dtoList;
    }

    static class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {

        /**
         * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
         */
        private static final int BATCH_COUNT = 1000;

        List<Map<Integer, String>> list;

        public NoModelDataListener(List<Map<Integer, String>> list) {
            this.list = list;
        }

        @Override
        public void invoke(Map<Integer, String> data, AnalysisContext context) {
            LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
            list.add(data);
            if (list.size() >= BATCH_COUNT) {
                saveData();
                list.clear();
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            saveData();
            LOGGER.info("所有数据解析完成！");
        }

        /**
         * 加上存储数据库
         */
        private void saveData() {
            LOGGER.info("{}条数据，开始存储数据库！", list.size());
            LOGGER.info("存储数据库成功！");
        }

    }

}
