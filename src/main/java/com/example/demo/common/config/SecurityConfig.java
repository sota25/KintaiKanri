package com.example.demo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.common.KintaiConstants;
import com.example.demo.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(KintaiConstants.WEBJARS_DIR, KintaiConstants.CSS_DIR, KintaiConstants.H2_DIR);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(KintaiConstants.WEBJARS_DIR).permitAll()
				.antMatchers(KintaiConstants.CSS_DIR).permitAll().antMatchers(KintaiConstants.LOGIN_URL).permitAll()
				.antMatchers(KintaiConstants.SIGNUP_URL).permitAll().anyRequest().authenticated();

		http.formLogin().loginProcessingUrl(KintaiConstants.LOGIN_URL)
				.usernameParameter(KintaiConstants.USER_NAME_PARAM).passwordParameter(KintaiConstants.PASSWORD_PARAM)
				.defaultSuccessUrl(KintaiConstants.HOME_URL, true).loginPage(KintaiConstants.LOGIN_URL)
				.failureUrl(KintaiConstants.LOGIN_URL.concat(KintaiConstants.ERROR_URL));

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

}
