package com.balionis.spring3.service;

import com.balionis.spring3.commons.AppCrashException;
import com.balionis.spring3.commons.AppClientException;

import com.balionis.spring3.model.AppModel;
import com.balionis.spring3.model.AppModels;
import com.balionis.spring3.model.AppModelID;
import com.balionis.spring3.model.AppModelFilter;

public interface AppManager {

	public AppModels find(AppModelFilter filter) throws AppCrashException, AppClientException;
	
	public AppModel read(AppModelID id) throws AppCrashException, AppClientException;
	
	public AppModelID write(AppModel model) throws AppCrashException, AppClientException;

	public void remove(AppModelID id) throws AppCrashException, AppClientException;
	
}
