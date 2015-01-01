package com.tcl.log.common.constants;

/**
 * @author kelong
 * @date 12/26/14
 */
public abstract class Constants {
    public static class Analysis {
        public static final String REQUEST_NULL = "NONE";
    }

    public static class HBASE {
        public static final String LOG_TABLE = "log";
        public static final String LOG_PV_CF = "pv";
        public static final String LOG_EXCEPTION_CF = "exception";
    }

    public static class HADOOP {
        public static final String HDFS_URL = "hdfs://192.168.56.99:9000";
        public static final String NGINX_INPUT_DIR = "/log_in/nginx";
        public static final String NGINX_OUTPUT_DIR = "/log_out/nginx";
    }
}
