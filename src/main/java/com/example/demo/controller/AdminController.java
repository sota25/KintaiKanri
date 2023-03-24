package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.KintaiConstants;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@GetMapping(KintaiConstants.ADMIN_HOME_URL)
	public String getAdminHome(Model model) {

		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_ADMIN_HOME_CONTENTS);

		int countNewUsers = userService.countNewUsers();

		model.addAttribute(KintaiConstants.MODEL_KEY_COUNT_NEW_USERS, countNewUsers);

		return KintaiConstants.ADMIN_HOME_LAYOUT_PASS;
	}

	@GetMapping(KintaiConstants.ADMIN_NEW_USER_LIST_URL)
	public String getNewUserList(Model model) {
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_NEW_USER_LIST_CONTENTS);

		List<Users> newUsersList = userService.findAllNewUSers();

		model.addAttribute(KintaiConstants.MODEL_KEY_NEW_USERS_LIST, newUsersList);

		return KintaiConstants.ADMIN_HOME_LAYOUT_PASS;

	}

	@GetMapping(KintaiConstants.MODEL_KEY_NEW_USERS_DETAIL + KintaiConstants.PATH_VAL_USER_ID)
	public String getNewUserDetail(Model model, @PathVariable(KintaiConstants.MODEL_KEY_USER_ID) int userId) {
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_NEW_USER_DETAIL_CONTENTS);

		Users user = userService.findOneUser(userId);

		model.addAttribute(KintaiConstants.MODEL_KEY_USER_ID, user.getUserId());
		model.addAttribute(KintaiConstants.MODEL_KEY_USER_NAME, user.getUserName());
		model.addAttribute(KintaiConstants.MODEL_KEY_EMAIL, user.getEmail());
		model.addAttribute(KintaiConstants.MODEL_KEY_REQUESTED_AT, user.getRequestedAt());

		return KintaiConstants.ADMIN_HOME_LAYOUT_PASS;

	}

	@PostMapping(value = KintaiConstants.MODEL_KEY_NEW_USERS_DETAIL
			+ KintaiConstants.PATH_VAL_USER_ID, params = KintaiConstants.PARAM_APPROVE)
	public String postAllowUserDetail(Model model, @PathVariable(KintaiConstants.MODEL_KEY_USER_ID) int userId) {
		Users user = userService.findOneUser(userId);
		user.setUserStatus(KintaiConstants.USER_STATUS_APPROVE_NUM);

		userService.updateUserStatus(user);

		return KintaiConstants.REDIRECT_ADMIN_HOME;

	}

	@PostMapping(value = KintaiConstants.MODEL_KEY_NEW_USERS_DETAIL
			+ KintaiConstants.PATH_VAL_USER_ID, params = KintaiConstants.PARAM_FROZEN)
	public String postFreezeUserDetail(Model model, @PathVariable(KintaiConstants.MODEL_KEY_USER_ID) int userId) {

		Users user = userService.findOneUser(userId);
		user.setUserStatus(KintaiConstants.USER_STATUS_FROZEN_NUM);

		userService.updateUserStatus(user);

		return KintaiConstants.REDIRECT_ADMIN_HOME;

	}

}
