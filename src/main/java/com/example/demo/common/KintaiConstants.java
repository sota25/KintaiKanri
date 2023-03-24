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

	/** メールアドレス変更画面ページ */
	public static final String CHANGE_EMAIL_URL = "/changeEmail";

	/** パスワード変更画面ページ */
	public static final String CHANGE_PASSWORD_URL = "/changePassword";

	/** 契約情報変更画面ページ */
	public static final String CHANGE_CONTRACT_URL = "/changeContract";

	/** 契約終了日更新画面ページ */
	public static final String UPDATE_END_DATE_URL = "/updateEndDate";

	/** エラー時追加URL */
	public static final String ERROR_URL = "?error";

	/** 勤怠登録画面ページ */
	public static final String WORK_TIME_URL = "/workTime";

	/** 契約一覧表示画面ページ */
	public static final String CONTRACT_LIST_URL = "/contracts";

	/** 勤務月一覧表示画面ページ */
	public static final String MONTH_LIST_URL = "/month";

	/** 勤怠一覧表示画面ページ */
	public static final String WORK_TIME_LIST_URL = "/workTimeDetail";

	/** 勤怠更新画面ページ */
	public static final String WORK_TIME_UPDATE_URL = "/updateWorkTime";

	/** 勤怠削除画面ページ */
	public static final String WORK_TIME_DELETE_URL = "/deleteWorkTime";

	/** 管理者ホーム画面ページ */
	public static final String ADMIN_HOME_URL = "/adminHome";

	/** 管理者新規ユーザー一覧画面ページ */
	public static final String ADMIN_NEW_USER_LIST_URL = "/newUserList";

	/** 動的URL(userId) */
	public static final String PATH_VAL_USER_ID = "/{userId}";

	/** 動的URL(contractId) */
	public static final String PATH_VAL_CONTRACT_ID = "/{contractId}";

	/** 動的URL(monthId) */
	public static final String PATH_VAL_MONTH_ID = "/{monthId}";

	/** 動的URL(workTimeId) */
	public static final String PATH_VAL_WORK_TIME_ID = "/{workTimeId}";

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

	/** ホームレイアウトのパス */
	public static final String HOME_LAYOUT_PASS = "contract/homeLayout";

	/** 勤怠登録ページのパス */
	public static final String WORK_TIME_PASS = "contract/workTime";

	/** 契約一覧表示画面ページのパス */
	public static final String CONTRACT_LIST_PASS = "contract/contracts";

	/** 管理者ホームレイアウトのパス */
	public static final String ADMIN_HOME_LAYOUT_PASS = "admin/adminHomeLayout";

	/*****************
	 * リダイレクトパス *
	 *****************/

	/** ログイン画面のリダイレクト */
	public static final String REDIRECT_LOGIN = "redirect:/login";

	/** 登録完了画面のリダイレクト */
	public static final String REDIRECT_COMPLETION = "redirect:/signup/completion";

	/** 契約登録画面のリダイレクト */
	public static final String REDIRECT_CONTRACT = "redirect:/contract";

	/** ホーム画面のリダイレクト */
	public static final String REDIRECT_HOME = "redirect:/home";

	/** 管理者ホーム画面のリダイレクト */
	public static final String REDIRECT_ADMIN_HOME = "redirect:/adminHome";

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

	/** usersテーブルIDキー */
	public static final String MODEL_KEY_USER_ID = "userId";

	/** usersテーブルIDキー */
	public static final String MODEL_KEY_USER_NAME = "userName";

	/** usersテーブルIDキー */
	public static final String MODEL_KEY_EMAIL = "email";

	/** usersテーブルIDキー */
	public static final String MODEL_KEY_REQUESTED_AT = "requestedAt";

	/** 現在のメールアドレスキー */
	public static final String MODEL_KEY_OLD_EMAIL = "oldEmail";

	/** contentsキ- */
	public static final String MODEL_KEY_CONTENTS = "contents";

	/** 契約情報キー */
	public static final String MODEL_KEY_CONTRACT = "contract";

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

	/** monthsテーブルIDキー */
	public static final String MODEL_KEY_MONTH_ID = "monthId";

	/** work_timesテーブルIDキー */
	public static final String MODEL_KEY_WORK_TIME_ID = "workTimeId";

	/** 勤務日キー */
	public static final String MODEL_KEY_WORK_DAY = "workDay";

	/** 開始時間テーブルIDキー */
	public static final String MODEL_KEY_START_TIME = "startTime";

	/** 開始時間テーブルIDキー */
	public static final String MODEL_KEY_BREAK_TIME = "breakTime";

	/** 開始時間テーブルIDキー */
	public static final String MODEL_KEY_END_TIME = "endTime";

	/** 契約一覧キー */
	public static final String MODEL_KEY_CONTRACT_LIST = "contracts";

	/** 月一覧キー */
	public static final String MODEL_KEY_MONTH_LIST = "monthList";

	/** 勤怠一覧キー */
	public static final String MODEL_KEY_WORK_TIME_LIST = "workTimeList";

	/** 勤怠登録フォームキー */
	public static final String MODEL_KEY_WORK_TIME_FORM = "workTimeForm";

	/** 勤怠詳細キー */
	public static final String MODEL_KEY_WORK_TIME_DETAIL = "workTimeDetail";

	/** 新規ユーザー件数キー */
	public static final String MODEL_KEY_COUNT_NEW_USERS = "countNewUsers";

	/** 新規ユーザー一覧キー */
	public static final String MODEL_KEY_NEW_USERS_LIST = "newUsersList";

	/** 新規ユーザー詳細キー */
	public static final String MODEL_KEY_NEW_USERS_DETAIL = "newUserDetail";

	/*****************
	 * モデルバリュー値 *
	 *****************/

	/** homeコンテンツ */
	public static final String MODEL_VAL_HOME_CONTENTS = "contract/home::home_contents";

	/** changeEmailコンテンツ */
	public static final String MODEL_VAL_CHANGE_EMAIL = "user/changeEmail::changeEmail_contents";

	/** changePasswordコンテンツ */
	public static final String MODEL_VAL_CHANGE_PASSWORD = "user/changePassword::changePassword_contents";

	/** changeContractコンテンツ */
	public static final String MODEL_VAL_CHANGE_CONTRACT_CONTENTS = "contract/changeContract::changeContract_contents";

	/** contractsコンテンツ */
	public static final String MODEL_VAL_CONTRACTS_CONTENTS = "contract/contracts::contracts_contents";

	/** updateEndDateコンテンツ */
	public static final String MODEL_VAL_UPDARE_END_DATE_CONTENTS = "contract/updateEndDate::updateEndDate_contents";

	/** monthコンテンツ */
	public static final String MODEL_VAL_MONTH_CONTENTS = "contract/month::month_contents";

	/** workTimeDetailsコンテンツ */
	public static final String MODEL_VAL_WORK_TIME_CONTENTS = "contract/workTimeDetail::workTimeDetail_contents";

	/** updateWorkTimeコンテンツ */
	public static final String MODEL_VAL_UPDATE_WORK_TIME_CONTENTS = "contract/updateWorkTime::updateWorkTime_contents";

	/** deleteWorkTimeコンテンツ */
	public static final String MODEL_VAL_DELETE_WORK_TIME_CONTENTS = "contract/deleteWorkTime::deleteWorkTime_contents";

	/** adminHomeコンテンツ */
	public static final String MODEL_VAL_ADMIN_HOME_CONTENTS = "admin/adminHome::adminHome_contents";

	/** newUserListコンテンツ */
	public static final String MODEL_VAL_NEW_USER_LIST_CONTENTS = "admin/newUserList::newUserList_contents";

	/** newUserDetailコンテンツ */
	public static final String MODEL_VAL_NEW_USER_DETAIL_CONTENTS = "admin/newUserDetail::newUserDetail_contents";

	/***********
	 * Param値 *
	 ***********/

	/** contractId Param値 */
	public static final String PARAM_CONTRACT_ID = "contractId";

	/** year Param値 */
	public static final String PARAM_YEAR = "year";

	/** month Param値 */
	public static final String PARAM_MONTH = "month";

	/** update Param値 */
	public static final String PARAM_UPDATE = "update";

	/** delete Param値 */
	public static final String PARAM_DELETE = "delete";

	/** approve Param値 */
	public static final String PARAM_APPROVE = "approve";

	/** frozen Param値 */
	public static final String PARAM_FROZEN = "frozen";

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
