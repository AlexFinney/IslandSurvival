package com.skeeter144.events;

import com.skeeter144.main.IslandSurvival;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConnectionHandler {

	@SubscribeEvent
	public void onPlayerJoinEvent(EntityJoinWorldEvent e){
		if(e.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)e.entity;
			if(IslandSurvival.instance.getPlayerLevelsDatabase().getPlayerLevels(player.getPersistentID()) == null){
				IslandSurvival.instance.getPlayerLevelsDatabase().addNewPlayer(player);
			}
		}
	}
	
}
