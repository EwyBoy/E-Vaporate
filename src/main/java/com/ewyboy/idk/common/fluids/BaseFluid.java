package com.ewyboy.idk.common.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import static com.ewyboy.idk.Reference.Info.MOD_ID;

/**
 * Created by EwyBoy
 */
public class BaseFluid extends Fluid {

    public BaseFluid(String fluidName, int viscosity, int density, int luminosity) {
        super(fluidName,
                new ResourceLocation(MOD_ID + ":" + "blocks" + "/" + fluidName + "_still"),
                new ResourceLocation(MOD_ID + ":" + "blocks" + "/" + fluidName + "_flow")
        );
        this.setViscosity(viscosity);
        this.setDensity(density);
        this.setLuminosity(luminosity);
        FluidRegistry.registerFluid(this);
    }
}