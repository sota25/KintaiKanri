package com.example.demo.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import com.example.demo.model.valid.ValidGroup1;

import lombok.Data;

@Data
public class ChangeContractForm {

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

}
