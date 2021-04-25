package com.game.dao;

import com.game.model.TransactionSaveRequest;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.UUID;

@DynamoDbBean
public class Transaction {

    String id;
    String user;
    String product;
    Double amount;

    public Transaction() {
    }

    public Transaction(TransactionSaveRequest transactionSaveRequest) {
        this.id = UUID.randomUUID().toString();
        user = transactionSaveRequest.getUser();
        product = transactionSaveRequest.getProduct();
        amount = transactionSaveRequest.getAmount();
    }

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
