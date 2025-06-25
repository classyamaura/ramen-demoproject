package com.example.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Repository
public class OrderDetailsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(OrderDetailsEntity orderDetails) {
        String sql = "INSERT INTO order_details (order_id, item_name, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, orderDetails.getOrderId(), orderDetails.getItemName(), orderDetails.getQuantity());
    }

    public List<OrderDetailsEntity> findByOrderId(Long orderId) {
        String sql = "SELECT order_detail_id, order_id, item_name, quantity FROM order_details WHERE order_id = ?";
        return jdbcTemplate.query(sql, new OrderDetailsRowMapper(), orderId);
    }

    public List<Map<String, Object>> findTotalQuantitiesByItem() {
        String sql = "SELECT item_name, SUM(quantity) as total_quantity FROM order_details GROUP BY item_name";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, Object> item = new HashMap<>();
            item.put("itemName", rs.getString("item_name"));
            item.put("totalQuantity", rs.getInt("total_quantity"));
            return item;
        });
    }

    private static class OrderDetailsRowMapper implements RowMapper<OrderDetailsEntity> {
        @Override
        public OrderDetailsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDetailsEntity orderDetails = new OrderDetailsEntity();
            orderDetails.setOrderDetailId(rs.getLong("order_detail_id"));
            orderDetails.setOrderId(rs.getLong("order_id"));
            orderDetails.setItemName(rs.getString("item_name"));
            orderDetails.setQuantity(rs.getInt("quantity"));
            return orderDetails;
        }
    }
} 