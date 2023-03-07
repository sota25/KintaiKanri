package com.example.demo.common.security.impl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails {

	private int userId;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private int userStatus;

	public UserDetailsImpl(int userId, String email, String password,
			Collection<? extends GrantedAuthority> authorities, int userStatus) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.userStatus = userStatus;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
