package com.example.demo.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.demo.model.valid.ValidGroup1;
import com.example.demo.model.valid.ValidGroup2;

import lombok.Data;

@Data
public class ChangeEmailForm {

	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup1.class)
	private String newEmail;

	@Email
	private String confilmNewEmail;

	@AssertTrue(groups = ValidGroup2.class, message = "{email_check}")
	public boolean isNewEmailValid() {
		if (newEmail == null || newEmail.isEmpty() || newEmail.equals("")) {
			return true;
		}

		return newEmail.equals(confilmNewEmail);
	}

}
