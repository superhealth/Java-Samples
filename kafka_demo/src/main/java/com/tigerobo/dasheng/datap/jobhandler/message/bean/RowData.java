package com.tigerobo.dasheng.datap.jobhandler.message.bean;


import java.util.Map;

public class RowData {


    //库名称
    String schemaName;
    //表名
    String tableName;

    // key:字段名称
    // value:字段详情
    Map<String, CellData> cellDataMap;

    // 操作：增、删、改
    OperationType operationType;


    public RowData() {
    }

    public RowData(String schemaName, String tableName, Map<String, CellData> cellDataMap, OperationType operationType) {
        this.schemaName = schemaName;
        this.tableName = tableName;
        this.cellDataMap = cellDataMap;
        this.operationType = operationType;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, CellData> getCellDataMap() {
        return cellDataMap;
    }

    public void setCellDataMap(Map<String, CellData> cellDataMap) {
        this.cellDataMap = cellDataMap;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
