package com.ewyboy.idk.common.loaders;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

/**
 * Created by EwyBoy
 */
public class ConfigLoader {

    public static Configuration config;

    public static void registerConfig(File file) {
        config = new Configuration(file);

        config.load();
        //Config here
        if (config.hasChanged()) config.save();
    }
}
