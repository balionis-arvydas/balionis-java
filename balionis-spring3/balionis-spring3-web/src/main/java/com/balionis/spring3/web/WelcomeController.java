package com.balionis.spring3.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.balionis.spring3.commons.AppVersion;
import com.balionis.spring3.commons.AppCrashException;

@Controller
@SessionAttributes({AppDef.APP_BUILD_VERSION, AppDef.APP_BUILD_TIME})
public class WelcomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping("/welcome")
    public String handleRequest(Model model) throws AppCrashException {
        AppVersion version = AppVersion.builder().withClassLoader(getClass().getClassLoader()).build();
        
        LOGGER.info("handleRequest: buildVersion={}, buildTime={}", 
        		version.getBuildNumber(), version.getBuildTime());
        
        return AppDef.WELCOME_VIEW; 
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(AppCrashException exc) {
        LOGGER.info("handleException:...", exc);
        
        ModelAndView model = new ModelAndView(AppDef.WORKING_VIEW);
        model.addObject(AppDef.EXCEPTION_CODE, exc.getClass().getSimpleName());
        model.addObject(AppDef.EXCEPTION_MESSAGE, exc.getMessage());        
        return model;
    }    
}