package com.tcl.log.admin.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by _think on 2015/1/1.
 */
@Controller
public class LogController {
    @RequestMapping(value = "/log/index")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/log/stat")
    public String stat() {
        return "";
    }
}
