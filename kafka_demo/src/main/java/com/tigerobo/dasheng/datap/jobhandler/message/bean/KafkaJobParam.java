package com.tigerobo.dasheng.datap.jobhandler.message.bean;

/**
 * @Description: 增量消息任务参数
 * @Author: NickyRing
 * @Date: 8/16/21 9:49 AM
 */
public class KafkaJobParam {

    //数据库
    String db;

    //数据库表或MongoDB的集合
    String table;

    //关联字段
    String field;

    //是否为视图的主表，主表增删改查保持同步，辅表所有操作对于视图来说都是更新
    boolean mainTable;

    // 关联索引ID
    long indexId;

    //关联任务ID
    Integer jobId;

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isMainTable() {
        return mainTable;
    }

    public void setMainTable(boolean mainTable) {
        this.mainTable = mainTable;
    }

    public long getIndexId() {
        return indexId;
    }

    public void setIndexId(long indexId) {
        this.indexId = indexId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
