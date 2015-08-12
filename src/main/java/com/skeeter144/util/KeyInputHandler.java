package com.skeeter144.util;

import com.skeeter144.gui.LevelsGUI;
import com.skeeter144.main.IslandSurvival;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputHandler {

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		ChunkCoordinates cc = Minecraft.getMinecraft().thePlayer.getPlayerCoordinates();
		
		if (KeyBindings.keyLevels.isPressed()){
			Minecraft.getMinecraft().thePlayer.openGui(IslandSurvival.instance, IslandSurvival.levelsGuiId,
					Minecraft.getMinecraft().theWorld, cc.posX, cc.posY, cc.posZ);
		}
	}
}
