package com.tcl.log.manage;

import com.tcl.log.persistent.model.Pv;

import java.util.List;

/**
 * @author kelong
 * @date 1/4/15
 */
public interface LogManage {
    List<Pv> selectPvByRow(String appName,String hour);
}
