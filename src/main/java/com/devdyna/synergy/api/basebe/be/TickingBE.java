package com.devdyna.synergy.api.basebe.be;

import java.util.List;

import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import com.devdyna.synergy.api.beLogic.SimpleAOE;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

@SuppressWarnings("null")
public class TickingBE extends BlockEntity {

    public final static String RADIUS = "aoe";
    public final static String HEIGHT = "height";

    protected int radius;
    protected int height;
    protected List<BlockPos> area = null;

    public TickingBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    /**
     * Server only ticking
     * Useful for block events
     */
    public void tickServer() {
    }

    /**
     * Client only ticking
     * Useful for player events
     */
    public void tickClient() {
    }

    /**
     * Client and Server ticking
     * 
     * Usefull for particles
     */
    public void tickBoth() {
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        if (this instanceof SimpleAOE)
            output.putInt(RADIUS, radius);
        if (this instanceof AreaOfEffect)
            output.putInt(HEIGHT, height);
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        if (this instanceof SimpleAOE)
            if (input.getInt(RADIUS).isPresent())
                radius = input.getInt(RADIUS).get();
        if (this instanceof AreaOfEffect)
            if (input.getInt(HEIGHT).isPresent())
                height = input.getInt(HEIGHT).get();
        super.loadAdditional(input);
    }

    // required to sync client to server data
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void rebuildArea() {
        if (this instanceof AreaOfEffect be)
            if (level != null)
                area = be.getAreaSelection(level,
                        getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING),
                        getBlockPos());
    }

    public boolean isAreaNull() {
        return area == null;
    }

    @Override
    public CompoundTag getUpdateTag(Provider lookupProvider) {
        CompoundTag tag = super.getUpdateTag(lookupProvider);
        if (this instanceof SimpleAOE)
            tag.putInt(RADIUS, radius);
        if (this instanceof AreaOfEffect)
            tag.putInt(HEIGHT, height);
        return tag;
    }

    @Override
    public void handleUpdateTag(ValueInput input) {
        super.handleUpdateTag(input);
        if (this instanceof SimpleAOE)
            radius = input.getInt(RADIUS).get();
        if (this instanceof AreaOfEffect) {
            height = input.getInt(HEIGHT).get();
            rebuildArea();
        }
    }

    public void updateAOE() {
        area = null;
    }

}
