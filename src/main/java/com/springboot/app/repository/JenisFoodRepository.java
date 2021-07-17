package com.springboot.app.repository;

import java.util.List;
import java.util.Optional;

import com.springboot.app.model.Food;
import com.springboot.app.model.JenisFood;

public interface JenisFoodRepository {
	int count();
    int save(JenisFood jenisFood);
    List<JenisFood> findAll();
}
