package com.balionis.spring3.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.balionis.spring3.web.AppDef;
import com.balionis.spring3.commons.AppCrashException;
import com.balionis.spring3.commons.AppVersion;

/** */
public class VersionTag extends BodyTagSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionTag.class);
	
    public static final long serialVersionUID = 0L;

    /** */
    public VersionTag() {
    }

    /** */
    public int doStartTag() throws JspTagException {
        return EVAL_BODY_AGAIN;
    }

    /** */
    public void doInitBody() throws JspTagException {
        setDisplayAttributes();
    }

    /** */
    public int doAfterBody() throws JspTagException {
        int outVal = SKIP_BODY;
        try {
            BodyContent body = getBodyContent();
            body.writeOut(getPreviousOut());
            return outVal;
        } catch (IOException ex) {
        	LOGGER.error("doAfterBody:...", ex);
            throw new JspTagException(ex);
        }
    }

    /** */
    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }

    /** */
    public int setDisplayAttributes() throws JspTagException {
        int outVal = SKIP_BODY;
        
        AppVersion version = null;
        try {
            version = AppVersion.builder().withClassLoader(VersionTag.class.getClassLoader()).build();        
            pageContext.setAttribute(AppDef.APP_BUILD_VERSION, version.getBuildNumber());
            pageContext.setAttribute(AppDef.APP_BUILD_TIME, version.getBuildTime());
        	
        } catch(AppCrashException exc) {
        	LOGGER.error("setDisplayAttributes: version={}", version);
        	throw new JspTagException(exc);
        }
        
        outVal = EVAL_BODY_BUFFERED;
        return outVal;
    }
}
