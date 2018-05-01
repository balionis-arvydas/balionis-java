package com.balionis.card.security;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;

@EnableWebSecurity
class CardSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CardSecurityConfigurer.class);

    @Autowired
    CardSecurityConfig config;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //
            .anyRequest().authenticated() //
            .and().requestCache().requestCache(new NullRequestCache()) //
            .and().httpBasic() //
            .and().csrf().disable();
    }

    // @see https://docs.spring.io/spring-security/site/docs/5.0.x/api/org/springframework/security/crypto/password/NoOpPasswordEncoder.html
    // for deprecated solution resolution...
    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer = auth.inMemoryAuthentication();
        UserDetailsBuilder builder = null;

        logger.debug("configureGlobal: cards={}", config.getCards().keySet());

        for (Map.Entry<String, String> c : config.getCards().entrySet()) {
            if (builder == null) {
                builder = configurer.withUser(c.getKey()).password(c.getValue()).authorities("ROLE_USER");
            } else {
                builder = builder.and().withUser(c.getKey()).password(c.getValue()).authorities("ROLE_USER");
            }
        }
    }
}
