package com.tcl.log.analysis.mapreduce;

import com.tcl.log.analysis.model.LogKpi;
import com.tcl.log.analysis.model.Pv;
import com.tcl.log.analysis.util.HbaseUtil;
import com.tcl.log.analysis.util.Parser;
import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.CommonUtil;
import com.tcl.log.common.util.JsonParser;
import com.tcl.log.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kelong
 * @date 12/31/14
 */
public class LogStat extends Configured implements Tool {
    public static Logger LOG = Logger.getLogger(LogStat.class);


    public static class Map extends Mapper<Object, Text, Text, Text> {
        private static Text line = new Text();//每行数据

        private Text word = new Text();
        private Text values = new Text();

        //实现map函数
        public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
            LogKpi kpi = LogKpi.filterPVs(value.toString());
            if (kpi.isValid()) {
                String hour = kpi.getTime_local_Date_hour();
                String request = kpi.getRequest();
                String remote_Addr = kpi.getRemote_addr();
                boolean http_success = kpi.isHttp_success();
                word.set(hour + "#" + request);
                values.set(remote_Addr + "#" + (http_success == true ? 1 : 0));//IP_请求状态
                context.write(word, values);//请求,1次
            }
        }
    }


    public static class Reduce extends
        TableReducer<Text, Text, ImmutableBytesWritable> {

        //实现reduce函数
        public void reduce(Text key, Iterable<Text> values,
                           Context context)

            throws IOException, InterruptedException {
            Set<String> ipSet = new HashSet<String>();
            int totalPv = 0;
            int successPv = 0;
            for (Text val : values) {
                String[] arr = val.toString().split("#");
                ipSet.add(arr[0]);
                successPv += CommonUtil.objtoInt(arr[1]);//请求成功次数
                totalPv += 1;//请求次数
            }
            int totalIp = ipSet.size();
            String valueStr = StringUtil.append(totalPv, "#", successPv + "#" + totalIp);
            String keyStr = key.toString();
            Pv pv = Parser.parserPv(keyStr, valueStr);
            String[] keys = keyStr.split("#");
            LOG.info(keys[0]);
            Put put =
                HbaseUtil.getPut(keys[0], Constants.HBASE.LOG_PV_CF, keys[1], JsonParser.toString(pv));
            context.write(new ImmutableBytesWritable(key.getBytes()), put);
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.56.99,192.168.56.100,192.168.56.111");
        conf.set("hbase.zookeeper.property.clientPort", "4181");
        conf.set("fs.default.name", Constants.HADOOP.HDFS_URL);
        conf.set(TableOutputFormat.OUTPUT_TABLE,"log");
        String[] ioArgs = new String[] {args[0]};
        String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
        Job job = new Job(conf, "WordCount table with " + args[0]);
        job.setJarByClass(LogStat.class);
        Path in = new Path(otherArgs[0]);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TableOutputFormat.class);
        FileInputFormat.addInputPath(job, in);
        //TableMapReduceUtil.initTableReducerJob("log",Reduce.class,job);
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args)throws Exception{
        String currentDate="20141229";
        String inputDir = StringUtil.append(Constants.HADOOP.NGINX_INPUT_DIR, "/", currentDate);
        String[] args1 = new String[] {inputDir};
        ToolRunner.run(new Configuration(), new LogStat(), args1);
    }
}
