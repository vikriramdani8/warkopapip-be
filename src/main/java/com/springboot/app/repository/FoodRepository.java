package com.springboot.app.repository;

import java.util.List;
import java.util.Optional;

import com.springboot.app.model.Food;

public interface FoodRepository {
	int count();
    int save(Food food);
    int update(Food food);
    int deleteById(Long id);
    List<Food> findAll();
    List<Food> findByName(String name);
    Optional<Food> findById(int id);
}
