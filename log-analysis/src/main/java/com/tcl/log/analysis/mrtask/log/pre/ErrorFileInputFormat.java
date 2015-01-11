package com.tcl.log.analysis.mrtask.log.pre;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * Created by _think on 2015/1/7.
 */
public class ErrorFileInputFormat extends
        FileInputFormat<NullWritable, BytesWritable> {

    @Override
    public RecordReader<NullWritable, BytesWritable> createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        ErrorFileRecordReader reader = new ErrorFileRecordReader();
        reader.initialize(inputSplit, taskAttemptContext);
        return reader;
    }
}
