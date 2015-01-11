package com.tcl.log.analysis.util;

import org.apache.log4j.Logger;

/**
 * @author kelong
 * @date 12/30/14
 */
public class ParserUtil {
    private static Logger LOG = Logger.getLogger(ParserUtil.class);
    /**
     * 获取文件tag
     *
     * @param fileName
     * @return
     */
    public static String getFileTag(String fileName) {
        String[] tags = fileName.split("_");
        return tags[0];
    }
}
