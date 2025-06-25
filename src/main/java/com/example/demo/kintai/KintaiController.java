package com.example.demo.kintai;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KintaiController {

	//トップページの表示
	@RequestMapping("/kintaitop")
	public String kintaitop(Model model) {

		return "kintai/Kintai_top";
	}
	//管理者ログイン画面
	@RequestMapping("/login")
	public String login(Model model) {

		return "kintai/Kintai_login";
	}


}
