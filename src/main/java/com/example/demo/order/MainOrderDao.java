package com.example.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class MainOrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Long insert(MainOrderEntity mainOrder) {
        final String sql = "INSERT INTO mainorders (store_name, person_in_charge, order_date, remarks) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, mainOrder.getStoreName());
            ps.setString(2, mainOrder.getPersonInCharge());
            ps.setDate(3, mainOrder.getOrderDate());
            ps.setString(4, mainOrder.getRemarks());
            return ps;
        }, keyHolder);

        return keyHolder.getKey() != null ? keyHolder.getKey().longValue() : null;
    }

    public List<MainOrderEntity> findAll() {
        String sql = "SELECT order_id, store_name, person_in_charge, order_date, remarks FROM mainorders";
        return jdbcTemplate.query(sql, new MainOrderRowMapper());
    }

    private static class MainOrderRowMapper implements RowMapper<MainOrderEntity> {
        @Override
        public MainOrderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            MainOrderEntity mainOrder = new MainOrderEntity();
            mainOrder.setOrderId(rs.getLong("order_id"));
            mainOrder.setStoreName(rs.getString("store_name"));
            mainOrder.setPersonInCharge(rs.getString("person_in_charge"));
            mainOrder.setOrderDate(rs.getDate("order_date"));
            mainOrder.setRemarks(rs.getString("remarks"));
            return mainOrder;
        }
    }
} 