package com.skeeter144.database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerLevelsDatabase implements Serializable {
	
	HashMap<Integer, PlayerLevels> playerDatabase = new HashMap<Integer, PlayerLevels>();
	
	
	public void PlayerLevelsDatabase(){
		
	}

	
	public PlayerLevels getPlayerLevels(UUID uuid){
		return playerDatabase.get(uuid.hashCode());
	}
	
	public void addNewPlayer(EntityPlayer player){
		if(playerDatabase.get(player.getPersistentID().hashCode()) == null)
			playerDatabase.put(player.getPersistentID().hashCode(), new PlayerLevels(player));
	}
	
}
