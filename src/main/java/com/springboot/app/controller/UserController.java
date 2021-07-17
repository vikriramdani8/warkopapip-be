package com.springboot.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.app.model.Response;
import com.springboot.app.model.User;
import com.springboot.app.repository.UserRepository;

@ResponseBody
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;	
	ObjectMapper mapper = new ObjectMapper();

	@GetMapping("/get")
	public List<User> User() {
		List<User> user = userRepository.findAll();
	    return user;
	}
	
	@GetMapping("/detail")
	public Optional<User> Detail(@RequestParam(value = "id", defaultValue = "0") String id) {
		Optional<User> user = userRepository.findById(Integer.valueOf(id));
		return user;
	}
	
	@PostMapping(
			value = "/login",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Optional<User> Detail(@RequestBody User user) {
		Optional<User> users = userRepository.login(user.getUsername(), user.getUserpassword());
		return users;
	}
	
	@PostMapping(
			value = "/add",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Response add(@RequestBody User user) {
		System.out.println(user.getUserid());		
		int row = userRepository.save(user);
		System.out.println(row);
		
		return new Response("00", "Input data berhasil");
	}
	
	@DeleteMapping("/delete")
	public Response detail(@RequestParam(value = "id", defaultValue = "0") String id) {
		if (id != "0") {
			int row = userRepository.deleteById(Long.valueOf(id));
			if (row > 0) {
				return new Response("00", "Delete Berhasil");
			} else {
				return new Response("X06", "Delete gagal");
			}
		}
		
		return new Response("X06", "Params Kosong");
	}
	
	@PutMapping("/update")
	public Response update(@RequestParam(value = "id", defaultValue = "0") String id, @RequestBody User user) {
		if (id != "0") {
			user.setUserId(Integer.valueOf(id));
			System.out.println(user.getUserid());
			System.out.println(user.getUseremail());
			System.out.println(user.getUsername());
			System.out.println(user.getUserpassword());
			System.out.println(user.getUserphone());
			System.out.println(user.getJenisuser());
			
			int row = userRepository.update(user);
			if (row > 0) {
				return new Response("00", "Update Berhasil");
			} else {
				return new Response("X06", "update gagal");
			}
		}
		
		return new Response("X06", "Params Kosong");
	}
}
