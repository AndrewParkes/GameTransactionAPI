package com.sega.controller;

import com.sega.model.ResponseResult;
import com.sega.model.TransactionProductRequest;
import com.sega.model.TransactionSaveRequest;
import com.sega.model.TransactionUserRequest;
import com.sega.service.TransactionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionRequestService transactionRequestService;

    @PutMapping("/save")
    public ResponseEntity<ResponseResult> saveRequest(@Validated @RequestBody TransactionSaveRequest transactionSaveRequest) {

        return ResponseEntity.ok(transactionRequestService.save(
                transactionSaveRequest));
    }

    @PostMapping("/retrieveAll")
    public ResponseEntity<ResponseResult> retrieveAllRequest() {

        return ResponseEntity.ok(transactionRequestService.getAllTransactions());
    }

    @PostMapping("/retrieveByUser")
    public ResponseEntity<ResponseResult> retrieveAllRequest(@RequestBody TransactionUserRequest transactionRequest) {

        return ResponseEntity.ok(transactionRequestService.getTransactionsByUser(transactionRequest));
    }

    @PostMapping("/retrieveByProduct")
    public ResponseEntity<ResponseResult> retrieveAllRequest(@RequestBody TransactionProductRequest transactionRequest) {

        return ResponseEntity.ok(transactionRequestService.getTransactionsByProduct(transactionRequest));
    }
}
