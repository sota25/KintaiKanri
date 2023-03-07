package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Contracts;
import com.example.demo.entity.Users;

@Mapper
public interface KintaiMapper {

	/**
	 * ユーザー登録処理Mapper
	 * 
	 * @param users
	 */
	public int insertUser(Users users);

	/**
	 * ログインユーザー取得Mapper(1件)
	 * 
	 * @param email ログインメールアドレス
	 */
	public Users findLoginUser(String email);

	/**
	 * 契約登録処理Mapper
	 * 
	 * @param contracts
	 * @return
	 */
	public int insertCont(Contracts contracts);

//	public List<Contracts> findAllCont(String userId);
//
//	public Contracts findOneCont(String contractId);

	/**
	 * 現在の契約取得Mapper
	 * 
	 * @param userId ログインユーザーID
	 * @return contractテーブル
	 */
	public Contracts findCurrentCont(int userId);
}
