package com.example.demo.form;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.model.valid.ValidGroup1;

import lombok.Data;

@Data
public class UpdateEndDateForm {

	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

}
