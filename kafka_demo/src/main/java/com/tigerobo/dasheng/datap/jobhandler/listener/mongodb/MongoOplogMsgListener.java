package com.tigerobo.dasheng.datap.jobhandler.listener.mongodb;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * <p>示例：
 *  java -cp ./kafka_demo-1.0-SNAPSHOT.jar com.tigerobo.dasheng.datap.jobhandler.listener.mongodb.MongoOplogMsgListener --uri=mongodb://139.199.43.252:2040 --dataBase=axzq --dataBase=axzq --collectName=donny start
 * @Author: NickyRing
 * @Date: 12/3/21 6:39 PM
 */
@Component
@Slf4j
public class MongoOplogMsgListener {


    public static void main(String args[]) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> {
            logger.setLevel(Level.INFO);
        });

        Option option_uri = Option.builder()
                .required(true)
                .desc("The mongoDb uri option")
                .longOpt("uri")
                .hasArg()
                .optionalArg(false)
                .build();
        Option option_dataBase = Option.builder()
                .required(true)
                .desc("The dataBase option")
                .longOpt("dataBase")
                .hasArg()
                .optionalArg(false)
                .build();
        Option option_collectName = Option.builder()
                .required(true)
                .desc("The collectName option")
                .longOpt("collectName")
                .hasArg()
                .optionalArg(false)
                .build();

        Options options = new Options()
                .addOption(option_uri)
                .addOption(option_dataBase)
                .addOption(option_collectName);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);

            run(
                    commandLine.getOptionValue("uri"),
                    commandLine.getOptionValue("dataBase"),
                    commandLine.getOptionValue("collectName")
            );

        } catch (Exception exception) {
            System.out.print("error: ");
            System.out.println(exception.getMessage());
        }

    }

    private static void run(String uri, String dataBase, String collectName) {


//         uri = "mongodb://139.199.43.252:2040";
//         dataBase = "axzq";
//         collectName = "donny";


        MongoClient mongoClient = getInstance(uri);

        MongoDatabase database = mongoClient.getDatabase(dataBase);
        MongoCollection<Document> collec = database.getCollection(collectName);
        List<Bson> pipeline = java.util.Collections.singletonList(Aggregates.match(Filters.or(
                Document.parse("{}"),
                Filters.in("operationType", Arrays.asList("insert", "update", "replace", "delete")))));
        MongoCursor<ChangeStreamDocument<Document>> cursor = collec.watch(pipeline).iterator();
        while (cursor.hasNext()) {
            ChangeStreamDocument<Document> next = cursor.next();
            String Operation = next.getOperationType().getValue();
            System.out.println(next.getDocumentKey().getObjectId("_id").getValue() + ":" + Operation);
        }
    }

    /**
     * 根据mongo的URI获取
     *
     * @param uri
     * @return
     */
    public synchronized static MongoClient getInstance(String uri) {

        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        try {
            return mongoClient;
        } catch (Exception e) {
            log.info("MongoDb : {} test connection failed!", uri);
            mongoClient.close();
        }
        return null;
    }
}
