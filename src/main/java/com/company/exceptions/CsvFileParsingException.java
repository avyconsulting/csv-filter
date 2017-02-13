package com.company.exceptions;

public class CsvFileParsingException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public CsvFileParsingException (Exception cause) {
        super(cause);
    }

}
