package com.tcl.log.analysis.task.pool;

import com.tcl.log.analysis.task.runable.LogPvTask;
import org.apache.log4j.Logger;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kelong
 * @date 12/30/14
 */
public class ThreadPoolManager {
    private Logger LOG = Logger.getLogger(ThreadPoolManager.class);

    private static ScheduledThreadPoolExecutor dayLogAnalysisExecutorService;

    static {
        dayLogAnalysisExecutorService = new ScheduledThreadPoolExecutor(1);
    }

    /**
     * 定义执行定时任务
     *
     * @param logPvtask
     */
    public static void addDayLogAnalysisTask(LogPvTask logPvtask) {
        dayLogAnalysisExecutorService.scheduleAtFixedRate(logPvtask, 0, 1000 * 60 * 60 * 24, TimeUnit.MILLISECONDS);
    }
}
