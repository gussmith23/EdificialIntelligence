package com.gus.edificialIntelligence.mysql;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


import com.gus.edificialIntelligence.EdificialIntelligencePlugin;

public class MysqlPlayerManager implements Listener{
	
	//players online right now
	public static HashMap<String, Integer> onlinePlayers = new HashMap<String, Integer>();
	
	private EdificialIntelligencePlugin plugin;
	
	//used for updates
	Statement updateStatement;

	public MysqlPlayerManager(EdificialIntelligencePlugin plugin){
		
		this.plugin = plugin;
				
		//create table if it doesn't exist
		DatabaseMetaData meta = null;
		try{
			
			updateStatement = plugin.cmgr.conn.createStatement();

			Statement stmt = (Statement) plugin.cmgr.conn.createStatement();
			meta = (DatabaseMetaData) plugin.cmgr.conn.getMetaData();
			ResultSet res = meta.getTables(null, null, "players", null);
			if(!res.first()){
				stmt.execute("CREATE TABLE IF NOT EXISTS players(id int NOT NULL, username varchar(16) NOT NULL)");
				plugin.log.info("Players table created.");
			}else plugin.log.info("Players table found.");
			
			//the following lines add players already connected to the server
			Player[] players = plugin.getServer().getOnlinePlayers();
			
			//if server not empty
			if(players.length != 0){
				
				ResultSet rs = (ResultSet) stmt.executeQuery("SELECT username, id FROM players");
				
				//if no players have been added to the db at all
				if(!rs.first()){
					int rep = 0;
					for(Player p:players){
						
						stmt.executeUpdate("INSERT INTO players SET id = " + rep +" , username = '" + p.getName() + "'");
						onlinePlayers.put(p.getName(), rep);
						rep++;
					}
				}
				
				//add them in
				for(Player p:players){
					
					plugin.log.info("1");
					
					//getting the next id to be used
					//this is messy as fuck, sql wise
					Statement idFindStatement = plugin.cmgr.conn.createStatement();
					ResultSet idFind = (ResultSet) idFindStatement.executeQuery("SELECT id FROM players ORDER BY id DESC");
					idFind.next();
					int id = idFind.getInt(1) + 1;
					
					while(rs.next()){
						//if the player's in
						if(rs.getString(1).equalsIgnoreCase(p.getName())) return;
						plugin.log.info("groan shark");

						
					}
					//this will be true if the thing returned before the end of the line was reached
					if(!rs.next()){
						updateStatement.executeUpdate("INSERT INTO players SET id = " + id + " , username = '" + p.getName() + "'");
						onlinePlayers.put(p.getName(), id);
						plugin.log.info("2");

					}else{//otherwise, if the while loop exited early
						
						//just add them to the online players and be done
						MysqlPlayerManager.onlinePlayers.put(p.getName(), rs.getInt(2));
						plugin.log.info("3");

					}
					
					rs.first();
				}
			}
			stmt.close();
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent event){

		
		ResultSet rs;
		
		try {
			Statement stmt = (Statement) plugin.cmgr.conn.createStatement();
			rs = (ResultSet) stmt.executeQuery("SELECT username, id FROM players");
			boolean exists = false;
			while(rs.next()){
				
				
				//if the player's already there
				if(rs.getString(1).equalsIgnoreCase(event.getPlayer().getName())) {
					exists = true;
					onlinePlayers.put(event.getPlayer().getName(), rs.getInt(2));
					return;
				}
				
			}
			
			//true if no one is in the list yet
			if(!rs.first()){
	
				stmt.executeUpdate("INSERT INTO players SET id = '0' , username = '" + event.getPlayer().getName() + "'");
				onlinePlayers.put(event.getPlayer().getName(), 0);
			}
			
			
			//what happens if the player's not there
			if(exists==false){
				
				rs = (ResultSet) stmt.executeQuery("SELECT id FROM players ORDER BY id DESC");
				rs.next();
				int id = rs.getInt(1) + 1;
				
				stmt.executeUpdate("INSERT INTO players SET id = '" + id + "' , username = '" + event.getPlayer().getName() + "'"); 
				onlinePlayers.put(event.getPlayer().getName(), id);
				
			}
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@EventHandler
	public void playerLeave(PlayerQuitEvent event){
		//remove the player from the online hashmap
		onlinePlayers.remove(event.getPlayer().getName());
	}
	
}
