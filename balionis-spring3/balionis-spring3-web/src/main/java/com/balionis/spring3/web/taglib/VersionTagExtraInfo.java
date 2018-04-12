package com.balionis.spring3.web.taglib;

import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.VariableInfo;

import com.balionis.spring3.web.AppDef;

/** */
public class VersionTagExtraInfo extends TagExtraInfo {

    /** */
    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[] {
            new VariableInfo(AppDef.APP_BUILD_VERSION, "String", true, VariableInfo.NESTED),
            new VariableInfo(AppDef.APP_BUILD_TIME, "String", true, VariableInfo.NESTED),
        };
    }
}