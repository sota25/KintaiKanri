package com.example.demo.controller;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.KintaiConstants;
import com.example.demo.common.security.impl.UserDetailsImpl;
import com.example.demo.entity.Contracts;
import com.example.demo.entity.Months;
import com.example.demo.entity.Users;
import com.example.demo.entity.WorkTimes;
import com.example.demo.form.ContractForm;
import com.example.demo.form.SignupForm;
import com.example.demo.form.WorkTimeForm;
import com.example.demo.model.valid.GroupOrder;
import com.example.demo.service.ContractService;
import com.example.demo.service.MonthService;
import com.example.demo.service.UserService;
import com.example.demo.service.WorkTimeService;

/**
 * 登録処理関連Controllerクラス
 * 
 * @author S.TAKASUGI
 */
@Controller
public class InsertController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private ContractService contractService;

	@Autowired
	private MonthService monthService;

	@Autowired
	private WorkTimeService workTimeService;

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

	/**
	 * 勤怠登録画面のgetメソッド
	 * 
	 * @param form       勤怠登録フォーム
	 * @param model      Modelクラス
	 * @param contractId 最新の契約のcontractテーブルID
	 * @return 勤怠登録画面のパス
	 */
	@GetMapping(KintaiConstants.WORK_TIME_URL + (KintaiConstants.PATH_VAL_CONTRACT_ID))
	public String getWorkTime(@ModelAttribute WorkTimeForm form, Model model,
			@PathVariable(KintaiConstants.MODEL_KEY_CONTRACT_ID) int contractId) {

		model.addAttribute("contents", "contract/workTime::workTime_contents");

		return KintaiConstants.WORK_TIME_PASS;
	}

	/**
	 * 勤怠登録画面のpostメソッド
	 * 
	 * @param form       勤怠登録フォーム
	 * @param result     BindingResultクラス
	 * @param model      Modelクラス
	 * @param contractId 最新の契約のcontractテーブルID
	 * @return home画面へのリダイレクト
	 */
	@Transactional
	@PostMapping(KintaiConstants.WORK_TIME_URL + (KintaiConstants.PATH_VAL_CONTRACT_ID))
	public String postWorkTime(@ModelAttribute @Validated(GroupOrder.class) WorkTimeForm form, BindingResult result,
			Model model, @PathVariable(KintaiConstants.MODEL_KEY_CONTRACT_ID) int contractId) {

		if (result.hasErrors()) {
			return getWorkTime(form, model, contractId);
		}

		WorkTimes work = modelMapper.map(form, WorkTimes.class);
		Months monthsOfWorkTime = getMonthOfWorkTime(contractId, form.getWorkDay());// 入力した勤務日情報と一致する月テーブル情報を取得
		work.setMonthId(monthsOfWorkTime.getMonthId());// monthIdをsetする必要がある

		workTimeService.insertWorkTimes(work);

		return KintaiConstants.REDIRECT_HOME;
	}

	/**
	 * 勤怠情報入力時の該当月テーブル取得 <br>
	 * 入力した勤務日に対する月テーブルが存在しない場合、登録処理を実行 </br>
	 * 
	 * @param contractId 最新の契約のcontractテーブルID
	 * @param workDay    入力した勤務日
	 * @return 勤怠情報入力時の該当月テーブル
	 */
	private Months getMonthOfWorkTime(int contractId, LocalDate workDay) {

		int year = workDay.getYear();
		int month = workDay.getMonthValue();

		Months monthsOfWorkTime = monthService.findMonthOfWorkTime(contractId, year, month);

		// 勤務日に対応する月テーブルデータが存在しない場合、新規で登録処理を行う
		if (monthsOfWorkTime == null) {
			// monthsテーブルはformから値をとってこないため、ModelMapperは使えない
			// ↑嘘をついている可能性はあり
			Months months = new Months();
			months.setContractId(contractId);
			months.setYear(year);
			months.setMonth(month);

			monthService.insertMonts(months);

			monthsOfWorkTime = monthService.findMonthOfWorkTime(contractId, year, month);
		}

		return monthsOfWorkTime;
	}

}
