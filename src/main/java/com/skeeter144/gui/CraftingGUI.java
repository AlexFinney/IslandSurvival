package com.skeeter144.gui;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.skeeter144.skills.Recipe;
import com.skeeter144.skills.SkillCrafting;
import com.skeeter144.util.Strings;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CraftingGUI extends GuiScreen{
	
	ResourceLocation textureLoc = new ResourceLocation(Strings.MODID, "textures/gui/craftingGui.png");

	int guiWidth = 256;
	int guiHeight = 192;
	

	int iconWidth = 32;
	int iconHeight = 32;
	
	int tooltipWidth = 74;
	int tooltipHeight = 64;
	
	Rectangle2D currentHoverRect = null;
	int curHoverX = 0;
	int curHoverY = 0;
	
	public CraftingGUI() {
	}
	
	@Override
	public void drawScreen(int x, int y, float ticks) {
		
		
		drawBackground();
		drawTabIcons();
		drawCraftingIcons();
		
		
		super.drawScreen(x, y, ticks);
	}

	private void drawCraftingIcons() {
		
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		
		ArrayList<Recipe> recipes = SkillCrafting.getRecipes();
		RenderItem render = new RenderItem();
		
		int currentRow = 0;
		for(int i = 0; i < recipes.size(); ++i){
			
			ItemStack result = recipes.get(i).getResult();
			Item resultItem = result.getItem();
			
			render.renderItemAndEffectIntoGUI(resultItem.getFontRenderer(result), mc.getTextureManager(), 
					result, guiX + 10 + i * 16 + i * 2, 0);
		}
	}

	private void drawTabIcons() {
		
	}

	private void drawBackground() {
			int guiX = (width - guiWidth) / 2;
			int guiY = (height - guiHeight) / 2;
			GL11.glColor4f(1, 1, 1, 1);
			drawDefaultBackground();
			mc.renderEngine.bindTexture(new ResourceLocation(Strings.MODID, "textures/gui/craftingGui.png"));
			drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	
}
