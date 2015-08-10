package com.skeeter144.crafting;

import com.skeeter144.items.ISItems;
import com.skeeter144.items.ISTools;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ISRecipes {

	public static void init(){
		GameRegistry.addShapedRecipe(new ItemStack(ISTools.makeshiftHatchet,1), new Object[]
				{"AB", " C", 'A', Blocks.cobblestone, 'B', Blocks.vine, 'C', Items.stick});
		GameRegistry.addShapedRecipe(new ItemStack(ISItems.chisel), new Object[]
				{"A","B",'A',Blocks.cobblestone,'B',Items.stick});
	}
}
