package com.tcl.log.manage.vo.log;

import com.tcl.log.persistent.model.log.AppPv;

import java.util.Comparator;

/**
 * @author kelong
 * @date 1/5/15
 */
public class AppPvComparable implements Comparator<AppPv> {
    public boolean sortASC = false;
    public boolean sortByPv = false;

    @Override
    public int compare(AppPv o1, AppPv o2) {
        int result = 0;
        if (sortASC) {
            //升序
            if (sortByPv) {
                result = sortByPv(o1, o2);
            } else {
                result = sortByPv(o1, o2);
            }
        } else {
            //降序
            if (sortByPv) {
                result = -sortByPv(o1, o2);
            } else {
                result = -sortByPv(o1, o2);
            }
        }
        return result;
    }

    public int sortByPv(AppPv o1, AppPv o2) {
        if (o1.getPv() > o2.getPv()) {
            return 1;
        } else if (o1.getPv() < o2.getPv()) {
            return -1;
        }
        return 0;
    }


    public void setSortASC(boolean sortASC) {
        this.sortASC = sortASC;
    }

    public void setSortByPv(boolean sortByPv) {
        this.sortByPv = sortByPv;
    }
}
