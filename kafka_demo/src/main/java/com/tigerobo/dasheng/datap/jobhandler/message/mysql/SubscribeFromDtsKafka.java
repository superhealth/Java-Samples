package com.tigerobo.dasheng.datap.jobhandler.message.mysql;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.alibaba.fastjson.JSONObject;
import com.tigerobo.dasheng.datap.jobhandler.message.ThreadPoolUtil;
import com.tigerobo.dasheng.datap.jobhandler.message.bean.CellData;
import com.tigerobo.dasheng.datap.jobhandler.message.bean.OperationType;
import com.tigerobo.dasheng.datap.jobhandler.message.bean.RowData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.header.Header;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * @Description:
 *  java -cp ./kafka_demo-1.0-SNAPSHOT.jar com.tigerobo.dasheng.datap.jobhandler.message.mysql.SubscribeFromDtsKafka  --brokers=139.199.43.252:2080 --topic=topic-subs-8gzeqtg9bo-cdb-qdrrg8qn --group=consumer-grp-subs-8gzeqtg9bo-isp --user=account-subs-8gzeqtg9bo-isp --password=isp#123456 start
 * @Author: NickyRing
 * @Date: 12/3/21 9:19 PM
 */
@Slf4j
@Component
public class SubscribeFromDtsKafka {
    private static Map<Integer, partitionMsgConsumer> partitionMsgConsumers = new HashMap<>();

    private static String TOPIC_FORMAT = "%s_%s_%s_%s_%s";

    private static Map<String, KafkaConsumerRunnable> consumerRunnableMap = new HashMap<>();


    public static void main(String[] args) {
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
    }

    private static void run(String brokers, String group, String userName, String password, String topicStr) {

        //虎博测试环境  加载kafka.properties。
//            String brokers = "shanghai-kafka-0.cdb-dts.tencentcs.com:32169";
//            String group = "consumer-grp-subs-77lcs0ngxt-datacenter";
//            String userName = "account-subs-77lcs0ngxt-datacenter";
//            String password = "IVNik9YYCY";
//            String topicStr = "topic-subs-77lcs0ngxt-cdb-a9xougui";

        //内网地址：shenzhenfsi-kafka-2.cdb-dts.tencentcs.com.cn:32359
        // 安信SIT环境  加载kafka.properties。
//         brokers = "139.199.43.252:2080";
//         group = "consumer-grp-subs-8gzeqtg9bo-isp";
//         userName = "account-subs-8gzeqtg9bo-isp";
//         password = "isp#123456";
//         topicStr = "topic-subs-8gzeqtg9bo-cdb-qdrrg8qn";


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
    private static Properties buildProperties(String brokers, String group, String user, String password) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, group + "_" + Instant.now().getEpochSecond());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");

