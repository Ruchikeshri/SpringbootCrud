package com.stackroute.exception;

public class BlogAlreadyExistsException extends RuntimeException{
    private String message;
    public BlogAlreadyExistsException(){

    }
    public BlogAlreadyExistsException(String message){
        super();
        this.message=message;
    }
}
