package com.skeeter144.mobs.models;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPenguin extends ModelBase
{
  //fields
    ModelRenderer leftWing;
    ModelRenderer rightWing;
    ModelRenderer beak;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightLeg;
    ModelRenderer leftLeg;
  
  public ModelPenguin()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      leftWing = new ModelRenderer(this, 40, 16);
      leftWing.addBox(0F, 0F, 0F, 1, 12, 4);
      leftWing.setRotationPoint(3F, 8F, -2F);
      leftWing.setTextureSize(64, 64);
      leftWing.mirror = true;
      setRotation(leftWing, 0F, 0F, -0.2216568F);
      rightWing = new ModelRenderer(this, 40, 16);
      rightWing.addBox(0F, 0F, 0F, 1, 12, 4);
      rightWing.setRotationPoint(-4F, 8F, -2F);
      rightWing.setTextureSize(64, 64);
      rightWing.mirror = true;
      setRotation(rightWing, 0F, 0F, 0.2216568F);
      beak = new ModelRenderer(this, 30, 0);
      beak.addBox(0F, 0F, 0F, 2, 1, 1);
      beak.setRotationPoint(-1F, 6F, -5F);
      beak.setTextureSize(64, 64);
      beak.mirror = true;
      setRotation(beak, 0F, 0F, 0F);
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 6, 4, 6);
      head.setRotationPoint(1F, 12F, 0F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 16, 16);
      body.addBox(-4F, 0F, -2F, 8, 12, 4);
      body.setRotationPoint(0F, 8F, 0F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightLeg = new ModelRenderer(this, 0, 16);
      rightLeg.addBox(-2F, 0F, -2F, 2, 4, 4);
      rightLeg.setRotationPoint(-2F, 20F, 0F);
      rightLeg.setTextureSize(64, 64);
      rightLeg.mirror = true;
      setRotation(rightLeg, 0F, 0F, 0F);
      leftLeg = new ModelRenderer(this, 0, 16);
      leftLeg.addBox(-2F, 0F, -2F, 2, 4, 4);
      leftLeg.setRotationPoint(4F, 20F, 0F);
      leftLeg.setTextureSize(64, 64);
      leftLeg.mirror = true;
      setRotation(leftLeg, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    leftWing.render(f5);
    rightWing.render(f5);
    beak.render(f5);
    head.render(f5);
    body.render(f5);
    rightLeg.render(f5);
    leftLeg.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.leftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.rightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;  
		this.rightWing.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leftWing.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;  
  
  }

}
