package com.balionis.spring3.web;

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
    
    @Deprecated
    public static AppCrashException newS200(String message) {
        final String S200 = "com.balionis.spring3.web.S000";
        final String S200_MSG = "{1}";
        
        String[] args = new String[1];
        args[0] = message;
        return new AppCrashExceptionImpl(S200, args, S200_MSG, null);
    }
}
