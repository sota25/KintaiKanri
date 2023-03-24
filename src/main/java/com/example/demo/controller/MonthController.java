package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.common.KintaiConstants;
import com.example.demo.entity.Contracts;
import com.example.demo.entity.Months;
import com.example.demo.service.ContractService;
import com.example.demo.service.MonthService;

/**
 * 
 * @author sotatk25
 *
 */
@Controller
public class MonthController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private MonthService monthService;

	@GetMapping(KintaiConstants.MONTH_LIST_URL + KintaiConstants.PATH_VAL_CONTRACT_ID)
	public String getMonthList(Model model, @PathVariable(KintaiConstants.MODEL_KEY_CONTRACT_ID) int contractId) {

		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_MONTH_CONTENTS);

		Contracts contract = contractService.findOneCont(contractId);
		model.addAttribute(KintaiConstants.MODEL_KEY_OFFICE_NAME, contract.getOfficeName());

		List<Months> monthList = monthService.findAllMonth(contractId);
		model.addAttribute(KintaiConstants.MODEL_KEY_MONTH_LIST, monthList);

		return KintaiConstants.HOME_LAYOUT_PASS;

	}

}