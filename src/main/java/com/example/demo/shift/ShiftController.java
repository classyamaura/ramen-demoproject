package com.example.demo.shift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShiftController {


//index画面
@RequestMapping("/top")
public String top(Model model) {
	return "shift/shift-index";
	
}
//シフト提出画面
@RequestMapping("/submit")
public String submit(Model model) {
	return "shift/shift-submit";
	
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

}