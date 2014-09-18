package us.gusworks.gus.edificialIntelligence.listeners;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import us.gusworks.gus.edificialIntelligence.spaces.OneDimensionalSpace;

import com.gus.edificialIntelligence.EdificialIntelligencePlugin;

public class OneDimensionalSpaceListener implements Listener {

	EdificialIntelligencePlugin plugin;
	final int DISTANCE = 20;
	
	public OneDimensionalSpaceListener(EdificialIntelligencePlugin plugin){
		
		this.plugin=plugin;
		
	}
	
	@EventHandler
	void check(BlockPlaceEvent event){
		
		if(!event.getPlayer().getName().equalsIgnoreCase("gus23")) return;
		
		
		boolean found = false;
		BlockFace[] faces = {BlockFace.NORTH,BlockFace.SOUTH,BlockFace.EAST,BlockFace.WEST,BlockFace.UP,BlockFace.DOWN};
		
		for(BlockFace face:faces){
			
			found = false;
			
		
			for(int i = 2;(i<=DISTANCE)&&(!found);i++){
				
				
				//if it's something other than air, and DEBUG if the set of placed blocks contains it
				if((!event.getBlock().getRelative(face, i).getType().equals(Material.AIR)&&DebugSetCreator.blocks.contains(event.getBlock().getRelative(face, i)))){
					
					EdificialIntelligencePlugin.log.info("rhona");
					
					new OneDimensionalSpace(event.getBlock(), event.getBlock().getRelative(face, i));
					found=true;
					
				}
				
				
			}
			
		}
		
	}
	
}
