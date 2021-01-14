package com.zalpi.avaliacaobackend.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@NonNull
	private UserDetailsService userDetails;

	@NonNull
	private SecurityConfigInfoConstants config;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET, config.getSignupUrl()).permitAll()
			.antMatchers(  "/user/hello-unauthorized").permitAll()
			//.antMatchers("/user/**").permitAll()
			.antMatchers("/user/**").hasRole("ADMIN")
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager(), config))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetails, config));
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetails).passwordEncoder(new BCryptPasswordEncoder());
	}
}
