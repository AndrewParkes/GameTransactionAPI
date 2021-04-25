package com.game.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
public class TransactionProductRequest {

    private static final String CANNOT_BE_MISSING_OR_EMPTY = " cannot be missing or empty";
    private static final String CANNOT_BE_NULL = " cannot be null";

    private static final String PRODUCT = "Product";
    private static final String PRODUCT_EXAMPLE = "Mega Drive";
    private static final String PRODUCT_DESCRIPTION = "The name of the product for the transaction";

    @NotNull(message = PRODUCT + CANNOT_BE_NULL)
    @NotBlank(message = PRODUCT + CANNOT_BE_MISSING_OR_EMPTY)
    @ApiModelProperty(example = PRODUCT_EXAMPLE, required = true, notes = PRODUCT_DESCRIPTION)
    String product;

    TransactionProductRequest() {
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
