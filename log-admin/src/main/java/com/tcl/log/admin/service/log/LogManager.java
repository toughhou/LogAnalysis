package com.tcl.log.admin.service.log;

import com.tcl.log.admin.vo.chart.ChartSeries;
import com.tcl.log.admin.vo.chart.ChartVo;
import com.tcl.log.common.constants.GlobalInstance;
import com.tcl.log.common.util.StringUtil;
import com.tcl.log.manage.biz.log.LogManage;
import com.tcl.log.manage.vo.log.AppKpi;
import org.apache.commons.collections.ArrayStack;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by _think on 2015/1/10.
 */
@Component
public class LogManager {
    @Resource
    private LogManage logManage;

    public ChartVo findAppKpis(String rowName, String kpiName) {
//        List<AppKpi> kpiList = logManage.findAppKpis(rowName, kpiName);
        List<AppKpi> kpiList=new ArrayList<AppKpi>();
        //测试列子
        for(int i=0;i<24;i++){
            if(i==10){
                continue;
            }
            AppKpi appKpi=new AppKpi();
            appKpi.setKpiName("PV");
            appKpi.setKpiValue(new BigDecimal(i*1000));
            appKpi.setHour(i+"");
            kpiList.add(appKpi);
        }
        AppKpi appKpi=new AppKpi();
        appKpi.setKpiName("PV");
        appKpi.setKpiValue(new BigDecimal(10*5000));
        appKpi.setHour(10+"");
        kpiList.add(appKpi);
        Collections.sort(kpiList);
        List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
        ChartSeries chartSeries = new ChartSeries();
        chartSeries.setName(StringUtil.splitRow(rowName)[0]);
        //x轴
        List categories = GlobalInstance.getHoursStrFamilly();
        //值
        List data = new ArrayList();
        for (AppKpi kpi : kpiList) {
            data.add(kpi.getKpiValue());
        }
        chartSeries.setData(data);
        chartSeriesList.add(chartSeries);
        return new ChartVo(categories, chartSeriesList);
    }
}
