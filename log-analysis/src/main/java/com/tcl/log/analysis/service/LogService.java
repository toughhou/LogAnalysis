package com.tcl.log.analysis.service;

/**
 * @author kelong
 * @date 12/30/14
 */
public interface LogService {
    public void insertLogToHbase(String dateStr);
}
