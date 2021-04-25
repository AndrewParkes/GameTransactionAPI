package com.game.validator;

import com.game.exception.ProcessingRequestException;
import com.game.model.TransactionSaveRequest;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class TransactionRequestValidation implements ConstraintValidator<TransactionRequestValidator, TransactionSaveRequest> {

    @Override
    public void initialize(TransactionRequestValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(TransactionSaveRequest transactionSaveRequest, ConstraintValidatorContext constraintValidatorContext) {
        checkPositiveAmount(transactionSaveRequest);
        check2DecimalPlaces(transactionSaveRequest);
        return true;
    }

    private void check2DecimalPlaces(TransactionSaveRequest transactionSaveRequest) {
        if (BigDecimal.valueOf(transactionSaveRequest.getAmount()).scale() > 2) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 607, "Transaction amount should only have 2 decimal places");
        }
    }

    private void checkPositiveAmount(TransactionSaveRequest transactionSaveRequest) {
        if (transactionSaveRequest.getAmount() < 0) {
            throw new ProcessingRequestException(HttpStatus.BAD_REQUEST, 606, "Transaction amount should be positive");
        }
    }

}