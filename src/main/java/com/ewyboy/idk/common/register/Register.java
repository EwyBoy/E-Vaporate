package com.ewyboy.idk.common.register;

import com.ewyboy.idk.common.blocks.BlockBlender;
import com.ewyboy.idk.common.items.ItemVape;
import com.ewyboy.idk.common.tiles.TileBlender;

/**
 * Created by EwyBoy
 */
public class Register {

    public static final class Blocks {
        public static BlockBlender blender = new BlockBlender();
    }

    public static final class Items {
        public static ItemVape vape = new ItemVape();
    }

    public static final class Tiles {
        public static TileBlender tileBlender = new TileBlender();
    }
}
