package com.tcl.log.persistent.model;

import java.util.Comparator;

/**
 * @author kelong
 * @date 1/5/15
 */
public class PvComparable implements Comparator<Pv> {
    public boolean sortASC = false;
    public boolean sortByPv = false;

    @Override
    public int compare(Pv o1, Pv o2) {
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

    public int sortByPv(Pv o1, Pv o2) {
        if (o1.getTotalNum() > o2.getTotalNum()) {
            return 1;
        } else if (o1.getTotalNum() < o2.getTotalNum()) {
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
