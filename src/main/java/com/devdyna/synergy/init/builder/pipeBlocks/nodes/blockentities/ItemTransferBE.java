package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities;

import com.devdyna.synergy.init.builder._core.pipes.nodeLogic;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.NodeBaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class ItemTransferBE extends NodeBaseBE implements nodeLogic {

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
            // LogUtil.info("input null");
            return;
        }

        if (output == null) {
            // LogUtil.info("output null");
            return;
        }
        
        if (level.getGameTime() % 20 == 0)
            moveItems(input, output);

    }

}
