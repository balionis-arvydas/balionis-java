package com.balionis.spring3.service.repository;

import com.balionis.spring3.commons.AppCrashException;
import com.balionis.spring3.commons.AppClientException;

import com.balionis.spring3.model.AppModel;
import com.balionis.spring3.model.AppModels;
import com.balionis.spring3.model.AppModelID;
import com.balionis.spring3.model.AppModelFilter;

public interface AppModelDAO {
	
	public AppModel read(AppModelID id) throws AppCrashException, AppClientException;
	
	public AppModelID write(AppModel record)  throws AppCrashException, AppClientException;

	public AppModels find(AppModelFilter filter) throws AppCrashException, AppClientException;
	
	public void remove(AppModelID id) throws AppCrashException, AppClientException;
	
}
