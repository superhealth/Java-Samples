package com.tigerobo.dasheng.datap.jobhandler.message.bean;

public enum OperationType {

    /**
     * <code>INSERT = 0;</code>
     */
    INSERT(0),
    /**
     * <code>UPDATE = 1;</code>
     */
    UPDATE(1),
    /**
     * <code>DELETE = 2;</code>
     */
    DELETE(2);

    private final int value;

    private OperationType(int value) {
        this.value = value;
    }


    public static OperationType getOperationTypeByMongoOp(String op) {
        if (op == null || op.equals("")) {
            return null;
        }
        if (op.equalsIgnoreCase("i")) {
            return INSERT;
        } else if (op.equalsIgnoreCase("u")) {
            return UPDATE;
        } else if (op.equalsIgnoreCase("d")) {
            return DELETE;
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}
