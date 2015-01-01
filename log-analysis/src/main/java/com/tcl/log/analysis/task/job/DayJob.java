package com.tcl.log.analysis.task.job;

import com.tcl.log.analysis.task.pool.ThreadPoolManager;
import com.tcl.log.analysis.task.runable.LogPvTask;

/**
 * @author kelong
 * @date 12/30/14
 */
public class DayJob {
    public static void main(String[] args) {
        ThreadPoolManager.addDayLogAnalysisTask(new LogPvTask());
    }
}
