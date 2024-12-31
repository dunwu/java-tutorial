package io.github.dunwu.local.task;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class TimerExample {

    public static void main(String[] args) {
        // 创建一个 Timer 对象
        Timer timer = new Timer();

        // 创建一个 TimerTask 对象，这个任务将在 2 秒后执行，并且每 1 秒重复执行一次
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                log.info("task 执行时间：{}", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            }
        };

        // 安排任务在 2 秒后执行，并且每 1 秒重复执行一次
        timer.schedule(task, 2000, 1000);

        // 主线程等待 10 秒后结束
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 取消定时器和所有已安排的任务
        timer.cancel();
    }

}
