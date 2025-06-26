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
	
	//編集確認画面
	@PostMapping("/tenpoedit/confirm")
	public String tenpoeditConfirm(
	        @RequestParam("name") String name,
	        @RequestParam("date") String dateStr,
	        @RequestParam("startTime") String newStartTimeStr,
	        @RequestParam("endTime") String newEndTimeStr,
	        @RequestParam(value = "reason", required = false) String reason,
	        @RequestParam(value = "otherReason", required = false) String otherReason,
	        Model model) {

	    LocalDate date = LocalDate.parse(dateStr);

	    KintaiEntity oldKintai = kintaiDao.findByNameAndDate(name, date);
	    if (oldKintai == null) {
	        return "redirect:/tenmanager";
	    }

	   
	    String displayReason = reason;
	    if ("other".equals(reason) && otherReason != null && !otherReason.trim().isEmpty()) {
	        displayReason = otherReason.trim();
	    } else if (reason == null || reason.isEmpty()) {
	        displayReason = "未指定";
	    }


	    model.addAttribute("name", name);
	    model.addAttribute("date", dateStr);
	    model.addAttribute("oldStartTime", oldKintai.getStartTime().toLocalTime().toString()); // 取时间部分字符串
	    model.addAttribute("oldEndTime", oldKintai.getEndTime().toLocalTime().toString());
	    model.addAttribute("newStartTime", newStartTimeStr);
	    model.addAttribute("newEndTime", newEndTimeStr);
	    model.addAttribute("reason", displayReason);

	    return "kintai/Kintai_confirm";
	}

	//修正した情報を更新する
	@PostMapping("/tenpoedit/update")
	public String updateKintai(
	        @RequestParam("name") String name,
	        @RequestParam("date") String dateStr,
	        @RequestParam("startTime") String newStartTimeStr,
	        @RequestParam("endTime") String newEndTimeStr,
	        RedirectAttributes redirectAttributes) {

	    try {
	        // 日付解析
	        LocalDate date = LocalDate.parse(dateStr);

	        // 旧データ取得
	        KintaiEntity kintai = kintaiDao.findByNameAndDate(name, date);
	        if (kintai == null) {
	            redirectAttributes.addFlashAttribute("error", "更新対象のデータが見つかりません。");
	            return "redirect:/tenmanager";
	        }

	        // 新しい時間を LocalDateTime に変換
	        LocalDateTime newStartTime = LocalDateTime.of(date, LocalDateTime.parse(dateStr + "T" + newStartTimeStr).toLocalTime());
	        LocalDateTime newEndTime = LocalDateTime.of(date, LocalDateTime.parse(dateStr + "T" + newEndTimeStr).toLocalTime());

	        // 更新
	        kintai.setStartTime(newStartTime);
	        kintai.setEndTime(newEndTime);

	        // 保存
	        kintaiDao.update(kintai);

	        // 完了後、一覧ページへ戻る
	        redirectAttributes.addFlashAttribute("success", "勤怠情報を更新しました。");
	        return "redirect:/tenmanager";

	    } catch (Exception e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("error", "更新中にエラーが発生しました。");
	        return "redirect:/tenmanager";
	    }
	}

}
