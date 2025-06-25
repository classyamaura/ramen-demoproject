package com.example.demo.order;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WeightSensorDao {

    private final JdbcTemplate jdbcTemplate;

    public WeightSensorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(WeightSensorEntity weightSensor) {
        String sql = "INSERT INTO weight_sensors (sensor_name, material_name, registered_weight, threshold_weight) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                weightSensor.getSensorName(),
                weightSensor.getMaterialName(),
                weightSensor.getRegisteredWeight(),
                weightSensor.getThresholdWeight());
    }

    public List<WeightSensorEntity> findAll() {
        String sql = "SELECT sensor_id, sensor_name, material_name, registered_weight, threshold_weight FROM weight_sensors";
        return jdbcTemplate.query(sql, new WeightSensorRowMapper());
    }

    private static class WeightSensorRowMapper implements RowMapper<WeightSensorEntity> {
        @Override
        public WeightSensorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long sensorId = rs.getLong("sensor_id");
            String sensorName = rs.getString("sensor_name");
            String materialName = rs.getString("material_name");
            Integer registeredWeight = rs.getInt("registered_weight");
            Integer thresholdWeight = rs.getInt("threshold_weight");
            return new WeightSensorEntity(sensorId, sensorName, materialName, registeredWeight, thresholdWeight);
        }
    }

    // 必要に応じて、他の取得メソッド（findById, findAllなど）も追加できます
} 