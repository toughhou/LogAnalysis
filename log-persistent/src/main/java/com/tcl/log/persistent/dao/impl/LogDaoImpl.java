package com.tcl.log.persistent.dao.impl;

import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.JsonParser;
import com.tcl.log.persistent.dao.LogDao;
import com.tcl.log.persistent.habse.dao.HbaseBaseDao;
import com.tcl.log.persistent.model.Pv;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kelong
 * @date 1/4/15
 */
@Component
public class LogDaoImpl implements LogDao {
    Logger LOG = Logger.getLogger(LogDaoImpl.class);
    @Resource
    private HbaseBaseDao hbaseBaseDao;

    @Override
    public List<Pv> selectPvByRow(String rowKey) {
        List<Pv> pvList = new ArrayList();
        try {
            Result result = hbaseBaseDao.getOneRecord(Constants.HBASE.LOG_PV_CF, rowKey);
            for (KeyValue kv : result.raw()) {
                String cellValue = new String(kv.getValue());
                Pv pv = (Pv) JsonParser.jsonStrToObj(cellValue, Pv.class);
                pvList.add(pv);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return pvList;
    }
}
