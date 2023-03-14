package com.nameList.service.exception;

public class DuplicatedNameException extends Exception {

    public DuplicatedNameException() {
        super("There is duplicated names at the list");
    }
}
