package com.tcl.log.common.constants;

/**
 * @author kelong
 * @date 12/26/14
 */
public abstract class Constants {
    public static class ANALYSIS {
        public static final int LOG_STAT_HOUR=1;
        public static final int LOG_STAT_DAY=2;
    }

    public static class HBASE {
        public static final String LOG_PV = "log_apppv";
        //kpi
        public static final String LOG_KPI = "log_appkpi";
        public static final String LOG_FAMILY_All="all";

        //exception
        public static final String LOG_EXCEPTION="log_exception";
        public static final String LOG_EXCEPTION_F1="F1";
    }

    public static class HADOOP {
        public static final String HDFS_URL = "hdfs://192.168.56.99:9000";
        public static final String NGINX_INPUT_DIR = "/log_in/nginx";
        public static final String NGINX_OUTPUT_DIR = "/log_out/nginx";
    }

    public static class ADMIN{
    }
}
