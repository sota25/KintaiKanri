package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * usersテーブル
 */
@Data
public class Users {

	/** usersテーブルID */
	private int userId;

	/** ユーザーネーム */
	private String userName;

	/** メールアドレス */
	private String email;

	/** パスワード */
	private String password;

	/** 権限 */
	private int role;

	/** 承認ステータス */
	private int userStatus;

	/** 登録日時 */
	private LocalDateTime requestedAt;
}
