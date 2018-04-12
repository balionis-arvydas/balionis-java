package com.balionis.spring3.web;

import com.balionis.spring3.commons.AppClientException;

/** */
public class AppClientExceptionBuilder {

    public static class AppClientExceptionImpl extends AppClientException {
        private static final long serialVersionUID = 1000000000000000000L;
        
        /** */
        private AppClientExceptionImpl(String code, String[] args, String msg, Exception exc) {
            super(code, args, msg, exc);
        }       
    }
        
    @Deprecated
    public static AppClientException newC200(String message) {
        final String C200 = "com.balionis.spring3.web.C200"; 
        final String C200_MSG = "{1}.";
        
        String[] args = new String[1];
        args[0] = message;
        return new AppClientExceptionImpl(C200, args, C200_MSG, null);
    }
}
