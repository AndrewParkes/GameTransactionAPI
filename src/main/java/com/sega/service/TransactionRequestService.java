package com.sega.service;

import com.sega.dao.Transaction;
import com.sega.db.DynamoDbService;
import com.sega.model.ResponseResult;
import com.sega.model.TransactionRequest;
import com.sega.model.TransactionSaveRequest;
import com.sega.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionRequestService {

    @Autowired
    DynamoDbService dynamoDbService;

    public ResponseResult getAllTransactions() {

        List<Transaction> transactions = dynamoDbService.getAllTransactions();

        return new TransactionResponse("Success", 200, transactions);

    }

    public ResponseResult getTransactionsByUser(TransactionRequest transactionRequest) {

        List<Transaction> transactions = dynamoDbService.getTransactionByAttributeEqualsValue("user", transactionRequest.getUser());

        return new TransactionResponse("Success", 200, transactions);

    }

    public ResponseResult save(TransactionSaveRequest transactionSaveRequest) {

        dynamoDbService.save(new Transaction(transactionSaveRequest));

        return new ResponseResult("Success", 200);
    }
}
