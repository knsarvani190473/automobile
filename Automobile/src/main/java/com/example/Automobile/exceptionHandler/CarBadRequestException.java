package com.example.Automobile.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CarBadRequestException extends RuntimeException{
    private String errorCode;
    private String errorDescription;

    public CarBadRequestException(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription=errorDescription;
    }
    public String getErrorCode(){
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}

