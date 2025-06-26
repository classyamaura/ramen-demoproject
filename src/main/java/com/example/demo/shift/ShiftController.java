package com.example.demo.shift;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	//シフト提出画面 (登録用)
	@GetMapping("/submit")
	public String submit(Model model,
	                     @RequestParam(value = "year", required = false) Integer yearParam,
	                     @RequestParam(value = "month", required = false) Integer monthParam) {
	    
	    int currentYear = (yearParam != null) ? yearParam : java.time.LocalDate.now().getYear();
	    int currentMonth = (monthParam != null) ? monthParam : 8; // デフォルトを8月 (August) に設定

	    List<StaffEntity> staffList = shiftdao.findAllStaff();
	    List<ShiftEntity> shiftsForMonth = shiftdao.findShiftsByMonthAndYear(currentYear, currentMonth);
	    model.addAttribute("staffList", staffList);
	    model.addAttribute("shiftsForMonth", shiftsForMonth);
	    model.addAttribute("currentYear", currentYear);
	    model.addAttribute("currentMonth", currentMonth);
		return "shift/shift-submit";

	}

	// シフト登録処理
	@PostMapping("/registerShift") // エンドポイント名を変更
	public String registerShift(@RequestParam("shiftDate") String shiftDateStr,
	                            @RequestParam("staffId") int staffId,
	                            @RequestParam(value = "startTime", required = false) String startTimeStr,
	                            @RequestParam(value = "endTime", required = false) String endTimeStr,
	                            @RequestParam(value = "remarks", required = false) String remarks,
	                            @RequestParam("sourcePage") String sourcePage,
	                            RedirectAttributes redirectAttributes) {
	    try {
	        Date shiftDate = Date.valueOf(LocalDate.parse(shiftDateStr));
	        
	        // 時刻が入力されている場合のみシフトを登録/更新
	        if (startTimeStr != null && !startTimeStr.isEmpty() &&
	            endTimeStr != null && !endTimeStr.isEmpty()) {

	            Time startTime = Time.valueOf(startTimeStr + ":00");
	            Time endTime = Time.valueOf(endTimeStr + ":00");
	            String remark = (remarks != null) ? remarks : "";

	            ShiftEntity existingShift = shiftdao.findShiftByDateAndStaff(shiftDate, staffId);

	            if (existingShift == null) {
	                // 新規登録
	                ShiftEntity newShift = new ShiftEntity();
	                newShift.setStaffId(staffId);
	                newShift.setShiftDate(shiftDate);
	                newShift.setStartTime(startTime);
	                newShift.setEndTime(endTime);
	                newShift.setRemarks(remark);
	                shiftdao.saveShift(newShift);
	            } else {
	                // 更新
	                existingShift.setStartTime(startTime);
	                existingShift.setEndTime(endTime);
	                existingShift.setRemarks(remark);
	                shiftdao.updateShift(existingShift);
	            }
	        } else { // 時刻がクリアされた場合、既存シフトがあれば削除
	            ShiftEntity existingShift = shiftdao.findShiftByDateAndStaff(shiftDate, staffId);
	            if (existingShift != null) {
	                shiftdao.deleteShift(existingShift.getShiftId());
	            }
	        }
	        redirectAttributes.addFlashAttribute("message", "シフトが正常に登録/更新されました！");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", "シフトの登録/更新に失敗しました: " + e.getMessage());
	    }
	    return "redirect:/" + sourcePage; // 元のページにリダイレクト
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

	//管理者シフト一覧（原案）- シフトカレンダー表示 (編集用)
	@GetMapping("/genan")
	public String genan(Model model,
	                    @RequestParam(value = "year", required = false) Integer yearParam,
	                    @RequestParam(value = "month", required = false) Integer monthParam) {

		List<StaffEntity> staffList = shiftdao.findAllStaff();
		model.addAttribute("staffList", staffList);

		// 現在の年と月を取得 (またはパラメータから取得)
		int currentYear = (yearParam != null) ? yearParam : LocalDate.now().getYear();
		int currentMonth = (monthParam != null) ? monthParam : 8; // デフォルトを8月 (August) に設定

		// その月の全シフトデータを取得
		List<ShiftEntity> shiftsForMonth = shiftdao.findShiftsByMonthAndYear(currentYear, currentMonth);
		model.addAttribute("shiftsForMonth", shiftsForMonth);

		// 今日の日付の全スタッフのシフト情報を取得し、モデルに追加
		Date today = Date.valueOf(LocalDate.now()); // 初期表示を現在の日付に設定
		List<ShiftEntity> shiftsForToday = shiftdao.findShiftsByDate(today);
		model.addAttribute("shiftsForSelectedDate", shiftsForToday); // 新しく追加するモデル属性
		model.addAttribute("selectedDateDisplay", LocalDate.now().getMonthValue() + "月" + LocalDate.now().getDayOfMonth() + "日"); // 表示用の日付

		model.addAttribute("currentYear", currentYear);
		model.addAttribute("currentMonth", currentMonth);

		return "shift/shift-genan";
	}

	// 特定の日付のシフト情報を取得 (JSON API)
	@GetMapping("/getShiftsForDate")
	@ResponseBody // JSON形式でデータを返す
	public List<ShiftEntity> getShiftsForDate(@RequestParam("date") String dateStr) {
		Date shiftDate = Date.valueOf(LocalDate.parse(dateStr));
		return shiftdao.findShiftsByDate(shiftDate);
	}

	@RequestMapping("/complete")
	public String conmplete(Model model) {
		return "shift/shift-complete";
	}

    // シフト削除処理
    @PostMapping("/deleteShift")
    @ResponseBody // クライアントにメッセージを返すため
    public String deleteShift(@RequestParam("shiftId") int shiftId, RedirectAttributes redirectAttributes) {
        try {
            shiftdao.deleteShift(shiftId);
            return "シフトが正常に削除されました。";
        } catch (Exception e) {
            return "シフトの削除に失敗しました: " + e.getMessage();
        }
    }

    // 日付ごとのシフトを一括保存・更新・削除するAPI
    @PostMapping("/saveDailySchedule")
    @ResponseBody
    public String saveDailySchedule(@RequestBody DailyShiftUpdate dailyShiftUpdate) {
        try {
            Date shiftDate = Date.valueOf(LocalDate.parse(dailyShiftUpdate.getShiftDate()));
            List<ShiftEntry> shiftEntries = dailyShiftUpdate.getShifts();

            for (ShiftEntry entry : shiftEntries) {
                int staffId = entry.getStaffId();
                String startTimeStr = entry.getStartTime();
                String endTimeStr = entry.getEndTime();
                String remarks = entry.getRemarks();

                ShiftEntity existingShift = shiftdao.findShiftByDateAndStaff(shiftDate, staffId);

                if (startTimeStr != null && !startTimeStr.isEmpty() &&
                    endTimeStr != null && !endTimeStr.isEmpty()) {
                    // 新規登録または更新
                    Time startTime = Time.valueOf(startTimeStr + ":00");
                    Time endTime = Time.valueOf(endTimeStr + ":00");

                    if (existingShift == null) {
                        ShiftEntity newShift = new ShiftEntity();
                        newShift.setStaffId(staffId);
                        newShift.setShiftDate(shiftDate);
                        newShift.setStartTime(startTime);
                        newShift.setEndTime(endTime);
                        newShift.setRemarks(remarks);
                        shiftdao.saveShift(newShift);
                    } else {
                        existingShift.setStartTime(startTime);
                        existingShift.setEndTime(endTime);
                        existingShift.setRemarks(remarks);
                        shiftdao.updateShift(existingShift);
                    }
                } else {
                    // シフト時間が空の場合、既存シフトがあれば削除
                    if (existingShift != null) {
                        shiftdao.deleteShift(existingShift.getShiftId());
                    }
                }
            }
            return "シフトデータが正常に保存されました！";
        } catch (Exception e) {
            return "シフトデータの保存に失敗しました: " + e.getMessage();
        }
    }

}