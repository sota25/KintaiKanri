package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.KintaiConstants;
import com.example.demo.entity.Users;
import com.example.demo.repository.KintaiMapper;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	/** KintaiMapperクラスの呼び出し */
	@Autowired
	private KintaiMapper kintaiMapper;

	@Override
	public int insertUser(Users users) {

		users.setRole(KintaiConstants.ROLE_GENERAL_NUM);
		users.setUserStatus(KintaiConstants.ROLE_GENERAL_NUM);
		users.setRequestedAt(dateToSecond());

		return kintaiMapper.insertOne(users);
	}

	/**
	 * 現在時間取得メソッド
	 * 
	 * @return 現在時間(ミリ秒切り捨て)
	 */
	// Date型だと工数が多くなる
	public LocalDateTime dateToSecond() {

		LocalDateTime requestedAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

		return requestedAt;

	}
}
