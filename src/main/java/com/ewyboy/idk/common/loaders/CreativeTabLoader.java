package com.ewyboy.idk.common.loaders;

import com.ewyboy.idk.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Created by EwyBoy
 */
public class CreativeTabLoader {

    public static CreativeTabs tabIDK = new CreativeTabs (Reference.Info.MOD_ID) {
        public ItemStack getIconItemStack() {
            return new ItemStack(Blocks.ANVIL);
        }
        @Override
        public ItemStack getTabIconItem() {return null;}
    };

}
