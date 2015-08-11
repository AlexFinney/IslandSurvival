package com.skeeter144.blocks;

import com.skeeter144.util.Strings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ISBlocks {
	
	public static Block blackBlock = new BlockBlack(Material.cloth).setBlockName("blackBlock")
			.setBlockTextureName(Strings.MODID + ":blackBlock").setCreativeTab(CreativeTabs.tabBlock);
	
	public static Block mithrilOreBlock = new BlockMithrilOre(Material.iron).setBlockName("mithrilOreBlock")
			.setBlockTextureName(Strings.MODID + ":mithrilOreBlock").setCreativeTab(CreativeTabs.tabBlock);
	
	public static Block adamantiteOreBlock = new BlockAdamantiteOre(Material.iron).setBlockName("adamantiteOreBlock")
			.setBlockTextureName(Strings.MODID + ":adamantiteOreBlock").setCreativeTab(CreativeTabs.tabBlock);
	
	public static Block dragonStoneOreBlock = new BlockDragonStoneOre(Material.iron).setBlockName("dragonStoneOreBlock")
			.setBlockTextureName(Strings.MODID + ":dragonStoneOreBlock").setCreativeTab(CreativeTabs.tabBlock);
	
	public static void init(){
		GameRegistry.registerBlock(blackBlock, blackBlock.getUnlocalizedName());
		GameRegistry.registerBlock(mithrilOreBlock, mithrilOreBlock.getUnlocalizedName());
		GameRegistry.registerBlock(adamantiteOreBlock, adamantiteOreBlock.getUnlocalizedName());
		GameRegistry.registerBlock(dragonStoneOreBlock, dragonStoneOreBlock.getUnlocalizedName());
	}

}
