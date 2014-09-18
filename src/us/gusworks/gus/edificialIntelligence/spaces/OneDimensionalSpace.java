package us.gusworks.gus.edificialIntelligence.spaces;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import us.gusworks.gus.edificialIntelligence.listeners.TwoDimensionalSpaceListener;

import com.gus.edificialIntelligence.EdificialIntelligencePlugin;

public class OneDimensionalSpace {
	
	static final int X = 0;
	static final int Y = 1;
	static final int Z = 2;
	//the one dimensional spaces separated into 3 arraylists by their orientation
	public static Object[] oneDimensionalSpaces= {new ArrayList<OneDimensionalSpace>(),new ArrayList<OneDimensionalSpace>(),new ArrayList<OneDimensionalSpace>()};
	
	public ArrayList<Block> blocks = new ArrayList<Block>();
	public Vector unit;
	public int orientation;

	public OneDimensionalSpace(Block b1, Block b2){
				
		unit = b2.getLocation().subtract(b1.getLocation()).toVector().normalize();
		
		//this could be written better
		if(unit.getBlockX()!=0)orientation=X;else if(unit.getBlockY()!=0)orientation=Y;else orientation=Z;
		
		Location loc1 = b1.getLocation();
		Location loc2 = b2.getLocation();
		Location locUnit = unit.toLocation(b1.getWorld());
		World world = b1.getWorld();
		
		for(Location loc = new Location(world, loc1.getX() + locUnit.getX(), loc1.getY() + locUnit.getY(), loc1.getZ() + locUnit.getZ()); (!loc.toVector().equals(loc2.toVector())); loc.add(locUnit)){
			
			blocks.add(world.getBlockAt(loc));
			
		}
				
		EdificialIntelligencePlugin.log.info("New one dimensional space length " + getLength() + " orientation " + orientation + ".");
		
		((ArrayList<OneDimensionalSpace>) oneDimensionalSpaces[orientation]).add(this);
		
		EdificialIntelligencePlugin.log.info("size: " + ((ArrayList<OneDimensionalSpace>) oneDimensionalSpaces[orientation]).size());
		
		TwoDimensionalSpaceListener.update();
		
	}
	
	public int getLength(){
		
		return blocks.size();
		
	}
	

}
