package com.example.demo.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.model.valid.ValidGroup1;

import lombok.Data;

/**
 * 契約登録フォーム
 */
@Data
public class ContractForm {

	/** 契約時間 */
	@NotNull(groups = ValidGroup1.class)
	private int contractTime;

	/** 始業時間 */
	@NotNull(groups = ValidGroup1.class)
	private LocalTime startTime;

	/** 休憩時間 */
	@NotNull(groups = ValidGroup1.class)
	private LocalTime breakTime;

	/** 終業時間 */
	@NotNull(groups = ValidGroup1.class)
	private LocalTime endTime;

	/** 契約開始日 */
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	/** 会社名 */
	@NotBlank(groups = ValidGroup1.class)
	private String officeName;
}
