package com.tcl.log.persistent.dao.impl;

import com.tcl.log.persistent.dao.LogDao;
import com.tcl.log.persistent.habse.dao.HbaseBaseDao;
import com.tcl.log.persistent.model.Pv;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kelong
 * @date 1/4/15
 */
@Component
public class LogDaoImpl implements LogDao {
    @Resource
    private HbaseBaseDao hbaseBaseDao;

    @Override
    public List<Pv> selectPvByRow(String rowKey) {
        Result result=hbaseBaseDao.getOneRecord()
        return null;
    }
}
