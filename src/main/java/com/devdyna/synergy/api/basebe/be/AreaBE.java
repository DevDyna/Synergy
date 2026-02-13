package com.devdyna.synergy.api.basebe.be;

import java.util.List;

import com.devdyna.synergy.api.beLogic.AreaOfEffect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public abstract class AreaBE extends TickingBE implements AreaOfEffect {

    public final static String WIDTH = "width";
    public final static String HEIGHT = "height";

    protected int width;
    protected int height;
    protected List<BlockPos> area = null;

    public AreaBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.width = getWidth();
        this.height = getHeight();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider registries) {

        if (this instanceof AreaOfEffect) {
            tag.putInt(HEIGHT, height);
            tag.putInt(WIDTH, width);
        }
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {

        if (this instanceof AreaOfEffect) {
            if (tag.contains(HEIGHT))
                height = tag.getInt(HEIGHT);
            if (tag.contains(WIDTH))
                width = tag.getInt(WIDTH);
        }
        super.loadAdditional(tag, registries);
    }

    // required to sync client to server data
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level.isClientSide())
            rebuildArea();
    }

    private void rebuildArea() {
        if (this instanceof AreaOfEffect be)
            if (level != null)
                area = be.getArea();
    }

    @Override
    public CompoundTag getUpdateTag(Provider lookupProvider) {
        CompoundTag tag = super.getUpdateTag(lookupProvider);
        if (this instanceof AreaOfEffect) {
            tag.putInt(HEIGHT, height);
            tag.putInt(WIDTH, width);
        }
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, Provider lookupProvider) {
        super.handleUpdateTag(tag, lookupProvider);
        if (this instanceof AreaOfEffect) {
            if (tag.contains(HEIGHT))
                height = tag.getInt(HEIGHT);
            if (tag.contains(WIDTH))
                width = tag.getInt(WIDTH);
            rebuildArea();
        }

    }

    public void resetAOE() {
        area = null;
    }

}
