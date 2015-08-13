package com.skeeter144.packets;

import java.util.ArrayList;

import com.skeeter144.skills.Recipe;
import com.skeeter144.skills.SkillCrafting;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
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

		
		@Override
		public IMessage onMessage(CraftingMessage message, MessageContext ctx) {
			
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			
			int id = message.recipeId;
			
			Recipe recipe = SkillCrafting.getRecipeForId(id);
			if(recipe == null){
				return null;
			}
			
			InventoryPlayer inv = player.inventory;
			ArrayList<ItemStack> rr = recipe.getResourcesRequired();
			for(int j = 0; j < rr.size(); ++j){
				
				for(int i = 0; i < inv.getSizeInventory(); ++i){
					
					if(inv.getStackInSlot(i).getItem().equals(rr.get(j).getItem())){
						if(inv.getStackInSlot(i).stackSize <= rr.get(j).stackSize){
							System.out.println("can craft!");
						}
					}
					
				}
			}
			
		
			return null;
		}
		
	}

}
