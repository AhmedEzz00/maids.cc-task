package com.example.library_management_system.Error.exceptions;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException() {
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
