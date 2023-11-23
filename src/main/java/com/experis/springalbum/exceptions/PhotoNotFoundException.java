package com.experis.springalbum.exceptions;

public class PhotoNotFoundException extends RuntimeException{
    public PhotoNotFoundException(String message) {
        super(message);
    }
}
