package com.springboot.app.jdbc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.User;

@Repository
public class NamedParameterJdbcUserRepository extends JdbcUserRepository {
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
    public int update(User user) {
        return namedParameterJdbcTemplate.update(
                "update useraccess set username = :username, useremail = :useremail, userpassword = :userpassword, userphone = :userphone, jenisuser = :jenisuser where userid = :userid",
                new BeanPropertySqlParameterSource(user));
    }
	
	@Override
    public Optional<User> findById(int userid) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from useraccess where userid = :userid",
                new MapSqlParameterSource("userid", userid),
                (rs, rowNum) ->
                        Optional.of(new User(
                        		rs.getInt("userid"),
                        		rs.getString("username"),
                        		rs.getString("useremail"),
                        		rs.getString("userpassword"),
                        		rs.getString("userphone"),
                        		rs.getString("jenisuser")
                        ))
        );
    }
	
	@Override
    public Optional<User> login(String username, String pw) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("username", username);
		paramSource.addValue("userpassword", pw);
		
        return namedParameterJdbcTemplate.queryForObject(
                "select * from useraccess where username = :username AND userpassword = :userpassword",
                paramSource,
                (rs, rowNum) ->
                        Optional.of(new User(
                        		rs.getInt("userid"),
                        		rs.getString("username"),
                        		rs.getString("useremail"),
                        		rs.getString("userpassword"),
                        		rs.getString("userphone"),
                        		rs.getString("jenisuser")
                        ))
        );
    }
}
