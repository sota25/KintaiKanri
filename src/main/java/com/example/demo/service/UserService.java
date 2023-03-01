package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;

@Service
public interface UserService {

	/**
	 * ユーザー登録処理
	 * 
	 * @param users usersテーブル
	 * @return ユーザー登録処理Mapper
	 */
	public int insertUser(Users users);

}
