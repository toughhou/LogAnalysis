package com.tcl.log.manage.vo.log;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider;
import com.tcl.log.common.util.CommonUtil;
import com.tcl.log.common.util.StringUtil;

import java.math.BigDecimal;

/**
 * Created by _think on 2015/1/10.
 */
public class AppKpi implements Comparable {
    private String day;
    private String hour;
    private String kpiName;
    private BigDecimal kpiValue;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public BigDecimal getKpiValue() {
        return kpiValue;
    }

    public void setKpiValue(BigDecimal kpiValue) {
        this.kpiValue = kpiValue;
    }

    @Override
    public int compareTo(Object o) {
        AppKpi api=(AppKpi)o;
        int hour1= CommonUtil.objtoInt(this.getHour());
        int hour2=CommonUtil.objtoInt(api.getHour());
        if(hour1>hour2){
            return 1;
        }else if(hour1<hour2){
            return -1;
        }
        return 0;
    }
}
