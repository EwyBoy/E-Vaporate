package com.ewyboy.idk.proxy;

import com.ewyboy.idk.common.particles.VapeParticle;
import com.ewyboy.idk.common.register.Register;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import static com.ewyboy.idk.Reference.Info.MOD_ID;

/**
 * Created by EwyBoy
 */
public class ClientProxy extends CommonProxy {

    public Side getSide(){return Side.CLIENT;}

    @Override
    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        initFluidModels();
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
    public void initFluidModels() {
        final ModelResourceLocation fluidLocation = new ModelResourceLocation(MOD_ID + ":" + "fluids", "liquid_vape");
        ModelLoader.setCustomStateMapper(Register.Blocks.liquid_vape, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return fluidLocation;
            }
        });
    }

    @Override
    public boolean shiftPressed() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
    }

    @Override
    public boolean ctrlPressed() {
        return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void spawnVapeSmoke(World w, double x, double y,double z, double vx, double vy,double vz, float scale) {
        Minecraft.getMinecraft().effectRenderer.addEffect(new VapeParticle(w, x, y, z, vx, vy, vz, scale));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void spawnVapeSmokeRGB(World w, double x, double y,double z, double vx, double vy,double vz, float scale, double R, double G, double B) {
        Minecraft.getMinecraft().effectRenderer.addEffect(new VapeParticle(w, x, y, z, vx, vy, vz, scale, R, G, B));
    }

}

