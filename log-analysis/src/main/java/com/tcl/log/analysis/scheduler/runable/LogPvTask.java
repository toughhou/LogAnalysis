package com.tcl.log.analysis.scheduler.runable;


import com.tcl.log.analysis.service.LogService;
import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.SpringBeanHolder;
import com.tcl.log.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

/**
 * 从hdfs扫描日志文件分析指标，生成结果文件到指定目录
 *
 * @author kelong
 * @date 12/30/14
 */
public class LogPvTask implements Runnable {
    public static Logger LOG = Logger.getLogger(LogPvTask.class);
    private LogService service;

    public LogPvTask() {
        service = (LogService) SpringBeanHolder.getSpringBean("logService");
    }

    @Override
    public void run() {
        try {
            //            String currentDate = DateUtil.getBeforeDays(1, "yyyyMMdd");
            String currentDate = "20141229";
            String inputDir = StringUtil.append(Constants.HADOOP.NGINX_INPUT_DIR, "/", currentDate);
            String[] args = new String[] {currentDate};
          //  ToolRunner.run(new Configuration(), new LogStat(), args);
            LOG.info("====================FINISH===========");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
