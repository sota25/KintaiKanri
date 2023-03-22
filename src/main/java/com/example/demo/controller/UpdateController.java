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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.KintaiConstants;
import com.example.demo.common.security.impl.UserDetailsImpl;
import com.example.demo.entity.Months;
import com.example.demo.entity.Users;
import com.example.demo.entity.WorkTimes;
import com.example.demo.form.ChangeEmailForm;
import com.example.demo.form.ChangePasswordForm;
import com.example.demo.form.WorkTimeForm;
import com.example.demo.model.valid.GroupOrder;
import com.example.demo.service.MonthService;
import com.example.demo.service.UserService;
import com.example.demo.service.WorkTimesService;

@Controller
public class UpdateController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private MonthService monthService;

	@Autowired
	private WorkTimesService workTimesService;

	@GetMapping(KintaiConstants.WORK_TIME_UPDATE_URL + KintaiConstants.PATH_VAL_WORK_TIME_ID)
	public String getUpdateWorkTime(Model model, @PathVariable(KintaiConstants.MODEL_KEY_WORK_TIME_ID) int workTimeId,
			@ModelAttribute WorkTimeForm form) {
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_UPDATE_WORK_TIME_CONTENTS);

		WorkTimes workTime = workTimesService.findOneWorkTimes(workTimeId);

		form.setWorkDay(workTime.getWorkDay());
		form.setStartTime(workTime.getStartTime());
		form.setBreakTime(workTime.getBreakTime());
		form.setEndTime(workTime.getEndTime());

		model.addAttribute(KintaiConstants.MODEL_KEY_WORK_TIME_FORM, form);

		model.addAttribute(KintaiConstants.MODEL_KEY_WORK_TIME_ID, workTime.getWorkTimeId());
		model.addAttribute(KintaiConstants.MODEL_KEY_MONTH_ID, workTime.getMonthId());
		model.addAttribute(KintaiConstants.MODEL_KEY_WORK_TIME_DETAIL, workTime);

		return KintaiConstants.HOME_LAYOUT_PASS;

	}

	@PostMapping(value = KintaiConstants.WORK_TIME_UPDATE_URL
			+ KintaiConstants.PATH_VAL_WORK_TIME_ID, params = KintaiConstants.PARAM_UPDATE)
	public String postUpdateWorkTime(@ModelAttribute @Validated(GroupOrder.class) WorkTimeForm form,
			BindingResult bindingResult, Model model,
			@PathVariable(KintaiConstants.MODEL_KEY_WORK_TIME_ID) int workTimeId) {

		if (bindingResult.hasErrors()) {
			return getUpdateWorkTime(model, workTimeId, form);
		}

		int contractId = monthService.getContractId(workTimeId);
		Months month = monthService.getMonthOfWorkTime(contractId, form.getWorkDay());

		WorkTimes work = modelMapper.map(form, WorkTimes.class);
		work.setWorkTimeId(workTimeId);
		work.setMonthId(month.getMonthId());

		workTimesService.updateWorkTimes(work);

		return KintaiConstants.REDIRECT_HOME;

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
		loginUser.setEmail(form.getNewEmail());

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
		loginUser.setPassword(form.getNewPassword());
		userService.updatePassword(loginUser);

		return KintaiConstants.REDIRECT_HOME;

	}

}
