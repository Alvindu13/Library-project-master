package com.library.web.exceptions;

public class UserNofFoundException extends RuntimeException {
    public UserNofFoundException(Long id) {
        super("User id not found : " + id);
    }

}
