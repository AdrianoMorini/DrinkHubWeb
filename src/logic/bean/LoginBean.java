package logic.bean;

import logic.model.Address;

public class LoginBean {
	private String username;
	private String password;
	private String name;
	private String surname;
	private String image;
	private Address addr;

	public LoginBean() {
		this.username = "";
		this.password = "";
		this.name = "";
		this.surname = "";
		this.image = "";
		this.setAddr(null);

	}

	public void setUsername(String user) {
		this.username = user;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public String getPassword() {
		return this.password;
	}

	public String getName() {
		return name;
	}

	public void setUName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}
}