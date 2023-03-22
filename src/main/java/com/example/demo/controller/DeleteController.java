package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.KintaiConstants;
import com.example.demo.entity.WorkTimes;
import com.example.demo.form.WorkTimeForm;
import com.example.demo.service.WorkTimesService;

@Controller
public class DeleteController {

	@Autowired
	private WorkTimesService workTimesService;

	@GetMapping(KintaiConstants.WORK_TIME_DELETE_URL + KintaiConstants.PATH_VAL_WORK_TIME_ID)
	public String getDeleteWorkTime(Model model, @PathVariable(KintaiConstants.MODEL_KEY_WORK_TIME_ID) int workTimeId,
			@ModelAttribute WorkTimeForm form) {
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, "contract/deleteWorkTime::deleteWorkTime_contents");

		WorkTimes workTime = workTimesService.findOneWorkTimes(workTimeId);

		model.addAttribute(KintaiConstants.MODEL_KEY_WORK_TIME_ID, workTime.getWorkTimeId());
		model.addAttribute(KintaiConstants.MODEL_KEY_MONTH_ID, workTime.getMonthId());
		model.addAttribute(KintaiConstants.MODEL_KEY_WORK_DAY, workTime.getWorkDay());
		model.addAttribute(KintaiConstants.MODEL_KEY_START_TIME, workTime.getStartTime());
		model.addAttribute(KintaiConstants.MODEL_KEY_BREAK_TIME, workTime.getBreakTime());
		model.addAttribute(KintaiConstants.MODEL_KEY_END_TIME, workTime.getEndTime());

		return KintaiConstants.HOME_LAYOUT_PASS;

	}

	@PostMapping(value = KintaiConstants.WORK_TIME_DELETE_URL
			+ KintaiConstants.PATH_VAL_WORK_TIME_ID, params = "delete")
	public String postDeleteWorkTime(@ModelAttribute WorkTimeForm form, Model model,
			@PathVariable(KintaiConstants.MODEL_KEY_WORK_TIME_ID) int workTimeId) {

		workTimesService.deleteWorkTimes(workTimeId);

		return KintaiConstants.REDIRECT_HOME;

	}

}
