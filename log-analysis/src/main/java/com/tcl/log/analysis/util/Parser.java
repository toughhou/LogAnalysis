package com.tcl.log.analysis.util;

import com.tcl.log.analysis.model.LogKpi;
import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.CommonUtil;
import com.tcl.log.common.util.StringUtil;
import com.tcl.log.persistent.model.Pv;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kelong
 * @date 12/30/14
 */
public class Parser {
    private static Logger LOG = Logger.getLogger(Parser.class);

    /**
     * @param fileTag
     * @param lineValue
     * @return
     */
    public static String[] parseing(String fileTag, String lineValue, int type) {
        LogKpi kpi = LogKpi.filterPVs(lineValue);
        String[] logArr = null;
        if (kpi != null && kpi.isValid()) {
            String timeKey=null;
            switch (type) {
                case Constants.ANALYSIS.LOG_STAT_HOUR:
                    timeKey = kpi.getTime_local_Date_hour();
                    break;
                case Constants.ANALYSIS.LOG_STAT_DAY:
                    timeKey = kpi.getTime_local_Day();
                    break;
            }
            String requestUrl = kpi.getRequest();
            if (StringUtils.isEmpty(timeKey)||StringUtils.isEmpty(requestUrl)) {
                return null;
            }
            String remote_Addr = kpi.getRemote_addr();
            boolean http_success = kpi.isHttp_success();
            String requestTime = kpi.getRequestTime();
            int http_success_flag = (http_success == true ? 1 : 0);
            String rowKey = StringUtil.append(fileTag, "_", timeKey, "#", requestUrl);
            String rowValue =
                StringUtil.append(requestTime, "#", remote_Addr, "#", http_success_flag);
            logArr = new String[] {rowKey, rowValue};
        }
        return logArr;
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public static Pv parserPv(String key, String value) {
        String[] keyFields = key.split("#");
        String[] valueFields = value.split("#");
        Pv pv = new Pv();
        pv.setPvKey(keyFields[0]);
        pv.setRequestUrl(keyFields[1]);
        pv.setMaxRequestTime(CommonUtil.strTodouble(valueFields[0]));
        pv.setAvgRequestTime(CommonUtil.strTodouble(valueFields[1]));
        pv.setTotalNum(CommonUtil.objtoInt(valueFields[2]));
        pv.setSuccessNum(CommonUtil.objtoInt(valueFields[3]));
        pv.setIpNum(CommonUtil.objtoInt(valueFields[4]));
        return pv;
    }

    /**
     * 获取文件tag
     *
     * @param fileName
     * @return
     */
    public static String getFileTag(String fileName) {
        String[] tags = fileName.split("_");
        return tags[0];
    }
}
