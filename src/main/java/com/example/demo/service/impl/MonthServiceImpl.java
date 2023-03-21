package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Months;
import com.example.demo.repository.KintaiMapper;
import com.example.demo.service.MonthService;

@Service
public class MonthServiceImpl implements MonthService {

	@Autowired
	private KintaiMapper kintaiMapper;

	@Override
	public int insertMonts(Months month) {
		return kintaiMapper.insertMonths(month);
	}

	@Override
	public List<Months> findAllMonth(int contractId) {
		return kintaiMapper.findAllMonths(contractId);
	}

	@Override
	public Months findMonthOfWorkTime(int contractId, int year, int month) {
		return kintaiMapper.findMonthOfWorkTime(contractId, year, month);
	}

	/**
	 * 勤怠情報入力時の該当月テーブル取得 <br>
	 * 入力した勤務日に対する月テーブルが存在しない場合、登録処理を実行 </br>
	 * 
	 * @param contractId 最新の契約のcontractテーブルID
	 * @param workDay    入力した勤務日
	 * @return 勤怠情報入力時の該当月テーブル
	 */
	public Months getMonthOfWorkTime(int contractId, LocalDate workDay) {

		int year = workDay.getYear();
		int month = workDay.getMonthValue();

		Months monthsOfWorkTime = findMonthOfWorkTime(contractId, year, month);

		// 勤務日に対応する月テーブルデータが存在しない場合、新規で登録処理を行う
		if (monthsOfWorkTime == null) {
			// monthsテーブルはformから値をとってこないため、ModelMapperは使えない
			// ↑嘘をついている可能性はあり
			Months months = new Months();
			months.setContractId(contractId);
			months.setYear(year);
			months.setMonth(month);

			insertMonts(months);

			monthsOfWorkTime = findMonthOfWorkTime(contractId, year, month);
		}

		return monthsOfWorkTime;
	}
}
