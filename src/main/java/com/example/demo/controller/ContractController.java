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
import com.example.demo.entity.Contracts;
import com.example.demo.form.ContractForm;
import com.example.demo.model.valid.GroupOrder;
import com.example.demo.service.ContractService;

@Controller
public class ContractController {

	@Autowired
	private ContractService service;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * 
	 * @param form
	 * @param model
	 * @param user
	 * @return
	 */
	@GetMapping(KintaiConstants.CONTRACT_URL)
	public String getAddContract(@ModelAttribute ContractForm form, Model model,
			@AuthenticationPrincipal UserDetailsImpl user) {
		return KintaiConstants.ADD_CONTRACT_PASS;
	}

	@PostMapping(KintaiConstants.CONTRACT_URL)
	public String postAddContract(@ModelAttribute @Validated(GroupOrder.class) ContractForm form, BindingResult result,
			Model model, @AuthenticationPrincipal UserDetailsImpl user) {

		if (result.hasErrors()) {
			return getAddContract(form, model, user);
		}

		Contracts contract = modelMapper.map(form, Contracts.class);
		contract.setUserId(user.getUserId());

		service.insertCont(contract);

		return KintaiConstants.REDIRECT_HOME;
	}
}
