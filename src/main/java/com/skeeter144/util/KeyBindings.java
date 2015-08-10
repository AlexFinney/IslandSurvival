package com.skeeter144.util;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindings {
	
	
	public static KeyBinding keyLevels;
	
	public static void init(){
		
		
		keyLevels = new KeyBinding("key.levels", Keyboard.KEY_L, "GUI");
		
		
		ClientRegistry.registerKeyBinding(keyLevels);
	}
}
