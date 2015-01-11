package com.tcl.log.analysis.mrtask.log.parser;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.util.Map;

/**
 * Created by _think on 2015/1/9.
 */
public interface Parser {
    /**
     * 对map行的解析
     *
     * @param key
     * @param value
     * @param context
     * @param type
     * @return
     */
    public Map<String, Object> mapParser(Object key, Text value, Mapper.Context context, int type);

    /**
     * reduce计算解析
     *
     * @param key
     * @param values
     * @param type
     * @return
     */
    public Map<String, Object> reduceParser(Text key, Iterable<Text> values, int type);
}
