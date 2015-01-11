package com.tcl.log.admin.controler.log;

import com.tcl.log.admin.controler.BaseController;
import com.tcl.log.admin.service.log.LogManager;
import com.tcl.log.admin.vo.JsonResult;
import com.tcl.log.admin.vo.chart.ChartVo;
import com.tcl.log.common.util.StringUtil;
import com.tcl.log.manage.biz.log.LogManage;
import com.tcl.log.persistent.model.log.AppPv;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by _think on 2015/1/1.
 */
@Controller
public class LogController extends BaseController {
    @Resource
    private LogManage logManage;
    @Resource
    private LogManager logManager;

    /**
     * 日志首页统计页面
     *
     * @return
     */
    @RequestMapping(value = "/log/dashborad")
    public String index() {
        return "/log/log_dashborad2";
    }

    @RequestMapping(value = "/log/tables")
    public String table() {
        return "/log/tables";
    }


    @RequestMapping(value = "/log/stat",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult stat(String appName, String timeKey) {
        List<AppPv> appPvs=logManage.selectPvByRow(StringUtil.append(appName, "_", timeKey));
        JsonResult jsonResult=null;
        if(appPvs!=null){
            jsonResult=buildSuccess(appPvs);
        }else{
            jsonResult=buildError();
        }
        return jsonResult;
    }

    /**
     *
     * @param rowName
     * @param kpiName
     * @return
     */
    @RequestMapping(value = "/log/chart",method = RequestMethod.POST)
    @ResponseBody
    public ChartVo statChart(String rowName, String kpiName) {
        return logManager.findAppKpis(rowName, kpiName);
    }
}
