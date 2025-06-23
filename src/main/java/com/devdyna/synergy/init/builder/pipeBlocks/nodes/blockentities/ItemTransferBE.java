package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities;

import com.devdyna.synergy.init.builder.pipeBlocks.nodes.NodeBaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class ItemTransferBE extends NodeBaseBE  {

    public ItemTransferBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public ItemTransferBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.ITEM_TRANSFER.get(), pos, blockState);
    }

    @Override
    public void tickServer() {

        if (level == null)
            return;

        var input = getInputBlock(getBlockState(), level, getBlockPos());
        var output = getOutputBlock(getBlockState(), level, getBlockPos());

        if (input == null) {
            return;
        }

        if (output == null) {
            return;
        }
        
        if (level.getGameTime() % getTickDelay() == 0)
            moveItems(input, output);

    }

    @Override
    public int getTickDelay() {
        return 20;
    }

}
