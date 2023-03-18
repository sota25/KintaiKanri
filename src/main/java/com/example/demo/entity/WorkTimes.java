package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

/**
 * work_timesテーブル
 */
@Data
public class WorkTimes {

	/** work_timeテーブルID */
	private int workTimeId;

	/** monthテーブルID */
	private int monthId;

	/** 勤務日 */
	private LocalDate workDay;

	/** 始業時間 */
	private LocalTime startTime;

	/** 休憩時間 */
	private LocalTime breakTime;

	/** 終業時間 */
	private LocalTime endTime;
}
