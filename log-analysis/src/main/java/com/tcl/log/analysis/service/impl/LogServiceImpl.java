package com.tcl.log.analysis.service.impl;

import com.tcl.log.analysis.service.LogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author kelong
 * @date 12/30/14
 */
@Service("logService")
public class LogServiceImpl extends BaseService implements LogService {
    public static Logger LOG = Logger.getLogger(LogServiceImpl.class);

}
