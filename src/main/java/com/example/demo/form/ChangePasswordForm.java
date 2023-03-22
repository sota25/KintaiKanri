package com.example.demo.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.example.demo.model.valid.ValidGroup1;
import com.example.demo.model.valid.ValidGroup2;

import lombok.Data;

@Data
public class ChangePasswordForm {

	@Length(min = 8, max = 50, groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup1.class)
	private String oldPassword;

	@Length(min = 8, max = 50, groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup1.class)
	private String newPassword;

	@Length(min = 8, max = 50)
	private String confilmNewPassword;

	@AssertTrue(groups = ValidGroup2.class, message = "{pass_check}")
	public boolean isNewPasswordValid() {
		if (newPassword == null || newPassword.isEmpty() || newPassword.equals("")) {
			return true;
		}

		return newPassword.equals(confilmNewPassword);
	}
}
