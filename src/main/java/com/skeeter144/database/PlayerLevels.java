package com.skeeter144.database;

import java.io.Serializable;

import com.skeeter144.main.IslandSurvival;
import com.skeeter144.skills.SkillsMain;
import com.skeeter144.util.Strings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class PlayerLevels implements Serializable{
	
	private static final long serialVersionUID = -4350468145669926761L;

	int uuidHash;
	
	int miningExp = 0;
	int attackExp = 0;
	int defenseExp = 0;
	
	int miningLevel = 1;
	int attackLevel = 1;
	int defenseLevel = 1;
	
	
	
	private void addExp(int newExp, EntityPlayer player, int prevExp, String skillName){
		int oldLevel = 0;
		switch(skillName){
			case Strings.MINING:
				oldLevel = miningLevel;
				break;
			case Strings.ATTACK:
				oldLevel = attackLevel;
				break;
			case Strings.DEFENSE:
				oldLevel = defenseLevel;
				break;
		}
		int newLevel = SkillsMain.getLevelForExp(newExp);
		if(oldLevel != newLevel){
			
			IslandSurvival.instance.spawnFirework(player);
			
			player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + 
					"You have level up to " + skillName + " " + newLevel + "!"));
			switch(skillName){
			case Strings.MINING:
				++ miningLevel;
				break;
			case Strings.ATTACK:
				++attackLevel;
				break;
			case Strings.DEFENSE:
				++defenseLevel;
				break;
		}
		}
			
	}
	
	public PlayerLevels(EntityPlayer player){
		uuidHash = player.getPersistentID().hashCode();
	}

	public int getUuidHash() {
		return uuidHash;
	}

	public int getMiningExp() {
		return miningExp;
	}

	public void addMiningExp(int val, EntityPlayer player){
		if(val < 0)
			val = 0;
		int oldExp = miningExp;
		this.miningExp += val;
		addExp(miningExp,player, oldExp, Strings.MINING);
	}
	
	public int getMiningLevel(){
		return miningLevel;
	}
	
	public int getAttackExp() {
		return attackExp;
	}
	
	public int getAttackLevel(){
		return attackLevel;
	}
	
	public void addAttackExp(int val, EntityPlayer player){
		if(val < 0)
			val = 0;
		int oldExp = attackExp;
		this.attackExp += val;
		addExp(attackExp,player,oldExp, Strings.ATTACK);
	}

	public int getDefenseExp() {
		return defenseExp;
	}
	
	public void addDefenseExp(int val, EntityPlayer player){
		if(val < 0)
			val = 0;
		int oldExp = defenseExp;
		this.defenseExp += val;
		addExp(defenseExp,player,defenseExp, Strings.DEFENSE);
	}
	
	public int getDefenseLevel(){
		return defenseLevel;
	}
	

}
