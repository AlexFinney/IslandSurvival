package com.skeeter144.gui;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import net.minecraft.util.ResourceLocation;

public class GuiLevelsButton {
	
	private Rectangle2D.Double rect;
	private ResourceLocation location;
	private int u, v;
	String name;
	
	
	public GuiLevelsButton(int u, int v, ResourceLocation location, String name){
			rect = new Rectangle2D.Double(0, 0, 0, 0);
			this.u = u;
			this.v = v;
			this.name = name;
	}
	
	public boolean containsPoint(Point2D.Double point){
		return rect.contains(point);
	}
	
	public Rectangle2D.Double getRect(){
		return rect;
	}
	
	public ResourceLocation getLocation(){
		return location;
	}
	
	public void setRect(Rectangle2D.Double rect){
		this.rect = rect;
	}
	
	public int getU(){
		return u;
	}
	
	public int getV(){
		return v;
	}
	
	public String getName(){
		return name;
	}

}
