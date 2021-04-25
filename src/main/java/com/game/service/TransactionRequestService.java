package com.game.service;

import com.game.dao.Transaction;
import com.game.db.DynamoDbService;
import com.game.model.ResponseResult;
import com.game.model.TransactionListResponse;
import com.game.model.TransactionProductRequest;
import com.game.model.TransactionResponse;
import com.game.model.TransactionSaveRequest;
import com.game.model.TransactionUpdateRequest;
import com.game.model.TransactionUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionRequestService {

    @Autowired
    DynamoDbService dynamoDbService;

    public ResponseResult getAllTransactions() {

        List<Transaction> transactions = dynamoDbService.getAllTransactions();

        return new TransactionListResponse("Success", 200, transactionsToTransactionResponse(transactions));

    }

    public ResponseResult getTransactionsByUser(TransactionUserRequest transactionRequest) {

        List<Transaction> transactions = dynamoDbService.getTransactionByAttributeEqualsValue("user", transactionRequest.getUser());

        return new TransactionListResponse("Success", 200, transactionsToTransactionResponse(transactions));

    }

    public ResponseResult getTransactionsByProduct(TransactionProductRequest transactionRequest) {

        List<Transaction> transactions = dynamoDbService.getTransactionByAttributeEqualsValue("product", transactionRequest.getProduct());

        return new TransactionListResponse("Success", 200, transactionsToTransactionResponse(transactions));

    }

    public ResponseResult save(TransactionSaveRequest transactionSaveRequest) {

        dynamoDbService.save(new Transaction(transactionSaveRequest));

        return new ResponseResult("Success", 200);
    }

    public ResponseResult update(TransactionUpdateRequest transactionUpdateRequest) {

        dynamoDbService.update(transactionUpdateRequest);

        return new ResponseResult("Success", 200);
    }

    private List<TransactionResponse> transactionsToTransactionResponse(List<Transaction> transactions) {
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionResponses.add(new TransactionResponse(transaction));
        }
        return transactionResponses;
    }
}
