package com.tcl.log.analysis.task.job;

import com.tcl.log.analysis.mapreduce.LogStatByDay;
import com.tcl.log.analysis.mapreduce.LogStatByHour;
import com.tcl.log.analysis.task.pool.ThreadPoolManager;
import com.tcl.log.analysis.task.runable.LogPvTask;
import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.DateUtil;
import com.tcl.log.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

/**
 * @author kelong
 * @date 12/30/14
 */
public class DayJob {
    static Logger LOG = Logger.getLogger(DayJob.class);

    public static void main(String[] args) throws Exception {
        LOG.info("Day logstatJob start.....");
        String currentDate = DateUtil.getBeforeDays(1, "yyyyMMdd");
        String inputDir = StringUtil.append(Constants.HADOOP.NGINX_INPUT_DIR, "/", currentDate);
        String[] args1 = new String[] {inputDir};
        ToolRunner.run(new Configuration(), new LogStatByDay(), args1);
        ToolRunner.run(new Configuration(), new LogStatByHour(), args1);
        LOG.info("Day logstatJob end.....");
    }
}
