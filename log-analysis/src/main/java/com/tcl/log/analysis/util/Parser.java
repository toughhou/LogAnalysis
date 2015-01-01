package com.tcl.log.analysis.util;

import com.tcl.log.analysis.model.Pv;
import com.tcl.log.common.util.CommonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kelong
 * @date 12/30/14
 */
public class Parser {
    private static Logger LOG = Logger.getLogger(Parser.class);

    /**
     * 解析pv对象
     *
     * @param fileContent
     * @return
     */
    public static List<Pv> parserPv(String fileContent) {
        if (StringUtils.isEmpty(fileContent)) {
            LOG.warn("file content is empty." + new Date());
            return null;
        }
        List<Pv> pvList = new ArrayList<Pv>();
        try {
            String[] rows = fileContent.split("\\n");
            if (rows != null && rows.length > 0) {
                for (String row : rows) {
                    Pv pv = new Pv();
                    String[] columns = row.split("\\t");
                    String[] fields = columns[0].split("#");
                    String[] kpiFields = columns[1].split("#");
                    pv.setPvKey(fields[0]);
                    pv.setRequestUrl(fields[1]);
                    pv.setTotalNum(CommonUtil.objtoInt(kpiFields[0]));
                    pv.setSuccessNum(CommonUtil.objtoInt(kpiFields[1]));
                    pv.setIpNum(CommonUtil.objtoInt(kpiFields[2]));
                    pvList.add(pv);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return pvList;
    }

    public static Pv parserPv(String key,String value){
        String[] keyFields = key.split("#");
        String[] valueFields=value.split("#");
        Pv pv=new Pv();
        pv.setPvKey(keyFields[0]);
        pv.setRequestUrl(keyFields[1]);
        pv.setTotalNum(CommonUtil.objtoInt(valueFields[0]));
        pv.setSuccessNum(CommonUtil.objtoInt(valueFields[1]));
        pv.setIpNum(CommonUtil.objtoInt(valueFields[2]));
        return pv;
    }
}
