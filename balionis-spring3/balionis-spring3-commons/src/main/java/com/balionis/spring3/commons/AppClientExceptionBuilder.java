package com.balionis.spring3.commons;

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
    @Deprecated
    public static AppClientException newC000(String message) {
        final String C000 = "com.balionis.spring3.commons.C000"; 
        final String C000_MSG = "{1}";
        
        String[] args = new String[1];
        args[0] = message;
        return new AppClientExceptionImpl(C000, args, C000_MSG, null);
    }

    /** */
    public static AppClientException newC001(String fname) {
        final String C001 = "com.balionis.spring3.C001"; 
        final String C001_MSG = "Cannot find file {1}.";
        
        String[] args = new String[1];
        args[0] = fname;
        return new AppClientExceptionImpl(C001, args, C001_MSG, null);
    }
}
