package com.game.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TransactionListResponse extends ResponseResult {

    @ApiModelProperty(notes = "List of transactions")
    private List<TransactionResponse> transactions;

    public TransactionListResponse(String resultMessage, int resultCode, List<TransactionResponse> transactions) {
        super(resultMessage, resultCode);
        this.transactions = transactions;
    }

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResponse> transactions) {
        this.transactions = transactions;
    }
}
