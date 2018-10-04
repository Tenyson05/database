package com.database;

import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int randNum =  (int)Math.floor (Math.random()*100);
		ItemDatabase db = new ItemDatabase();
		Item testItem = new Item(0, "Test Item " +randNum);
		
		if(db.add(testItem)) {
			System.out.println("Item added successfully : " + testItem );
		}
		List<Item> items = db.retrieveAll();
		System.out.println("Retrieved "+items.size()+" items");
		System.out.println("Showing items");
		for(Item item : items) {
			System.out.println(item);
		}
 
	}

}
