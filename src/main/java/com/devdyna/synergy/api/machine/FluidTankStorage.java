package com.devdyna.synergy.api.machine;

import com.devdyna.synergy.api.beLogic.SimpleFluidStorage;
import com.devdyna.synergy.api.utils.x;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public interface FluidTankStorage extends SimpleFluidStorage {

    public enum FluidTankType {
        INPUT(),
        OUTPUT();

    
    
        public class Codec{
            public static final StreamCodec<ByteBuf, FluidTankType> STREAM =
        ByteBufCodecs.idMapper(
                id -> FluidTankType.values()[id],
                FluidTankType::ordinal
        );
        }
    
    }

    /**
     * atm not really used
     */
    abstract FluidTankType getTankIOType();

    default ItemStack getAsBucket(){
        return x.item(getFluidStorage().getFluid().getFluid().getBucket());
    }

}
