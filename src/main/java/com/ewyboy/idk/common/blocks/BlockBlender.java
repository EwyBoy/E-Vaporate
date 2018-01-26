package com.ewyboy.idk.common.blocks;

import com.ewyboy.bibliotheca.common.block.BlockBaseModeledFacing;
import com.ewyboy.bibliotheca.common.compatibilities.waila.IWailaInformationUser;
import com.ewyboy.bibliotheca.common.interfaces.IBlockRenderer;
import com.ewyboy.idk.client.BlenderTESR;
import com.ewyboy.idk.common.loaders.CreativeTabLoader;
import com.ewyboy.idk.common.tiles.TileBlender;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
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


    private TileBlender getTE(World world, BlockPos pos) {
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
            if (te.getStack().isEmpty()) {
                if (!player.getHeldItem(hand).isEmpty()) {
                    // There is no item in the blender and the player is holding an item. We move that item to blender
                    //If player is clicking with a fluid container it fills the blender
                    if (FluidUtil.interactWithFluidHandler(player, hand, world, pos, side)) {
                        return true;
                    } else {
                        ItemStack stack = player.getHeldItem(hand).copy();
                        int stackSize  = stack.getCount();

                        if (player.getHeldItem(hand).getCount() > 16) {
                            stack.setCount(16);
                            te.setStack(stack);
                            ItemStack returnStack = player.getHeldItem(hand).copy();
                            returnStack.shrink(16);
                        } else {
                            stack.setCount(stackSize);
                            te.setStack(stack);
                            ItemStack returnStack = player.getHeldItem(hand).copy();
                            returnStack.shrink(stackSize);
                        }
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                    }
                    // Make sure the client knows about the changes in the player inventory
                    player.openContainer.detectAndSendChanges();
                }
            } else {
                ItemStack stack = te.getStack();
                // There is a stack in the blender. In this case we remove it and try to put it in the players inventory if there is room

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
        // Return true also on the client to make sure that MC knows we handled this and will not try to place a block on the client
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
            if (blender.tank.getFluid() != null) {
                list.add("Fluid: " + blender.tank.getFluid().getFluid().getName());
            }
            if (!blender.stack.isEmpty()) {
                list.add("Item: " + blender.stack.getItem().getItemStackDisplayName(blender.stack));
                list.add("Amount: " + blender.getStack().getCount());
            }
        return list;
    }
}
