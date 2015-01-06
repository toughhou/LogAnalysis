package com.tcl.log.persistent.dao.impl;

import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.JsonParser;
import com.tcl.log.persistent.dao.LogDao;
import com.tcl.log.persistent.habse.dao.HbaseBaseDao;
import com.tcl.log.persistent.model.Pv;
import com.tcl.log.persistent.model.PvComparable;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    public List<Pv> selectPvByRow(String rowKey,int type) {
        List<Pv> pvList = new ArrayList();
        try {
            String tableName;
            if(type==2){
                tableName=Constants.HBASE.LOG_TABLE_DAY;
            }else{
                tableName=Constants.HBASE.LOG_TABLE_HOUR;
            }
            Result result = hbaseBaseDao.getOneRecord(tableName, rowKey);
            for (KeyValue kv : result.raw()) {
                String cellValue = new String(kv.getValue());
                Pv pv = (Pv) JsonParser.jsonStrToObj(cellValue, Pv.class);
                pvList.add(pv);
            }
            PvComparable comparator=new PvComparable();
            Collections.sort(pvList, comparator);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return pvList;
    }
}
