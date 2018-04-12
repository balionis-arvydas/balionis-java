package com.balionis.spring3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.balionis.spring3.commons.AppCrashException;
import com.balionis.spring3.commons.AppClientException;
import com.balionis.spring3.model.AppModel;
import com.balionis.spring3.model.AppModels;
import com.balionis.spring3.model.AppModelID;
import com.balionis.spring3.model.AppModelFilter;
import com.balionis.spring3.service.repository.AppModelDAO;

import com.balionis.spring3.service.repository.AppPropertyDAO;

@Service("manager")
public class AppManagerImpl implements AppManager {

	public static final int DEFAULT_BRAND_PK = 1;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AppManagerImpl.class);
	
	private AppPropertyDAO propertyDao;
	private AppModelDAO modelDao;

    @Autowired
	public void setPropertyDAO(@Qualifier("propertyDao") AppPropertyDAO propertyDao) {
		this.propertyDao = propertyDao;
	}

    @Autowired
	public void setModelDAO(@Qualifier("modelDao") AppModelDAO modelDao) {
		this.modelDao = modelDao;
	}

	public AppModels find(AppModelFilter filter) throws AppCrashException, AppClientException {
		LOGGER.debug("find: filter={}", filter);
    	
		AppModels models = modelDao.find(filter);

		LOGGER.debug("find: models={}", models);
		
		return models;
	}
	
	public AppModel read(AppModelID id) throws AppCrashException, AppClientException {
		LOGGER.debug("read: id={}", id);
    	
		AppModel model = modelDao.read(id);

		LOGGER.debug("read: model={}", model);
		
		return model;
	}

    @Transactional	
	public AppModelID write(AppModel model) throws AppCrashException, AppClientException {
		
		LOGGER.debug("write: model={}", model);
		
		if (model.getId() == null || model.getId().getKey() == null) {
			long next = propertyDao.readNext();
			AppModelID id = new AppModelID(new Long(next));
			model = new AppModel(id, model);
		}
		
		AppModelID id = modelDao.write(model);

		LOGGER.debug("write: id={}", id);
		
		return id;		
	}

    @Transactional
	public void remove(AppModelID id) throws AppCrashException, AppClientException {
		
		LOGGER.debug("remove: id={}", id);

		AppModel record = new AppModel(id);
		
		AppModelFilter filter = new AppModelFilter();
		filter.setModel(record);
		
		AppModels records = modelDao.find(filter);
		if (records.isEmpty()) {
			throw AppClientExceptionBuilder.newC101(id);
		} else {
			modelDao.remove(id);
		}

		LOGGER.debug("remove: id={} deleted.", id);
	}
}
