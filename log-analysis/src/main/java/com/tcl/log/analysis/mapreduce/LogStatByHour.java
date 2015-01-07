package com.tcl.log.analysis.mapreduce;

import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.DateUtil;
import com.tcl.log.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @author kelong
 * @date 12/31/14
 */
public class LogStatByHour extends Configured implements Tool {
    public static Logger LOG = Logger.getLogger(LogStatByHour.class);


    public static class Map extends LogStatBaseMapper {
        //实现map函数
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            super.map(key, value, context, Constants.ANALYSIS.LOG_STAT_HOUR);
        }
    }

    public static class Reduce extends
        LogStatBaseReduce {
        //实现reduce函数
        public void reduce(Text key, Iterable<Text> values,
                           Context context)
            throws IOException, InterruptedException {
            super.reduce(key,values,context,Constants.ANALYSIS.LOG_STAT_HOUR);
        }
    }


    @Override
    public int run(String[] args) throws Exception {
        LOG.info("LogStat Hour Job start.");
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.56.99,192.168.56.100,192.168.56.111");
        conf.set("hbase.zookeeper.property.clientPort", "4181");
        conf.set("fs.default.name", Constants.HADOOP.HDFS_URL);
        conf.set(TableOutputFormat.OUTPUT_TABLE, Constants.HBASE.LOG_TABLE_HOUR);
        String[] ioArgs = new String[] {args[0]};
        String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
        Job job = new Job(conf, "pv stat");
        job.setJarByClass(LogStatByHour.class);
        Path in = new Path(otherArgs[0]);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TableOutputFormat.class);
        FileInputFormat.addInputPath(job, in);
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        String currentDate = DateUtil.getBeforeDays(1,"yyyyMMdd");
        String inputDir = StringUtil.append(Constants.HADOOP.NGINX_INPUT_DIR, "/", currentDate);
        String[] args1 = new String[] {inputDir};
        ToolRunner.run(new Configuration(), new LogStatByHour(), args1);
    }
}
