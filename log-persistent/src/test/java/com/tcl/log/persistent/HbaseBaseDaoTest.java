package com.tcl.log.persistent;

import com.tcl.log.common.util.JsonParser;
import com.tcl.log.persistent.habse.dao.HbaseBaseDao;
import com.tcl.log.persistent.model.Pv;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author kelong
 * @date 12/29/14
 */

public class HbaseBaseDaoTest {
    private HbaseBaseDao hbaseBaseDao;

    @Before
    public void init() {
        hbaseBaseDao=new HbaseBaseDao();
    }

    @Test
    public void creatTableTest() throws Exception {
        hbaseBaseDao.creatTable("log_day", "pv", "exception");
        System.out.println("==========================");
    }

    @Test
    public void insertRecordTest() throws Exception {
        for (int i = 0; i < 10; i++) {
            Pv pv = new Pv();
            pv.setRequestUrl("/phone/unregister" + i);
            pv.setTotalNum(10000 * i);
            pv.setSuccessNum(5000 * i);
            pv.setIpNum(1000 * i);
            hbaseBaseDao.insertRecord("log", "app1_2014121913", "pv", pv.getRequestUrl(), JsonParser.toString(pv));
        }
    }

    @Test
    public void getOneRecordTest() throws Exception {
        Result result = hbaseBaseDao.getOneRecord("log", "findmeback_2015010407");
        List<Cell> cells=result.getColumnCells("pv".getBytes(),"/phone/unregister".getBytes());
        for(Cell cell:cells){
            System.out.println(new String(cell.getValue()));
        }
        for (KeyValue kv : result.raw()) {
            System.out.println(new String(kv.getKey()) + "=====>" + new String(kv.getValue()));
        }
    }

    @Test
    public void getAllRecordTest() throws Exception {
        List<Result> resultList = hbaseBaseDao.getAllRecord("log");
        for (Result result : resultList) {
            for (KeyValue kv : result.raw()) {
                System.out.println(new String(kv.getKey()) + "=====>" + new String(kv.getValue()));
            }
        }
    }
}
