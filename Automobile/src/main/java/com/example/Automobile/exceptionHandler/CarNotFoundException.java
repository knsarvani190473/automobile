package com.example.Automobile.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException {
    private String errorCode;
    private String errorDescription;

    public CarNotFoundException(String errorCode, String errorDescription) {
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
