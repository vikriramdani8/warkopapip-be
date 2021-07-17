package com.springboot.app.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springboot.app.model.Order;
import com.springboot.app.repository.OrderRepository;

public class JdbcOrderRepository implements OrderRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public int count() {
		return jdbcTemplate
                .queryForObject("select count(*) from orders", Integer.class);
	}

	@Override
	public int save(Order order) {
		return jdbcTemplate.update(
                "insert into orders (orderid, foodid, custid, qty, price, orderdate, status) values((SELECT MAX(orderid)+1 FROM orders),?,?,?,?,?,'Pending')",
                order.getFoodid(), order.getCustid(), order.getQty(), order.getPrice(), order.getOrderdate());
	}

	@Override
	public int update(Order order) {
		return jdbcTemplate.update(
                "update orders set foodid = ?, custid = ?, qty = ?, price = ?, orderdate = ?, status = ? where orderid = ?",
                order.getFoodid(), order.getCustid(), order.getQty(), order.getPrice(), order.getOrderdate(), order.getStatus(), order.getOrderid());
	}

	@Override
	public int deleteById(Long id) {
		return jdbcTemplate.update(
                "delete from orders where orderid = ?", id);
	}

	@Override
	public List<Order> findAll() {
		return jdbcTemplate.query(
                "select * from orders",
                (rs, rowNum) ->
                        new Order(
                        		rs.getInt("orderid"),
                        		rs.getInt("foodid"),
                        		rs.getInt("custid"),
                        		rs.getInt("qty"),
                        		rs.getInt("price"),
                        		rs.getString("orderdate"),
                        		rs.getString("status")
                        )
        );
	}

	@SuppressWarnings("deprecation")
	@Override
	public Optional<Order> findById(int id) {
		return jdbcTemplate.queryForObject(
                "select * from orders",
                new Object[]{id},
                (rs, rowNum) ->
                	Optional.of(new Order(
                			rs.getInt("orderid"),
                    		rs.getInt("foodid"),
                    		rs.getInt("custid"),
                    		rs.getInt("qty"),
                    		rs.getInt("price"),
                    		rs.getString("orderdate"),
                    		rs.getString("status")
                        ))
        );
	}
	
}
