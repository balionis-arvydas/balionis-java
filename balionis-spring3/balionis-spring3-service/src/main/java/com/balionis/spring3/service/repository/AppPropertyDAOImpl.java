package com.balionis.spring3.service.repository;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.balionis.spring3.commons.AppCrashException;
import com.balionis.spring3.commons.AppClientException;
import com.balionis.spring3.service.AppCrashExceptionBuilder;

@Repository("propertyDAO")
public class AppPropertyDAOImpl extends NamedParameterJdbcDaoSupport implements AppPropertyDAO {
	
	public static String PROPERTY_CD = "property_cd";
	public static final String DATA = "data";
		
    private static final Logger LOGGER = LoggerFactory.getLogger(AppPropertyDAOImpl.class);
	
	private String propertyCode;
	
	@Value("${propertyCode:?}")
	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public Long readNext() throws AppCrashException, AppClientException {
		
		LOGGER.debug("readNext: propertyCode={}", propertyCode);
    	
    	String sql1 = "UPDATE app_property "
    			      + "SET data = CAST(data AS UNSIGNED) + 1, "
   			      	  + "modify_at = NOW() "    			      
    			      + "WHERE property_cd = :property_cd";
    	
    	LOGGER.debug("readNext: sql1={}", sql1);
    	
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put(PROPERTY_CD, propertyCode);
    	
    	int count = getNamedParameterJdbcTemplate().update(sql1, namedParameters);

    	if (count < 1) {
    		LOGGER.error("readNext: can not find propertyCode={}", propertyCode);
        	throw AppCrashExceptionBuilder.newS101(propertyCode);
    	}
		
    	String sql2 = "SELECT data FROM app_property"
    			      + " WHERE property_cd = :property_cd ";
    	
    	LOGGER.debug("readNext: sql2={}", sql2);

    	Long next = getNamedParameterJdbcTemplate().queryForObject(sql2, namedParameters, Long.class);
    	
    	LOGGER.debug("readNext: next={}", next);
		
		return next;
	}	
}
