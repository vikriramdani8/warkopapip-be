package com.springboot.app.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springboot.app.model.User;
import com.springboot.app.repository.UserRepository;

public class JdbcUserRepository implements UserRepository{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public int count() {
		 return jdbcTemplate
	                .queryForObject("select count(*) from useraccess", Integer.class);
	}
	
//	@Override
//	public int login(String name, String pw) {
//		 return jdbcTemplate
//	                .queryForObject("select * from useraccess where username= ? and password = ?", name, pw, Integer.class);
//	}

	@Override
	public int save(User user) {
		return jdbcTemplate.update(
                "insert into useraccess (userid, username, useremail, userpassword, userphone, jenisuser) values((SELECT MAX(userId)+1 FROM useraccess),?,?,?,?,?)",
                user.getUsername(), user.getUseremail(), user.getUserpassword(), user.getUserphone(), user.getJenisuser());
	}

	@Override
	public int update(User user) {
		return jdbcTemplate.update(
                "update useraccess set username = ?, useremail = ?, userpassword = ?, userphone = ?, jenisuser = ? where userid = ?",
                user.getUsername(), user.getUseremail(), user.getUserpassword(), user.getUserphone(), user.getJenisuser(), user.getUserid());
	}

	@Override
	public int deleteById(Long id) {
		return jdbcTemplate.update(
                "delete from useraccess where userid = ?", id);
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query(
                "select * from useraccess",
                (rs, rowNum) ->
                        new User(
                        		rs.getInt("userid"),
                        		rs.getString("username"),
                        		rs.getString("useremail"),
                        		rs.getString("userpassword"),
                        		rs.getString("userphone"),
                        		rs.getString("jenisuser")
                        )
        );
	}

	@Override
	public List<User> findByName(String name) {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Optional<User> findById(int userid) {
		return jdbcTemplate.queryForObject(
                "select * from useraccess",
                new Object[]{userid},
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
	
	@SuppressWarnings("deprecation")
	@Override
	public Optional<User> login(String un, String pw) {
		return jdbcTemplate.queryForObject(
                "select * from useraccess",
                new Object[]{un, pw},
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
