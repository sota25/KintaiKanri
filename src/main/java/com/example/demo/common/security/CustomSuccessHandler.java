package com.example.demo.common.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.common.KintaiConstants;
import com.example.demo.common.security.impl.UserDetailsImpl;
import com.example.demo.service.ContractService;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private ContractService contractSetvice;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
//      log.debug("レスポンスがすでにコミットされています。次のURLへリダイレクトできません。 :" + targetUrl);
			System.out.println("レスポンスがすでにコミットされています。次のURLへリダイレクトできません。");
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {

		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

		int count = contractSetvice.countCont(userDetailsImpl.getUserId());

		boolean isAdmin = false;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals(KintaiConstants.ROLE_ADMIN)) {
				isAdmin = true;
				break;
			}
		}

		if (isAdmin) {
			return KintaiConstants.ADMIN_HOME_URL;
		} else if (count == 0 && userDetailsImpl.getUserStatus() == KintaiConstants.USER_STATUS_APPROVE_NUM) {
			return KintaiConstants.CONTRACT_URL;
		} else if (userDetailsImpl.getUserStatus() == KintaiConstants.USER_STATUS_APPROVE_NUM && count != 0) {
			return KintaiConstants.HOME_URL;
		} else if (userDetailsImpl.getUserStatus() == KintaiConstants.USER_STATUS_NOT_APPROVE_NUM) {
			return KintaiConstants.SIGNUP_COMPLETION_URL;
		} else {
			throw new IllegalStateException();
		}
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

}
