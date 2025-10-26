package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.node.builder.NodeBaseBlock;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities.*;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class EnergyTransfer extends NodeBaseBlock {

    public EnergyTransfer() {
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new EnergyTransferBE(p, s);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.PipeStuff.nodes.type_transfer));
        t.add(Component.translatable(Main.ID + "." + zStatic.PipeStuff.pipe + ".extend"));
    }

}
