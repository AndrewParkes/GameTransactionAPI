package com.sega.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
public class TransactionRequest {

    private static final String CANNOT_BE_MISSING_OR_EMPTY = " cannot be missing or empty";
    private static final String CANNOT_BE_NULL = " cannot be null";

    private static final String USERNAME = "Username";
    private static final String USERNAME_EXAMPLE = "user123";
    private static final String USERNAME_DESCRIPTION = "Username creating the transaction";

    @NotNull(message = USERNAME + CANNOT_BE_NULL)
    @NotBlank(message = USERNAME + CANNOT_BE_MISSING_OR_EMPTY)
    @ApiModelProperty(example = USERNAME_EXAMPLE,  required = true, notes = USERNAME_DESCRIPTION)
    String user;

    TransactionRequest() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
