package com.chains.library.exception;

public class ServiceException extends RuntimeException{
    String code;
    public ServiceException(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }

    public ServiceException(String code, String message){
        super(message);
        this.code = code;
    }
}
