package com.game.db;

import com.game.dao.Transaction;
import com.game.exception.ProcessingRequestException;
import com.game.model.TransactionUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

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

        try {
            DynamoDbTable<Transaction> table = enhancedClient.table(TRANSACTION, TableSchema.fromBean(Transaction.class));

            return Arrays.asList(table.scan().items().stream().toArray(Transaction[]::new));
        } catch (DynamoDbException e) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 602, "Unable to retrieve transactions", e);
        }
    }

    public void save(Transaction transaction) {

        try {
            DynamoDbTable<Transaction> table = enhancedClient.table(TRANSACTION, TableSchema.fromBean(Transaction.class));

            table.putItem(transaction);

        } catch (DynamoDbException e) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 603, "Unable to save transaction", e);
        }
    }

    public List<Transaction> getTransactionByAttributeEqualsValue(String attribute, String value) {
        AttributeValue attributeValue = AttributeValue.builder()
                .s(value)// Attribute is a String
                .build();

        return getTransactionByAttributeEqualsValue(attribute, attributeValue);
    }

    public List<Transaction> getTransactionByAttributeEqualsValue(String attribute, int value) {

        AttributeValue attributeValue = AttributeValue.builder()
                .n(String.valueOf(value))// Attribute is a Number
                .build();

        return getTransactionByAttributeEqualsValue(attribute, attributeValue);
    }

    private List<Transaction> getTransactionByAttributeEqualsValue(String attribute, AttributeValue attributeValue) {

        try {
            DynamoDbTable<Transaction> table = enhancedClient.table(TRANSACTION, TableSchema.fromBean(Transaction.class));

            Map<String, AttributeValue> myMap = new HashMap<>();
            myMap.put(":" + attribute, attributeValue);

            Map<String, String> myExMap = new HashMap<>();
            myExMap.put("#" + attribute, attribute);

            Expression expression = Expression.builder()
                    .expression("#" + attribute + " = :" + attribute)
                    .expressionValues(myMap)
                    .expressionNames(myExMap)
                    .build();

            ScanEnhancedRequest enhancedRequest = ScanEnhancedRequest.builder()
                    .filterExpression(expression)
                    .build();

            return Arrays.asList(table.scan(enhancedRequest).items().stream().toArray(Transaction[]::new));
        } catch (DynamoDbException e) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 604, "Unable to retrieve transactions", e);
        }
    }

    public void update(TransactionUpdateRequest transactionUpdateRequest) {

        //IS there a cleaner way of doing this?

        if (transactionUpdateRequest.getAmount() != null) {
            update("id", transactionUpdateRequest.getId(), "amount", String.valueOf(transactionUpdateRequest.getAmount()));
        }
        if (transactionUpdateRequest.getProduct() != null) {
            update("id", transactionUpdateRequest.getId(), "product", String.valueOf(transactionUpdateRequest.getProduct()));
        }
        if (transactionUpdateRequest.getUser() != null) {
            update("id", transactionUpdateRequest.getId(), "user", String.valueOf(transactionUpdateRequest.getUser()));
        }
    }

    private void update(String key,
                        String keyVal,
                        String name,
                        String updateVal) {

        HashMap<String, AttributeValue> itemKey = new HashMap<>();

        itemKey.put(key, AttributeValue.builder().s(keyVal).build());

        HashMap<String, AttributeValueUpdate> updatedValues =
                new HashMap<>();

        updatedValues.put(name, AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(updateVal).build())
                .action(AttributeAction.PUT)
                .build());

        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(TRANSACTION)
                .key(itemKey)
                .attributeUpdates(updatedValues)
                .build();

        try {
            ddb.updateItem(request);
        } catch (ResourceNotFoundException e) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 605, "Unable to update transaction. Transaction not found", e);
        } catch (DynamoDbException e) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 606, "Unable to update transaction", e);
        }
    }
}
