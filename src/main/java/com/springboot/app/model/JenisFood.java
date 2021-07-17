package com.springboot.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jenisfoods")
public class JenisFood {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int jenisfoodid;
	
	String jenisfoodname;

	public JenisFood(int jenisfoodid, String jenisfoodname) {
		super();
		this.jenisfoodid = jenisfoodid;
		this.jenisfoodname = jenisfoodname;
	}

	public int getJenisfoodid() {
		return jenisfoodid;
	}

	public void setJenisfoodid(int jenisfoodid) {
		this.jenisfoodid = jenisfoodid;
	}

	public String getJenisfoodname() {
		return jenisfoodname;
	}

	public void setJenisfoodname(String jenisfoodname) {
		this.jenisfoodname = jenisfoodname;
	}
	
	
}
