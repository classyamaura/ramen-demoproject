package com.example.demo.shift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShiftController {



@RequestMapping("/top")
public String top(Model model) {
	return "shift/shift-index";
	
}

//管理者画面
@RequestMapping("/manage")
public String manage(Model model) {
	return "shift/shift-manage";
}

}