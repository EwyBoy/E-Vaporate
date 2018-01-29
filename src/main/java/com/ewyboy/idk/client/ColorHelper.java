package com.ewyboy.idk.client;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by EwyBoy
 */
public class ColorHelper {

    public static int getColorForVape(ItemStack stack) {
        Item item = stack.getItem();

        if (item == Items.CARROT) {
            return 0xff00ff00;
        } else if ((item == Items.APPLE) || (item == Items.MELON)) {
            return 0xff00ff00;
        } else if (item == Items.BEETROOT) {
            return 0xff00ff00;
        }

        return 0xff00ff00;
    }

}
