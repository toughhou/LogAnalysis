package com.tcl.log.analysis.service.impl;


import com.tcl.log.analysis.hbase.dao.HbaseBaseDao;

import javax.annotation.Resource;

/**
 * @author kelong
 * @date 12/30/14
 */
public abstract class BaseService {
    @Resource
    protected HbaseBaseDao hbaseBaseDao;
}
