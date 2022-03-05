package dev.be.pongdang.controller.exceptionhandler;

import static dev.be.pongdang.common.enums.response.ReturnCode.UNKNOWN_ERROR;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.be.pongdang.common.exception.CustomException;
import dev.be.pongdang.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CommonResponse<?> handlerCustomException(CustomException e) {
        log.error("Break Exception", e);
        return getCommonResponse(e.getReturnCode(), e.getReturnMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse<?> handleException(Exception e) {
        log.error("Unknown Exception", e);
        return new CommonResponse<>(UNKNOWN_ERROR);
    }

    private CommonResponse getCommonResponse(String returnCode, String returnMessage) {
        return CommonResponse.builder()
                             .returnCode(returnCode)
                             .returnMessage(returnMessage)
                             .build();
    }

}
