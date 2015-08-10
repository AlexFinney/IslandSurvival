package com.skeeter144.util;

import java.io.Serializable;

import net.minecraft.world.World;

public class BlockWrapper implements Serializable{
	
	int id;
	int x;
	int y;
	int z;
	String worldName;
	
	
	public BlockWrapper(int id, int x, int y, int z, World world){
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.worldName = world.getWorldInfo().getWorldName();
	}


	public int getId() {
		return id;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getZ() {
		return z;
	}


	public String getWorldName() {
		return worldName;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof BlockWrapper))
			return false;
		BlockWrapper other = (BlockWrapper)obj;
		if(other.getId() != this.id)
			return false;
		if(!other.getWorldName().equals(this.worldName))
			return false;
		if(other.getX() != this.x || other.getY() != this.y || other.getZ() != this.z)
			return false;
		return true;		
	}

}
