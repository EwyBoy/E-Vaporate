package com.ewyboy.idk.client;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by EwyBoy
 */
public class ColorHelper {

    public static int getColorForVape(ItemStack stack, boolean isRenderer) {
        Item item = stack.getItem();
        if (item == Items.CARROT) {
            return isRenderer ? 0xffff6e00 : 0xff6e00;
        } else if ((item == Items.APPLE) || (item == Items.MELON)) {
            return isRenderer ? 0xffff4747 : 0xff4747;
        } else if (item == Items.BEETROOT || item == Items.SPIDER_EYE) {
            return isRenderer ? 0xff511212 : 0x511212;
        } else if (item == Items.MUSHROOM_STEW || item == Items.RABBIT_STEW) {
            return isRenderer ? 0xff966b5a : 0x966b5a;
        } else if (item == Items.BREAD) {
            return isRenderer ? 0xff51360e : 0x51360e;
        } else if (item == Items.COOKIE) {
            return isRenderer ? 0xff99600d : 0x99600d;
        } else if (item == Items.PORKCHOP || item == Items.CHICKEN || item == Items.RABBIT) {
            return isRenderer ? 0xffc49a6b : 0xc49a6b;
        } else if (item == Items.COOKED_CHICKEN || item == Items.COOKED_PORKCHOP || item == Items.COOKED_RABBIT || item == Items.ROTTEN_FLESH) {
            return isRenderer ? 0xff825624 : 0x825624;
        } else if (item == Items.FISH) {
            return isRenderer ? 0xff4f8996 : 0x4f8996;
        } else if (item == Items.COOKED_FISH) {
            return isRenderer ? 0xff29484f : 0x29484f;
        } else if (item == Items.MUTTON || item == Items.BEEF) {
            return isRenderer ? 0xffa33528 : 0xa33528;
        } else if (item == Items.COOKED_MUTTON || item == Items.COOKED_BEEF) {
            return isRenderer ? 0xff331a08 : 0x331a08;
        } else if (item == Items.CAKE) {
            return isRenderer ? 0xffcca488 : 0xcca488;
        } else if (item == Items.POTATO) {
            return isRenderer ? 0xff82573a : 0x82573a;
        } else if (item == Items.PUMPKIN_PIE) {
            return isRenderer ? 0xff964e36 : 0x964e36;
        }
        return isRenderer ? 0xffffffff : 0xffffff;
    }

}
