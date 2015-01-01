package com.tcl.log.analysis.model;

/**
 * @author kelong
 * @date 12/30/14
 */
public class Pv {
    private String pvKey;
    private String requestUrl;
    private long totalNum;
    private long successNum;
    private long ipNum;

    public String getPvKey() {
        return pvKey;
    }

    public void setPvKey(String pvKey) {
        this.pvKey = pvKey;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public long getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(long successNum) {
        this.successNum = successNum;
    }

    public long getIpNum() {
        return ipNum;
    }

    public void setIpNum(long ipNum) {
        this.ipNum = ipNum;
    }
}