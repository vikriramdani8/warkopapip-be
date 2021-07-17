package com.springboot.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springboot.app.model.User;
import com.springboot.app.repository.FoodRepository;
import com.springboot.app.repository.OrderRepository;
import com.springboot.app.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SpringbootAppApplication implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(SpringbootAppApplication.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    //@Qualifier("jdbcBookRepository")             
//    @Qualifier("namedParameterJdbcUserRepository") 
//	@Qualifier("namedParameterJdbcOrderRepository") 
	
    private UserRepository userRepository;
	private FoodRepository foodRepository;
	private OrderRepository orderRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		runJdbc();
	}
	
	void runJdbc() {
		log.info("StartApplication...");	
	}
	
	

}
