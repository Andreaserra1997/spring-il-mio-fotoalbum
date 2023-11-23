package com.experis.springalbum.exceptions;

public class CategoryNameUniqueException extends RuntimeException{
    public CategoryNameUniqueException(String message) {
        super(message);
    }
}
