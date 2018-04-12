package com.balionis.spring3.web.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;

@Component
public class AppAuthenticationProvider implements AuthenticationProvider, MessageSourceAware {
   
    private static final Logger LOGGER = LoggerFactory.getLogger(AppAuthenticationProvider.class);
    
    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    
    private static final Collection<GrantedAuthority> authorities = new java.util.ArrayList<GrantedAuthority>();
    
    static {
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOGGER.debug("authenticate: authentication=[]", authentication);
        
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();

        String password = (authentication.getCredentials() == null) ? "NONE_PROVIDED" : authentication.getCredentials().toString();

        // you can call your own authentication service. 
        if (!"myuser".equals(username) || !"mypass".equals(password)) {
            throw new BadCredentialsException(messages.getMessage("AppAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        
        Authentication res = new UsernamePasswordAuthenticationToken(username, password, authorities);
        
        LOGGER.debug("authenticate: res={}", res);
        
        return res;
    }
    
    public boolean supports(Class<?> authentication) {
        LOGGER.debug("supports: authentication={}", authentication.getName());
        
        return UsernamePasswordAuthenticationToken.class.equals(authentication); 
    }
       
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }    
}