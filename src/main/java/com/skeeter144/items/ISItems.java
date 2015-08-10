package com.skeeter144.items;

import com.skeeter144.util.Strings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ISItems {

	public static Item chisel  = new ItemChisel().setUnlocalizedName("chisel").setTextureName(Strings.MODID +  ":chisel")
			.setCreativeTab(CreativeTabs.tabMisc).setMaxStackSize(1);
	
	public static void init(){
		GameRegistry.registerItem(chisel, chisel.getUnlocalizedName().substring(5));
	}
	
}
