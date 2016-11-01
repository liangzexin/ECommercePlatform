package com.fashionshopping.model.bean;

public class EchoAccount {
	
	private Integer id;
    private String login;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", login=" + login + "]";
	}
}
