package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.KintaiConstants;
import com.example.demo.entity.Users;
import com.example.demo.form.SignupForm;
import com.example.demo.model.valid.GroupOrder;
import com.example.demo.service.UserService;

@Controller
public class SignupController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService service;

	/**
	 * 新規登録画面のgetメソッド
	 * 
	 * @param form  新規登録用フォーム
	 * @param model Modelクラス
	 * @return 新規登録画面のパス
	 */
	@GetMapping(KintaiConstants.SIGNUP_URL)
	public String getSignup(@ModelAttribute SignupForm form, Model model) {
		return KintaiConstants.SIGNUP_PASS;
	}

	/**
	 * 新規登録画面のgetメソッド
	 * 
	 * @param form   新規登録用フォーム
	 * @param result BindingResultクラス
	 * @param model  Modelクラス
	 * @return 登録完了画面へのリダイレクトパス
	 */
	@PostMapping(KintaiConstants.SIGNUP_URL)
	public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			return getSignup(form, model);
		}

		Users users = modelMapper.map(form, Users.class);

		service.insertUser(users);

		return KintaiConstants.REDIRECT_COMPLETION;

	}

	/**
	 * 登録完了画面のgetメソッド
	 * 
	 * @return 登録完了画面のパス
	 */
	@GetMapping(KintaiConstants.SIGNUP_COMPLETION_URL)
	public String geetSignupCompletion() {

		return KintaiConstants.SIGNUP_COMPLETION_PASS;
	}

}
