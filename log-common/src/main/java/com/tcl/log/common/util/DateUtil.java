package com.tcl.log.common.util;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kelong on 9/2/14.
 */
public class DateUtil {

    private final static Logger LOG = Logger.getLogger(DateUtil.class);

    /**
     * 时间转换为字符串
     *
     * @param format ：格式方式
     * @param date   ：时间
     * @return
     */
    public static String fomartDateToStr(String format, Date date) {
        String dateString = null;
        try {
            if (null != date) {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                dateString = formatter.format(date);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return dateString;
    }

    /**
     * @param days
     * @return
     */
    public static String getBeforeDays(int days, String format) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -days);
        return fomartDateToStr(format, c.getTime());
    }

    /**
     * @param hours
     * @return
     */
    public static String getBeforeHours(int hours) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, -hours);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
    }

    /**
     * 获取当前时间到明天1点的毫秒数
     *
     * @return
     */
    public static long getSecordByDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, +1);
        c.set(Calendar.HOUR_OF_DAY, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date d = new Date();
        return c.getTime().getTime() - d.getTime();
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getSecordByDay());
    }
}