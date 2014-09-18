package com.gus.edificialIntelligence;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
	
	static FileConfiguration config;
	EdificialIntelligencePlugin plugin;
	
	public ConfigManager(EdificialIntelligencePlugin plugin){
		
		this.plugin = plugin;
		
		config = plugin.getConfig();
		
		//config.options().copyDefaults(true);
		
		//defaults();
		
		plugin.saveConfig();
		
	}

	private void defaults() {
		// TODO Auto-generated method stub
		
		config.addDefault("mysql.connection.databaseName", "databaseName");
		config.addDefault("mysql.connection.username", "username");
		config.addDefault("mysql.connection.password", "password");
		
		plugin.saveConfig();
		
	}
	
}
