package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities;

import java.util.ArrayList;

import com.devdyna.synergy.api.node.nodeType;
import com.devdyna.synergy.api.node.builder.NodeBaseBE;
import com.devdyna.synergy.init.dataMaps.zDataMaps;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings({ "null", "unchecked" })
public class ItemProviderBE extends NodeBaseBE {

    public ItemProviderBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public ItemProviderBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.ITEM_PROVIDER.get(), pos, blockState);
    }

    @Override
    protected void execute(BlockPos input, BlockPos output) {

        var outState = level.getBlockState(output);
        var outBE = level.getBlockEntity(output);
        if (outBE == null)
            return;
        var outCap = getCapType().getCapability(level, output, outState, outBE, null);
        if (outCap == null)
            return;

        // TODO probably it could be more optimized
        // TODO jei

        var direction = getBlockState().getValue(nodeType.FACING);
        var blockGen = getBlockPos().relative(direction);
        var belowGen = blockGen.relative(direction);

        ArrayList<Block> conditions = new ArrayList<>();
        ArrayList<Boolean> resultCheck = new ArrayList<>();

        Item resulItem = level.getBlockState(blockGen).getBlock().asItem();

        var dataHolder = new ItemStack(resulItem).getItemHolder()
                .getData(zDataMaps.PROVIDER_RECIPES);

        if (dataHolder != null) {

            for (BlockState blocks : dataHolder.blocksToCheck()) {
                conditions.add(blocks.getBlock());
            }

        } else {
            resulItem = null;
        }

        boolean found = false;

        if (resulItem != null) {
            for (Block blockToCheck : conditions) {
                for (Direction dir : Direction.values())
                    if (dir != direction && dir != direction.getOpposite()) {
                        if (level.getBlockState(blockGen.relative(dir)).is(blockToCheck)) {
                            found = true;
                            break;
                        }
                    }

                resultCheck.add(found);
                found = false;
            }

            // DONT MIX OTHER CONDITIONS OR IT WILL BREAK!
            var finalValue = resultCheck.stream().allMatch(Boolean::booleanValue);

            var below = dataHolder.belowBlock().isAir() ? true
                    : level.getBlockState(belowGen).is(dataHolder.belowBlock().getBlock());

            if (finalValue && below)
                // if (level.getGameTime() % getTickDelay() == 0)
                itemToOutput(new ItemStack(resulItem, 1), (IItemHandler) outCap);
        }
    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.ItemHandler.BLOCK;
    }

}
