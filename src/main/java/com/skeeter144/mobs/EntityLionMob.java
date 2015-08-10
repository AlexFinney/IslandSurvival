package com.skeeter144.mobs;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityLionMob extends EntityAnimal {

	public EntityLionMob(World world) {
		super(world);
		
		this.setSize(1.0f, 1.0f);
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0f,true ));
		this.tasks.addTask(0, new EntityAILeapAtTarget(this, 1.0f));
		this.tasks.addTask(2,new EntityAIWander(this, .75f));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.tasks.addTask(4,new EntityAIPanic(this, 1.0f));
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return null;
	}
	@Override
	public boolean isAIEnabled(){
		return true;
	}
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0F);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.75F);
	}

}
