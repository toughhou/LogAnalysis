package com.tcl.log.analysis.hbase.dao;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kelong
 * @date 12/29/14
 */
@Component
public class HbaseBaseDao {
    Logger LOG = Logger.getLogger(HbaseBaseDao.class);
    static HBaseConfiguration cfg = null;

    static {
//        Configuration HBASE_CONFIG = new Configuration();
        Configuration HBASE_CONFIG = HBaseConfiguration.create();
        HBASE_CONFIG.set("hbase.zookeeper.quorum", "192.168.56.99,192.168.56.100,192.168.56.111");
        HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "4181");
        cfg = new HBaseConfiguration(HBASE_CONFIG);
    }

    /**
     * create Table
     */
    public void creatTable(String tablename, String... familyArgs) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(cfg);
        if (admin.tableExists(tablename)) {
            LOG.info("Table Exist.");
        } else {
            HTableDescriptor tableDesc = new HTableDescriptor(tablename);
            if (familyArgs != null && familyArgs.length > 0) {
                for (String family : familyArgs) {
                    tableDesc.addFamily(new HColumnDescriptor(family));
                }
            }
            admin.createTable(tableDesc);
            LOG.info("Create Success.");
        }
    }

    /**
     * insert Row
     * @param tableName
     * @param rowKey
     * @param family
     * @param qualifier
     * @param value
     * @throws java.io.IOException
     */
    public void insertRecord(String tableName, String rowKey, String family, String qualifier, String value) throws IOException {
        HTable table = new HTable(cfg, tableName);
        Put put = new Put(rowKey.getBytes());
        put.add(family.getBytes(), qualifier.getBytes(), value.getBytes());
        table.put(put);
    }

    /**
     * delete Record
     * @param tableName
     * @param rowKey
     * @throws java.io.IOException
     */
    public void deleteRecord(String tableName, String rowKey) throws IOException {
        HTable table = new HTable(cfg, tableName);
        Delete del = new Delete(rowKey.getBytes());
        table.delete(del);
    }

    /**
     * get Record By rowKey
     * @param tableName
     * @param rowKey
     * @return
     * @throws java.io.IOException
     */
    public Result getOneRecord(String tableName, String rowKey) throws IOException {
        HTable table = new HTable(cfg, tableName);
        Get get = new Get(rowKey.getBytes());
        Result rs = table.get(get);
        return rs;
    }

    /**
     * get All Record
     * @param tableName
     * @return
     * @throws java.io.IOException
     */
    public List<Result> getAllRecord(String tableName) throws IOException {
        HTable table = new HTable(cfg, tableName);
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        List<Result> list = new ArrayList<Result>();
        for (Result r : scanner) {
            list.add(r);
        }
        scanner.close();
        return list;
    }
}
