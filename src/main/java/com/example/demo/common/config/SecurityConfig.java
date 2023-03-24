package com.example.demo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.common.KintaiConstants;
import com.example.demo.common.security.CustomSuccessHandler;

/**
 * セキュリティ実装クラス
 * 
 * @author S.TAKASUGI
 *
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	/** PasswordEncoderのBean設定 */
	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationSuccessHandler customSuccessHandler() {
		// カスタムで作成したハンドラを返す
		return new CustomSuccessHandler();
	}

	/** セキュリティ対象外設定メソッド */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(KintaiConstants.WEBJARS_DIR, KintaiConstants.CSS_DIR, KintaiConstants.H2_DIR);
	}

	/* セキュリティ各種設定 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 直リンク設定
		http.authorizeRequests().antMatchers(KintaiConstants.WEBJARS_DIR).permitAll()
				.antMatchers(KintaiConstants.CSS_DIR).permitAll().antMatchers(KintaiConstants.LOGIN_URL).permitAll()
				.antMatchers(KintaiConstants.SIGNUP_URL).permitAll().antMatchers(KintaiConstants.SIGNUP_COMPLETION_URL)
				.permitAll().anyRequest().authenticated();

		// ログイン設定
		http.formLogin().loginProcessingUrl(KintaiConstants.LOGIN_URL)
				.usernameParameter(KintaiConstants.USER_NAME_PARAM).passwordParameter(KintaiConstants.PASSWORD_PARAM)
				.successHandler(customSuccessHandler()).loginPage(KintaiConstants.LOGIN_URL)
				.failureUrl(KintaiConstants.LOGIN_URL.concat(KintaiConstants.ERROR_URL));

		// ログアウト設定
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher(KintaiConstants.LOGOUT_URL))
				.logoutUrl(KintaiConstants.LOGOUT_URL).logoutSuccessUrl(KintaiConstants.LOGOUT_SUCCESS_URL);

	}

	/** 認証設定メソッド */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}
