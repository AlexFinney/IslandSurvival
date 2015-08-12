package com.skeeter144.skills;

import java.util.ArrayList;
import java.util.HashMap;

import com.skeeter144.items.ISItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SkillCrafting {
	
	static ArrayList<Recipe> recipes;
	
	public static void init(){
		recipes = new ArrayList<Recipe>();
		
		
		ArrayList<ItemStack> chiselList = new ArrayList<ItemStack>();
		chiselList.add(new ItemStack(Blocks.cobblestone, 1));
		chiselList.add(new ItemStack(Items.stick, 1));
		recipes.add(new Recipe(new ItemStack(ISItems.chisel,1), chiselList));
	}
	
	
	
	public static ArrayList<Recipe>  getRecipes(){
		return recipes;
	}
	

}
