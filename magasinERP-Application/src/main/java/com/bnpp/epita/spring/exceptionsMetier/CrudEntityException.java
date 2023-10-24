package com.bnpp.epita.spring.exceptionsMetier;

public class CrudEntityException extends Exception{
    public CrudEntityException(String message){
        super(message);
    }
}
