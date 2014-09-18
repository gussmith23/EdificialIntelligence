package com.gus.edificialIntelligence.mysql;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gus.edificialIntelligence.EdificialIntelligencePlugin;

public class MysqlRawManager implements Listener{
	
	EdificialIntelligencePlugin plugin;
	
	public MysqlRawManager(EdificialIntelligencePlugin plugin){
		
		this.plugin = plugin;
		
		//create table if it doesn't exist
		DatabaseMetaData meta = null;
		try {
			
			Statement stmt = (Statement) plugin.cmgr.conn.createStatement();
			meta = (DatabaseMetaData) plugin.cmgr.conn.getMetaData();
			ResultSet res = meta.getTables(null, null, "raw", null);
			if(!res.first()){
				stmt.execute("CREATE TABLE IF NOT EXISTS raw(time bigint NOT NULL, playerid int NOT NULL, action bit NOT NULL, x int NOT NULL, y int NOT NULL, z int NOT NULL)");
				stmt.close();
				plugin.log.info("Raw data table created.");
			}else plugin.log.info("Raw data table found.");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@EventHandler
	public void playerPlacedBlock(BlockPlaceEvent event){
		
		//time bigint NOT NULL, playerid int NOT NULL, x int NOT NULL, y int NOT NULL, z int NOT NULL
		
		Statement stmt;
		Date date = new Date();
		int playerid = MysqlPlayerManager.onlinePlayers.get(event.getPlayer().getName());
		Block b = event.getBlock();
		
		try {
			stmt = (Statement) plugin.cmgr.conn.createStatement();
			stmt.executeUpdate("INSERT INTO raw SET time = '" + 
					date.getTime() +
					"', playerid = '" +
					playerid +
					"', action = '1', " +
					"x = '" +
					b.getX() +
					"', y = '" +
					b.getY() +
					"', z = '" +
					b.getZ() + "'");
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@EventHandler
	public void playerBrokeBlock(BlockBreakEvent event){
		
		Statement stmt;
		Date date = new Date();
		int playerid = MysqlPlayerManager.onlinePlayers.get(event.getPlayer().getName());
		Block b = event.getBlock();
		
		try {
			stmt = (Statement) plugin.cmgr.conn.createStatement();
			stmt.executeUpdate("INSERT INTO raw SET time = '" + 
					date.getTime() +
					"', playerid = '" +
					playerid +
					"', action = '0', " +
					"x = '" +
					b.getX() +
					"', y = '" +
					b.getY() +
					"', z = '" +
					b.getZ() + "'");
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
