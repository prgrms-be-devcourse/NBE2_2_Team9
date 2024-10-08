package com.medinine.pillbuddy.global.advice;

import com.medinine.pillbuddy.global.exception.ErrorResponse;
import com.medinine.pillbuddy.global.exception.PillBuddyCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(PillBuddyCustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(PillBuddyCustomException e) {
        ErrorResponse response = ErrorResponse.of(e.getErrorCode().getHttpStatus(), e.getMessage());
        log.error("Error Message: {}", e.getErrorCode().getMessage());
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}
