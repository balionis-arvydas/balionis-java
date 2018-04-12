package com.balionis.spring3.service;

import com.balionis.spring3.commons.AppClientException;

import com.balionis.spring3.model.AppModelID;

/** */
public class AppClientExceptionBuilder {

    public static class AppClientExceptionImpl extends AppClientException {
        private static final long serialVersionUID = 1000000000000000000L;
        
        /** */
        private AppClientExceptionImpl(String code, String[] args, String msg, Exception exc) {
            super(code, args, msg, exc);
        }       
    }
        
    /** */
    public static AppClientException newC101(AppModelID key) {
        final String C101 = "com.balionis.spring3.service.C101"; 
        final String C101_MSG = "Cannot find model {1}.";
        
        String[] args = new String[1];
        args[0] = String.valueOf(key.getKey());
        return new AppClientExceptionImpl(C101, args, C101_MSG, null);
    }
}
