package com.tigerobo.dasheng.datap.jobhandler.message.mongodb;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerobo.dasheng.datap.jobhandler.message.ThreadPoolUtil;
import com.tigerobo.dasheng.datap.jobhandler.message.bean.CellData;
import com.tigerobo.dasheng.datap.jobhandler.message.bean.OperationType;
import com.tigerobo.dasheng.datap.jobhandler.message.bean.RowData;
import org.apache.commons.cli.*;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/**
 * @Description:
 *  java -cp ./kafka_demo-1.0-SNAPSHOT.jar com.tigerobo.dasheng.datap.jobhandler.message.mongodb.SubscribeFromCKafka  --brokers=10.0.0.89:9092 --topic=axzq --group=mongoshake_local --user=ckafka-y25m3jon#isp_test --password=isp_test start
 * @Author: NickyRing
 * @Date: 12/3/21 9:22 PM
 */
public class SubscribeFromCKafka {

    private static String MONGODB_KEY = "_id";

    private static String TOPIC_FORMAT = "%s_%s_%s_%s_%s";

    private static Map<String, KafkaConsumerRunnable> consumerRunnableMap = new HashMap<>();

    public static void main(String args[]) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> {
            logger.setLevel(Level.INFO);
        });

        Option option_brokers = Option.builder()
                .required(true)
                .desc("The brokers option")
                .longOpt("brokers")
                .hasArg()
                .optionalArg(false)
                .build();
        Option option_topic = Option.builder()
                .required(true)
                .desc("The topic option")
                .longOpt("topic")
                .hasArg()
                .optionalArg(false)
                .build();
        Option option_group = Option.builder()
                .required(true)
                .desc("The group option")
                .longOpt("group")
                .hasArg()
                .optionalArg(false)
                .build();
        Option option_user = Option.builder()
                .required(false)
                .desc("The user option")
                .longOpt("user")
                .hasArg()
                .optionalArg(false)
                .build();
        Option option_password = Option.builder()
                .required(false)
                .desc("The password option")
                .longOpt("password")
                .hasArg()
                .optionalArg(false)
                .build();

        Options options = new Options()
                .addOption(option_brokers)
                .addOption(option_topic)
                .addOption(option_user)
                .addOption(option_password)
                .addOption(option_group);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);

            run(
                    commandLine.getOptionValue("brokers"),
                    commandLine.getOptionValue("group"),
                    commandLine.getOptionValue("userName"),
                    commandLine.getOptionValue("password"),
                    commandLine.getOptionValue("topicStr")
            );

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        //加载kafka.properties。
//        String brokers = "ckafka-lnd9brwr.ap-shanghai.ckafka.tencentcloudmq.com:6013";
//        String group = "group_local";
//        String userName = "ckafka-lnd9brwr#anxin";
//        String password = "T7veduzhw2";
//        String topicStr = "mongo_incr";

        //加载kafka.properties。
