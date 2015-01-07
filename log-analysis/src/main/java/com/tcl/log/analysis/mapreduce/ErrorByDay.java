package com.tcl.log.analysis.mapreduce;

import com.tcl.log.analysis.util.Parser;
import com.tcl.log.common.constants.Constants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @author kelong
 * @date 1/7/15
 */
public class ErrorByDay extends Configured implements Tool {
    public static Logger LOG = Logger.getLogger(ErrorByDay.class);

    protected static Text word = new Text();
    protected static Text values = new Text();

    public static class Map extends Mapper<Object, Text, Text, Text> {
        //实现map函数
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            InputSplit inputSplit = context.getInputSplit();
            String fileName = ((FileSplit) inputSplit).getPath().getName();
            String fileTag = Parser.getFileTag(fileName);
            String[] rowArr =
                Parser.parseing(fileTag, value.toString(), statType);
            if (rowArr != null && rowArr.length == 2) {
                word.set(rowArr[0]);
                values.set(rowArr[1]);//IP_请求状态
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
        conf.set(TableOutputFormat.OUTPUT_TABLE, Constants.HBASE.LOG_TABLE_DAY);
        String[] ioArgs = new String[] {args[0]};
        String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
        Job job = new Job(conf, "pv stat");
        job.setJarByClass(LogStatByDay.class);
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
}
