package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	/**
	 * ログイン画面のgetメソッド
	 * 
	 * @return ログイン画面のパス
	 */
	@GetMapping("/login")
	public String getLogin() {
		return "user/login";
	}

}
