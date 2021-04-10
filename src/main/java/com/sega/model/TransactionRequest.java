package com.sega.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
public class TransactionRequest {

    private static final String USERNAME_EXAMPLE = "user123";
    private static final String USERNAME_DESCRIPTION = "Username who created the transaction";

    private static final String PRODUCT_EXAMPLE = "Sega";
    private static final String PRODUCT_DESCRIPTION = "Product purchased by the user";

    private static final String AMOUNT_EXAMPLE = "120.99";
    private static final String AMOUNT_DESCRIPTION = "Amount for product";

    @ApiModelProperty(example = USERNAME_EXAMPLE,  required = true, notes = USERNAME_DESCRIPTION)
    String user;

    @ApiModelProperty(example = PRODUCT_EXAMPLE,  required = true, notes = PRODUCT_DESCRIPTION)
    String product;

    @ApiModelProperty(example = AMOUNT_EXAMPLE,  required = true, notes = AMOUNT_DESCRIPTION)
    Double minAmount;

    @ApiModelProperty(example = AMOUNT_EXAMPLE,  required = true, notes = AMOUNT_DESCRIPTION)
    Double maxAmount;

    TransactionRequest() {
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

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }
}
