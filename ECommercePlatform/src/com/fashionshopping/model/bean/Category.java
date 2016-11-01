package com.fashionshopping.model.bean;

public class Category implements java.io.Serializable{
	
   

	@Override
	public String toString() {
		return "Category [id=" + id + ", type=" + type + ", hot=" + hot
				+ ", aid=" + aid + "]";
	}

	private Integer id;
    private String type;
    private Boolean hot;
    private Integer aid;
    private String login;
 
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Category(){}
    
    public Category(String type,Boolean hot){
    	this.type=type;
    	this.hot=hot;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getHot() {
		return hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}
}
