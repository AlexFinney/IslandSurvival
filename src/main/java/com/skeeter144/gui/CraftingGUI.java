package com.skeeter144.gui;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.skeeter144.main.IslandSurvival;
import com.skeeter144.packets.CraftingMessage;
import com.skeeter144.skills.Recipe;
import com.skeeter144.skills.SkillCrafting;
import com.skeeter144.util.Strings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CraftingGUI extends GuiScreen {

	ResourceLocation textureLoc = new ResourceLocation(Strings.MODID, "textures/gui/craftingGui.png");

	int guiWidth = 256;
	int guiHeight = 192;

	int iconWidth = 16;
	int iconHeight = 16;

	int tooltipWidth = 74;
	int tooltipHeight = 64;

	Rectangle2D currentHoverRect = null;
	int curHoverX = 0;
	int curHoverY = 0;

	static ArrayList<GuiRecipeSlot> slots;

	public CraftingGUI() {

	}

	@Override
	public void drawScreen(int x, int y, float ticks) {

		drawBackground();
		drawTabIcons();
		drawCraftingIcons();
		drawItemInfo();

	}

	private void drawItemInfo() {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		for(int i = 0; i < slots.size(); ++i){
			if(slots.get(i).containsPoint(getAdjustedMousePosition())){
				fontRendererObj.drawSplitString(slots.get(i).getRecipe().getName(),  guiX + 170, guiY + 10, 50, 0xFFFFFF);
				
				ArrayList<ItemStack> rr = slots.get(i).getRecipe().getResourcesRequired();
				
				for(int j = 0; j < rr.size(); ++j){
					final ItemStack stack =	rr.get(j);
					
					int levelRequired = slots.get(i).getRecipe().getLevelRequired();
					int expGiven = slots.get(i).getRecipe().getExpGiven();
					
					String str = stack.getDisplayName() + " x " + stack.stackSize;
					
					IslandSurvival.instance.getPlayerLevelsDatabase().getPlayerLevels(player.getPersistentID());
						
					
						
						
					fontRendererObj.drawString(str, guiX + 168, guiY + 60 + j * fontRendererObj.FONT_HEIGHT + 4, 0xFFFFFF);
					fontRendererObj.drawString("Needed Level: " + levelRequired, guiX + 168, guiY + (guiHeight - 30), 0xFFFFFF);
					fontRendererObj.drawString("Exp Given: " + expGiven, guiX + 168, guiY + (guiHeight - 30) + fontRendererObj.FONT_HEIGHT + 2, 0xFFFF00);
				
				}
			
			}
		}
		
		renderIcons();
		
	}
	
	private void craft(Recipe recipe){
		IslandSurvival.network.sendToServer(new CraftingMessage(recipe.getId()));
	}

	private void renderIcons() {
		for(int i = 0; i < slots.size(); ++i){
			ItemStack result = SkillCrafting.getRecipes().get(i).getResult();
			Item resultItem = result.getItem();
			
			RenderItem render = new RenderItem();
			render.renderItemAndEffectIntoGUI(resultItem.getFontRenderer(result), mc.getTextureManager(), result,
					(int)slots.get(i).getX(), (int)slots.get(i).getY());
		}
	}

	private void drawCraftingIcons() {
		slots = new ArrayList<GuiRecipeSlot>();
		
		ArrayList<Recipe> recipes = SkillCrafting.getRecipes();

		
		
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;

		int currentColumn = 0;
		int currentRow = 0;
	
		for (int i = 0; i < recipes.size(); ++i) {
	
			

			if (currentColumn * 16 + currentColumn * 4 + 10 > guiWidth) {
				currentRow++;
				currentColumn = 0;
			}

			slots.add(new GuiRecipeSlot(recipes.get(i), guiX + 10 + currentColumn * 16 + currentColumn * 4,
					guiY + 10 + currentRow * 16 + currentRow * 4, 16, 16));
			
			++currentColumn;	
		
		}//end for loop		
		
	}

	@Override
	protected void mouseClicked(int x, int y, int button) {
		if(button == 0){
			
			for(int i = 0; i < slots.size(); ++i){
				if(slots.get(i).containsPoint(getAdjustedMousePosition())){
					Recipe recipe = slots.get(i).getRecipe();
					IslandSurvival.network.sendToServer(new CraftingMessage(recipe.getId()));
					System.out.println("clicked!");
				}
			}
			
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

	@Override
	protected void keyTyped(char c, int key) {
		switch (key) {
		case Keyboard.KEY_E:
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
			break;
		}

		super.keyTyped(c, key);
	}
	
	private Point2D.Double getAdjustedMousePosition(){		
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		
		final int width = sr.getScaledWidth();
		final int height = sr.getScaledHeight();
		final int mouseX = Mouse.getX() * width / mc.displayWidth;
		final int mouseY = (height - Mouse.getY() * height / mc.displayHeight - 1);
		return new Point2D.Double(mouseX,mouseY);
	}
	
	public void notifyOfCanCraft(boolean bool){
		
	}

}
