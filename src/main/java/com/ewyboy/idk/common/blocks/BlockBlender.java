package com.ewyboy.idk.common.blocks;

import com.ewyboy.bibliotheca.common.block.BlockBaseModeledFacing;
import com.ewyboy.bibliotheca.common.interfaces.IBlockRenderer;
import com.ewyboy.idk.client.BlenderTESR;
import com.ewyboy.idk.common.loaders.CreativeTabLoader;
import com.ewyboy.idk.common.tiles.TileBlender;
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
import net.minecraftforge.fml.client.registry.ClientRegistry;

import javax.annotation.Nullable;

/**
 * Created by EwyBoy
 */
public class BlockBlender extends BlockBaseModeledFacing implements ITileEntityProvider, IBlockRenderer {

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
                    // There is no item in the pedestal and the player is holding an item. We move that item
                    // to the pedestal
                    te.setStack(player.getHeldItem(hand));
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                    // Make sure the client knows about the changes in the player inventory
                    player.openContainer.detectAndSendChanges();
                }
            } else {
                // There is a stack in the pedestal. In this case we remove it and try to put it in the
                // players inventory if there is room
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

        // Return true also on the client to make sure that MC knows we handled this and will not try to place
        // a block on the client
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileBlender();
    }
}
