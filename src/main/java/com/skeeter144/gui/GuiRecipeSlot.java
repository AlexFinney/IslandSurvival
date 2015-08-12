package com.skeeter144.gui;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.skeeter144.skills.Recipe;

public class GuiRecipeSlot {
	
	private Recipe recipe;
	
	private Rectangle2D rect;
	
	public GuiRecipeSlot(Recipe recipe, int x, int y, int width, int height){
		rect = new Rectangle2D.Double(x,y,width,height);
		this.recipe = recipe;
	}
	
	public double getX(){
		return rect.getX();
	}
	
	public double getY(){
		return rect.getY();
	}
	
	public double getWidth(){
		return rect.getWidth();
	}
	
	public double getHeight(){
		return rect.getHeight();
	}
	
	public boolean containsPoint(Point2D.Double point){
		return rect.contains(point);
	}
	
	public void setRect(Rectangle2D.Double rect){
		this.rect = rect;
	}
	
	public Recipe getRecipe(){
		return recipe;
	}
}
