package com.example.demo.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.example.demo.model.valid.ValidGroup1;
import com.example.demo.model.valid.ValidGroup2;

import lombok.Data;

/**
 * 新規登録用フォーム
 */
@Data
public class SignupForm {

	/** ユーザーネーム */
	@NotBlank(groups = ValidGroup1.class)
	private String userName;

	/** メールアドレス */
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup1.class)
	private String email;

	/** パスワード */
	@Length(min = 8, max = 50, groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup1.class)
	private String password;

	/** パスワード確認用 */
	@Length(min = 8, max = 50)
	private String confilmPassword;

	/** パスワード一致確認バリデーション */
	@AssertTrue(groups = ValidGroup2.class, message = "{pass_check}")
	public boolean isPasswordValid() {
		if (password == null || password.equals("")) {
			return true;
		}

		return password.equals(confilmPassword);
	}

}
