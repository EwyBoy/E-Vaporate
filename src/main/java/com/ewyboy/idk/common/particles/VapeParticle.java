package com.ewyboy.idk.common.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

/**
 * Created by EwyBoy
 */
public class VapeParticle extends ParticleSmokeNormal {

    private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
    private static final ResourceLocation texture = new ResourceLocation("idk","textures/particles/smoke.png");

    public VapeParticle(World world, double x, double y, double z, double velX, double velY, double velZ, float scale) {
        super(world, x, y, z, velX, velY,velZ, scale);
        this.particleAlpha = 0.99F;
    }

    public VapeParticle(World world, double x, double y, double z, double velX, double velY, double velZ, float scale, double r, double g, double b) {
        this(world, x, y, z, velX, velY, velZ, scale);
        this.particleRed = (float) r;
        this.particleGreen = (float) g;
        this.particleBlue = (float) b;
    }

    @Override
    public boolean shouldDisableDepth() {
        return true;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entity, float x, float y, float z, float velX, float velY, float velZ) {
        TesselatorWrapper.getInstance().draw().begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GlStateManager.pushMatrix();
        //GlStateManager.disableAlpha();
        boolean enabled = GL11.glIsEnabled(GL11.GL_BLEND);
        GlStateManager.enableBlend();
        super.renderParticle(buffer, entity, x, y, z, velX, velY, velZ);
        TesselatorWrapper.getInstance().draw().begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        Minecraft.getMinecraft().renderEngine.bindTexture(particleTextures);
        if(!enabled) GlStateManager.disableBlend();
        //GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
    }
}