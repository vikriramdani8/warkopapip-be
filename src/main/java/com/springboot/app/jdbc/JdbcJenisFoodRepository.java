package com.springboot.app.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.JenisFood;
import com.springboot.app.repository.JenisFoodRepository;

@Repository
public class JdbcJenisFoodRepository implements JenisFoodRepository {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public int count() {
		return jdbcTemplate
                .queryForObject("select count(*) from jenisfoods", Integer.class);
	}

	@Override
	public int save(JenisFood jenisFood) {
		return jdbcTemplate.update(
                "insert into jenisfoods VALUES((SELECT MAX(jenisfoodid)+1 FROM jenisfoods), ?)",
                jenisFood.getJenisfoodname());
	}

	@Override
	public List<JenisFood> findAll() {
		return jdbcTemplate.query(
                "select * from jenisfoods",
                (rs, rowNum) ->
                        new JenisFood(
                        		rs.getInt("jenisfoodid"),
                        		rs.getString("jenisfoodname")
                        )
        );
	}
}
