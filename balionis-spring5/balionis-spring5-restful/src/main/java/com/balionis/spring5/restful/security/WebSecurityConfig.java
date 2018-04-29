package com.balionis.spring5.restful.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.context.annotation.Bean;

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //
            .anyRequest().authenticated() //
            .and().requestCache().requestCache(new NullRequestCache()) //
            .and().httpBasic() //
            .and().csrf().disable();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() //
             .withUser("user").password("password").authorities("ROLE_USER") //
             .and() //
             .withUser("admin").password("password").authorities("ROLE_USER", "ROLE_ADMIN");
    }
}
