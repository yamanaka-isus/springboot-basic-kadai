package com.example.springkadaiform.form;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactForm {

	@NotBlank(message = "名前は必須です")
	private String name;
	
	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "メールアドレスの形式が正しくありません")
	private String email;
	
	@NotBlank(message = "お問い合わせ内容は必須です")
	private String message;
	
}
