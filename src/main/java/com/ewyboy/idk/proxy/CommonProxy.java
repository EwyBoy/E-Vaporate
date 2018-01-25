package com.ewyboy.idk.proxy;

import com.ewyboy.bibliotheca.common.compatibilities.CompatibilityHandler;
import com.ewyboy.bibliotheca.common.loaders.BlockLoader;
import com.ewyboy.bibliotheca.common.loaders.ItemLoader;
import com.ewyboy.bibliotheca.common.loaders.TileEntityLoader;
import com.ewyboy.idk.Reference;
import com.ewyboy.idk.common.register.Register;
import com.ewyboy.idk.common.loaders.ConfigLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by EwyBoy
 */
public class CommonProxy {

    public Side getSide(){return Side.SERVER;}

    public void preInit(FMLPreInitializationEvent event) {
        CompatibilityHandler.registerWaila();
        ConfigLoader.registerConfig(event.getSuggestedConfigurationFile());
        BlockLoader.init(Reference.Info.MOD_ID, Register.Blocks.class);
        ItemLoader.init(Reference.Info.MOD_ID, Register.Items.class);
        TileEntityLoader.init(Register.Tiles.class);
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) { }

}
