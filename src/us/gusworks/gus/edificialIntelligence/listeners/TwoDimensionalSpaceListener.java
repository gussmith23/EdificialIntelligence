package us.gusworks.gus.edificialIntelligence.listeners;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;

import us.gusworks.gus.edificialIntelligence.spaces.OneDimensionalSpace;
import us.gusworks.gus.edificialIntelligence.spaces.TwoDimensionalSpace;

import com.gus.edificialIntelligence.EdificialIntelligencePlugin;

public class TwoDimensionalSpaceListener implements Listener {

	EdificialIntelligencePlugin plugin;
	public static ArrayList<TwoDimensionalSpace> debugListTwoDimensionalSpaces = new ArrayList<TwoDimensionalSpace>();
	
	public TwoDimensionalSpaceListener(EdificialIntelligencePlugin plugin){
		
		this.plugin = plugin;
		
	}

	public static void update() {
		
		ArrayList<OneDimensionalSpace> al = new ArrayList<OneDimensionalSpace>();
		al.addAll((Collection<? extends OneDimensionalSpace>) OneDimensionalSpace.oneDimensionalSpaces[0]);al.addAll((Collection<? extends OneDimensionalSpace>) OneDimensionalSpace.oneDimensionalSpaces[1]);al.addAll((Collection<? extends OneDimensionalSpace>) OneDimensionalSpace.oneDimensionalSpaces[2]);
		
		EdificialIntelligencePlugin.log.info("1");
		
		//so now the one dimensional spaces are separated out into three arrays
		for(OneDimensionalSpace space : al){
			
			EdificialIntelligencePlugin.log.info("2");
			
			//this will cycle twice: once for each orientation that is not this space's orientation
			for(int i=1;i<3;i++){
				
				EdificialIntelligencePlugin.log.info("3");
				
				//gives the orientation to be checked
				int targetOrientation = (space.orientation + i)%3;
				ArrayList<OneDimensionalSpace> targetArrayList = (ArrayList<OneDimensionalSpace>) OneDimensionalSpace.oneDimensionalSpaces[targetOrientation];
				
				EdificialIntelligencePlugin.log.info(" " + targetOrientation+ " " + targetArrayList.size());
				
				//so at this point we have one space ('space') in one orientation, and
				//a set of spaces ('targetArrayList') in another orientation. we'll now
				//check for overlap in these sets, which is, thankfully, easy.
				
				//this cycles through the spaces to be checked against our main space
				for(OneDimensionalSpace targetSpace : targetArrayList){
					
					EdificialIntelligencePlugin.log.info("4");
					
					//this cycles through the blocks in the target array
					for(Block targetBlock : targetSpace.blocks){
						
						EdificialIntelligencePlugin.log.info("5");
						
						//this cycles through the blocks in the original 'space' space.
						for(Block targetSourceBlock : space.blocks){
							
							EdificialIntelligencePlugin.log.info("6");
							
							if(targetBlock.equals(targetSourceBlock)) TwoDimensionalSpaceListener.debugListTwoDimensionalSpaces.add(new TwoDimensionalSpace(space,targetSpace));							
							
						}
						
						
					}
					
				}
				
				
				
			}
			
		}
		
		
		
		
	}
	
	public static void visualize(){
		
		EdificialIntelligencePlugin.log.info("bub");
		
		for(TwoDimensionalSpace space : debugListTwoDimensionalSpaces){
			
			EdificialIntelligencePlugin.log.info("bib");
			
			for(Block b : space.blocks){
				
				EdificialIntelligencePlugin.log.info("crip");
				
				b.setType(Material.OBSIDIAN);
				
			}
			
		}
		
	}

}
