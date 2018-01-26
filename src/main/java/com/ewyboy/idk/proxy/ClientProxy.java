package com.ewyboy.idk.proxy;

import com.ewyboy.idk.common.particles.VapeParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by EwyBoy
 */
public class ClientProxy extends CommonProxy {

    public Side getSide(){return Side.CLIENT;}

    @Override
    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void spawnVapeSmoke(World w, double x, double y,double z, double vx, double vy,double vz, float scale) {
        //ParticleHelper.spawnParticle(new VapeParticle(w, x, y, z, vx, vy, vz));
        Minecraft.getMinecraft().effectRenderer.addEffect(new VapeParticle(w, x, y, z, vx, vy, vz, scale));
    }

}

