package com.database;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDatabase {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	private static final String DRIVER ="org.sqlite.JDBC";
	
	public ItemDatabase() {
		try{
			Class.forName(DRIVER).newInstance();
			String connectionString="jdbc:sqlite:items.sqlite";
			connection = DriverManager.getConnection(connectionString);
			
			ensureDatabseTablesExist();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(InstantiationException e){
			e.printStackTrace();
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void ensureDatabseTablesExist() {
		try {
			statement = connection.createStatement();
			String ddlQuery = "CREATE TABLE IF NOT EXISTS" +
							  " items (id INTEGER PRIMARY KEY "+
							  " AUTOINCREMENT, name varchar(50)"+
							  " )";
			boolean wasQuerySucessful = statement.execute(ddlQuery);
			if(wasQuerySucessful) {
				System.out.println("Item table exist");
			}
			else {
				System.out.println("Item table may not exist");
			}				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	List<Item> retrieveAll() {
		List<Item> items = new ArrayList<Item>();
		try{
			String query = "SELECT * FROM items";
			resultSet = statement.executeQuery(query);
			if(resultSet != null) {
				while(resultSet.next()) {
					Item item = new Item();
					item.setId( resultSet.getInt(1) );
//					item.getId( resultSet.getInt(1) );
					item.setName( resultSet.getString(2) );
					items.add(item);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	boolean add(Item item) {
		boolean successful = false;
		try {
			String query = "INSERT INTO items (name) values (?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, item.getName());
			int numberOfAffectedRows = ps.executeUpdate();
			successful = numberOfAffectedRows > 0;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return successful;
	}

}
