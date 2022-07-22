package com.bilgeadam.SpringBootRestHibernateJPA.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//2 farklı yolu var
@Configuration
public class SeConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/*/getById/*").authenticated();
//		http.csrf().ignoringAntMatchers("/*/ekle");
//		http.authorizeRequests().antMatchers("/*/ekle").authenticated();
		http.authorizeRequests().anyRequest().permitAll();
		// jwt filtlerini sisteme tanıtmak için yazdık.
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	}
}
