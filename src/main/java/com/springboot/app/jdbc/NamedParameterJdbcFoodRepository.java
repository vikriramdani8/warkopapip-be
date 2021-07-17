package com.springboot.app.jdbc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Food;

@Repository
public class NamedParameterJdbcFoodRepository extends JdbcFoodRepository{
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
    public int update(Food food) {
        return namedParameterJdbcTemplate.update(
                "update foods set foodjenisid = :foodjenisid, foodname = :foodname, fooddesc = :fooddesc, foodprice = :foodprice, stock = :stock, urlimage = :urlimage where foodid = :foodid",
                new BeanPropertySqlParameterSource(food));
    }
	
	@Override
    public Optional<Food> findById(int id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from foods where foodid = :foodid",
                new MapSqlParameterSource("foodid", id),
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
