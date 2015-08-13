package com.skeeter144.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.skeeter144.main.IslandSurvival;

import cpw.mods.fml.common.network.IGuiHandler;

public class ISGuiHandler implements IGuiHandler {

	public static CraftingGUI currentCraftingGUI;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		switch(ID){
			case IslandSurvival.levelsGuiId:
				return new LevelsGUI();
			case IslandSurvival.craftingGuiId:
				currentCraftingGUI = new CraftingGUI();
				return currentCraftingGUI;
		}
		return null;
	}

}
