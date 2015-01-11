package com.tcl.log.common.util;

/**
 * @author kelong
 * @date 12/26/14
 */
public class StringUtil {
    public static String append(Object... args) {
        StringBuffer buf = new StringBuffer();
        if (args != null && args.length > 0) {
            for (Object obj : args) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String[] splitRow(String row) {
        String[] rows = row.split("_");
        return rows;
    }

    public static String fillStr(int arg) {
        String result = "";
        if (arg < 10) {
            result = "0" + result;
        } else {
            result = String.valueOf(arg);
        }
        return result;
    }
}
