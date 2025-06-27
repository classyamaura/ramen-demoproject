package com.example.demo.order;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public OrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderEntity> findAll() {
        String sql = "SELECT o.id, o.name, o.weight, ws.registered_weight AS registered_weight_from_sensor, ws.threshold_weight " +
                     "FROM orders o LEFT JOIN weight_sensors ws ON o.name = ws.material_name";
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }

    public void updateWeight(Long id, Integer weight) {
        String sql = "UPDATE orders SET weight = ? WHERE id = ?";
        jdbcTemplate.update(sql, weight, id);
    }

    private static class OrderRowMapper implements RowMapper<OrderEntity> {
        @Override
        public OrderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            Integer weight = rs.getInt("weight");
            Integer registeredWeightFromSensor = rs.getObject("registered_weight_from_sensor", Integer.class);
            Integer thresholdWeight = rs.getObject("threshold_weight", Integer.class);

            Double calculatedUnit = null;
            if (registeredWeightFromSensor != null && registeredWeightFromSensor > 0) {
                calculatedUnit = (double) weight / registeredWeightFromSensor;
            }

            String statusSymbol = "";
            if (weight == 0) {
                statusSymbol = "×";
            } else if (thresholdWeight != null && thresholdWeight != 0) {
                if (weight >= thresholdWeight * 2) {
                    statusSymbol = "◎";
                } else if (weight >= thresholdWeight) {
                    statusSymbol = "〇";
                } else {
                    statusSymbol = "△";
                }
            } else {
                statusSymbol = "-";
            }

            int requiredOrderQuantity = 0;
            if (thresholdWeight != null && weight != null && registeredWeightFromSensor != null && registeredWeightFromSensor > 0) {
                double neededWeight = thresholdWeight - weight;
                if (neededWeight > 0) {
                    requiredOrderQuantity = (int) Math.ceil(neededWeight / registeredWeightFromSensor);
                }
            }
            
            OrderEntity order = new OrderEntity(id, name, weight, registeredWeightFromSensor, calculatedUnit, statusSymbol);
            order.setThresholdWeight(thresholdWeight != null ? thresholdWeight : 0);
            order.setRequiredOrderQuantity(requiredOrderQuantity);
            return order;
        }
    }
}
