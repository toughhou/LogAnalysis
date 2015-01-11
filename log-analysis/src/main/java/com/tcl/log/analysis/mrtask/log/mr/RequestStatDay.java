package com.tcl.log.analysis.mrtask.log.mr;

import com.tcl.log.analysis.mrtask.log.parser.Parser;
import com.tcl.log.analysis.mrtask.log.parser.RequestParser;
import com.tcl.log.analysis.util.HbaseUtil;
import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.CommonUtil;
import com.tcl.log.common.util.JsonParser;
import com.tcl.log.common.util.StringUtil;
import com.tcl.log.persistent.model.log.AppPv;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
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
import java.util.Map;

/**
 * @author kelong
 * @date 12/31/14
 */
public class RequestStatDay extends Configured implements Tool {
    public static Logger LOG = Logger.getLogger(RequestStatDay.class);

    static Parser parser = new RequestParser();

    public static class MapMaper extends Mapper<Object, Text, Text, Text> {
        protected Text word = new Text();
        protected Text values = new Text();

        //实现map函数
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            Map<String, Object> result =
                    parser.mapParser(key, value, context, Constants.ANALYSIS.LOG_STAT_DAY);
            if (result != null) {
                word.set(CommonUtil.objtoStr(result.get("key")));
                values.set(CommonUtil.objtoStr(result.get("value")));
                context.write(word, values);
            }
        }
    }


    public static class Reduce extends
            TableReducer<Text, Text, ImmutableBytesWritable> {
        //实现reduce函数
        public void reduce(Text key, Iterable<Text> values,
                           Context context)
                throws IOException, InterruptedException {
            Map<String, Object> result = parser.reduceParser(key, values, Constants.ANALYSIS.LOG_STAT_DAY);
            String rowKey = key.toString();
            AppPv appPv = (AppPv) result.get("appPv");
            //pv入库
            Put pvPut =
                    HbaseUtil.getPut(appPv.getPvKey(), Constants.HBASE.LOG_FAMILY_All, appPv.getRequestUrl(), JsonParser.toString(appPv));
            context.write(new ImmutableBytesWritable(key.getBytes()), pvPut);
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        LOG.info("LogStat Day Job start.");
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.56.99,192.168.56.100,192.168.56.111");
        conf.set("hbase.zookeeper.property.clientPort", "4181");
        conf.set("fs.default.name", Constants.HADOOP.HDFS_URL);
        conf.set("dfs.socket.timeout", "36000000");
        conf.set(TableOutputFormat.OUTPUT_TABLE, Constants.HBASE.LOG_PV);
        String[] ioArgs = new String[]{args[0]};
        String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
        Job job = new Job(conf, "pv stat");
        job.setJarByClass(RequestStatDay.class);
        Path in = new Path(otherArgs[0]);
        job.setMapperClass(MapMaper.class);
        job.setReducerClass(Reduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TableOutputFormat.class);
        FileInputFormat.addInputPath(job, in);
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
//        String currentDate = DateUtil.getBeforeDays(1, "yyyyMMdd");
        String currentDate = "20150103";
        String inputDir = StringUtil.append(Constants.HADOOP.NGINX_INPUT_DIR, "/", currentDate);
        String[] args1 = new String[]{inputDir};
        ToolRunner.run(new Configuration(), new RequestStatDay(), args1);
    }
}
