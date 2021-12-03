package com.tigerobo.dasheng.datap.jobhandler.message.bean;


import java.util.concurrent.ExecutorService;

public class DatapActuator {

    //执行器目标地址
    String targetUrl;

    //线程池
    ExecutorService workExecutor;


    public DatapActuator(String targetUrl, ExecutorService workExecutor) {
        this.targetUrl = targetUrl;
        this.workExecutor = workExecutor;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public ExecutorService getWorkExecutor() {
        return workExecutor;
    }

    public void setWorkExecutor(ExecutorService workExecutor) {
        this.workExecutor = workExecutor;
    }
}
