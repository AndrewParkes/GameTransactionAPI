package com.sega.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TransactionProductRequest {

    private static final String PRODUCT_EXAMPLE = "Sega";
    private static final String PRODUCT_DESCRIPTION = "Product purchased by the user";

    @ApiModelProperty(example = PRODUCT_EXAMPLE,  required = true, notes = PRODUCT_DESCRIPTION)
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
