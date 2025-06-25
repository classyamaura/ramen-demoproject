package com.example.demo.kintai;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KintaiDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<KintaiEntity> search(String name, LocalDate date, String yearMonth) {
        StringBuilder sql = new StringBuilder("SELECT name, start_time, end_time, work_time FROM kintai WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            sql.append(" AND name = ? ");
            params.add(name);
        }

        if (date != null) {
            sql.append(" AND CAST(start_time AS DATE) = ? ");
            params.add(Date.valueOf(date));
        } else if (yearMonth != null && !yearMonth.isEmpty()) {
            sql.append(" AND FORMATDATETIME(start_time, 'yyyy-MM') = ? ");
            params.add(yearMonth);
        }

        sql.append(" ORDER BY start_time");
        return jdbcTemplate.query(sql.toString(), params.toArray(), new KintaiRowMapper());
    }
}
