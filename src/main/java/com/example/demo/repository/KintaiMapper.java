package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Users;

@Mapper
public interface KintaiMapper {

	/**
	 * ユーザー登録処理Mapper
	 * 
	 * @param users
	 */
	public int insertOne(Users users);

	/**
	 * ログインユーザー取得Mapper(1件)
	 * 
	 * @param email ログインメールアドレス
	 */
	public Users findLoginUser(String email);
}
