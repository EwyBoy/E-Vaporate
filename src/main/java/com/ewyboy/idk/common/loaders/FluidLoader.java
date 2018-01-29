package com.ewyboy.idk.common.loaders;

import com.ewyboy.idk.common.fluids.BaseFluid;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by EwyBoy
 */
public class FluidLoader {

    public static Fluid LIQUID_VAPE;

    public static void init() {
        LIQUID_VAPE = new BaseFluid("liquid_vape", 0, 200, 15);
        LIQUID_VAPE = FluidRegistry.getFluid("liquid_vape");
        if (!FluidRegistry.getBucketFluids().contains(LIQUID_VAPE)) FluidRegistry.addBucketForFluid(LIQUID_VAPE);
    }

}
