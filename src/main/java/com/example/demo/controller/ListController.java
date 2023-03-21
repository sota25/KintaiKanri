package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.common.KintaiConstants;
import com.example.demo.common.security.impl.UserDetailsImpl;
import com.example.demo.entity.Contracts;
import com.example.demo.entity.Months;
import com.example.demo.entity.WorkTimes;
import com.example.demo.form.WorkTimeForm;
import com.example.demo.service.ContractService;
import com.example.demo.service.MonthService;
import com.example.demo.service.WorkTimesService;

/**
 * 
 * @author sotatk25
 *
 */
@Controller
public class ListController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private MonthService monthService;

	@Autowired
	private WorkTimesService workTimesService;

	@GetMapping(KintaiConstants.CONTRACT_LIST_URL)
	public String getContractList(Model model, @AuthenticationPrincipal UserDetailsImpl user) {
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_CONTRACT_CONTENTS);

		List<Contracts> contracts = contractService.findAllCont(user.getUserId());
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTRACT_LIST, contracts);

		return KintaiConstants.HOME_LAYOUT_PASS;
	}

	@GetMapping(KintaiConstants.MONTH_LIST_URL + KintaiConstants.PATH_VAL_CONTRACT_ID)
	public String getMonthList(Model model, @PathVariable(KintaiConstants.MODEL_KEY_CONTRACT_ID) int contractId) {

		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_MONTH_CONTENTS);

		Contracts contract = contractService.findOneCont(contractId);
		model.addAttribute(KintaiConstants.MODEL_KEY_OFFICE_NAME, contract.getOfficeName());

		List<Months> monthList = monthService.findAllMonth(contractId);
		model.addAttribute(KintaiConstants.MODEL_KEY_MONTH_LIST, monthList);

		return KintaiConstants.HOME_LAYOUT_PASS;

	}

	@GetMapping(KintaiConstants.WORK_TIME_LIST_URL + KintaiConstants.PATH_VAL_MONTH_ID)
	public String getWorkTimeDetail(Model model, @PathVariable(KintaiConstants.MODEL_KEY_MONTH_ID) int monthId,
			@ModelAttribute WorkTimeForm form) {

		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_WORK_TIME_CONTENTS);

		List<WorkTimes> workTimeList = workTimesService.findAllWorkTimes(monthId);
		model.addAttribute(KintaiConstants.MODEL_KEY_WORK_TIME_LIST, workTimeList);

		return KintaiConstants.HOME_LAYOUT_PASS;

	}
}