package com.sega.controller;

import com.sega.exception.ProcessingRequestException;
import com.sega.model.ResponseResult;
import com.sega.model.TransactionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/create")
public class CreateTransactionController {

    @PatchMapping
    public ResponseEntity<ResponseResult> transferRequest(@RequestBody @ModelAttribute TransactionRequest transactionRequest) {

        throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 404, "transaction not found");
        //return ok(null);
    }
}
