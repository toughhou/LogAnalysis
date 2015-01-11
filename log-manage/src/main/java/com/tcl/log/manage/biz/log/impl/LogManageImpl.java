package com.tcl.log.manage.biz.log.impl;

import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.JsonParser;
import com.tcl.log.manage.biz.log.LogManage;
import com.tcl.log.manage.vo.log.AppKpi;
import com.tcl.log.manage.vo.log.AppPvComparable;
import com.tcl.log.persistent.dao.LogDao;
import com.tcl.log.persistent.dao.hbase.HbaseDao;
import com.tcl.log.persistent.model.hbase.CellEntity;
import com.tcl.log.persistent.model.log.AppPv;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kelong
 * @date 1/4/15
 */
@Service("logManage")
public class LogManageImpl implements LogManage {
    Logger Log = Logger.getLogger(LogManageImpl.class);
    //    @Resource
    private LogDao logDao;

    private HbaseDao hbaseDao;

    @Override
    public List<AppPv> selectPvByRow(String rowKey) {
        List<AppPv> pvs = new ArrayList<AppPv>();
        try {
            /*
            List<CellEntity> cellEntities = hbaseDao.findCellsByRowFamilly(Constants.HBASE.LOG_PV, rowKey, Constants.HBASE.LOG_FAMILY_All);
            for (CellEntity cellEntity : cellEntities) {
                AppPv appPv = (AppPv) JsonParser.jsonStrToObj(cellEntity.getValue(), AppPv.class);
                pvs.add(appPv);
            }*/
            AppPv appPv=new AppPv();
            appPv.setRequestUrl("/log/index");
            appPv.setAvgRequestTime(0.111);
            appPv.setPvKey("20150110");
            appPv.setPv(1000);
            appPv.setUf(100000);
            appPv.setAvgRequestTime(0.56);
            pvs.add(appPv);
            AppPvComparable comparator = new AppPvComparable();
            Collections.sort(pvs, comparator);
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            return null;
        }
        return pvs;
    }

    @Override
    public List<AppKpi> findAppKpis(String rowName, String kpiName) {
        List<AppKpi> appKpis = new ArrayList<AppKpi>();
        try {
            List<CellEntity> tableEntities = hbaseDao.findCellsByRowAndColumn(Constants.HBASE.LOG_KPI, rowName, kpiName);
            for (CellEntity entity : tableEntities) {
                AppKpi appKpi = new AppKpi();
                appKpi.setDay(entity.getRow());
                appKpi.setKpiName(entity.getFamily());
                appKpi.setHour(entity.getQualifier());
                appKpi.setKpiValue(new BigDecimal(entity.getValue().toString()));
                appKpis.add(appKpi);
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return appKpis;
    }
}
