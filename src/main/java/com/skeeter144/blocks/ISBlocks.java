package com.skeeter144.blocks;

import com.skeeter144.util.Strings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ISBlocks {
	
	public static Block blackBlock = new BlockBlack(Material.cloth).setBlockName("blackBlock")
			.setBlockTextureName(Strings.MODID + ":blackBlock").setCreativeTab(CreativeTabs.tabBlock);
	
	public static void init(){
		GameRegistry.registerBlock(blackBlock, blackBlock.getUnlocalizedName());
	}

}
