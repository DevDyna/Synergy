package com.devdyna.synergy.init.builder.pipe_blocks.nodes.blocks;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.BlockAbilities.tooltips.multi_simple.NodeRetrieval;
import com.devdyna.synergy.api.node.builder.NodeBaseBlock;
import com.devdyna.synergy.init.builder.pipe_blocks.nodes.blockentities.*;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class FluidRetrieval extends NodeBaseBlock implements NodeRetrieval {

    public FluidRetrieval() {
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new FluidRetrievalBE(p, s);
    }

    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    // TooltipFlag f) {
    // t.add(Component.translatable(Main.ID + "." +
    // zStatic.PipeStuff.nodes.type_retrieval));
    // t.add(Component.translatable(Main.ID + "." + zStatic.PipeStuff.pipe +
    // ".extend"));
    // }

}
