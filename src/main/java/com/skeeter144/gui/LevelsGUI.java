package com.skeeter144.gui;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.skeeter144.database.PlayerLevelsDatabase;
import com.skeeter144.main.IslandSurvival;
import com.skeeter144.skills.SkillsMain;
import com.skeeter144.util.Strings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class LevelsGUI extends GuiScreen {
	
	ResourceLocation iconsGuiLoc = new ResourceLocation(Strings.MODID, "textures/gui/levelsGui.png");
	ResourceLocation toolTipLoc = new ResourceLocation(Strings.MODID, "textures/gui/toolTipGui.png");
	
	int guiWidth = 256;
	int guiHeight = 192;
	
	int iconWidth = 32;
	int iconHeight = 32;
	
	int tooltipWidth = 74;
	int tooltipHeight = 64;
	
	Rectangle2D currentHoverRect = null;
	int curHoverX = 0;
	int curHoverY = 0;
	String hoverName = "";
	
	//two pixel space between images + 32 pixels in width
	Rectangle2D miningRect = new Rectangle2D.Double(12,12,iconWidth,iconHeight);
	Rectangle2D attackRect = new Rectangle2D.Double(12 + 1 * iconWidth + 2 * 1 ,12,iconWidth,iconHeight);
	Rectangle2D defenseRect = new Rectangle2D.Double(12 + 2 * iconWidth + 2 * 2, 12, iconWidth,iconHeight);
	
	
	@Override
	public void drawScreen(int x, int y, float ticks) {
		
		
		drawBackground();
		drawLevelIcons();
		
		
		
	}
	
	
	
	private void drawToolTip() {
		if(currentHoverRect == null)
			return;
		
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		PlayerLevelsDatabase db = IslandSurvival.instance.getPlayerLevelsDatabase();
		int level = 0;
		int exp = 0;
		int expRem = 0;
		
		switch(hoverName){
			case Strings.MINING:				
				exp = db.getPlayerLevels(player.getPersistentID()).getMiningExp();				
				break;
			case Strings.ATTACK:
				exp = db.getPlayerLevels(player.getPersistentID()).getAttackExp();	
				break;
			case Strings.DEFENSE:
				exp = db.getPlayerLevels(player.getPersistentID()).getDefenseExp();	
				break;
		}
		
		level = SkillsMain.getLevelForExp(exp);
		expRem = SkillsMain.getExpToNextLevel(level, exp);	
		
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		
		int toolTipX = guiX - tooltipWidth - 5;
		int toolTipY = guiY + guiHeight / 4;
		
		DecimalFormat formatter = new DecimalFormat("#,###");
		
		mc.renderEngine.bindTexture(toolTipLoc);
		drawTexturedModalRect(guiX - tooltipWidth - 5, guiY +  guiHeight / 4 , 0, 0, tooltipWidth, tooltipHeight);
		fontRendererObj.drawString(hoverName, toolTipX + 5, toolTipY  + 5, 0xFFFFFF);
		
		fontRendererObj.drawString(Strings.LEVEL, toolTipX + 5, toolTipY + 20, 0xFFFFFF);
		fontRendererObj.drawString(Integer.toString(level), toolTipX + 37, toolTipY + 20, 0x00FF00);
		
		fontRendererObj.drawString(Strings.EXP , toolTipX + 5, toolTipY + 31, 0xFFFFFF);
		fontRendererObj.drawString(formatter.format((double)exp), toolTipX + 26, toolTipY + 31, 0xFFFF00);
		
		fontRendererObj.drawString(Strings.EXP_TO, toolTipX + 5, toolTipY + 42, 0xFFFFFF);
		fontRendererObj.drawString(formatter.format((double)expRem), toolTipX + 27, toolTipY + 51, 0x0000FF);
		
		fontRendererObj.drawString("dsfsdf", 0, 0, 0xFFFFFF);
		
	}



	private void drawLevelIcons() {
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		currentHoverRect = null;
		curHoverX = 0;
		curHoverY = 0;
		hoverName = "";
				
		if(miningRect.contains(getAdjustedMousePosition())){
			drawTexturedModalRect((int)miningRect.getX() + guiX, (int)miningRect.getY() + guiY, 0, 224, (int)miningRect.getWidth(), (int)miningRect.getHeight());
			currentHoverRect = miningRect;
			curHoverX = (int)miningRect.getX() + guiX;
			curHoverY = (int)miningRect.getY() + guiY;
			hoverName = Strings.MINING;
		}else{
			drawTexturedModalRect((int)miningRect.getX() + guiX, (int)miningRect.getY() + guiY, 0, 192, (int)miningRect.getWidth(), (int)miningRect.getHeight());
		}
		
		if(attackRect.contains(getAdjustedMousePosition())){
			drawTexturedModalRect((int)attackRect.getX() + guiX, (int)attackRect.getY() + guiY, 32, 224, (int)attackRect.getWidth(), (int)attackRect.getHeight());
			currentHoverRect = attackRect;
			curHoverX = (int)attackRect.getX() + guiX;
			curHoverY = (int)attackRect.getY() + guiY;
			hoverName = Strings.ATTACK;
		}else{
			drawTexturedModalRect((int)attackRect.getX() + guiX, (int)attackRect.getY() + guiY, 32, 192, (int)attackRect.getWidth(), (int)attackRect.getHeight());
		}
		
		if(defenseRect.contains(getAdjustedMousePosition())){
			drawTexturedModalRect((int)defenseRect.getX() + guiX, (int)defenseRect.getY() + guiY, 64, 224, (int)defenseRect.getWidth(), (int)attackRect.getHeight());
			currentHoverRect = defenseRect;
			curHoverX = (int)defenseRect.getX() + guiX;
			curHoverY = (int)defenseRect.getY() + guiY;
			hoverName = Strings.DEFENSE;
		}else{
			drawTexturedModalRect((int)defenseRect.getX() + guiX, (int)defenseRect.getY() + guiY, 64, 192, (int)attackRect.getWidth(), (int)defenseRect.getHeight());
		}
		drawToolTip();
	}

	

	private void drawBackground(){
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		GL11.glColor4f(1, 1, 1, 1);
		drawDefaultBackground();
		mc.renderEngine.bindTexture(new ResourceLocation(Strings.MODID, "textures/gui/levelsGui.png"));
		drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
	}
	
	
	@Override
	public void initGui() {
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		
		
		
		super.initGui();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	protected void keyTyped(char c, int key) {	
		switch(key){
			case Keyboard.KEY_E:
			case Keyboard.KEY_L:
			 	this.mc.displayGuiScreen((GuiScreen)null);
	            this.mc.setIngameFocus();
	            break;
		}
		
		super.keyTyped(c, key);
	}
	
	private Point2D.Double getAdjustedMousePosition(){		
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		
		final int width = sr.getScaledWidth();
		final int height = sr.getScaledHeight();
		final int guiLeft = (width - this.guiWidth) / 2;
		final int guiTop = (height - this.guiHeight) / 2;
		final int mouseX = (Mouse.getX() * width / mc.displayWidth) - guiLeft;
		final int mouseY = (height - Mouse.getY() * height / mc.displayHeight - 1) - guiTop;
		return new Point2D.Double(mouseX,mouseY);
	}

	
}
