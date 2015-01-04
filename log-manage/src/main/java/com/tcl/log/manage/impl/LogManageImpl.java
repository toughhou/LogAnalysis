package com.tcl.log.manage.impl;

import com.tcl.log.manage.LogManage;
import com.tcl.log.persistent.habse.dao.HbaseBaseDao;
import com.tcl.log.persistent.model.Pv;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kelong
 * @date 1/4/15
 */
@Service("logManage")
public class LogManageImpl implements LogManage {
    @Resource
    private HbaseBaseDao hbaseBaseDao;

    @Override
    public List<Pv> selectPvByRow(String appName, String hour) {
        return null;
    }
}
