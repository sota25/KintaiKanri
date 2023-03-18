package com.example.demo.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.model.valid.ValidGroup1;

import lombok.Data;

/**
 * 勤怠登録用フォーム
 */
@Data
public class WorkTimeForm {

	/** 勤務日 */
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate workDay;

	/** 始業時間 */
	@NotNull(groups = ValidGroup1.class)
	private LocalTime startTime;

	/** 休憩時間 */
	@NotNull(groups = ValidGroup1.class)
	private LocalTime breakTime;

	/** 終業時間 */
	@NotNull(groups = ValidGroup1.class)
	private LocalTime endTime;

}
