package com.tcl.log.analysis.service.impl;

import com.tcl.log.analysis.hdfs.HdfsFile;
import com.tcl.log.analysis.service.LogService;
import com.tcl.log.analysis.util.Parser;
import com.tcl.log.common.constants.Constants;
import com.tcl.log.common.util.JsonParser;
import com.tcl.log.common.util.StringUtil;
import com.tcl.log.persistent.model.Pv;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author kelong
 * @date 12/30/14
 */
@Service("logService")
public class LogServiceImpl extends BaseService implements LogService {
    public static Logger LOG = Logger.getLogger(LogServiceImpl.class);

    @Override
    public void insertLogToHbase(String dateStr) {
        try {
            LOG.info("insert hbase start.");
            String outputDir = StringUtil.append(Constants.HADOOP.NGINX_OUTPUT_DIR, "/", dateStr);
            Path[] dirPaths = HdfsFile.getDirFromHdfs(outputDir);
            if (dirPaths != null) {
                for (Path path : dirPaths) {
                    StringBuilder fileContent = HdfsFile.readFromHdfs(path.toString());
                    List<Pv> pvList = Parser.parserPv(fileContent.toString());
                    int count = insertLogHbaseByFile(pvList);
                    LOG.info(StringUtil.append("insert Success:", count + ",hdfs file directory:" + path));
                }
            }
            LOG.info("insert hbase end.");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private int insertLogHbaseByFile(List<Pv> pvList) {
        int count = 0;
        if (pvList == null) {
            LOG.warn("pv list is null");
            return count;
        }
        for (Pv pv : pvList) {
            try {
                hbaseBaseDao.insertRecord(Constants.HBASE.LOG_TABLE, pv.getPvKey(), Constants.HBASE.LOG_PV_CF,
                        pv.getRequestUrl(), JsonParser.toString(pv));
                count++;
            } catch (IOException e) {
                //TODO
                //插入失败的放到失败队列统一从新入库
                LOG.error(e.getMessage(), e);
            }
        }
        return count;
    }
}
