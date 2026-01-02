package com.devdyna.synergy.api.basebe.be;

import com.devdyna.synergy.api.beLogic.AreaOfEffect;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

@SuppressWarnings("null")
public abstract class MachineBE extends BEStorage {

    public final static String RADIUS = "aoe";

    protected int radius;

    public MachineBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
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
        if (this instanceof AreaOfEffect)
            output.putInt(RADIUS, radius);
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        if (this instanceof AreaOfEffect)
            if (input.getInt(RADIUS).isPresent())
                radius = input.getInt(RADIUS).get();
        super.loadAdditional(input);
    }

}
