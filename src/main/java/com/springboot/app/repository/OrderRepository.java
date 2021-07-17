package com.springboot.app.repository;

import java.util.List;
import java.util.Optional;

import com.springboot.app.model.Order;

public interface OrderRepository {
	int count();
    int save(Order order);
    int update(Order order);
    int deleteById(Long id);
    List<Order> findAll();
    Optional<Order> findById(int id);
}
