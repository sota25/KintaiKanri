package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.common.KintaiConstants;

@Controller
public class LoginController {

	/**
	 * ログイン画面のgetメソッド
	 * 
	 * @return ログイン画面のパス
	 */
	@GetMapping(KintaiConstants.LOGIN_URL)
	public String getLogin() {
		return KintaiConstants.LOGIN_PASS;
	}

}
