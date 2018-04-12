package com.balionis.spring3.commons;

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
    @Deprecated
    public static AppCrashException newS000(String message) {
        final String S000 = "com.balionis.spring3.commons.S000";
        final String S000_MSG = "AppCrashException: {1}.";
        
        String[] args = new String[1];
        args[0] = message;
        return new AppCrashExceptionImpl(S000, args, S000_MSG, null);
    }

    /** */
    public static AppCrashException newS001(String resource) {
        final String S001 = "com.balionis.spring3.S001";
        final String S001_MSG = "Cannot find resource {1}.";
        
        String[] args = new String[1];
        args[0] = resource;
        return new AppCrashExceptionImpl(S001, args, S001_MSG, null);
    }
}
