package com.example.demo.shift;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShiftDao{

	private final JdbcTemplate db;

	public ShiftDao(JdbcTemplate db) {
		this.db = db;
	}
	
	// 全スタッフの一覧表示
	public List<StaffEntity> findAllStaff() {
		String sql = "SELECT staff_id, staff_name FROM staff";
		List<Map<String, Object>> resultDb = db.queryForList(sql);
		List<StaffEntity> staffList = new ArrayList<>();
		for (Map<String, Object> result : resultDb) {
			StaffEntity staff = new StaffEntity();
			staff.setStaffId((int) result.get("staff_id"));
			staff.setStaffName((String) result.get("staff_name"));
			staffList.add(staff);
		}
		return staffList;
	}

	// シフト情報の保存
	public void saveShift(ShiftEntity shift) {
		String sql = "INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (?, ?, ?, ?, ?)";
		db.update(sql, shift.getStaffId(), shift.getShiftDate(), shift.getStartTime(), shift.getEndTime(), shift.getRemarks());
	}
	
	// 既存のsearchDbメソッドは削除または修正されます
	// シフト一覧表示 (日付とスタッフIDでフィルタリングできるように修正予定)
	public List<ShiftEntity> findShiftsByDateAndStaff(Date date, int staffId) {
	    String sql = "SELECT shift_id, staff_id, shift_date, start_time, end_time, remarks FROM shifts WHERE shift_date = ? AND staff_id = ?";
	    List<Map<String, Object>> resultDb = db.queryForList(sql, date, staffId);
	    List<ShiftEntity> shiftList = new ArrayList<>();
	    for (Map<String, Object> result : resultDb) {
	        ShiftEntity shift = new ShiftEntity();
	        shift.setShiftId((int) result.get("shift_id"));
	        shift.setStaffId((int) result.get("staff_id"));
	        shift.setShiftDate((Date) result.get("shift_date"));
	        shift.setStartTime((Time) result.get("start_time"));
	        shift.setEndTime((Time) result.get("end_time"));
	        shift.setRemarks((String) result.get("remarks"));
	        shiftList.add(shift);
	    }
	    return shiftList;
	}

	// 指定された月と年の全シフトを取得
	public List<ShiftEntity> findShiftsByMonthAndYear(int year, int month) {
	    String sql = "SELECT shift_id, staff_id, shift_date, start_time, end_time, remarks FROM shifts WHERE YEAR(shift_date) = ? AND MONTH(shift_date) = ?";
	    List<Map<String, Object>> resultDb = db.queryForList(sql, year, month);
	    List<ShiftEntity> shiftList = new ArrayList<>();
	    for (Map<String, Object> result : resultDb) {
	        ShiftEntity shift = new ShiftEntity();
	        shift.setShiftId((int) result.get("shift_id"));
	        shift.setStaffId((int) result.get("staff_id"));
	        shift.setShiftDate(Date.valueOf(result.get("shift_date").toString()));
	        shift.setStartTime(Time.valueOf(result.get("start_time").toString()));
	        shift.setEndTime(Time.valueOf(result.get("end_time").toString()));
	        shift.setRemarks((String) result.get("remarks"));
	        shiftList.add(shift);
	    }
	    return shiftList;
	}

	// 指定された日付の全シフトを取得
	public List<ShiftEntity> findShiftsByDate(Date date) {
	    String sql = "SELECT s.shift_id, s.staff_id, s.shift_date, s.start_time, s.end_time, s.remarks, st.staff_name " +
	                 "FROM shifts s JOIN staff st ON s.staff_id = st.staff_id WHERE s.shift_date = ?";
	    List<Map<String, Object>> resultDb = db.queryForList(sql, date);
	    List<ShiftEntity> shiftList = new ArrayList<>();
	    for (Map<String, Object> result : resultDb) {
	        ShiftEntity shift = new ShiftEntity();
	        shift.setShiftId((int) result.get("shift_id"));
	        shift.setStaffId((int) result.get("staff_id"));
	        shift.setShiftDate(Date.valueOf(result.get("shift_date").toString()));
	        shift.setStartTime(Time.valueOf(result.get("start_time").toString()));
	        shift.setEndTime(Time.valueOf(result.get("end_time").toString()));
	        shift.setRemarks((String) result.get("remarks"));
	        shift.setStaffName((String) result.get("staff_name"));
	        shiftList.add(shift);
	    }
	    return shiftList;
	}

	// シフト情報の更新
	public void updateShift(ShiftEntity shift) {
	    String sql = "UPDATE shifts SET start_time = ?, end_time = ?, remarks = ? WHERE staff_id = ? AND shift_date = ?";
	    db.update(sql, shift.getStartTime(), shift.getEndTime(), shift.getRemarks(), shift.getStaffId(), shift.getShiftDate());
	}

	// シフト情報の削除
	public void deleteShift(int shiftId) {
	    String sql = "DELETE FROM shifts WHERE shift_id = ?";
	    db.update(sql, shiftId);
	}

	// 特定の日付とスタッフIDのシフトを取得 (更新または存在チェック用)
	public ShiftEntity findShiftByDateAndStaff(Date date, int staffId) {
	    String sql = "SELECT shift_id, staff_id, shift_date, start_time, end_time, remarks FROM shifts WHERE shift_date = ? AND staff_id = ?";
	    try {
	        return db.queryForObject(sql, new ShiftRowMapper(), date, staffId);
	    } catch (org.springframework.dao.EmptyResultDataAccessException e) {
	        return null; // シフトが見つからない場合
	    }
	}

	// ShiftRowMapperクラスをここに定義
	private static class ShiftRowMapper implements org.springframework.jdbc.core.RowMapper<ShiftEntity> {
	    @Override
	    public ShiftEntity mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
	        ShiftEntity shift = new ShiftEntity();
	        shift.setShiftId(rs.getInt("shift_id"));
	        shift.setStaffId(rs.getInt("staff_id"));
	        shift.setShiftDate(rs.getDate("shift_date"));
	        shift.setStartTime(rs.getTime("start_time"));
	        shift.setEndTime(rs.getTime("end_time"));
	        shift.setRemarks(rs.getString("remarks"));
	        return shift;
	    }
	}
}
