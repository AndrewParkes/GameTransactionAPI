package com.game.model;

import com.game.dao.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TransactionResponse {

    @ApiModelProperty(example = "n4798d-fjklds-894njd")
    String id;
    @ApiModelProperty(example = "user123")
    String user;
    @ApiModelProperty(example = "Genesis")
    String product;
    @ApiModelProperty(example = "100.00")
    Double amount;

    public TransactionResponse(Transaction transaction) {
        id = transaction.getId();
        user = transaction.getUser();
        product = transaction.getProduct();
        amount = transaction.getAmount();
    }

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
