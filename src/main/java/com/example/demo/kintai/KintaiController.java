package com.example.demo.kintai;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
	
	//管理画面（店舗）
	
	// 全体従業員の名前取得
	private List<String> getAllNames() {
	    return kintaiDao.findAllNames(); // Dao に追加するメソッド
	    
	}
	@GetMapping("/tenmanager")
	public String tenmanager(Model model) {
	    model.addAttribute("kintaiList", new ArrayList<KintaiEntity>());
	    model.addAttribute("nameList", getAllNames()); 
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
	    model.addAttribute("nameList", getAllNames());

	    return "kintai/Kintai_tenmanager";
	}

	//編集画面
	@RequestMapping("/tenpoedit")
	public String tenpoedit(Model model) {

		return "kintai/Kintai_tenpoEdit";
	}
	
	//パスワードでログイン
	@PostMapping("/admin/auth")
	public String adminAuth(@RequestParam("password") String password, RedirectAttributes redirectAttributes) {
	    if ("1111".equals(password)) {
	        //パスワードが正しい
	        return "redirect:/tenmanager";
	    } else {
	        //パスワードが違い
	        redirectAttributes.addFlashAttribute("error", "パスワードが違います。");
	        return "redirect:/logintenpo";
	    }
	}
	
	//店舗勤怠情報を編集する
	@GetMapping("/edit")
	public String editPage(@RequestParam("name") String name,
	                       @RequestParam("startTime") String startTimeStr,
	                       Model model,
	                       RedirectAttributes redirectAttributes) {
	    try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);

	        KintaiEntity kintai = kintaiDao.findByNameAndStartTime(name, startTime);
	        if (kintai == null) {
	            redirectAttributes.addFlashAttribute("error", "データが見つかりません。");
	            return "redirect:/tenmanager";
	        }

	        model.addAttribute("kintai", kintai);
	        return "kintai/Kintai_tenpoEdit"; 
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", "エラーが発生しました。");
	        return "redirect:/tenmanager";
	    }
	}

}
