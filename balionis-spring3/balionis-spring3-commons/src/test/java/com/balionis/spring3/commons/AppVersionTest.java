package com.balionis.spring3.commons;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Test;

/**
 */
public class AppVersionTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppVersionTest.class);
    
    @Test
    public void testMe() {
        try {
            AppVersion version = AppVersion.builder().build();
            
            LOGGER.info("testMe: version={}", version);
            
            assertNull(version.getProjectVersion());
            
        } catch(Throwable exc) {
            LOGGER.error("", exc);
            fail(exc.toString());
        }
    }
}
