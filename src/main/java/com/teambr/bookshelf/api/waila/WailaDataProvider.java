package com.teambr.bookshelf.api.waila;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class WailaDataProvider implements IWailaDataProvider {

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof IWaila) {
            IWaila tile = (IWaila) accessor.getTileEntity();
            return tile.returnWailaStack(accessor, config);
        }
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if(accessor.getTileEntity() instanceof IWaila) {
            IWaila tile = (IWaila) accessor.getTileEntity();
            tile.returnWailaHead(currenttip);
        }
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if(accessor.getTileEntity() instanceof IWaila) {
            IWaila tile = (IWaila) accessor.getTileEntity();
            tile.returnWailaBody(currenttip);
        }
        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if(accessor.getTileEntity() instanceof IWaila) {
            IWaila tile = (IWaila) accessor.getTileEntity();
            tile.returnWailaTail(currenttip);
        }
        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
        if (te instanceof IWaila) {
            NBTTagCompound returnTag = ((IWaila) te).returnNBTData(player, te, tag, world, x, y, z);
            if (returnTag != null)
                return returnTag;
        }
        return tag;
    }

    @SuppressWarnings("unused")
    public static void callbackRegister(IWailaRegistrar registrar) {
        registrar.registerHeadProvider(new WailaDataProvider(), IWaila.class);
        registrar.registerBodyProvider(new WailaDataProvider(), IWaila.class);
        registrar.registerTailProvider(new WailaDataProvider(), IWaila.class);
        registrar.registerStackProvider(new WailaDataProvider(), IWaila.class);
        registrar.registerNBTProvider(new WailaDataProvider(), IWaila.class);
    }

    @SuppressWarnings("unused")
    public static void callbackRegisterServer(IWailaRegistrar registrar) {
        registrar.registerNBTProvider(new WailaDataProvider(), IWaila.class);
    }
}