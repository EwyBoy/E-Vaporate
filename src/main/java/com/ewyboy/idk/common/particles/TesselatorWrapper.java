package com.ewyboy.idk.common.particles;

/**
 * Created by EwyBoy
 */
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;

public class TesselatorWrapper {

    private Tessellator tess;

    protected TesselatorWrapper() {
        this(Tessellator.getInstance());
    }

    protected TesselatorWrapper(Tessellator tess) {
        this.tess = tess;
    }

    public static TesselatorWrapper getInstance() {
        return new TesselatorWrapper();
    }

    public TesselatorWrapper begin(int mode, VertexFormat format) {
        tess.getBuffer().begin(mode, format);
        return this;
    }

    public TesselatorWrapper startDrawing(int mode) {
        begin(mode, DefaultVertexFormats.POSITION_TEX);
        return this;
    }

    public TesselatorWrapper startDrawingWithColor(int mode) {
        begin(mode, DefaultVertexFormats.POSITION_TEX_COLOR);
        return this;
    }

    public TesselatorWrapper startDrawingQuads() {
        startDrawing(GL11.GL_QUADS);
        return this;
    }

    public TesselatorWrapper startDrawingQuadsWithColor() {
        startDrawingWithColor(GL11.GL_QUADS);
        return this;
    }

    public TesselatorWrapper addVertexWithUV(double x, double y, double z, double u, double v) {
        tess.getBuffer().pos(x, y, z).tex(u, v).endVertex();
        return this;
    }

    public TesselatorWrapper addVertex(double x, double y, double z) {
        tess.getBuffer().pos(x, y, z).endVertex();
        return this;
    }

    public TesselatorWrapper draw() {
        tess.draw();
        return this;
    }

    public TesselatorWrapper setColorRGBA_F(float r, float g, float b, float a) {
        tess.getBuffer().color(r,g,b,a);
        return this;
    }

    public TesselatorWrapper setColorOpaque_I(int color) {
        float cR = (float)((color & 0xFF0000) >> 16) / 0xff;
        float cG = (float)((color & 0xFF00) >> 8) / 0xff;
        float cB = (float)((color & 0xFF)) / 0xff;

        tess.getBuffer().color(cR,cG,cB,1);
        return this;
    }

    public TesselatorWrapper setColorRGBA_I(int color, int alpha) {
        float cR = (float)((color & 0xFF0000) >> 16) / 0xff;
        float cG = (float)((color & 0xFF00) >> 8) / 0xff;
        float cB = (float)((color & 0xFF)) / 0xff;

        tess.getBuffer().color(cR,cG,cB,(float)alpha/255);
        return this;
    }

    public TesselatorWrapper setTranslation(double x, double y, double z) {
        tess.getBuffer().putPosition(x, y, z);
        return this;
    }

    public TesselatorWrapper setNormal(float x, float y, float z) {
        tess.getBuffer().normal(x, y, z);
        return this;
    }
}
