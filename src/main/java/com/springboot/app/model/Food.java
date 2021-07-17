package com.springboot.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "foods")
public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int foodid;
	
	int foodjenisid;
	String foodname;
	String fooddesc;
	int foodprice;
	int stock;
	String urlimage;
	
	public Food(int foodid, int foodjenisid, String foodname, String fooddesc, int foodprice, int stock,
			String urlimage) {
		super();
		this.foodid = foodid;
		this.foodjenisid = foodjenisid;
		this.foodname = foodname;
		this.fooddesc = fooddesc;
		this.foodprice = foodprice;
		this.stock = stock;
		this.urlimage = urlimage;
	}

	public String getUrlimage() {
		return urlimage;
	}

	public void setUrlimage(String urlimage) {
		this.urlimage = urlimage;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getFoodid() {
		return foodid;
	}
	public void setFoodid(int foodid) {
		this.foodid = foodid;
	}
	public int getFoodjenisid() {
		return foodjenisid;
	}
	public void setFoodjenisid(int foodjenisid) {
		this.foodjenisid = foodjenisid;
	}
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	public String getFooddesc() {
		return fooddesc;
	}
	public void setFooddesc(String fooddesc) {
		this.fooddesc = fooddesc;
	}
	public int getFoodprice() {
		return foodprice;
	}
	public void setFoodprice(int foodprice) {
		this.foodprice = foodprice;
	}
}
