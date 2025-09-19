package com.devdyna.synergy.api.coreBE;

import com.devdyna.synergy.api.beLogic.AreaOfEffect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class BaseBE extends BlockEntity {

    public final static String RADIUS = "aoe";

    protected int radius;

    public BaseBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
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
    protected void saveAdditional(CompoundTag tag, Provider registries) {

        if (this instanceof AreaOfEffect)
            tag.putInt(RADIUS, radius);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {

        if (this instanceof AreaOfEffect)
            if (tag.contains(RADIUS))
                radius = tag.getInt(RADIUS);
        super.loadAdditional(tag, registries);
    }

}
