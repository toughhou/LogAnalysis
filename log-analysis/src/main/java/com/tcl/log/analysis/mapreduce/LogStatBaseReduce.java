package com.tcl.log.analysis.mapreduce;

import com.tcl.log.analysis.util.HbaseUtil;
import com.tcl.log.analysis.util.Parser;
import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.CommonUtil;
import com.tcl.log.common.util.JsonParser;
import com.tcl.log.common.util.StringUtil;
import com.tcl.log.persistent.model.Pv;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kelong
 * @date 1/5/15
 */
public class LogStatBaseReduce extends
    TableReducer<Text, Text, ImmutableBytesWritable> {
    public void reduce(Text key, Iterable<Text> values,
                       Context context, int statType) throws IOException, InterruptedException {
        Set<String> ipSet = new HashSet<String>();
        long totalPv = 0;
        int successPv = 0;
        double requestTime = 0;//请求时间
        double maxRequestTime = 0;//最大请求时间
        double avgRequestTime = 0;//平均请求时间
        double totalRequestTime = 0;//总请求时间
        for (Text val : values) {
            String[] arr = val.toString().split("#");
            requestTime = CommonUtil.strTodouble(arr[0]);
            if (totalPv == 0) {
                maxRequestTime = CommonUtil.strTodouble(arr[0]);
            }
            if (requestTime > maxRequestTime) {
                maxRequestTime = requestTime;
            }
            totalRequestTime += requestTime;
            ipSet.add(arr[1]);
            successPv += CommonUtil.objtoInt(arr[2]);//请求成功次数
            totalPv++;//请求次数
        }
        BigDecimal bg = new BigDecimal(totalRequestTime / totalPv);
        avgRequestTime = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        int totalIp = ipSet.size();
        //输出格式,maxRequestTime#avgRequestTime#totalPv#successPv#totalIp
        String valueStr =
            StringUtil.append(maxRequestTime, "#", avgRequestTime, "#", totalPv, "#",
                successPv + "#" + totalIp);
        String keyStr = key.toString();
        Pv pv = Parser.parserPv(keyStr, valueStr);
        String[] keys = keyStr.split("#");
        Put put =
            HbaseUtil.getPut(keys[0], Constants.HBASE.LOG_PV_CF, keys[1], JsonParser.toString(pv));
        context.write(new ImmutableBytesWritable(key.getBytes()), put);
    }
}
