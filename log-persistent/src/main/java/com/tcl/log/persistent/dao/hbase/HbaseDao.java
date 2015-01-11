package com.tcl.log.persistent.dao.hbase;

import com.tcl.log.persistent.model.hbase.CellEntity;

import java.util.List;

/**
 * hbase的相关操作
 * Created by _think on 2015/1/11.
 */
public interface HbaseDao {
    /**
     * 通表,行，列族，列获取值
     *
     * @param table
     * @param row
     * @param family
     * @param qualifier
     * @return
     * @throws Exception
     */
    CellEntity findCell(String table, String row, String family, String qualifier) throws Exception;

    /**
     * get column datas by familly
     * @param table
     * @param row
     * @param family
     * @return
     * @throws Exception
     */
    List<CellEntity> findCellsByRowFamilly(String table,String row,String family)throws Exception;

    /**
     * 通过表名，行，列名获取所有family的字段值
     *
     * @param table
     * @param row
     * @param qualifier
     * @return
     * @throws Exception
     */
    List<CellEntity> findCellsByRowAndColumn(String table, String row, String qualifier) throws Exception;
}
