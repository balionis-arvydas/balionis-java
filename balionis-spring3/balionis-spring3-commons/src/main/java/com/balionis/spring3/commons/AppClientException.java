package com.balionis.spring3.commons;

/** */
public class AppClientException extends AppException {

    private static final long serialVersionUID = 1000000000000000000L;
  
    /** */
    protected AppClientException(String code, String[] args, String msg, Exception exc) {
        super(code, args, msg, exc);
    }
    
}
