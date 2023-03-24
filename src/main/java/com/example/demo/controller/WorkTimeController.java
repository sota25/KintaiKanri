package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.entity.Months;
import com.example.demo.entity.WorkTimes;
import com.example.demo.form.WorkTimeForm;
import com.example.demo.model.valid.GroupOrder;
import com.example.demo.service.WorkTimesService;
import com.example.demo.service.impl.MonthServiceImpl;

@Controller
public class WorkTimeController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MonthServiceImpl monthService;

	@Autowired
	private WorkTimesService workTimesService;

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
		Months monthsOfWorkTime = monthService.getMonthOfWorkTime(contractId, form.getWorkDay());// 入力した勤務日情報と一致する月テーブル情報を取得
		work.setMonthId(monthsOfWorkTime.getMonthId());// monthIdをsetする必要がある

		workTimesService.insertWorkTimes(work);

		return KintaiConstants.REDIRECT_HOME;
	}

	@GetMapping(KintaiConstants.WORK_TIME_LIST_URL + KintaiConstants.PATH_VAL_MONTH_ID)
	public String getWorkTimeDetail(Model model, @PathVariable(KintaiConstants.MODEL_KEY_MONTH_ID) int monthId,
			@ModelAttribute WorkTimeForm form) {

		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_WORK_TIME_CONTENTS);

		List<WorkTimes> workTimeList = workTimesService.findAllWorkTimes(monthId);
		model.addAttribute(KintaiConstants.MODEL_KEY_WORK_TIME_LIST, workTimeList);

		return KintaiConstants.HOME_LAYOUT_PASS;

	}

	@GetMapping(KintaiConstants.WORK_TIME_UPDATE_URL + KintaiConstants.PATH_VAL_WORK_TIME_ID)
	public String getUpdateWorkTime(Model model, @PathVariable(KintaiConstants.MODEL_KEY_WORK_TIME_ID) int workTimeId,
			@ModelAttribute WorkTimeForm form) {
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_UPDATE_WORK_TIME_CONTENTS);

		WorkTimes workTime = workTimesService.findOneWorkTimes(workTimeId);

		modelMapper.map(workTime, form);

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

		WorkTimes workTime = workTimesService.findOneWorkTimes(workTimeId);
		modelMapper.map(form, workTime);
		workTime.setMonthId(month.getMonthId());

		workTimesService.updateWorkTimes(workTime);

		return KintaiConstants.REDIRECT_HOME;

	}

	@GetMapping(KintaiConstants.WORK_TIME_DELETE_URL + KintaiConstants.PATH_VAL_WORK_TIME_ID)
	public String getDeleteWorkTime(Model model, @PathVariable(KintaiConstants.MODEL_KEY_WORK_TIME_ID) int workTimeId,
			@ModelAttribute WorkTimeForm form) {
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_DELETE_WORK_TIME_CONTENTS);

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
			+ KintaiConstants.PATH_VAL_WORK_TIME_ID, params = KintaiConstants.PARAM_DELETE)
	public String postDeleteWorkTime(@ModelAttribute WorkTimeForm form, Model model,
			@PathVariable(KintaiConstants.MODEL_KEY_WORK_TIME_ID) int workTimeId) {

		workTimesService.deleteWorkTimes(workTimeId);

		return KintaiConstants.REDIRECT_HOME;

	}
}
