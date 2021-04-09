package com.sega.controller;

import com.sega.model.ResponseResult;
import com.sega.model.TransactionRequest;
import com.sega.model.TransactionSaveRequest;
import com.sega.service.TransactionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionRequestService transactionRequestService;

    @PutMapping("/save")
    public ResponseEntity<ResponseResult> saveRequest(@RequestBody TransactionSaveRequest transactionSaveRequest) {

        return ResponseEntity.ok(transactionRequestService.save(
                transactionSaveRequest));
    }

    @PostMapping("/retrieveAll")
    public ResponseEntity<ResponseResult> retrieveAllRequest() {

        return ResponseEntity.ok(transactionRequestService.getAllTransactions());
    }

    @PostMapping("/retrieveByUser")
    public ResponseEntity<ResponseResult> retrieveAllRequest(@RequestBody TransactionRequest transactionRequest) {

        return ResponseEntity.ok(transactionRequestService.getTransactionsByUser(transactionRequest));
    }
}
