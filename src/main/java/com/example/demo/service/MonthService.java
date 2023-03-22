package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Months;

@Service
public interface MonthService {

	/**
	 * 
	 * @param month
	 * @return
	 */
	public int insertMonts(Months month);

	/**
	 * 
	 * @param contractId
	 * @return
	 */
	public List<Months> findAllMonth(int contractId);

	/**
	 * 
	 * @param contractId
	 * @param year
	 * @param month
	 * @return
	 */
	public Months findMonthOfWorkTime(int contractId, int year, int month);

	/**
	 * 勤怠情報入力時の該当月テーブル取得 <br>
	 * 入力した勤務日に対する月テーブルが存在しない場合、登録処理を実行 </br>
	 * 
	 * @param contractId 最新の契約のcontractテーブルID
	 * @param workDay    入力した勤務日
	 * @return 勤怠情報入力時の該当月テーブル
	 */
	public Months getMonthOfWorkTime(int contractId, LocalDate workDay);

	/**
	 * 
	 * @param workTimeId
	 * @return
	 */
	public int getContractId(int workTimeId);

}
