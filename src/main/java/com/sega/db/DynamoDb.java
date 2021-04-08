package com.sega.db;

import java.time.Instant;
import java.util.Iterator;

import com.sega.dao.Transaction;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Service
public class DynamoDb {

    public int getDynamoDBItem(DynamoDbClient ddb,
                               String tableName,
                               String partitionKeyName,
                               String partitionKeyVal,
                               String partitionAlias) {


        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(ddb)
                .build();

        try{
            DynamoDbTable<Transaction> mappedTable = enhancedClient.table("Transaction", TableSchema.fromBean(Transaction.class));
            QueryConditional queryConditional = QueryConditional
                    .keyEqualTo(Key.builder()
                            .partitionValue(1)
                            .build());

            // Get items in the table and write out the ID value
            Iterator<Transaction> results = mappedTable.query(queryConditional).items().iterator();
            int result=-1;

            while (results.hasNext()) {
                Transaction rec = results.next();
                result = rec.getId();
                System.out.println("The record id is " + rec);
            }
            return result;

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return -1;
    }

}
