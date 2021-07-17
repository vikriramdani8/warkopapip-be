package com.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.app.model.JenisFood;
import com.springboot.app.model.Response;
import com.springboot.app.repository.JenisFoodRepository;

@ResponseBody
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/jenisfoods")
public class JenisFoodController {
	@Autowired
	private JenisFoodRepository jenisFoodRepository;	
	ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping("/get")
	public List<JenisFood> JenisFood() {
		List<JenisFood> food = jenisFoodRepository.findAll();
	    return food;
	}
	
	@PostMapping(
			value = "/add",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Response add(@RequestBody JenisFood jenisFood) {		
		int row = jenisFoodRepository.save(jenisFood);
		return new Response("00", "Input data berhasil");
	}
}
