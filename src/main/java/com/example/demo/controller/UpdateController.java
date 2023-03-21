package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.KintaiConstants;
import com.example.demo.entity.Months;
import com.example.demo.entity.WorkTimes;
import com.example.demo.form.WorkTimeForm;
import com.example.demo.model.valid.GroupOrder;
import com.example.demo.service.WorkTimesService;
import com.example.demo.service.impl.MonthServiceImpl;

@Controller
public class UpdateController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MonthServiceImpl monthService;

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

		Months month = monthService.getMonthOfWorkTime(workTimeId, form.getWorkDay());

		WorkTimes work = modelMapper.map(form, WorkTimes.class);
		work.setWorkTimeId(workTimeId);
		work.setMonthId(month.getMonthId());

		workTimesService.updateWorkTimes(work);

		return KintaiConstants.REDIRECT_HOME;

	}

}
