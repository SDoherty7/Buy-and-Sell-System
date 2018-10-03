package org.wappbean;

public class Item {

	String title;
	String category;
	String description;
	int bid;
	int id;
	String username;
	
	
	public Item(String title, String category, String description, int bid,
			int id, String username) {
		super();
		this.title = title;
		this.category = category;
		this.description = description;
		this.bid = bid;
		this.id = id;
		this.username = username;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
