package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.common.KintaiConstants;
import com.example.demo.common.security.impl.UserDetailsImpl;
import com.example.demo.entity.Contracts;
import com.example.demo.service.ContractService;
import com.example.demo.service.common.CommonService;

/**
 * ホーム画面関連Controllerクラス
 * 
 * @author sotatk25
 */
@Controller
public class HomeController {

	@Autowired
	private ContractService contractService;

	/**
	 * ホーム画面のgetメソッド
	 * 
	 * @param model Modelクラス
	 * @param user  ログインユーザー情報
	 * @return ホーム画面のパス
	 */
	@GetMapping(KintaiConstants.HOME_URL)
	public String getHome(Model model, @AuthenticationPrincipal UserDetailsImpl user) {

		// コンテンツ部分にホーム画面を表示するための文字列を登録
		// 解体新書第二版ベースだといらないかも
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_HOME_CONTENTS);

		// ホーム画面で必要な契約情報を登録
		Contracts currentCont = contractService.findCurrentCont(user.getUserId());
		model.addAttribute(KintaiConstants.MODEL_KEY_CONTRACT_ID, currentCont.getUserId());
		model.addAttribute(KintaiConstants.MODEL_KEY_OFFICE_NAME, currentCont.getOfficeName());

		// ホーム画面に表示する現在時刻を取得
		LocalDateTime currentDate = CommonService.getCurrentDate();
		model.addAttribute(KintaiConstants.MODEL_KEY_YEAR, currentDate.getYear());
		model.addAttribute(KintaiConstants.MODEL_KEY_MONTH, currentDate.getMonthValue());
		model.addAttribute(KintaiConstants.MODEL_KEY_DAY, currentDate.getDayOfMonth());
		model.addAttribute(KintaiConstants.MODEL_KEY_DAY_OF_WEEK,
				currentDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.JAPANESE));

		return KintaiConstants.HOME_LAYOUT_PASS;
	}

}
