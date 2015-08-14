package com.skeeter144.packets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.skeeter144.items.ISItems;
import com.skeeter144.main.IslandSurvival;
import com.skeeter144.skills.Recipe;
import com.skeeter144.skills.SkillCrafting;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldServer;

public class CraftingMessage implements IMessage {

	int recipeId;
	
	public CraftingMessage(){
		
	}
	
	public CraftingMessage(int id){
		this.recipeId = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.recipeId = ByteBufUtils.readVarShort(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarShort(buf, recipeId);
	}
	
	public static class Handler implements IMessageHandler<CraftingMessage, IMessage>{

		
		private void craftItem(EntityPlayer player, Recipe recipe){
			for(int i = 0; i < recipe.getResourcesRequired().size(); ++i){
				for(int j = 0; j < recipe.getResourcesRequired().get(i).stackSize; ++j){
					player.inventory.consumeInventoryItem(recipe.getResourcesRequired().get(i).getItem());
				}
			}
			recipe.getResult().getItem();
			int itemId = Item.getIdFromItem(recipe.getResult().getItem());
			Item item = Item.getItemById(itemId);
			EntityItem dropItem = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(item));
			dropItem.delayBeforeCanPickup = 0;
			player.worldObj.spawnEntityInWorld(dropItem);
			
			IslandSurvival.instance.getPlayerLevelsDatabase().getPlayerLevels(player.getPersistentID())
				.addCraftingExp(recipe.getExpGiven(), player);
			
			player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + 
					"You successfully crafted the " + recipe.getName()));
		}
		
		@Override
		public IMessage onMessage(CraftingMessage message, MessageContext ctx) {
			
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			
			int id = message.recipeId;
			
			Recipe recipe = SkillCrafting.getRecipeForId(id);
			if(recipe == null){
				return null;
			}
			
			int levelReq = recipe.getLevelRequired();
			int playerLevel = IslandSurvival.instance.getPlayerLevelsDatabase()
					.getPlayerLevels(player.getPersistentID()).getCraftingLevel();
			System.out.println("Player level: " + playerLevel + " Level Req: " + levelReq);
			if(playerLevel < levelReq){
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + 
						"You need at least level " + levelReq + " Crafting to create the " + recipe.getName() ));
				return null;
			}
			
			ArrayList<ItemStack> rr = recipe.getResourcesRequired();
			ArrayList<String> missingItems = new ArrayList<String>();
			HashMap<Integer, Integer> itemsMap = new HashMap<Integer, Integer>();
			
			for(int i = 0; i < player.inventory.getSizeInventory(); ++i){
				if(player.inventory.getStackInSlot(i) == null){
					continue;
				}
				int itemId = Item.getIdFromItem(player.inventory.getStackInSlot(i).getItem());
				if(itemsMap.get(itemId) != null){
					int curVal = itemsMap.get(itemId);
					itemsMap.put(itemId, curVal + player.inventory.getStackInSlot(i).stackSize);
				}else{
					itemsMap.put(itemId, player.inventory.getStackInSlot(i).stackSize);
				}
				
			}			
			for(int i = 0; i < rr.size(); ++i){
				Item item = rr.get(i).getItem();
				int itemId = Item.getIdFromItem(item);
				
				Integer val = itemsMap.get(itemId);
				if(val == null || val < rr.get(i).stackSize){
					missingItems.add(new ItemStack(item).getDisplayName());
				}
				
			}
			
			
			if(!missingItems.isEmpty()){
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + 
						"You are missing the following items: "));
				for(int i = 0; i < missingItems.size(); ++i){
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + 
								"     "  +  missingItems.get(i)));
				}
			}else{
				craftItem(player, recipe);
			}
			
		
			return null;
		}
		
	}

}
