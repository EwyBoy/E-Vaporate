package com.ewyboy.idk.client;

import com.ewyboy.idk.common.blocks.BlockBlender;
import com.ewyboy.idk.common.tiles.TileBlender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidTank;

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
                renderFluid(te);
            GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    private void renderFluid(TileBlender te) {
        if (te.getTank() != null) {
            final FluidTank fluid = te.tank;
            if (fluid != null &&  fluid.getFluid() != null && fluid.getFluidAmount() > 0) {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                FluidRenderHelper.renderFluid(te, fluid.getFluid(), te.getPos(),
                        0.375d, 0.45d, 0.375d,
                        0.00d, 0.00d, 0.00d,
                        0.25d, 0.40d, 0.25d
                );
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        }
    }

    private void renderItem(TileBlender te) {
        ItemStack stack = te.getStack();
        if (!stack.isEmpty()) {
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableLighting();
            GlStateManager.pushMatrix();
                GlStateManager.translate(.5, .65, .5);
                if (te.getWorld().getBlockState(te.getPos()).getValue(BlockBlender.ENABLED)) {
                    long angle = (System.currentTimeMillis() * 3) % 360;
                    GlStateManager.rotate(angle, 0, 1, 0);
                }
                if (stack.getItem() instanceof ItemBlock) {
                    GlStateManager.scale(0.17, 0.17, 0.17);
                } else {
                    GlStateManager.scale(.275f, .275f, .275f);
                }
                Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.popMatrix();
        }
    }
}
