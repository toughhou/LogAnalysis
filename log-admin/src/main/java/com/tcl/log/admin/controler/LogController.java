package com.tcl.log.admin.controler;

import com.tcl.log.common.util.StringUtil;
import com.tcl.log.manage.LogManage;
import com.tcl.log.persistent.model.Pv;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by _think on 2015/1/1.
 */
@Controller
public class LogController {
    @Resource
    private LogManage logManage;

    @RequestMapping(value = "/log/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/log/stat")
    @ResponseBody
    public List<Pv> stat(String appName, String timeKey,Integer type) {
        return logManage.selectPvByRow(StringUtil.append(appName, "_", timeKey),type);
    }
}
