package com.ewyboy.idk.common.fluids;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by EwyBoy
 */
public class BaseFluidBlock extends BlockFluidClassic {

    public BaseFluidBlock(Fluid fluid, Material material) {
        super(fluid, material);
    }
}