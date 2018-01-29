package com.ewyboy.idk;

import com.ewyboy.idk.proxy.CommonProxy;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.ewyboy.idk.Reference.Info.*;
import static com.ewyboy.idk.Reference.Path.CLIENT_PROXY;
import static com.ewyboy.idk.Reference.Path.COMMON_PROXY;

@Mod(modid = MOD_ID,  name = NAME, version = VERSION, dependencies = DEPENDENCIES)
public class ModOff {

    public ModOff() {
        FluidRegistry.enableUniversalBucket();
    }

    static { FluidRegistry.enableUniversalBucket(); }

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}

