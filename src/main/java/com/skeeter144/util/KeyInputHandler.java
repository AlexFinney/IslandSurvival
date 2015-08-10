package com.skeeter144.util;

import com.skeeter144.gui.LevelsGUI;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputHandler {

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (KeyBindings.keyLevels.isPressed()){
			
			if(!MinecraftServer.getServer().isDedicatedServer())
				Minecraft.getMinecraft().displayGuiScreen(new LevelsGUI());
		}
	}
}
