package com.tcl.log.persistent.model.log;

/**
 * @author kelong
 * @date 12/30/14
 */
public class AppPv {
    private String pvKey;
    private String requestUrl;
    private long pv;
    private long spv;
    private long uv;
    private double maxRequestTime;
    private double avgRequestTime;
    private double uf;

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

    public long getPv() {
        return pv;
    }

    public void setPv(long pv) {
        this.pv = pv;
    }

    public long getSpv() {
        return spv;
    }

    public void setSpv(long spv) {
        this.spv = spv;
    }

    public long getUv() {
        return uv;
    }

    public void setUv(long uv) {
        this.uv = uv;
    }

    public double getMaxRequestTime() {
        return maxRequestTime;
    }

    public void setMaxRequestTime(double maxRequestTime) {
        this.maxRequestTime = maxRequestTime;
    }

    public double getAvgRequestTime() {
        return avgRequestTime;
    }

    public void setAvgRequestTime(double avgRequestTime) {
        this.avgRequestTime = avgRequestTime;
    }

    public double getUf() {
        return uf;
    }

    public void setUf(double uf) {
        this.uf = uf;
    }
}
