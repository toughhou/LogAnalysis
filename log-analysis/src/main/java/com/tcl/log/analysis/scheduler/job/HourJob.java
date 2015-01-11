package com.tcl.log.analysis.scheduler.job;

import com.tcl.log.analysis.mrtask.log.mr.RequestStatHour;
import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.DateUtil;
import com.tcl.log.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

/**
 * @author kelong
 * @date 1/7/15
 */
public class HourJob {
    static Logger LOG = Logger.getLogger(DayJob.class);

    public static void main(String[] args) throws Exception {
        LOG.info("Hour logstatJob start.....");
        String currentDate = DateUtil.getBeforeDays(1, "yyyyMMdd");
        String inputDir = StringUtil.append(Constants.HADOOP.NGINX_INPUT_DIR, "/", currentDate);
        String[] args1 = new String[] {inputDir};
        ToolRunner.run(new Configuration(), new RequestStatHour(), args1);
        LOG.info("Hour logstatJob end.....");
    }
}
