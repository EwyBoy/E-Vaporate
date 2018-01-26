package com.ewyboy.idk.common.items;

import com.ewyboy.bibliotheca.common.interfaces.IItemRenderer;
import com.ewyboy.idk.ModOff;
import com.ewyboy.idk.common.loaders.CreativeTabLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by EwyBoy
 */
public class ItemVape extends ItemFood implements IItemRenderer {

    public ItemVape() {
        super(0, 0.0f, false);
        this.setHasSubtypes(true);
        setMaxStackSize(1);
        setMaxDamage(16);
        setDamage(new ItemStack(this), 0);
        setCreativeTab(CreativeTabLoader.tabIDK);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 64;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 10.0f, world.rand.nextFloat() + 1.0f * 0.9f);
        return super.onItemRightClick(world, playerIn, handIn);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            stack.setItemDamage(stack.getItemDamage() + 1);
        }
        return stack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        World world = Minecraft.getMinecraft().world;
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        Random random = new Random();
        Vec3d vec3d = new Vec3d(((double)random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
        vec3d = vec3d.rotatePitch(-player.rotationPitch * 0.017453292F);
        vec3d = vec3d.rotateYaw(-player.rotationYaw * 0.017453292F);
        double d0 = (double)(-random.nextFloat()) * 0.6D - 0.3D;
        Vec3d vec3d1 = new Vec3d(((double)random.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
        vec3d1 = vec3d1.rotatePitch(-player.rotationPitch * 0.017453292F);
        vec3d1 = vec3d1.rotateYaw(-player.rotationYaw * 0.017453292F);
        vec3d1 = vec3d1.addVector(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ);

        if (world.isRemote) {
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, vec3d1.x, vec3d1.y + 0.95, vec3d1.z, vec3d.x, 0, vec3d.z);
            ModOff.proxy.spawnVapeSmoke(world,  vec3d1.x, vec3d1.y + 0.95, vec3d1.z, 0,0,0, random.nextInt(4) + 1);
        }

        return EnumAction.DRINK;
    }

    @Override
    public int[] modelMetas() {
        return new int[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemRenderer() {
        ModelResourceLocation model0 = new ModelResourceLocation(getRegistryName() + "_empty", "inventory");
        ModelResourceLocation model1 = new ModelResourceLocation(getRegistryName() + "_full", "inventory");
        ModelBakery.registerItemVariants(this, model0, model1);
        ModelLoader.setCustomMeshDefinition(this, stack -> stack.getItemDamage() == 0 ? model0 : model1);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < 2; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }
}
