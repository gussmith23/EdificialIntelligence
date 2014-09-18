package us.gusworks.gus.edificialIntelligence.listeners;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gus.edificialIntelligence.EdificialIntelligencePlugin;

public class DebugSetCreator implements Listener {
	
	private ArrayList<Material> uncheckedMaterials = new ArrayList<Material>();
	
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	
	public DebugSetCreator(){
			
		uncheckedMaterials.add(Material.AIR); uncheckedMaterials.add(Material.TORCH);
		
	}
	
	@EventHandler
	void onBlockPlace(BlockPlaceEvent event){
				
		if(!event.getPlayer().getName().equalsIgnoreCase("gus23")) return;
		if(uncheckedMaterials.contains(event.getBlock().getType())) return;
		
		blocks.add(event.getBlock());
		
		
	}

}
