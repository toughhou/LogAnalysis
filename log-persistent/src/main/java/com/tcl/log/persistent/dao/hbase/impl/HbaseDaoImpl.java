package com.tcl.log.persistent.dao.hbase.impl;

import com.tcl.log.common.constants.GlobalInstance;
import com.tcl.log.persistent.dao.hbase.HbaseDao;
import com.tcl.log.persistent.dao.hbase.HbaseHelper;
import com.tcl.log.persistent.model.hbase.CellEntity;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

/**
 * Created by _think on 2015/1/11.
 */
@Component
public class HbaseDaoImpl implements HbaseDao {
    @Override
    public CellEntity findCell(String table, String row, String family, String qualifier) throws Exception {
        String value = HbaseHelper.getResultByColumn(table, row, family, qualifier);
        CellEntity cellEntity = new CellEntity(row, family, qualifier, value);
        return cellEntity;
    }

    @Override
    public List<CellEntity> findCellsByRowFamilly(String table, String row, String family) throws Exception {
        Result result = HbaseHelper.getResultByFamily(table, row, family);
        List<CellEntity> cellEntities = new ArrayList<CellEntity>();
        for (KeyValue keyValue : result.raw()) {
            String value = new String(keyValue.getValue());
            CellEntity cellEntity = new CellEntity(table, row, family, value);
            cellEntities.add(cellEntity);
        }
        return cellEntities;
    }

    @Override
    public List<CellEntity> findCellsByRowAndColumn(String table, String row, String qualifier) throws Exception {
        Result result = HbaseHelper.getResult(table, row);
        List<String> familys = GlobalInstance.getHoursStrFamilly();
        List<CellEntity> cellEntities = new ArrayList<CellEntity>();
        for (String family : familys) {
            NavigableMap<byte[], byte[]> map = result.getFamilyMap(Bytes.toBytes(family));
            String columnValue = new String(map.get(Bytes.toBytes(qualifier)));
            CellEntity cellEntity = new CellEntity(row, family, qualifier, columnValue);
            cellEntities.add(cellEntity);
        }
        return cellEntities;
    }
}
