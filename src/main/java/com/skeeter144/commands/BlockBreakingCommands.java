package com.skeeter144.commands;

import java.util.ArrayList;
import java.util.List;

import com.skeeter144.events.BlockBreakingHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class BlockBreakingCommands implements ICommand {
	private List aliases;

	public BlockBreakingCommands() {
		this.aliases = new ArrayList();
		this.aliases.add("blockbreaking");
		this.aliases.add("bb");
	}

	@Override
	public String getCommandName() {
		return "sample";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/bb priority <level> (all, op, none)";
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		EntityPlayer p = (EntityPlayer) sender;
		if(!MinecraftServer.getServer().getConfigurationManager().func_152596_g(p.getGameProfile())){
			sender.addChatMessage(new ChatComponentTranslation(
					EnumChatFormatting.RED + "You do not have access to that command."));
		}
			
		if (args.length == 0) {
			sender.addChatMessage(new ChatComponentTranslation(
					EnumChatFormatting.RED + "Not enough arguments!"));
			return;
		}

		if (args[0].equalsIgnoreCase("perm") || args[0].equalsIgnoreCase("permission")) {
			if (args.length != 2) {
				sender.addChatMessage(new ChatComponentTranslation(
						EnumChatFormatting.RED
								+ "/blockb permission <level> (all, op, none)"));
				return;
			} else {
				if (args[1].equalsIgnoreCase("all")	
						|| args[1].equalsIgnoreCase("op")
						|| args[1].equalsIgnoreCase("none")) {
					
					BlockBreakingHandler.setPermissionLevel(args[1]);
					sender.addChatMessage(new ChatComponentTranslation(
							EnumChatFormatting.GREEN
									+ "Block breaking permission has been set to <" + args[1] + ">" ));
				} else {
					sender.addChatMessage(new ChatComponentTranslation(
							EnumChatFormatting.RED
									+ "/blockbreaking priority <level> (all, op, none)"));
				}
			}

		}

	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender icommandsender,
			String[] astring) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		return false;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}