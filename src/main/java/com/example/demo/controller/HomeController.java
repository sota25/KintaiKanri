package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.common.KintaiConstants;

@Controller
public class HomeController {

	@GetMapping(KintaiConstants.HOME_URL)
	public String getHome(Model model, @AuthenticationPrincipal UserDetails user) {

		model.addAttribute(KintaiConstants.MODEL_KEY_CONTENTS, KintaiConstants.MODEL_VAL_HOME_CONTENTS);

		LocalDate currentDate = LocalDate.now();
		model.addAttribute(KintaiConstants.MODEL_KEY_YEAR, currentDate.getYear());
		model.addAttribute(KintaiConstants.MODEL_KEY_MONTH, currentDate.getMonthValue());
		model.addAttribute(KintaiConstants.MODEL_KEY_DAY, currentDate.getDayOfMonth());
		model.addAttribute(KintaiConstants.MODEL_KEY_DAY_OF_WEEK,
				currentDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.JAPANESE));

		return KintaiConstants.HOME_LAYOUT_PASS;
	}

}
