package com.example.demo.common;

/**
 * 定数クラス
 * 
 * @author S.TAKASUGI
 */
public class KintaiConstants {

	/*******
	 * URL *
	 *******/

	/** ログインページ */
	public static final String LOGIN_URL = "/login";

	/** ログアウトページ */
	public static final String LOGOUT_URL = "/logout";

	/** ユーザー新規登録ページ */
	public static final String SIGNUP_URL = "/signup";

	/** ユーザー登録完了ページ */
	public static final String SIGNUP_COMPLETION_URL = "/signup/completion";

	/** 契約登録画面ページ */
	public static final String CONTRACT_URL = "/contract";

	/** ホーム画面ページ */
	public static final String HOME_URL = "/home";

	/** エラー時追加URL */
	public static final String ERROR_URL = "?error";

	/** 勤怠登録画面ページ */
	public static final String WORK_TIME_URL = "/workTime";

	/** 契約一覧表示画面ページ */
	public static final String CONTRACT_LIST_URL = "contracts";

	/** 勤務月一覧表示画面ページ */
	public static final String MONTH_LIST_URL = "month";

	/** 動的URL(contractId) */
	public static final String PATH_VAL_CONTRACT_ID = "/{contractId}";

	/************
	 * 遷移先パス *
	 ************/

	/** ログイン画面のパス */
	public static final String LOGIN_PASS = "user/login";

	/** ユーザー新規登録ページのパス */
	public static final String SIGNUP_PASS = "user/signup";

	/** 契約登録ページのパス */
	public static final String ADD_CONTRACT_PASS = "contract/contract";

	/** ユーザー登録完了ページのパス */
	public static final String SIGNUP_COMPLETION_PASS = "user/completion";

	/** ホーム画面ページのパス */
	public static final String HOME_LAYOUT_PASS = "contract/homeLayout";

	/** 勤怠登録ページのパス */
	public static final String WORK_TIME_PASS = "contract/workTime";

	/** 契約一覧表示画面ページのパス */
	public static final String CONTRACT_LIST_PASS = "contract/contracts";

	/*****************
	 * リダイレクトパス *
	 *****************/

	/** ログイン画面のリダイレクト */
	public static final String REDIRECT_LOGIN = "redirect:/login";

	/** 登録完了画面のリダイレクト */
	public static final String REDIRECT_COMPLETION = "redirect:/signup/completion";

	/** ホーム画面のリダイレクト */
	public static final String REDIRECT_HOME = "redirect:/home";

	/**************
	 * データ固定値 *
	 **************/

	/** 管理者権限データ登録値 */
	public static final int ROLE_ADMIN_NUM = 0;

	/** 管理者権限変換値 */
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	/** 一般権限データ登録値 */
	public static final int ROLE_GENERAL_NUM = 1;

	/** 一般権限変換値 */
	public static final String ROLE_GENERAL = "ROLE_GENERAL";

	/** 未承認データ登録値 */
	public static final int USER_STATUS_NOT_APPROVE_NUM = 0;

	/** 承認データ登録値 */
	public static final int USER_STATUS_APPROVE_NUM = 1;

	/** 凍結データ登録値 */
	public static final int USER_STATUS_FROZEN_NUM = 2;

	/**************
	 * モデルキー値 *
	 **************/

	/** contentsキ- */
	public static final String MODEL_KEY_CONTENTS = "contents";

	/** contractsテーブルIDキー */
	public static final String MODEL_KEY_CONTRACT_ID = "contractId";

	/** 年キー */
	public static final String MODEL_KEY_YEAR = "year";

	/** 月キー */
	public static final String MODEL_KEY_MONTH = "month";

	/** 日キー */
	public static final String MODEL_KEY_DAY = "day";

	/** 曜日キー */
	public static final String MODEL_KEY_DAY_OF_WEEK = "dayOfWeek";

	/** 会社名キー */
	public static final String MODEL_KEY_OFFICE_NAME = "officeName";

	/** 契約一覧キー */
	public static final String MODEL_KEY_CONTRACT_LIST = "contracts";

	/** 月一覧キー */
	public static final String MODEL_KEY_MONTH_LIST = "monthList";

	/*****************
	 * モデルバリュー値 *
	 *****************/

	/** homeコンテンツ */
	public static final String MODEL_VAL_HOME_CONTENTS = "contract/home::home_contents";

	/** homeコンテンツ */
	public static final String MODEL_VAL_CONTRACT_CONTENTS = "contract/contracts::contracts_contents";

	/** homeコンテンツ */
	public static final String MODEL_VAL_MONTH_CONTENTS = "contract/month::month_contents";

	/******************
	 * セキュリティ関連 *
	 ******************/

	/** webjars階層 */
	public static final String WEBJARS_DIR = "/webjars/**";

	/** css階層 */
	public static final String CSS_DIR = "/css/**";

	/** h2階層 */
	public static final String H2_DIR = "/h2-console/**";

	/** ユーザーネームパラメータ */
	public static final String USER_NAME_PARAM = "email";

	/** パスワードパラメータ */
	public static final String PASSWORD_PARAM = "password";

	/** ログアウト成功 */
	public static final String LOGOUT_SUCCESS_URL = "/login?logout";

	/******************
	 * エラーメッセージ *
	 ******************/

	/** user not foundエラーメッセージ */
	public static final String USER_NOT_FOUND = "user not found";
}