        if (null != user && !user.equals("")) {
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
            props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-512");
            props.put(SaslConfigs.SASL_JAAS_CONFIG,
                    "org.apache.kafka.common.security.scram.ScramLoginModule required " +
                            "  username=\"" + user + "\"" +
                            "  password=\"" + password + "\";");
        }
        return props;
    }


    private final static String partitionSeqKey = "ps";

    private static long getPartitionSeq(ConsumerRecord<String, byte[]> msg) {
        Iterator<Header> it = msg.headers().headers(partitionSeqKey).iterator();
        if (it.hasNext()) {
            Header h = it.next();
            return Long.parseUnsignedLong(new String(h.value()));
        }

        throw new IllegalStateException(partitionSeqKey + " does not exists");
    }


    /**
     * @Description: 解析MySQL binlog日志消息
     * @Author: NickyRing
     * @Date: 8/16/21 9:58 PM
     */
    private static void decode(int partitionNum, long offset, long partitionSeq, SubscribeDataProto.Entry entry) throws Exception {
        SubscribeDataProto.Header header = entry.getHeader();
        log.debug(
                String.format("-->[partition: %d, offset: %d, partitionSeq: %d] [%s:%d], happenedAt: %s",
                        partitionNum,
                        offset,
                        partitionSeq,
                        header.getFileName(), header.getPosition(),
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(header.getTimestamp()),
                                TimeZone.getDefault().toZoneId())));

        switch (entry.getHeader().getMessageType()) {
            case HEARTBEAT:
                break;
            case CHECKPOINT:
                break;
            case BEGIN:
                break;
            case COMMIT:
                break;
            case ROLLBACK:
                break;
            case DDL:
                break;
            case DML:
                switch (entry.getEvent().getDmlEvent().getDmlEventType()) {
                    case INSERT:
                        transInsert(entry);
                        break;
                    case DELETE:
                        transDelete(entry);
                        break;
                    case UPDATE:
                        transUpdate(entry);
                        break;
                    default:
                        throw new IllegalStateException("unsupported dml event type");
                }
                break;
            default:
                throw new IllegalStateException("unsupported message type");
        }
    }

    private static void transInsert(SubscribeDataProto.Entry entry) throws Exception {
        SubscribeDataProto.DMLEvent dmlEvt = entry.getEvent().getDmlEvent();
        for (SubscribeDataProto.RowChange row : dmlEvt.getRowsList()) {
            Map<String, CellData> cellDataMap = new HashMap<>();
            for (int i = 0; i < row.getNewColumnsCount(); i++) {
                SubscribeDataProto.Column colDef = dmlEvt.getColumns(i);
                SubscribeDataProto.Data col = row.getNewColumns(i);

                if (col.getDataType() == SubscribeDataProto.DataType.NIL) {
                    log.error(colDef.getName() + " IS NULL");
                    continue;
                }

                cellDataMap.put(colDef.getName(), new CellData(colDef.getOriginalType(), colDef.getName(), decodeValue(col)));
            }
            RowData rowData = new RowData(entry.getHeader().getSchemaName(),
                    entry.getHeader().getTableName(), cellDataMap, OperationType.INSERT);
            log.debug("INSERT:" + JSONObject.toJSONString(rowData));
        }

    }

    private static void transDelete(SubscribeDataProto.Entry entry) throws Exception {
        SubscribeDataProto.DMLEvent dmlEvt = entry.getEvent().getDmlEvent();

        for (SubscribeDataProto.RowChange row : dmlEvt.getRowsList()) {
            Map<String, CellData> cellDataMap = new HashMap<>();
            for (int i = 0; i < row.getOldColumnsCount(); i++) {
                SubscribeDataProto.Data col = row.getOldColumns(i);
                SubscribeDataProto.Column colDef = dmlEvt.getColumns(i);

                if (col.getDataType() == SubscribeDataProto.DataType.NA) {
                    continue;
                }
                if (col.getDataType() == SubscribeDataProto.DataType.NIL) {
                    log.error(colDef.getName() + " IS NULL");
                    continue;
                }
                cellDataMap.put(colDef.getName(), new CellData(colDef.getOriginalType(), colDef.getName(), decodeValue(col)));
            }

            RowData rowData = new RowData(entry.getHeader().getSchemaName(),
                    entry.getHeader().getTableName(), cellDataMap, OperationType.DELETE);
            log.debug("DELETE:" + JSONObject.toJSONString(rowData));
        }
    }

    private static void transUpdate(SubscribeDataProto.Entry entry) throws Exception {
        SubscribeDataProto.DMLEvent dmlEvt = entry.getEvent().getDmlEvent();
        for (SubscribeDataProto.RowChange row : dmlEvt.getRowsList()) {
            Map<String, CellData> cellDataMap = new HashMap<>();

            for (int i = 0; i < row.getNewColumnsCount(); i++) {
                SubscribeDataProto.Data col = row.getNewColumns(i);
                if (col.getDataType() == SubscribeDataProto.DataType.NA) {
                    continue;
                }
                SubscribeDataProto.Column colDef = dmlEvt.getColumns(i);
                if (col.getDataType() == SubscribeDataProto.DataType.NIL) {
                    log.error(colDef.getName() + " IS NULL");
                    continue;
                }

                if (!cellDataMap.containsKey(colDef.getName())) {
                    cellDataMap.put(colDef.getName(), new CellData(colDef.getOriginalType(), colDef.getName(), decodeValue(col)));
                }
            }

            for (int i = 0; i < row.getOldColumnsCount(); i++) {
                SubscribeDataProto.Data col = row.getOldColumns(i);
                SubscribeDataProto.Column colDef = dmlEvt.getColumns(i);

                if (col.getDataType() == SubscribeDataProto.DataType.NA) {
                    continue;
                }
                if (col.getDataType() == SubscribeDataProto.DataType.NIL) {
                    log.error(colDef.getName() + " IS NULL");
                    continue;
                }
                if (!cellDataMap.containsKey(colDef.getName())) {
                    cellDataMap.put(colDef.getName(), new CellData(colDef.getOriginalType(), colDef.getName(), decodeValue(col)));
                }
            }
            RowData rowData = new RowData(entry.getHeader().getSchemaName(),
                    entry.getHeader().getTableName(), cellDataMap, OperationType.UPDATE);


            log.debug("UPDATE:" + JSONObject.toJSONString(rowData));
        }
    }

    /**
     * @Description: 解析字段值
     * @Author: NickyRing
     * @Date: 8/16/21 10:55 PM
     */
    private static String decodeValue(SubscribeDataProto.Data data) throws Exception {
        switch (data.getDataType()) {
            case INT8:
            case INT16:
            case INT32:
            case INT64:
            case UINT8:
            case UINT16:
            case UINT32:
            case UINT64:
            case DECIMAL:
            case FLOAT32:
            case FLOAT64:
                return data.getSv();
            case STRING:
                return new String(data.getBv().toByteArray());
            case BYTES:
                return DatatypeConverter.printHexBinary(data.getBv().toByteArray());
            case NA:
                return "DEFAULT";
            case NIL:
                return "NULL";
            default:
                throw new IllegalStateException("unsupported data type");
        }
    }

    /**
     * @Description: 消息消费
     * @Author: NickyRing
     * @Date: 8/16/21 10:55 PM
     */
    private static class partitionMsgConsumer {
        ByteArrayOutputStream completeMsg = new ByteArrayOutputStream();
        long lastPS = 0;
        TopicPartition tp;

        private partitionMsgConsumer(TopicPartition tp) {
            this.tp = tp;
        }

        private void consume(KafkaConsumer<String, byte[]> consumer, ConsumerRecord<String, byte[]> record) throws Exception {
            // Each message has a sequence id in the partition, this id is continuous monotonic increasing,
            // these messages are duplicated if the id rotate;
            // There is unexpected message lost in kafka if the id is discontinuous,
            // have to reset the subscribe and client when it happened.

            long ps = getPartitionSeq(record);
            if (ps <= lastPS) {
                System.out.println("Duplicated message: " + record);
                return;
            } else if (ps != lastPS + 1 && lastPS != 0) {
                throw new IllegalStateException("the partition seq is discontinuous, last: " + lastPS + ", current: " + ps);
            }
            lastPS = ps;
            SubscribeDataProto.Envelope envelope = SubscribeDataProto.Envelope.parseFrom(record.value());
            if (1 != envelope.getVersion()) {
                throw new IllegalStateException(String.format("unsupported version: %d", envelope.getVersion()));
            }

            // The size of one event maybe exceed the size limitation of kafka message,
            // then the event will be split into more than one message,
            // each message has an index, the value starts from zero to total-1,
            // have to rebuild the byte array before decode the object by protobuf.
            if (0 == envelope.getIndex()) {
                completeMsg = new ByteArrayOutputStream();
                envelope.getData().writeTo(completeMsg);
            } else {
                envelope.getData().writeTo(completeMsg);
            }

            if (envelope.getIndex() < envelope.getTotal() - 1) {
                return;
            }

            SubscribeDataProto.Entries entries = SubscribeDataProto.Entries.parseFrom(completeMsg.toByteArray());
            for (SubscribeDataProto.Entry entry : entries.getItemsList()) {
                decode(record.partition(), record.offset(), ps, entry);
            }
            // commit at checkpoint message
            if (entries.getItemsCount() > 0
                    && entries.getItems(0).getHeader().getMessageType() == SubscribeDataProto.MessageType.CHECKPOINT) {
                consumer.commitAsync(Collections.singletonMap(tp, new OffsetAndMetadata(record.offset() + 1)), new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                        //提交失败
                        if (e != null) {
                            System.out.println("commit failed" + map);
                        }
                    }
                });
            }
        }
    }

    private static class partitionSubLis implements ConsumerRebalanceListener {
        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            for (TopicPartition p : partitions) {
                partitionMsgConsumers.computeIfAbsent(p.partition(), k -> new partitionMsgConsumer(p));
            }
        }

        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            for (TopicPartition p : partitions) {
                partitionMsgConsumers.remove(p.partition());
            }
        }
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

            KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(kafkaProperties);
            consumer.subscribe(Collections.singletonList(topicStr), new partitionSubLis());
            while (true) {
                try {
                    //检查任务是否存在
                    if (!consumerRunnableMap.containsKey(taskKey)) {
                        break;
                    }
                    ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, byte[]> record : records) {
                        partitionMsgConsumer pc = partitionMsgConsumers.get(record.partition());
                        if (null == pc) {
                            throw new IllegalStateException("no such partition consumer " + record.partition());
                        }
                        pc.consume(consumer, record);
                    }
                } catch (Exception e) {
                    System.out.println("consumer error!");
                }
            }
            consumer.close();
        }
    }
}
