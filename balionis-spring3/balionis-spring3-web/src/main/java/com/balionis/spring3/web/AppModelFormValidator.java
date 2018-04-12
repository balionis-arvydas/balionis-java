package com.balionis.spring3.web;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.StringUtils;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import com.balionis.spring3.model.AppModel;
import com.balionis.spring3.model.AppModelID;

public class AppModelFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppModelFormValidator.class);

    private AppModel validatedModel;        
    
    public boolean supports(Class<?> candidate) {
        return AppModelForm.class.isAssignableFrom(candidate);
    }

    public AppModel getValidatedModel() {
        return validatedModel;
    }

    public void validate(Object obj, Errors errors) {
        LOGGER.debug("validate: obj={}", obj);
        
        AppModelForm form = (AppModelForm) obj;
        
        NumberFormat parser = new DecimalFormat("##0");

        validatedModel = new AppModel(null);
        
        if (StringUtils.isNotBlank(form.getModelKey())) {
            try {
                long key = parser.parse(form.getModelKey()).longValue();
                AppModelID id = new AppModelID(new Long(key));
                validatedModel.setId(id);
                
            } catch(ParseException exc) {
                String[] args = {form.getModelKey()};
                errors.reject(AppDef.ERROR_MODEL_FORM_MODEL_KEY_MISMATCH, args, AppDef.ERROR_MODEL_FORM_MODEL_KEY_MISMATCH);
                LOGGER.info("validate: key=" + form.getModelKey(), exc);
            }                            
        }
                        
        if (StringUtils.isNotBlank(form.getType())) {
            validatedModel.setType(StringUtils.trim(form.getType()));
        }

        if (StringUtils.isBlank(form.getName())) {
            errors.rejectValue(AppDef.NAME, AppDef.ERROR_MODEL_FORM_NAME_REQUIRED);
        } else {
        	validatedModel.setName(StringUtils.strip(form.getName()));
        }
    }
}