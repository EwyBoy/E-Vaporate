package com.ewyboy.idk.common.loaders;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.ewyboy.idk.Reference.Info.MOD_ID;

/**
 * Created by EwyBoy
 */
public class SoundLoader {

    public static SoundEvent blender;

    public static void init(IForgeRegistry<SoundEvent> registry) {
        blender = registerSound(registry, new ResourceLocation(MOD_ID, "blender"));
    }

    private static SoundEvent registerSound(IForgeRegistry<SoundEvent> registry, ResourceLocation sound) {
        SoundEvent event = new SoundEvent(sound).setRegistryName(sound);
        registry.register(event);
        return event;
    }

}
