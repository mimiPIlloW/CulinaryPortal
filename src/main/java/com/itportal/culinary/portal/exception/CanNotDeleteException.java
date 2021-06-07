package com.itportal.culinary.portal.exception;

public class CanNotDeleteException extends RuntimeException{
    public CanNotDeleteException(String message){
        super("Unable to delete - " + message);
    }
}
