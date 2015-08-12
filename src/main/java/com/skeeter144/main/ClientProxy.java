package com.skeeter144.main;

import com.skeeter144.mobs.EntityLionMob;
import com.skeeter144.mobs.EntityPenguinMob;
import com.skeeter144.mobs.models.ModelLion;
import com.skeeter144.mobs.models.ModelPenguin;
import com.skeeter144.mobs.rendering.RenderLionMob;
import com.skeeter144.mobs.rendering.RenderPenguinMob;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy {

	@Override
	public boolean isRemote() {
		return false;
	}
	
	@Override
	public void registerRenderThings(){
		RenderingRegistry.registerEntityRenderingHandler(EntityPenguinMob.class, 
				new RenderPenguinMob(new ModelPenguin(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityLionMob.class, 
				new RenderLionMob(new ModelLion(), 0));
	}
}
