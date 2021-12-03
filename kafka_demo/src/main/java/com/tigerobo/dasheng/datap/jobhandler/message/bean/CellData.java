package com.tigerobo.dasheng.datap.jobhandler.message.bean;


public class CellData {

    //字段类型
    String fieldType;

    //字段名称
    String fieldName;

    //字段值
    Object fieldValue;

    public CellData(String fieldType, String fieldName, Object fieldValue) {
        this.fieldType = fieldType;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}
