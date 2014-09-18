package com.gus.edificialIntelligence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager implements Runnable {
	
	EdificialIntelligencePlugin plugin;
	public Connection conn;
	
	public ConnectionManager(EdificialIntelligencePlugin plugin){
		
		this.plugin = plugin;
		run();
		
	}
	
	public void run() {
		
		plugin.log.info("Attempting connection...");
		
		conn = null;
		
		try{
			
			//conn = DriverManager.getConnection("jdbc:mysql://localhost/" + ConfigManager.config.getString("mysql.connection.databaseName"), ConfigManager.config.getString("mysql.connection.username"), ConfigManager.config.getString("mysql.connection.password"));
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/ei?autoReconnect=true", "root", "4391");
			
			plugin.log.info("Connected!");
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}
		
	}
}
