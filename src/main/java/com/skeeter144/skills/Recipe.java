package com.skeeter144.skills;

import java.util.ArrayList;
import java.util.HashMap;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class Recipe {
	
	
	ItemStack result;
	ArrayList<ItemStack> resourcesRequired;
	
	public Recipe(ItemStack result, ArrayList<ItemStack> resourcesRequired){
		this.result = result;
		this.resourcesRequired = resourcesRequired;
	}
	
	public String getName(){
		return StatCollector.translateToLocal(result.getUnlocalizedName() + ".name");
	}
	
	public ArrayList<ItemStack> getResourcesRequired(){
		return resourcesRequired;
	}

	public ItemStack getResult(){
		return result;
	}
}
