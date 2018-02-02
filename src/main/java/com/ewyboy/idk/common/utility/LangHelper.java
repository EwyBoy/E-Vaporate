package com.ewyboy.idk.common.utility;

import net.minecraft.client.resources.I18n;

/**
 * Created by EwyBoy
 */
public class LangHelper {

    public static String lang(String key) {
        return I18n.format("idk." + key);
    }
}
