package com.example.springkadaiform.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {


@GetMapping("/form")
public String showForm(Model model) {
	//Flash属性として contactForm（バリデーション結果を含む）が渡ってきていればそれを使う
    if (!model.containsAttribute("contactForm")) {
    	// 無ければ新しく空のフォームを作る
        model.addAttribute("contactForm", new ContactForm());
    }
    return "contactFormView";
}

	// 確認画面の表示 /confirmでsubmitされたらの処理
	@PostMapping("/confirm")
	public String confirmForm(
			@Validated ContactForm form,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		// バリデーションNG
    if (bindingResult.hasErrors()) {
    	// フォームクラスをビューに受け渡す
        redirectAttributes.addFlashAttribute("contactForm", form);
        // バリデーション結果をビューに受け渡す
        redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
            + Conventions.getVariableName(form), bindingResult);
        return "redirect:/form";
    }

		// バリデーションOK
		redirectAttributes.addFlashAttribute("contactForm", form);
		return "confirmView"; // POSTのままビュー表示
	}
}