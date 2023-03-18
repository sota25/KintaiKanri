package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.KintaiConstants;

/**
 * 認証関連のControllerクラス
 * 
 * @author S.TAKASUGI
 *
 */
@Controller
public class AuthController {

	/**
	 * ログイン画面のgetメソッド
	 * 
	 * @return ログイン画面のパス
	 */
	@GetMapping(KintaiConstants.LOGIN_URL)
	public String getLogin() {
		return KintaiConstants.LOGIN_PASS;
	}

	/**
	 * ログアウト処理のpostメソッド
	 * 
	 * @return ログイン画面リダイレクト
	 */
	@PostMapping(KintaiConstants.LOGOUT_URL)
	public String postLogout() {
		return KintaiConstants.REDIRECT_LOGIN;
	}

}
