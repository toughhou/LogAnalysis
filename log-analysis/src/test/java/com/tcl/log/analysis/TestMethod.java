package com.tcl.log.analysis;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author kelong
 * @date 1/5/15
 */
public class TestMethod {
    Logger LOG = Logger.getLogger(TestMethod.class);

    @Test
    public void test() {
//        String s = "aaaaa?ddddd?dddd";
        String s=null;
        int[] a=new int[]{1,2};
        try {
//            int x = s.indexOf("?");
            int y=a[5];
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        }
    }
}
