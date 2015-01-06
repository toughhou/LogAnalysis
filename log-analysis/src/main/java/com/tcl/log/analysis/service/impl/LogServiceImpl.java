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

}
