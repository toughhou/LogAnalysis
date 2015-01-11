package com.tcl.log.analysis.scheduler.job;

import com.tcl.log.analysis.mrtask.log.mr.RequestStatDay;
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
        ToolRunner.run(new Configuration(), new RequestStatDay(), args1);
        LOG.info("Day logstatJob end.....");
    }
}
