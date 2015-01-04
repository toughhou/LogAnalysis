package com.tcl.log.analysis.model;

import com.tcl.log.common.util.StringUtil;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author kelong
 * @date 12/26/14
 */
public class LogKpi {
    static Logger LOG= Logger.getLogger(LogKpi.class);

    private String remote_addr;// 记录客户端的ip地址
    private String remote_user;// 记录客户端用户名称,忽略属性"-"
    private String time_local;// 记录访问时间与时区
    private String request;// 记录请求的url与http协议
    private String status;// 记录请求状态；成功是200
    private String body_bytes_sent;// 记录发送给客户端文件主体内容大小
    private String requestTime;//请求时间
    private String http_referer;// 用来记录从那个页面链接访问过来的
    private String http_forward;//
    private String http_user_agent;// 记录客户浏览器的相关信息

    private boolean valid = true;// 判断数据是否合法
    private boolean http_success = true;//判断请求是否成功
    private String request_method;

    private static LogKpi parser(String line) {
        LogKpi kpi = new LogKpi();
        String[] arr = line.split(" ");
        if (arr.length > 11) {
            kpi.setRemote_addr(arr[0]);
            kpi.setRemote_user(arr[1]);
            kpi.setTime_local(arr[3].substring(1));
            kpi.setRequest(arr[6]);
            kpi.setStatus(arr[8]);
            kpi.setBody_bytes_sent(arr[9]);
            kpi.setRequestTime(arr[10]);
            kpi.setHttp_forward(arr[11]);
            kpi.setHttp_referer(arr[12]);
            if (arr.length > 14) {
                kpi.setHttp_user_agent(arr[13] + " " + arr[14]);
            } else {
                kpi.setHttp_user_agent(arr[13]);
            }
            if (Integer.parseInt(kpi.getStatus()) >= 400) {// 大于400，HTTP错误
                kpi.setHttp_success(false);
            }
        } else {
            kpi.setValid(false);
        }
        return kpi;
    }

    /**
     * 按page的pv分类
     */
    public static LogKpi filterPVs(String line) {
        LogKpi kpi = null;
        try {
            kpi = parser(line);
        } catch (Exception e) {
//            LOG.error(e.getMessage()+"#####"+line,e);
        }
        return kpi;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("valid:" + this.valid);
        sb.append("\nremote_addr:" + this.remote_addr);
        sb.append("\nremote_user:" + this.remote_user);
        sb.append("\ntime_local:" + this.time_local);
        sb.append("\nrequest:" + this.request);
        sb.append("\nstatus:" + this.status);
        sb.append("\nbody_bytes_sent:" + this.body_bytes_sent);
        sb.append("\nrequest_time:" + this.requestTime);
        sb.append("\nhttp_forward:"+this.http_forward);
        sb.append("\nhttp_referer:" + this.http_referer);
        sb.append("\nhttp_user_agent:" + this.http_user_agent);
        return sb.toString();
    }

    public String getRemote_addr() {
        return remote_addr;
    }

    public void setRemote_addr(String remote_addr) {
        this.remote_addr = remote_addr;
    }

    public String getRemote_user() {
        return remote_user;
    }

    public void setRemote_user(String remote_user) {
        this.remote_user = remote_user;
    }

    public String getTime_local() {
        return time_local;
    }

    public Date getTime_local_Date() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
        return df.parse(this.time_local);
    }

    public String getTime_local_Date_hour() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
            return df.format(this.getTime_local_Date());
        } catch (Exception e) {
        }
        return "";
    }

    public void setTime_local(String time_local) {
        this.time_local = time_local;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody_bytes_sent() {
        return body_bytes_sent;
    }

    public void setBody_bytes_sent(String body_bytes_sent) {
        this.body_bytes_sent = body_bytes_sent;
    }

    public String getHttp_referer() {
        return http_referer;
    }

    public String getHttp_referer_domain() {
        if (http_referer.length() < 8) {
            return http_referer;
        }

        String str = this.http_referer.replace("\"", "").replace("http://", "").replace("https://", "");
        return str.indexOf("/") > 0 ? str.substring(0, str.indexOf("/")) : str;
    }

    public void setHttp_referer(String http_referer) {
        this.http_referer = http_referer;
    }

    public String getHttp_user_agent() {
        return http_user_agent;
    }

    public void setHttp_user_agent(String http_user_agent) {
        this.http_user_agent = http_user_agent;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isHttp_success() {
        return http_success;
    }

    public void setHttp_success(boolean http_success) {
        this.http_success = http_success;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getHttp_forward() {
        return http_forward;
    }

    public void setHttp_forward(String http_forward) {
        this.http_forward = http_forward;
    }

    public String getRequest_method() {
        return request_method;
    }

    public void setRequest_method(String request_method) {
        this.request_method = request_method;
    }

    public static void main(String args[]) {
//        String line = "222.68.172.190 - - [18/Sep/2013:06:49:57 +0000] \"GET /images/my.jpg HTTP/1.1\" 200 19939 \"http://www.angularjs.cn/A00n\" \"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36\"";
        String line = "154.0.180.89 - - [24/Dec/2014:08:19:45 +0000] \"POST /phone/unregister HTTP/1.1\" 200 28 \"-\" \"-\"";
        String line2="189.241.201.152 - - [04/Jan/2015:07:13:17 +0000] \"POST /phone/unregister HTTP/1.1\" 200 28 0.706 \"-\" \"-\" \"-\"\n";
        String line3="113.175.190.13 - - [04/Jan/2015:07:53:00 +0000] \"-\" 400 0 59.994 \"-\" \"-\" \"-\"";
        LogKpi logKpi=LogKpi.filterPVs(line3);
        System.out.println(logKpi.toString());
        System.out.println(StringUtil.append("a","b"));
    }
}
