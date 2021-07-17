package com.springboot.app.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Order;

@Repository
public class NamedParameterJdbcOrderRepository extends JdbcOrderRepository {
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
    public int update(Order order) {
        return namedParameterJdbcTemplate.update(
                "update orders set foodid = :foodid, custid = :custid, qty= :qty, price= :price, orderdate = :orderdate, status = :status where orderid = :orderid",
                new BeanPropertySqlParameterSource(order));
    }
}
