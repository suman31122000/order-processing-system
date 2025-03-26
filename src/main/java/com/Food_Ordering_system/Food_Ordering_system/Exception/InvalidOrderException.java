package com.Food_Ordering_system.Food_Ordering_system.Exception;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException(String message){
        super(message);
    }
}