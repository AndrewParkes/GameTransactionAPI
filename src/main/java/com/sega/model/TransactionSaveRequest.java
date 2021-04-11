package com.sega.model;

import com.sega.validator.TransactionRequestValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
@TransactionRequestValidator
public class TransactionSaveRequest {

    private static final String CANNOT_BE_MISSING_OR_EMPTY = " cannot be missing or empty";
    private static final String CANNOT_BE_NULL = " cannot be null";

    private static final String USERNAME = "Username";
    private static final String USERNAME_EXAMPLE = "user123";
    private static final String USERNAME_DESCRIPTION = "Username creating the transaction";

    private static final String PRODUCT = "Product";
    private static final String PRODUCT_EXAMPLE = "Mega Drive";
    private static final String PRODUCT_DESCRIPTION = "The name of the product for the transaction";

    private static final String AMOUNT = "Amount";
    private static final String AMOUNT_EXAMPLE = "189.99";
    private static final String AMOUNT_DESCRIPTION = "GB£ amount of the transaction";

    @NotNull(message = USERNAME + CANNOT_BE_NULL)
    @NotBlank(message = USERNAME + CANNOT_BE_MISSING_OR_EMPTY)
    @ApiModelProperty(example = USERNAME_EXAMPLE,  required = true, notes = USERNAME_DESCRIPTION)
    String user;

    @NotNull(message = PRODUCT + CANNOT_BE_NULL)
    @NotBlank(message = PRODUCT + CANNOT_BE_MISSING_OR_EMPTY)
    @ApiModelProperty(example = PRODUCT_EXAMPLE,  required = true, notes = PRODUCT_DESCRIPTION)
    String product;

    @NotNull(message = AMOUNT + CANNOT_BE_NULL)
    @ApiModelProperty(example = AMOUNT_EXAMPLE,  required = true, notes = AMOUNT_DESCRIPTION)
    double amount;

    TransactionSaveRequest() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
