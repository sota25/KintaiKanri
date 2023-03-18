package com.example.demo.service.common;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class CommonService {

	/**
	 * 現在時間取得メソッド
	 * 
	 * @return 現在時間(ミリ秒切り捨て)
	 */
	// Date型だと工数が多くなる
	public static LocalDateTime getCurrentDate() {

		return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

	}
}
