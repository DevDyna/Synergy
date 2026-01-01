package com.devdyna.synergy.init.builder.pipe_blocks.nodes.blocks;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.BlockAbilities.tooltips.multi_simple.NodeTransfer;
import com.devdyna.synergy.api.node.builder.NodeBaseBlock;
import com.devdyna.synergy.init.builder.pipe_blocks.nodes.blockentities.*;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class EnergyTransfer extends NodeBaseBlock implements NodeTransfer {

    public EnergyTransfer() {
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new EnergyTransferBE(p, s);
    }

    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    //         TooltipFlag f) {
    //     t.add(Component.translatable(Main.ID + "." + zStatic.PipeStuff.nodes.type_transfer));
    //     t.add(Component.translatable(Main.ID + "." + zStatic.PipeStuff.pipe + ".extend"));
    // }

}
