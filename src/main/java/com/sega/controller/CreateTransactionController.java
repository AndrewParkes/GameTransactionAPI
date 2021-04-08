package com.sega.controller;

import com.sega.exception.ProcessingRequestException;
import com.sega.model.ResponseResult;
import com.sega.model.TransactionRequest;
import com.sega.service.TransactionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/create")
public class CreateTransactionController {

    @Autowired
    TransactionRequestService transactionRequestService;

    @PatchMapping
    public ResponseEntity<ResponseResult> transferRequest(@RequestBody @ModelAttribute TransactionRequest transactionRequest) {

        return ResponseEntity.ok(transactionRequestService.save(
                transactionRequest));
    }
}
