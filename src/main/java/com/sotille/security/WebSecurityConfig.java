package com.sotille.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sotille.security.jwt.CustomAuthenticationProvider;
import com.sotille.security.jwt.JWTAuthenticationFilter;
import com.sotille.security.jwt.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Create a default account
//		auth.inMemoryAuthentication()
//		.withUser("admin")
//		.password("password")
//		.roles("ADMIN");

		auth.authenticationProvider(this.customAuthenticationProvider);
	}

     @Override
     protected void configure(HttpSecurity http) throws Exception {
         // disable caching
         http.headers().cacheControl();

         http.csrf().disable() // disable csrf for our requests.
             .authorizeRequests()
             .antMatchers("/").permitAll()
             .antMatchers("/customer/**").permitAll()
             .antMatchers(HttpMethod.POST, "/login").permitAll()
             .anyRequest().authenticated()
             .and()
             // We filter the api/login requests
             .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
             // And filter other requests to check the presence of JWT in header
             .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
     }
}