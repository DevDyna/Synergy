package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities;

import java.util.ArrayList;

import com.devdyna.synergy.api.node.nodeType;
import com.devdyna.synergy.api.node.builder.NodeBaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class ItemProviderBE extends NodeBaseBE {

    public ItemProviderBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public ItemProviderBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.ITEM_PROVIDER.get(), pos, blockState);
    }

    public int getTickDelay() {
        return 20;
    }

    @Override
    public void tickServer() {

        if (level == null)
            return;

        var output = getOutputBlock(getBlockState(), level, getBlockPos());

        if (output == null)
            return;

        var direction = getBlockState().getValue(nodeType.FACING);
        var blockGen = getBlockPos().relative(direction);
        var belowGen = blockGen.relative(direction);

        ArrayList<Block> conditions = new ArrayList<>();
        ArrayList<Boolean> resultCheck = new ArrayList<>();

        Item resulItem = null;

        if (level.getBlockState(blockGen).is(Blocks.COBBLESTONE)) {
            conditions.add(Blocks.LAVA);
            conditions.add(Blocks.WATER);
            resulItem = Blocks.COBBLESTONE.asItem();
        }

        if (level.getBlockState(blockGen).is(Blocks.BASALT) && level.getBlockState(belowGen).is(Blocks.SOUL_SOIL)) {
            conditions.add(Blocks.LAVA);
            conditions.add(Blocks.BLUE_ICE);
            resulItem = Blocks.BASALT.asItem();
        }
        boolean found = false;
        for (Block blockToCheck : conditions) {
            for (Direction dir : Direction.values()) {
                if (dir != direction && dir != direction.getOpposite()) {
                    if (level.getBlockState(blockGen.relative(dir)).is(blockToCheck)) {
                        found = true;
                        break;
                    }
                }
            }
            resultCheck.add(found);
            found = false;
        }

        var finalValue = resultCheck.stream().allMatch(Boolean::booleanValue);

        if (resulItem != null && finalValue)
            if (level.getGameTime() % getTickDelay() == 0)
                itemToOutput(new ItemStack(resulItem, 1), output);

    }

}
