package com.springboot.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "useraccess")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	private String username;
	private String useremail;
	private String userpassword;
	private String userphone;
	private String jenisuser;
	
	public User(int userid, String username, String useremail, String userpassword, String userphone,
			String jenisuser) {
		super();
		this.userid = userid;
		this.username = username;
		this.useremail = useremail;
		this.userpassword = userpassword;
		this.userphone = userphone;
		this.jenisuser = jenisuser;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserId(int userid) {
		this.userid = userid;
	}
	public String getJenisuser() {
		return jenisuser;
	}
	public void setJenisuser(String jenisuser) {
		this.jenisuser = jenisuser;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userid + ", username=" + username + ", useremail=" + useremail + ", userpassword="
				+ userpassword + ", userphone=" + userphone + ", jenisuser=" + jenisuser + "]";
	}
}
