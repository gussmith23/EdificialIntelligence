package us.gusworks.gus.edificialIntelligence.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gus.edificialIntelligence.EdificialIntelligencePlugin;

public class EIListener implements Listener{

	EdificialIntelligencePlugin plugin;
	
	public EIListener(EdificialIntelligencePlugin plugin) {
		// TODO Auto-generated constructor stub
		
		this.plugin = plugin; 
		
	}

	@EventHandler(priority=EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent event){
		
	}
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event){
		
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event){
		
		plugin.mysqlPlayerManager.playerJoin(event);
		
		
	}
	
	
}
