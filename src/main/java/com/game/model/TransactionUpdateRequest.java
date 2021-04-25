package com.game.model;

import com.game.validator.TransactionRequestValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
@TransactionRequestValidator
public class TransactionUpdateRequest extends TransactionSaveRequest {

    protected static final String ID = "Id";
    protected static final String ID_EXAMPLE = "n4798d-fjklds-894njd";
    protected static final String ID_DESCRIPTION = "Id of the transaction";

    @NotNull(message = ID + CANNOT_BE_NULL)
    @NotBlank(message = ID + CANNOT_BE_MISSING_OR_EMPTY)
    @ApiModelProperty(example = ID_EXAMPLE, notes = ID_DESCRIPTION)
    String id;

    @ApiModelProperty(example = USERNAME_EXAMPLE, notes = USERNAME_DESCRIPTION)
    String user;

    @ApiModelProperty(example = PRODUCT_EXAMPLE, notes = PRODUCT_DESCRIPTION)
    String product;

    @ApiModelProperty(example = AMOUNT_EXAMPLE, notes = AMOUNT_DESCRIPTION)
    double amount;

    TransactionUpdateRequest() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
