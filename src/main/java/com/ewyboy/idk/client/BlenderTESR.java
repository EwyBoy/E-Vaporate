package com.ewyboy.idk.client;

import com.ewyboy.idk.common.tiles.TileBlender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by EwyBoy
 */
public class BlenderTESR extends TileEntitySpecialRenderer<TileBlender> {

    @Override
    public void render(TileBlender te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushAttrib();
            GlStateManager.pushMatrix();
                GlStateManager.translate(x, y, z);
                GlStateManager.disableRescaleNormal();
                renderItem(te);
            GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    private void renderItem(TileBlender te) {
        ItemStack stack = te.getStack();
        if (!stack.isEmpty()) {
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableLighting();
            GlStateManager.pushMatrix();
                GlStateManager.translate(.5, .65, .5);
                long angle = (System.currentTimeMillis() / 10) % 360;
                GlStateManager.rotate(angle, 0, 1, 0);
                if (stack.getItem() instanceof ItemBlock) {
                    GlStateManager.scale(0.185, 0.185, 0.185);
                } else {
                    GlStateManager.scale(.35f, .35f, .35f);
                }
                Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.popMatrix();
        }
    }
}
