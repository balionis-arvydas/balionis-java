package com.balionis.spring3.service.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.balionis.spring3.commons.AppClientException;
import com.balionis.spring3.commons.AppCrashException;
import com.balionis.spring3.service.AppClientExceptionBuilder;
import com.balionis.spring3.model.AppModel;
import com.balionis.spring3.model.AppModelID;
import com.balionis.spring3.model.AppModelFilter;
import com.balionis.spring3.model.AppModels;

@Repository("modelDAO2")
public class AppModelDAOImpl2 extends HibernateDaoSupport implements AppModelDAO {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AppModelDAOImpl2.class);
	
	public AppModel read(final AppModelID key) throws AppCrashException, AppClientException {
		LOGGER.debug("read: key={}", key);
    	
    	AppModel template = getHibernateTemplate().load(AppModel.class, key);
    	
    	if (template == null) {
    		LOGGER.warn("read: can not find template for key={}", key);
    		throw AppClientExceptionBuilder.newC101(key);
    	}
    	
		LOGGER.debug("read: template={}", template);
		return template;
	}
	
	public AppModelID write(final AppModel template)  throws AppCrashException, AppClientException {
		LOGGER.debug("write: template={}", template);
		
		getHibernateTemplate().saveOrUpdate(template);

		LOGGER.debug("write: updated template={}", template);
    	
    	return template.getId();    	
	}

	public AppModels find(final AppModelFilter filter) throws AppCrashException, AppClientException {
		
    	LOGGER.debug("find: filter=" + filter);
		
    	@SuppressWarnings ("unchecked")
    	List<AppModel> found = getHibernateTemplate().executeFind(new HibernateCallback<List<AppModel>>() {
    		public List<AppModel> doInHibernate(Session session) {
                Criteria criteria = session.createCriteria(AppModel.class);
            	if (filter.hasModel()) {
            		AppModel t = filter.getModel();
            		if (t.getId() != null) {
                        criteria.add(Restrictions.eq("id", t.getId()));
            		}
            		if (t.getName() != null) {
                        criteria.add(Restrictions.like("name", t.getName()));
            		}
            		if (t.getType() != null) {
                        criteria.add(Restrictions.eq("type", t.getType()));
            		}
            	}                
                return criteria.list();
            }    		
    	});
    	
    	AppModels models = new AppModels();
    	for (AppModel m : found) {
    		models.add(m);
    	}
    	
    	LOGGER.debug("find: models={}", models);
    	
		return models;
	}	

	public void remove(AppModelID key) throws AppCrashException, AppClientException {

    	LOGGER.debug("remove: key={}", key);
    	
    	AppModel template = read(key);

    	LOGGER.debug("remove: template={}", template);
    	
    	getHibernateTemplate().delete(template);

    	LOGGER.debug("remove: delete key={}", key);
	}	
}
