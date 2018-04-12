package com.balionis.spring3.web;

import java.io.Serializable;

public class AppModelForm implements Serializable {

    private static final long serialVersionUID = 0x1L;

    private String modelKey;
    private String name;
    private String type;

    public String getModelKey() {
        return modelKey;
    }
    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
	@Override
	public String toString() {
		return "AppModelForm [modelKey=" + modelKey + ", name=" + name
				+ ", type=" + type + "]";
	}
}