package com.tcl.log.persistent;

import com.tcl.log.common.util.JsonParser;
import com.tcl.log.persistent.dao.hbase.HbaseHelper;
import com.tcl.log.persistent.model.log.AppPv;
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

    @Before
    public void init() {

    }

    @Test
    public void creatTableTest() throws Exception {
        HbaseHelper.creatTable("log_day", "pv", "exception");
        System.out.println("==========================");
    }

    @Test
    public void insertRecordTest() throws Exception {
        for (int i = 0; i < 10; i++) {
            AppPv pv = new AppPv();
            pv.setRequestUrl("/phone/unregister" + i);
            pv.setPv(10000 * i);
            pv.setSpv(5000 * i);
            pv.setUv(1000 * i);
            HbaseHelper.insertRecord("log", "app1_2014121913", "pv", pv.getRequestUrl(), JsonParser.toString(pv));
        }
    }

    @Test
    public void getOneRecordTest() throws Exception {
        Result result = HbaseHelper.getResult("log", "20150107");
        List<Cell> cells=result.getColumnCells("exception".getBytes(),"exception1".getBytes());
        for(Cell cell:cells){
            System.out.println(new String(cell.getValue()));
        }
//        for (KeyValue kv : result.raw()) {
//            System.out.println(new String(kv.getKey()) + "=====>" + new String(kv.getValue()));
//        }
    }

    @Test
    public void getAllRecordTest() throws Exception {
        List<Result> resultList = HbaseHelper.getResultScann("log");
        for (Result result : resultList) {
            for (KeyValue kv : result.raw()) {
                System.out.println(new String(kv.getKey()) + "=====>" + new String(kv.getValue()));
            }
        }
    }
}
