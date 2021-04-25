package com.game.controller;

import com.game.model.ResponseResult;
import com.game.model.TransactionListResponse;
import com.game.model.TransactionProductRequest;
import com.game.model.TransactionSaveRequest;
import com.game.model.TransactionUpdateRequest;
import com.game.model.TransactionUserRequest;
import com.game.service.TransactionRequestService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionRequestService transactionRequestService;

    @ApiOperation(value = "save Transaction")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = ResponseResult.class),
            @ApiResponse(code = 400, message = "Unable to save", response = ResponseResult.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorisation", value = "API Credentials: user authorization", paramType = "header", example = "xxxxxx", dataTypeClass = String.class)})
    @PostMapping("/save")
    public ResponseEntity<ResponseResult> saveRequest(@Validated @RequestBody TransactionSaveRequest transactionSaveRequest) {

        return ResponseEntity.ok(transactionRequestService.save(
                transactionSaveRequest));
    }

    @ApiOperation(value = "retrieve all Transaction")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = TransactionListResponse.class),
            @ApiResponse(code = 400, message = "Unable to retrieve Transactions", response = ResponseResult.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorisation", value = "API Credentials: user authorization", paramType = "header", example = "xxxxxx", dataTypeClass = String.class)})
    @PostMapping("/retrieveall")
    public ResponseEntity<ResponseResult> retrieveAllRequest() {

        return ResponseEntity.ok(transactionRequestService.getAllTransactions());
    }

    @ApiOperation(value = "retrieve all Transaction")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = TransactionListResponse.class),
            @ApiResponse(code = 400, message = "Unable to retrieve Transactions", response = ResponseResult.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorisation", value = "API Credentials: user authorization", paramType = "header", example = "xxxxxx", dataTypeClass = String.class)})
    @PostMapping("/retrievebyuser")
    public ResponseEntity<ResponseResult> retrieveAllRequest(@RequestBody TransactionUserRequest transactionRequest) {

        return ResponseEntity.ok(transactionRequestService.getTransactionsByUser(transactionRequest));
    }

    @ApiOperation(value = "retrieve all Transaction")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = TransactionListResponse.class),
            @ApiResponse(code = 400, message = "Unable to retrieve Transactions", response = ResponseResult.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorisation", value = "API Credentials: user authorization", paramType = "header", example = "xxxxxx", dataTypeClass = String.class)})
    @PostMapping("/retrievebyproduct")
    public ResponseEntity<ResponseResult> retrieveAllRequest(@RequestBody TransactionProductRequest transactionRequest) {

        return ResponseEntity.ok(transactionRequestService.getTransactionsByProduct(transactionRequest));
    }

    @ApiOperation(value = "update Transaction using id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = ResponseResult.class),
            @ApiResponse(code = 400, message = "Unable to update", response = ResponseResult.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorisation", value = "API Credentials: user authorization", paramType = "header", example = "xxxxxx", dataTypeClass = String.class)})
    @PutMapping("/update")
    public ResponseEntity<ResponseResult> updateRequest(@Validated @RequestBody TransactionUpdateRequest transactionUpdateRequest) {

        return ResponseEntity.ok(transactionRequestService.update(
                transactionUpdateRequest));
    }
}
