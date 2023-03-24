package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.KintaiConstants;
import com.example.demo.common.security.impl.UserDetailsImpl;
import com.example.demo.entity.Users;
import com.example.demo.form.ChangeEmailForm;
import com.example.demo.form.ChangePasswordForm;
import com.example.demo.form.SignupForm;
import com.example.demo.model.valid.GroupOrder;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

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
		userService.insertUser(users);

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

	@GetMapping(KintaiConstants.CHANGE_EMAIL_URL)
	public String getChangeEmail(@ModelAttribute ChangeEmailForm form, Model model,
			@AuthenticationPrincipal UserDetailsImpl user) {
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_CHANGE_EMAIL);

		Users loginUser = userService.findOneUser(user.getUserId());
		model.addAttribute(KintaiConstants.MODEL_KEY_OLD_EMAIL, loginUser.getEmail());

		return KintaiConstants.HOME_LAYOUT_PASS;

	}

	@PostMapping(value = KintaiConstants.CHANGE_EMAIL_URL, params = KintaiConstants.PARAM_UPDATE)
	public String postChangeEmail(@ModelAttribute @Validated(GroupOrder.class) ChangeEmailForm form,
			BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetailsImpl user) {
		if (bindingResult.hasErrors()) {
			return getChangeEmail(form, model, user);
		}

		Users loginUser = userService.findOneUser(user.getUserId());
		modelMapper.map(form, loginUser);

		userService.updateEmail(loginUser);

		return KintaiConstants.REDIRECT_HOME;
	}

	@GetMapping(KintaiConstants.CHANGE_PASSWORD_URL)
	public String getChangePassword(@ModelAttribute ChangePasswordForm form, Model model,
			@AuthenticationPrincipal UserDetailsImpl user) {

		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_CHANGE_PASSWORD);

		return KintaiConstants.HOME_LAYOUT_PASS;

	}

	@PostMapping(KintaiConstants.CHANGE_PASSWORD_URL)
	public String postChangePassword(@ModelAttribute @Validated(GroupOrder.class) ChangePasswordForm form,
			BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetailsImpl user) {

		if (bindingResult.hasErrors()) {
			return getChangePassword(form, model, user);
		}
		Users loginUser = userService.findOneUser(user.getUserId());
		modelMapper.map(form, loginUser);

		userService.updatePassword(loginUser);

		return KintaiConstants.REDIRECT_HOME;

	}
}
