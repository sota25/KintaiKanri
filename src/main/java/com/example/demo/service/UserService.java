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

	/**
	 * ユーザー取得(1件)
	 * 
	 * @param userId ログインメールアドレス
	 * @return ログインユーザー取得Mapper(1件)
	 */
	public Users findOneUser(int userId);

	/**
	 * ログインユーザー取得(1件)
	 * 
	 * @param email ログインメールアドレス
	 * @return ログインユーザー取得Mapper(1件)
	 */
	public Users findLoginUser(String email);

	/**
	 * 
	 * @param users
	 * @return
	 */
	public int updateEmail(Users users);

	/**
	 * 
	 * @param users
	 * @return
	 */
	public int updatePassword(Users users);

}
