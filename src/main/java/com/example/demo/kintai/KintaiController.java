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

	//管理者ログイン画面(店舗)
	@RequestMapping("/logintenpo")
	public String logintenpo(Model model) {

		return "kintai/Kintai_logintenpo";
	}

	//管理者ログイン画面(本社)
	@RequestMapping("/loginhonsya")
	public String login(Model model) {

		return "kintai/Kintai_loginhonsya";
	}

	//編集画面
	@RequestMapping("/tenpoedit")
	public String tenpoedit(Model model) {

		return "kintai/Kintai_tenpoEdit";
	}
	
	

}
