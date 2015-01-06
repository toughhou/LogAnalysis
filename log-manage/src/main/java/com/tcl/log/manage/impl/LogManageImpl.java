package com.tcl.log.manage.impl;

import com.tcl.log.manage.LogManage;
import com.tcl.log.persistent.dao.LogDao;
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
    private LogDao logDao;

    @Override
    public List<Pv> selectPvByRow(String rowKey,int type) {
        return logDao.selectPvByRow(rowKey,type);
    }
}
