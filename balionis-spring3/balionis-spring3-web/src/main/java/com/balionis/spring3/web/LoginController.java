package com.balionis.spring3.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.balionis.spring3.commons.AppClientException;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value="/login", method = RequestMethod.GET)    
    public ModelAndView handleRequest(
            ModelMap model, HttpServletRequest request) throws AppClientException, Exception {
        
        LOGGER.debug("handleRequest:+");
           
        return new ModelAndView(AppDef.LOGIN_VIEW);
    }
    		
    @RequestMapping(value="/loginError", method = RequestMethod.GET)    
    public ModelAndView handleErrorRequest(
            ModelMap model, HttpServletRequest request) throws AppClientException, Exception {
        
        LOGGER.debug("handleErrorRequest:+");
           
        model.addAttribute("error", "true");
        
        return new ModelAndView(AppDef.LOGIN_VIEW);
    }
}