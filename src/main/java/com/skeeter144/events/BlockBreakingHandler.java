package com.skeeter144.events;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.skeeter144.blocks.ISBlocks;
import com.skeeter144.main.IslandSurvival;
import com.skeeter144.skills.SkillMining;
import com.skeeter144.util.BlockWrapper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockBreakingHandler {
	
	private final Block[] mineableBlocks = new Block[]
			{Blocks.iron_ore, 
			Blocks.gold_ore, 
			Blocks.diamond_ore,
			Blocks.coal_ore, 
			Blocks.redstone_ore, 
			Blocks.emerald_ore,
			ISBlocks.mithrilOreBlock,
			ISBlocks.adamantiteOreBlock};
	
	private final HashMap<Block, Integer> blockRespawnTimes = new HashMap<Block, Integer>();
	private ArrayList<BlockWrapper> brokenBlocks = new ArrayList<BlockWrapper> ();
	
	private static String permissionLevel = "all";
		
	public BlockBreakingHandler(){
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
		blockRespawnTimes.put(Blocks.iron_ore, 15);
		blockRespawnTimes.put(Blocks.gold_ore, 30);
		blockRespawnTimes.put(Blocks.diamond_ore, 120);
		blockRespawnTimes.put(Blocks.coal_ore, 10);
		blockRespawnTimes.put(Blocks.redstone_ore, 15);
		blockRespawnTimes.put(Blocks.emerald_ore, 45);
		blockRespawnTimes.put(ISBlocks.mithrilOreBlock, 60);
		blockRespawnTimes.put(ISBlocks.adamantiteOreBlock, 90);
	}
	
	@SubscribeEvent
	public void onBlockBreaking(BreakEvent e){
		if(playerHasPermission(e.getPlayer())){
			return;
		}	
		e.setExpToDrop(0);
		
	//"world guard" checks	
		boolean found = false;
		for(Block b : mineableBlocks){
			if(e.block.getUnlocalizedName().equals(b.getUnlocalizedName())){
				found = true;
				break;
			}
		}//end for loop
		
		if(!found){
			e.setCanceled(true);
			e.getPlayer().addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + 
					"You cannot break that block!"));
			return;
		}
		
		
	//pickaxe checks
		if( e.getPlayer().inventory.getCurrentItem().getItem()instanceof ItemPickaxe){
			ItemPickaxe pick = (ItemPickaxe) e.getPlayer().inventory.getCurrentItem().getItem();
			Integer level = SkillMining.getLevelForPick(pick);
			if(level == null){
				e.setCanceled(true);
				System.out.println("\n\n\nERROR! PLAYER'S PICKAXE NAME NOT REGISTER IN SKILLMINING\n\n\n");
				return;
			}
			if(IslandSurvival.instance.getPlayerLevelsDatabase().getPlayerLevels(e.getPlayer().getPersistentID())
					.getMiningLevel() < level.intValue()){
				e.getPlayer().addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + 
						"You need at least level " + level.intValue() + " Mining to use that pickaxe!"));
				e.setCanceled(true);
				return;
			}
			
			int pickTier = SkillMining.getTierForPickaxe(pick);
			Block b = e.block;
			Integer blockTier = SkillMining.getTierForBlock(b);
			
			if(pickTier < blockTier){
				String strPickTier = SkillMining.getPickaxeNameForTier(blockTier);
				String a = "";
				switch(strPickTier){
					case "Iron":
						a = "an";
						break;
					default:
						a= "a";
				}
				e.getPlayer().addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + 
						"You need at least " + a + " "  + strPickTier + " Pickaxe to"
								+ " mine " + e.block.getLocalizedName()	+ "!"));
				e.setCanceled(true);
				
			}
			
				
		}else{
			e.getPlayer().addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + 
					"You need to mine ores with a pickaxe, silly!"));
		}
		
		
	//block level checks	
		Integer levelReqForBlock = SkillMining.getLevelForBlockId(Block.getIdFromBlock(e.block));
		if(levelReqForBlock == null)
			return;
		
		if(IslandSurvival.instance.getPlayerLevelsDatabase().getPlayerLevels(e.getPlayer()
				.getPersistentID()).getMiningLevel() < levelReqForBlock.intValue()){
			if(playerHasPermission(e.getPlayer()))
				return;
			e.setCanceled(true);
			e.getPlayer().addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + 
					"You need at least Level " + EnumChatFormatting.WHITE +  levelReqForBlock + EnumChatFormatting.RED +" Mining to mine that!"));
		}
		
				
	}//end onBlockBreak
	
	private boolean playerHasPermission(EntityPlayer p){
		
		if(permissionLevel.equalsIgnoreCase("none"))
			return true;
		else if(permissionLevel.equalsIgnoreCase("op")){
			if(MinecraftServer.getServer().getConfigurationManager()
					.func_152596_g(p.getGameProfile()))
				return true;
		}
		
		return false;
		
		
	}
	
	public static void setPermissionLevel(String newLevel){
		permissionLevel = newLevel;
	}
	
	@SubscribeEvent
	public void onHarvest(HarvestDropsEvent e){
		if( permissionLevel.equals("op") && playerHasPermission(e.harvester)){
			return;
		}
		if(e.harvester != null){
			for(ItemStack is :  e.block.getDrops(e.world, e.x, e.y, e.x, 0, e.fortuneLevel)){
				e.harvester.inventory.addItemStackToInventory(is);
			}
			
			e.harvester.addExperience(e.block.getExpDrop(e.world, e.blockMetadata, e.fortuneLevel));
			
			e.world.setBlock(e.x, e.y, e.z, Blocks.bedrock);
			
			IslandSurvival.instance.getPlayerLevelsDatabase().getPlayerLevels(e.harvester.getPersistentID()).
				addMiningExp(SkillMining.getExpForBlock(e.block), e.harvester);
			
			brokenBlocks.add(new BlockWrapper(e.block.getIdFromBlock(e.block),
					e.x, e.y, e.z, e.world));
			
			BlockRespawner br = new BlockRespawner(e.world, e.x, e.y, e.z, e.block);
			Thread t = new Thread(br);
			t.start();
			
			e.drops.clear();
		}
		
	}
	
	
	
	private class BlockRespawner implements Runnable{

		World world;
		Integer x;
		Integer y; 
		Integer z;
		
		Block block;
		float timer = 0;
		
		public BlockRespawner(World world, int x, int y, int z, Block block){
			this.world = world;
			this.x = x;
			this.y = y;
			this.z = z;
			this.block = block;
		}
		
		@Override
		public void run() {
			if(world == null || x == null || y == null || z == null)
				try {
					throw new Exception("Block data not initizialized! Something went wrong with"
							+ " the passed block data.");
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			Timer timer = new Timer();
			timer.schedule(new TimerTask(){
				@Override
				public void run() {
					brokenBlocks.remove(new BlockWrapper(Block.getIdFromBlock(block),x,y,z,world));
					world.setBlock(x, y, z, block);
					
				}				
			}, blockRespawnTimes.get(block) * 1000);
		}
		
	}
	
	public void prepareForShutdown(){
		
		File dir = new File("data");
		dir.mkdir();
		try {
			ObjectOutputStream oos = 
				new ObjectOutputStream( new FileOutputStream(dir.getAbsolutePath() + "/brokenBlocks.bin"));
			oos.writeObject(brokenBlocks);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadBlocks(){
		File dir = new File("data");
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dir.getAbsolutePath() + "/brokenBlocks.bin"));
			Object o = ois.readObject();
			if(o instanceof ArrayList){
				ArrayList<BlockWrapper> list = (ArrayList<BlockWrapper>) o;
				for(BlockWrapper wrapper : list){
					Block block = Block.getBlockById(wrapper.getId());
					String worldName = wrapper.getWorldName();
					World world = null;
					for(WorldServer ws : MinecraftServer.getServer().worldServers){
						if(worldName.equals(ws.getWorldInfo().getWorldName())){
							world = ws;
							break;
						}
					}
					if(world != null){
						world.setBlock(wrapper.getX(), wrapper.getY(), wrapper.getZ(), block);
						brokenBlocks.add(wrapper);
					}
				}
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
