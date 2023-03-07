package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.common.KintaiConstants;
import com.example.demo.common.security.impl.UserDetailsImpl;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String role = "";

		Users loginUser = service.findLoginUser(email);
		if (loginUser == null) {
			throw new UsernameNotFoundException(KintaiConstants.USER_NOT_FOUND);
		}

		// authorityにroleをセットするためにはStringに変換する必要がある
		if (loginUser.getRole() == 0) {
			role = KintaiConstants.ROLE_ADMIN;
		} else {
			role = KintaiConstants.ROLE_GENERAL;
		}

		GrantedAuthority authority = new SimpleGrantedAuthority(role);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);

		UserDetails userDetails = (UserDetails) new UserDetailsImpl(loginUser.getUserId(), email,
				loginUser.getPassword(), authorities, loginUser.getUserStatus());

//		UserDetailsImpl userDetails = new UserDetailsImpl(loginUser.getUserId(), email, loginUser.getPassword(),
//				authorities, loginUser.getUserStatus());

		return userDetails;

	}

}
