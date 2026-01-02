package com.devdyna.synergy.api.basebe.be;

import org.jspecify.annotations.Nullable;

import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.init.types.zHandlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

/**
 * BEStorage with MenuType integrated
 */
@SuppressWarnings("null")
public abstract class BEStorage extends BEMenu implements ItemStorageBlock {

    public BEStorage(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public @Nullable Level getLevel() {
        return level;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        getStorage().serialize(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        getStorage().deserialize(input);
        super.loadAdditional(input);
    }

    @Override
    public ItemStacksResourceHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    @Override
    public int MachineSlots() {
        return 1;
    }
}
