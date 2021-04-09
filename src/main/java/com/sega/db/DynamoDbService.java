package com.sega.db;

import com.sega.dao.Transaction;
import com.sega.exception.ProcessingRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DynamoDbService {

    public static final String TRANSACTION = "transaction";
    private static DynamoDbClient ddb;
    private static DynamoDbEnhancedClient enhancedClient;

    @PostConstruct
    void postConstruct() {
        Region region = Region.US_EAST_2;
        ddb = DynamoDbClient.builder()
                .region(region)
                .build();

        enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(ddb)
                .build();

    }

    @PreDestroy
    void preDestroy() {
        ddb.close();
    }

    public List<Transaction> getAllTransactions() {

        try{
            DynamoDbTable<Transaction> table = enhancedClient.table(TRANSACTION, TableSchema.fromBean(Transaction.class));

            // Get items in the Record table and write out the ID value
            return Arrays.asList(table.scan().items().stream().toArray(Transaction[]::new));
        } catch (DynamoDbException e) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 405, "Unable to retrieve transactions", e);
        }
    }

    public void save(Transaction transaction) {

        try {
            DynamoDbTable<Transaction> table = enhancedClient.table(TRANSACTION, TableSchema.fromBean(Transaction.class));

            // Put the customer data into a DynamoDB table
            table.putItem(transaction);

        } catch (DynamoDbException e) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 405, "Unable to save transaction", e);
        }
    }

    public List<Transaction> getTransactionByAttributeEqualsValue(String attribute, String value) {
        AttributeValue attributeValue = AttributeValue.builder()
                .s(value)
                .build();

        return getTransactionByAttributeEqualsValue(attribute, attributeValue);
    }

    public List<Transaction> getTransactionByAttributeEqualsValue(String attribute, int value) {

        AttributeValue attributeValue = AttributeValue.builder()
                .n(String.valueOf(value))
                .build();

        return getTransactionByAttributeEqualsValue(attribute, attributeValue);
    }

    public List<Transaction> getTransactionByAttributeEqualsValue2(String attribute, String attributeValue) {

        try{
            DynamoDbTable<Transaction> table = enhancedClient.table(TRANSACTION, TableSchema.fromBean(Transaction.class));

            // Set the Expression so only Closed items are queried from the Work table
            Expression expression = Expression.builder()
                    .expression(attribute + " = " + attributeValue)
                    .build();

            ScanEnhancedRequest enhancedRequest = ScanEnhancedRequest.builder()
                    .filterExpression(expression)
                    .build();

            // Get items in the Record table and write out the ID value
            return Arrays.asList(table.scan(enhancedRequest).items().stream().toArray(Transaction[]::new));
        } catch (DynamoDbException e) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 405, "Unable to retrieve transactions", e);
        }
    }

    public List<Transaction> getTransactionByAttributeEqualsValue(String attribute, AttributeValue attributeValue) {

        try{
            DynamoDbTable<Transaction> table = enhancedClient.table(TRANSACTION, TableSchema.fromBean(Transaction.class));

            // Get only Open items in the Work table
            Map<String, AttributeValue> myMap = new HashMap<>();
            myMap.put(":" + attribute, attributeValue);

            Map<String, String> myExMap = new HashMap<>();
            myExMap.put("#" + attribute, attribute);

            // Set the Expression so only Closed items are queried from the Work table
            Expression expression = Expression.builder()
                    .expression("#" + attribute + " = :"  + attribute)
                    .expressionValues(myMap)
                    .expressionNames(myExMap)
                    .build();

            ScanEnhancedRequest enhancedRequest = ScanEnhancedRequest.builder()
                    .filterExpression(expression)
                    .build();

            // Get items in the Record table and write out the ID value
            return Arrays.asList(table.scan(enhancedRequest).items().stream().toArray(Transaction[]::new));
        } catch (DynamoDbException e) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 405, "Unable to retrieve transactions", e);
        }
    }
}