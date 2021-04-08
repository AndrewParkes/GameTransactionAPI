package com.sega.service;

import com.sega.db.DynamoDb;
import com.sega.exception.ProcessingRequestException;
import com.sega.model.TransactionRequest;
import com.sega.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Service
public class TransactionRequestService {

    @Autowired
    DynamoDb dynamoDb;

    public TransactionResponse save(TransactionRequest transactionRequest) {

        Region region = Region.US_EAST_2;
        DynamoDbClient ddb = DynamoDbClient.builder()
                .region(region)
                .build();

        int count = dynamoDb.getDynamoDBItem(ddb, "Transaction", "id", "0", "#a");

        System.out.println(count);
        ddb.close();

        throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 404, "transaction not found");

        /*DocumentRequest documentRequest = (DocumentRequest) baseRequest;
        return new DocumentResponse(ResponseResultEnum.DOCUMENTS, EigerResultCodeEnum.FULFILLED, getDocumentForRequest(
                documentRequest));*/

    }
}
