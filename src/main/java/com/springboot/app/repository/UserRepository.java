package com.springboot.app.repository;

import java.util.List;
import java.util.Optional;

import com.springboot.app.model.User;

public interface UserRepository{
	int count();
    int save(User user);
    int update(User user);
    int deleteById(Long id);
    List<User> findAll();
    List<User> findByName(String name);
    Optional<User> findById(int id);
    Optional<User> login(String un, String pw);
}
