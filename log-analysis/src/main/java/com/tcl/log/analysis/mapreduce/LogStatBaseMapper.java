package com.tcl.log.analysis.mapreduce;

import com.tcl.log.analysis.util.Parser;
import com.tcl.log.common.constants.Constants;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author kelong
 * @date 1/5/15
 */
public class LogStatBaseMapper extends Mapper<Object, Text, Text, Text> {
    protected Text word = new Text();
    protected Text values = new Text();

    /**
     *
     * @param key
     * @param value
     * @param context
     * @param statType
     * @throws IOException
     * @throws InterruptedException
     */
    public void map(Object key, Text value, Context context,int statType)
        throws IOException, InterruptedException {
        InputSplit inputSplit = context.getInputSplit();
        String fileName = ((FileSplit) inputSplit).getPath().getName();
        String fileTag = Parser.getFileTag(fileName);
        String[] rowArr =
            Parser.parseing(fileTag, value.toString(), Constants.ANALYSIS.LOG_STAT_DAY);
        if (rowArr != null && rowArr.length == 2) {
            word.set(rowArr[0]);
            values.set(rowArr[1]);//IP_请求状态
            context.write(word, values);//请求,1次
        }
    }
}
