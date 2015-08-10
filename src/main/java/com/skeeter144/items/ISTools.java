package com.skeeter144.items;

import com.skeeter144.util.Strings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraftforge.common.util.EnumHelper;

public class ISTools {

	static Item.ToolMaterial MAKESHIFT = EnumHelper.addToolMaterial("MAKESHIFT", 
			1, 256, 2.0f, 4.0f, 5);
	
	
	public static Item makeshiftHatchet = new ToolMakeshiftHatchet(MAKESHIFT)
		.setUnlocalizedName("makeshiftHatchet").setTextureName(Strings.MODID + ":makeshiftHatchet")
		.setCreativeTab(CreativeTabs.tabTools);
	
	public static void init(){
		GameRegistry.registerItem(makeshiftHatchet, makeshiftHatchet.getUnlocalizedName());
	}
	
}
