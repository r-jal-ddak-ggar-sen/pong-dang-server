package dev.be.pongdang.common.enums.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnCode implements CodeEnum, TextEnum, ReturnEnum {

    SUCCESS("0000", "Success."),

    NOT_EXIST("4000", "Not Exist"),
    EXIST_NICKNAME("4001", "Already Exist NickName"),

    UNKNOWN_ERROR("9999", "Unable to process your request. Please try again later.");

    private String code;
    private String text;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getReturnCode() {
        return getCode();
    }

    @Override
    public String getReturnMessage() {
        return getText();
    }
}
