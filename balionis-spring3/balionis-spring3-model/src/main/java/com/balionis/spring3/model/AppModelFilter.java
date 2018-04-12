package com.balionis.spring3.model;

public class AppModelFilter implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private AppModel model;

    public AppModelFilter() {
    }

    public AppModel getModel() {
    	return model;
    }

    public void setModel(AppModel model) {
    	this.model = model;
    }

    public boolean hasModel() {
    	return (model != null);
    }

	@Override
	public String toString() {
		return "AppModelFilter [model=" + model + "]";
	}
}