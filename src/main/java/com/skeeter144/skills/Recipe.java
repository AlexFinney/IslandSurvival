package com.skeeter144.skills;

import java.util.ArrayList;
import java.util.HashMap;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Recipe {
	
	
	ItemStack result;
	ArrayList<ItemStack> resourcesRequired;
	
	public Recipe(ItemStack result, ArrayList<ItemStack> resourcesRequired){
		this.result = result;
		this.resourcesRequired = resourcesRequired;
	}
	
	public String getName(){
		return LanguageRegistry.instance().getStringLocalization(result.getUnlocalizedName());
	}
	
	public ArrayList<ItemStack> getResourcesRequired(){
		return resourcesRequired;
	}

	public ItemStack getResult(){
		return result;
	}
}
