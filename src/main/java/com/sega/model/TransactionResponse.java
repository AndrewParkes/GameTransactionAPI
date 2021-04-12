package com.sega.model;

import com.sega.dao.Transaction;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TransactionResponse extends ResponseResult {

    @ApiModelProperty(notes = "List of transactions")
    private List<Transaction> transactions;

    public TransactionResponse(String resultMessage, int resultCode, List<Transaction> transactions) {
        super(resultMessage, resultCode);
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
