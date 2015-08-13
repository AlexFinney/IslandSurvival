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
	int levelRequired;
	int expGiven;
	int id;
	
	public Recipe(ItemStack result, ArrayList<ItemStack> resourcesRequired, int levelRequired, int expGiven, int id){
		this.result = result;
		this.resourcesRequired = resourcesRequired;
		this.levelRequired = levelRequired;
		this.expGiven = expGiven;
		this.id = id;
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
	
	public int getExpGiven(){
		return expGiven;
	}
	
	public int getLevelRequired(){
		return levelRequired;
	}
	
	public int getId(){
		return id;
	}
}
