package com.game.model;

import com.game.validator.TransactionRequestValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
@TransactionRequestValidator
public class TransactionSaveRequest {

    protected static final String CANNOT_BE_MISSING_OR_EMPTY = " cannot be missing or empty";
    protected static final String CANNOT_BE_NULL = " cannot be null";

    protected static final String USERNAME = "Username";
    protected static final String USERNAME_EXAMPLE = "user123";
    protected static final String USERNAME_DESCRIPTION = "Username creating the transaction";

    protected static final String PRODUCT = "Product";
    protected static final String PRODUCT_EXAMPLE = "Mega Drive";
    protected static final String PRODUCT_DESCRIPTION = "The name of the product for the transaction";

    protected static final String AMOUNT = "Amount";
    protected static final String AMOUNT_EXAMPLE = "189.99";
    protected static final String AMOUNT_DESCRIPTION = "GB£ amount of the transaction";

    @NotNull(message = USERNAME + CANNOT_BE_NULL)
    @NotBlank(message = USERNAME + CANNOT_BE_MISSING_OR_EMPTY)
    @ApiModelProperty(example = USERNAME_EXAMPLE, required = true, notes = USERNAME_DESCRIPTION)
    String user;

    @NotNull(message = PRODUCT + CANNOT_BE_NULL)
    @NotBlank(message = PRODUCT + CANNOT_BE_MISSING_OR_EMPTY)
    @ApiModelProperty(example = PRODUCT_EXAMPLE, required = true, notes = PRODUCT_DESCRIPTION)
    String product;

    @NotNull(message = AMOUNT + CANNOT_BE_NULL)
    @ApiModelProperty(example = AMOUNT_EXAMPLE, required = true, notes = AMOUNT_DESCRIPTION)
    Double amount;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
