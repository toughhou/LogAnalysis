package com.tcl.log.admin.controler;

import com.tcl.log.admin.vo.JsonResult;

/**
 * Created by _think on 2015/1/11.
 */
public class BaseController {
    /**
     * return sucess.
     *
     * @param data
     */
    public JsonResult buildSuccess(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode("0");
        jsonResult.setMsg("sucess");
        jsonResult.setData(data);
        return jsonResult;
    }

    /**
     * return fail.
     *
     * @return
     */
    public JsonResult buildError() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode("600");
        jsonResult.setMsg("fail");
        return jsonResult;
    }
}
