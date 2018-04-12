package com.balionis.spring3.service;

import com.balionis.spring3.commons.AppCrashException;

/** */
public class AppCrashExceptionBuilder {

    public static class AppCrashExceptionImpl extends AppCrashException {
        private static final long serialVersionUID = 1000000000000000000L;
        
        /** */
        private AppCrashExceptionImpl(String code, String[] args, String msg, Exception exc) {
            super(code, args, msg, exc);
        }       
    }
    
    /** */
    public static AppCrashException newS101(String propertyCode) {
        final String S101 = "com.mycompany.myproject.service.S101";
        final String S101_MSG = "Cannot find property with code {1}.";
        
        String[] args = new String[1];
        args[0] = propertyCode;
        return new AppCrashExceptionImpl(S101, args, S101_MSG, null);
    }
}
