package io.github.dunwu.local.task;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
        // 创建一个 ScheduledExecutorService 对象，它将使用一个线程池来执行任务
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // 创建一个 Runnable 对象，这个任务将在 2 秒后执行，并且每 1 秒重复执行一次
        Runnable task = () -> {
            log.info("task 执行时间：{}", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        };

        // 安排任务在 2 秒后执行，并且每 1 秒重复执行一次
        executor.scheduleAtFixedRate(task, 2, 1, TimeUnit.SECONDS);

        // 主线程等待 10 秒后结束
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭 executor，这将停止所有正在执行的任务，并拒绝新任务的提交
        executor.shutdown();
    }

}
