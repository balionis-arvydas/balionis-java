package com.balionis.spring3.commons;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/** */
public class AppException extends Exception {

    private static final long serialVersionUID = 1000000000000000000L;
  
    private String    code;
    private String[]  args;

    /** */
    public String getCode() {
        return (code);
    }

    /** */
    public String[] getArgs() {
        return (this.args);
    }

    /** */
    public String toString() {
        StringBuilder buf = new StringBuilder();

        buf.append("{ code=\"" + code + "\", args=[");
        for (int i=0; i < args.length; i++) {
            if (i > 0) {
                buf.append(",");
            }
            buf.append("\"" + args[i] + "\"");
        }
        buf.append("]");
        if (getMessage() != null) {
            buf.append(", message=\"" + getMessage() + "\"");
        }
        
        if (getCause() != null) {
            PrintStream out = null;
            try {
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                out = new PrintStream(bout);
                getCause().printStackTrace(out);

                buf.append(", reason=\"" + out.toString() + "\"");
          
            } finally {
                if (out != null) out.close();
            }
        }
        buf.append("}");
      
        return buf.toString();
    }

    /** */
    protected AppException(String code, String[] args, String message, Exception cause) {
        super(message, cause);

        this.code = code;
        this.args = args == null ? (new String[0]) : args;
    }
}
