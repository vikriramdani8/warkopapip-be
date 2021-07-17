package com.springboot.app.controller;

import java.util.List;

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
import com.springboot.app.model.Order;
import com.springboot.app.model.Response;
import com.springboot.app.repository.OrderRepository;

@ResponseBody
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;	
	ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping("/get")
	public List<Order> Order() {
		List<Order> order = orderRepository.findAll();
	    return order;
	}
	
	@PostMapping(
			value = "/add",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Response add(@RequestBody Order order) {
		int row = orderRepository.save(order);
		return new Response("00", "Input data berhasil");
	}
	
	@DeleteMapping("/delete")
	public Response detail(@RequestParam(value = "id", defaultValue = "0") String id) {
		if (id != "0") {
			int row = orderRepository.deleteById(Long.valueOf(id));
			if (row > 0) {
				return new Response("00", "Delete Berhasil");
			} else {
				return new Response("X06", "Delete gagal");
			}
		}
		
		return new Response("X06", "Params Kosong");
	}
	
	@PutMapping("/update")
	public Response update(@RequestParam(value = "id", defaultValue = "0") String id, @RequestBody Order order) {
		if (id != "0") {
			order.setOrderid(Integer.valueOf(id));
			int row = orderRepository.update(order);
			if (row > 0) {
				return new Response("00", "Update Berhasil");
			} else {
				return new Response("X06", "update gagal");
			}
		}
		
		return new Response("X06", "Params Kosong");
	}
}
