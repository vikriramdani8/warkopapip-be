package com.springboot.app.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springboot.app.model.Food;
import com.springboot.app.repository.FoodRepository;

public class JdbcFoodRepository implements FoodRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public int count() {
		 return jdbcTemplate
	                .queryForObject("select count(*) from foods", Integer.class);
	}

	@Override
	public int save(Food food) {
		return jdbcTemplate.update(
                "insert into foods (foodid, foodjenisid, foodname, fooddesc, foodprice, stock, urlimage) values((SELECT MAX(foodid)+1 FROM foods),?,?,?,?,?,?)",
                food.getFoodjenisid(), food.getFoodname(), food.getFooddesc(), food.getFoodprice(), food.getStock(), food.getUrlimage());
	}

	@Override
	public int update(Food food) {
		return jdbcTemplate.update(
                "update foods set foodjenisid = ?, foodname = ?, fooddesc = ?, foodprice = ?, stock = ?, urlimage = ? where foodid = ?",
                food.getFoodjenisid(), food.getFoodname(), food.getFooddesc(), food.getFoodprice(), food.getStock(), food.getFoodid(), food.getUrlimage());
	}

	@Override
	public int deleteById(Long id) {
		return jdbcTemplate.update(
                "delete from foods where foodid = ?", id);
	}

	@Override
	public List<Food> findAll() {
		return jdbcTemplate.query(
                "select * from foods",
                (rs, rowNum) ->
                        new Food(
                        		rs.getInt("foodid"),
                        		rs.getInt("foodjenisid"),
                        		rs.getString("foodname"),
                        		rs.getString("fooddesc"),
                        		rs.getInt("foodprice"),
                        		rs.getInt("stock"),
                        		rs.getString("urlimage")
                        )
        );
	}

	@Override
	public List<Food> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Optional<Food> findById(int id) {
		return jdbcTemplate.queryForObject(
                "select * from foods",
                new Object[]{id},
                (rs, rowNum) ->
                	Optional.of(new Food(
                			rs.getInt("foodid"),
                    		rs.getInt("foodjenisid"),
                    		rs.getString("foodname"),
                    		rs.getString("fooddesc"),
                    		rs.getInt("foodprice"),
                    		rs.getInt("stock"),
                    		rs.getString("urlimage")
                        ))
        );
	}

}
