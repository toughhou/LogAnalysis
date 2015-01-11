package com.tcl.log.manage.biz.log;

import com.tcl.log.manage.vo.log.AppKpi;
import com.tcl.log.persistent.model.log.AppPv;

import java.util.List;

/**
 * @author kelong
 * @date 1/4/15
 */
public interface LogManage {
    List<AppPv> selectPvByRow(String rowKey);

    List<AppKpi> findAppKpis(String rowKey, String kpiName);
}
