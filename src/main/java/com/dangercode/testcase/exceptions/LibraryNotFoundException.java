package com.dangercode.testcase.exceptions;


public class LibraryNotFoundException extends RuntimeException{

    public LibraryNotFoundException(String message) {
        super(message);
    }
}
