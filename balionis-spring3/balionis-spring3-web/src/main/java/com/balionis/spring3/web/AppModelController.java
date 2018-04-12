package com.balionis.spring3.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import com.balionis.spring3.commons.AppClientException;
import com.balionis.spring3.model.AppModelID;
import com.balionis.spring3.model.AppModel;
import com.balionis.spring3.service.AppManager;

@Controller
@RequestMapping(value="/model")
public class AppModelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppModelController.class);

    private AppManager manager;

    @Autowired
    public void setManager(AppManager manager) {
        this.manager = manager;
    }

    @ModelAttribute(AppDef.TYPE_OPTIONS)
    public Map<String,String> createTypeOptions() {
        
        LOGGER.debug("createTypeOptions(+)");
        
        Map<String,String> typeOptions = new HashMap<>();
        typeOptions.put("1", "One");
        typeOptions.put("2", "Two");
        typeOptions.put("3", "Three");

        LOGGER.debug("createTypeOptions: typeOptions=" + typeOptions);
        
        return typeOptions;
    }
    
    @ModelAttribute(AppDef.MODEL_FORM)
    public AppModelForm createModelForm(ModelMap model) throws AppClientException {
        
        LOGGER.debug("createModelForm(+)");
                
        AppModelForm form = new AppModelForm();
        
        LOGGER.debug("createTemplateForm: form={}", form);
        
        return form;
    }
    
    @RequestMapping(method = RequestMethod.GET)    
    public ModelAndView handleRequest(
            ModelMap model, HttpServletRequest request) throws AppClientException, Exception {
        
        LOGGER.debug("handleRequest:+");
           
        AppModelForm form = new AppModelForm();
        
        ModelAndView mov = new ModelAndView(AppDef.MODEL_VIEW);
        mov.addObject(AppDef.MODEL_FORM, form);
                
        LOGGER.debug("handleRequest: form={}", form);
        
        return mov;
    }
/*
    @InitBinder(AppDef.MODEL_FORM)
    public void registerInitBinder(WebDataBinder binder) { 
    	binder.addValidators(new AppModelFormValidator());
    }
*/    
    @RequestMapping(method = RequestMethod.POST)    
    public String handleSaveRequest(
            @ModelAttribute(AppDef.MODEL_FORM) AppModelForm form,
            BindingResult result, ModelMap modelMap,
            HttpServletRequest request) throws AppClientException, Exception {

    	LOGGER.debug("handleSaveRequest: form={}", form);
        
    	// choose to do internal validation rather web binders to get use of getValidatedModel (see below)
        AppModelFormValidator validator = new AppModelFormValidator();
        
        validator.validate(form, result);
                
        if (result.hasErrors()) {
            LOGGER.debug("handleSaveRequest: invalid form=" + form);
            return AppDef.MODEL_VIEW;
        }
                
        AppModel model = validator.getValidatedModel();

    	LOGGER.debug("handleSaveRequest: model={}", model);
        
        AppModelID modelKey = manager.write(model);
    	LOGGER.debug("handleSaveRequest: modelKey={}", modelKey);

        model.setId(modelKey);
    	
        @SuppressWarnings("unchecked")
		List<AppModel> models = (List<AppModel>) request.getSession().getAttribute(AppDef.MODELS);
        
        if (models == null) {
        	models = new ArrayList<>();
        	request.getSession().setAttribute(AppDef.MODELS, models);
        }
        models.add(model);
        
        LOGGER.debug("handleSaveRequest: models={}", models);
        
        return AppDef.MODEL_REDIRECT;
    }        

    @RequestMapping(value="{modelKey}", method = RequestMethod.GET)
    public @ResponseBody AppModel handleViewRequest(@PathVariable("modelKey") long key, 
    		HttpServletRequest request, HttpServletResponse response) 
    				throws AppClientException, Exception {
    	
        LOGGER.debug("handleViewRequest: key={}", key);

        AppModel model = null;        
        try {
            
            AppModelID modelKey = new AppModelID(new Long(key));

            LOGGER.debug("handleViewRequest: modelKey={}", modelKey);
            
            model = manager.read(modelKey);
            
            LOGGER.debug("handleViewRequest: model={}", model);

        } catch(AppClientException exc) {
            LOGGER.info("handleViewRequest: exc=" + exc);
            throw exc;
        } catch(Exception exc) {
            LOGGER.warn("handleViewRequest: ...", exc);
            throw exc;
        }                        
        return model;        
    }
    
    @ExceptionHandler(AppClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(AppClientException exc) {
        LOGGER.info("handleException: exc={}", exc);
        
        String message = new MessageFormat(exc.getMessage()).format(exc.getArgs(), new StringBuffer(), null).toString();
        
        ModelAndView model = new ModelAndView(AppDef.WORKING_VIEW);
        model.addObject(AppDef.EXCEPTION_CODE, exc.getCode());
        model.addObject(AppDef.EXCEPTION_MESSAGE, message);
        
        return model;
    }    

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(Exception exc) {
        LOGGER.info("handleException: exc={}", exc);

        StringWriter writer = new StringWriter();        
        exc.printStackTrace(new PrintWriter(writer));
        
        ModelAndView model = new ModelAndView(AppDef.WORKING_VIEW);
        model.addObject(AppDef.EXCEPTION_CODE, exc.getClass().getName());
        model.addObject(AppDef.EXCEPTION_MESSAGE, writer.toString());
        
        return model;
    }    
}