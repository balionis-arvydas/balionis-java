package com.balionis.spring3.service.repository;

import com.balionis.spring3.commons.AppCrashException;
import com.balionis.spring3.commons.AppClientException;

public interface AppPropertyDAO {
	
	public Long readNext() throws AppCrashException, AppClientException;
	
}
