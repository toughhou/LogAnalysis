package com.tcl.log.analysis.model;

import com.tcl.log.common.util.DateUtil;
import com.tcl.log.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * @author kelong
 * @date 1/7/15
 */
public class LogError {
    private String rowKey;
    private String level;
    private String errorTime;
    private String className;
    private String errorInfo;

    /**
     * 是否ERROR信息
     *
     * @param value
     * @return
     */
    public static boolean isError(String value) {
        if (StringUtils.isEmpty(value) || !value.startsWith("[ERROR]")) {
            return false;
        }
        return true;
    }


    /**
     * 解析异常对象
     * @param value
     * @return
     */
    public static LogError parseing(String value) {
        if(!isError(value)){
            return null;
        }
        LogError error = new LogError();
        String[] field = value.split(" ");
        error.setErrorTime(StringUtil.append(field[1], " ", field[2]));
        int index = value.indexOf(field[3]) + field[3].length();
        error.setClassName(value.substring(index));
        return error;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(String errorTime) {
        this.errorTime = errorTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Date getTime_local_Date() {
        return DateUtil.fomartStrToDate("yyyy-MM-dd HH:mm:sss", this.errorTime);
    }

    public String getErrorTimeDay() {
        return DateUtil.fomartDateToStr("yyyyMMdd", this.getTime_local_Date());
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }
}
