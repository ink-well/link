package com.inkwell.errors;

public class LinkAppException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public LinkAppException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public LinkAppException() {
        super();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
