package us.gusworks.gus.edificialIntelligence.listeners;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gus.edificialIntelligence.EdificialIntelligencePlugin;

public class CornerListener implements Listener {
	
	EdificialIntelligencePlugin plugin;
	
	public CornerListener(EdificialIntelligencePlugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		
		Block orig = event.getBlock();
		boolean u = orig.getRelative(BlockFace.UP).getType().equals(Material.AIR);
		boolean n = orig.getRelative(BlockFace.NORTH).getType().equals(Material.AIR);
		boolean s = orig.getRelative(BlockFace.SOUTH).getType().equals(Material.AIR);
		boolean e = orig.getRelative(BlockFace.EAST).getType().equals(Material.AIR);
		boolean w = orig.getRelative(BlockFace.WEST).getType().equals(Material.AIR);
		boolean d = orig.getRelative(BlockFace.DOWN).getType().equals(Material.AIR);
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		//quadrant 1
		if(!u&&!e&&!n) list.add(1);
		
		//quadrant 2
		if(!u&&!w&&!n) list.add(2);
		
		//quadrant 3
		if(!u&&!w&&!s) list.add(3);
		
		//quadrant 4
		if(!u&&!e&&!s) list.add(4);
		
		//quadrant 5
		if(!d&&!e&&!n) list.add(5);
		
		//quadrant 6
		if(!d&&!w&&!n) list.add(6);
		
		//quadrant 7
		if(!d&&!w&&!s) list.add(7);
		
		//quadrant 8
		if(!d&&!e&&!s) list.add(8);
		
		
		if(!list.isEmpty()){
			plugin.log.info("Octants: " + list.toString() + ". (" + event.getPlayer().getName() + ")");
		}
		
		
		
		
		
	}
	
}
