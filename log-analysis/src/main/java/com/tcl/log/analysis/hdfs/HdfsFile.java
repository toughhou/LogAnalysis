package com.tcl.log.analysis.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;

/**
 * JAVA API of HDFS
 *
 * @author kelong
 * @date 12/30/14
 */
public class HdfsFile {
    public static Logger LOG = Logger.getLogger(HdfsFile.class);

    /**
     * get Directory
     *
     * @param hdfsDir
     * @return
     */
    public static Path[] getDirFromHdfs(String hdfsDir) {
        FileSystem fs = null;
        Path[] listedPaths = null;
        try {
            Configuration conf = new Configuration();
            fs = FileSystem.get(URI.create(hdfsDir), conf);
            FileStatus fileList[] = fs.listStatus(new Path(hdfsDir));
            listedPaths = FileUtil.stat2Paths(fileList);
            fs.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return listedPaths;
    }

    /**
     * copy file from hdfs to local
     *
     * @param descHdfsPath
     * @param localPath
     */
    public static void copyToLocal(String descHdfsPath, String localPath) {
        try {
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(URI.create(descHdfsPath), conf);
            FSDataInputStream hdfsInStream = fs.open(new Path(descHdfsPath));
            OutputStream out = new FileOutputStream(localPath);
            byte[] ioBuffer = new byte[1024];
            int readLen = hdfsInStream.read(ioBuffer);
            while (-1 != readLen) {
                out.write(ioBuffer, 0, readLen);
                readLen = hdfsInStream.read(ioBuffer);
            }
            out.close();
            hdfsInStream.close();
            fs.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * read file from hdfsfile
     *
     * @param descHdfsFilePath
     * @return
     */
    public static StringBuilder readFromHdfs(String descHdfsFilePath) {
        StringBuilder content = new StringBuilder();
        try {
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(URI.create(descHdfsFilePath), conf);
            FSDataInputStream dis = fs.open(new Path(descHdfsFilePath));
            InputStreamReader isr = new InputStreamReader(dis, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String str = "";
            while ((str = br.readLine()) != null) {
                content.append(str);
            }
            br.close();
            isr.close();
            dis.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return content;
    }
}
