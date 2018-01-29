package com.ewyboy.idk.common.blocks;

import com.ewyboy.bibliotheca.common.block.BlockBaseModeledFacing;
import com.ewyboy.bibliotheca.common.compatibilities.waila.IWailaInformationUser;
import com.ewyboy.bibliotheca.common.interfaces.IBlockRenderer;
import com.ewyboy.idk.client.BlenderTESR;
import com.ewyboy.idk.common.loaders.CreativeTabLoader;
import com.ewyboy.idk.common.tiles.TileBlender;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by EwyBoy
 */
public class BlockBlender extends BlockBaseModeledFacing implements ITileEntityProvider, IBlockRenderer, IWailaInformationUser {

    public static final PropertyBool ENABLED = PropertyBool.create("enabled");

    public BlockBlender() {
        super(Material.IRON);
        setHardness(1.0f);
        setCreativeTab(CreativeTabLoader.tabIDK);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        setDefaultState(blockState.getBaseState().withProperty(ENABLED, false));
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos p_189540_5_) {
        int powered = world.isBlockIndirectlyGettingPowered(pos);
        world.setBlockState(pos, state.withProperty(ENABLED, powered > 0), 3);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(ENABLED, (meta & 8) != 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex() + (state.getValue(ENABLED) ? 8 : 0);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, ENABLED);
    }


    public static TileBlender getTE(World world, BlockPos pos) {
        return (TileBlender) world.getTileEntity(pos);
    }

    @Override
    public void registerBlockRenderer() {
        super.registerBlockRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlender.class, new BlenderTESR());
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileBlender te = getTE(world, pos);
            if (FluidUtil.interactWithFluidHandler(player, hand, world, pos, side)) {
                player.openContainer.detectAndSendChanges();
                te.changed();
                te.updateContainingBlockInfo();
                return true;
            } else {
                if (te.getStack().isEmpty()) {
                    if (!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() instanceof ItemFood) {
                        // There is no item in the blender and the player is holding an item. We move that item to the pedestal
                        te.setStack(player.getHeldItem(hand));
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                        // Make sure the client knows about the changes in the player inventory
                        player.openContainer.detectAndSendChanges();
                    }
                } else {
                    // There is a stack in the blender. In this case we remove it and try to put it in the players inventory if there is room
                    ItemStack stack = te.getStack();
                    te.setStack(ItemStack.EMPTY);
                    if (!player.inventory.addItemStackToInventory(stack)) {
                        // Not possible. Throw item in the world
                        EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                        world.spawnEntity(entityItem);
                    } else {
                        player.openContainer.detectAndSendChanges();
                    }
                }
            }
        }
        // Return true also on the client to make sure that MC knows we handled this and will not try to place
        // a block on the client
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileBlender();
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> list, IWailaDataAccessor accessor, IWailaConfigHandler iWailaConfigHandler) {
        TileBlender blender = getTE(accessor.getWorld(), accessor.getPosition());

        if (blender.getStack().isEmpty()) {
            list.add("Add your favorite food to the blender");
        } else  {
            list.add("Item: " + blender.stack.getItem().getItemStackDisplayName(blender.stack));
            list.add("Amount: " + blender.getStack().getCount());
        }

        if (blender.getTank().getFluid() == null) {
            list.add("Add some water to the blender");
        } else {
            list.add("Fluid: " + blender.tank.getFluid().getFluid().getName());
        }

        //list.add("State: " + accessor.getWorld().getBlockState(accessor.getPosition()).getValue(ENABLED));

        return list;
    }
}
