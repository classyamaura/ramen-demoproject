package com.example.demo.kintai;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class KintaiRowMapper implements RowMapper<KintaiEntity> {
    @Override
    public KintaiEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        KintaiEntity k = new KintaiEntity();
        k.setName(rs.getString("name"));
        k.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
        k.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
        k.setWorkTime(rs.getString("work_time")); // INTERVALをStringとして扱う
        return k;
    }
}
