package com.ewyboy.idk.common.items;

import com.ewyboy.bibliotheca.common.interfaces.IItemRenderer;
import com.ewyboy.bibliotheca.common.utility.Logger;
import com.ewyboy.idk.ModOff;
import com.ewyboy.idk.common.loaders.CreativeTabLoader;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Created by EwyBoy
 */
public class ItemVape extends ItemFood implements IItemRenderer {

    public int color;

    public ItemVape() {
        super(2, 0.3f, false);
        this.setHasSubtypes(true);
        setMaxStackSize(1);
        setMaxDamage(64);
        this.setDamage(new ItemStack(this), 0);
        setCreativeTab(CreativeTabLoader.tabIDK);
    }

    public void saveFoodPropsToNBT(ItemStack stack, int heal, float saturation, int color) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        nbt.setInteger("heal", heal);
        nbt.setFloat("saturation", saturation);
        nbt.setInteger("color", color);
    }

    private String[] text = new String[] {
            "§b",
            "§2",
            "§3",
            "§4",
            "§5",
            "§6",
            "§9",
            "§a",
            "§b",
            "§c",
            "§d",
            "§e",
    };

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        NBTTagCompound nbt = stack.getTagCompound();
        Random r = new Random();

        if (ModOff.proxy.ctrlPressed()) {
            tooltip.add(text[r.nextInt(10)] + "B" + text[r.nextInt(10)] + "A" + text[r.nextInt(10)] + "M" + text[r.nextInt(10)] + "B" + text[r.nextInt(10)] + "O" + text[r.nextInt(10)] + "O" + text[r.nextInt(10)] + "Z" + text[r.nextInt(10)] + "L" + text[r.nextInt(10)] + "E" + text[r.nextInt(10)] + "D");
            tooltip.add("§7You have to press shift");
        }

        if (ModOff.proxy.shiftPressed()) {
            tooltip.add("Use left: " + stack.getItemDamage());
            if (nbt != null) {
                if (nbt.hasKey("heal")) {
                    tooltip.add("Heal Amount: " + nbt.getInteger("heal"));
                }
                if (nbt.hasKey("saturation")) {
                    tooltip.add("Saturation Amount: " + nbt.getFloat("saturation"));
                }
            }
        }

        if (!ModOff.proxy.ctrlPressed() && !ModOff.proxy.shiftPressed()) {
            tooltip.add("Press ctrl to display extra info");
        }
    }

    @Override
    public int getHealAmount(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null) {
            if (nbt.hasKey("heal")) {
                return nbt.getInteger("heal");
            }
        }
        return 0;
    }

    @Override
    public float getSaturationModifier(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null) {
            if (nbt.hasKey("saturation")) {
                return nbt.getFloat("saturation");
            }
        }
        return 0;
    }

    public int getColor(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null) {
            if (nbt.hasKey("color")) {
                return nbt.getInteger("color");
            }
        }
        return 0xffffffff;
    }

    public static Color hex2Rgb(String colorStr) {
        Logger.info("R: " + Integer.valueOf(colorStr.substring(1, 3), 16));
        Logger.info("G: " + Integer.valueOf(colorStr.substring(3, 5), 16));
        Logger.info("B: " + Integer.valueOf(colorStr.substring(5, 7), 16));

        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16)
        );
    }

    private String colorConverter(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
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
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        World world = player.world;
        Random random = new Random();
        Vec3d vec3d = new Vec3d(((double)random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
        vec3d = vec3d.rotatePitch(-player.rotationPitch * 0.017453292F);
        vec3d = vec3d.rotateYaw(-player.rotationYaw * 0.017453292F);
        double d0 = (double)(-random.nextFloat()) * 0.6D - 0.3D;
        Vec3d vec3d1 = new Vec3d(((double)random.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
        vec3d1 = vec3d1.rotatePitch(-player.rotationPitch * 0.017453292F);
        vec3d1 = vec3d1.rotateYaw(-player.rotationYaw * 0.017453292F);
        vec3d1 = vec3d1.addVector(player.posX, player.posY + (double) player.getEyeHeight(), player.posZ);

        if (stack.getItemDamage() > 0) {
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, vec3d1.x, vec3d1.y + 0.95, vec3d1.z, vec3d.x, 0, vec3d.z);
            if (random.nextInt(2) + 1 == 1) {
                ModOff.proxy.spawnVapeSmokeRGB(world,  vec3d1.x, vec3d1.y + 0.95, vec3d1.z, 0,0,0, random.nextInt(4) + 1, hex2Rgb(colorConverter(getColor(stack))).getRed(), hex2Rgb(colorConverter(getColor(stack))).getGreen(), hex2Rgb(colorConverter(getColor(stack))).getBlue());
            } else {
                ModOff.proxy.spawnVapeSmoke(world,  vec3d1.x, vec3d1.y + 0.95, vec3d1.z, 0,0,0, random.nextInt(4) + 1);
            }
        }
        super.onUsingTick(stack, player, count);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        player.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 10.0f, world.rand.nextFloat() + 1.0f * 0.9f);
        return super.onItemRightClick(world, player, handIn);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            if (stack.getItemDamage() > 0) {
                EntityPlayer entityplayer = (EntityPlayer)entityLiving;
                entityplayer.getFoodStats().addStats(this, stack);
                worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
                this.onFoodEaten(stack, worldIn, entityplayer);
                stack.setItemDamage(stack.getItemDamage() - 1);
                if (entityplayer instanceof EntityPlayerMP) {
                    CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
                }
                if (stack.getItemDamage() == 0) {
                    NBTTagCompound nbt = stack.getTagCompound();
                    if (nbt != null) {
                        nbt.removeTag("heal");
                        nbt.removeTag("saturation");
                        nbt.removeTag("color");
                    }
                }
            }
        }
        return stack;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return stack.getItemDamage() > 0 ? EnumAction.DRINK : EnumAction.NONE;
    }

    @Override
    @SideOnly(Side.CLIENT)
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
}
