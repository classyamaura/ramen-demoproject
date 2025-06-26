package com.example.demo.kintai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

public class KintaiRowMapper implements RowMapper<KintaiEntity> {
    @Override
    public KintaiEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        KintaiEntity k = new KintaiEntity();
        k.setName(rs.getString("name"));

        LocalDateTime start = rs.getTimestamp("start_time").toLocalDateTime();
        LocalDateTime end = rs.getTimestamp("end_time").toLocalDateTime();

        k.setStartTime(start);
        k.setEndTime(end);

        // 勤務時間の差分を計算（退勤 - 出勤）
        Duration duration = Duration.between(start, end);
        long totalMinutes = duration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;

        // フォーマットして格納
        k.setWorkDurationStr(hours + "時間" + minutes + "分");

        return k;
    }
}