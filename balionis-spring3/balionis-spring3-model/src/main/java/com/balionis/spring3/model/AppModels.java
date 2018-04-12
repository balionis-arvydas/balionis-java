package com.balionis.spring3.model;

import java.util.List;
import java.util.ArrayList;

public class AppModels implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<AppModel> values;
	
    public AppModels() {
    	values = new ArrayList<AppModel>();
    }

    public List<AppModel> getValues() {
    	return this.values;
    }

    public void setValues(List<AppModel> list) {
		this.values.clear();
		this.values.addAll(list);
    }

	public boolean isEmpty() {
		return (values != null ? values.isEmpty() : true);
	}
    
    public void add(AppModel obj) {
		this.values.add(obj);
    }

	@Override
	public String toString() {
		return "AppModels [values=" + values + "]";
	}
}