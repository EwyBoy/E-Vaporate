package com.ewyboy.idk.common.loaders;

import com.ewyboy.idk.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by EwyBoy
 */
public class EventLoader {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
      public void textureHook(TextureStitchEvent.Pre event) {
         if (event.getMap().equals(Minecraft.getMinecraft().getTextureMapBlocks())) {
             event.getMap().registerSprite(new ResourceLocation(Reference.Info.MOD_ID + ":" + "blocks/power_animation"));
             event.getMap().registerSprite(new ResourceLocation(Reference.Info.MOD_ID + ":" + "particles/smoke"));
         }
    }

}
