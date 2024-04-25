package com.example.library_management_system.Error.exceptions;

public  class DeleteAssosiatedRecordsException extends RuntimeException{
    public DeleteAssosiatedRecordsException() {}

    public DeleteAssosiatedRecordsException(String message) {
        super(message);
    }
}