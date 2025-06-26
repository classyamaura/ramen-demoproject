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

    public List<KintaiEntity> search(String name, LocalDate date, String yearMonth) {
        StringBuilder sql = new StringBuilder("SELECT name, start_time, end_time FROM kintai WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            sql.append(" AND name = ? ");
            params.add(name);
        }

        if (date != null) {
        	sql.append(" AND TO_CHAR(start_time, 'YYYY-MM-DD') = ? ");
            params.add(date.toString());
        }
        else if (yearMonth != null && !yearMonth.isEmpty()) {
        	sql.append(" AND TO_CHAR(start_time, 'YYYY-MM') = ? ");
            params.add(yearMonth);
        }

        sql.append(" ORDER BY start_time");
        return jdbcTemplate.query(sql.toString(), params.toArray(), new KintaiRowMapper());
    }
    
    //名前検索
    public List<String> findAllNames() {
        String sql = "SELECT DISTINCT name FROM kintai ORDER BY name";
        return jdbcTemplate.queryForList(sql, String.class);
    }
    
    //勤怠情報を選択すると編集に入る
    public KintaiEntity findByNameAndStartTime(String name, LocalDateTime startTime) {
        String sql = "SELECT name, start_time, end_time FROM kintai WHERE name = ? AND start_time = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{name, startTime}, new KintaiRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
