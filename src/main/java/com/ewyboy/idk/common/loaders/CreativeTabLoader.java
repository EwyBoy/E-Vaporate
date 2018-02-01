package com.ewyboy.idk.common.loaders;

import com.ewyboy.idk.Reference;
import com.ewyboy.idk.common.register.Register;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * Created by EwyBoy
 */
public class CreativeTabLoader {

    public static CreativeTabs tabIDK = new CreativeTabs (Reference.Info.MOD_ID) {
        public ItemStack getIconItemStack() {
            return new ItemStack(Register.Items.vape);
        }
        @Override
        public ItemStack getTabIconItem() {return null;}
    };

}
