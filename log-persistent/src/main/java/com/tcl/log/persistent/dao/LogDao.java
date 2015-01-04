package com.tcl.log.persistent.dao;

import com.tcl.log.persistent.model.Pv;

import java.util.List;

/**
 * @author kelong
 * @date 1/4/15
 */
public interface LogDao {
    /**
     *
     * @param rowKey
     * @return
     */
    List<Pv> selectPvByRow(String rowKey);
}
