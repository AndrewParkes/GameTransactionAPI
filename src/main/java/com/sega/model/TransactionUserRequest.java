package com.sega.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TransactionUserRequest {

    private static final String USERNAME_EXAMPLE = "user123";
    private static final String USERNAME_DESCRIPTION = "Username who created the transaction";

    @ApiModelProperty(example = USERNAME_EXAMPLE,  required = true, notes = USERNAME_DESCRIPTION)
    String user;

    TransactionUserRequest() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
