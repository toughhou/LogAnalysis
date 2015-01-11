package com.tcl.log.common.constants;

import com.tcl.log.common.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by _think on 2015/1/11.
 */
public class GlobalInstance {
    public static List<String> getHoursStrFamilly() {
        List<String> hourList = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
           hourList.add(String.valueOf(i));
        }
        return hourList;
    }
}
