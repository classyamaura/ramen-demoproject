package com.example.demo.kintai;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class KintaiController {
	
	@Autowired
	private KintaiDao kintaiDao;


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
	
	@GetMapping("/tenmanager")
	public String tenmanager(Model model) {
	    model.addAttribute("kintaiList", new ArrayList<KintaiEntity>());
	    return "kintai/Kintai_tenmanager";
	}

	@GetMapping("/tenmanager/search")
	public String tenmanagerSearch(
	        @RequestParam(required = false) String yearMonth,
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
	        @RequestParam(required = false) String name,
	        Model model) {

	    List<KintaiEntity> kintaiList = kintaiDao.search(name, date, yearMonth);

	    model.addAttribute("kintaiList", kintaiList);
	    model.addAttribute("searchName", name);
	    model.addAttribute("searchDate", date);
	    model.addAttribute("searchYearMonth", yearMonth);

	    return "kintai/Kintai_tenmanager";
	}

}
