package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.common.KintaiConstants;
import com.example.demo.entity.Users;
import com.example.demo.repository.KintaiMapper;
import com.example.demo.service.UserService;
import com.example.demo.service.common.CommonService;

@Service
public class UserServiceImpl implements UserService {

	/** KintaiMapperクラスの呼び出し */
	@Autowired
	private KintaiMapper kintaiMapper;

	@Autowired
	private PasswordEncoder encoder;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insertUser(Users users) {

		String rawPassword = users.getPassword();
		users.setPassword(encoder.encode(rawPassword));
		users.setRole(KintaiConstants.ROLE_GENERAL_NUM);// roleの登録値は1(一般ユーザー)で固定
		users.setUserStatus(KintaiConstants.ROLE_GENERAL_NUM);// userStatusの登録値は0(未承認)で固定
		users.setRequestedAt(CommonService.getCurrentDate());

		return kintaiMapper.insertUser(users);
	}

	@Override
	public Users findOneUser(int userId) {
		return kintaiMapper.findOneUser(userId);
	}

	@Override
	public Users findLoginUser(String email) {

		return kintaiMapper.findLoginUser(email);
	}

	@Override
	public int updateEmail(Users users) {
		return kintaiMapper.updateUsers(users);
	}

	@Override
	public int updatePassword(Users users) {
		String rawPassword = users.getPassword();
		users.setPassword(encoder.encode(rawPassword));

		return kintaiMapper.updateUsers(users);
	}

}
