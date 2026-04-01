package com.devdyna.synergy.api.basebe.be;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public abstract class TickingTankBE extends BETank {

    public TickingTankBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    /**
     * Server only ticking
     * <br/>
     * <br/>
     * Level-null SAFE
     * <br/>
     * <br/>
     * Useful for block events
     * <br/>
     * <br/>
     * Dont require super!
     */
    public void tickServer() {
    }

    /**
     * Client only ticking
     * <br/>
     * <br/>
     * Level-null SAFE
     * <br/>
     * <br/>
     * Useful for player events
     * <br/>
     * <br/>
     * Dont require super!
     */
    public void tickClient() {
    }

    /**
     * Client and Server ticking
     * <br/>
     * <br/>
     * Level-null SAFE
     * <br/>
     * <br/>
     * Usefull for particles
     * <br/>
     * <br/>
     * Dont require super!
     */
    public void tickBoth() {
    }

    // required to sync client to server data
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    /**
     * update this specific BE
     */
    public void update() {
        setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

}
