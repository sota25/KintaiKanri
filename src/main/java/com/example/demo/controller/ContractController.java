package com.example.demo.controller;

import java.util.List;

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
import com.example.demo.form.ChangeContractForm;
import com.example.demo.form.ContractForm;
import com.example.demo.form.UpdateEndDateForm;
import com.example.demo.model.valid.GroupOrder;
import com.example.demo.service.ContractService;

@Controller
public class ContractController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ContractService contractService;

	/**
	 * 契約登録画面のgetメソッド
	 * 
	 * @param form  契約登録フォーム
	 * @param model Modelクラス
	 * @param user  ログインユーザー情報
	 * @return 契約登録画面のパス
	 */
	@GetMapping(KintaiConstants.CONTRACT_URL)
	public String getAddContract(@ModelAttribute ContractForm form, Model model,
			@AuthenticationPrincipal UserDetailsImpl user) {

		return KintaiConstants.ADD_CONTRACT_PASS;
	}

	/**
	 * 契約登録画面のpostメソッド
	 *
	 * @param form   契約登録フォーム
	 * @param result BindingResultクラス
	 * @param model  Modelクラス
	 * @param user   ログインユーザー情報
	 * @return home画面へのリダイレクト
	 */
	@PostMapping(KintaiConstants.CONTRACT_URL)
	public String postAddContract(@ModelAttribute @Validated(GroupOrder.class) ContractForm form, BindingResult result,
			Model model, @AuthenticationPrincipal UserDetailsImpl user) {

		if (result.hasErrors()) {
			return getAddContract(form, model, user);
		}

		Contracts contract = modelMapper.map(form, Contracts.class);
		contract.setUserId(user.getUserId()); // ログインユーザーのユーザーIDをsetする必要がある

		contractService.insertCont(contract);

		return KintaiConstants.REDIRECT_HOME;
	}

	@GetMapping(KintaiConstants.CONTRACT_LIST_URL)
	public String getContractList(Model model, @AuthenticationPrincipal UserDetailsImpl user) {
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_CONTRACTS_CONTENTS);

		List<Contracts> contracts = contractService.findAllCont(user.getUserId());
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTRACT_LIST, contracts);

		return KintaiConstants.HOME_LAYOUT_PASS;
	}

	@GetMapping(KintaiConstants.CHANGE_CONTRACT_URL)
	public String getChangeContract(@ModelAttribute ChangeContractForm form, Model model,
			@AuthenticationPrincipal UserDetailsImpl user) {
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_CHANGE_CONTRACT_CONTENTS);

		Contracts contract = contractService.findCurrentCont(user.getUserId());

		modelMapper.map(contract, form);

		model.addAttribute(KintaiConstants.MODEL_KEY_CONTRACT, contract);

		return KintaiConstants.HOME_LAYOUT_PASS;
	}

	@PostMapping(KintaiConstants.CHANGE_CONTRACT_URL)
	public String postChangeContract(@ModelAttribute @Validated(GroupOrder.class) ChangeContractForm form,
			BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetailsImpl user) throws Exception {

		if (bindingResult.hasErrors()) {
			return getChangeContract(form, model, user);
		}

		Contracts contract = contractService.findCurrentCont(user.getUserId());
		modelMapper.map(form, contract);

		contractService.updateCont(contract);

		return KintaiConstants.REDIRECT_HOME;
	}

	@GetMapping((KintaiConstants.UPDATE_END_DATE_URL))
	public String getUpdateEndDate(@ModelAttribute UpdateEndDateForm form, Model model,
			@AuthenticationPrincipal UserDetailsImpl user) {
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_UPDARE_END_DATE_CONTENTS);

		return KintaiConstants.HOME_LAYOUT_PASS;
	}

	@PostMapping((KintaiConstants.UPDATE_END_DATE_URL))
	public String postUpdateEndDate(@ModelAttribute @Validated(GroupOrder.class) UpdateEndDateForm form,
			BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetailsImpl user) throws Exception {

		if (bindingResult.hasErrors()) {
			return getUpdateEndDate(form, model, user);
		}

		Contracts contract = contractService.findCurrentCont(user.getUserId());
		modelMapper.map(form, contract);

		contractService.updateCont(contract);

		return KintaiConstants.REDIRECT_CONTRACT;
	}
}
