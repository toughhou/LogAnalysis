package com.tcl.log.analysis.mrtask.log.parser;

import com.tcl.log.analysis.model.LogKpi;
import com.tcl.log.analysis.util.ParserUtil;
import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.CommonUtil;
import com.tcl.log.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by _think on 2015/1/9.
 */
public class AppKpiParser implements Parser {

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
        if (StringUtils.isEmpty(timeKey)) {
            return null;
        }
        String remote_Addr = kpi.getRemote_addr();
        String mapKey = StringUtil.append(fileTag, "_", timeKey);
        String mapValue = StringUtil.append(remote_Addr, "#", kpi.getBody_bytes_sent());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", mapKey);
        map.put("value", mapValue);
        return map;
    }

    @Override
    public Map<String, Object> reduceParser(Text key, Iterable<Text> values, int type) {
        Set<String> ipSet = new HashSet<String>();
        long pv = 0;//page view
        long uv = 0;
        double uf = 0;//总请求时间
        for (Text val : values) {
            String[] arr = val.toString().split("#");
            ipSet.add(arr[0]);
            pv++;//请求次数
            uf += CommonUtil.strTodouble(arr[1]);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pv", pv);
        map.put("uv", uv);
        map.put("uf",uf);
        return map;
    }
}
