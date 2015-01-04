package com.tcl.log.analysis.mapreduce;

import com.tcl.log.analysis.model.LogKpi;
import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.CommonUtil;
import com.tcl.log.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kelong
 * @date 12/26/14
 */
public class LogPv extends Configured implements Tool {
    public static Logger LOG = Logger.getLogger(LogPv.class);
    public static class Map extends Mapper<Object, Text, Text, Text> {
        private static Text line = new Text();//每行数据

        private Text word = new Text();
        private Text values = new Text();

        //实现map函数
        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {
            //获取文件名
            InputSplit inputSplit = context.getInputSplit();
            String fileName = ((FileSplit) inputSplit).getPath().getName();
            LogKpi kpi = LogKpi.filterPVs(value.toString());
            if(kpi.isValid()){
                String hour = kpi.getTime_local_Date_hour();
                String request = kpi.getRequest();
                String remote_Addr=kpi.getRemote_addr();
                boolean http_success=kpi.isHttp_success();
                word.set(hour+"#"+request);
                values.set(remote_Addr+"#"+(http_success==true?1:0));//IP_请求状态
                context.write(word, values);//请求,1次
            }
        }
    }

    public static class Reduce extends

            Reducer<Text, Text, Text, Text> {

        //实现reduce函数
        public void reduce(Text key, Iterable<Text> values, Context context)

                throws IOException, InterruptedException {
            Set<String> ipSet=new HashSet<String>();
            int totalPv=0;
            int successPv=0;
            for (Text val : values) {
                String[] arr=val.toString().split("#");
                ipSet.add(arr[0]);
                successPv+=CommonUtil.objtoInt(arr[1]);//请求成功次数
                totalPv+=1;//请求次数
            }
            int totalIp=ipSet.size();
            String result=StringUtil.append(totalPv,"#",successPv+"#"+totalIp);
            context.write(key,new Text(result));//计算
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        String dateStr="20141229";
        String inputDir = StringUtil.append(Constants.HADOOP.NGINX_INPUT_DIR, "/", dateStr);
        String outputDir = StringUtil.append(Constants.HADOOP.NGINX_OUTPUT_DIR, "/", dateStr);
        Configuration conf =getConf();
        conf.set("fs.default.name", Constants.HADOOP.HDFS_URL);
        String[] ioArgs = new String[] {inputDir, outputDir};
        String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
        if (otherArgs.length != 2) {
            LOG.error("dir error");
            System.exit(2);
        }
        Job job = new Job(conf, "nginx log stat");
        job.setJarByClass(LogPv.class);
        //设置Map和Reduce处理类
        job.setMapperClass(LogPv.Map.class);
        job.setReducerClass(LogPv.Reduce.class);
        //设置输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //设置输入和输出目录
        Path inputPath=new Path(otherArgs[0]);
        Path outputPath=new Path(otherArgs[1]);
        FileSystem fs = outputPath.getFileSystem(conf);
        if(fs.exists(outputPath)){
            fs.delete(outputPath,true);
        }
        FileInputFormat.addInputPath(job, inputPath);
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        LOG.info("start job....");
        return (job.waitForCompletion(true) ? 0 : 1);
    }
}
