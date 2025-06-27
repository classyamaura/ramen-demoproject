package com.example.demo.kintai;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KintaiDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 一覧検索（名前・日付・年月）　※employee表とJOINして時給も取得
    public List<KintaiEntity> search(String name, LocalDate date, String yearMonth) {
        StringBuilder sql = new StringBuilder(
            "SELECT k.id, k.name, k.start_time, k.end_time, e.hourly_wage " +
            "FROM kintai k " +
            "LEFT JOIN employee e ON k.name = e.name " +
            "WHERE 1=1 "
        );
        List<Object> params = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            sql.append(" AND k.name = ? ");
            params.add(name);
        }

        if (date != null) {
            sql.append(" AND FORMATDATETIME(k.start_time, 'yyyy-MM-dd') = ? ");
            params.add(date.toString());
        } else if (yearMonth != null && !yearMonth.isEmpty()) {
            sql.append(" AND FORMATDATETIME(k.start_time, 'yyyy-MM') = ? ");
            params.add(yearMonth);
        }

        sql.append(" ORDER BY k.start_time");

        return jdbcTemplate.query(sql.toString(), params.toArray(), new KintaiRowMapper());
    }

    // 名前一覧取得
    public List<String> findAllNames() {
        String sql = "SELECT DISTINCT name FROM kintai ORDER BY name";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    // 編集画面で使用（名前と出勤時間から取得）
    public KintaiEntity findByNameAndStartTime(String name, LocalDateTime startTime) {
        String sql = "SELECT k.id, k.name, k.start_time, k.end_time, e.hourly_wage " +
                     "FROM kintai k LEFT JOIN employee e ON k.name = e.name " +
                     "WHERE k.name = ? AND k.start_time = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{name, startTime}, new KintaiRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // 確認画面で使用（名前と日付から取得）
    public KintaiEntity findByNameAndDate(String name, LocalDate date) {
        String sql = "SELECT k.id, k.name, k.start_time, k.end_time, e.hourly_wage " +
                     "FROM kintai k LEFT JOIN employee e ON k.name = e.name " +
                     "WHERE k.name = ? AND FORMATDATETIME(k.start_time, 'yyyy-MM-dd') = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{name, date.toString()}, new KintaiRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // 更新処理（idで指定して1件だけ更新）
    public void update(KintaiEntity kintai) {
        String sql = "UPDATE kintai SET start_time = ?, end_time = ? WHERE id = ?";
        jdbcTemplate.update(sql,
            kintai.getStartTime(),
            kintai.getEndTime(),
            kintai.getId()
        );
    }

    // 時給データを取得する（個別に必要なら）
    public Integer findHourlyWageByName(String name) {
        String sql = "SELECT hourly_wage FROM employee WHERE name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{name}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
