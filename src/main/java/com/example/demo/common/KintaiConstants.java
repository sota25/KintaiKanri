package com.example.demo.common;

public class KintaiConstants {

	/*******
	 * URL *
	 *******/

	/** ログインページ */
	public static final String LOGIN_URL = "/login";

	/** ユーザー新規登録ページ */
	public static final String SIGNUP_URL = "/signup";

	/** ユーザー登録完了ページ */
	public static final String SIGNUP_COMPLETION_URL = "/signup/completion";

	/************
	 * 遷移先パス *
	 ************/

	/** ログイン画面のパス */
	public static final String LOGIN_PASS = "user/login";

	/** ユーザー新規登録ページのパス */
	public static final String SIGNUP_PASS = "user/signup";

	/** ユーザー登録完了ページのパス */
	public static final String SIGNUP_COMPLETION_PASS = "user/completion";

	/*****************
	 * リダイレクトパス *
	 *****************/

	/** ログイン画面のリダイレクト */
	public static final String REDIRECT_LOGIN = "redirect:/login";

	/** 登録完了画面のリダイレクト */
	public static final String REDIRECT_COMPLETION = "redirect:/signup/completion";

	/**************
	 * データ固定値 *
	 **************/

	/** 管理者権限データ */
	public static final int ROLE_ADMIN_NUM = 0;

	/** 一般権限データ */
	public static final int ROLE_GENERAL_NUM = 1;

	/** 未承認データ */
	public static final int USER_STATUS_NOT_APPROVE_NUM = 0;

	/** 承認データ */
	public static final int USER_STATUS_APPROVE_NUM = 1;

	/** 凍結データ */
	public static final int USER_STATUS_FROZEN_NUM = 2;
}
