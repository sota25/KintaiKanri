package com.example.demo.entity;

import lombok.Data;

/**
 * monthsテーブル
 */
@Data
public class Months {

	/** monthsテーブルID */
	private int monthId;

	/** contractテーブルID */
	private int contractId;

	/** 年 */
	private int year;

	/** 月 */
	private int month;

}
