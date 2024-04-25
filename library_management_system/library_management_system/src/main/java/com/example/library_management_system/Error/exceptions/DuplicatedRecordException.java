package com.example.library_management_system.Error.exceptions;

public class DuplicatedRecordException  extends RuntimeException{
    public DuplicatedRecordException() {

    }

    public DuplicatedRecordException(String message) {
        super(message);
    }
}