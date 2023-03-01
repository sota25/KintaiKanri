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

}
