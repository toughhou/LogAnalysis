package com.tcl.log.analysis.mrtask.log.parser;

import com.tcl.log.analysis.model.LogKpi;
import com.tcl.log.analysis.util.ParserUtil;
import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.CommonUtil;
import com.tcl.log.common.util.StringUtil;
import com.tcl.log.persistent.model.log.AppPv;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by _think on 2015/1/9.
 */
public class RequestParser implements Parser {
    @Override
    public Map<String, Object> mapParser(Object key, Text value, Mapper.Context context, int type) {
        InputSplit inputSplit = context.getInputSplit();
        String fileName = ((FileSplit) inputSplit).getPath().getName();
        String fileTag = ParserUtil.getFileTag(fileName);
        LogKpi kpi = LogKpi.filterPVs(value.toString());
        if (kpi != null && kpi.isValid()) {
            return null;
        }
        String timeKey = null;
        switch (type) {
            case Constants.ANALYSIS.LOG_STAT_HOUR:
                timeKey = kpi.getTime_local_Date_hour();
                break;
            case Constants.ANALYSIS.LOG_STAT_DAY:
                timeKey = kpi.getTime_local_Day();
                break;
        }
        String requestUrl = kpi.getRequest();
        if (StringUtils.isEmpty(timeKey) || StringUtils.isEmpty(requestUrl)) {
            return null;
        }
        String remote_Addr = kpi.getRemote_addr();
        boolean http_success = kpi.isHttp_success();
        String requestTime = kpi.getRequestTime();
        int http_success_flag = (http_success == true ? 1 : 0);
        String uf = kpi.getBody_bytes_sent();
        String mapKey = StringUtil.append(fileTag, "_", timeKey, "#", requestUrl);
        String mapValue =
                StringUtil.append(requestTime, "#", remote_Addr, "#", http_success_flag, "#", uf);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", mapKey);
        map.put("value", mapValue);
        return map;
    }

    @Override
    public Map<String, Object> reduceParser(Text key, Iterable<Text> values, int type) {
        Set<String> ipSet = new HashSet<String>();
        long pv = 0;
        int spv = 0;
        double requestTime = 0;//请求时间
        double maxRequestTime = 0;//最大请求时间
        double avgRequestTime = 0;//平均请求时间
        double totalRequestTime = 0;//总请求时间
        double uf = 0;
        for (Text val : values) {
            String[] arr = val.toString().split("#");
            requestTime = CommonUtil.strTodouble(arr[0]);
            if (pv == 0) {
                maxRequestTime = requestTime;
            }
            if (requestTime > maxRequestTime) {
                maxRequestTime = requestTime;
            }
            totalRequestTime += requestTime;
            ipSet.add(arr[1]);
            spv += CommonUtil.objtoInt(arr[2]);//请求成功次数
            pv++;//请求次数
            uf += CommonUtil.strTodouble(arr[3]);//请求流量
        }
        BigDecimal bg = new BigDecimal(totalRequestTime / pv);
        avgRequestTime = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        String rowKey = key.toString();
        String[] keyFields = rowKey.split("#");
        AppPv appPv = new AppPv();
        appPv.setPvKey(keyFields[0]);
        appPv.setRequestUrl(keyFields[1]);
        appPv.setMaxRequestTime(maxRequestTime);
        appPv.setAvgRequestTime(avgRequestTime);
        appPv.setPv(pv);
        appPv.setSpv(spv);
        appPv.setUv(ipSet.size());
        appPv.setUf(uf);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appPv", appPv);
        return map;
    }
}
