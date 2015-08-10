package com.skeeter144.skills;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPickaxe;

public class SkillMining {
	
	
	private static HashMap<String, Integer> pickaxeTierMap = new HashMap<String, Integer>();
	private static HashMap<Integer, String> pickaxeNameForTierMap = new HashMap<Integer, String>();
	private static HashMap<String, Integer> blockTierMap = new HashMap<String, Integer>();
	private static HashMap<String, Integer> pickaxeMap = new HashMap<String, Integer>();
	private static HashMap<Integer, Integer> levelsMap = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> expMap = new HashMap<Integer, Integer>();
	
	public static void init(){
		
		
		//tiers: 1 = wood, 2 = wood, 3 = gold, 4 = diamond
		pickaxeTierMap.put(Items.wooden_pickaxe.getUnlocalizedName(), 1);
		pickaxeTierMap.put(Items.iron_pickaxe.getUnlocalizedName(), 2);
		pickaxeTierMap.put(Items.golden_pickaxe.getUnlocalizedName(), 3);
		pickaxeTierMap.put(Items.diamond_pickaxe.getUnlocalizedName(), 4);
		
		pickaxeNameForTierMap.put(1, "Wooden");
		pickaxeNameForTierMap.put(2, "Iron");
		pickaxeNameForTierMap.put(1, "Gold");
		pickaxeNameForTierMap.put(1, "Diamond");
		
		blockTierMap.put(Blocks.coal_ore.getUnlocalizedName(), 1);
		blockTierMap.put(Blocks.iron_ore.getUnlocalizedName(), 2);
		blockTierMap.put(Blocks.gold_ore.getUnlocalizedName(), 2);
		blockTierMap.put(Blocks.diamond_ore.getUnlocalizedName(), 2);
		
		pickaxeMap.put(Items.wooden_pickaxe.getUnlocalizedName(), 0);
		pickaxeMap.put(Items.iron_pickaxe.getUnlocalizedName(), 3);
		pickaxeMap.put(Items.golden_pickaxe.getUnlocalizedName(), 7);
		pickaxeMap.put(Items.diamond_pickaxe.getUnlocalizedName(), 10);
		
		
		levelsMap.put(Block.getIdFromBlock(Blocks.coal_ore), 0);
		levelsMap.put(Block.getIdFromBlock(Blocks.iron_ore), 3);
		levelsMap.put(Block.getIdFromBlock(Blocks.gold_ore), 10);
		levelsMap.put(Block.getIdFromBlock(Blocks.diamond_ore), 15);
		
		expMap.put(Block.getIdFromBlock(Blocks.coal_ore), 5);
		expMap.put(Block.getIdFromBlock(Blocks.iron_ore), 10);
		expMap.put(Block.getIdFromBlock(Blocks.gold_ore), 25);
		expMap.put(Block.getIdFromBlock(Blocks.diamond_ore), 250);
		
		
		
	}
	
	public static int getExpForBlock(Block block){
		
		int exp = expMap.get(Block.getIdFromBlock(block));
		if(exp == 0)
			exp = 5;
		return exp;
	}
	
	public static Integer getLevelForBlockId(int id){
		return levelsMap.get(id);
	}
	
	public static Integer getLevelForPick(ItemPickaxe pick){
		
		return pickaxeMap.get(pick.getUnlocalizedName());
	}
	
	public static Integer getTierForBlock(Block block){
		return blockTierMap.get(block.getUnlocalizedName());
	}
	
	public static Integer getTierForPickaxe(ItemPickaxe pick){
		return pickaxeTierMap.get(pick.getUnlocalizedName());
	}
	
	public static String getPickaxeNameForTier(int val){
		return pickaxeNameForTierMap.get(val);
	}

}
