package com.balionis.spring3.service.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.balionis.spring3.commons.AppCrashException;
import com.balionis.spring3.commons.AppClientException;
import com.balionis.spring3.service.AppClientExceptionBuilder;
import com.balionis.spring3.model.AppModel;
import com.balionis.spring3.model.AppModels;
import com.balionis.spring3.model.AppModelID;
import com.balionis.spring3.model.AppModelFilter;

@Repository("modelDAO1")
public class AppModelDAOImpl1 extends NamedParameterJdbcDaoSupport implements AppModelDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppModelDAOImpl1.class);
	
	public static final String MODEL_PK = "model_pk";
	public static final String NAME = "name";
	public static final String MODEL_TYPE = "model_type";
	
	public static String MODIFY_AT = "modify_at";	
	
	private static class AppModelMapper implements ParameterizedRowMapper<AppModel> {
		
		public AppModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			AppModelID id = new AppModelID(rs.getLong(MODEL_PK));

			AppModel obj = new AppModel(id);			
			obj.setType(rs.getString(MODEL_TYPE));
			obj.setName(rs.getString(NAME));

			Timestamp ts = rs.getTimestamp(MODIFY_AT);
			Date modifyAt = new Date(ts.getTime());
			obj.setModifyAt(modifyAt);
			
			LOGGER.info("mapRow: obj={}", obj);
			
			return obj;
		}
	}
	
	public AppModel read(AppModelID key) throws AppCrashException, AppClientException {
		LOGGER.debug("read: key={}", key);
    	
    	String sql = "SELECT model_pk, name, model_type, modify_at "
    				+ " FROM app_model "
    				+ "WHERE model_pk = :model_pk ";
    	
    	LOGGER.debug("read: sql={}", sql);

    	Map<String, Object> namedParameters = new HashMap<String, Object>();
    	namedParameters.put(MODEL_PK, key.getKey());
    	
    	List<AppModel> models = getNamedParameterJdbcTemplate().query(
    			sql, namedParameters, new AppModelMapper());

    	if (models == null || models.isEmpty()) {
    		LOGGER.info("read: can not find template with key={}", key);
    		throw AppClientExceptionBuilder.newC101(key);
    	}
    	
    	AppModel model = models.get(0);
		
    	LOGGER.debug("read: model={}", model);
		
		return model;
	}
	
	public AppModelID write(final AppModel model)  throws AppCrashException, AppClientException {
		
		LOGGER.debug("write: model={}", model);
		
    	String sql = "UPDATE app_model "
    			      + "SET name = :name, "    			      
    			          + "model_type = :model_type, "    			      
    			      	  + "modify_at = NOW() "    			      
    			    + "WHERE model_pk = :model_pk";
    	
    	LOGGER.debug("write: sql={}", sql);

        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put(MODEL_PK, model.getId().getKey());
        namedParameters.put(NAME, model.getName());
        namedParameters.put(MODEL_TYPE, model.getType());
    	
    	int count = getNamedParameterJdbcTemplate().update(sql, namedParameters);

    	if (count <= 0) {
        	sql = "INSERT INTO app_model "
        		  + "(model_pk, name, model_type, modify_at) "
        		  + "VALUES "
        		  + "(:model_pk, :name, :model_type, NOW())";
        		  
        	LOGGER.debug("write: sql={}", sql);

        	count = getNamedParameterJdbcTemplate().update(sql, namedParameters);
    	} 

    	LOGGER.debug("write: updated {} records for model={}", count, model);
    	
    	return model.getId();
	}

	public AppModels find(AppModelFilter filter) throws AppCrashException, AppClientException {
		
    	LOGGER.debug("find: filter=" + filter);
		
    	StringBuffer sql = new StringBuffer("SELECT model_pk, name, model_type, modify_at FROM app_model WHERE 1 = 1 ");
        Map<String, Object> namedParameters = new HashMap<String, Object>();
    	
    	if (filter.hasModel()) {
    		AppModel r = filter.getModel();
    		if (r.getId() != null) {
    			AppModelID id = r.getId();
    			if (id.getKey() != null) {
           			sql.append(" AND model_pk = :model_pk");
           	        namedParameters.put(MODEL_PK, id.getKey());
    			}
    		}
			if (r.getName() != null) {
	       			sql.append(" AND name LIKE :name");
       	        namedParameters.put(NAME, "%" + r.getName() + "%");
			}
			if (r.getType() != null) {
	       			sql.append(" AND model_type = :model_type");
           	        namedParameters.put(MODEL_TYPE, r.getType());
			}
    	}
    	sql.append(" ORDER BY name");

    	LOGGER.debug("find: sql={}", sql);

    	List<AppModel> rows = getNamedParameterJdbcTemplate().query(
    			sql.toString(), namedParameters, new AppModelMapper());

    	AppModels models = new AppModels();
    	models.setValues(rows);
    	
       	LOGGER.debug("find: models={}", models);
		    	
		return models;
	}	

	public void remove(AppModelID key) throws AppCrashException, AppClientException {

    	LOGGER.debug("remove: key={}", key);

    	String sql = "DELETE FROM app_model "
    				 + "WHERE model_pk = :model_pk";
	
    	LOGGER.debug("remove: sql={}", sql);


    	Map<String, Object> namedParameters = new HashMap<String, Object>();
    	namedParameters.put(MODEL_PK, key.getKey());

    	int count = getNamedParameterJdbcTemplate().update(sql, namedParameters);
    	
    	LOGGER.debug("remov: deleted " + count + " records");
    	
	}	
}
