package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

/**
 * contractsテーブル
 */
@Data
public class Contracts {

	/** ContractsテーブルID */
	private int contractId;

	/** UsersテーブルIDContracts */
	private int userId;

	/** 契約時間 */
	private int contractTime;

	/** 始業時間 */
	private LocalTime startTime;

	/** 休憩時間 */
	private LocalTime breakTime;

	/** 終業時間 */
	private LocalTime endTime;

	/** 契約開始日 */
	private LocalDate startDate;

	/** 会社名 */
	private String officeName;

	/** 契約終了日 */
	private LocalDate endDate;
}
