package com.tcl.log.common.util;

/**
 * @author kelong
 * @date 12/26/14
 */
public class CommonUtil {
    public static int objtoInt(Object obj) {
        int result = 0;
        try {
            result = Integer.parseInt(obj.toString());
        } catch (NumberFormatException e) {
        }
        return result;
    }
}
