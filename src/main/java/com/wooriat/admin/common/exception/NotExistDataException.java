package com.wooriat.admin.common.exception;

public class NotExistDataException extends RuntimeException {
    public NotExistDataException() {
        super();
    }

    public NotExistDataException(String message) {
        super(message);
    }

    public NotExistDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistDataException(Throwable cause) {
        super(cause);
    }
}
