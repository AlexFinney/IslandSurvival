package com.skeeter144.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLion extends ModelBase
{
  //fields
    ModelRenderer Tail3;
    ModelRenderer Mane;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer Paw1;
    ModelRenderer Paw2;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer Paw3;
    ModelRenderer Paw4;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
  
  public ModelLion()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Tail3 = new ModelRenderer(this, 0, 57);
      Tail3.addBox(0F, 0F, 0F, 2, 2, 2);
      Tail3.setRotationPoint(-0.5F, 8F, 22F);
      Tail3.setTextureSize(64, 64);
      Tail3.mirror = true;
      setRotation(Tail3, -0.5759587F, 0F, 0F);
      Mane = new ModelRenderer(this, 0, 46);
      Mane.addBox(0F, 0F, 0F, 15, 15, 3);
      Mane.setRotationPoint(-7F, -5F, -10F);
      Mane.setTextureSize(64, 64);
      Mane.mirror = true;
      setRotation(Mane, 0F, 0F, 0F);
      head = new ModelRenderer(this, 0, 31);
      head.addBox(-4F, -4F, -6F, 8, 8, 6);
      head.setRotationPoint(0F, 2F, -8F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 0, 0);
      body.addBox(-6F, -10F, -7F, 12, 18, 10);
      body.setRotationPoint(0F, 3F, 2F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, 1.570796F, 0F, 0F);
      Paw1 = new ModelRenderer(this, 48, 58);
      Paw1.addBox(0F, 0F, 0F, 4, 2, 4);
      Paw1.setRotationPoint(-6F, 20F, -10F);
      Paw1.setTextureSize(64, 64);
      Paw1.mirror = true;
      setRotation(Paw1, 0F, 0F, 0F);
      Paw2 = new ModelRenderer(this, 48, 58);
      Paw2.addBox(0F, 0F, 0F, 4, 2, 4);
      Paw2.setRotationPoint(2F, 20F, -10F);
      Paw2.setTextureSize(64, 64);
      Paw2.mirror = true;
      setRotation(Paw2, 0F, 0F, 0F);
      leg1 = new ModelRenderer(this, 48, 36);
      leg1.addBox(-3F, 0F, -2F, 4, 12, 4);
      leg1.setRotationPoint(-3F, 10F, 7F);
      leg1.setTextureSize(64, 64);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0F, 0F);
      leg2 = new ModelRenderer(this, 48, 36);
      leg2.addBox(-1F, 0F, -2F, 4, 12, 4);
      leg2.setRotationPoint(3F, 10F, 7F);
      leg2.setTextureSize(64, 64);
      leg2.mirror = true;
      setRotation(leg2, 0F, 0F, 0F);
      leg3 = new ModelRenderer(this, 48, 36);
      leg3.addBox(-3F, 0F, -3F, 4, 12, 4);
      leg3.setRotationPoint(-3F, 10F, -5F);
      leg3.setTextureSize(64, 64);
      leg3.mirror = true;
      setRotation(leg3, 0F, 0F, 0F);
      leg4 = new ModelRenderer(this, 48, 36);
      leg4.addBox(-1F, 0F, -3F, 4, 12, 4);
      leg4.setRotationPoint(3F, 10F, -5F);
      leg4.setTextureSize(64, 64);
      leg4.mirror = true;
      setRotation(leg4, 0F, 0F, 0F);
      Paw3 = new ModelRenderer(this, 48, 58);
      Paw3.addBox(0F, 0F, 0F, 4, 2, 4);
      Paw3.setRotationPoint(-6F, 20F, 3F);
      Paw3.setTextureSize(64, 64);
      Paw3.mirror = true;
      setRotation(Paw3, 0F, 0F, 0F);
      Paw4 = new ModelRenderer(this, 48, 58);
      Paw4.addBox(0F, 0F, 0F, 4, 2, 4);
      Paw4.setRotationPoint(2F, 20F, 3F);
      Paw4.setTextureSize(64, 64);
      Paw4.mirror = true;
      setRotation(Paw4, 0F, 0F, 0F);
      Tail1 = new ModelRenderer(this, 45, 0);
      Tail1.addBox(0F, 0F, 0F, 1, 1, 8);
      Tail1.setRotationPoint(0F, 0F, 10F);
      Tail1.setTextureSize(64, 64);
      Tail1.mirror = true;
      setRotation(Tail1, -0.4363323F, 0F, 0F);
      Tail2 = new ModelRenderer(this, 45, 0);
      Tail2.addBox(0F, 0F, 0F, 1, 1, 8);
      Tail2.setRotationPoint(0F, 3.4F, 17.2F);
      Tail2.setTextureSize(64, 64);
      Tail2.mirror = true;
      setRotation(Tail2, -0.8203047F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Tail3.render(f5);
    Mane.render(f5);
    head.render(f5);
    body.render(f5);
    Paw1.render(f5);
    Paw2.render(f5);
    leg1.render(f5);
    leg2.render(f5);
    leg3.render(f5);
    leg4.render(f5);
    Paw3.render(f5);
    Paw4.render(f5);
    Tail1.render(f5);
    Tail2.render(f5);
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
  }

}