//        String brokers = "139.199.43.252:2100";
//        String brokers = "10.0.0.89:9092";
//        String group = "mongoshake_local";
//        String userName = "ckafka-y25m3jon#isp_test";
//        String password = "isp_test";
//        String topicStr = "axzq";

    }

    private static void run(String brokers, String group, String userName, String password, String topicStr) {
        Properties props = buildProperties(brokers, group, userName, password);

        String taskKey = String.format(TOPIC_FORMAT, brokers, group, userName, password, topicStr);
        consumerRunnableMap.put(taskKey, new KafkaConsumerRunnable(props, topicStr, taskKey));


        consumerRunnableMap.forEach((k, v) -> {
            final ThreadFactory ioThreadFactory = ThreadPoolUtil.getThreadFactory("mongoConsumer");
            ExecutorService ioExecutor = Executors.newSingleThreadExecutor(ioThreadFactory);
            ioExecutor.execute(v);

        });
    }

    /**
     * @Description: 组装kafka连接参数
     * @Author: NickyRing
     * @Date: 8/15/21 11:00 PM
     */
    private static Properties buildProperties(
            String brokers,
            String group,
            String userName,
            String password) {
        Properties props = new Properties();
        //设置接入点，请通过控制台获取对应Topic的接入点。
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);

        if (null != userName && !userName.equals("")) {
            //接入协议。
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
            //Plain方式。
            props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
            props.put(SaslConfigs.SASL_JAAS_CONFIG,
                    "org.apache.kafka.common.security.scram.ScramLoginModule required " +
                            "  username=\"" + userName + "\"" +
                            "  password=\"" + password + "\";");
        }

        //两次Poll之间的最大允许间隔。
        //消费者超过该值没有返回心跳，服务端判断消费者处于非存活状态，服务端将消费者从Consumer Group移除并触发Rebalance，默认30s。
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
        //每次Poll的最大数量。
        //注意该值不要改得太大，如果Poll太多数据，而不能在下次Poll之前消费完，则会触发一次负载均衡，产生卡顿。
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 30);
        //消息的反序列化方式。
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //当前消费实例所属的消费组，请在控制台申请之后填写。
        //属于同一个组的消费实例，会负载消费消息。
        props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        return props;
    }

    /**
     * @Description: 解析MongoDB消息
     * @Author: NickyRing
     * @Date: 8/15/21 9:17 PM
     */
    private static RowData decode(String message) {
        RowData rowData = new RowData();
        try {
            JSONObject mongoOplog = JSONObject.parseObject(message);
            if (!mongoOplog.getString("ns").contains(".")) {
                return rowData;
            }
            String[] databases = mongoOplog.getString("ns").split("\\.");
            rowData.setSchemaName(databases[0]);
            rowData.setTableName(databases[1]);

            String op = mongoOplog.getString("op");
            OperationType operationType = OperationType.getOperationTypeByMongoOp(op);
            if (operationType == null) {
                return rowData;
            }
            rowData.setOperationType(operationType);

            Map<String, CellData> cellDataMap = new HashMap<>();

            //新增
            if (mongoOplog.getJSONArray("o") != null && (op.equals("i") || op.equals("d"))) {
                JSONArray jsonArray = mongoOplog.getJSONArray("o");
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject column = jsonArray.getJSONObject(i);
                    if (column.getString("Name") != null && column.getString("Name").equals(MONGODB_KEY)) {
                        cellDataMap.put(column.getString("Name"), new CellData("string", column.getString("Name"), column.get("Value")));
                    }
                }
            }
            //更新和删除
            if (mongoOplog.getJSONObject("o2") != null && mongoOplog.getJSONObject("o2").containsKey(MONGODB_KEY) && (op.equals("u") || op.equals("d"))) {
                cellDataMap.put(MONGODB_KEY, new CellData("string", MONGODB_KEY, mongoOplog.getJSONObject("o2").getString(MONGODB_KEY)));
            }

            rowData.setCellDataMap(cellDataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowData;
    }

    /**
     * @Description: kafka消息消费
     * @Author: NickyRing
     * @Date: 8/15/21 2:44 PM
     */
    static class KafkaConsumerRunnable implements Runnable {
        private Properties kafkaProperties;
        private String topicStr;
        private String taskKey;

        public KafkaConsumerRunnable(Properties kafkaProperties, String topicStr, String taskKey) {
            this.kafkaProperties = kafkaProperties;
            this.topicStr = topicStr;
            this.taskKey = taskKey;
        }

        @Override
        public void run() {
            //构造消费对象，也即生成一个消费实例。
            KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(kafkaProperties);
            //设置消费组订阅的Topic，可以订阅多个。
            //如果GROUP_ID_CONFIG是一样，则订阅的Topic也建议设置成一样。
            List<String> subscribedTopics = new ArrayList<String>();
            //如果需要订阅多个Topic，则在这里添加进去即可。
            //每个Topic需要先在控制台进行创建。
            String[] topics = topicStr.split(",");
            for (String topic : topics) {
                subscribedTopics.add(topic.trim());
            }
            consumer.subscribe(subscribedTopics);
            //循环消费消息。
            while (true) {
                try {
                    //检查任务是否存在
                    if (!consumerRunnableMap.containsKey(taskKey)) {
                        break;
                    }
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
                    //必须在下次Poll之前消费完这些数据, 且总耗时不得超过SESSION_TIMEOUT_MS_CONFIG。
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println(String.format("MongoDB Consume partition:%d offset:%d", record.partition(), record.offset()));
                        System.out.println(JSONObject.toJSONString(decode(record.value())));
                        consumer.commitSync();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("consumer error!");
                }
            }
            //销毁构造消费对象
            consumer.close();
        }
    }
}
