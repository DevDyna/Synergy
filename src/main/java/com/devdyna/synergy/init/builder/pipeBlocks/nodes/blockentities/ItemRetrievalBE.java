package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities;

import com.devdyna.synergy.api.node.builder.NodeBaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class ItemRetrievalBE extends NodeBaseBE {

    public ItemRetrievalBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public ItemRetrievalBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.ITEM_RETRIEVAL.get(), pos, blockState);
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
            moveItems(output, input);

    }

    @Override
    public int getTickDelay() {
        return 20;
    }

}
