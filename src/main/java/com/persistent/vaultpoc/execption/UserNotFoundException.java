package com.persistent.vaultpoc.execption;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) { super(message);}
}
