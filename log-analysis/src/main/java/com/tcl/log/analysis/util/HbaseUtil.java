package com.tcl.log.analysis.util;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author kelong
 * @date 12/31/14
 */
public class HbaseUtil {
    /**
     *
     * @param rowKey
     * @param family
     * @param qualifier
     * @param value
     * @return
     */
    public static Put getPut(String rowKey, String family, String qualifier, String value){
        Put put = new Put(Bytes.toBytes(rowKey));
        put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier),
            Bytes.toBytes(value));
        return put;
    }
}
