package com.example.Automobile.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CarServiceExceptionHandler {

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCarServiceException(CarNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorDescription());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CarInternalErrorException.class)
    public ResponseEntity<ErrorResponse> handleCarServiceException(CarInternalErrorException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorDescription());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(CarBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleCarServiceException(CarBadRequestException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorDescription());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    static class ErrorResponse {
        private String errorCode;
        private String errorDescription;

        public String getErrorCode() {
            return errorCode;
        }

        public String getErrorDescription() {
            return errorDescription;
        }


        public ErrorResponse(String errorCode, String errorDescription) {
            this.errorCode = errorCode;
            this.errorDescription = errorDescription;
        }
    }

}
