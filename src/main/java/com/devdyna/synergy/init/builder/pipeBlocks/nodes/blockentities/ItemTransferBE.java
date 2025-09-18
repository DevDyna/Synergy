package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities;

import com.devdyna.synergy.api.node.builder.NodeBaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings({ "null" })
public class ItemTransferBE extends NodeBaseBE {

    public ItemTransferBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public ItemTransferBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.ITEM_TRANSFER.get(), pos, blockState);
    }

    @Override
    protected void executeItem(BlockPos inputPos, IItemHandler input, BlockPos outputPos, IItemHandler output) {
        moveItems(input, output, 1);
    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.ItemHandler.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getOutput();
    }

}
