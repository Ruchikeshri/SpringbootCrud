package com.stackroute.exception;

public class BlogNotFoundException extends RuntimeException{
    private String message;
    public BlogNotFoundException(){

    }
    public BlogNotFoundException(String message){
        super();
        this.message=message;
    }
}
