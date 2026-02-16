package com.devdyna.synergy.api.basebe.be;

import java.util.List;

import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import net.minecraft.core.HolderLookup.Provider;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * <b>STANDALONE BASE BE</b>
 * <br/>
 * <br/>
 * Base BE storage with menu and a custom handler to filter multiple slots on
 * automation handling
 * <br/>
 * <br/>
 * This Base BE is inspired from
 * <code>com.devdyna.synergy.api.machine.BaseMachineBE</code> to be used to
 * create simple-complex machines
 * <br/>
 * <br/>
 * |-----------------------------------------------------------------|<br/>
 * <br/>
 * <br/>
 * credit: @DevDyna
 */
@SuppressWarnings("null")
public abstract class MachineBE extends BEStorage {

    public final static String WIDTH = "width";
    public final static String HEIGHT = "height";

    protected int width;
    protected int height;
    protected List<BlockPos> area = null;

    public MachineBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        // this.width = getWidth();
        // this.height = getHeight();
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

    @Override
    public void onLoad() {
        super.onLoad();
        if (this instanceof AreaOfEffect)
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
        if (this instanceof AreaOfEffect)
            area = null;
    }

}
