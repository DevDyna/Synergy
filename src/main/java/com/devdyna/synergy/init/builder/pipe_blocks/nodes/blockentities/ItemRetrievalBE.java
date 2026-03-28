package com.devdyna.synergy.init.builder.pipe_blocks.nodes.blockentities;

import com.devdyna.synergy.api.node_pipe.ItemNodeType;
import com.devdyna.synergy.api.node_pipe.builder.NodeBaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings({ "null" })
public class ItemRetrievalBE extends NodeBaseBE implements ItemNodeType {

    public ItemRetrievalBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public ItemRetrievalBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.ITEM_RETRIEVAL.get(), pos, blockState);
    }

    @Override
    protected void executeItem(IItemHandler input, IItemHandler output) {
        moveItems(output, input, getStack(getCapType()));
    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.ItemHandler.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getInputPos();
    }

    @Override
    public ItemStack getItemStack() {
        return getFirstItem((IItemHandler) getOutputCap());
    }

    @Override
    public BlockPos defineInput() {
        return getOutputPos();
    }

    @Override
    public Direction getInputDirection() {
        return getDirectionFromPath();
    }

    @Override
    public Direction getOutputDirection() {
        return getNodeDirection().getOpposite();
    }

}
