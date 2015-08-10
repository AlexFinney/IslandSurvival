package com.skeeter144.mobs;

import java.lang.reflect.Array;

import scala.actors.threadpool.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import com.skeeter144.main.IslandSurvival;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ISMobs {

	public static void init(){
		registerEntity();
	}
	
	public static void registerEntity(){
		createEntity(EntityPenguinMob.class, "Penguin Mob", 0x0000FF, 0xFFFFFF);
		createEntity(EntityLionMob.class, "Lion Mob", 0x0000FF, 0x000F0F);
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		
		int randomId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
		EntityRegistry.registerModEntity(entityClass, entityName, randomId, IslandSurvival.instance, 64, 1, true);
		EntityRegistry.addSpawn(entityClass, 2, 0, 1, EnumCreatureType.creature, BiomeGenBase.forest);
		
		createEgg(randomId, solidColor, spotColor);
		
	}
	
	private static void createEgg(int randomId, int solidColor, int spotColor){
		EntityList.entityEggs.put(Integer.valueOf(randomId), new EntityList.EntityEggInfo(randomId, solidColor, spotColor));
		
	}
	
	
}
