package com.gus.edificialIntelligence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import us.gusworks.gus.edificialIntelligence.listeners.CornerListener;
import us.gusworks.gus.edificialIntelligence.listeners.DebugSetCreator;
import us.gusworks.gus.edificialIntelligence.listeners.EIListener;
import us.gusworks.gus.edificialIntelligence.listeners.OneDimensionalSpaceListener;
import us.gusworks.gus.edificialIntelligence.listeners.TwoDimensionalSpaceListener;
import us.gusworks.gus.edificialIntelligence.spaces.OneDimensionalSpace;

import com.gus.edificialIntelligence.mysql.MysqlPlayerManager;
import com.gus.edificialIntelligence.mysql.MysqlRawManager;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.Statement;

public class EdificialIntelligencePlugin extends JavaPlugin{
	
	static public PluginLogger log;
	EIListener listener;
	public ConnectionManager cmgr;
	//playermanager logs those who join, puts them into the database of players, assigns them an id, and builds an online players hashmap
	public MysqlPlayerManager mysqlPlayerManager;
	//rawmanager logs all blocks placed
	public MysqlRawManager mysqlRawManager;
	public Statement stmt;
	public CornerListener cornerListener;
	private OneDimensionalSpaceListener oneDimensionalSpaceListener; 
	private DebugSetCreator dsc;

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		super.onEnable();
		
		log = new PluginLogger(this);
		listener = new EIListener(this);
		new ConfigManager(this);
		cmgr = new ConnectionManager(this);
		mysqlPlayerManager = new MysqlPlayerManager(this);
		mysqlRawManager = new MysqlRawManager(this);
		stmt = null;
		cornerListener = new CornerListener(this);
		oneDimensionalSpaceListener = new OneDimensionalSpaceListener(this);
		dsc = new DebugSetCreator();
		
		
		
		
		this.getServer().getPluginManager().registerEvents(listener, this);
		//this.getServer().getPluginManager().registerEvents(cornerListener, this);
		//this.getServer().getPluginManager().registerEvents(mysqlRawManager, this);
		//this.getServer().getPluginManager().registerEvents(mysqlPlayerManager, this);
		//this.getServer().getPluginManager().registerEvents(space2DListener, this);
		this.getServer().getPluginManager().registerEvents(oneDimensionalSpaceListener, this);
		this.getServer().getPluginManager().registerEvents(dsc, this);
		
		//this may not be a good idea
		//this.getServer().getScheduler().scheduleAsyncDelayedTask(this, new ConnectionManager(this));
		
		
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		// TODO Auto-generated method stub
		
		if(command.getName().equalsIgnoreCase("visualize")) TwoDimensionalSpaceListener.visualize();
		
		
		return super.onCommand(sender, command, label, args);
	}
	

}
