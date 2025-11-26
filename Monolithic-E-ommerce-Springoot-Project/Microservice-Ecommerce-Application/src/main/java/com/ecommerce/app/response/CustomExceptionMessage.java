package com.ecommerce.app.response;

import lombok.Getter;

public class CustomExceptionMessage extends RuntimeException{

    private  final String message;
    @Getter
    private  final int errorCode;

    public CustomExceptionMessage(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
