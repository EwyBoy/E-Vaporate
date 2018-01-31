package com.ewyboy.idk.common.loaders;

import com.ewyboy.idk.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
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
             event.getMap().registerSprite(new ResourceLocation(Reference.Info.MOD_ID + ":" + "blocks/liquid_vape_flow"));
             event.getMap().registerSprite(new ResourceLocation(Reference.Info.MOD_ID + ":" + "particles/smoke"));
         }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerSounds(RegistryEvent.Register<SoundEvent> sounds) {
        SoundLoader.init(sounds.getRegistry());
    }

}
