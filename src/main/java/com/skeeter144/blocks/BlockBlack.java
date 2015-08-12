package com.skeeter144.blocks;

import com.skeeter144.main.IslandSurvival;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockBlack extends Block {

	protected BlockBlack(Material material) {
		super(material);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer p, int q, float a, float b, float c) {
		p.openGui(IslandSurvival.instance, IslandSurvival.craftingGuiId, world, x, y, z);
		return true;
	}

}
