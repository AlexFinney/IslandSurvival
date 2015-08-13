package com.skeeter144.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFirework;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import com.skeeter144.blocks.ISBlocks;
import com.skeeter144.commands.BlockBreakingCommands;
import com.skeeter144.crafting.ISRecipes;
import com.skeeter144.database.PlayerLevelsDatabase;
import com.skeeter144.events.BlockBreakingHandler;
import com.skeeter144.events.ConnectionHandler;
import com.skeeter144.items.ISItems;
import com.skeeter144.items.ISTools;
import com.skeeter144.mobs.ISMobs;
import com.skeeter144.packets.CraftingMessage;
import com.skeeter144.packets.CraftingMessage.Handler;
import com.skeeter144.skills.SkillCrafting;
import com.skeeter144.skills.SkillMining;
import com.skeeter144.skills.SkillsMain;
import com.skeeter144.util.KeyBindings;
import com.skeeter144.util.KeyInputHandler;
import com.skeeter144.util.Strings;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid=Strings.MODID, name=Strings.NAME, version=Strings.VERSION)
public class IslandSurvival {
	
	
	public static final int levelsGuiId = 0;
	public static final int craftingGuiId = 1;
	
	public static SimpleNetworkWrapper network;
	
	
	@SidedProxy(clientSide = "com.skeeter144.main.ClientProxy", serverSide = "com.skeeter144.main.ServerProxy")
	public static ServerProxy proxy;
	
	@Metadata
    public static ModMetadata meta;
	
	@Instance(value=Strings.MODID)
	public static IslandSurvival instance;
	
	private BlockBreakingHandler handler;
	
	private PlayerLevelsDatabase database;
	
	
		
	public PlayerLevelsDatabase getPlayerLevelsDatabase(){
		return database;
	}
	
	private void loadDatabase() {
		File file = new File("data/database.bin");
		if(!file.exists()){
			database = new PlayerLevelsDatabase();
			return;
		}	
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			database = (PlayerLevelsDatabase)ois.readObject();
			if(database == null)
				database = new PlayerLevelsDatabase();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void saveDatabase(){
		File file = new File("data/database.bin");
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(database);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e){
		// Item/block init and registering
		
		ISItems.init();
		ISTools.init();
		ISBlocks.init();
		
		SkillsMain.init();
		SkillMining.init();
		SkillCrafting.init();
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("MyChannel");
		network.registerMessage(Handler.class, CraftingMessage.class, 0, Side.SERVER);
		
		
		//Config handling
		
		loadDatabase();
		
		handler = new BlockBreakingHandler();
		
		
		MinecraftForge.EVENT_BUS.register(new ConnectionHandler());
		
		if(!proxy.isRemote()){
			KeyBindings.init();
			FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		}
		
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent e){
		//Proxy, TileEntity, crafting, entity, GUI and Packet Registering
		
		ISRecipes.init();
		ISMobs.init();
		proxy.registerRenderThings();
		proxy.registerNetworkThings();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e){
		
	}
		
	@Mod.EventHandler
	public void serverStopping(FMLServerStoppingEvent e){
		handler.prepareForShutdown();
		saveDatabase();
	}
	
	@Mod.EventHandler
	public void serverStarting(FMLServerStartedEvent e){
		handler.loadBlocks();
	}
	
	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	  {
	    event.registerServerCommand(new BlockBreakingCommands());
	  }
	
	@SubscribeEvent
	public void onPlayerLogin(EntityJoinWorldEvent p){
		System.out.println("\n\n\n\n\n--------------------------------------------\n\n\n\n");
		if(p.entity instanceof EntityPlayer){
			EntityPlayer pl = (EntityPlayer) p.entity;
			System.out.println("player logged in!!! :d" + " " + pl.getUniqueID().hashCode());
		}
	}
	
	public void spawnFirework(EntityPlayer player){
		System.out.println("spawn particles");
		Items.fireworks.onItemUse(new ItemStack(Items.fireworks), player, player.worldObj, player.getPlayerCoordinates().posX,
																						   player.getPlayerCoordinates().posY,
																						   player.getPlayerCoordinates().posZ, 
																						   0, 0, 0, 0);
	}
	

}
