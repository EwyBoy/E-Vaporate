package com.ewyboy.idk.proxy;

import com.ewyboy.bibliotheca.common.compatibilities.CompatibilityHandler;
import com.ewyboy.bibliotheca.common.loaders.BlockLoader;
import com.ewyboy.bibliotheca.common.loaders.ItemLoader;
import com.ewyboy.bibliotheca.common.loaders.TileEntityLoader;
import com.ewyboy.idk.Reference;
import com.ewyboy.idk.common.loaders.ConfigLoader;
import com.ewyboy.idk.common.loaders.EventLoader;
import com.ewyboy.idk.common.loaders.FluidLoader;
import com.ewyboy.idk.common.register.Register;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by EwyBoy
 */
public class CommonProxy {

    public Side getSide() {
        return Side.SERVER;
    }

    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventLoader());
        CompatibilityHandler.registerWaila();
        ConfigLoader.registerConfig(event.getSuggestedConfigurationFile());
        FluidLoader.init();
        BlockLoader.init(Reference.Info.MOD_ID, Register.Blocks.class);
        ItemLoader.init(Reference.Info.MOD_ID, Register.Items.class);
        TileEntityLoader.init(Register.Tiles.class);
    }

    public void init(FMLInitializationEvent event) { }

    public void postInit(FMLPostInitializationEvent event) { }

    public boolean shiftPressed() { return false; }

    public boolean ctrlPressed() { return false; }

    public void spawnVapeSmoke(World w, double x, double y, double z, double vx, double vy, double vz, float scale) { }

    public void spawnVapeSmokeRGB(World w, double x, double y,double z, double vx, double vy,double vz, float scale, double R, double G, double B) { }

    public void initFluidModels() {}
}