package com.sega.model;

import io.swagger.annotations.ApiModelProperty;

public class ResponseResult {

    @ApiModelProperty(notes = "Additional information regarding the result", example = "Request is un/successful.")
    private String resultMessage;

    @ApiModelProperty(notes = "Status Code for success or failures", example = "4/200")
    private Integer resultCode;

    public ResponseResult(String resultMessage, Integer resultCode) {
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

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }
}
