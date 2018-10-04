package com.database;

public class Item {
	private int id;
	private String name;
	
	public Item(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Item() {
		this.id = 0;
		this.name = "";
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}

//	public void setId(int int1) {
//		// TODO Auto-generated method stub
//		
//	}

}
