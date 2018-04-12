package com.balionis.spring3.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.balionis.spring3.model.AppModel;
import com.balionis.spring3.model.AppModelID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class AppManagerImplTest extends AbstractTransactionalJUnit4SpringContextTests {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AppManagerImplTest.class);
	
	private AppManager manager;
	
    @Autowired
    public void setManager(@Qualifier("manager") AppManager manager) {
        this.manager = manager;
    }
    
    @Before
    public void setUpTestDataWithinTransaction() {
        super.executeSqlScript("classpath:test-clear.sql", false);
        super.executeSqlScript("classpath:test-data.sql", false);
    }
    
    @Test
    public void testMe() {
    	try {
    		
    		String name = "MyName";
    		String type = "X";

        	AppModelID expectedId = null; 
        	AppModel expected = new AppModel(expectedId);
        	expected.setName(name);
        	expected.setType(type);
        	
        	LOGGER.info("expected={}", expected);
        	
        	AppModelID actualId = manager.write(expected);

        	LOGGER.info("actualId={}", actualId);
        	
        	assertTrue(actualId.getKey() > 0);
        	
        	AppModel actual = manager.read(actualId);

        	LOGGER.info("actual={}", actual);

        	assertEquals(actual.getType(), type);
        	
    	} catch(Throwable exc) {
    		logger.warn("testMe", exc);
    		fail(exc.toString());
    	}
   	}
}
