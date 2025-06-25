package com.example.demo.shift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ShiftController {
	private ShiftDao shiftdao;

	public ShiftController(ShiftDao shiftdao) {
		this.shiftdao = shiftdao;

	}


//index画面
@RequestMapping("/shifttop")
public String top(Model model) {
	return "shift/shift-index";
	
}
//シフト提出画面
@RequestMapping("/submit")
public String submit(Model model) {
	return "shift/shift-submit";
	
}
//自分のシフトを確認する画面に入るログイン
@RequestMapping("/personal")
public String personal(Model model) {
	return "shift/shiftpersonal";
	
}
//自分のシフトを確認する画面
@RequestMapping("/show")
public String show(Model model) {
	return "shift/shift-show";
	
}


//管理者画面
@RequestMapping("/manage")
public String manage(Model model) {
	return "shift/shift-manage";
}

//管理者シフト一覧（原案）
@RequestMapping("/genan")
public String genan(Model model) {
	return "shift/shift-genan";
}

}