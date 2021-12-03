package com.tigerobo.dasheng.datap.jobhandler.message;


import com.tigerobo.dasheng.datap.jobhandler.message.bean.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

//任务处理分发器
@Component
public class MsgDispatcher {

    /**
     * 这个就是(int)'０'
     */
    private static final int MIN_UPPER_N = 65296;
    /**
     * 这个就是(int)'９'
     */
    private static final int MAX_UPPER_N = 65305;


    //key:"schemaName.tableName"
    //value:任务关联的索引
    private static Map<String, List<KafkaJobParam>> indexMap = new HashMap<>();

    //key:关联索引ID
    //value:执行器调用地址
    private static Map<Long, List<DatapActuator>> indexActuatorMap = new HashMap<>();

    static {
//        indexActuatorMap.forEach((k, v) -> {
//            BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(1000);
//            ExecutorService workExecutor = new ThreadPoolExecutor(12, 12,
//                    0L, TimeUnit.MILLISECONDS, taskQueue, SubscribeFromCKafka.getThreadFactory("DatapActuator"));
//            v.setWorkExecutor(workExecutor);
//        });

    }


    public boolean doTask(RowData rowData) {

        if (StringUtils.isEmpty(rowData.getSchemaName()) || StringUtils.isEmpty(rowData.getTableName())) {
            return false;
        }
        List<KafkaJobParam> kafkaJobParams = indexMap.get(String.format("%s.%s", rowData.getSchemaName(), rowData.getTableName()));

        for (KafkaJobParam kafkaJobParam : kafkaJobParams) {
            // 检查是否包含关联字段
            if (rowData.getCellDataMap().containsKey(kafkaJobParam.getField())) {
                CellData cellData = rowData.getCellDataMap().get(kafkaJobParam.getField());
                String value = cellData.getFieldValue().toString();

                //获取该索引的所有执行器
                List<DatapActuator> datapActuators = indexActuatorMap.get(kafkaJobParam.getIndexId());
                if (CollectionUtils.isEmpty(datapActuators)) {
                    continue;
                }

                int hashCode = (isNumber(value) ? Integer.parseInt(value) : toHash(value)) % datapActuators.size();
                for (int i = 0; i < datapActuators.size(); i++) {
                    if (hashCode != i) {
                        continue;
                    }
                    DatapActuator datapActuator = datapActuators.get(i);
                    String targetUrl = datapActuator.getTargetUrl();
                    //是否为视图的主表，主表增删改查保持同步，辅表所有操作对于视图来说都是更新
                    int operationType = kafkaJobParam.isMainTable() ? rowData.getOperationType().getValue() : OperationType.UPDATE.getValue();

                    datapActuator.getWorkExecutor().execute(new DatapActuatorRunnable(targetUrl, value, operationType));
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String word = "123";

        System.out.println("toHash:" + toHash(word) % 3);
        if (isNumber(word)) {
            System.out.println("number:" + Long.parseLong(word) % 3);
        } else {
            System.out.println("toHash:" + toHash(word) % 3);
        }

    }

    // 将字符串转成hash值
    public static int toHash(String key) {
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) { // 从字符串的左边开始计算
            hashCode += key.charAt(i);// 将获取到的字符串转换成数字，比如a的码值是97，则97-96=1
        }
        System.out.println(hashCode);
        return hashCode;
    }

    /**
     * 判断一个字符串是否是数字
     *
     * @param word
     * @return
     */
    public static boolean isNumber(String word) {
        char c = 0;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            c = word.charAt(i);
            if ((c >= '0' && c <= '9') || c >= MIN_UPPER_N && c <= MAX_UPPER_N || c == '.') {
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * @Description: kafka消息消费
     * @Author: NickyRing
     * @Date: 8/15/21 2:44 PM
     */
    static class DatapActuatorRunnable implements Runnable {
        //datap 执行器地址
        private String targetUrl;
        //视图主键值
        private String primaryValue;
        // 对应操作
        private int OperationType;

        public DatapActuatorRunnable(String targetUrl, String primaryValue, int operationType) {
            this.targetUrl = targetUrl;
            this.primaryValue = primaryValue;
            OperationType = operationType;
        }

        @Override
        public void run() {
            //调用restfull接口，处理数据
        }
    }
}
