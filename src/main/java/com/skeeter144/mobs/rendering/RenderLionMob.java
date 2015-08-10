package com.skeeter144.mobs.rendering;

import com.skeeter144.mobs.EntityLionMob;
import com.skeeter144.util.Strings;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLionMob  extends RenderLiving{

	private static final ResourceLocation mobTextures = new ResourceLocation(Strings.MODID + ":textures/entity/lionMob.png");
	
	
	public RenderLionMob(ModelBase modelBase, float par2) {
		super(modelBase, par2);
	}

	protected ResourceLocation getEntityTexture(EntityLionMob entity){
		return mobTextures;
	}
	
	protected ResourceLocation getEntityTexture(Entity entity){
		return this.getEntityTexture((EntityLionMob)entity);
	}
	
}
