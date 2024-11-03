package com.bank_account.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourseNotFoundException extends RuntimeException {

    public ResourseNotFoundException(String resourseName, String filedName, String filedValue) {
        super(String.format("%s not found with the given data %s : %s", resourseName, filedName, filedValue));
    }
}
