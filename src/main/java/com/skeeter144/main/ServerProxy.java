package com.skeeter144.main;

import com.skeeter144.gui.ISGuiHandler;

import cpw.mods.fml.common.network.NetworkRegistry;

public class ServerProxy {
	
	public void registerRenderThings(){
		
	}
	
	public void registerNetworkThings(){
		NetworkRegistry.INSTANCE.registerGuiHandler(IslandSurvival.instance, new ISGuiHandler());
	}
	
}
