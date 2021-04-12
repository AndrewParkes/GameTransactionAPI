package com.sega.dao;

import com.sega.model.TransactionSaveRequest;
import io.swagger.annotations.ApiModelProperty;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.UUID;

@DynamoDbBean
public class Transaction {

    @ApiModelProperty(example = "n4798d-fjklds-894njd")
    String id;
    @ApiModelProperty(example = "user123")
    String user;
    @ApiModelProperty(example = "Genesis")
    String product;
    @ApiModelProperty(example = "100.00")
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
