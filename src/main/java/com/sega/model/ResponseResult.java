package com.sega.model;

import io.swagger.annotations.ApiModelProperty;

public class ResponseResult {

    @ApiModelProperty(notes = "Additional information regarding the result", example = "Request is successful.")
    private String resultMessage;

    @ApiModelProperty(notes = "Status Code for success or failures", example = "200")
    private int resultCode;

    public ResponseResult(String resultMessage, int resultCode) {
        this.resultMessage = resultMessage;
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
