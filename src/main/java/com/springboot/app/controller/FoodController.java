package com.springboot.app.controller;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
import com.springboot.app.model.Food;
import com.springboot.app.repository.FoodRepository;

@ResponseBody
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/foods")
public class FoodController {
	
	@Autowired
	private FoodRepository foodRepository;	
	ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping("/get")
	public List<Food> Food() {
		List<Food> food = foodRepository.findAll();
	    return food;
	}
	
	@PostMapping(
			value = "/add",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Response add(@RequestBody Food food) {
		System.out.println(food.getFoodid());		
		int row = foodRepository.save(food);
		return new Response("00", "Input data berhasil");
	}
	
	@DeleteMapping("/delete")
	public Response detail(@RequestParam(value = "id", defaultValue = "0") String id) {
		if (id != "0") {
			int row = foodRepository.deleteById(Long.valueOf(id));
			if (row > 0) {
				return new Response("00", "Delete Berhasil");
			} else {
				return new Response("X06", "Delete gagal");
			}
		}
		
		return new Response("X06", "Params Kosong");
	}
	
	@PutMapping("/update")
	public Response update(@RequestParam(value = "id", defaultValue = "0") String id, @RequestBody Food food) {
		if (id != "0") {
			food.setFoodid(Integer.valueOf(id));
			int row = foodRepository.update(food);
			if (row > 0) {
				return new Response("00", "Update Berhasil");
			} else {
				return new Response("X06", "update gagal");
			}
		}
		
		return new Response("X06", "Params Kosong");
	}
}	
