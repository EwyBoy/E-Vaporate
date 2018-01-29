package com.ewyboy.idk.common.register;

import com.ewyboy.idk.common.fluids.BaseFluidBlock;
import com.ewyboy.idk.common.blocks.BlockBlender;
import com.ewyboy.idk.common.items.ItemVape;
import com.ewyboy.idk.common.loaders.FluidLoader;
import com.ewyboy.idk.common.tiles.TileBlender;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

/**
 * Created by EwyBoy
 */
public class Register {

    public static final class Blocks {
        public static BlockBlender blender = new BlockBlender();
        public static BlockFluidClassic liquid_vape = new BaseFluidBlock(FluidLoader.LIQUID_VAPE, Material.LAVA);
    }

    public static final class Items {
        public static ItemVape vape = new ItemVape();
    }

    public static final class Tiles {
        public static TileBlender tileBlender = new TileBlender();
    }
}
